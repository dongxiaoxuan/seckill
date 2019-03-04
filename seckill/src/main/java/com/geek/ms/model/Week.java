package com.geek.ms.model;

import java.io.Serializable;

public class Week implements Serializable{

	private static final long serialVersionUID = 5860455837465011134L;

	private int id;
	private String name;
	public Week() {
		super();
	}
	public Week(int id, String name) {
		super();
		this.id = id;
		this.name = name;
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "Week [id=" + id + ", name=" + name + "]";
	}
	
}
