package com.geek.ms.pojo.vo;

import java.io.Serializable;

public class CourseModel implements Serializable{

	private static final long serialVersionUID = -4554055647531583120L;

	private Integer id;//ciid
	private String name;//课程名
	private Integer userId;
	private String realName;//老师真名
	private String college;//所属学院
	private Integer studentNumber;//课余量
	private Integer ciid;//sci.ciid
	public CourseModel() {
		super();
	}
	public CourseModel(Integer id, String name, Integer userId, String realName, String college, Integer studentNumber,Integer ciid) {
		super();
		this.id = id;
		this.name = name;
		this.userId = userId;
		this.realName = realName;
		this.college = college;
		this.studentNumber = studentNumber;
		this.ciid = ciid;
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
	public Integer getStudentNumber() {
		return studentNumber;
	}
	public void setStudentNumber(Integer studentNumber) {
		this.studentNumber = studentNumber;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCiid() {
		return ciid;
	}
	public void setCiid(Integer ciid) {
		this.ciid = ciid;
	}
	@Override
	public String toString() {
		return "CourseModel [id=" + id + ", name=" + name + ", userId=" + userId + ", realName=" + realName
				+ ", college=" + college + ", studentNumber=" + studentNumber + ", ciid=" + ciid + "]";
	}
	
}
