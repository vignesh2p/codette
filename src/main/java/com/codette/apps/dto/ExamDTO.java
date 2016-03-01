package com.codette.apps.dto;

import java.util.List;


public class ExamDTO extends BaseDTO{
	private String exam;
	private List<ClassesDTO> classRooms;
	public String getExam() {
		return exam;
	}
	public void setExam(String exam) {
		this.exam = exam;
	}
	public List<ClassesDTO> getClassRooms() {
		return classRooms;
	}
	public void setClassRooms(List<ClassesDTO> classRooms) {
		this.classRooms = classRooms;
	}
	

	

}
