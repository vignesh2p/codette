package com.codette.apps.frontend.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value=Include.NON_EMPTY)
public class Address {

	private String id;
	private String line1;
	private String line2;
	private String country;
	private String state;
	private String city;
	private String type;
	private String zipcode;
	
	private DropDownValue address_state;
	private DropDownValue address_country;
	
	
	public String getLine1() {
		return line1;
	}
	public void setLine1(String line1) {
		this.line1 = line1;
	}
	public String getLine2() {
		return line2;
	}
	public void setLine2(String line2) {
		this.line2 = line2;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the address_state
	 */
	public DropDownValue getAddress_state() {
		return address_state;
	}
	/**
	 * @param address_state the address_state to set
	 */
	public void setAddress_state(DropDownValue address_state) {
		this.address_state = address_state;
	}
	/**
	 * @return the address_country
	 */
	public DropDownValue getAddress_country() {
		return address_country;
	}
	/**
	 * @param address_country the address_country to set
	 */
	public void setAddress_country(DropDownValue address_country) {
		this.address_country = address_country;
	}
	
	
}
