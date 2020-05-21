package com.geek.ms.pojo.vo;

import java.io.Serializable;

public class Product implements Serializable{

	private static final long serialVersionUID = 7255831055030991895L;

	private String realName;
	private String number;
	private String score;
	public Product() {
		super();
	}
	public Product(String realName, String number, String score) {
		super();
		this.realName = realName;
		this.number = number;
		this.score = score;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	@Override
	public String toString() {
		return "Product [realName=" + realName + ", number=" + number + ", score=" + score + "]";
	}
	
	
}
