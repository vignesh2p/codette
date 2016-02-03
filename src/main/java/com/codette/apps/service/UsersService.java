package com.codette.apps.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.codette.apps.dao.UserDAO;
import com.codette.apps.dto.CommunityDTO;
import com.codette.apps.dto.DesignationDTO;
import com.codette.apps.dto.GenderDTO;
import com.codette.apps.dto.ReligionDTO;
import com.codette.apps.dto.ResponseBean;
import com.codette.apps.dto.RoleDTO;
import com.codette.apps.dto.UserAuthenticationDTO;
import com.codette.apps.dto.UserDTO;
import com.codette.apps.util.CommonConstants;

@Component
public class UsersService {

	public static final Gson gson = new GsonBuilder().setDateFormat(CommonConstants.ISO_DATE_FORMAT).create();
	@Resource
	UserDAO userDAO;
	
	@Resource
	CommonService commonService ;
	/**
	 * 
	 * @param userDTO
	 * @return
	 */
	 @Transactional
	public ResponseBean updateUser(UserDTO userDTO, Integer acessId,  Integer userId) {
		userDTO = getBasicIds(userDTO);
		return userDAO.updateUser(userDTO, acessId, userId);
	}

	/**
	 * 
	 * @param userId
	 * @return
	 */
	 @Transactional
	public ResponseBean deleteUser(Integer userId,Integer phoneNumberId,Integer addressId, Integer accessId) {
		return userDAO.deleteUser(userId,phoneNumberId,addressId, accessId);
	}

	/**
	 * 
	 * @param userDTO
	 * @return
	 * @throws Exception 
	 */
	 @Transactional
	public ResponseBean insertUser(UserDTO userDTO, Integer accessId) throws Exception {
		userDTO = getBasicIds(userDTO);
		System.out.println("userDTO>>>>>>>"+gson.toJson(userDTO));
		return userDAO.insertUser(userDTO, accessId);
	}

	/**
	 * 
	 * @return
	 */
	public List<UserDTO> getUsers(String role) {
		return userDAO.getUsers(role);
	}

	public UserDTO aurthentication(UserAuthenticationDTO userAuthenticationDTO) {
		// TODO Auto-generated method stub
		return userDAO.aurthentication(userAuthenticationDTO);
	}

	public UserDTO getUser(Integer userId) {
		// TODO Auto-generated method stub
		return userDAO.getUser(userId);
	}

	
	public UserDTO getBasicIds(UserDTO userDTO){
		GenderDTO genderDTO = null;
		RoleDTO roleDTO = null;
		ReligionDTO religionDTO =null;
		CommunityDTO communityDTO = null;
		DesignationDTO designationDTO = null;
		if(userDTO.getGender() != null){
			genderDTO = userDTO.getGender() ;
			genderDTO.setId(commonService.getId(genderDTO.getGender(), CommonConstants.GENDER));
			userDTO.setGender(genderDTO);
		}
		if(userDTO.getRole() != null){
			roleDTO = userDTO.getRole() ;
			roleDTO.setId(commonService.getId(roleDTO.getRole(), CommonConstants.ROLE));
			userDTO.setRole(roleDTO);
		}
		if(userDTO.getReligion() != null){
			religionDTO = userDTO.getReligion() ;
			religionDTO.setId(commonService.getId(religionDTO.getReligion(), CommonConstants.RELIGION));
			userDTO.setReligion(religionDTO);
		}
		if(userDTO.getCommunity() != null){
			communityDTO = userDTO.getCommunity() ;
			communityDTO.setId(commonService.getId(communityDTO.getCommunity(), CommonConstants.COMMUNITY));
			userDTO.setCommunity(communityDTO);
		}
		if(userDTO.getDesignation() != null){
			designationDTO = userDTO.getDesignation() ;
			designationDTO.setId(commonService.getId(designationDTO.getDesignation(), CommonConstants.DESIGNATION));
			userDTO.setDesignation(designationDTO);
		}
		return userDTO;
	}
}
