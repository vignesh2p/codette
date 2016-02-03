package com.codette.apps.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.codette.apps.dto.StatusDTO;

public class FormStatusRowMapper implements RowMapper<StatusDTO> {

	@Override
	public StatusDTO mapRow(ResultSet rs, int arg1) throws SQLException {
		if (rs == null) {
			return null;
		}
	   StatusDTO formStatusDTO = new StatusDTO();
		formStatusDTO.setId(rs.getInt("ID"));
		formStatusDTO.setStatus(rs.getString("STATUS"));
		return formStatusDTO;
	}
	
	

}
