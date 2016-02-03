package com.codette.apps.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import com.codette.apps.dto.StaffAddressDTO;

public class StaffAddressResultsetExtractor implements  ResultSetExtractor {

	@Override
	public Object extractData(ResultSet rs) throws SQLException,
			DataAccessException {
	StaffAddressDTO staffAddressDTO = new StaffAddressDTO();
	staffAddressDTO.setId(rs.getInt("ID"));
	staffAddressDTO.setIdStaff(rs.getInt("ID_STAFF"));
	staffAddressDTO.setAddress(rs.getString("ADDRESS"));
	staffAddressDTO.setIsPrimary(rs.getInt("IS_PRIMARY"));
	return staffAddressDTO;
	}
	
}
