package com.codette.apps.dao.impl;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;

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
			Object object = null;
         if(userId != null && role.isEmpty()){
        	 object = getJdbcTemplate().query(getClassListquery(userId,orgId),classRoomExtractor.getHandlingClassesList());
	         }
         else{
	       	 object = getAllClassList(orgId);
	         }
		return object;
	}
	
	
	@Override
	public Object createNewClassRoom(Integer orgId,Integer standardId,
			Integer sectionId, Integer userId, Integer accessId) {
		ResponseBean responseBean = new ResponseBean();
		if(checkForClass(standardId,sectionId))
		getJdbcTemplate().update(getCreateClassRoom(standardId,sectionId,orgId,accessId));
		return responseBean;
	}
	
	
	private Object getAllClassList(Integer orgId) {

		
		List<ClassesDTO> classList = new ArrayList<ClassesDTO>();
			classList = getJdbcTemplate().query(getAllClassListQuery(orgId), classRoomExtractor.getAllClassList());
		return classList;
		
	}
	
	
	
	
	/*===============================================SQL====================================================*/
	
	
	
	
	
	
	
	

	private boolean checkForClass(Integer standardId,Integer sectionId) {
		String checkForClass = "SELECT COUNT(*) FROM CLASSES WHERE IS_DELETED = 0 AND ID_STANDARD = "+standardId+" AND ID_SECTION = "+sectionId;
		Integer count = getJdbcTemplate().queryForObject(checkForClass,Integer.class);
		if(count == 0){
			return true;
		}
		return false;
	}

	
	private String getClassListquery(Integer userId, Integer orgId) {
		String CLASS_LIST = "SELECT * FROM `staff_class` A "
				+ "LEFT OUTER JOIN SUBJECT SUB ON A.ID_SUBJECT = SUB.ID AND SUB.ID_ORGANIZATION = "+orgId
				+ "LEFT OUTER JOIN STANDARD STD ON A.ID_STANDARD = STD.ID AND STD.ID_ORGANIZATION = "+orgId
				+ "LEFT OUTER JOIN SECTION SEC ON A.ID_SECTION = SEC.ID AND SEC.ID_ORGANIZATION = "+orgId
				+ "LEFT OUTER JOIN YEAR Y ON A.ID_YEAR = Y.ID AND Y.ID_ORGANIZATION = "+orgId	
				+ "WHERE A.ID_USER = "+userId +" A.IS_DELETED = 0 AND STD.IS_DELETED = 0 AND SEC.IS_DELETED = 0 " 
						+ " AND A.ID_ORGANIZATION = "+orgId+ "ORDER BY STD.ID , SEC.ID";
		return CLASS_LIST;
	}
	
	private String getAllClassListQuery(Integer orgId) {
		String LIST_ALL_CLASS=" SELECT * FROM `classes` A "
				+ "LEFT OUTER JOIN SECTION SEC ON A.ID_SECTION = SEC.ID AND ID_ORGANIZATION = "+orgId
				+ "LEFT OUTER JOIN STANDARD STD ON A.ID_STANDARD = STD.ID ID_ORGANIZATION = "+orgId 
				+ " WHERE A.IS_DELETED = 0 AND STD.IS_DELETED = 0 AND SEC.IS_DELETED = 0 ORDER BY STD.ID , SEC.ID ";
				return LIST_ALL_CLASS;
	}
	
	private String getCreateClassRoom(Integer standardId, Integer sectionId, Integer orgId, Integer accessId) {
		// TODO Auto-generated method stub
		 String CREATE_CLASS_ROOM = "INSERT INTO `classes` (`ID_STANDARD`, `ID_SECTION`, `ID_ORGANIZATION`, `IS_DELETED`, `CREATED_ON`, `CREATED_BY`)"
				+ " VALUES("+standardId+","+sectionId+","+orgId+",0,NOW(),"+accessId+")";
		 return CREATE_CLASS_ROOM;
	}
}
