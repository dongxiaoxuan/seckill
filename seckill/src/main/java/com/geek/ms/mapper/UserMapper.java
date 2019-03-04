package com.geek.ms.mapper;

import java.util.List;

import com.geek.ms.pojo.vo.User;

public interface UserMapper {

	User selectByUsername(String username);
	
	List<User> selectPageAll(int start, int length);
	
	List<User> selectAll();
	
	void addUser(User user);
	
	void setEnable(User user);
}
