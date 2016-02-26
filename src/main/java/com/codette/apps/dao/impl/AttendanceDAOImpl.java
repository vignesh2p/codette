package com.codette.apps.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;

import com.codette.apps.ResultSetExtractor.AttendenceExtractor;
import com.codette.apps.dao.AttendanceDAO;
import com.codette.apps.dto.AttendenceDTO;
import com.codette.apps.dto.ResponseBean;

public class AttendanceDAOImpl extends NamedParameterJdbcDaoSupport  implements AttendanceDAO{
	
	@Resource 
	private AttendenceExtractor attendenceExtractor;
	
  
	@Override
	public Object enableAttendance(Integer orgId, Integer userId,
			Integer accessId) throws NullPointerException {
		
		ResponseBean responseBean = new ResponseBean();
		
		String ENABLE = "UPDATE ATTENDENCE SET IS_ENABLE = 1 "
				+ " WHERE ID_USER IN (SELECT `ID` FROM USER WHERE ID_STANDARD = ? AND ID_SECTION = ? AND ID_ORGANIZATION = "+orgId+" AND IS_DELETED = 0) AND  ID_ORGANIZATION = "+orgId;
		List<Integer> ids = getClassForStaff( orgId, userId);
			Object[] inputIds = {ids.get(0),ids.get(1)};
			getJdbcTemplate().update(ENABLE, inputIds);
			return responseBean;
	}

	@Override
	public Object getAttendance(Integer orgId, Integer userId) {
		
		List<Integer> ids = getClassForStaff( orgId, userId);
		Integer standardId = ids.get(0);
		Integer sectionId = ids.get(1);
		Object object = getStudentListforattendence( standardId, sectionId,orgId);
		return object;
		
	}



	public Object getStudentListforattendence(Integer standardId,
			Integer sectionId,Integer orgId) {
		
		String GET_STUDENTS = "SELECT * FROM ATTENDENCE ATD " 
				+ " LEFT OUTER JOIN USER A ON A.ID = ATD.ID_STUDENT AND ID_ORGANIZATION = "+orgId
				+ " LEFT OUTER JOIN STANDARD STD ON A.ID_STANDARD = STD.ID AND ID_ORGANIZATION = "+orgId
				+ " LEFT OUTER JOIN SECTION SEC ON A.ID_SECTION = SEC.ID AND ID_ORGANIZATION = "+orgId
				+ " WHERE A.IS_DELETED = 0 AND A.ID_STANDARD =? AND A.ID_SECTION =? AND ID_ORGANIZATION = "+orgId
				+ " ORDER BY A.FIRST_NAME ASC";	
		List<AttendenceDTO> attendenceList = new ArrayList<AttendenceDTO>(); 
		Integer[] inputIds = {standardId, sectionId};
		attendenceList = getJdbcTemplate().query(GET_STUDENTS,inputIds,attendenceExtractor.getAttendenceList());
		
	return attendenceList;
	}

	private List<Integer> getClassForStaff(Integer orgId, Integer userId) {
		String CLASS_LIST = "SELECT ID_STANDARD,ID_SECTION FROM `staff_class` "
				+ " WHERE IS_CLASS_TEACHER = 1 AND IS_DELETED = 0 AND ID_USER = "+userId+" ID_ORGANIZATION = "+orgId;
		List<Integer> ids = new ArrayList<Integer>();
	    ids = getJdbcTemplate().query(CLASS_LIST, new ResultSetExtractor<List<Integer>>(){

			public List<Integer> extractData(ResultSet rs)
					throws SQLException, DataAccessException {
				List<Integer> ids = new ArrayList<Integer>();
				ids.add(rs.getInt("ID_STANDARD"));
				ids.add(rs.getInt("ID_SECTION"));
				return ids;
			}});
		return ids ;
	}

	@Override
	public Object updateAttendance(Integer orgId, List<Integer> userIds,
			Integer accessId) {
		ResponseBean responseBean = new ResponseBean();
		String ATTENDENCE = "UPDATE ATTENDENCE SET IS_ABSENT = 1 "
				+ " WHERE ID_USER = ? AND ID_ORGANIZATION = "+orgId;
		for(Integer studentId : userIds){
			Object[] inputIds = {studentId,orgId};
		getJdbcTemplate().update(ATTENDENCE, inputIds);
		}
		return responseBean;
	}


	@Override
	public Object createAttendanceProfile(Integer orgId,Integer userId,
			Integer accessId) {
		ResponseBean responseBean = new ResponseBean();
		String ATTENDENCE = "INSERT INTO `attendence`(`ID_USER`,`ID_ORGANIZATION`, `IS_ABSENT`,`IS_ENABLE`,`IS_DELETED`,`CREATED_ON`,`CREATED_BY`) "
		      		+ "VALUES (?,?,0,0,0,NOW(),"+accessId+")";
		Object[] inputIds = {userId,orgId};
		getJdbcTemplate().update(ATTENDENCE, inputIds);
		return responseBean;
	}


}
