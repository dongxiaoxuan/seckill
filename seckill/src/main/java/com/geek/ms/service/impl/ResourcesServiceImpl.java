package com.geek.ms.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.geek.ms.mapper.ResourcesMapper;
import com.geek.ms.model.Resources;
import com.geek.ms.service.ResourcesService;

@Service
public class ResourcesServiceImpl implements ResourcesService{

	@Autowired
	private ResourcesMapper resourcesMapper;
	
	@Override
	public List<Resources> queryAll() {
		// TODO Auto-generated method stub
		return resourcesMapper.queryAll();
	}

	@Override
	public List<Resources> loadUserResources(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return resourcesMapper.loadUserResources(map);
	}

	@Override
	public List<Resources> selectPageAll(int start, int length) {
		// TODO Auto-generated method stub
		return resourcesMapper.selectPageAll(start, length);
	}

	@Override
	public void add(Resources resources) {
		// TODO Auto-generated method stub
		/*
		 * add
		 */
	}

	@Override
	public void delete(Resources resources) {
		// TODO Auto-generated method stub
		/*
		 * delete
		 */
	}

}
