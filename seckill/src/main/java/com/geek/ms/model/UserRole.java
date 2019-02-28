package com.geek.ms.model;

import java.io.Serializable;

public class UserRole implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5325504361470644630L;
	private int id;
	private User user;
	private Role role;
	
	public UserRole() {
		super();
	}
	public UserRole(int id, User user, Role role) {
		super();
		this.id = id;
		this.user = user;
		this.role = role;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	@Override
	public String toString() {
		return "UserRole [id=" + id + ", users=" + user + ", roles=" + role + "]";
	}
	
}
