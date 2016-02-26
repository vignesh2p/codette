package com.codette.apps.frontend.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.codette.apps.frontend.model.User;
import com.codette.apps.frontend.service.BaseService;
import com.codette.apps.frontend.service.UserService;
import com.codette.apps.util.CommonConstants;
import com.codette.apps.util.MessageUtils;


@Controller
@RequestMapping("/staff")
public class UserController extends BaseController{
	
	
	@Resource
	BaseController baseController;
	
	@Resource
	UserService userService;
	
	@Resource
	BaseService baseService;
	
	

	@RequestMapping(value = "/staffs",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getStaffsList(HttpSession session) throws IOException {
		 List<User> userList = null;
			try{
				userList = userService.getUsersList(CommonConstants.ROLE_NT_STAFF,session,locale);
		}catch(Exception ex){
			ex.printStackTrace();
			return new ResponseEntity<>(setCustomExceptionHandler(ex, MessageUtils.getMessage("error.getting.users")),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<List<User>>(userList, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/teachingStaffs",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getTeachingStaffs(HttpSession session) throws IOException {
		 List<User> userList = null;
			try{
				userList = userService.getUsersList(CommonConstants.ROLE_T_STAFF,session,locale);
		}catch(Exception ex){
			ex.printStackTrace();
			return new ResponseEntity<>(setCustomExceptionHandler(ex, MessageUtils.getMessage("error.getting.users")),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<List<User>>(userList, HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/createStaff/{role}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?>  createStaff(@PathVariable(value="role")String role, @RequestBody User user, HttpSession session){
		System.out.println("json>>>>>>>>>>>>"+gson.toJson(user));
		String userId =(String) session.getAttribute("userId");
		Object obj = null;
		try {
			if(role != null){
			obj = userService.createUser(user, "1", session, role);
			
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<Object>(obj, HttpStatus.OK);
	}
	
	@RequestMapping(value="/getProfile")
	public ResponseEntity<?> getProfileByUserId(HttpSession session){
		String userId = session.getAttribute(CommonConstants.SESSION_USER_ID).toString();
		User obj = null;
		try {
			if(userId != null){
				obj = userService.getUserById(userId, session);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Object>(obj, HttpStatus.OK);
	}
	
	@RequestMapping(value="/updateProfile" , method = RequestMethod.PUT)
	public ResponseEntity<?> updateProfile(@RequestBody User user, HttpSession session){
		String userId = session.getAttribute(CommonConstants.SESSION_USER_ID).toString();
		String role = session.getAttribute(CommonConstants.SESSION_USERROLE).toString();
		Object obj = null;
		try {
			if(userId != null && user != null){
				obj = userService.updateUserById(userId, user, role, session);
			}
		} catch (Exception  e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Object>(obj, HttpStatus.OK);
	}
	
}
