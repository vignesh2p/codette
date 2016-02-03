package com.codette.apps.dto;

public class StaffPhoneNumberDTO {

	 private Integer id;
	 private Integer idStaff;
	 private Long phoneNumber;
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
	public Long getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(Long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public Integer getIsPrimary() {
		return isPrimary;
	}
	public void setIsPrimary(Integer isPrimary) {
		this.isPrimary = isPrimary;
	}
	 
	 
}
