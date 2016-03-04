package com.codette.apps.dto;

public class MarkSheetDTO extends BaseDTO {

	private Integer idExam;
	private ClassesDTO classRoom;
	private SubjectDTO subject;
	

	public Integer getIdExam() {
		return idExam;
	}
	public void setIdExam(Integer idExam) {
		this.idExam = idExam;
	}
	public ClassesDTO getClassRoom() {
		return classRoom;
	}
	public void setClassRoom(ClassesDTO classRoom) {
		this.classRoom = classRoom;
	}
	public SubjectDTO getSubject() {
		return subject;
	}
	public void setSubject(SubjectDTO subject) {
		this.subject = subject;
	}
	
	
	
}
