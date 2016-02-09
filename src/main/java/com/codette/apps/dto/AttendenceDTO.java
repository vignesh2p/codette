package com.codette.apps.dto;

public class AttendenceDTO extends BaseDTO {
	private UserDTO student;
	private Integer isAbsent;
	private Integer isEnable;
	

	public Integer getIsAbsent() {
		return isAbsent;
	}
	public void setIsAbsent(Integer isAbsent) {
		this.isAbsent = isAbsent;
	}

	public UserDTO getStudent() {
		return student;
	}
	public void setStudent(UserDTO student) {
		this.student = student;
	}
	public Integer getIsEnable() {
		return isEnable;
	}
	public void setIsEnable(Integer isEnable) {
		this.isEnable = isEnable;
	}
	
	
}
