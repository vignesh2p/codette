package com.codette.apps.dto;

public class UserAuthenticationDTO extends BaseDTO{
  
	private String userName;
	private String userSecret;
	private UserDTO user;
	

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
	public UserDTO getUser() {
		return user;
	}
	public void setUser(UserDTO user) {
		this.user = user;
	}


	
}
