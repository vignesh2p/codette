package com.codette.apps.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.codette.apps.dao.AttendanceDAO;
import com.codette.apps.util.CommonConstants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Component
public class AttendanceService {
	
	final static Logger logger = Logger.getLogger(AttendanceService.class);
	public static final Gson gson = new GsonBuilder().setDateFormat(CommonConstants.ISO_DATE_FORMAT).create();
	@Resource
	private AttendanceDAO attendanceDAO;
	

	@Transactional
	public  Object enableAttendance(Integer orgId,
			Integer userId, Integer accessId) throws Exception{
		// TODO Auto-generated method stub
		return attendanceDAO.enableAttendance(orgId,userId,accessId);
	}

	public  Object getAttendance(Integer orgId,
			Integer userId)throws Exception {
		// TODO Auto-generated method stub
		 return  attendanceDAO.getAttendance(orgId,userId);
		 
	}

	@Transactional
	public  Object updateAttendance(Integer orgId,List<Integer> userIds,
			Integer accessId) throws Exception{
		// TODO Auto-generated method stub
		return attendanceDAO.updateAttendance(orgId,userIds,accessId);
	}

	@Transactional
	public Object createAttendanceProfile(Integer orgId, Integer userId,
			Integer accessId) throws Exception{
		return attendanceDAO.createAttendanceProfile(orgId,userId,accessId);
	}
}
