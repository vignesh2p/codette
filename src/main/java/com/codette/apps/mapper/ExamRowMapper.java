package com.codette.apps.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.codette.apps.dto.ExamDTO;

public class ExamRowMapper implements RowMapper<ExamDTO>{

	@Override
	public ExamDTO mapRow(ResultSet rs, int arg1) throws SQLException {
		if (rs == null) {
			return null;
		}
		ExamDTO examDTO = new ExamDTO();
		examDTO.setId(rs.getInt("ID"));
		examDTO.setExam(rs.getString("EXAM"));
		return examDTO;
	}

}
