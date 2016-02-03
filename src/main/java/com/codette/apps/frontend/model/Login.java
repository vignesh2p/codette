package com.codette.apps.frontend.model;

import java.io.Serializable;

public class Login implements Serializable{

	private static final long serialVersionUID = 6233198351469637219L;
	private String userName;
	private String userSecret;
	
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
}
