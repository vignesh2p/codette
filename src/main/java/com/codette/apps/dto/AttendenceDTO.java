package com.codette.apps.dto;

public class AttendenceDTO extends BaseDTO {
	private UserDTO user;
	private Integer isAbsent;
	

	public Integer getIsAbsent() {
		return isAbsent;
	}
	public void setIsAbsent(Integer isAbsent) {
		this.isAbsent = isAbsent;
	}

	public UserDTO getUser() {
		return user;
	}
	public void setUser(UserDTO user) {
		this.user = user;
	}

	
	
}
