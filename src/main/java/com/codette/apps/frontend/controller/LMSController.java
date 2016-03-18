package com.codette.apps.frontend.controller;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.codette.apps.frontend.model.LeaveManagement;
import com.codette.apps.frontend.model.User;
import com.codette.apps.frontend.service.LMSService;
import com.codette.apps.util.CommonConstants;

@Controller
@RequestMapping(value= "/lms")
public class LMSController extends BaseController{

	@Resource
	LMSService lMSService; 
	
	
	/**
	 * 
	 * @param status
	 * @param session
	 * @return
	 */
	@RequestMapping(value= "/{status}", method = RequestMethod.GET)
	public ResponseEntity<?> getLMSList(@PathVariable(value="status")String status, HttpSession session){
		List<LeaveManagement> lmsList = null;
		Map<String, String> queryString = new HashMap<String, String>();
		try {
		if(status != null && !status.isEmpty()){
			queryString.put(CommonConstants.STATUS, status);
			lmsList = lMSService.getLMSByStatus(queryString, session);
		}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<List<LeaveManagement>>(lmsList, HttpStatus.OK);
	}
	
	
	/**
	 * 
	 * @param leaveManagement
	 * @param session
	 * @return
	 */
	@RequestMapping(value = CommonConstants.CREATE ,method = RequestMethod.POST)
	public ResponseEntity<?> createLeaveRequest(@RequestBody LeaveManagement leaveManagement, HttpSession session, HttpServletRequest request){
		Object obj = null;
		try {
			Integer userId = Integer.valueOf(session.getAttribute(CommonConstants.SESSION_USER_ID).toString());
			System.out.println(gson.toJson(leaveManagement));
			obj = lMSService.createLMSRequest(leaveManagement, userId, session);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Object>(obj, HttpStatus.OK);
	}
	
	

	/**
	 * 
	 * @param leaveManagement
	 * @param session
	 * @return
	 */
	@RequestMapping(value = CommonConstants.UPDATE , method = RequestMethod.PUT)
	public ResponseEntity<?> updateStatusForLeaveRequest(@RequestBody List<LeaveManagement> leaveManagementList, HttpSession session){
		Object obj = null;
		try {
			if(leaveManagementList != null && !leaveManagementList.isEmpty()){
				System.out.println("leaveManagementList----------"+gson.toJson(leaveManagementList));
				obj = lMSService.updateLMS(leaveManagementList, session);
				System.out.println("Obj--------"+gson.toJson(obj));
			}
		} catch (Exception e) {
				e.printStackTrace();
		}
		return  new ResponseEntity<Object>(obj , HttpStatus.OK) ;
	}
	
	
}
