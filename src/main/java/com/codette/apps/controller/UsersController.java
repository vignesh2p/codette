package com.codette.apps.controller;



import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.codette.apps.service.CommonService;
import com.codette.apps.service.UsersService;
import com.codette.apps.dto.UserDTO;
import com.codette.apps.util.CommonConstants;

@Controller
@RequestMapping("/users")
public class UsersController {
	
	@Resource
	private UsersService usersService;
	
	@Resource
	private CommonService commonService;
	
	final static Logger logger = Logger.getLogger(UsersController.class);
	public static final Gson gson = new GsonBuilder().setDateFormat(CommonConstants.ISO_DATE_FORMAT).create();
	
	@RequestMapping(value = "/ping", method = RequestMethod.GET )
	@ResponseBody
	public Object test(){
		return "success";
		
	}
	

	@RequestMapping(value = "/{orgId}/users/{role}", method = RequestMethod.GET )
	@ResponseBody
	public Object getUsers(
			@PathVariable( value="orgId") String orgId,
			@PathVariable( value="role") String role,
			@RequestParam( value="standardId",required = false) String standardId,
			@RequestParam( value="sectionId",required = false) String sectionId ,
			HttpServletRequest request, HttpSession session)  {
		Integer stdId = Integer.valueOf(standardId);
		Integer secId = Integer.valueOf(sectionId);
			return usersService.getUsers(Integer.valueOf(orgId),role,stdId,secId);
	}
	
	
	
	@RequestMapping(value = "/{orgId}/getUser/{userId}", method = RequestMethod.GET)
	@ResponseBody
	public Object getUser(@PathVariable( value="orgId") Integer orgId,@PathVariable Integer userId) throws Exception {
		return usersService.getUser(Integer.valueOf(orgId),userId);
	}
	
	
	
	@RequestMapping(value = "/{orgId}/createUser", method = RequestMethod.POST)
	@ResponseBody
	public Object createUser(@PathVariable( value="orgId") Integer orgId,@RequestBody UserDTO userDTO,  HttpSession session, HttpServletRequest request) throws Exception {
		Object object = usersService.createUser(userDTO,orgId,commonService.getAccessId(request));
		return object;
	}


	
	@RequestMapping(value = "{orgId}/updateuser/{userId}", method = RequestMethod.PUT)
	@ResponseBody
	public Object updateUser(@PathVariable( value="orgId") Integer orgId,@PathVariable Integer userId, @RequestBody UserDTO userDTO, HttpSession session, HttpServletRequest request) throws Exception {
		Object  object = usersService.updateUser(userDTO, orgId, userId,commonService.getAccessId(request));
		return object;
	}

	
	
	@RequestMapping(value = "{orgId}/deleteuser/{userId}/{accessId}", method = RequestMethod.DELETE)
	@ResponseBody
	public Object deleteUser(@PathVariable Integer userId,Integer orgId,HttpSession session,HttpServletRequest request) throws Exception {
		Object object = usersService.deleteUser(orgId ,userId, commonService.getAccessId(request));
		return object;
	}
	


   
 
}
