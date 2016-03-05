/**
 * 
 */
package com.codette.apps.frontend.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.codette.apps.frontend.model.PagePermission;
import com.codette.apps.frontend.model.ResponseMessage;
import com.codette.apps.frontend.model.RoleMenu;
import com.codette.apps.frontend.model.Roles;
import com.codette.apps.frontend.service.BaseService;
import com.codette.apps.util.CommonConstants;

/**
 * @author Vignesh
 *
 */
@Component
public class BaseController {
	
	public static final Gson gson = new GsonBuilder().setDateFormat(CommonConstants.ISO_DATE_FORMAT).create();

	String locale = CommonConstants.LOCALE_EN_US; 
	
	@Resource
	BaseService baseService;
	
	/**
	 * Setting custom exception
	 * @param ex
	 * @param message
	 * @return
	 * @throws IOException
	 */
	public ResponseEntity<?> setCustomExceptionHandler(Exception ex) throws IOException {
		ResponseMessage responseMessage = new ResponseMessage();
		if(ex instanceof HttpClientErrorException){
			if(((HttpClientErrorException)ex).getStatusCode() == HttpStatus.UNAUTHORIZED){ 
				System.out.println("----0---------"+((HttpClientErrorException)ex).getResponseBodyAsString());
				responseMessage.setStatus(String.valueOf(HttpStatus.UNAUTHORIZED)); 		
				responseMessage.setMessage(((HttpClientErrorException)ex).getMessage()); 		
			}else if(((HttpClientErrorException)ex).getStatusCode() == HttpStatus.NOT_FOUND){ 				
				responseMessage.setStatus(String.valueOf(HttpServletResponse.SC_NOT_FOUND)); 	
			}else if(((HttpClientErrorException)ex).getStatusCode() == HttpStatus.UNAUTHORIZED ){ 				
				responseMessage.setStatus(String.valueOf(HttpServletResponse.SC_UNAUTHORIZED)); 
			} 	
		}	
		
		else {
			if(ex instanceof ParseException) {
				responseMessage.setStatus(String.valueOf(HttpServletResponse.SC_CONFLICT));
				responseMessage.setMessage(ex.getMessage());
			} else if(ex instanceof IOException) {
				responseMessage.setStatus(String.valueOf(HttpServletResponse.SC_CONFLICT));
				responseMessage.setMessage(ex.getMessage());
			}
		}
	 return new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	/**
	 * Set message & status in ResponseMessage model 
	 * @param message
	 * @param status
	 * @return
	 */
	public ResponseMessage setResponse(String message, String status){
		ResponseMessage responseMessage = new ResponseMessage();
		responseMessage.setMessage(message);
		responseMessage.setStatus(status);
		return responseMessage;
	}
	
	/**
	 * Get Role Based permission.
	 * @param model The ModelMap.
	 * @param session The HttpSession.
	 * @return model {@link ModelMap}
	 * @throws Exception
	 */
	public Roles getRoleBasedPermission(HttpSession session) throws Exception {
		Roles role = new Roles();
		String userRole = session.getAttribute(CommonConstants.SESSION_USERROLE).toString();
		System.out.println("userRole>>>>>>>>>>>>>>>>>>>>>"+userRole);
		String jsonString = baseService.getJsonConfig(CommonConstants.ROLE_BASED_NAVIGATION_MENU);
		role.setUserName(session.getAttribute(CommonConstants.SESSION_FIRSTNAME) + " " + session.getAttribute(CommonConstants.SESSION_LASTNAME));
		role.setUserRole(userRole);
		RoleMenu roleMenus = baseService.convertRoleMenu(jsonString);
		RoleMenu roleMenu2 = new RoleMenu();
		List<PagePermission> permissions = new ArrayList<PagePermission>();
		for(PagePermission roleMenu : roleMenus.getMenuItems()){
			if(roleMenu.getViewby().contains(userRole)){
				permissions.add(roleMenu);
			}
		}
		roleMenu2.setMenuItems(permissions);
		role.setRoleBasedPermissions(roleMenu2);
		return role;
	}
}
