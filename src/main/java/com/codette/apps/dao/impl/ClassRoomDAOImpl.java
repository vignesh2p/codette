package com.codette.apps.dao.impl;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.jdbc.support.KeyHolder;

import com.codette.apps.ResultSetExtractor.ClassRoomExtractor;
import com.codette.apps.dao.ClassRoomDAO;
import com.codette.apps.dto.ClassesDTO;
import com.codette.apps.dto.ResponseBean;
import com.codette.apps.dto.StaffClassDTO;
import com.codette.apps.util.CommonConstants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public class ClassRoomDAOImpl extends NamedParameterJdbcDaoSupport implements ClassRoomDAO{
	
	@Resource
	private ClassRoomExtractor classRoomExtractor;
	
	final static Logger logger = Logger.getLogger(ClassRoomDAOImpl.class);
	public static final Gson gson = new GsonBuilder().setDateFormat(CommonConstants.ISO_DATE_FORMAT).create();


	
	
	
	@Override
	public Object getClassList(Integer orgId, Integer userId,
			String role) {
		System.out.println();
		List<StaffClassDTO> classList = new ArrayList<StaffClassDTO>();
		String CLASS_LIST = "SELECT * FROM `staff_class` A "
				+ "LEFT OUTER JOIN SUBJECT SUB ON A.ID_SUBJECT = SUB.ID AND SUB.ID_ORGANIZATION = "+orgId
				+ "LEFT OUTER JOIN STANDARD STD ON A.ID_STANDARD = STD.ID AND STD.ID_ORGANIZATION = "+orgId
				+ "LEFT OUTER JOIN SECTION SEC ON A.ID_SECTION = SEC.ID AND SEC.ID_ORGANIZATION = "+orgId
				+ "LEFT OUTER JOIN YEAR Y ON A.ID_YEAR = Y.ID AND Y.ID_ORGANIZATION = "+orgId	
				+ "WHERE A.ID_USER = "+userId +" A.IS_DELETED = 0 AND STD.IS_DELETED = 0 AND SEC.IS_DELETED = 0 " 
						+ " AND A.ID_ORGANIZATION = "+orgId+ "ORDER BY STD.ID , SEC.ID";	
         if(userId != null && role != null){
        	 return classList = getJdbcTemplate().query(CLASS_LIST,classRoomExtractor.getHandlingClassesList());
	         }
         else{
	         return	 getAllClassList(orgId);
	         }
	}

	
	
	
	
	
	
	private Object getAllClassList(Integer orgId) {

		String LIST_ALL_CLASS=" SELECT * FROM `classes` A "
				+ "LEFT OUTER JOIN SECTION SEC ON A.ID_SECTION = SEC.ID AND ID_ORGANIZATION = "+orgId
				+ "LEFT OUTER JOIN STANDARD STD ON A.ID_STANDARD = STD.ID ID_ORGANIZATION = "+orgId 
				+ " WHERE A.IS_DELETED = 0 AND STD.IS_DELETED = 0 AND SEC.IS_DELETED = 0 ORDER BY STD.ID , SEC.ID ";
		
		List<ClassesDTO> classList = new ArrayList<ClassesDTO>();
			classList = getJdbcTemplate().query(LIST_ALL_CLASS, classRoomExtractor.getAllClassList());
		return classList;
		
	}
	
	
	
	
	
	

	@Override
	public Object createNewClassRoom(Integer orgId,Integer standardId,
			Integer sectionId, Integer userId, Integer accessId) {
		ResponseBean responseBean = new ResponseBean();
		String CREATE_CLASS_ROOM = "INSERT INTO `classes` (`ID_STANDARD`, `ID_SECTION`, `ID_ORGANIZATION`, `IS_DELETED`, `CREATED_ON`, `CREATED_BY`)"
				+ " VALUES(?,?,?,0,NOW(),?)";
		Object[] ids = {standardId,sectionId,orgId,accessId};
		KeyHolder key = null ;
		Integer id =null;
		if(checkForClass( standardId,sectionId))
		id = getJdbcTemplate().update(CREATE_CLASS_ROOM,ids, key);
		return responseBean;
	}







	private boolean checkForClass(Integer standardId,Integer sectionId) {
		String checkForClass = "SELECT COUNT(*) FROM CLASSES WHERE IS_DELETED = 0 AND ID_STANDARD = "+standardId+" AND ID_SECTION = "+sectionId;
		Integer count = getJdbcTemplate().update(checkForClass);
		if(count == 0){
			return true;
		}
		return false;
	}

}
