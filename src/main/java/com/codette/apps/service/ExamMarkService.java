package com.codette.apps.service;

import java.text.ParseException;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.codette.apps.dao.ExamMarkDAO;
import com.codette.apps.dao.impl.StaffDAOImpl;
import com.codette.apps.dto.ExamDTO;
import com.codette.apps.dto.MarkSheetDTO;
import com.codette.apps.dto.ResponseBean;
import com.codette.apps.tbl.Exam;
import com.codette.apps.translator.ExamMarkTranslator;
import com.codette.apps.util.CommonConstants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
@Component
public class ExamMarkService {
	
	final static Logger logger = Logger.getLogger(StaffDAOImpl.class);
	public static final Gson gson = new GsonBuilder().setDateFormat(CommonConstants.ISO_DATE_FORMAT).create();
	
	@Resource
	private ExamMarkDAO examMarkDAO;
	
	@Resource
	private ExamMarkTranslator examMarkTranslator;
	
	@Transactional
	public Object createExam(ExamDTO examDto, Integer orgId, Integer userId, Integer accessId) throws ParseException {
		// TODO Auto-generated method stub
		Exam exam = examMarkTranslator.translateExamdtoToexam(examDto, orgId);
		examMarkTranslator.translateExamdtoToMarksheet(examDto.getStandards(), orgId);
		return examMarkDAO.createExam(examDto,orgId,userId,accessId);
	}

	public Object getMarkSheet(Integer orgId, Integer userId, String role) {
		return examMarkDAO.getMarkSheet(orgId, userId, role);
	}


}
