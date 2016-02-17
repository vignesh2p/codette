package com.codette.apps.tbl;



public class LeaveManagement extends Base {
   private Integer id;
   private User staff;
   private String startTime;
   private String endTime;
   private String reason;
   private Integer isTaken;
   private Status status;
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public User getStaff() {
	return staff;
}
public void setStaff(User staff) {
	this.staff = staff;
}
public String getStartTime() {
	return startTime;
}
public void setStartTime(String startTime) {
	this.startTime = startTime;
}
public String getEndTime() {
	return endTime;
}
public void setEndTime(String endTime) {
	this.endTime = endTime;
}
public String getReason() {
	return reason;
}
public void setReason(String reason) {
	this.reason = reason;
}
public Integer getIsTaken() {
	return isTaken;
}
public void setIsTaken(Integer isTaken) {
	this.isTaken = isTaken;
}
public Status getFormStatus() {
	return status;
}
public void setFormStatus(Status status) {
	this.status = status;
}


}
