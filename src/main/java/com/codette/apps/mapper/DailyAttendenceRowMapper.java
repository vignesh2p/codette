package com.codette.apps.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.codette.apps.dto.DailyAbsenteesDTO;

public class DailyAttendenceRowMapper implements RowMapper<DailyAbsenteesDTO>{

	@Override
	public DailyAbsenteesDTO mapRow(ResultSet rs, int arg1) throws SQLException {
		if (rs == null) {
			return null;
		}
		DailyAbsenteesDTO dailyAbsenteesDTO = new DailyAbsenteesDTO();
		dailyAbsenteesDTO.setStudentId(rs.getInt("ID_STUDENT"));
		dailyAbsenteesDTO.setDateOfLeave(rs.getDate("DATE_OF_BIRTH"));
		dailyAbsenteesDTO.setCreatedOn(rs.getTimestamp("CREATED_ON"));
		return dailyAbsenteesDTO;
	}

}
