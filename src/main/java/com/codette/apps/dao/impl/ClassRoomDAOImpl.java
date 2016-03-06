package com.codette.apps.dao.impl;


import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.codette.apps.ResultSetExtractor.ClassRoomExtractor;
import com.codette.apps.dao.ClassRoomDAO;
import com.codette.apps.dto.StaffClassDTO;
import com.codette.apps.service.CommonService;
import com.codette.apps.util.CommonConstants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public class ClassRoomDAOImpl extends NamedParameterJdbcDaoSupport implements ClassRoomDAO{
	
	@Resource
	private ClassRoomExtractor classRoomExtractor;
	
	@Resource
	private CommonService commonService;
	final static Logger logger = Logger.getLogger(ClassRoomDAOImpl.class);
	public static final Gson gson = new GsonBuilder().setDateFormat(CommonConstants.ISO_DATE_FORMAT).create();


	
	
	
	@Override
	public Object getClassList(Integer orgId, Integer userId,
			String role) throws Exception{
			Object object = null;
         if(userId != null){
        	return object = getJdbcTemplate().query(getClassListquery(userId,orgId),classRoomExtractor.getHandlingClassesList());
	         }
       
		return object;
	}
	
	@Override
    public Object getAllClassList(Integer orgId) throws Exception {

		
		Object object = null;
		object = getJdbcTemplate().query(getAllClassListQuery(orgId), classRoomExtractor.getAllClassList());
		return object;
		
	}
    
	
	@Override
	public Object createNewClassRoom(Integer orgId,Integer standardId,
			Integer sectionId, Integer userId, Integer accessId) throws Exception {
		   KeyHolder keyHolder = new GeneratedKeyHolder();
		   SqlParameterSource sql = null ;
		   Integer classId = null;
		   classId = checkForClass(standardId,sectionId,orgId);
		if(classId == 0){
		getNamedParameterJdbcTemplate().update(getCreateClassRoom(standardId,sectionId,orgId,accessId),sql,keyHolder);
		 classId = keyHolder.getKey().intValue();
		}
		return classId;
	}
	
	
	
	@Override
	public Object createHandlingClassforStaff(List<StaffClassDTO> staffClasses,
			Integer orgId, Integer userId, String role,Integer accessId) throws Exception {
		for(StaffClassDTO staffClass : staffClasses){
			staffClass.setOrgId(orgId);
			if(staffClass.getId() != null && staffClass.getId() != 0){
				getJdbcTemplate().update(getUpdateStaffHandlingClass(staffClass,userId,accessId));
			}else{
				getJdbcTemplate().update(getCreateStaffHandlingClass(staffClass,userId,accessId));
			}
		}
		return "Success";
	}
	


	
	
	
	
	/*===============================================SQL====================================================*/
	
	
	
	
	
	
	
	

	

	private Integer checkForClass(Integer standardId,Integer sectionId , Integer orgId) throws Exception {
		String checkForClass = "SELECT COUNT(*) FROM CLASSES WHERE IS_DELETED = 0 AND ID_STANDARD = "+standardId+" AND ID_SECTION = "+sectionId+" AND ID_ORGANIZATION = "+orgId;
		String CLASS_ID = "SELECT ID FROM CLASSES WHERE IS_DELETED = 0 AND ID_STANDARD = "+standardId+" AND ID_SECTION = "+sectionId+" AND ID_ORGANIZATION = "+orgId;
		Integer check = (Integer)getJdbcTemplate().queryForObject(checkForClass,Integer.class);
		if(check == 0 || check == null){
			return 0;
		}
		 return (Integer)getJdbcTemplate().queryForObject(CLASS_ID,Integer.class);
		
	}

	
	private String getClassListquery(Integer userId, Integer orgId) throws Exception {
		String CLASS_LIST = "SELECT * FROM `staff_class` SC "
				+ " LEFT OUTER JOIN `classes` A ON SC.ID_CLASS = A.ID"
				+ " LEFT OUTER JOIN SECTION SEC ON A.ID_SECTION = SEC.ID AND A.ID_ORGANIZATION = "+orgId
				+ " LEFT OUTER JOIN STANDARD STD ON A.ID_STANDARD = STD.ID AND STD.ID_ORGANIZATION = "+orgId 
				+ " WHERE A.IS_DELETED = 0 "
				+ " AND SC.ID_USER = "+userId +" AND SC.IS_DELETED = 0 " 
						+ " AND SC.ID_ORGANIZATION = "+orgId+" AND SC.ID_YEAR = "+commonService.getAcademicYearId()+
						" ORDER BY STD.ID , SEC.ID ";
		return CLASS_LIST;
	}
	
	private String getAllClassListQuery(Integer orgId) throws Exception {
		String LIST_ALL_CLASS=" SELECT * FROM `classes` A "
				+ " LEFT OUTER JOIN SECTION SEC ON A.ID_SECTION = SEC.ID AND A.ID_ORGANIZATION = "+orgId
				+ " LEFT OUTER JOIN STANDARD STD ON A.ID_STANDARD = STD.ID AND STD.ID_ORGANIZATION = "+orgId 
				+ " WHERE A.IS_DELETED = 0  ORDER BY STD.ID , SEC.ID ";
				return LIST_ALL_CLASS;
	}
	
	private String getCreateClassRoom(Integer standardId, Integer sectionId, Integer orgId, Integer accessId) throws Exception {
		// TODO Auto-generated method stub
		 String CREATE_CLASS_ROOM = "INSERT INTO `classes` (`ID_STANDARD`, `ID_SECTION`, `ID_ORGANIZATION`,`IS_ATTENDANCE_ENABLE`, `IS_DELETED`, `CREATED_ON`, `CREATED_BY`)"
				+ " VALUES("+standardId+","+sectionId+","+orgId+",0,0,NOW(),"+accessId+")";
		 return CREATE_CLASS_ROOM;
	}


	private String getCreateStaffHandlingClass(StaffClassDTO staffClass,Integer userId,Integer accessId) throws Exception {
		String query = "INSERT INTO `staff_class`(`ID_ORGANIZATION`, `ID_USER`, `ID_CLASS`, `ID_SUBJECT`, `ID_YEAR`,"
				+ " `IS_CLASS_TEACHER`, `IS_DELETED`, `CREATED_ON`, `CREATED_BY`)"
				+ " VALUES "
				+ "("+staffClass.getOrgId()+","+userId+","+staffClass.getClassRoom().getId()+","+staffClass.getSubject().getId()+","+staffClass.getYear().getId()+",";
		           if(staffClass.getIsClassTeacher() == 1){
		        	   query = query+ "1,0,NOW(),"+accessId+")";
		           }else{
		        	  query = query+ "0,0,NOW(),"+accessId+")";
		           }
		          
		return query;
	}
	
	
	private String getUpdateStaffHandlingClass(StaffClassDTO staffClass,
			Integer userId, Integer accessId) throws Exception {
	    String query = "UPDATE `staff_class` SET ";
	    if(userId != null){
	    query += "`ID_USER`="+userId+",";
	    }
	    if(staffClass.getClassRoom().getId() != null){
	   	query += "`ID_CLASS`= "+staffClass.getClassRoom().getId()+",";
	    }
	    if(staffClass.getSubject().getId() != null){
	    query += "`ID_SUBJECT`="+staffClass.getSubject().getId()+",";
	    }
	    if(staffClass.getIsClassTeacher()!= null){
	    query += "`IS_CLASS_TEACHER`="+staffClass.getIsClassTeacher()+",";
	    }
	    query += "`UPDATED_ON`=NOW(),`UPDATED_BY`="+accessId;
	   	query +=" WHERE `ID` = "+staffClass.getId()+" AND `ID_ORGANIZATION` = "+staffClass.getOrgId();
	   
		return query;
	}
}
