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
	final static Logger logger = Logger.getLogger(StaffDAOImpl.class);
	public static final Gson gson = new GsonBuilder().setDateFormat(CommonConstants.ISO_DATE_FORMAT).create();
	@Resource
	CommonService commonServic;
	
	@RequestMapping(value= "/"+ CommonConstants.COMMUNITY)
	@ResponseBody
	public List<CommunityDTO> getCommunityList(){
		return commonServic.getCommunity();
	}

	@RequestMapping(value= "/{orgId}/"+ CommonConstants.DESIGNATION)
	@ResponseBody
	public List<DesignationDTO> getDesignationList(@PathVariable( value="orgId") String orgId, HttpServletRequest requests){
		return commonServic.getDesignation(orgId);
	}
	
	@RequestMapping(value= "/"+ CommonConstants.RELIGION)
	@ResponseBody
	public List<ReligionDTO> getReligionList(){
		return commonServic.getReligion();
	}
	@RequestMapping(value= "/{orgId}/"+ CommonConstants.STANDARD_URL)
	@ResponseBody
	public List<StandardDTO> getStandardList(@PathVariable( value="orgId") String orgId, HttpServletRequest requests){
		return commonServic.getStandard(orgId);
	}
	@RequestMapping(value= "/{orgId}/"+ CommonConstants.SECTION_URL)
	@ResponseBody
	public List<SectionDTO> getSectionList(@PathVariable( value="orgId") String orgId, HttpServletRequest requests){
		return commonServic.getSection(orgId);
	}
}
