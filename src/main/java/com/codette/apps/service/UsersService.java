package com.codette.apps.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.codette.apps.dao.UserDAO;
import com.codette.apps.dao.impl.StaffDAOImpl;
import com.codette.apps.dto.AttendenceDTO;
import com.codette.apps.dto.ClassesDTO;
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

	final static Logger logger = Logger.getLogger(StaffDAOImpl.class);
	public static final Gson gson = new GsonBuilder().setDateFormat(CommonConstants.ISO_DATE_FORMAT).create();
	@Resource
	UserDAO userDAO;
	
	@Resource
	CommonService commonService ;

	 @Transactional
	public ResponseBean createUser(UserDTO userDTO, String orgId,
				Integer accessId) {
			userDTO = commonService.getBasicIds(userDTO);
			return userDAO.createUser(userDTO,orgId, accessId);
	}
	 
	 
	 @Transactional
	public ResponseBean updateUser(UserDTO userDTO, String orgId, Integer acessId,  Integer userId) {
		userDTO = commonService.getBasicIds(userDTO);
		return userDAO.updateUser(userDTO, acessId, userId);
	}

	 @Transactional
	public ResponseBean deleteUser(Integer orgId, Integer userId,
				Integer accessId) {
			// TODO Auto-generated method stub
			return userDAO.deleteUser(orgId,userId, accessId);
	}
	 
	 public UserDTO getUser(Integer userId) {
			// TODO Auto-generated method stub
			return userDAO.getUser(userId);
		}

	public List<UserDTO> getUsers(String role, Integer stdId, Integer secId) {
		return userDAO.getUsers(role,stdId,secId);
	}

}
