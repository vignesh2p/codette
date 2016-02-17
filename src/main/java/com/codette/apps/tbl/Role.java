package com.codette.apps.tbl;

import java.util.List;


public class Role extends Base{

	private Integer id;
	private String role;
	
	private List<String> privileges;

	/**
	 * @return the privileges
	 */
	
	public List<String> getPrivileges() {
		return privileges;
	}
	
	public Integer getId() { 
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	/**
	 * @param privileges the privileges to set
	 */
	public void setPrivileges(List<String> privileges) {
		this.privileges = privileges;
	}

}