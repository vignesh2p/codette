package com.codette.apps.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.codette.apps.dao.ExamMarkDAO;
import com.codette.apps.dto.ExamDTO;
import com.codette.apps.dto.StandardDTO;
import com.codette.apps.service.CommonService;
import com.codette.apps.service.ExamMarkService;
import com.codette.apps.util.CommonConstants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ExamMarkDAOImpl extends NamedParameterJdbcDaoSupport implements ExamMarkDAO{
	
	final static Logger logger = Logger.getLogger(ExamMarkDAOImpl.class);
	public static final Gson gson = new GsonBuilder().setDateFormat(CommonConstants.ISO_DATE_FORMAT).create();
	@Resource
	private CommonService commonService;

	
	
	String SECTIONS ="SELECT ID_SECTION FROM CLASSES WHERE ID_STANDARD = ? AND IS_DELETED = 0 AND ID_ORGANIZATION = ?";
	
	String Marksheet = "INSERT INTO MARK_SHEET "
			+ "(ID_ORGANIZATION,ID_EXAM,ID_STANDARD,ID_SECTION,IS_DELETED,CREATED_ON,CREATED_BY)"
			+ " VALUES (?,?,?,?,?,NOW(),?)";
	
	@Override
	public Object createExam(ExamDTO exam, Integer orgId,Integer accessId) {
	
		  SqlParameterSource sps = null;
		  KeyHolder keyHolder = new GeneratedKeyHolder();
		  Integer exam_id = null;
	          getNamedParameterJdbcTemplate().update(getCreateNewExam(orgId,exam.getExam(),accessId),
				sps, keyHolder);
		        exam_id = keyHolder.getKey().intValue();
	     
		  System.out.println("exam id" +exam_id);
		  
		  Integer stdId = null;
		  System.out.println(" standard list "+gson.toJson(exam.getStandards()));
		  for(StandardDTO standard : exam.getStandards()){
			            List <Integer> sectionIds = new ArrayList<Integer>();	
			            stdId = commonService.getId(standard.getStandard(), CommonConstants.STANDARD);
			            sectionIds = getJdbcTemplate().query(SECTIONS,
					                         new Object[] {stdId,orgId},                         
					                         new ResultSetExtractor<List<Integer>>(){
													@Override
													public List<Integer> extractData(ResultSet rs) throws SQLException,
															DataAccessException {
														List<Integer> ids = new ArrayList<Integer>(); 
														while(rs.next()){
															Integer id = rs.getInt("ID_SECTION");
															ids.add(id);
														}
														return ids;
													}});
				  
				  for (Integer sectionId : sectionIds){
					  Object[]  values = {orgId,exam_id,stdId,sectionId,0,accessId};
				      getJdbcTemplate().update(Marksheet,values );
				  }
		  }
			
		return "success";
	}

	private String getCreateNewExam(Integer orgId, String exam, Integer accessId) {
		
		String NEW_EXAM;
		return  NEW_EXAM = "INSERT INTO `exam`(`ID_ORGANIZATION`,`EXAM`, `IS_DELETED`, `CREATED_ON`, `CREATED_BY`) "
				+ "VALUES ( "+orgId+",'"+exam+"',0,NOW(),"+accessId+")";
	}

	@Override
	public Object getMarkSheet(Integer orgId, Integer userId,
			String role) {
		String marksheet = "select * from mark_sheet left outer join";
		return null;
	}

}
