package com.geek.ms.pojo.vo;

import java.io.Serializable;

public class UserInfoDetail implements Serializable{

	private static final long serialVersionUID = 1761758918356233448L;

	private int id;
	private String colName;
	private String chineseCol;
	private int roleId;
	private int enable;
	public UserInfoDetail() {
		super();
	}
	public UserInfoDetail(int id, String colName, String chineseCol, int roleId, int enable) {
		super();
		this.id = id;
		this.colName = colName;
		this.chineseCol = chineseCol;
		this.roleId = roleId;
		this.enable = enable;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getColName() {
		return colName;
	}
	public void setColName(String colName) {
		this.colName = colName;
	}
	public String getChineseCol() {
		return chineseCol;
	}
	public void setChineseCol(String chineseCol) {
		this.chineseCol = chineseCol;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public int getEnable() {
		return enable;
	}
	public void setEnable(int enable) {
		this.enable = enable;
	}
	@Override
	public String toString() {
		return "UserInfoDetail [id=" + id + ", colName=" + colName + ", chineseCol=" + chineseCol + ", roleId=" + roleId
				+ ", enable=" + enable + "]";
	}
	
}
