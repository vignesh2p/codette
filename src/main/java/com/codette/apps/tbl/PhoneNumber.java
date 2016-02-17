package com.codette.apps.tbl;

public class PhoneNumber {
  private Integer id;
  private Integer idStudent;
  private String phoneNumber;
  private Integer isPrimary;
  private Integer idStudentRelation;
  private StudentRelation studentRelation;
  
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public Integer getIdStudent() {
	return idStudent;
}
public void setIdStudent(Integer idStudent) {
	this.idStudent = idStudent;
}

public Integer getIsPrimary() {
	return isPrimary;
}
public void setIsPrimary(Integer isPrimary) {
	this.isPrimary = isPrimary;
}
public Integer getIdStudentRelation() {
	return idStudentRelation;
}
public void setIdStudentRelation(Integer idStudentRelation) {
	this.idStudentRelation = idStudentRelation;
}
public StudentRelation getStudentRelation() {
	return studentRelation;
}
public void setStudentRelation(StudentRelation studentRelation) {
	this.studentRelation = studentRelation;
}
public String getPhoneNumber() {
	return phoneNumber;
}
public void setPhoneNumber(String phoneNumber) {
	this.phoneNumber = phoneNumber;
}


  
}
