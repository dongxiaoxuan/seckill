package com.geek.ms.pojo.po;

import java.io.Serializable;

public class Years implements Serializable{

	private static final long serialVersionUID = 6381243704072299620L;

	private int id;
	private String years;
	private int term;
	public Years() {
		super();
	}
	public Years(int id, String years, int term) {
		super();
		this.id = id;
		this.years = years;
		this.term = term;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getYears() {
		return years;
	}
	public void setYears(String years) {
		this.years = years;
	}
	public int getTerm() {
		return term;
	}
	public void setTerm(int term) {
		this.term = term;
	}
	@Override
	public String toString() {
		return "Years [id=" + id + ", years=" + years + ", term=" + term + "]";
	}
	
}
