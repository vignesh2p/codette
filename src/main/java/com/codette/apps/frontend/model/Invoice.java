package com.codette.apps.frontend.model;

public class Invoice {
	private String primaryApprover;
	private String secondaryApprover;
	/**
	 * @return the primaryApprover
	 */
	public String getPrimaryApprover() {
		return primaryApprover;
	}
	/**
	 * @param primaryApprover the primaryApprover to set
	 */
	public void setPrimaryApprover(String primaryApprover) {
		this.primaryApprover = primaryApprover;
	}
	/**
	 * @return the secondaryApprover
	 */
	public String getSecondaryApprover() {
		return secondaryApprover;
	}
	/**
	 * @param secondaryApprover the secondaryApprover to set
	 */
	public void setSecondaryApprover(String secondaryApprover) {
		this.secondaryApprover = secondaryApprover;
	}

}
