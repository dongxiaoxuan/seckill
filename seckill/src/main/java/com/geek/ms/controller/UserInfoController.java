package com.geek.ms.controller;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.geek.ms.pojo.vo.Role;
import com.geek.ms.pojo.vo.UserInfo;
import com.geek.ms.pojo.vo.UserInfoDetail;
import com.geek.ms.service.UserInfoService;

@Controller
@RequestMapping("/userinfo")
public class UserInfoController {

	@Autowired
	private UserInfoService userInfoService;
	
	@RequestMapping
	public String loadUserInfo(Model model) {
		Map<UserInfoDetail, String> commenMap = new HashMap<>();
		Map<UserInfoDetail, String> privateMap = new HashMap<>();
		Map<String, String> roleNameMap = new HashMap<>();
		List<Integer> commentEnables = new ArrayList<Integer>();
		List<Integer> privateEnables = new ArrayList<Integer>();
		int privatenumber = 0;
		Role role = null;
		UserInfo userInfoes = userInfoService.selectAllUserInfo((int)SecurityUtils.getSubject().getSession().getAttribute("userSessionId"));
		List<UserInfoDetail> userInfoDetails = userInfoService.selectAllUserInfoDetail();
		@SuppressWarnings("unchecked")
		List<Role> roleList = (List<Role>) SecurityUtils.getSubject().getSession().getAttribute("roleSession");
		Class<? extends UserInfo> cls = userInfoes.getClass();
		Field[] fields = cls.getDeclaredFields();
		for(int i = 0; i < fields.length; i ++) {
			Field f = fields[i];
			//通过反射访问private字段时，必须设置Accessible为true才可以访问
			f.setAccessible(true);
			try {
				for(int j = 0; j < userInfoDetails.size(); j ++) {
					if(f.getName().equals(userInfoDetails.get(j).getColName())) {
						if((!f.getName().equals("id")) && (!f.getName().equals("userId")) && (!f.getName().equals("serialVersionUID")) ) {
							
							if(userInfoDetails.get(j).getRoleId() == 0) {
								commenMap.put(userInfoDetails.get(j),  String.valueOf(f.get(userInfoes)));
								commentEnables.add(userInfoDetails.get(j).getEnable());
							}else {
								if((privatenumber + 1) <= roleList.size() && roleList != null) {
									role = roleList.get(privatenumber);
									roleNameMap.put(role.getName(),role.getRoleName());
									privatenumber ++;
								}
								if(userInfoDetails.get(j).getRoleId() == role.getId()) {
									privateMap.put(userInfoDetails.get(j),  String.valueOf(f.get(userInfoes)));
									privateEnables.add(userInfoDetails.get(j).getEnable());
								}
							}
						}
					}
				}
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		/*
		 * commenMap && privateMap: Map{key : chineseCol, value : col}
		 * commentResult && privateResult: Map{key : enable, value : cMap{} || pMap{}}
		 */
		model.addAttribute("roleNameMap", roleNameMap);
		model.addAttribute("commentMap", commenMap);
		model.addAttribute("privateMap", privateMap);
		model.addAttribute("commentEnables", commentEnables);
		model.addAttribute("privateEnables", privateEnables);
		return "info";
	}
	
	@RequestMapping("/updateInfo")
	public String update(UserInfo u) {
		u.setUserId((int)SecurityUtils.getSubject().getSession().getAttribute("userSessionId"));
		userInfoService.updateUserInfoByUserId(u);
		return "redirect:/userinfo";
	}
	
}
