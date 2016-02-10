package com.codette.apps.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
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
import com.codette.apps.dao.impl.StaffDAOImpl;
import com.codette.apps.dto.AttendenceDTO;
import com.codette.apps.dto.ClassesDTO;
import com.codette.apps.dto.ResponseBean;
import com.codette.apps.dto.RoleDTO;
import com.codette.apps.dto.StaffClassDTO;
import com.codette.apps.dto.UserAuthenticationDTO;
import com.codette.apps.dto.UserDTO;
import com.codette.apps.util.CommonConstants;

@Controller
@RequestMapping("/users")
public class UsersController {
	
	@Resource
	UsersService usersService;
	
	@Resource
	CommonService commonService;
	
	final static Logger logger = Logger.getLogger(StaffDAOImpl.class);
	public static final Gson gson = new GsonBuilder().setDateFormat(CommonConstants.ISO_DATE_FORMAT).create();
	

	@RequestMapping(value = "/{orgId}/users/{role}/{standardId}/{sectionId}", method = RequestMethod.GET )
	@ResponseBody
	public List<UserDTO> getUsers(
			@RequestParam( value="role") String role,
			@RequestParam( value="standardId") String standardId,
			@RequestParam( value="sectionId") String sectionId ,
			HttpServletRequest request, HttpSession session)  {
		Integer stdId = Integer.valueOf(standardId);
		Integer secId = Integer.valueOf(sectionId);
			return usersService.getUsers(role,stdId,secId);
	}
	
	@RequestMapping(value = "/{orgId}/getUser/{userId}", method = RequestMethod.GET)
	@ResponseBody
	public UserDTO getUser(@PathVariable( value="orgId") String orgId,@PathVariable Integer userId) throws Exception {
		return usersService.getUser(userId);
	}
	
	@RequestMapping(value = "/{orgId}/createUser", method = RequestMethod.POST)
	@ResponseBody
	public ResponseBean createUser(@PathVariable( value="orgId") String orgId,@RequestBody UserDTO userDTO,  HttpSession session, HttpServletRequest request) throws Exception {
		ResponseBean responseBean = new ResponseBean();
		responseBean = usersService.createUser(userDTO,orgId,commonService.getAccessId(request));
		return responseBean;
	}


	@RequestMapping(value = "{orgId}/updateuser/{userId}", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseBean updateUser(@PathVariable( value="orgId") String orgId,@PathVariable Integer userId, @RequestBody UserDTO userDTO, HttpSession session, HttpServletRequest request) throws Exception {
		ResponseBean responseBean = new ResponseBean();
		responseBean = usersService.updateUser(userDTO, orgId, userId,commonService.getAccessId(request));
		return responseBean;
	}

	@RequestMapping(value = "{orgId}/deleteuser/{userId}/{accessId}", method = RequestMethod.DELETE)
	@ResponseBody
	public ResponseBean deleteUser(@PathVariable Integer userId,Integer orgId,HttpSession session,HttpServletRequest request) throws Exception {
		ResponseBean responseBean = new ResponseBean();
		responseBean = usersService.deleteUser(orgId ,userId, commonService.getAccessId(request));
		return responseBean;
	}
	


   
 
}
