package com.codette.apps.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.codette.apps.dao.impl.StaffDAOImpl;
import com.codette.apps.dto.AttendenceDTO;
import com.codette.apps.dto.ResponseBean;
import com.codette.apps.dto.UserDTO;
import com.codette.apps.service.AttendanceService;
import com.codette.apps.service.CommonService;
import com.codette.apps.util.CommonConstants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


@Controller
@RequestMapping("/attendance")
public class AttendanceController {
	
	final static Logger logger = Logger.getLogger(StaffDAOImpl.class);
	public static final Gson gson = new GsonBuilder().setDateFormat(CommonConstants.ISO_DATE_FORMAT).create();
	
	@Resource
	AttendanceService attendanceService;
	
	@Resource
	CommonService commonService;
	@RequestMapping(value = "/{orgId}/enableattendence/{userId}", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseBean enableAttendence(
			@PathVariable( value="orgId") String orgId,
			@PathVariable( value="userId") String userId,
			HttpServletRequest request)  {
			return attendanceService.enableAttendance(Integer.valueOf(orgId),Integer.valueOf(userId),commonService.getAccessId(request));
	}   
    
   @RequestMapping(value = "/{orgId}/getattendencesheet/{userId}", method = RequestMethod.GET)
	@ResponseBody
	public List<AttendenceDTO> getAttendence(
			@PathVariable( value="orgId") String orgId,
			@PathVariable( value="userId") String userId,
			HttpServletRequest requests)  {
			return attendanceService.getAttendance(Integer.valueOf(orgId),Integer.valueOf(userId));
	   	}
   
   
	
	@RequestMapping(value = "/{orgId}/updateattendance/{userIds}", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseBean updateAttendence(
			@PathVariable( value="orgId") String orgId,
			@PathVariable List<Integer> userIds, 
			@RequestBody UserDTO userDTO,
			HttpSession session,
			HttpServletRequest request) throws Exception {
		
		ResponseBean responseBean = new ResponseBean();
		responseBean = attendanceService.updateAttendance(Integer.valueOf(orgId),userIds, commonService.getAccessId(request));
		return responseBean;
	}

}
