package com.codette.apps.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.codette.apps.dao.impl.StaffDAOImpl;
import com.codette.apps.dto.ClassesDTO;
import com.codette.apps.dto.StaffClassDTO;
import com.codette.apps.util.CommonConstants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Component
public class ClassRoomService {
	
	final static Logger logger = Logger.getLogger(StaffDAOImpl.class);
	public static final Gson gson = new GsonBuilder().setDateFormat(CommonConstants.ISO_DATE_FORMAT).create();

	public List<StaffClassDTO> getClassList(Integer orgId, Integer userId,
			String role) {
		// TODO Auto-generated method stub
		return getClassList(orgId, userId,role);
	}

	public List<ClassesDTO> getAllClassList(Integer orgId ) {
		// TODO Auto-generated method stub
		return  getAllClassList(orgId);
	}

}
