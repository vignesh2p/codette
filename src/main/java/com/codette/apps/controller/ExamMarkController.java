package com.codette.apps.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.codette.apps.dto.ExamDTO;
import com.codette.apps.dto.LeaveManagementDTO;
import com.codette.apps.dto.MarkSheetDTO;
import com.codette.apps.dto.ResponseBean;
import com.codette.apps.dto.StandardDTO;
import com.codette.apps.service.ExamMarkService;
import com.codette.apps.service.LMService;
import com.codette.apps.util.CommonConstants;

@Controller
@RequestMapping(value= "/exam")
public class ExamMarkController {
	@Resource
	ExamMarkService ExamMarkService;
	/**
	 * 
	 * @param status
	 * @param session
	 * @return
	 */
	@RequestMapping(value= "/marksheets/{userId}")
	@ResponseBody
	public List<MarkSheetDTO> getMarkSheet(@RequestBody Integer userId,HttpEntity<String> entity, HttpServletRequest request){
		List<MarkSheetDTO> markSheet = null;
		try {
			String userID = request.getHeader(CommonConstants.SESSION_USER_ID);
			String role = request.getHeader(CommonConstants.SESSION_USERROLE);
			if(userID != null && role != null){
				//Integer userId = Integer.valueOf(userID);
				System.out.println("userId"+userID+role);
				markSheet = ExamMarkService.getMarkSheet(userId);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return markSheet;
	}
	
	/**
	 * 
	 * @param leaveManagement
	 * @param session
	 * @return
	 */
	@RequestMapping(value = CommonConstants.CREATE_EXAM,method = RequestMethod.POST)
	@ResponseBody
	public ResponseBean createLeaveRequest(@RequestBody ExamDTO exam,HttpServletRequest request){
		ResponseBean responseBean = new ResponseBean();
		try {
			System.out.println("lllllll");
			if(exam != null ){
				String userID = request.getHeader(CommonConstants.SESSION_USER_ID);
				String role = request.getHeader(CommonConstants.SESSION_USERROLE);
				if(userID != null && role != null){
					System.out.println("lllllll");
					Integer userId = Integer.valueOf(userID);
					responseBean = ExamMarkService.createExam(exam,userId);
			}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return  responseBean ;
	}

	
}
