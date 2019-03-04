package com.geek.ms.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

public class StudentCourseInfo implements Serializable{

	private static final long serialVersionUID = -6845363569155863927L;

	private int id;
	private int userId;
	private int couresInfoId;
	private Timestamp timestamp;
	private List<UserInfo> userInfo;
	public StudentCourseInfo() {
		super();
	}
	public StudentCourseInfo(int id, int userId, int couresInfoId, Timestamp timestamp, List<UserInfo> userInfo) {
		super();
		this.id = id;
		this.userId = userId;
		this.couresInfoId = couresInfoId;
		this.timestamp = timestamp;
		this.userInfo = userInfo;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getCouresInfoId() {
		return couresInfoId;
	}
	public void setCouresInfoId(int couresInfoId) {
		this.couresInfoId = couresInfoId;
	}
	public Timestamp getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
	public List<UserInfo> getUserInfo() {
		return userInfo;
	}
	public void setUserInfo(List<UserInfo> userInfo) {
		this.userInfo = userInfo;
	}
	@Override
	public String toString() {
		return "StudentCourseInfo [id=" + id + ", userId=" + userId + ", couresInfoId=" + couresInfoId + ", timestamp="
				+ timestamp + ", userInfo=" + userInfo + "]";
	}
	
	
}
