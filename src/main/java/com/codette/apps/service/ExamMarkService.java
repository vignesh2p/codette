package com.codette.apps.service;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.codette.apps.dao.ExamMarkDAO;
import com.codette.apps.dto.ExamDTO;
import com.codette.apps.util.CommonConstants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
@Component
public class ExamMarkService {
	
	final static Logger logger = Logger.getLogger(ExamMarkService.class);
	public static final Gson gson = new GsonBuilder().setDateFormat(CommonConstants.ISO_DATE_FORMAT).create();
	
	@Resource
	private ExamMarkDAO examMarkDAO;
	
	
	@Transactional
	public Object createExam(ExamDTO examDto, Integer orgId, Integer accessId, String role)  {
		// TODO Auto-generated method stub
		//Exam exam = examMarkTranslator.translateExamdtoToexam(examDto, orgId);
		//examMarkTranslator.translateExamdtoToMarksheet(examDto.getStandards(), orgId);
		return examMarkDAO.createExam(examDto,orgId,accessId,role);
	}

	public Object getMarkSheet(Integer orgId, Integer userId, String role) {
		return examMarkDAO.getMarkSheet(orgId, userId, role);
	}

	@Transactional
	public Object deleteExam(Integer examId, Integer orgId,
			Integer accessId) {
		// TODO Auto-generated method stub
		return  examMarkDAO.deleteExam(examId, orgId, accessId);
	}

	public Object getExams(Integer orgId, Integer accessId, String role) {
		// TODO Auto-generated method stub
		return  examMarkDAO.getExams(orgId, accessId, role);
	}


}
