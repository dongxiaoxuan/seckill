package com.geek.ms.shiro;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.mgt.RealmSecurityManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.apache.shiro.util.ByteSource;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;

import com.geek.ms.pojo.vo.Resources;
import com.geek.ms.pojo.vo.Role;
import com.geek.ms.pojo.vo.User;
import com.geek.ms.service.ResourcesService;
import com.geek.ms.service.RoleService;
import com.geek.ms.service.UserService;

public class MyShiroRealm extends AuthorizingRealm{
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ResourcesService resourcesService;
	
	@Autowired
	private RoleService roleSerivce;
	
	@Autowired
    private RedisSessionDAO redisSessionDAO;

	//授权
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		User user = (User) SecurityUtils.getSubject().getPrincipal();
		Map<String, Object> map = new HashMap<>();
		map.put("userId", user.getId());
		List<Resources> resourcesList = resourcesService.loadUserResources(map);
		System.out.println("resourcesList:" + resourcesList);
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		for(Resources resources : resourcesList) {
			info.addStringPermission(resources.getUrl());
		}
		List<Role> roleList = roleSerivce.loadUserRole(map);
		for(Role role : roleList) {
			info.addRole(role.getName());
		}
		System.out.println("realm:"+roleList);
		Session session = SecurityUtils.getSubject().getSession();
		session.setAttribute("roleSession", roleList);
		session.setAttribute("resourcesSession", resourcesList);
		return info;
	}
	
	//认证
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		
		String username = (String)token.getPrincipal();
		User user = userService.selectByUsername(username);
		if (user == null)
			throw new UnknownAccountException();
		if(0 == user.getEnable())
			throw new LockedAccountException();
		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
				user, //用户
				user.getPassword(), //数据库加盐密码
				ByteSource.Util.bytes(username), //token生成的加盐密码
				getName()
		);
		//当验证通过后，把用户信息放在session里
		Session session = SecurityUtils.getSubject().getSession();
		session.setAttribute("userSession", user);
		session.setAttribute("userSessionId", user.getId());
		return authenticationInfo;
	}
	
	/**
     * 根据userId 清除当前session存在的用户的权限缓存
     * userrole和roleresources时调用
     * @param userIds 已经修改了权限的userId
     */
    public void clearUserAuthByUserId(List<Integer> userIds){
        if(null == userIds || userIds.size() == 0)	return ;
        //获取所有session
        Collection<Session> sessions = redisSessionDAO.getActiveSessions();
        //定义返回
        List<SimplePrincipalCollection> list = new ArrayList<SimplePrincipalCollection>();
        for (Session session:sessions){
            //获取session登录信息。
            Object obj = session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
            if(null != obj && obj instanceof SimplePrincipalCollection){
                //强转
                SimplePrincipalCollection spc = (SimplePrincipalCollection)obj;
                //判断用户，匹配用户ID。
                obj = spc.getPrimaryPrincipal();
                if(null != obj && obj instanceof User){
                    User user = (User) obj;
                    System.out.println("user:"+user);
                    //比较用户ID，符合即加入集合
                    if(null != user && userIds.contains(user.getId())){
                        list.add(spc);
                    }
                }
            }
        }
        RealmSecurityManager securityManager =
                (RealmSecurityManager) SecurityUtils.getSecurityManager();
        MyShiroRealm realm = (MyShiroRealm)securityManager.getRealms().iterator().next();
        for (SimplePrincipalCollection simplePrincipalCollection : list) {
            realm.clearCachedAuthorizationInfo(simplePrincipalCollection);
        }
    }
}
