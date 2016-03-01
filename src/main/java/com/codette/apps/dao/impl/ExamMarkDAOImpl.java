package com.codette.apps.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.codette.apps.dao.ExamMarkDAO;
import com.codette.apps.dto.ClassesDTO;
import com.codette.apps.dto.ExamDTO;
import com.codette.apps.dto.StandardDTO;
import com.codette.apps.service.CommonService;
import com.codette.apps.util.CommonConstants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ExamMarkDAOImpl extends NamedParameterJdbcDaoSupport implements ExamMarkDAO{
	
	final static Logger logger = Logger.getLogger(ExamMarkDAOImpl.class);
	public static final Gson gson = new GsonBuilder().setDateFormat(CommonConstants.ISO_DATE_FORMAT).create();
	@Resource
	private CommonService commonService;
	
	String Marksheet = "INSERT INTO MARK_SHEET "
			+ "(ID_ORGANIZATION,ID_EXAM,ID_CLASS,ID_SUBJECT,IS_DELETED,CREATED_ON,CREATED_BY)"
			+ " VALUES (?,?,?,?,?,NOW(),?)";
	
	@Override
	public Object createExam(ExamDTO exam, Integer orgId,Integer accessId,String role) {
	
		  SqlParameterSource sps = null;
		  KeyHolder keyHolder = new GeneratedKeyHolder();
		  Integer exam_id = null;
	          getNamedParameterJdbcTemplate().update(getCreateNewExam(orgId,exam.getExam(),accessId),
				sps, keyHolder);
		        exam_id = keyHolder.getKey().intValue();
	             List<Integer> subjectIds = new ArrayList<Integer>();
		      
		  for(ClassesDTO classRoom : exam.getClassRooms()){
				  subjectIds = getJdbcTemplate().queryForList(getSubjectsForClass(classRoom.getId(),role,accessId),Integer.class);
			  for(Integer subjectId : subjectIds){
			   	  Object[]  values = {orgId,exam_id,classRoom.getId(),subjectId,0,accessId};
				      getJdbcTemplate().update(Marksheet,values );
			  }
		  }
			
		return "success";
	}
	
	@Override
	public Object getMarkSheet(Integer orgId, Integer userId,
			String role) {
		String marksheet = " select * from mark_sheet ms"
				+ " left outer join mark m on ms. ";
		return null;
	}


	

	@Override
	public Object deleteExam(Integer examId, Integer orgId, Integer accessId) {
		String DELETE_EXAM = "UPDATE `exam` SET `IS_DELETED`= ? ,`UPDATED_ON`= NOW(),`UPDATED_BY`= ? WHERE `ID` = ? AND `ID_ORGANIZATION`= ? ";
		String DELETE_MARKSHEET = " UPDATE `mark_sheet` SET `IS_DELETED` = ? ,`UPDATED_ON`=NOW(),`UPDATED_BY`=? WHERE `ID_EXAM`= ? AND `ID_ORGANIZATION`= ? ";
		Object[] inputs = new Object[]{0,accessId,examId,orgId};
	    getJdbcTemplate().update(DELETE_EXAM,inputs);
	    getJdbcTemplate().update(DELETE_MARKSHEET,inputs);
		return null;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	private String getSubjectsForClass(Integer classId,String role, Integer accessId) {
      String query = "SELECT  `ID_SUBJECT`  FROM `staff_class` WHERE `ID_CLASS` = "+classId+" AND `IS_DELETED` = 0 ";
        if(role.equalsIgnoreCase(CommonConstants.ROLE_T_STAFF)){
        	query = query + " AND `ID_USER` = "+accessId;
        }
		return null;
	}

	private String getCreateNewExam(Integer orgId, String exam, Integer accessId) {
		
		String NEW_EXAM;
		return  NEW_EXAM = "INSERT INTO `exam`(`ID_ORGANIZATION`,`EXAM`, `IS_DELETED`, `CREATED_ON`, `CREATED_BY`) "
				+ "VALUES ( "+orgId+",'"+exam+"',0,NOW(),"+accessId+")";
	}

	
}
