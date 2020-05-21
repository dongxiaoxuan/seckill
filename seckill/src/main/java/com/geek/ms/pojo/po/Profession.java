package com.geek.ms.pojo.po;

import java.io.Serializable;

public class Profession implements Serializable{

	private static final long serialVersionUID = -5744668545644825101L;
	
	private int id;
	private String name;
	
	public Profession() {
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
		return "Profession [id=" + id + ", name=" + name + "]";
	}

}
