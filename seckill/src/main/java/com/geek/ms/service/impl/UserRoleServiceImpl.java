package com.geek.ms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Service; 
import org.springframework.transaction.annotation.Propagation; 
import org.springframework.transaction.annotation.Transactional;

import com.geek.ms.pojo.vo.UserRole;
import com.geek.ms.service.UserRoleService; 
import com.geek.ms.shiro.MyShiroRealm;
  
@Service 
public class UserRoleServiceImpl implements UserRoleService{

	@Autowired 
	private MyShiroRealm myShiroRealm;
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED,readOnly=false,rollbackFor={Exception.class}) 
	public void addUserRole(UserRole userRole) {
		// TODO Auto-generated method stub
		
		//1.userRoleMapper.add(userRole); 2.更新权限↓
		List<Integer> userIds = null; 
		myShiroRealm.clearUserAuthByUserId(userIds); 
	}
  
}
 
