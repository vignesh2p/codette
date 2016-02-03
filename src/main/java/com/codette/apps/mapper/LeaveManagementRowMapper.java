package com.codette.apps.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.codette.apps.dto.LeaveManagementDTO;
import com.codette.apps.dto.StatusDTO;
import com.codette.apps.dto.UserDTO;

public class LeaveManagementRowMapper implements RowMapper<LeaveManagementDTO>{

	@Override
	public LeaveManagementDTO mapRow(ResultSet rs, int arg1)
			throws SQLException {
		if (rs == null) {
			return null;
		}
		LeaveManagementDTO leaveManagementDTO = new LeaveManagementDTO();
		leaveManagementDTO.setId(rs.getInt("ID"));
		leaveManagementDTO.setStartTime(rs.getTimestamp("START_TIME").toString());
		leaveManagementDTO.setEndTime(rs.getTimestamp("END_TIME").toString());
		leaveManagementDTO.setReason(rs.getString("REASON"));
		leaveManagementDTO.setIsTaken(rs.getInt("IS_TAKEN"));
		UserDTO staff = new UserDTO();
		staff.setId(rs.getInt("ID_STAFF"));
		staff.setFirstName(rs.getString("FIRST_NAME"));
		staff.setLastName(rs.getString("LAST_NAME"));
		leaveManagementDTO.setStaff(staff);
		StatusDTO status = new StatusDTO();
		status.setId(rs.getInt("ID_FORM_STATUS"));
		status.setStatus(rs.getString("STATUS"));
		leaveManagementDTO.setFormStatus(status);
		return leaveManagementDTO;
	}

}
