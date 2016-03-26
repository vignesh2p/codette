package com.codette.apps.controller;


import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.codette.apps.dto.StaffClassDTO;
import com.codette.apps.service.ClassRoomService;
import com.codette.apps.service.CommonService;
import com.codette.apps.util.CommonConstants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


@Controller
@RequestMapping("/class")
public class ClassRoomController extends CommonBaseController{
	
	final static Logger logger = Logger.getLogger(ClassRoomController.class);
	public static final Gson gson = new GsonBuilder().setDateFormat(CommonConstants.ISO_DATE_FORMAT).create();
	
	@Resource
	private ClassRoomService classRoomService;
	
	@Resource
	private CommonService commonService ;
	
	  @RequestMapping(value =  "/staffclasses", method = RequestMethod.GET)
		 @ResponseBody
		 public Object getClassList(
				 @RequestParam(value ="orgId" , required = false) Integer orgId,
				 @RequestParam(value ="userId" , required = false) Integer userId,
				 HttpServletRequest request, HttpSession session) throws Exception{
		  Object object = null;
		  try{
			 	object = gson.toJson(classRoomService.getClassList(getOrganizationId(),getAccessId(),getRole()));
		  }
		  catch(Exception e){
			    setCustomExceptionHandler(e);
		  }
		  return object;
		 }	   

	     @RequestMapping(value =  "/list", method = RequestMethod.GET)
		 @ResponseBody
		 public Object getAllClassList(@RequestParam(value ="orgId" , required = false) Integer orgId,
				 @RequestParam(value ="staffId" , required = false) Integer staffId,
				 HttpServletRequest request, HttpSession session) throws Exception{
		  Object object = null;
		  try {
			  if(staffId != null) {
				  object = classRoomService.getClassList(getOrganizationId(), staffId, getRole());
			  } else {
				  object = classRoomService.getAllClassList(getOrganizationId());
			  }
		  } catch(Exception e) {
			 setCustomExceptionHandler(e);
		  }
		  return object;
		 }	   
	  
	  
	  
	  @RequestMapping(value =  "/createstaffclasses", method = RequestMethod.POST)
		 @ResponseBody
		 public Object createHandlingClassforStaff(@RequestBody List<StaffClassDTO> staffClasses,
				 @RequestParam(value ="orgId", required = false) Integer orgId,
				 @RequestParam(value ="userId",required = false) Integer userId,
				 HttpSession session) throws Exception{
				  Object object = null;
				  String role = getRole();
				  try{
				  if(role.equalsIgnoreCase( CommonConstants.TEACHING_STAFF) ){
					     object =  classRoomService.createHandlingClassforStaff(staffClasses,getOrganizationId(),getAccessId(),role,getAccessId());
					  }
				  if(orgId != null && userId != null &&
				     (role.equalsIgnoreCase(CommonConstants.PRINCIPAL) || role.equalsIgnoreCase(CommonConstants.ADMIN))){
					     object = classRoomService.createHandlingClassforStaff(staffClasses, getOrganizationId(), userId, role, getAccessId());	  
					  }
				  }catch(Exception e){
					  setCustomExceptionHandler(e);
				  }
					object = gson.toJson(object);
			return object;
	  }
	  
  }
