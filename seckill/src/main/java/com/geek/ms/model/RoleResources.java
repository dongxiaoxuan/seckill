package com.geek.ms.model;

import java.io.Serializable;

public class RoleResources implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7802001399587985746L;
	private int id;
	private Role role;
	private Resources resources;
	public RoleResources() {
		super();
	}
	public RoleResources(int id, Role role, Resources resources) {
		super();
		this.id = id;
		this.role = role;
		this.resources = resources;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public Resources getResources() {
		return resources;
	}
	public void setResources(Resources resources) {
		this.resources = resources;
	}
	@Override
	public String toString() {
		return "RoleResources [id=" + id + ", role=" + role + ", resources=" + resources + "]";
	}
}
