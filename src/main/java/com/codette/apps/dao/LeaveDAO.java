package com.codette.apps.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.codette.apps.dto.LeaveManagementDTO;
import com.codette.apps.dto.ResponseBean;
public interface LeaveDAO {


	public List<LeaveManagementDTO> getPendingLeave(String status,Integer userId,String role);
	public List<LeaveManagementDTO> getHistoryLeave(String status,Integer userId,String role);

	public ResponseBean Applyleave(LeaveManagementDTO leave,Integer accessId);

	public ResponseBean  statusChange(List<LeaveManagementDTO> leaveDTO, Integer userId);
	
}
