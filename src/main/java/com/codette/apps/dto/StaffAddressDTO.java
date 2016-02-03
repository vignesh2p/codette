package com.codette.apps.dto;

public class StaffAddressDTO {
 
	 private Integer id;
	 private Integer idStaff;
	 private String address;
	 private Integer isPrimary;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getIdStaff() {
		return idStaff;
	}
	public void setIdStaff(Integer idStaff) {
		this.idStaff = idStaff;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Integer getIsPrimary() {
		return isPrimary;
	}
	public void setIsPrimary(Integer isPrimary) {
		this.isPrimary = isPrimary;
	}
	 
}
