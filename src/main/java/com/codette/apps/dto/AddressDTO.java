package com.codette.apps.dto;

import java.sql.Timestamp;

public class AddressDTO extends BaseDTO{

	private Integer id;
	private UserDTO user;
	private String address;
	private Integer isPrimary;
	private StudentRelationDTO studentRelation;

	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public UserDTO getUser() {
		return user;
	}
	public void setUser(UserDTO user) {
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
	public StudentRelationDTO getStudentRelation() {
		return studentRelation;
	}
	public void setStudentRelation(StudentRelationDTO studentRelation) {
		this.studentRelation = studentRelation;
	}

	
	
	
}
