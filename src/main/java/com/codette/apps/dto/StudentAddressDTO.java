package com.codette.apps.dto;

import java.sql.Timestamp;

public class StudentAddressDTO {

	private Integer id;
	private StudentDTO student;
	private String address;
	private Integer isPrimary;
	private StudentRelationDTO studentRelation;
	private Integer isDeleted;
	private Timestamp createdOn;
	private Integer createdBy;
	private Timestamp updatedOn;
	private Integer updatedBy;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public StudentDTO getStudent() {
		return student;
	}
	public void setStudent(StudentDTO student) {
		this.student = student;
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
	public Integer getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
	}
	public Timestamp getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Timestamp createdOn) {
		this.createdOn = createdOn;
	}
	public Integer getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}
	public Timestamp getUpdatedOn() {
		return updatedOn;
	}
	public void setUpdatedOn(Timestamp updatedOn) {
		this.updatedOn = updatedOn;
	}
	public Integer getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}
	
	
	
}
