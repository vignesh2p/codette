package com.codette.apps.tbl;

import java.sql.Timestamp;
import java.util.Date;

public class DailyAbsentees extends Base{
  private Integer studentId;
  private User user;
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
public User getUser() {
	return user;
}
public void setUser(User user) {
	this.user = user;
}



}
