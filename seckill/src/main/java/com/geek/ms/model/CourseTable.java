package com.geek.ms.model;

import java.io.Serializable;

public class CourseTable implements Serializable{

	private static final long serialVersionUID = -1971561436442874079L;

	private int id;//ciid
	private int cid;
	private String name;//course name
	private String realName;//teacher name
	private int wid;//week id
	private int fid;//festivals id
	public CourseTable() {
		super();
	}
	public CourseTable(int id, int cid, String name, String realName, int wid, int fid) {
		super();
		this.id = id;
		this.cid = cid;
		this.name = name;
		this.realName = realName;
		this.wid = wid;
		this.fid = fid;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public int getWid() {
		return wid;
	}
	public void setWid(int wid) {
		this.wid = wid;
	}
	public int getFid() {
		return fid;
	}
	public void setFid(int fid) {
		this.fid = fid;
	}
	@Override
	public String toString() {
		return "CourseTable [id=" + id + ", cid=" + cid + ", name=" + name + ", realName=" + realName + ", wid=" + wid
				+ ", fid=" + fid + "]";
	}
	
}
