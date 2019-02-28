package com.geek.ms.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class StudentCouresInfo implements Serializable{

	private static final long serialVersionUID = -6845363569155863927L;

	private int id;
	private int userId;
	private int couresInfoId;
	private Timestamp timestamp;
	public StudentCouresInfo() {
		super();
	}
	public StudentCouresInfo(int id, int userId, int couresInfoId, Timestamp timestamp) {
		super();
		this.id = id;
		this.userId = userId;
		this.couresInfoId = couresInfoId;
		this.timestamp = timestamp;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getCouresInfoId() {
		return couresInfoId;
	}
	public void setCouresInfoId(int couresInfoId) {
		this.couresInfoId = couresInfoId;
	}
	public Timestamp getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
	@Override
	public String toString() {
		return "StudentCouresInfo [id=" + id + ", userId=" + userId + ", couresInfoId=" + couresInfoId
				+ ", timestamp=" + timestamp + "]";
	}
	
}
