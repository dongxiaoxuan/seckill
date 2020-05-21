package com.geek.ms.pojo.vo;

import java.io.Serializable;

public class ScoreByTeacher implements Serializable{

	private static final long serialVersionUID = 8420045470765195763L;
	
	private String number;
	private String realName;
	private int score;
	public ScoreByTeacher() {
		super();
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	@Override
	public String toString() {
		return "ScoreByTeacher [number=" + number + ", realName=" + realName + ", score=" + score + "]";
	}

}
