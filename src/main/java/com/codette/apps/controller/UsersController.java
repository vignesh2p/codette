package com.codette.apps.controller;



import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.codette.apps.dto.UserDTO;
import com.codette.apps.service.CommonService;
import com.codette.apps.service.UsersService;
import com.codette.apps.util.CommonConstants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Controller
@RequestMapping("/users")
public class UsersController extends CommonBaseController {
	
	@Resource
	private UsersService usersService;
	
	final static Logger logger = Logger.getLogger(UsersController.class);
	public static final Gson gson = new GsonBuilder().setDateFormat(CommonConstants.ISO_DATE_FORMAT).create();
	
	@RequestMapping(value = "/ping", method = RequestMethod.GET )
	@ResponseBody
	public Object test(){
		
		return getRole();
		
	}
	

	@RequestMapping(value = "/{role}/list", method = RequestMethod.GET ) //working good
	@ResponseBody
	public Object getUsers(@PathVariable(value="role") String role,
			@RequestParam( value="classId",required = false) Integer classId,
			@RequestParam( value="orgId",required = false) Integer orgId ,
			@RequestParam( value="search",required = false) String search,
			@RequestParam( value="includeDetails",required = false) boolean includeDetails,
			HttpServletRequest request, HttpSession session)  {
			if(orgId!= null && orgId != 0){
				return usersService.getUsers(orgId, role, classId,includeDetails,search);
			}
			return usersService.getUsers(getOrganizationId(), role, classId,includeDetails,search);
	}
	
	
	


	@RequestMapping(value = "/{userId}", method = RequestMethod.GET) //working good
	@ResponseBody
	public Object getUser(@RequestParam( value="orgId", required=false) Integer orgId,
			HttpServletRequest request,
			@PathVariable(value="userId") Integer userId) throws Exception {
		return usersService.getUser(getOrganizationId(),userId);
	}
	
	
	
	@RequestMapping(value = "/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object createUser(@RequestBody UserDTO userDTO,
			@RequestParam( value="orgId",required = false) Integer orgId ,
			HttpSession session, HttpServletRequest request) throws Exception { //working good
		Object object = null;
		if(orgId != null && orgId != 0){
		    object = usersService.createUser(userDTO,orgId, getAccessId());
		}else{
			object  = usersService.createUser(userDTO,getOrganizationId(), getAccessId());
		}
		return object;
	}


	
	@RequestMapping(value = "/update/{userId}", method = RequestMethod.PUT)
	@ResponseBody
	public Object updateUser(
			@PathVariable Integer userId, @RequestBody UserDTO userDTO, HttpSession session, HttpServletRequest request) throws Exception {
		Object  object = usersService.updateUser(userDTO, getOrganizationId(), userId,getAccessId());
		return object;
	}

	
	
	@RequestMapping(value = "/delete/{userId}", method = RequestMethod.DELETE)
	@ResponseBody
	public Object deleteUser(@PathVariable Integer userId,HttpSession session,HttpServletRequest request) throws Exception {
		Object object = usersService.deleteUser(getOrganizationId() ,userId, getAccessId());
		return object;
	}
	


   
 
}
