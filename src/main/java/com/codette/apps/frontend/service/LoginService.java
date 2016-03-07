package com.codette.apps.frontend.service;

import java.io.IOException;
import java.text.ParseException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.codette.apps.dto.UserAuthenticationDTO;
import com.codette.apps.dto.UserDTO;
import com.codette.apps.frontend.model.User;
import com.codette.apps.frontend.translator.LoginTranslator;
import com.codette.apps.frontend.translator.UserTranslator;
import com.codette.apps.util.CommonConstants;

@Component
public class LoginService extends BaseService{
	
	public static final Gson gson = new GsonBuilder().setDateFormat(CommonConstants.ISO_DATE_FORMAT).create();
	
	
	@Resource
	LoginTranslator loginTranslator;
	
	@Resource
	UserTranslator userTranslator;
	
	/***
	 * Authenticates the User from API.
	 * @param userName The String value of UserName (cannot be null).
	 * @param secret The String value of secret (cannot be null).
	 * @param session The HttpSession.
	 * @return user A not null {@link User}
	 * @throws ParseException 
	 * @throws Exception 
	 */
	public User userAuthentication(String userName, String secret, HttpSession session, String locale, HttpServletRequest request) throws IOException, ParseException {	
		User user= new User();
		UserDTO userDTO = new UserDTO();
		UserAuthenticationDTO userAuthenticationDTO = loginTranslator.translateToUserAuthenticationDTO(userName, secret);
		String postString = gson.toJson(userAuthenticationDTO);
		ResponseEntity<Object> response = null;
		try {
			HttpEntity<String> entity = preparePost(postString, session);
			 response = restTemplate.exchange(getAPIBaseURL()+"/authentication/login", 
							HttpMethod.POST, entity, Object.class);

			System.out.println("response.getBody()-----"+gson.toJson(response.getBody()));
			userDTO = loginTranslator.convertToUserDTO(response.getBody());
			user = userTranslator.translateToUser(userDTO, locale);
			
		}catch (IOException e) {
			throw e;
		}catch (JsonSyntaxException e) {
			throw e;
		} catch (HttpClientErrorException e) {
			throw e;
		}    
		return user;
	}
	
	/**
	 * Logout the authenticated users.
	 * @param session the HttpSession.  
	 * @throws Exception
	 */
	public void logout(HttpSession session) throws IOException{
		try{
			HttpEntity<String> entity = prepareDelete(session);
			// This will authenticate the user
			restTemplate.exchange(getAPIBaseURL()
				+ CommonConstants.ORGANIZATIONS_BASE_URL 
				+ CommonConstants.USERS_BASE_URL 
				+ CommonConstants.SESSION_BASE_URL,
				HttpMethod.DELETE, entity, Object.class);
			
		}catch (IOException e) {
			throw e;
		}catch (JsonSyntaxException e) {
			throw e;
		} catch (HttpClientErrorException e) {
			throw e;
		} 
	}
}
