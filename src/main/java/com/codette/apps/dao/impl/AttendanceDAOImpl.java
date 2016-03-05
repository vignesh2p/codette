package com.codette.apps.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;

import com.codette.apps.ResultSetExtractor.AttendenceExtractor;
import com.codette.apps.dao.AttendanceDAO;
import com.codette.apps.dto.ClassesDTO;
import com.codette.apps.dto.ResponseBean;
import com.codette.apps.dto.UserDTO;

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
		
		Integer classId = getClassForStaff( orgId, userId);
		List<UserDTO> studentsList = getJdbcTemplate().query(getStudentList(orgId,classId),attendenceExtractor.getStudentList());
		
		object = getJdbcTemplate().query(getClassForAttendance(orgId,classId),attendenceExtractor.getClassAttendance(studentsList));
	return object;
		
	}



	
	@Override
	public Object updateAttendance(Integer orgId, List<Integer> userIds,
			Integer accessId) {
		ResponseBean responseBean = new ResponseBean();
		String ATTENDENCE = "UPDATE ATTENDENCE SET IS_ABSENT = ? "
				+ " WHERE ID_USER = ? AND ID_ORGANIZATION = ?";
		
		String DISABLE = "UPDATE CLASSES SET IS_ATTENDANCE_ENABLE = 0 "
				+ " WHERE ID = "+getClassForStaff( orgId, accessId)+" AND ID_ORGANIZATION = "+orgId+" AND IS_DELETED = 0 ";
		for(Integer studentId : userIds){
			Object[] inputIds = {1,studentId,orgId};
		getJdbcTemplate().update(ATTENDENCE, inputIds);
		}
		getJdbcTemplate().update(DISABLE);
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
	
	

	private String getStudentList(Integer orgId, Integer classId) {
		String GET_STUDENTS = "SELECT ATD.ID_USER,A.FIRST_NAME,A.LAST_NAME,A.EMAIL_ADDRESS,A.DATE_OF_BIRTH,ATD.IS_ABSENT "
				+ " FROM USER A "
				+ " LEFT OUTER JOIN ATTENDENCE ATD ON A.ID = ATD.ID_USER AND ATD.ID_ORGANIZATION = "+orgId
				+ " WHERE A.IS_DELETED = 0 AND A.ID_CLASS = "+classId+" AND A.ID_ORGANIZATION = "+orgId
				+ " ORDER BY A.FIRST_NAME ASC";
		return GET_STUDENTS;	
	}
	
	private String getClassForAttendance(Integer orgId,Integer classId) {
		String CLASS_LIST = "SELECT IS_ATTENDANCE_ENABLE FROM `classes` "
				+ " WHERE IS_DELETED = 0 AND ID = "+classId+" AND ID_ORGANIZATION = "+orgId;
		return CLASS_LIST;
		
	}

}
