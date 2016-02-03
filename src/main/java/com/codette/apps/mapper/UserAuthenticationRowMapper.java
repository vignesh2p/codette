package com.codette.apps.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.codette.apps.dto.UserAuthenticationDTO;

public class UserAuthenticationRowMapper implements RowMapper<UserAuthenticationDTO> {

	@Override
	public UserAuthenticationDTO mapRow(ResultSet rs, int arg1)
			throws SQLException {
		if (rs == null) {
			return null;
		}
		
		UserAuthenticationDTO userAuthentication = new UserAuthenticationDTO();
		//user.setId(rs.getInt("id"));
		userAuthentication.setUserName(rs.getString("USER_NAME"));
		userAuthentication.setUserSecret(rs.getString("USER_SECRET"));
		userAuthentication.setId(rs.getInt("ID_USER"));
		return userAuthentication;
	}

}
