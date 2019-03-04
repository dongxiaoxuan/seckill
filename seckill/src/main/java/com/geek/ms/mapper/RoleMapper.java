package com.geek.ms.mapper;

import java.util.List;
import java.util.Map;

import com.geek.ms.pojo.vo.Resources;
import com.geek.ms.pojo.vo.Role;

public interface RoleMapper {

	List<Role> selectAll();
	
	List<Role> loadUserRole(Map<String,Object> map);
	
	List<Role> selectPageAll(int start, int length);
	
	List<Resources> selectOtherUrl(int id);
}
