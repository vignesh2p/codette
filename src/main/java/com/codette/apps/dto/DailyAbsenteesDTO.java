package com.codette.apps.dto;

import java.sql.Timestamp;
import java.util.Date;

public class DailyAbsenteesDTO {
  private Integer studentId;
  private StudentDTO student;
  private Date dateOfLeave;
  private Timestamp createdOn;
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
public Timestamp getCreatedOn() {
	return createdOn;
}
public void setCreatedOn(Timestamp createdOn) {
	this.createdOn = createdOn;
}
public StudentDTO getStudent() {
	return student;
}
public void setStudent(StudentDTO student) {
	this.student = student;
}


}
