package com.codette.apps.dao;

import org.springframework.stereotype.Component;

import com.codette.apps.dto.ExamDTO;


@Component
public interface ExamMarkDAO {

	public Object createExam(ExamDTO exam, Integer orgId, Integer accessId, String role)throws Exception;
	
	public Object getMarkSheet(Integer orgId, Integer userId, String role) throws Exception;

	public Object deleteExam(Integer examId, Integer orgId, Integer accessId)throws Exception;

	public Object getExams(Integer orgId, Integer accessId, String role)throws Exception;

}
