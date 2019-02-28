package com.geek.ms.model;

import java.io.Serializable;

public class CourseModel implements Serializable{

	private static final long serialVersionUID = -4554055647531583120L;

	private Integer id;
	private String name;//课程名
	private Integer userId;
	private String realName;//老师真名
	private String college;//所属学院
	private Integer amount;//课余量
	public CourseModel() {
		super();
	}
	public CourseModel(Integer id, String name, Integer userId, String realName, String college, Integer amount) {
		super();
		this.id = id;
		this.name = name;
		this.userId = userId;
		this.realName = realName;
		this.college = college;
		this.amount = amount;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getCollege() {
		return college;
	}
	public void setCollege(String college) {
		this.college = college;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "CourseModel [id=" + id + ", name=" + name + ", userId=" + userId + ", realName=" + realName
				+ ", college=" + college + ", amount=" + amount + "]";
	}
	
}
