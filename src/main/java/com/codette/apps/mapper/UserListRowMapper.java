package com.codette.apps.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.codette.apps.dto.CommunityDTO;
import com.codette.apps.dto.DesignationDTO;
import com.codette.apps.dto.GenderDTO;
import com.codette.apps.dto.ReligionDTO;
import com.codette.apps.dto.RoleDTO;
import com.codette.apps.dto.UserDTO;

public class UserListRowMapper implements RowMapper<UserDTO> {

	public UserDTO mapRow(ResultSet rs, int arg1) throws SQLException {
		if (rs == null) {
			return null;
		}

	
	    UserDTO user = new UserDTO();
		user.setId(rs.getInt("ID"));
		if(rs.getInt("ID_ROLE")!=0){
	       RoleDTO role = new RoleDTO();
	       role.setId(rs.getInt("ID"));
	       role.setRole(rs.getString("ROLE"));
		   user.setRole(role);
	    }
		user.setFirstName(rs.getString("FIRST_NAME"));
		user.setLastName(rs.getString("LAST_NAME"));
		user.setDateOfBirth(rs.getDate("DATE_OF_BIRTH").toString());
		user.setEmailAddresses(rs.getString("EMAIL_ADDRESS"));
		user.setExperience(rs.getInt("EXPERIENCE"));
		user.setBioGraphy(rs.getString("BIO_GRAPHY"));
		user.setDateOfJoining(rs.getString("DATE_OF_JOINING"));
		user.setFatherName(rs.getString("FATHER_NAME"));
		user.setAge(rs.getInt("AGE"));
		if(rs.getInt("ID_DESIGNATION")!= 0){
			DesignationDTO designation = new DesignationDTO();
			designation.setId(rs.getInt("ID_DESIGNATION"));
			designation.setDesignation(rs.getString("DESIGNATION"));
		    user.setDesignation(designation);
		}
		if((rs.getInt("ID_GENDER")) != 0){
			GenderDTO gender = new GenderDTO();
			gender.setId(rs.getInt("ID_GENDER"));
			gender.setGender(rs.getString("GENDER"));
			user.setGender(gender);
		}
		if(rs.getInt("ID_COMMUNITY") !=0){
			CommunityDTO community = new CommunityDTO();
			community.setId(rs.getInt("ID_COMMUNITY"));
			community.setCommunity(rs.getString("COMMUNITY"));
			user.setCommunity(community);
		}
		if(rs.getInt("ID_RELIGION") !=0){
			ReligionDTO religion = new ReligionDTO();
			religion.setId(rs.getInt("ID_RELIGION") );
			religion.setReligion(rs.getString("RELIGION"));
			user.setReligion(religion);
		}
		return user;
	}

}
