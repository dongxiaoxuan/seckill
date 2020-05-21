package com.geek.ms.mapper;

import java.util.List;

public interface RoleResourcesMapper {

	void addRoleResource(Integer roleId, Integer reId);
	
	void deleteRoleResource(Integer roleId, Integer reId);
	
	List<Integer> findeUserIdByRoleId(Integer roleId);
	
}
