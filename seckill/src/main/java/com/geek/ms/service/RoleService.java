package com.geek.ms.service;

import java.util.List;
import java.util.Map;

import com.geek.ms.pojo.vo.Resources;
import com.geek.ms.pojo.vo.Role;

public interface RoleService {
	
	List<Role> selectAll();
	
	List<Role> loadUserRole(Map<String,Object> map);
	
	List<Role> selectPageAll(int start, int length);
	
	List<Resources> queryAll();
	
	List<Resources> selectOtherUrl(int id);
	
	void deleteRole(int id);
	
}
