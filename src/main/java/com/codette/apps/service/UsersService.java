package com.codette.apps.service;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.codette.apps.dao.UserDAO;
import com.codette.apps.dto.UserDTO;
import com.codette.apps.util.CommonConstants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Component
public class UsersService {

	final static Logger logger = Logger.getLogger(UsersService.class);
	public static final Gson gson = new GsonBuilder().setDateFormat(CommonConstants.ISO_DATE_FORMAT).create();
	@Resource
	private UserDAO userDAO;
	
	@Resource
	private CommonService commonService ;

	 @Transactional
	public Object createUser(UserDTO userDTO, Integer orgId,
				Integer accessId) throws DataIntegrityViolationException {
			userDTO = commonService.getBasicIds(userDTO);
			return userDAO.createUser(userDTO,orgId, accessId);
	}
	 
	 
	 @Transactional
	public Object updateUser(UserDTO userDTO, Integer orgId, Integer accessId,  Integer userId) {
		userDTO = commonService.getBasicIds(userDTO);
		return userDAO.updateUser(userDTO,orgId, accessId, userId);
	}

	 @Transactional
	public Object deleteUser(Integer orgId, Integer userId,
				Integer accessId) {
			// TODO Auto-generated method stub
			return userDAO.deleteUser(orgId,userId, accessId);
	}
	 
	 public Object getUser(Integer orgId, Integer userId) {
			// TODO Auto-generated method stub
			return userDAO.getUser(orgId,userId);
		}

	public Object getUsers(Integer orgId, String role, Integer classId, boolean includeDetails,String search) {
		return userDAO.getUsers(orgId, role, classId, includeDetails, search);
	}


}
