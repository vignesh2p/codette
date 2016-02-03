package com.codette.apps.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.codette.apps.dto.DesignationDTO;

public class DesignationRowMapper  implements RowMapper<DesignationDTO> {


		@Override
		public DesignationDTO mapRow(ResultSet rs, int arg1) throws SQLException {
			if (rs == null) {
				return null;
			}
			
			DesignationDTO designationDTO = new DesignationDTO();
			designationDTO.setId(rs.getInt("ID"));
			designationDTO.setDesignation(rs.getString("DESIGNATION"));
			return designationDTO;
		}

	}

