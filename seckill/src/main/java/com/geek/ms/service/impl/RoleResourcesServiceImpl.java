package com.geek.ms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation; 
import org.springframework.transaction.annotation.Transactional;

import com.geek.ms.model.RoleResources; 
import com.geek.ms.service.RoleResourcesService; 
import com.geek.ms.shiro.MyShiroRealm;

@Service 
public class RoleResourcesServiceImpl implements RoleResourcesService{
  
	@Autowired private MyShiroRealm myShiroRealm;
  
	//更新权限
	@Override
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation=Propagation.REQUIRED, readOnly=false, rollbackFor={Exception.class}) 
	public void addRoleResources(RoleResources roleResources) { 
		// TODO Auto-generated method stub
  
//  1.roleResourcesmapper.add(); 
//  2.更行权限缓存↓ 
//  List userids = userRoleMapper.findeUserIdByRoleId(roleId);
		
		List<Integer> userids = null; 
		myShiroRealm.clearUserAuthByUserId(userids); 
	}
}
 
