package com.geek.ms.pojo.vo;

import java.io.Serializable;

public class ScoreByYears implements Serializable{

	private static final long serialVersionUID = 4735583968508539122L;

	private int yearsId;
	private String name;
	private String score;
	public ScoreByYears() {
		super();
	}
	public ScoreByYears(int yearsId, String name, String score) {
		super();
		this.yearsId = yearsId;
		this.name = name;
		this.score = score;
	}
	public int getYearsId() {
		return yearsId;
	}
	public void setYearsId(int yearsId) {
		this.yearsId = yearsId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	@Override
	public String toString() {
		return "ScoreByYears [yearsId=" + yearsId + ", name=" + name + ", score=" + score + "]";
	}
	
}
