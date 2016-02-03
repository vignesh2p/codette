package com.codette.apps.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.codette.apps.dao.LeaveDAO;
import com.codette.apps.dto.LeaveManagementDTO;
import com.codette.apps.dto.ResponseBean;
@Component
public class LMService {
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
