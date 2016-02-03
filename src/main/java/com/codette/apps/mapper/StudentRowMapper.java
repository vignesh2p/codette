package com.codette.apps.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.codette.apps.dto.CommunityDTO;
import com.codette.apps.dto.StudentDTO;

public class StudentRowMapper implements RowMapper<StudentDTO>{

	@Override
	public StudentDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		
		CommunityDTO communityDTO = new CommunityDTO();
		communityDTO.setId(rs.getInt(""));
		
		return null;
	}

}
