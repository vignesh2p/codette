package com.codette.apps.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.codette.apps.dao.ExamMarkDAO;
import com.codette.apps.dto.ExamDTO;
import com.codette.apps.dto.MarkSheetDTO;
import com.codette.apps.dto.ResponseBean;
@Component
public class ExamMarkService {
	
	@Resource
	ExamMarkDAO examMarkDAO;

	public ResponseBean createExam(ExamDTO exam, Integer userId) {
		// TODO Auto-generated method stub
		return examMarkDAO.createExam(exam,userId);
	}

	public List<MarkSheetDTO> getMarkSheet(Integer userId) {
		return examMarkDAO.getMarkSheet(userId);
	}


}
