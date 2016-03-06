package com.codette.apps.frontend.service;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;

import com.google.gson.JsonSyntaxException;
import com.codette.apps.dto.UserDTO;
import com.codette.apps.frontend.model.User;
import com.codette.apps.frontend.translator.LoginTranslator;
import com.codette.apps.frontend.translator.UserTranslator;
import com.codette.apps.util.CommonConstants;

/**
 * User : Vignesh2p
 * Date : 08/21/2015
 */
@Component
public class UserService  extends BaseService {

	
	@Resource
	LoginTranslator loginTranslator;
	
	@Resource
	UserTranslator userTranslator;
	
	/**
	 * Getting Clients List
	 * @param queryString
	 * @param organizationId
	 * @param session
	 * @param locale
	 * @return
	 * @throws IOException
	 */
	public List<User> getUsersList(String role, HttpSession session, String locale) throws IOException {
		List<UserDTO> userDTOList = null;
		List<User> userList = null;
		try {
			HttpEntity<String> requestEntity = prepareGet(session); 
			ResponseEntity<Object> response =
							restTemplate.exchange(getAPIBaseURL()
							+ CommonConstants.USERS_BASE_URL +"/"+role  +"/list",
							HttpMethod.GET, requestEntity, Object.class);

			userDTOList = userTranslator.translateToUserDTOList(response.getBody());
			userList = userTranslator.translateToUserList(userDTOList, locale);
		}catch (IOException e) {
			e.printStackTrace();
			throw e;
		}catch (JsonSyntaxException e) {
			e.printStackTrace();
			throw e;
		}catch (HttpClientErrorException e) {
			e.printStackTrace();
			throw e;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			e.printStackTrace();
		}
		return userList;
	}

	/**
	 * 
	 * 
	 * @param user
	 * @param userID
	 * @param session
	 * @return
	 * @throws ParseException 
	 * @throws IOException
	 */
	public Object createUser(User user, String userID, HttpSession session , String role) throws ParseException, IOException {
		UserDTO userDTO = userTranslator.translateToUserDTO(user, role);
		String postString = gson.toJson(userDTO);
		System.out.println("postString>>>>"+postString);
		try {
			HttpEntity<String> entity = preparePost(postString, session);
			ResponseEntity<Object> response = restTemplate.exchange(getAPIBaseURL() 
							+ CommonConstants.SLASH + CommonConstants.USERS_BASE_URL + CommonConstants.SLASH 
							+ userID + CommonConstants.CREATE_USERS_BASEURL, HttpMethod.POST, entity ,Object.class);
			
			return response.getStatusCode();
		} catch (JsonSyntaxException e) {
			throw e;
		} catch (HttpClientErrorException e) {
			throw e;
		}
	}
	
	/**
	 * 
	 * @param userId
	 * @param locale
	 * @param session
	 * @return
	 * @throws IOException
	 */
	public User getUserById(String userId, HttpSession session) throws IOException{
		UserDTO userDTO = null; User user = null;
		try {
			HttpEntity<String> requestEntity = prepareGet(session); 
			ResponseEntity<Object> response =
							restTemplate.exchange(getAPIBaseURL()
							+ CommonConstants.USERS_BASE_URL + CommonConstants.GET_USER 
							+ CommonConstants.SLASH + userId, HttpMethod.GET, requestEntity, Object.class);
		System.out.println("response.getBody()>>>>>>>"+gson.toJson(response.getBody()));
			userDTO = userTranslator.translateToUserDTO(response.getBody());
			user = userTranslator.translateToUser(userDTO, null);
		}catch (IOException e) {
			throw e;
		}catch (JsonSyntaxException e) {
			throw e;
		}catch (HttpClientErrorException e) {
			throw e;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}

	public Object updateUserById(String userId, User user, String role, HttpSession session) throws ParseException, IOException {
		UserDTO userDTO = userTranslator.translateToUserDTO(user, role);
		String postString = gson.toJson(userDTO);
		System.out.println("postString>>>>"+postString);
		try {
			HttpEntity<String> entity = preparePut(postString, session);
			ResponseEntity<Object> response = restTemplate.exchange(getAPIBaseURL() 
							+ CommonConstants.SLASH + CommonConstants.USERS_BASE_URL + CommonConstants.UPDATE_USER 
							+ CommonConstants.SLASH + userId, HttpMethod.PUT, entity ,Object.class);
			
			return response.getStatusCode();
		} catch (JsonSyntaxException e) {
			throw e;
		} catch (HttpClientErrorException e) {
			throw e;
		}
	}
	

}
