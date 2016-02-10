package com.codette.apps.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.codette.apps.dao.impl.StaffDAOImpl;
import com.codette.apps.dto.ClassesDTO;
import com.codette.apps.dto.StaffClassDTO;
import com.codette.apps.service.ClassRoomService;
import com.codette.apps.service.CommonService;
import com.codette.apps.util.CommonConstants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


@Controller
@RequestMapping("/classroom")
public class ClassRoomController {
	
	final static Logger logger = Logger.getLogger(StaffDAOImpl.class);
	public static final Gson gson = new GsonBuilder().setDateFormat(CommonConstants.ISO_DATE_FORMAT).create();
	@Resource
	ClassRoomService classRoomService;
	
	@Resource
	CommonService commonService ;
	
	  @RequestMapping(value =  "/{orgId}/staffclasses/{userId}/{role}", method = RequestMethod.GET)
		 @ResponseBody
		 public List<StaffClassDTO> getStudentsList(
				 @PathVariable(value ="orgId") String orgId,
				 @PathVariable(value ="userId") String userId,
				 @PathVariable(value ="role") String role,
				 HttpServletRequest request, HttpSession session){
		  List<StaffClassDTO> staffClassList = new ArrayList<StaffClassDTO>();
			staffClassList =  classRoomService.getClassList(Integer.valueOf(orgId),Integer.valueOf(userId),role);
			  return staffClassList;
		 }
	   
	   @RequestMapping(value = "/{orgId}/classes", method = RequestMethod.GET)
		 @ResponseBody
		 public List<ClassesDTO> getAllClassList( @PathVariable(value ="orgId") String orgId,
				 HttpServletRequest request, HttpSession session){
			  return classRoomService.getAllClassList(Integer.valueOf(orgId));
		 }
	    
	

}
