package com.codette.apps.dto;



public class LeaveManagementDTO extends BaseDTO {
   private UserDTO staff;
   private String startTime;
   private String endTime;
   private String reason;
   private Integer isTaken;
   private StatusDTO status;

   
public UserDTO getStaff() {
	return staff;
}
public void setStaff(UserDTO staff) {
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
public StatusDTO getStatus() {
	return status;
}
public void setStatus(StatusDTO status) {
	this.status = status;
}


}
