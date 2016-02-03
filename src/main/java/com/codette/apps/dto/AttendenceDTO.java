package com.codette.apps.dto;

public class AttendenceDTO {
	private Integer studentId;
	private StudentDTO student;
	private Integer isAbsent;
	
	public Integer getStudentId() {
		return studentId;
	}
	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}
	public Integer getIsAbsent() {
		return isAbsent;
	}
	public void setIsAbsent(Integer isAbsent) {
		this.isAbsent = isAbsent;
	}
	public StudentDTO getStudent() {
		return student;
	}
	public void setStudent(StudentDTO student) {
		this.student = student;
	}
	
	
}
