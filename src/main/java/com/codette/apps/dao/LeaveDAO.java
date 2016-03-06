package com.codette.apps.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.codette.apps.dto.LeaveManagementDTO;

@Component
public interface LeaveDAO {


	public Object getPendingLeave(Integer orgId, String status,Integer userId,String role)throws Exception;
	
	public Object getHistoryLeave(Integer orgId, String status,Integer userId,String role)throws Exception;

	public Object applyleave(LeaveManagementDTO leave,Integer orgId,Integer userId,Integer accessId)throws Exception;

	public Object  statusChange(List<LeaveManagementDTO> leaveDTO, Integer orgId, Integer userId, Integer accessId) throws Exception;
	
}
