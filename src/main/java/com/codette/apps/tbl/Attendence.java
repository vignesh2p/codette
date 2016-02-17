package com.codette.apps.tbl;

public class Attendence extends Base {
	private User student;
	private Integer isAbsent;
	private Integer isEnable;
	

	public Integer getIsAbsent() {
		return isAbsent;
	}
	public void setIsAbsent(Integer isAbsent) {
		this.isAbsent = isAbsent;
	}

	public User getStudent() {
		return student;
	}
	public void setStudent(User student) {
		this.student = student;
	}
	public Integer getIsEnable() {
		return isEnable;
	}
	public void setIsEnable(Integer isEnable) {
		this.isEnable = isEnable;
	}
	
	
}
