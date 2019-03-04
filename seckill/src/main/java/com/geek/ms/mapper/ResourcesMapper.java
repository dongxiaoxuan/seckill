package com.geek.ms.mapper;

import java.util.List;
import java.util.Map;

import com.geek.ms.pojo.vo.Resources;

public interface ResourcesMapper {
	
	List<Resources> queryAll();
	
	List<Resources> loadUserResources(Map<String,Object> map);
	
	List<Resources> selectPageAll(int start, int length);
	
}
