package com.geek.ms.pojo.vo;

import java.io.Serializable;
import java.util.List;

public class Resources implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1339016388890630574L;
	private int id;
	private String name;
	private String url;
	private int type;
	private int parentId;
	private String parentName;
	private List<Role> roles;
	
	public Resources() {
		super();
	}
	public Resources(int id, String name, String url, int type, int parentId, String parentName, List<Role> roles) {
		super();
		this.id = id;
		this.name = name;
		this.url = url;
		this.type = type;
		this.parentId = parentId;
		this.roles = roles;
		this.parentName = parentName;
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public List<Role> getRoles() {
		return roles;
	}
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	public String getParentName() {
		return parentName;
	}
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	@Override
	public String toString() {
		return "Resources [id=" + id + ", name=" + name + ", url=" + url + ", type=" + type + ", parentId=" + parentId
				+ ", parentName=" + parentName + ", roles=" + roles + "]";
	}
	
}
