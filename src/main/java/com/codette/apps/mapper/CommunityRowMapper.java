package com.codette.apps.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.codette.apps.dto.CommunityDTO;

public class CommunityRowMapper implements RowMapper<CommunityDTO>{

	@Override
	public CommunityDTO mapRow(ResultSet rs, int arg1) throws SQLException {
		if (rs == null) {
			return null;
		}
		
		CommunityDTO communityDTO = new CommunityDTO();
		communityDTO.setId(rs.getInt("ID"));
		communityDTO.setCommunity(rs.getString("COMMUNITY"));
		return communityDTO;
	}
}
