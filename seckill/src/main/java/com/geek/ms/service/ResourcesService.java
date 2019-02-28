package com.geek.ms.service;

import java.util.List;
import java.util.Map;

import com.geek.ms.model.Resources;

public interface ResourcesService {
	
	//加载所有资源，用于在shiro设置所有需要authc授权的资源
	List<Resources> queryAll();
	//通过指定条件查询资源，用于 1：显示登录用户独有菜单。2：授权时查询该用户所有资源
	List<Resources> loadUserResources(Map<String,Object> map);
	
	List<Resources> selectPageAll(int start, int length);
	
	void add(Resources resources);
	
	void delete(Resources resources);
	
}
