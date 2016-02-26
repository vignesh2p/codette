package com.codette.apps.controller;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.codette.apps.service.ClassRoomService;
import com.codette.apps.service.CommonService;
import com.codette.apps.util.CommonConstants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


@Controller
@RequestMapping("/classroom")
public class ClassRoomController {
	
	final static Logger logger = Logger.getLogger(ClassRoomController.class);
	public static final Gson gson = new GsonBuilder().setDateFormat(CommonConstants.ISO_DATE_FORMAT).create();
	
	@Resource
	private ClassRoomService classRoomService;
	
	@Resource
	private CommonService commonService ;
	
	  @RequestMapping(value =  "/staffclasses/{userId}/{role}", method = RequestMethod.GET)
		 @ResponseBody
		 public Object getClassList(
				 @RequestParam(value ="orgId") Integer orgId,
				 @RequestParam(value ="userId") Integer userId,
				 @PathVariable(value ="role") String role,
				 HttpServletRequest request, HttpSession session){
		  Object object = null;
		  try{
			  if(role == CommonConstants.TEACHING_STAFF){
			     object =  classRoomService.getClassList(orgId,userId,role);
			  }
			  if(role.equalsIgnoreCase(CommonConstants.PRINCIPAL) || role.equalsIgnoreCase(CommonConstants.ADMIN)){
			     object = classRoomService.getClassList(orgId,null,null);	  
			  }
			object = gson.toJson(object);
		  }
		  catch(Exception e){
			 
		  }
		  return object;
		 }	   

	  
	  @RequestMapping(value =  "/createstaffclasses/{userId}/{role}", method = RequestMethod.POST)
		 @ResponseBody
		 public Object createHandlingClassforStaff(){
		  Object object = null;
			return object;
	  }
	  
  }
