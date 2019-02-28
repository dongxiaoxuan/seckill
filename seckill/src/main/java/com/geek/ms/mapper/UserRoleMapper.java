package com.geek.ms.mapper;

public interface UserRoleMapper {
	
	Integer selectUserRoleByUserId(Integer userId);

	void setUserRole(Integer userId, Integer roleId);
	
	void updateUserRole(Integer userId, Integer roleId);
	
}
