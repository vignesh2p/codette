package com.codette.apps.dao;

import org.springframework.stereotype.Component;

import com.codette.apps.dto.ExamDTO;


@Component
public interface ExamMarkDAO {

	public Object createExam(ExamDTO exam, Integer orgId, Integer accessId, String role);
	
	public Object getMarkSheet(Integer orgId, Integer userId, String role);

	public Object deleteExam(Integer examId, Integer orgId, Integer accessId);

}
