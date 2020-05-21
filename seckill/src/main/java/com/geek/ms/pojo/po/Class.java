package com.geek.ms.pojo.po;

import java.io.Serializable;

public class Class implements Serializable{

	private static final long serialVersionUID = -195289457057199494L;
	private int id;
	private String name;
	public Class() {
		super();
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
	@Override
	public String toString() {
		return "Class [id=" + id + ", name=" + name + "]";
	}
	
}
