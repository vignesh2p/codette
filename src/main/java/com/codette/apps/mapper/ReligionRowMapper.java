package com.codette.apps.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.codette.apps.dto.ReligionDTO;

public class ReligionRowMapper implements RowMapper<ReligionDTO>{

	@Override
	public ReligionDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		if (rs == null) {
			return null;
		}
		
		ReligionDTO religionDTO = new ReligionDTO();
		religionDTO.setReligion(rs.getString("RELIGION"));
		religionDTO.setId(rs.getInt("ID"));
		return religionDTO;
	}
	

}
