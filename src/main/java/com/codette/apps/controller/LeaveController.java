package com.codette.apps.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.codette.apps.dto.LeaveManagementDTO;
import com.codette.apps.service.CommonService;
import com.codette.apps.service.LeaveService;
import com.codette.apps.util.CommonConstants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Controller
@RequestMapping(value= "/leave")
public class LeaveController extends CommonBaseController {

	final static Logger logger = Logger.getLogger(LeaveController.class);
	public static final Gson gson = new GsonBuilder().setDateFormat(CommonConstants.ISO_DATE_FORMAT).create();
	
	@Resource
	private LeaveService leaveService;
	
	@Resource
	private CommonService commonService;
	/**
	 * 
	 * @param status
	 * @param session
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value= "/list")
	@ResponseBody
	public Object getPendingLMSList(@RequestParam( value = "status", required = true) String status,
			@RequestParam(value="userId" , required = false) String userId,
			HttpEntity<String> entity, HttpServletRequest request) throws Exception{
			Object lmsList = null;
	try {
		if(userId == null){
			userId = getAccessId().toString();
		}
		
		if(status != null && CommonConstants.STATUS_PENDING.equalsIgnoreCase(status)){
			lmsList = leaveService.getPendingLeave(getOrganizationId(), status, Integer.valueOf(userId), getRole());
		} else if(status != null && CommonConstants.STATUS_HISTORY.equalsIgnoreCase(status)) {
			lmsList = leaveService.getHistoryLeave(getOrganizationId(), status, Integer.valueOf(userId), getRole());
		}
	    return lmsList;
	} catch (Exception ex) {
		ex.printStackTrace();
		return setCustomExceptionHandler(ex);
	}
		
	}
	
	/**
	 * 
	 * @param leaveManagement
	 * @param session
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value ="/create", method = RequestMethod.POST)
	@ResponseBody
	public Object createLeaveRequest(@RequestBody LeaveManagementDTO leaveManagementListDTO,
			@RequestParam(value="userId", required = false) String userId) throws Exception{
		Object object= null;
		try {
			if(userId == null){
				userId  = getAccessId().toString();
			}
			if(userId != null){
				object = leaveService.Applyleave(leaveManagementListDTO, getOrganizationId(), Integer.valueOf(userId), getAccessId());
			}
			return object;
		}catch (Exception ex) {
			ex.printStackTrace();
			return setCustomExceptionHandler(ex);
		}
	}
	

	/**
	 * 
	 * @param leaveManagement
	 * @param session
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = CommonConstants.UPDATE, method = RequestMethod.PUT)
	@ResponseBody
	public Object updateStatusForLeaveRequest(@RequestBody List<LeaveManagementDTO> leaveManagementListDTO,
			@RequestParam(value="userId", required = false) String userId,
			HttpServletRequest request) throws Exception{
		Object object = null;
		try {
			if(userId == null){
				userId  = getAccessId().toString();
			}
			object = leaveService.statusChange(leaveManagementListDTO,leaveManagementListDTO, getOrganizationId(), Integer.valueOf(userId), getAccessId());
			return object ;
		} catch (Exception ex) {
			return setCustomExceptionHandler(ex);
		}
	}
	
	
}
