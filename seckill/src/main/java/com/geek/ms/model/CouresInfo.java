package com.geek.ms.model;

import java.io.Serializable;

public class CouresInfo implements Serializable{

	private static final long serialVersionUID = 4858337369886484440L;
	
	private int id;
	private int couresId;
	private int userId;
	private int amount;
	public CouresInfo() {
		super();
	}
	public CouresInfo(int id, int couresId, int userId, int amount) {
		super();
		this.id = id;
		this.couresId = couresId;
		this.userId = userId;
		this.amount = amount;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCouresId() {
		return couresId;
	}
	public void setCouresId(int couresId) {
		this.couresId = couresId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	@Override
	public String toString() {
		return "CouresInfo [id=" + id + ", couresId=" + couresId + ", userId=" + userId + ", amount=" + amount
				+ "]";
	}
	
}
