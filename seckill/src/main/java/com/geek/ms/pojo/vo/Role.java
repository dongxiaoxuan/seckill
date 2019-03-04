package com.geek.ms.pojo.vo;

import java.io.Serializable;
import java.util.List;

public class Role implements Serializable{

	private static final long serialVersionUID = 392761602235584188L;
	private int id;
	private String name;
	private String roleName;
	private List<Resources> resources;
	private List<User> users;
	public Role() {
		super();
	}
	public Role(int id, String name, String roleName, List<Resources> resources, List<User> users) {
		super();
		this.id = id;
		this.name = name;
		this.roleName = roleName;
		this.resources = resources;
		this.users = users;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public List<Resources> getResources() {
		return resources;
	}
	public void setResources(List<Resources> resources) {
		this.resources = resources;
	}
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "Role [id=" + id + ", name=" + name + ", roleName=" + roleName + ", resources=" + resources + ", users="
				+ users + "]";
	}
	
}
