package com.codette.apps.dto;

import java.util.Date;

public class YearDTO {
   private Integer id;
   private String academicYear;
   private Date startDate;
   private Date endDate;
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public String getAcademicYear() {
	return academicYear;
}
public void setAcademicYear(String academicYear) {
	this.academicYear = academicYear;
}
public Date getStartDate() {
	return startDate;
}
public void setStartDate(Date startDate) {
	this.startDate = startDate;
}
public Date getEndDate() {
	return endDate;
}
public void setEndDate(Date endDate) {
	this.endDate = endDate;
}
   
}
