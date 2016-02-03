package com.codette.apps.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.codette.apps.dto.StaffAddressDTO;

public class StaffAddressRowMapper implements RowMapper<StaffAddressDTO>{

	@Override
	public StaffAddressDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
	if(rs == null){
		return null;
	}
	StaffAddressDTO staffAddressDTO = new StaffAddressDTO();
	staffAddressDTO.setId(rs.getInt("ID"));
	staffAddressDTO.setIdStaff(rs.getInt("ID_STAFF"));
	staffAddressDTO.setAddress(rs.getString("ADDRESS"));
	staffAddressDTO.setIsPrimary(rs.getInt("IS_PRIMARY"));
	return staffAddressDTO;
	}
	
}
