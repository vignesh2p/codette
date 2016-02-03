package com.codette.apps.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.codette.apps.dto.BloodGroupDTO;

public class BloodGroupRowMapper implements RowMapper<BloodGroupDTO> {




		@Override
		public BloodGroupDTO mapRow(ResultSet rs, int arg1) throws SQLException {
			if (rs == null) {
				return null;
			}
			
			BloodGroupDTO bloodGroupDTO = new BloodGroupDTO();
			bloodGroupDTO.setBloodGroup(rs.getString("NAME"));
			bloodGroupDTO.setId(rs.getInt("ID"));
			return bloodGroupDTO;
		}

	}


