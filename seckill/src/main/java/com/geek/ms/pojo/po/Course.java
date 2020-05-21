package com.geek.ms.pojo.po;

import java.io.Serializable;

import com.geek.ms.pojo.vo.CourseInfo;
import com.geek.ms.pojo.vo.UserInfo;

public class Course implements Serializable{

	private static final long serialVersionUID = 5417712725738331446L;

	private int id;
	private String name;
	private int ismust;
	private Integer yearsId;
	private Integer professionId;
	UserInfo userInfo;
	CourseInfo courseInfo;
	public Course() {
		super();
	}
	public Course(int id, String name, int ismust, int yearsId, int professionId) {
		super();
		this.id = id;
		this.name = name;
		this.ismust = ismust;
		this.yearsId = yearsId;
		this.professionId = professionId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getIsmust() {
		return ismust;
	}
	public void setIsmust(int ismust) {
		this.ismust = ismust;
	}
	public Integer getYearsId() {
		return yearsId;
	}
	public void setYearsId(Integer yearsId) {
		this.yearsId = yearsId;
	}
	public Integer getProfessionId() {
		return professionId;
	}
	public void setProfessionId(Integer professionId) {
		this.professionId = professionId;
	}
	public UserInfo getUserInfo() {
		return userInfo;
	}
	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}
	
	public CourseInfo getCourseInfo() {
		return courseInfo;
	}
	public void setCourseInfo(CourseInfo courseInfo) {
		this.courseInfo = courseInfo;
	}
	@Override
	public String toString() {
		return "Course [id=" + id + ", name=" + name + ", ismust=" + ismust + ", yearsId=" + yearsId + ", professionId="
				+ professionId + ", userInfo=" + userInfo + ", courseInfo=" + courseInfo + "]";
	}
	
}
