package com.geek.ms.service;

import java.util.List;

import com.geek.ms.pojo.vo.Role;
import com.geek.ms.pojo.vo.User;

public interface UserService {

	User selectByUsername(String username);
	
	List<User> selectPageAll(int start, int length);
	
	List<User> selectAll();
	
	void addUser(User user);
	//封号
	void setEnable(User user);
	//删号
	void deleteUser(User user);
	
	List<Role> selectAllRole();
	
	Integer selectUserRoleByUserId(Integer userId);

	void setUserRole(Integer userId, Integer roleId);
	
	void updateUserRole(Integer userId, Integer roleId);
}
