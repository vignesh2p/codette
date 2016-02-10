package com.codette.apps.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.codette.apps.dao.LeaveDAO;
import com.codette.apps.dao.impl.StaffDAOImpl;
import com.codette.apps.dto.LeaveManagementDTO;
import com.codette.apps.dto.ResponseBean;
import com.codette.apps.util.CommonConstants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
@Component
public class LMService {
	final static Logger logger = Logger.getLogger(StaffDAOImpl.class);
	public static final Gson gson = new GsonBuilder().setDateFormat(CommonConstants.ISO_DATE_FORMAT).create();
	@Resource
      LeaveDAO leaveDAO;
	
	
	public List<LeaveManagementDTO> getPendingLeave(String status,Integer userId, String role) {
		// TODO Auto-generated method stub
		return leaveDAO.getPendingLeave(status, userId,role);
	}
	
	public List<LeaveManagementDTO> getHistoryLeave(String status,Integer userId, String role) {
		// TODO Auto-generated method stub
		return leaveDAO.getHistoryLeave(status, userId,role);
	}
	 @Transactional
	public ResponseBean Applyleave(LeaveManagementDTO leaveDTO, Integer userId) {
		// TODO Auto-generated method stub
		return leaveDAO.Applyleave(leaveDTO,userId);
	}
	
	 @Transactional
	public ResponseBean statusChange(List<LeaveManagementDTO> leaveDTO, Integer userId) {
		// TODO Auto-generated method stub
		return  leaveDAO.statusChange(leaveDTO,userId);
	}
}
