package com.geek.ms.model;

import java.io.Serializable;

public class SeckillStat implements Serializable{

	private static final long serialVersionUID = -5864133557333232847L;

	private int ciid;
	private boolean haveCurrentUserId;
	public SeckillStat() {
		super();
	}
	public SeckillStat(int ciid, boolean haveCurrentUserId) {
		super();
		this.ciid = ciid;
		this.haveCurrentUserId = haveCurrentUserId;
	}
	public int getCiid() {
		return ciid;
	}
	public void setCiid(int ciid) {
		this.ciid = ciid;
	}
	public boolean isHaveCurrentUserId() {
		return haveCurrentUserId;
	}
	public void setHaveCurrentUserId(boolean haveCurrentUserId) {
		this.haveCurrentUserId = haveCurrentUserId;
	}
	@Override
	public String toString() {
		return "SeckillStat [ciid=" + ciid + ", haveCurrentUserId=" + haveCurrentUserId + "]";
	}
	
}
