package com.codette.apps.dto;

public class PhoneNumberDTO extends BaseDTO{
  private UserDTO user;
  private String phoneNumber;
  private Integer isPrimary;
  private StudentRelationDTO studentRelation;
  

  
public UserDTO getUser() {
	return user;
}
public void setUser(UserDTO user) {
	this.user = user;
}
public Integer getIsPrimary() {
	return isPrimary;
}
public void setIsPrimary(Integer isPrimary) {
	this.isPrimary = isPrimary;
}

public StudentRelationDTO getStudentRelation() {
	return studentRelation;
}
public void setStudentRelation(StudentRelationDTO studentRelation) {
	this.studentRelation = studentRelation;
}
public String getPhoneNumber() {
	return phoneNumber;
}
public void setPhoneNumber(String phoneNumber) {
	this.phoneNumber = phoneNumber;
}


  
}
