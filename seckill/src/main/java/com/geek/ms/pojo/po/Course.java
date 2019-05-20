package com.geek.ms.pojo.po;

import java.io.Serializable;

public class Course implements Serializable{

	private static final long serialVersionUID = 5417712725738331446L;

	private int id;
	private String name;
	private int ismust;
	private int yearsId;
	private int professionId;
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
	public int getYearsId() {
		return yearsId;
	}
	public void setYearsId(int yearsId) {
		this.yearsId = yearsId;
	}
	public int getProfessionId() {
		return professionId;
	}
	public void setProfessionId(int professionId) {
		this.professionId = professionId;
	}
	@Override
	public String toString() {
		return "Course [id=" + id + ", name=" + name + ", ismust=" + ismust + ", yearsId=" + yearsId + ", professionId="
				+ professionId + "]";
	}
	
	
}