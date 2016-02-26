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
import org.springframework.web.bind.annotation.ResponseBody;

import com.codette.apps.dto.LeaveManagementDTO;
import com.codette.apps.service.CommonService;
import com.codette.apps.service.LeaveService;
import com.codette.apps.util.CommonConstants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Controller
@RequestMapping(value= "/leavemanagement")
public class LeaveController extends CommonBaseController {

	final static Logger logger = Logger.getLogger(LeaveController.class);
	public static final Gson gson = new GsonBuilder().setDateFormat(CommonConstants.ISO_DATE_FORMAT).create();
	@Resource
	private LeaveService lMService;
	
	@Resource
	private CommonService commonService;
	/**
	 * 
	 * @param status
	 * @param session
	 * @return
	 */
	@RequestMapping(value= "/{orgId}/pending/{userId}")
	@ResponseBody
	public Object getPendingLMSList(@PathVariable( value="orgId") String orgId,
			@PathVariable( value="userId") String userId,
			HttpEntity<String> entity, HttpServletRequest request){
		Object object = null;
		try {
				
		    object = lMService.getPendingLeave(Integer.valueOf(orgId),CommonConstants.STATUS_PENDING,
		    		Integer.valueOf(userId),getRole());
		    if(object instanceof List){
		    	object = gson.toJson(object);
		    }
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return object;
	}
	
	
	@RequestMapping(value= "/{orgId}/history/{userId}")
	@ResponseBody
	public Object getHistoryLMSList(@PathVariable( value="orgId") String orgId,
			@PathVariable( value="userId") String userId, HttpServletRequest request){

		Object object = null;
		try {
				object = lMService.getHistoryLeave(Integer.valueOf(orgId),CommonConstants.STATUS_HISTORY, 
						Integer.valueOf(userId),getRole());
				  if(object instanceof List){
				    	object = gson.toJson(object);
				    }
		}catch (Exception e) {
			e.printStackTrace();
		}
		return object;
	}
	
	/**
	 * 
	 * @param leaveManagement
	 * @param session
	 * @return
	 */
	@RequestMapping(value ="{orgId}/createleave/userId",method = RequestMethod.POST)
	@ResponseBody
	public Object createLeaveRequest(@RequestBody LeaveManagementDTO leaveManagementListDTO,
			@PathVariable( value="orgId") String orgId,
			@PathVariable( value="userId") String userId,
			HttpServletRequest request){
		Object object= null;
		try {
					object = lMService.Applyleave(leaveManagementListDTO,
							Integer.valueOf(orgId),Integer.valueOf(userId),
							getAccessId());

		}catch (Exception e) {
			e.printStackTrace();
		}
		return object;
	}
	

	/**
	 * 
	 * @param leaveManagement
	 * @param session
	 * @return
	 */
	@RequestMapping(value = CommonConstants.UPDATE_REQUEST, method = RequestMethod.PUT)
	@ResponseBody
	public Object updateStatusForLeaveRequest(@RequestBody List<LeaveManagementDTO> leaveManagementListDTO,
			@PathVariable( value="orgId") String orgId,
			@PathVariable( value="userId") String userId,
			HttpServletRequest request){
		Object object = null;
		try {
					object = lMService.statusChange(leaveManagementListDTO,leaveManagementListDTO,Integer.valueOf(orgId),Integer.valueOf(userId), getAccessId());
		} catch (Exception e) {
				e.printStackTrace();
		}
		return object;
	}
	
	
}
