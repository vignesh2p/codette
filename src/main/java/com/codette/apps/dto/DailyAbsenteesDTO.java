package com.codette.apps.dto;

import java.sql.Timestamp;
import java.util.Date;

public class DailyAbsenteesDTO extends BaseDTO{
  private Integer studentId;
  private UserDTO user;
  private Date dateOfLeave;
 
public Integer getStudentId() {
	return studentId;
}
public void setStudentId(Integer studentId) {
	this.studentId = studentId;
}
public Date getDateOfLeave() {
	return dateOfLeave;
}
public void setDateOfLeave(Date dateOfLeave) {
	this.dateOfLeave = dateOfLeave;
}
public UserDTO getUser() {
	return user;
}
public void setUser(UserDTO user) {
	this.user = user;
}



}
