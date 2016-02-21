package com.codette.apps.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import com.codette.apps.dto.AddressDTO;
import com.codette.apps.dto.CommunityDTO;
import com.codette.apps.dto.DesignationDTO;
import com.codette.apps.dto.GenderDTO;
import com.codette.apps.dto.PhoneNumberDTO;
import com.codette.apps.dto.ReligionDTO;
import com.codette.apps.dto.RoleDTO;
import com.codette.apps.dto.UserDTO;

@Component
public class UserExtractor {

		public ResultSetExtractor<UserDTO> setUserDetails(final List<AddressDTO> addresses, final List<PhoneNumberDTO> phones) {
			return new ResultSetExtractor<UserDTO>(){

				public UserDTO extractData(ResultSet rs) throws SQLException,
						DataAccessException {
					UserDTO user = null;
				    if(rs.next()){
				    	user = new UserDTO();
					user.setId(rs.getInt("ID"));
					
					    RoleDTO role = new RoleDTO();
						role.setId(rs.getInt("ID"));
					    role.setRole(rs.getString("ROLE"));
					    user.setRole(role);
					    
					user.setFirstName(rs.getString("FIRST_NAME"));
					user.setLastName(rs.getString("LAST_NAME"));
					user.setDateOfBirth(rs.getDate("DATE_OF_BIRTH").toString());
					user.setEmailAddresses(rs.getString("EMAIL_ADDRESS"));
					user.setExperience(rs.getInt("EXPERIENCE"));
					user.setBioGraphy(rs.getString("BIO_GRAPHY"));
					user.setDateOfJoining(rs.getDate("DATE_OF_JOINING").toString());
					user.setFatherName(rs.getString("FATHER_NAME"));
					user.setAge(rs.getInt("AGE"));
					
					
						DesignationDTO designation = new DesignationDTO();
						designation.setId(rs.getInt("ID"));
						designation.setDesignation(rs.getString("DESIGNATION"));
					    user.setDesignation(designation);
					    
					    
						GenderDTO gender = new GenderDTO();
						gender.setId(rs.getInt("ID"));
						gender.setGender(rs.getString("GENDER"));
						user.setGender(gender);
						
						CommunityDTO community = new CommunityDTO();
						community.setId(rs.getInt("ID"));
						community.setCommunity("COMMUNITY");
						user.setCommunity(community);
						
						
						ReligionDTO religion = new ReligionDTO();
						religion.setId(rs.getInt("ID"));
						religion.setReligion("RELIGION");
						user.setReligion(religion);
						
						user.setAddresses(addresses);
						
						user.setPhoneNumbers(phones);
				    }
					return user;
				}

			  };
		}

		public ResultSetExtractor setAddressList() {
			// TODO Auto-generated method stub
			return null;
		}

		public ResultSetExtractor setPhonenumber() {
			// TODO Auto-generated method stub
			return null;
		}
}
