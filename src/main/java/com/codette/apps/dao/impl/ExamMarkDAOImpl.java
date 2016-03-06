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
import com.codette.apps.dto.ResponseBean;
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
	
	
	String Mark = "INSERT INTO `marks`( `ID_ORGANIZATION`, `ID_MARKSHEET`, `ID_USER`,`IS_DELETED`, `CREATED_ON`, `CREATED_BY`)"
			+ " VALUES (?,?,?,?,NOW(),?)";
	
	@Override
	public Object createExam(ExamDTO exam, Integer orgId,Integer accessId,String role) throws Exception{
	
		  ResponseBean responseBean = new ResponseBean();
		  SqlParameterSource sps = null;
		  KeyHolder keyHolder = new GeneratedKeyHolder();
		  Integer exam_id = null;
		  Integer markSheet_id = null;
	          getNamedParameterJdbcTemplate().update(getCreateNewExam(orgId,exam.getExam(),accessId),
				sps, keyHolder);
		        exam_id = keyHolder.getKey().intValue();
	             List<Integer> subjectIds = new ArrayList<Integer>();
	             List<Integer> studentIds = new ArrayList<Integer>();
		      
		  for(ClassesDTO classRoom : exam.getClassRooms()){
			  
			  subjectIds = getJdbcTemplate().queryForList(getSubjectsForClass(classRoom.getId(),role,accessId),Integer.class);
				  
			  for(Integer subjectId : subjectIds){
				  
			   	  Object[]  values = {orgId,exam_id,classRoom.getId(),subjectId,0,accessId};
				   	keyHolder = new GeneratedKeyHolder();
				   	getNamedParameterJdbcTemplate().update(getMarkSheet(values),sps,keyHolder );
				   	markSheet_id = keyHolder.getKey().intValue(); 
			   	    studentIds =  getJdbcTemplate().queryForList(getStudentsOfClass(classRoom.getId()),Integer.class);
			   	    
				   	for(Integer idUser : studentIds){
				   		
				   	    Object[]  input = {orgId,markSheet_id,idUser,0,accessId};
				   		getJdbcTemplate().update(Mark, input);
				   		
				   	}
			  }
		  }
			
		return responseBean;
	}
	
	
	@Override
	public Object getExams(Integer orgId, Integer accessId, String role) throws Exception{
		
		Object object =  null;
		//object = getJdbcTemplate().query(getExamList(role),examMarkExtractor.);
		return null;
	}


	private String getExamList(String role) throws Exception{
		String query = "SELECT * FROM exam WHERE IS_DELETED = 0 ";
				if(role.equalsIgnoreCase(CommonConstants.ROLE_T_STAFF)){
				query += " AND ID IN (?)";
				}
		return query;
	}


	@Override
	public Object getMarkSheet(Integer orgId, Integer userId,
			String role) throws Exception {
		String marksheet = " select * from mark_sheet ms"
				+ " left outer join mark m on ms. ";
		return null;
	}


	

	@Override
	public Object deleteExam(Integer examId, Integer orgId, Integer accessId)throws Exception {
		ResponseBean responseBean = new ResponseBean();
		String DELETE_EXAM = "UPDATE `exam` SET `IS_DELETED`= ? ,`UPDATED_ON`= NOW(),`UPDATED_BY`= ? WHERE `ID` = ? AND `ID_ORGANIZATION`= ? ";
		String DELETE_MARKSHEET = " UPDATE `mark_sheet` SET `IS_DELETED` = ? ,`UPDATED_ON`=NOW(),`UPDATED_BY`=? WHERE `ID_EXAM`= ? AND `ID_ORGANIZATION`= ? ";
		Object[] inputs = new Object[]{0,accessId,examId,orgId};
	    getJdbcTemplate().update(DELETE_EXAM,inputs);
	    getJdbcTemplate().update(DELETE_MARKSHEET,inputs);
		
		return responseBean;
	}

	
	


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	private String getSubjectsForClass(Integer classId,String role, Integer accessId)throws Exception {
      String query = "SELECT  `ID_SUBJECT`  FROM `staff_class` WHERE `ID_CLASS` = "+classId+" AND `IS_DELETED` = 0 ";
        if(role.equalsIgnoreCase(CommonConstants.ROLE_T_STAFF)){
        	query = query + " AND `ID_USER` = "+accessId;
        }
		return query;
	}

	private String getCreateNewExam(Integer orgId, String exam, Integer accessId)throws Exception {
		
		return  "INSERT INTO `exam`(`ID_ORGANIZATION`,`EXAM`, `IS_DELETED`, `CREATED_ON`, `CREATED_BY`) "
				+ "VALUES ( "+orgId+",'"+exam+"',0,NOW(),"+accessId+")";
	}

	private String getStudentsOfClass(Integer id) throws Exception{
        return "SELECT `ID` FROM `user` WHERE `ID_CLASS` = "+id;		
	}
	
	private String getMarkSheet(Object[] values) throws Exception {
		// TODO Auto-generated method stub
		return "INSERT INTO `mark_sheet`(  `ID_ORGANIZATION`, `ID_EXAM`, `ID_CLASS`, `ID_SUBJECT`, `IS_DELETED`, `CREATED_ON`, `CREATED_BY`)"
				+ " VALUES ("+values[0]+","+values[1]+","+values[2]+","+values[3]+","+values[4]+",NOW(),"+values[5]+")";
		
	}
	
	
}
