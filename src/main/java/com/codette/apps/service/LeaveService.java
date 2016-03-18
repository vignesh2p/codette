package com.codette.apps.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.codette.apps.dao.LeaveDAO;
import com.codette.apps.dto.LeaveManagementDTO;
import com.codette.apps.util.CommonConstants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
@Component
public class LeaveService {
	final static Logger logger = Logger.getLogger(LeaveService.class);
	public static final Gson gson = new GsonBuilder().setDateFormat(CommonConstants.ISO_DATE_FORMAT).create();
	@Resource
    private  LeaveDAO leaveDAO;
	
	
	public Object getPendingLeave(Integer orgId, String status,Integer userId, String role)throws Exception {
		// TODO Auto-generated method stub
		return leaveDAO.getPendingLeave(orgId,status, userId,role);
	}
	
	public Object getHistoryLeave(Integer orgId,String status,Integer userId, String role) throws Exception{
		// TODO Auto-generated method stub
		return leaveDAO.getHistoryLeave(orgId,status, userId,role);
	}
	
	 @Transactional
	public Object statusChange(List<LeaveManagementDTO> leaveDTO, List<LeaveManagementDTO> leaveManagementListDTO, Integer orgId, Integer userId, Integer accessId) throws Exception{
		// TODO Auto-generated method stub
		return  leaveDAO.statusChange(leaveDTO,orgId,userId,accessId);
	}

	 @Transactional
	public Object Applyleave(LeaveManagementDTO leaveManagementListDTO,
			Integer orgId, Integer userId, Integer accessId) throws Exception{
		// TODO Auto-generated method stub
		return leaveDAO.applyleave(leaveManagementListDTO, orgId, userId,accessId);
	}
}
