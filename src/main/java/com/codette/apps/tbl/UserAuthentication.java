package com.codette.apps.tbl;

public class UserAuthentication extends Base{
  
	private Integer id;
	private String userName;
	private String userSecret;
	private User Staff;
	
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

	public User getStaff() {
		return Staff;
	}
	public void setStaff(User staff) {
		Staff = staff;
	}
	
}
