package com.geek.ms.pojo.vo;

import java.io.Serializable;

public class StudentInfoToTeacher implements Serializable{

	private static final long serialVersionUID = 6323802292088724769L;
	
	private int id;
	private int cid;
	private int ciid;
	private int number;
	private String name;
	public StudentInfoToTeacher() {
		super();
	}
	public StudentInfoToTeacher(int id, int cid, int ciid, int number, String name) {
		super();
		this.id = id;
		this.cid = cid;
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
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	@Override
	public String toString() {
		return "StudentInfoToTeacher [id=" + id + ", cid=" + cid + ", ciid=" + ciid + ", number=" + number + ", name="
				+ name + "]";
	}
	
}
