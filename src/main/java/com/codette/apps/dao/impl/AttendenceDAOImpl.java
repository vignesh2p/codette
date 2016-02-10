package com.codette.apps.dao.impl;

import java.util.List;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;

import com.codette.apps.dao.AttendanceDAO;
import com.codette.apps.dto.AttendenceDTO;
import com.codette.apps.dto.ResponseBean;

public class AttendenceDAOImpl extends NamedParameterJdbcDaoSupport implements AttendanceDAO{

	@Override
	public ResponseBean enableAttendance(Integer orgId, Integer userId,
			Integer accessId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AttendenceDTO> getAttendance(Integer orgId, Integer userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseBean updateAttendance(Integer orgId, List<Integer> userIds,
			Integer accessId) {
		// TODO Auto-generated method stub
		return null;
	}

}
