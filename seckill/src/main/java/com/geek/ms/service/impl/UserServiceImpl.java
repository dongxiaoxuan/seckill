package com.geek.ms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.geek.ms.mapper.RoleMapper;
import com.geek.ms.mapper.UserMapper;
import com.geek.ms.mapper.UserRoleMapper;
import com.geek.ms.model.Role;
import com.geek.ms.model.User;
import com.geek.ms.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;
	@Autowired
	private RoleMapper roleMapper;
	@Autowired
	private UserRoleMapper userRoleMapper;

	@Override
	public User selectByUsername(String username) {
		return userMapper.selectByUsername(username);
	}

	@Override
	public List<User> selectPageAll(int start, int length) {
		return userMapper.selectPageAll(start, length);
	}

	@Override
	public List<User> selectAll() {
		return userMapper.selectAll();
	}

	@Override
	public void addUser(User user) {
		userMapper.addUser(user);
	}

	@Override
	public void setEnable(User user) {
		userMapper.setEnable(user);
	}

	@Override
	public List<Role> selectAllRole() {
		return roleMapper.selectAll();
	}

	@Override
	public void setUserRole(Integer userId, Integer roleId) {
		userRoleMapper.setUserRole(userId, roleId);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED,readOnly=false,rollbackFor={Exception.class}) 
	public void deleteUser(User user) { 
	//1.删除用户 2.删除用户角色
	
	}

	@Override
	public Integer selectUserRoleByUserId(Integer userId) {
		return userRoleMapper.selectUserRoleByUserId(userId);
	}

	@Override
	public void updateUserRole(Integer userId, Integer roleId) {
		userRoleMapper.updateUserRole(userId, roleId);
	}
}
