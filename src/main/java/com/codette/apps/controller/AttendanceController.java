package com.codette.apps.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.net.ssl.SSLEngineResult.Status;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.codette.apps.dto.ResponseBean;
import com.codette.apps.service.AttendanceService;
import com.codette.apps.service.CommonService;
import com.codette.apps.util.CommonConstants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


@Controller
@RequestMapping("/attendance")
public class AttendanceController {
	
	final static Logger logger = Logger.getLogger(AttendanceController.class);
	public static final Gson gson = new GsonBuilder().setDateFormat(CommonConstants.ISO_DATE_FORMAT).create();
	
	@Resource
	private AttendanceService attendanceService;
	
	@Resource
	private CommonService commonService;
	
	@RequestMapping(value = "/{orgId}/enableattendence/{userId}", method = RequestMethod.PUT)
	@ResponseBody
	public Object enableAttendence(
			@PathVariable( value="orgId") String orgId,
			@PathVariable( value="userId") String userId,
			HttpServletRequest request)  {
		Object object = null;
		try{
			object = attendanceService.enableAttendance(Integer.valueOf(orgId),Integer.valueOf(userId),commonService.getAccessId(request));
			if(object instanceof ResponseBean){
				ResponseBean responseBean = (ResponseBean) object;
				if(responseBean.getStatus().equalsIgnoreCase("SUCCESS")){
					return HttpStatus.SC_OK;
				}
			}
		} 
		catch(Exception e){
			
		}
		return object;

	}   
    
   @RequestMapping(value = "/{orgId}/getattendencesheet/{userId}", method = RequestMethod.GET)
	@ResponseBody
	public Object getAttendence(
			@PathVariable( value="orgId") String orgId,
			@PathVariable( value="userId") String userId,
			HttpServletRequest requests)  {
	   Object object =null;
	   try{
		   object =  attendanceService.getAttendance(Integer.valueOf(orgId),Integer.valueOf(userId));
		   if(object instanceof List){
		   object = gson.toJson(object);
		   }
		   if(object instanceof ResponseBean){
			   
		   }
	   	}catch(Exception e){
	   		
	   	}
	return object;
   }
   
	
	@RequestMapping(value = "/{orgId}/updateattendance/{userIds}", method = RequestMethod.PUT)
	@ResponseBody
	public Object updateAttendence(
			@PathVariable( value="orgId") String orgId,
			@PathVariable List<Integer> userIds, 
			HttpSession session,
			HttpServletRequest request) throws Exception {
		Object object = null;
		try{
			object = attendanceService.updateAttendance(Integer.valueOf(orgId),userIds, commonService.getAccessId(request));
		}catch(Exception e){

		}
		return Status.OK;
	}

}
