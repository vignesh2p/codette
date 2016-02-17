package com.codette.apps.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.codette.apps.service.CommonService;
import com.codette.apps.dao.impl.StaffDAOImpl;
import com.codette.apps.dto.CommunityDTO;
import com.codette.apps.dto.DesignationDTO;
import com.codette.apps.dto.ReligionDTO;
import com.codette.apps.dto.SectionDTO;
import com.codette.apps.dto.StandardDTO;
import com.codette.apps.util.CommonConstants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Controller
@RequestMapping(value=CommonConstants.COMMON_BASE_URL)
public class CommonBaseController {
	final static Logger logger = Logger.getLogger(CommonBaseController.class);
	public static final Gson gson = new GsonBuilder().setDateFormat(CommonConstants.ISO_DATE_FORMAT).create();
	@Resource
	private CommonService commonServic;
	
	@RequestMapping(value= "/"+ CommonConstants.COMMUNITY)
	@ResponseBody
	public Object getCommunityList(){
		Object object = null;
		object = gson.toJson(commonServic.getCommunity());
		return object; 
	}

	@RequestMapping(value= "/{orgId}/"+ CommonConstants.DESIGNATION)
	@ResponseBody
	public Object getDesignationList(@PathVariable( value="orgId") String orgId, HttpServletRequest requests){
		Object object = null;
		return object = gson.toJson(commonServic.getDesignation(orgId));
		
	}
	
	@RequestMapping(value= "/"+ CommonConstants.RELIGION)
	@ResponseBody
	public Object getReligionList(){
		Object object = null;
		return object = gson.toJson(commonServic.getReligion());
	}
	@RequestMapping(value= "/{orgId}/"+ CommonConstants.STANDARD_URL)
	@ResponseBody
	public Object getStandardList(@PathVariable( value="orgId") String orgId, HttpServletRequest requests){
		Object object = null;
		return object = gson.toJson(commonServic.getStandard(orgId));
	}
	@RequestMapping(value= "/{orgId}/"+ CommonConstants.SECTION_URL)
	@ResponseBody
	public Object getSectionList(@PathVariable( value="orgId") String orgId, HttpServletRequest requests){
		Object object = null;
		return object = gson.toJson(commonServic.getSection(orgId));
	}
}
