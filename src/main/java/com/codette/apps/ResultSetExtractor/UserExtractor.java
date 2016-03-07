package com.codette.apps.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import com.codette.apps.dto.AddressDTO;
import com.codette.apps.dto.BloodGroupDTO;
import com.codette.apps.dto.ClassesDTO;
import com.codette.apps.dto.CommunityDTO;
import com.codette.apps.dto.DesignationDTO;
import com.codette.apps.dto.GenderDTO;
import com.codette.apps.dto.OrganizationDTO;
import com.codette.apps.dto.PhoneNumberDTO;
import com.codette.apps.dto.ReligionDTO;
import com.codette.apps.dto.RoleDTO;
import com.codette.apps.dto.SectionDTO;
import com.codette.apps.dto.StandardDTO;
import com.codette.apps.dto.UserDTO;
import com.codette.apps.dto.YearDTO;

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
					
						OrganizationDTO org = new OrganizationDTO();
						org.setId(rs.getInt("ID_ORGANIZATION"));
						org.setOrganizationName(rs.getString("ORGANIZATION_NAME"));
						org.setNickName(rs.getString("NICK_NAME"));
					
					    RoleDTO role = new RoleDTO();
						role.setId(rs.getInt("ID_ROLE"));
					    role.setRole(rs.getString("ROLE"));
					    user.setRole(role);
					    
					user.setRegistrationNumber(rs.getString("REGISTRATION_ID"));  
					user.setFirstName(rs.getString("FIRST_NAME"));
					user.setLastName(rs.getString("LAST_NAME"));
					user.setDateOfBirth(rs.getDate("DATE_OF_BIRTH").toString());
					user.setEmailAddresses(rs.getString("EMAIL_ADDRESS"));
					user.setExperience(rs.getInt("EXPERIENCE"));
					user.setBioGraphy(rs.getString("BIO_GRAPHY"));
					user.setDateOfJoining(rs.getDate("DATE_OF_JOINING").toString());
					user.setFatherName(rs.getString("FATHER_NAME"));
					user.setMotherName(rs.getString("MOTHER_NAME"));
					user.setAge(rs.getInt("AGE"));
					
					
					ClassesDTO classesDTO = new ClassesDTO();
					
						StandardDTO standard = new StandardDTO();
						standard.setId(rs.getInt("ID_STANDARD"));
						standard.setStandard(rs.getString("STANDARD"));
						classesDTO.setStandard(standard);
						
						SectionDTO section = new SectionDTO();
						section.setId(rs.getInt("ID_SECTION"));
						section.setSection(rs.getString("SECTION"));
						classesDTO.setSection(section);
						
	                user.setClassRoom(classesDTO);
						
					
						DesignationDTO designation = new DesignationDTO();
						designation.setId(rs.getInt("ID_DESIGNATION"));
						designation.setDesignation(rs.getString("DESIGNATION"));
					    user.setDesignation(designation);
					    
					    
						GenderDTO gender = new GenderDTO();
						gender.setId(rs.getInt("ID_GENDER"));
						gender.setGender(rs.getString("GENDER"));
						user.setGender(gender);
						
						YearDTO year = new YearDTO();
						year.setId(rs.getInt("ID_YEAR"));
						year.setAcademicYear(rs.getString("ACADEMIC_YEAR"));
						user.setYear(year);
						
						CommunityDTO community = new CommunityDTO();
						community.setId(rs.getInt("ID_COMMUNITY"));
						community.setCommunity(rs.getString("COMMUNITY"));
						user.setCommunity(community);
						
						
						ReligionDTO religion = new ReligionDTO();
						religion.setId(rs.getInt("ID_RELIGION"));
						religion.setReligion(rs.getString("RELIGION"));
						user.setReligion(religion);
						
						BloodGroupDTO BG = new BloodGroupDTO();
						BG.setId(rs.getInt("ID_BLOOD_GROUP"));
						BG.setBloodGroup(rs.getString("BLOOD_GROUP"));
						user.setBloodGroup(BG);
						
						user.setIsDeleted(rs.getInt("IS_DELETED"));
						user.setCreatedOn(rs.getString("CREATED_ON"));
						user.setAddresses(addresses);
						
						user.setPhoneNumbers(phones);
				    }
					return user;
				}

			  };
		}
		
		public ResultSetExtractor<List<UserDTO>> setUserListDetails(final List<AddressDTO> addresses, final List<PhoneNumberDTO> phones) {
			return new ResultSetExtractor<List<UserDTO>>(){

				public List<UserDTO> extractData(ResultSet rs) throws SQLException,
						DataAccessException {
					List<UserDTO> users = new ArrayList<UserDTO>();
					UserDTO user = null;
				    while(rs.next()){
				    	user = new UserDTO();
					user.setId(rs.getInt("ID"));
					
						OrganizationDTO org = new OrganizationDTO();
						org.setId(rs.getInt("ID_ORGANIZATION"));
						org.setOrganizationName(rs.getString("ORGANIZATION_NAME"));
						org.setNickName(rs.getString("NICK_NAME"));
					
					    RoleDTO role = new RoleDTO();
						role.setId(rs.getInt("ID_ROLE"));
					    role.setRole(rs.getString("ROLE"));
					    user.setRole(role);
					    
					user.setRegistrationNumber(rs.getString("REGISTRATION_ID"));  
					user.setFirstName(rs.getString("FIRST_NAME"));
					user.setLastName(rs.getString("LAST_NAME"));
					user.setDateOfBirth(rs.getDate("DATE_OF_BIRTH").toString());
					user.setEmailAddresses(rs.getString("EMAIL_ADDRESS"));
					user.setExperience(rs.getInt("EXPERIENCE"));
					user.setBioGraphy(rs.getString("BIO_GRAPHY"));
					user.setDateOfJoining(rs.getDate("DATE_OF_JOINING").toString());
					user.setFatherName(rs.getString("FATHER_NAME"));
					user.setMotherName(rs.getString("MOTHER_NAME"));
					user.setAge(rs.getInt("AGE"));
					
					
					ClassesDTO classesDTO = new ClassesDTO();
					
						StandardDTO standard = new StandardDTO();
						standard.setId(rs.getInt("ID_STANDARD"));
						standard.setStandard(rs.getString("STANDARD"));
						classesDTO.setStandard(standard);
						
						SectionDTO section = new SectionDTO();
						section.setId(rs.getInt("ID_SECTION"));
						section.setSection(rs.getString("SECTION"));
						classesDTO.setSection(section);
						
	                user.setClassRoom(classesDTO);
						
					
						DesignationDTO designation = new DesignationDTO();
						designation.setId(rs.getInt("ID_DESIGNATION"));
						designation.setDesignation(rs.getString("DESIGNATION"));
					    user.setDesignation(designation);
					    
					    
						GenderDTO gender = new GenderDTO();
						gender.setId(rs.getInt("ID_GENDER"));
						gender.setGender(rs.getString("GENDER"));
						user.setGender(gender);
						
						YearDTO year = new YearDTO();
						year.setId(rs.getInt("ID_YEAR"));
						year.setAcademicYear(rs.getString("ACADEMIC_YEAR"));
						user.setYear(year);
						
						CommunityDTO community = new CommunityDTO();
						community.setId(rs.getInt("ID_COMMUNITY"));
						community.setCommunity(rs.getString("COMMUNITY"));
						user.setCommunity(community);
						
						
						ReligionDTO religion = new ReligionDTO();
						religion.setId(rs.getInt("ID_RELIGION"));
						religion.setReligion(rs.getString("RELIGION"));
						user.setReligion(religion);
						
						BloodGroupDTO BG = new BloodGroupDTO();
						BG.setId(rs.getInt("ID_BLOOD_GROUP"));
						BG.setBloodGroup(rs.getString("BLOOD_GROUP"));
						user.setBloodGroup(BG);
						
						user.setIsDeleted(rs.getInt("IS_DELETED"));
						user.setCreatedOn(rs.getString("CREATED_ON"));
						user.setAddresses(addresses);
						
						user.setPhoneNumbers(phones);
						users.add(user);
				    }
					return users;
				}

			  };
		}

		public ResultSetExtractor<List<AddressDTO>> setAddressList() {
			return new ResultSetExtractor<List<AddressDTO>>(){

				public List<AddressDTO> extractData(ResultSet rs) throws SQLException,
						DataAccessException {
					AddressDTO address = null;
					List<AddressDTO> addresses = new ArrayList<AddressDTO>();
					
				    while(rs.next()){
				    	address = new AddressDTO();
				    	address.setId(rs.getInt("ID"));
					
				    	address.setOrgId(rs.getInt("ID_ORGANIZATION"));
						
				    	address.setAddress(rs.getString("ADDRESS"));
				    	address.setIsPrimary(rs.getInt("IS_PRIMARY"));
				    		
				    	address.setIsDeleted(rs.getInt("IS_DELETED"));
				    	address.setCreatedOn(rs.getString("CREATED_ON"));
				    	address.setCreatedBy(rs.getInt("CREATED_BY"));
				    	address.setUpdatedOn(rs.getString("UPDATED_ON"));
				    	address.setCreatedBy(rs.getInt("UPDATED_BY"));
						
						addresses.add(address);
				    }
					return addresses;
				}

			  };
}

		public ResultSetExtractor<List<PhoneNumberDTO>> setPhonenumber() {
			return new ResultSetExtractor<List<PhoneNumberDTO>>(){

				public List<PhoneNumberDTO> extractData(ResultSet rs) throws SQLException,
						DataAccessException {
					PhoneNumberDTO phone = null;
					List<PhoneNumberDTO> phones = new ArrayList<PhoneNumberDTO>();
					
				    while(rs.next()){
				    	phone = new PhoneNumberDTO();
				    	phone.setId(rs.getInt("ID"));
					
				    	phone.setOrgId(rs.getInt("ID_ORGANIZATION"));
						
				    	phone.setPhoneNumber(rs.getString("PHONE_NUMBER"));
				    	phone.setIsPrimary(rs.getInt("IS_PRIMARY"));
				    		
				    	phone.setIsDeleted(rs.getInt("IS_DELETED"));
				    	phone.setCreatedOn(rs.getString("CREATED_ON"));
				    	phone.setCreatedBy(rs.getInt("CREATED_BY"));
				    	phone.setUpdatedOn(rs.getString("UPDATED_ON"));
				    	phone.setCreatedBy(rs.getInt("UPDATED_BY"));
						
						phones.add(phone);
				    }
					return phones;
				}

			  };
		}

		public ResultSetExtractor<List<UserDTO>> setUserList(Object object, Object object2) {
			// TODO Auto-generated method stub
			return new ResultSetExtractor<List<UserDTO>>(){

				public List<UserDTO> extractData(ResultSet rs) throws SQLException,
						DataAccessException {
					List<UserDTO> users = new ArrayList<UserDTO>();
					UserDTO user = null;
				    while(rs.next()){
				    	user = new UserDTO();
					user.setId(rs.getInt("ID"));
					/*
						OrganizationDTO org = new OrganizationDTO();
						org.setId(rs.getInt("ID_ORGANIZATION"));
						org.setOrganizationName(rs.getString("ORGANIZATION_NAME"));
						org.setNickName(rs.getString("NICK_NAME"));
					*/
					  /*  RoleDTO role = new RoleDTO();
						role.setId(rs.getInt("ID_ROLE"));
					    role.setRole(rs.getString("ROLE"));
					    user.setRole(role);*/
					    
					user.setRegistrationNumber(rs.getString("REGISTRATION_ID"));  
					user.setFirstName(rs.getString("FIRST_NAME"));
					user.setLastName(rs.getString("LAST_NAME"));
					user.setDateOfBirth(rs.getDate("DATE_OF_BIRTH").toString());
					user.setEmailAddresses(rs.getString("EMAIL_ADDRESS"));
					user.setExperience(rs.getInt("EXPERIENCE"));
					user.setBioGraphy(rs.getString("BIO_GRAPHY"));
					user.setDateOfJoining(rs.getDate("DATE_OF_JOINING").toString());
					user.setFatherName(rs.getString("FATHER_NAME"));
					user.setMotherName(rs.getString("MOTHER_NAME"));
					user.setAge(rs.getInt("AGE"));
					
					/*
					ClassesDTO classesDTO = new ClassesDTO();
					
						StandardDTO standard = new StandardDTO();
						standard.setId(rs.getInt("ID_STANDARD"));
						standard.setStandard(rs.getString("STANDARD"));
						classesDTO.setStandard(standard);
						
						SectionDTO section = new SectionDTO();
						section.setId(rs.getInt("ID_SECTION"));
						section.setSection(rs.getString("SECTION"));
						classesDTO.setSection(section);
						
	                user.setClassRoom(classesDTO);
						*/
					
			/*			DesignationDTO designation = new DesignationDTO();
						designation.setId(rs.getInt("ID_DESIGNATION"));
						//designation.setDesignation(rs.getString("DESIGNATION"));
					    user.setDesignation(designation);
					    
					    
						GenderDTO gender = new GenderDTO();
						gender.setId(rs.getInt("ID_GENDER"));
						//gender.setGender(rs.getString("GENDER"));
						user.setGender(gender);
						
						YearDTO year = new YearDTO();
						year.setId(rs.getInt("ID_YEAR"));
						//year.setAcademicYear(rs.getString("ACADEMIC_YEAR"));
						user.setYear(year);
						
						CommunityDTO community = new CommunityDTO();
						community.setId(rs.getInt("ID_COMMUNITY"));
					//	community.setCommunity(rs.getString("COMMUNITY"));
						user.setCommunity(community);
						
						
						ReligionDTO religion = new ReligionDTO();
						religion.setId(rs.getInt("ID_RELIGION"));
						//religion.setReligion(rs.getString("RELIGION"));
						user.setReligion(religion);
						
						BloodGroupDTO BG = new BloodGroupDTO();
						BG.setId(rs.getInt("ID_BLOOD_GROUP"));
						//BG.setBloodGroup(rs.getString("BLOOD_GROUP"));
						user.setBloodGroup(BG);*/
						
						user.setIsDeleted(rs.getInt("IS_DELETED"));
						user.setCreatedOn(rs.getString("CREATED_ON"));
						
						users.add(user);
				    }
					return users;
				}

			  };	
		}
}
