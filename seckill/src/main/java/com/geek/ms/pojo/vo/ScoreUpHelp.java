package com.geek.ms.pojo.vo;

import java.io.Serializable;

public class ScoreUpHelp implements Serializable{

	private static final long serialVersionUID = -1736645726487777517L;

	private Integer classesId;
	private Integer userId;
	private Integer courseId;
	private String startTime;
	private String endTime;
	private Integer courseInfoId;
	public ScoreUpHelp() {
		super();
	}
	public Integer getClassesId() {
		return classesId;
	}
	public void setClassesId(Integer classesId) {
		this.classesId = classesId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getCourseId() {
		return courseId;
	}
	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public Integer getCourseInfoId() {
		return courseInfoId;
	}
	public void setCourseInfoId(Integer courseInfoId) {
		this.courseInfoId = courseInfoId;
	}
	@Override
	public String toString() {
		return "ScoreUpHelp [classesId=" + classesId + ", userId=" + userId + ", courseId=" + courseId + ", startTime="
				+ startTime + ", endTime=" + endTime + ", courseInfoId=" + courseInfoId + "]";
	}
	
}
