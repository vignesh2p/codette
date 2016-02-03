package com.codette.apps.dto;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * User : Rubini
 * Date : 30/04/2015
 * Description: RoleService is used to Manage the Role Details
 */
@JsonInclude(value=Include.NON_EMPTY)
public class RoleDTO extends BaseDTO{

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