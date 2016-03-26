package com.codette.apps.frontend.model;

public class User extends Person{
	private String personId;
	private String sessionToken;
	private String userName;
	private String userRole;
	private String imageUrl;
	private DropDownValue standard;
	private DropDownValue section;
	private Class clas;
	
	
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getUserRole() {
		return userRole;
	}
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPersonId() {
		return personId;
	}
	public void setPersonId(String personId) {
		this.personId = personId;
	}
	public String getSessionToken() {
		return sessionToken;
	}
	public void setSessionToken(String sessionToken) {
		this.sessionToken = sessionToken;
	}
	public DropDownValue getStandard() {
		return standard;
	}
	public void setStandard(DropDownValue standard) {
		this.standard = standard;
	}
	public DropDownValue getSection() {
		return section;
	}
	public void setSection(DropDownValue section) {
		this.section = section;
	}
	public Class getClas() {
		return clas;
	}
	public void setClas(Class clas) {
		this.clas = clas;
	}
	
	
	
}
