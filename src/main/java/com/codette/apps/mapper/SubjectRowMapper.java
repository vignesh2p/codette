package com.codette.apps.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.codette.apps.dto.SubjectDTO;

public class SubjectRowMapper implements RowMapper<SubjectDTO>{

	@Override
	public SubjectDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		if(rs == null){
			return null;
		}
		SubjectDTO SubjectDTO = new SubjectDTO();
		SubjectDTO.setId(rs.getInt("ID"));
		SubjectDTO.setSubject(rs.getString("SUBJECT"));
		SubjectDTO.setSubjectCode(rs.getString("SUBJECT_CODE"));
		return SubjectDTO;
	}

}
