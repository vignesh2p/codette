package com.codette.apps.controller;

import java.io.IOException;
import java.text.ParseException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.codette.apps.frontend.model.ResponseMessage;
import com.codette.apps.service.CommonService;
import com.codette.apps.util.CommonConstants;
import com.codette.apps.util.FiedValidationException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Controller
@RequestMapping(value="/")
public class CommonBaseController {
	@Autowired
	HttpServletRequest request ;
	
	@Autowired
	HttpServletResponse response;
	
	final static Logger logger = Logger.getLogger(CommonBaseController.class);
	public static final Gson gson = new GsonBuilder().setDateFormat(CommonConstants.ISO_DATE_FORMAT).create();
	@Resource
	private CommonService commonServic;
	
	@RequestMapping(value= "/community")
	@ResponseBody
	public Object getCommunityList(
			@RequestParam (value = "orgId",required = false) Integer orgId){
		Object object = null;
		if(orgId != null && orgId != 0){
			return object = gson.toJson(commonServic.getCommunity(orgId));
		}
		object = gson.toJson(commonServic.getCommunity(getOrganizationId()));
		return object; 
	}

	@RequestMapping(value= "/designation")
	@ResponseBody
	public Object getDesignationList(@RequestParam (value = "orgId",required = false) Integer orgId){

		if(orgId != null && orgId != 0){
			return gson.toJson(commonServic.getDesignation(orgId));
		}
		return  gson.toJson(commonServic.getDesignation(getOrganizationId()));
		
	}
	
	@RequestMapping(value= "/religon")
	@ResponseBody
	public Object getReligionList(@RequestParam (value = "orgId",required = false) Integer orgId){

		if(orgId != null && orgId != 0){
			return  gson.toJson(commonServic.getReligion(orgId));
		}
		return  gson.toJson(commonServic.getReligion(getOrganizationId()));
	}
	@RequestMapping(value= "/standard")
	@ResponseBody
	public Object getStandardList(@RequestParam (value = "orgId",required = false) Integer orgId){

		if(orgId != null && orgId != 0){
			return  gson.toJson(commonServic.getStandard(orgId));
		}
		return  gson.toJson(commonServic.getStandard(getOrganizationId()));
	}
	@RequestMapping(value= "/section")
	@ResponseBody
	public Object getSectionList(@RequestParam (value = "orgId",required = false) Integer orgId){

		if(orgId != null && orgId != 0){
			return  gson.toJson(commonServic.getSection(orgId));
		}
		return  gson.toJson(commonServic.getSection(getOrganizationId()));
	}
	@RequestMapping(value= "/subject")
	@ResponseBody
	public Object getSubjectList(@RequestParam (value = "orgId",required = false) Integer orgId){
		if(orgId != null && orgId != 0){
			return  gson.toJson(commonServic.getSubject(orgId));
		}
		return  gson.toJson(commonServic.getSubject(getOrganizationId()));
	}
	public Integer getAccessId() {
		if(request.getHeader(CommonConstants.SESSION_USER_ID) != null){
			return Integer.valueOf(request.getHeader(CommonConstants.SESSION_USER_ID));	
		}
		return null;
	}
	
   public Integer getOrganizationId() {
		if(request.getHeader(CommonConstants.SESSION_ORG_ID) != null){
			return Integer.valueOf(request.getHeader(CommonConstants.SESSION_ORG_ID));
		}
		return null;
	}

	public String getRole() {
		if(request.getHeader(CommonConstants.SESSION_USERROLE) != null){
			return request.getHeader(CommonConstants.SESSION_USERROLE);
		}
		return null;
	}
	
	/**
	 * Setting custom exception
	 * @param ex
	 * @param message
	 * @return
	 * @throws IOException
	 */
	public ResponseEntity<?> setCustomExceptionHandler(Exception ex) throws Exception {
		ResponseMessage responseMessage = new ResponseMessage();
		if(ex instanceof FiedValidationException){
			responseMessage.setMessage(((FiedValidationException)ex).getMessage());
			responseMessage.setStatus(String.valueOf(HttpStatus.UNAUTHORIZED));
			return new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.UNAUTHORIZED);
		} else {
			if(ex instanceof ParseException) {
				responseMessage.setStatus(String.valueOf(HttpServletResponse.SC_CONFLICT));
				responseMessage.setMessage(ex.getMessage());
				return new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.CONFLICT);
			} else if(ex instanceof IOException) {
				responseMessage.setStatus(String.valueOf(HttpServletResponse.SC_CONFLICT));
				responseMessage.setMessage(ex.getMessage());
				return new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.INTERNAL_SERVER_ERROR);
			} else {
				responseMessage.setStatus(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR));
				responseMessage.setMessage(ex.getMessage());
				return new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
	}
	
}
