package com.codette.apps.dto;

import java.util.List;


public class ExamDTO extends BaseDTO{
	private Integer id;
	private String exam;
	private List<StandardDTO> standards;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
		
	}
	public String getExam() {
		return exam;
	}
	public void setExam(String exam) {
		this.exam = exam;
	}
	public List<StandardDTO> getStandards() {
		return standards;
	}
	public void setStandards(List<StandardDTO> standards) {
		this.standards = standards;
	}
	

}
