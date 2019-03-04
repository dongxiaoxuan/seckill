package com.geek.ms.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.geek.ms.mapper.ResourcesMapper;
import com.geek.ms.mapper.RoleMapper;
import com.geek.ms.pojo.vo.Resources;
import com.geek.ms.pojo.vo.Role;
import com.geek.ms.service.RoleService;
import com.geek.ms.shiro.MyShiroRealm;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleMapper roleMapper;

	@Autowired
	private ResourcesMapper resourcesMapper;
	
	@Autowired 
	private MyShiroRealm myShiroRealm;

	@Override
	public List<Role> selectPageAll(int start, int length) {
		return roleMapper.selectPageAll(start, length);
	}

	@Override
	public List<Role> selectAll() {
		return roleMapper.selectAll();
	}

	@Override
	public List<Resources> queryAll() {
		return resourcesMapper.queryAll();
	}

	@Override
	public List<Resources> selectOtherUrl(int id) {
		return roleMapper.selectOtherUrl(id);
	}

	
	@Override 
	//删除角色时有两个步骤 保证一致性
	@Transactional(propagation=Propagation.REQUIRED,readOnly=false,rollbackFor={Exception.class}) 
	public void deleteRole(int id) { 
  
		//1.删除角色 2.删除该角色资源
  
  }

	@Override
	public List<Role> loadUserRole(Map<String, Object> map) {
		return roleMapper.loadUserRole(map);
	}
	
}
