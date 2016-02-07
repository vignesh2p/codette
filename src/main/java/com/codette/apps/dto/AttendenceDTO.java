package com.codette.apps.dto;

public class AttendenceDTO extends BaseDTO {
	private StudentDTO student;
	private Integer isAbsent;
	private Integer isEnable;
	

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
	public Integer getIsEnable() {
		return isEnable;
	}
	public void setIsEnable(Integer isEnable) {
		this.isEnable = isEnable;
	}
	
	
}
