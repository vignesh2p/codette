package com.codette.apps.frontend.model;

import java.util.List;

public class Role {

	private String name;
	
	private List<String> privileges;

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the privileges
	 */
	public List<String> getPrivileges() {
		return privileges;
	}

	/**
	 * @param privileges the privileges to set
	 */
	public void setPrivileges(List<String> privileges) {
		this.privileges = privileges;
	}
	
	
	
}
