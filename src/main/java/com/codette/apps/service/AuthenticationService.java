package com.codette.apps.service;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.codette.apps.dao.AuthenticationDAO;
import com.codette.apps.dto.UserAuthenticationDTO;
import com.codette.apps.util.CommonConstants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Component
public class AuthenticationService {
	final static Logger logger = Logger.getLogger(AuthenticationService.class);
	public static final Gson gson = new GsonBuilder().setDateFormat(CommonConstants.ISO_DATE_FORMAT).create();
	@Resource
	private AuthenticationDAO authenticationDAO;
	

	public Object authentication(UserAuthenticationDTO userAuthenticationDTO) {
		return authenticationDAO.authentication(userAuthenticationDTO);
	}

	@Transactional
	public Object resetPassword(UserAuthenticationDTO userAuthenticationDTO, Integer accessId) {
		// TODO Auto-generated method stub
		return authenticationDAO.resetPassword(userAuthenticationDTO, accessId);
	}
    
	@Transactional
	public Object changePassword(UserAuthenticationDTO userAuthenticationDTO, String newPassword, Integer accessId) {
		// TODO Auto-generated method stub
		return authenticationDAO.changePassword(userAuthenticationDTO,newPassword,accessId);
	}
	
	@Transactional
	public Object createPassword(UserAuthenticationDTO userAuthenticationDTO, Integer accessId) {
		// TODO Auto-generated method stub
		return authenticationDAO.createPassword(userAuthenticationDTO,accessId);
	}


}
