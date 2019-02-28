package com.geek.ms.service;

import java.util.List;

import com.geek.ms.model.UserInfo;
import com.geek.ms.model.UserInfoDetail;

public interface UserInfoService {

	UserInfo selectAllUserInfo(int id);
	
	List<UserInfoDetail> selectAllUserInfoDetail();
	
	void updateUserInfoByUserId(UserInfo u);
}
