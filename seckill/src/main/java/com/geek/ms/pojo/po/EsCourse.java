package com.geek.ms.pojo.po;

import java.io.Serializable;

public class EsCourse implements Serializable{

	private static final long serialVersionUID = 8437294091684529347L;
	private String id;
	private String ismust;
	private String name;
	private String professionId;
	private String yearsId;
	public EsCourse() {
		super();
	}
	public EsCourse(String id, String ismust, String name, String professionId, String yearsId) {
		super();
		this.id = id;
		this.ismust = ismust;
		this.name = name;
		this.professionId = professionId;
		this.yearsId = yearsId;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getIsmust() {
		return ismust;
	}
	public void setIsmust(String ismust) {
		this.ismust = ismust;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getProfessionId() {
		return professionId;
	}
	public void setProfessionId(String professionId) {
		this.professionId = professionId;
	}
	public String getYearsId() {
		return yearsId;
	}
	public void setYearsId(String yearsId) {
		this.yearsId = yearsId;
	}
	@Override
	public String toString() {
		return "EsCourse [id=" + id + ", ismust=" + ismust + ", name=" + name + ", professionId=" + professionId
				+ ", yearsId=" + yearsId + "]";
	}
	
}
