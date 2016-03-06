package com.codette.apps.controller;

import java.text.ParseException;

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

import com.codette.apps.dto.ExamDTO;
import com.codette.apps.service.ExamMarkService;
import com.codette.apps.util.CommonConstants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Controller
@RequestMapping(value= "/exammark")
public class ExamMarkController extends CommonBaseController {
	
	final static Logger logger = Logger.getLogger(ExamMarkController.class);
	public static final Gson gson = new GsonBuilder().setDateFormat(CommonConstants.ISO_DATE_FORMAT).create();
	
	@Resource
	private ExamMarkService ExamMarkService;
	
	
	@RequestMapping(value= "/examlist")
	@ResponseBody
	public Object getExams(@RequestParam( value="orgId" , required = false) Integer orgId,
			@RequestParam (value = "userId" , required = false )Integer userId,
			HttpEntity<String> entity, 
			HttpServletRequest request) throws Exception{

		Object object = null;
			try{
				if(orgId != null && orgId != 0 && userId != null && userId != 0){
			    object = ExamMarkService.getExams(orgId,userId,getRole());
				}else{
		        object = ExamMarkService.getExams(getOrganizationId(),getAccessId(),getRole());	
				}
				return object;
			}catch(Exception ex){
				return setCustomExceptionHandler(ex);
			}
	}
	
	@RequestMapping(value= "/marksheet")
	@ResponseBody
	public Object getMarkSheet(@RequestParam( value="orgId" , required = false) Integer orgId,
			@RequestParam (value = "userId" , required = false )Integer userId,
			HttpEntity<String> entity, 
			HttpServletRequest request) throws Exception{
		Object object = null;
			try{
				if(orgId != null && orgId != 0 && userId != null && userId != 0){
			   object = ExamMarkService.getMarkSheet(orgId,userId,getRole());
				}else{
		       object = ExamMarkService.getMarkSheet(getOrganizationId(),getAccessId(),getRole());
				}
				 return object;
			}catch(Exception ex){
				return setCustomExceptionHandler(ex);
			}
	}
	
	/**
	 * 
	 * @param leaveManagement
	 * @param session
	 * @return
	 * @throws Exception 
	 * @throws ParseException 
	 */
	@RequestMapping(value = "/create",method = RequestMethod.POST)
	@ResponseBody
	public Object createExam(@RequestParam( value="orgId",required = false) Integer orgId,
			@RequestBody ExamDTO exam) throws Exception {
		Object object = null;
			try{
			if(orgId != null && orgId != 0){
				object = ExamMarkService.createExam(exam,orgId, getAccessId(),getRole());	
			}else{
				object = ExamMarkService.createExam(exam,getOrganizationId(), getAccessId(),getRole());
			}
			return  object;
			}catch(Exception ex){
		       return setCustomExceptionHandler(ex);
			}
	}

	@RequestMapping(value = "{examId}/delete",method = RequestMethod.POST)
	@ResponseBody
	public Object deleteExam(@RequestParam( value="orgId",required = false) Integer orgId,
		       @PathVariable(value = "examId") Integer examId) throws Exception {
		Object object = null;
			try{
			if(orgId != null && orgId != 0){
				object = ExamMarkService.deleteExam(examId,orgId, getAccessId());	
			}else{
				object = ExamMarkService.deleteExam(examId,getOrganizationId(), getAccessId());
			}
			return  object;
		}catch(Exception ex){
			return setCustomExceptionHandler(ex);
		}
	}
	
}
