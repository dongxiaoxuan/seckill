package com.geek.ms.model;

import java.io.Serializable;

public class UserInfo implements Serializable{

	private static final long serialVersionUID = 5521586277873199251L;

	private int id;
	private int userId;
	private String number;
	private String realName;
	private String phone;
	private String birthday;
	private String address;
	private String email;
	private String college;
	private String nation;
	private String idNumber;
	private String classes;
	private String subject;
	private String level;
	private String admin1;
	private String admin2;
	public UserInfo() {
		super();
	}
	public UserInfo(int id, int userId, String number, String realName, String phone, String birthday, String address,
			String email, String college, String nation, String idNumber, String classes, String subject, String level,
			String admin1, String admin2) {
		super();
		this.id = id;
		this.userId = userId;
		this.number = number;
		this.realName = realName;
		this.phone = phone;
		this.birthday = birthday;
		this.address = address;
		this.email = email;
		this.college = college;
		this.nation = nation;
		this.idNumber = idNumber;
		this.classes = classes;
		this.subject = subject;
		this.level = level;
		this.admin1 = admin1;
		this.admin2 = admin2;
	}

	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCollege() {
		return college;
	}
	public void setCollege(String college) {
		this.college = college;
	}
	public String getNation() {
		return nation;
	}
	public void setNation(String nation) {
		this.nation = nation;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getIdNumber() {
		return idNumber;
	}
	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}
	public String getClasses() {
		return classes;
	}
	public void setClasses(String classes) {
		this.classes = classes;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getAdmin1() {
		return admin1;
	}
	public void setAdmin1(String admin1) {
		this.admin1 = admin1;
	}
	public String getAdmin2() {
		return admin2;
	}
	public void setAdmin2(String admin2) {
		this.admin2 = admin2;
	}
	@Override
	public String toString() {
		return "UserInfo [id=" + id + ", userId=" + userId + ", number=" + number + ", realName=" + realName
				+ ", phone=" + phone + ", birthday=" + birthday + ", address=" + address + ", email=" + email
				+ ", college=" + college + ", nation=" + nation + ", idNumber=" + idNumber + ", classes=" + classes
				+ ", subject=" + subject + ", level=" + level + ", admin1=" + admin1 + ", admin2=" + admin2 + "]";
	}
	
}
