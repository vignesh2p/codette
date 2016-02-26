package com.codette.apps.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.codette.apps.service.CommonService;
import com.codette.apps.util.CommonConstants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Controller
@RequestMapping(value="/")
public class CommonBaseController {
	@Autowired
	HttpServletRequest request ;
	final static Logger logger = Logger.getLogger(CommonBaseController.class);
	public static final Gson gson = new GsonBuilder().setDateFormat(CommonConstants.ISO_DATE_FORMAT).create();
	@Resource
	private CommonService commonServic;
	
	@RequestMapping(value= "/community")
	@ResponseBody
	public Object getCommunityList(){
		Object object = null;
		object = gson.toJson(commonServic.getCommunity());
		return object; 
	}

	@RequestMapping(value= "/designation")
	@ResponseBody
	public Object getDesignationList(){
		Object object = null;
		return object = gson.toJson(commonServic.getDesignation(getOrganizationId()));
		
	}
	
	@RequestMapping(value= "/religon")
	@ResponseBody
	public Object getReligionList(){
		Object object = null;
		return object = gson.toJson(commonServic.getReligion());
	}
	@RequestMapping(value= "/standard")
	@ResponseBody
	public Object getStandardList(){
		Object object = null;
		return object = gson.toJson(commonServic.getStandard(getOrganizationId()));
	}
	@RequestMapping(value= "/section")
	@ResponseBody
	public Object getSectionList( HttpServletRequest requests){
		Object object = null;
		return object = gson.toJson(commonServic.getSection(getOrganizationId()));
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
}
