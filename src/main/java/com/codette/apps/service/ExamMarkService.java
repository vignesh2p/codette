package com.codette.apps.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.codette.apps.dao.ExamMarkDAO;
import com.codette.apps.dao.impl.StaffDAOImpl;
import com.codette.apps.dto.ExamDTO;
import com.codette.apps.dto.MarkSheetDTO;
import com.codette.apps.dto.ResponseBean;
import com.codette.apps.util.CommonConstants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
@Component
public class ExamMarkService {
	
	final static Logger logger = Logger.getLogger(StaffDAOImpl.class);
	public static final Gson gson = new GsonBuilder().setDateFormat(CommonConstants.ISO_DATE_FORMAT).create();
	
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