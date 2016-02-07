package com.codette.apps.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;

import com.codette.apps.dao.ExamMarkDAO;
import com.codette.apps.dto.ExamDTO;
import com.codette.apps.dto.MarkSheetDTO;
import com.codette.apps.dto.ResponseBean;
import com.codette.apps.dto.StandardDTO;

public class ExamMarkDAOImpl extends NamedParameterJdbcDaoSupport implements ExamMarkDAO{

	@Override
	public ResponseBean createExam(ExamDTO exam, Integer userId) {
		String NEW_EXAM = "INSERT INTO `exam`(`EXAM`, `IS_DELETED`, `CREATED_ON`, `CREATED_BY`) "
				+ "VALUES ( ? ,0,NOW(),?)";
		String SECTIONS ="SELECT ID_SECTION FROM CLASSES WHERE ID_STANDARD = ? AND IS_DELETED = 0 ";
		
		String Marksheet = "INSERT INTO MARK_SHEET "
				+ "(ID_EXAM,ID_STANDARD,ID_SECTION,IS_DELETED,CREATED_ON,CREATED_BY)"
				+ " VALUES (?,?,?,?,NOW(),?)";
		
		try{
		  Integer exam_id = getJdbcTemplate().update(NEW_EXAM, new Object[] {exam.getExam(),userId}, Integer.class);
		  System.out.println("exam id" +exam_id);
		  for(StandardDTO standard : exam.getStandards()){
			  List <Integer> sectionIds = new ArrayList<Integer>();	
			  sectionIds = getJdbcTemplate().query(
					  SECTIONS,new ResultSetExtractor<List<Integer>>(){

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
				  Object[] values = {exam_id,standard.getId(),sectionId,0,userId};
			  getJdbcTemplate().update(Marksheet,values );
			  }
		  }
			
		}catch(Exception e){
			
		}
		return null;
	}

	@Override
	public List<MarkSheetDTO> getMarkSheet(Integer userId) {
		return null;
	}

}
