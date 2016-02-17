package com.codette.apps.dao;

import java.util.List;

import com.codette.apps.dto.LeaveManagementDTO;
public interface LeaveDAO {


	public Object getPendingLeave(Integer orgId, String status,Integer userId,String role);
	
	public Object getHistoryLeave(Integer orgId, String status,Integer userId,String role);

	public Object applyleave(LeaveManagementDTO leave,Integer orgId,Integer userId,Integer accessId);

	public Object  statusChange(List<LeaveManagementDTO> leaveDTO, Integer orgId, Integer userId, Integer accessId);
	
}
