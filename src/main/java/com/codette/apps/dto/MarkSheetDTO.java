package com.codette.apps.dto;

import java.util.List;

public class MarkSheetDTO extends BaseDTO {

    private ExamDTO exam;
    private ClassesDTO classRoom;
	private SubjectDTO subject;
	private List<MarksDTO> marks;

	
	
	
	public ExamDTO getExam() {
		return exam;
	}
	public void setExam(ExamDTO exam) {
		this.exam = exam;
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
	public List<MarksDTO> getMarks() {
		return marks;
	}
	public void setMarks(List<MarksDTO> marks) {
		this.marks = marks;
	}
	
	
	
}
