package com.geek.ms.pojo.vo;

import java.io.Serializable;

public class StudentInfoToTeacher implements Serializable{

	private static final long serialVersionUID = 6323802292088724769L;
	
	private int ciid;
	private int number;
	private String name;
	public StudentInfoToTeacher() {
		super();
	}
	public StudentInfoToTeacher(int ciid, int number, String name) {
		super();
		this.ciid = ciid;
		this.number = number;
		this.name = name;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCiid() {
		return ciid;
	}
	public void setCiid(int ciid) {
		this.ciid = ciid;
	}
	@Override
	public String toString() {
		return "StudentInfoToTeacher [ciid=" + ciid + ", number=" + number + ", name=" + name + "]";
	}
	
}
