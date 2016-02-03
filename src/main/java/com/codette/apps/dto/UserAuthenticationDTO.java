package com.codette.apps.dto;

public class UserAuthenticationDTO {
  
	private Integer id;
	private String userName;
	private String userSecret;
	private UserDTO Staff;
	
	public Integer getId() {
		return id;
	}	
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserSecret() {
		return userSecret;
	}
	public void setUserSecret(String userSecret) {
		this.userSecret = userSecret;
	}

	public UserDTO getStaff() {
		return Staff;
	}
	public void setStaff(UserDTO staff) {
		Staff = staff;
	}
	
}
