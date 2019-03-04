package com.geek.ms.pojo.vo;

import java.io.Serializable;

public class Festivals implements Serializable{

	private static final long serialVersionUID = 4489500223351968143L;

	private int id;
	private String name;
	private int mark;
	public Festivals() {
		super();
	}
	public Festivals(int id, String name, int mark) {
		super();
		this.id = id;
		this.name = name;
		this.mark = mark;
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
	public int getMark() {
		return mark;
	}
	public void setMark(int mark) {
		this.mark = mark;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "Festivals [id=" + id + ", name=" + name + ", mark=" + mark + "]";
	}
	
}
