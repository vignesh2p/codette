package com.codette.apps.dto;

public class StandardSubjectDTO extends BaseDTO{
   private Integer id;
   private Integer idStandard;
   private Integer idSubject;
   private Integer idYear;
   private StandardDTO standard;
   private SubjectDTO subject;
   private YearDTO year;
   
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public StandardDTO getStandard() {
	return standard;
}
public void setStandard(StandardDTO standard) {
	this.standard = standard;
}
public SubjectDTO getSubject() {
	return subject;
}
public void setSubject(SubjectDTO subject) {
	this.subject = subject;
}
public YearDTO getYear() {
	return year;
}
public void setYear(YearDTO year) {
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
