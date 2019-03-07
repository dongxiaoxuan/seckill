package com.geek.ms.pojo.vo;

import java.io.Serializable;

public class CourseClassStudent implements Serializable{

	private static final long serialVersionUID = 84065064396622110L;

	private int classesId;
	private int userId;
	private String number;
	private String realName;
	private String collegeName;
	public CourseClassStudent() {
		super();
	}
	public CourseClassStudent(int classesId, int userId, String number, String realName, String collegeName) {
		super();
		this.classesId = classesId;
		this.userId = userId;
		this.number = number;
		this.realName = realName;
		this.collegeName = collegeName;
	}
	public int getClassesId() {
		return classesId;
	}
	public void setClassesId(int classesId) {
		this.classesId = classesId;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getCollegeName() {
		return collegeName;
	}
	public void setCollegeName(String collegeName) {
		this.collegeName = collegeName;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	@Override
	public String toString() {
		return "CourseClassStudent [classesId=" + classesId + ", userId=" + userId + ", number=" + number
				+ ", realName=" + realName + ", collegeName=" + collegeName + "]";
	}
}
