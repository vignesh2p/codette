package com.codette.apps.dto;


public class MarksDTO extends BaseDTO{
   
	 private UserDTO user;
	 private Integer obtainedMark;
	 private Integer maximumMark;
	 
	public UserDTO getUser() {
		return user;
	}
	public void setUser(UserDTO user) {
		this.user = user;
	}
	public Integer getObtainedMark() {
		return obtainedMark;
	}
	public void setObtainedMark(Integer obtainedMark) {
		this.obtainedMark = obtainedMark;
	}
	public Integer getMaximumMark() {
		return maximumMark;
	}
	public void setMaximumMark(Integer maximumMark) {
		this.maximumMark = maximumMark;
	}
	
	 
}
