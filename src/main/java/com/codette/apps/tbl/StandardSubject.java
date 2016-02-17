package com.codette.apps.tbl;

public class StandardSubject extends Base{
   private Integer id;
   private Integer idStandard;
   private Integer idSubject;
   private Integer idYear;
   private Standard standard;
   private Subject subject;
   private Year year;
   
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public Standard getStandard() {
	return standard;
}
public void setStandard(Standard standard) {
	this.standard = standard;
}
public Subject getSubject() {
	return subject;
}
public void setSubject(Subject subject) {
	this.subject = subject;
}
public Year getYear() {
	return year;
}
public void setYear(Year year) {
	this.year = year;
}
public Integer getIdStandard() {
	return idStandard;
}
public void setIdStandard(Integer idStandard) {
	this.idStandard = idStandard;
}
public Integer getIdSubject() {
	return idSubject;
}
public void setIdSubject(Integer idSubject) {
	this.idSubject = idSubject;
}
public Integer getIdYear() {
	return idYear;
}
public void setIdYear(Integer idYear) {
	this.idYear = idYear;
}

   
}
