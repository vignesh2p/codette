package com.codette.apps.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;

import com.codette.apps.ResultSetExtractor.AttendenceExtractor;
import com.codette.apps.dao.AttendanceDAO;
import com.codette.apps.dto.ResponseBean;

public class AttendanceDAOImpl extends NamedParameterJdbcDaoSupport  implements AttendanceDAO{
	
	@Resource 
	private AttendenceExtractor attendenceExtractor;
	
  
	@Override
	public Object enableAttendance(Integer orgId, Integer userId,
			Integer accessId) throws NullPointerException {
		
		ResponseBean responseBean = new ResponseBean();
		
		String ENABLE = "UPDATE CLASSES SET IS_ATTENDANCE_ENABLE = 1 "
				+ " WHERE ID = "+getClassForStaff( orgId, userId)+" AND ID_ORGANIZATION = "+orgId+" AND IS_DELETED = 0 ";
			getJdbcTemplate().update(ENABLE);
			return responseBean;
	}

	@Override
	public Object getAttendance(Integer orgId, Integer userId) {
		
		Object object = null;
		
		object = getJdbcTemplate().queryForList(getStudentList(orgId,userId),attendenceExtractor.getAttendenceList());
		
	return object;
		
	}



	@Override
	public Object updateAttendance(Integer orgId, List<Integer> userIds,
			Integer accessId) {
		ResponseBean responseBean = new ResponseBean();
		String ATTENDENCE = "UPDATE ATTENDENCE SET IS_ABSENT = 1 "
				+ " WHERE ID_USER = ? AND ID_ORGANIZATION = "+orgId;
		
		String DISABLE = "UPDATE CLASSES SET IS_ATTENDANCE_ENABLE = 0 "
				+ " WHERE ID_CLASS = ? AND ID_ORGANIZATION = "+orgId+" AND IS_DELETED = 0 ";
		for(Integer studentId : userIds){
			Object[] inputIds = {studentId,orgId};
		getJdbcTemplate().update(ATTENDENCE, inputIds);
		}
		Object[] inputId = {getClassForStaff( orgId, accessId)};
		getJdbcTemplate().update(DISABLE, inputId);
		return responseBean;
	}


	@Override
	public Object createAttendanceProfile(Integer orgId,Integer userId,
			Integer accessId) {
		ResponseBean responseBean = new ResponseBean();
		String ATTENDENCE = "INSERT INTO `attendence`(`ID_USER`,`ID_ORGANIZATION`, `IS_ABSENT`,`IS_DELETED`,`CREATED_ON`,`CREATED_BY`) "
		      		+ "VALUES (?,?,0,0,NOW(),"+accessId+")";
		Object[] inputIds = {userId,orgId};
		getJdbcTemplate().update(ATTENDENCE, inputIds);
		return responseBean;
	}


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	private Integer getClassForStaff(Integer orgId, Integer userId) {
		String CLASS_LIST = "SELECT ID_CLASS FROM `staff_class` "
				+ " WHERE IS_CLASS_TEACHER = 1 AND IS_DELETED = 0 AND ID_USER = "+userId+" AND ID_ORGANIZATION = "+orgId;
		Integer ids = null;
	    ids = getJdbcTemplate().queryForObject(CLASS_LIST,Integer.class);
		return ids ;
	}	
	
	

	private String getStudentList(Integer orgId, Integer userId) {
		String GET_STUDENTS = "SELECT * FROM ATTENDENCE ATD " 
				+ " LEFT OUTER JOIN USER A ON A.ID = ATD.ID_USER AND A.ID_ORGANIZATION = "+orgId
				+ " LEFT OUTER JOIN CLASSES CLS ON A.ID_CLASS = CLS.ID AND CLS.ID_ORGANIZATION = "+orgId
				+ " WHERE A.IS_DELETED = 0 AND A.ID_CLASS = "+getClassForStaff( orgId, userId)+" AND ATD.ID_ORGANIZATION = "+orgId
				+ " ORDER BY A.FIRST_NAME ASC";
		return GET_STUDENTS;	
	}
}
