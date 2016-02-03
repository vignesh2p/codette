package com.codette.apps.frontend.model;

import java.util.List;

public class BusinessProfile {
	private String name;
	private String classification;
	private String date;
	private String license;
	private String insurance;
	private List<Address> address;
	private String officeSpace;
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
	 * @return the classification
	 */
	public String getClassification() {
		return classification;
	}
	/**
	 * @param classification the classification to set
	 */
	public void setClassification(String classification) {
		this.classification = classification;
	}
	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}
	/**
	 * @param date the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}
	/**
	 * @return the license
	 */
	public String getLicense() {
		return license;
	}
	/**
	 * @param license the license to set
	 */
	public void setLicense(String license) {
		this.license = license;
	}
	/**
	 * @return the insurance
	 */
	public String getInsurance() {
		return insurance;
	}
	/**
	 * @param insurance the insurance to set
	 */
	public void setInsurance(String insurance) {
		this.insurance = insurance;
	}
	/**
	 * @return the address
	 */
	public List<Address> getAddress() {
		return address;
	}
	/**
	 * @param address the address to set
	 */
	public void setAddress(List<Address> address) {
		this.address = address;
	}
	/**
	 * @return the officeSpace
	 */
	public String getOfficeSpace() {
		return officeSpace;
	}
	/**
	 * @param officeSpace the officeSpace to set
	 */
	public void setOfficeSpace(String officeSpace) {
		this.officeSpace = officeSpace;
	}
	
	
}
