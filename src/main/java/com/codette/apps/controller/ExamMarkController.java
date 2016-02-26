package com.codette.apps.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.codette.apps.dto.ExamDTO;
import com.codette.apps.service.CommonService;
import com.codette.apps.service.ExamMarkService;
import com.codette.apps.util.CommonConstants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Controller
@RequestMapping(value= "/exam")
public class ExamMarkController extends CommonBaseController {
	
	final static Logger logger = Logger.getLogger(ExamMarkController.class);
	public static final Gson gson = new GsonBuilder().setDateFormat(CommonConstants.ISO_DATE_FORMAT).create();
	
	@Resource
	private ExamMarkService ExamMarkService;
	
	/**
	 * 
	 * @param status
	 * @param session
	 * @return
	 */
	@RequestMapping(value= "/{orgId}/marksheet/{userId}")
	@ResponseBody
	public Object getMarkSheet(@PathVariable( value="orgId") String orgId,@RequestBody Integer userId,HttpEntity<String> entity, HttpServletRequest request){

		Object object = null;
		try {
			object = ExamMarkService.getMarkSheet(Integer.valueOf(orgId),userId,getRole());
			if(object instanceof List){
			object = gson.toJson(object);
			}
			
		} catch (Exception e) {
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
	@RequestMapping(value = "/{orgId}/createexam/{userId}",method = RequestMethod.POST)
	@ResponseBody
	public Object createExam(@PathVariable( value="orgId") String orgId,@PathVariable( value="userId") String userId,@RequestBody ExamDTO exam,HttpServletRequest request){
		Object object = null;
		try {
				object = ExamMarkService.createExam(exam,Integer.valueOf(orgId),Integer.valueOf(userId), getAccessId());
		}catch (Exception e) {
			e.printStackTrace();
		}
		return  HttpStatus.SC_OK ;
	}

	
}
