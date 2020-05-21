package com.geek.ms.mapper;

import java.util.List;

import com.geek.ms.pojo.vo.UserInfo;
import com.geek.ms.pojo.vo.UserInfoDetail;

public interface UserInfoMapper {

	UserInfo selectAllUserInfo(int id);
	
	List<UserInfoDetail> selectAllUserInfoDetail();
	
	void updateUserInfoByUserId(UserInfo u);
	
	List<UserInfo> selectAllTeacher();
}
