package com.geek.ms.pojo.vo;

import java.io.Serializable;

import com.geek.ms.pojo.po.Course;

public class CourseInfo implements Serializable{

	private static final long serialVersionUID = 4858337369886484440L;
	
	private int id;
	private int couresId;
	private int userId;
	private int amount;
	private Course course;
	private UserInfo userInfo;
	public CourseInfo() {
		super();
	}
	public CourseInfo(int id, int couresId, int userId, int amount) {
		super();
		this.id = id;
		this.couresId = couresId;
		this.userId = userId;
		this.amount = amount;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCouresId() {
		return couresId;
	}
	public void setCouresId(int couresId) {
		this.couresId = couresId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	public UserInfo getUserInfo() {
		return userInfo;
	}
	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}
	@Override
	public String toString() {
		return "CourseInfo [id=" + id + ", couresId=" + couresId + ", userId=" + userId + ", amount=" + amount
				+ ", course=" + course + ", userInfo=" + userInfo + "]";
	}
	
}
