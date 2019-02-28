package com.geek.ms.mapper;

import java.util.List;

import com.geek.ms.model.UserInfo;
import com.geek.ms.model.UserInfoDetail;

public interface UserInfoMapper {

	UserInfo selectAllUserInfo(int id);
	
	List<UserInfoDetail> selectAllUserInfoDetail();
	
	void updateUserInfoByUserId(UserInfo u);
}
