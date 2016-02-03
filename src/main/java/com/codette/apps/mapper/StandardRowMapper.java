package com.codette.apps.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import com.codette.apps.dto.SectionDTO;
import com.codette.apps.dto.StandardDTO;

public class StandardRowMapper implements RowMapper<StandardDTO> {
	@Override
	public StandardDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		if(rs == null){
			return null;
		}
		StandardDTO standardDTO = new StandardDTO();
		standardDTO.setId(rs.getInt("ID"));
		standardDTO.setStandard(rs.getString("STANDARD"));
		return standardDTO;
	}

}
