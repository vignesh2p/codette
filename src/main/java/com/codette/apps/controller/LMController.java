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

import com.codette.apps.service.LMService;
import com.codette.apps.dao.impl.StaffDAOImpl;
import com.codette.apps.dto.LeaveManagementDTO;
import com.codette.apps.dto.ResponseBean;
import com.codette.apps.util.CommonConstants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Controller
@RequestMapping(value= "/leaveManagement")
public class LMController {

	final static Logger logger = Logger.getLogger(StaffDAOImpl.class);
	public static final Gson gson = new GsonBuilder().setDateFormat(CommonConstants.ISO_DATE_FORMAT).create();
	@Resource
	LMService lMService;
	/**
	 * 
	 * @param status
	 * @param session
	 * @return
	 */
	@RequestMapping(value= "/PENDING")
	@ResponseBody
	public List<LeaveManagementDTO> getPendingLMSList(@PathVariable( value="orgId") String orgId,HttpEntity<String> entity, HttpServletRequest request){
		List<LeaveManagementDTO> lmsList = null;
		try {
			String userID = request.getHeader(CommonConstants.SESSION_USER_ID);
			String role = request.getHeader(CommonConstants.SESSION_USERROLE);
			if(userID != null && role != null){
				Integer userId = Integer.valueOf(userID);
				System.out.println("userId"+userID+role);
				
				lmsList = lMService.getPendingLeave(CommonConstants.STATUS_PENDING, userId, role);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lmsList;
	}
	
	
	@RequestMapping(value= "/HISTORY")
	@ResponseBody
	public Object getHistoryLMSList( HttpServletRequest request){
		List<LeaveManagementDTO> lmsList = null;
		try {
			String userID = request.getHeader(CommonConstants.SESSION_USER_ID);
			String role = request.getHeader(CommonConstants.SESSION_USERROLE);
			if(userID != null && role != null){
				Integer userId = Integer.valueOf(userID);
				lmsList = lMService.getHistoryLeave(CommonConstants.STATUS_HISTORY, userId, role);
		}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return lmsList;
	}
	
	/**
	 * 
	 * @param leaveManagement
	 * @param session
	 * @return
	 */
	@RequestMapping(value = CommonConstants.CREATE_LEAVE_REQUEST,method = RequestMethod.POST)
	@ResponseBody
	public ResponseBean createLeaveRequest(@RequestBody LeaveManagementDTO leaveManagementListDTO,HttpServletRequest request){
		ResponseBean responseBean = new ResponseBean();
		try {
			System.out.println("lllllll");
			if(leaveManagementListDTO != null ){
				System.out.println("lllllll");
				String userID = request.getHeader(CommonConstants.SESSION_USER_ID);
				String role = request.getHeader(CommonConstants.SESSION_USERROLE);
				if(userID != null && role != null){
					System.out.println("lllllll");
					Integer userId = Integer.valueOf(userID);
					responseBean = lMService.Applyleave(leaveManagementListDTO,userId);
			}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return  responseBean ;
	}
	

	/**
	 * 
	 * @param leaveManagement
	 * @param session
	 * @return
	 */
	@RequestMapping(value = CommonConstants.UPDATE_REQUEST, method = RequestMethod.PUT)
	@ResponseBody
	public ResponseBean updateStatusForLeaveRequest(@RequestBody List<LeaveManagementDTO> leaveManagementListDTO,HttpServletRequest request){
		ResponseBean responseBean = new ResponseBean();
		try {
			if(leaveManagementListDTO != null && !leaveManagementListDTO.isEmpty()){
				String userID = request.getHeader(CommonConstants.SESSION_USER_ID);
				String role = request.getHeader(CommonConstants.SESSION_USERROLE);
				if(userID != null && role != null){
					Integer userId = Integer.valueOf(userID);
					responseBean = lMService.statusChange(leaveManagementListDTO, userId);
					System.out.println("responseBean--------->"+responseBean);
				}
			}
		} catch (Exception e) {
				e.printStackTrace();
		}
		return  responseBean ;
	}
	
	
}
