package com.codette.apps.controller;

import java.util.List;

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

import com.codette.apps.service.AttendanceService;
import com.codette.apps.service.CommonService;
import com.codette.apps.util.CommonConstants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


@Controller
@RequestMapping("/attendance")
public class AttendanceController extends CommonBaseController {
	
	final static Logger logger = Logger.getLogger(AttendanceController.class);
	public static final Gson gson = new GsonBuilder().setDateFormat(CommonConstants.ISO_DATE_FORMAT).create();
	
	@Resource
	private AttendanceService attendanceService;
	
	@Resource
	private CommonService commonService;
	
	@RequestMapping(value = "/enableattendance", method = RequestMethod.PUT)
	@ResponseBody
	public Object enableAttendence(
			@RequestParam( value="orgId" , required = false) Integer orgId,
			@RequestParam( value="userId" , required = false) Integer userId,
			HttpServletRequest request) throws Exception  {
		Object object = null;
		try{
			if(getRole().equalsIgnoreCase(CommonConstants.ROLE_T_STAFF)){
			if(orgId != null && orgId != 0 && userId != null && orgId != 0){
			object = attendanceService.enableAttendance(orgId,userId,getAccessId());
			}else{
				object = attendanceService.enableAttendance(getOrganizationId(),getAccessId(),getAccessId());	
			}
			}
		} 
		catch(Exception e){
			return setCustomExceptionHandler(e);
		}
		return object;

	}   
    
   @RequestMapping(value = "/attendencesheet", method = RequestMethod.GET)
	@ResponseBody
	public Object getAttendence(@RequestParam( value="orgId" , required = false) Integer orgId,
			@RequestParam( value="userId" , required = false) Integer userId) throws Exception  {
	   Object object =null;
	   try{
		   if(orgId != null && orgId != 0 && userId != null && orgId != 0){
			   object =  attendanceService.getAttendance(orgId,userId);
				}else{
					object =  attendanceService.getAttendance(getOrganizationId(),getAccessId());
				}
		
	   	}catch(Exception e){
	   		setCustomExceptionHandler(e);
	   	}
	return object;
   }
   
	
	@RequestMapping(value = "/updateattendance/{userIds}", method = RequestMethod.PUT)
	@ResponseBody
	public Object updateAttendence(
			@RequestParam( value="orgId" , required = false) Integer orgId,
			@PathVariable( value="userIds" ) List<Integer> userIds,
			HttpSession session,
			HttpServletRequest request) throws Exception {
		Object object = null;
		try{
			object = attendanceService.updateAttendance(getOrganizationId(), userIds, getAccessId());
		}catch(Exception e){
            setCustomExceptionHandler(e); 
		}
		return object;
	}

}
