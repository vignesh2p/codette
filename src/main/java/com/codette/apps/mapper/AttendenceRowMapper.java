package com.codette.apps.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.codette.apps.dto.AttendenceDTO;

public class AttendenceRowMapper implements RowMapper<AttendenceDTO> {


		@Override
		public AttendenceDTO mapRow(ResultSet rs, int arg1) throws SQLException {
			if (rs == null) {
				return null;
			}
			
			AttendenceDTO attendenceDTO = new AttendenceDTO();
			attendenceDTO.setIsAbsent(rs.getInt("IS_ABSENT"));
			return attendenceDTO;
		}

	}

