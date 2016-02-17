package com.codette.apps.tbl;

import java.sql.Timestamp;

public class Address extends Base{

	private Integer id;
	private User user;
	private String address;
	private Integer isPrimary;
	private StudentRelation studentRelation;

	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Integer getIsPrimary() {
		return isPrimary;
	}
	public void setIsPrimary(Integer isPrimary) {
		this.isPrimary = isPrimary;
	}
	public StudentRelation getStudentRelation() {
		return studentRelation;
	}
	public void setStudentRelation(StudentRelation studentRelation) {
		this.studentRelation = studentRelation;
	}

	
	
	
}
