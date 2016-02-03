package com.codette.apps.frontend.model;

import java.util.List;

public class Roles {
	private String userName;
	private String userRole;
	private RoleMenu roleBasedPermissions;
	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * @return the userRole
	 */
	public String getUserRole() {
		return userRole;
	}
	/**
	 * @param userRole the userRole to set
	 */
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	/**
	 * @return the roleBasedPermissions
	 */
	public RoleMenu getRoleBasedPermissions() {
		return roleBasedPermissions;
	}
	/**
	 * @param roleBasedPermissions the roleBasedPermissions to set
	 */
	public void setRoleBasedPermissions(RoleMenu roleBasedPermissions) {
		this.roleBasedPermissions = roleBasedPermissions;
	}

	
	
}
