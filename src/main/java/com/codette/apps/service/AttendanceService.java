package com.codette.apps.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.codette.apps.dao.AttendanceDAO;
import com.codette.apps.dao.impl.StaffDAOImpl;
import com.codette.apps.dto.AttendenceDTO;
import com.codette.apps.dto.ResponseBean;
import com.codette.apps.util.CommonConstants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Component
public class AttendanceService {
	
	final static Logger logger = Logger.getLogger(StaffDAOImpl.class);
	public static final Gson gson = new GsonBuilder().setDateFormat(CommonConstants.ISO_DATE_FORMAT).create();
	@Resource
	AttendanceDAO attendanceDAO;

	@Transactional
	public  ResponseBean enableAttendance(Integer orgId,
			Integer userId, Integer accessId) {
		// TODO Auto-generated method stub
		return attendanceDAO.enableAttendance(orgId,userId,accessId);
	}

	public  List<AttendenceDTO> getAttendance(Integer orgId,Integer userId) {
		// TODO Auto-generated method stub
		return attendanceDAO.getAttendance(orgId,userId);
	}

	@Transactional
	public  ResponseBean updateAttendance(Integer orgId,List<Integer> userIds,
			Integer accessId) {
		// TODO Auto-generated method stub
		return attendanceDAO.updateAttendance(orgId,userIds,accessId);
	}

}
