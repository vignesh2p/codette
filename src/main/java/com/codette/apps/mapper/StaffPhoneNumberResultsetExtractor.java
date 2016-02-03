package com.codette.apps.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.codette.apps.dto.StaffAddressDTO;
import com.codette.apps.dto.StaffPhoneNumberDTO;

public class StaffPhoneNumberResultsetExtractor implements ResultSetExtractor {

	@Override
	public Object extractData(ResultSet rs) throws SQLException,
			DataAccessException {
		StaffPhoneNumberDTO staffPhoneNumberDTO = new StaffPhoneNumberDTO();
		staffPhoneNumberDTO.setId(rs.getInt("ID"));
		staffPhoneNumberDTO.setIdStaff(rs.getInt("ID_STAFF"));
		staffPhoneNumberDTO.setPhoneNumber(Long.valueOf(rs.getString("PHONE_NUMBER")));
		staffPhoneNumberDTO.setIsPrimary(rs.getInt("IS_PRIMARY"));
		return staffPhoneNumberDTO;
	}

}
