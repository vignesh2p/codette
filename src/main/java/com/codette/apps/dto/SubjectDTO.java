package com.codette.apps.dto;

public class SubjectDTO extends BaseDTO{
   private String subject;
   private String subjectCode;
   

public String getSubjectCode() {
	return subjectCode;
}
public void setSubjectCode(String subjectCode) {
	this.subjectCode = subjectCode;
}
public String getSubject() {
	return subject;
}
public void setSubject(String subject) {
	this.subject = subject;
}

   
}
