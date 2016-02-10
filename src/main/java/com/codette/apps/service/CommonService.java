package com.codette.apps.service;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.codette.apps.dao.CommonDAO;
import com.codette.apps.dao.impl.StaffDAOImpl;
import com.codette.apps.dto.AddressDTO;
import com.codette.apps.dto.CommunityDTO;
import com.codette.apps.dto.DesignationDTO;
import com.codette.apps.dto.GenderDTO;
import com.codette.apps.dto.PhoneNumberDTO;
import com.codette.apps.dto.ReligionDTO;
import com.codette.apps.dto.RoleDTO;
import com.codette.apps.dto.SectionDTO;
import com.codette.apps.dto.StandardDTO;
import com.codette.apps.dto.StudentRelationDTO;
import com.codette.apps.dto.UserDTO;
import com.codette.apps.util.CommonConstants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Component
public class CommonService {
	
	final static Logger logger = Logger.getLogger(StaffDAOImpl.class);
	public static final Gson gson = new GsonBuilder().setDateFormat(CommonConstants.ISO_DATE_FORMAT).create();
	@Resource
	CommonDAO commonDAO;
	
	public List<CommunityDTO> getCommunity() {
		// TODO Auto-generated method stub
		return commonDAO.getCommunity();
	}
	
	public List<ReligionDTO> getReligion() {
		// TODO Auto-generated method stub
		return commonDAO.getReligion();
	}
	
	public List<DesignationDTO> getDesignation(String orgId) {
		// TODO Auto-generated method stub
		return commonDAO.getDesignation(Integer.valueOf(orgId));
	}

	public Integer getId(String entity, String type){
		return commonDAO.getId(entity, type);
	}

	public List<StandardDTO> getStandard(String orgId) {
		// TODO Auto-generated method stub
		return commonDAO.getStandard(Integer.valueOf(orgId));
	}

	public List<SectionDTO> getSection(String orgId) {
		// TODO Auto-generated method stub
		return commonDAO.getSection(Integer.valueOf(orgId));
	}

	public Integer getAccessId(HttpServletRequest request) {
		
		return Integer.valueOf(request.getHeader(CommonConstants.SESSION_USER_ID));
	}
	

	public UserDTO getBasicIds(UserDTO userDTO){
		GenderDTO genderDTO = null;
		RoleDTO roleDTO = null;
		ReligionDTO religionDTO =null;
		CommunityDTO communityDTO = null;
		DesignationDTO designationDTO = null;
		StandardDTO standard = null;
		SectionDTO section = null; 
		StudentRelationDTO relation = null;
			
		if(userDTO.getStandard() != null){
				standard = userDTO.getStandard() ;
				standard.setId(getId(standard.getStandard(), CommonConstants.STANDARD));
				userDTO.setStandard(standard);
			}
		if(userDTO.getSection() != null){
				section = userDTO.getSection() ;
				section.setId(getId(section.getSection(), CommonConstants.SECTION));
				userDTO.setSection(section);
			}
		if(userDTO.getGender() != null){
			genderDTO = userDTO.getGender() ;
			genderDTO.setId(getId(genderDTO.getGender(), CommonConstants.GENDER));
			userDTO.setGender(genderDTO);
		}
		if(userDTO.getRole() != null){
			roleDTO = userDTO.getRole() ;
			roleDTO.setId(getId(roleDTO.getRole(), CommonConstants.ROLE));
			userDTO.setRole(roleDTO);
		}
		if(userDTO.getReligion() != null){
			religionDTO = userDTO.getReligion() ;
			religionDTO.setId(getId(religionDTO.getReligion(), CommonConstants.RELIGION));
			userDTO.setReligion(religionDTO);
		}
		if(userDTO.getCommunity() != null){
			communityDTO = userDTO.getCommunity() ;
			communityDTO.setId(getId(communityDTO.getCommunity(), CommonConstants.COMMUNITY));
			userDTO.setCommunity(communityDTO);
		}
		if(userDTO.getDesignation() != null){
			designationDTO = userDTO.getDesignation() ;
			designationDTO.setId(getId(designationDTO.getDesignation(), CommonConstants.DESIGNATION));
			userDTO.setDesignation(designationDTO);
		}
		if(userDTO.getPhoneNumbers()!= null){
		for(PhoneNumberDTO phone: userDTO.getPhoneNumbers()){
		 relation = phone.getStudentRelation();
			relation.setId(getId(relation.getRelation(),CommonConstants.RELATION));
			phone.setStudentRelation(relation);
		}
		}
		if(userDTO.getAddresses() != null){
			for(AddressDTO address: userDTO.getAddresses()){
			 relation = address.getStudentRelation();
				relation.setId(getId(relation.getRelation(),CommonConstants.RELATION));
				address.setStudentRelation(relation);
			}

	}
		return userDTO;
	
		
	}
}
