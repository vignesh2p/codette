package com.codette.apps.dao.impl;

import java.util.List;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;

import com.codette.apps.dao.ClassRoomDAO;
import com.codette.apps.dto.ClassesDTO;
import com.codette.apps.dto.StaffClassDTO;

public class ClassRoomDAOImpl extends NamedParameterJdbcDaoSupport implements ClassRoomDAO{

	@Override
	public List<StaffClassDTO> getClassList(Integer orgId, Integer userId,
			String role) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ClassesDTO> getAllClassList(Integer orgId) {
		// TODO Auto-generated method stub
		return null;
	}

}
