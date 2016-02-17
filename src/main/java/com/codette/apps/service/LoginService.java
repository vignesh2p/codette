package com.codette.apps.service;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.codette.apps.dao.LoginDAO;
import com.codette.apps.dao.impl.StaffDAOImpl;
import com.codette.apps.dto.UserAuthenticationDTO;
import com.codette.apps.dto.UserDTO;
import com.codette.apps.util.CommonConstants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Component
public class LoginService {
	final static Logger logger = Logger.getLogger(StaffDAOImpl.class);
	public static final Gson gson = new GsonBuilder().setDateFormat(CommonConstants.ISO_DATE_FORMAT).create();
	@Resource
	LoginDAO loginDAO;
	
	@Resource
	CommonService commonService ;

	public UserDTO authentication(UserAuthenticationDTO userAuthenticationDTO) {
		// TODO Auto-generated method stub
		return loginDAO.authentication(userAuthenticationDTO);
	}

	@Transactional
	public UserDTO resetPassword(UserAuthenticationDTO userAuthenticationDTO) {
		// TODO Auto-generated method stub
		return loginDAO.resetPassword(userAuthenticationDTO);
	}
    
	@Transactional
	public UserDTO changePassword(UserAuthenticationDTO userAuthenticationDTO, String newPassword) {
		// TODO Auto-generated method stub
		return loginDAO.changePassword(userAuthenticationDTO);
	}
	
	@Transactional
	public UserDTO createPassword(UserAuthenticationDTO userAuthenticationDTO) {
		// TODO Auto-generated method stub
		return loginDAO.createPassword(userAuthenticationDTO);
	}

}
