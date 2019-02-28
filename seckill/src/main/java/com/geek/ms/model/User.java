package com.geek.ms.model;

import java.io.Serializable;
import java.util.List;

public class User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5559598750891168028L;
	private int id;
	private String username;
	private String password;
	private int enable;
	public List<Role> roles;
	public User() {
		super();
	}
	public User(int id, String username, String password, int enable, List<Role> roles) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.enable = enable;
		this.roles = roles;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getEnable() {
		return enable;
	}
	public void setEnable(int enable) {
		this.enable = enable;
	}
	public List<Role> getRole() {
		return roles;
	}
	public void setRole(List<Role> roles) {
		this.roles = roles;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", enable=" + enable + ", roles="
				+ roles + "]";
	}
	
}
