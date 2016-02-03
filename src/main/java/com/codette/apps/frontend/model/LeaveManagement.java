package com.codette.apps.frontend.model;

public class LeaveManagement{
	
	private Integer id;
	private String reason;
	private String from;
	private String fromTime;
	private String toTime;
	private String to;
	private String status;
	private Staff staff;
	private boolean approveStatus; 
	
	public boolean isApproveStatus() {
		return approveStatus;
	}
	public void setApproveStatus(boolean approveStatus) {
		this.approveStatus = approveStatus;
	}
	public Staff getStaff() {
		return staff;
	}
	public void setStaff(Staff staff) {
		this.staff = staff;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public String getFromTime() {
		return fromTime;
	}
	public void setFromTime(String fromTime) {
		this.fromTime = fromTime;
	}
	public String getToTime() {
		return toTime;
	}
	public void setToTime(String toTime) {
		this.toTime = toTime;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	

}
