package com.codette.apps.dto;

public class StudentPhoneNumberList {

	private Integer id;
	private StudentDTO student;
	private StudentPhoneNumberDTO phoneNumber;
	private StudentRelationDTO studentRelation;
	private Integer isPrimary;
	
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
	public StudentPhoneNumberDTO getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(StudentPhoneNumberDTO phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public StudentRelationDTO getStudentRelation() {
		return studentRelation;
	}
	public void setStudentRelation(StudentRelationDTO studentRelation) {
		this.studentRelation = studentRelation;
	}
	public Integer getIsPrimary() {
		return isPrimary;
	}
	public void setIsPrimary(Integer isPrimary) {
		this.isPrimary = isPrimary;
	}
	
}
