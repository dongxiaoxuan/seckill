package com.geek.ms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.geek.ms.mapper.UserInfoMapper;
import com.geek.ms.pojo.vo.UserInfo;
import com.geek.ms.pojo.vo.UserInfoDetail;
import com.geek.ms.service.UserInfoService;

@Service
public class UserInfoServiceImpl implements UserInfoService{

	@Autowired
	private UserInfoMapper userInfoMapper;
	
	@Override
	public UserInfo selectAllUserInfo(int id) {
		return userInfoMapper.selectAllUserInfo(id);
	}

	@Override
	public List<UserInfoDetail> selectAllUserInfoDetail() {
		return userInfoMapper.selectAllUserInfoDetail();
	}

	@Override
	public void updateUserInfoByUserId(UserInfo u) {
		userInfoMapper.updateUserInfoByUserId(u);
	}

}
