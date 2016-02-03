package com.codette.apps.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.codette.apps.service.CommonService;
import com.codette.apps.dto.CommunityDTO;
import com.codette.apps.dto.DesignationDTO;
import com.codette.apps.dto.ReligionDTO;
import com.codette.apps.dto.SectionDTO;
import com.codette.apps.dto.StandardDTO;
import com.codette.apps.util.CommonConstants;

@Controller
@RequestMapping(value=CommonConstants.COMMON_BASE_URL)
public class CommonBaseController {
	
	@Resource
	CommonService commonServic;
	
	@RequestMapping(value= "/"+ CommonConstants.COMMUNITY)
	@ResponseBody
	public List<CommunityDTO> getCommunityList(){
		return commonServic.getCommunity();
	}

	@RequestMapping(value= "/"+ CommonConstants.DESIGNATION)
	@ResponseBody
	public List<DesignationDTO> getDesignationList(){
		return commonServic.getDesignation();
	}
	
	@RequestMapping(value= "/"+ CommonConstants.RELIGION)
	@ResponseBody
	public List<ReligionDTO> getReligionList(){
		return commonServic.getReligion();
	}
	@RequestMapping(value= "/"+ CommonConstants.STANDARD_URL)
	@ResponseBody
	public List<StandardDTO> getStandardList(){
		return commonServic.getStandard();
	}
	@RequestMapping(value= "/"+ CommonConstants.SECTION_URL)
	@ResponseBody
	public List<SectionDTO> getSectionList(){
		return commonServic.getSection();
	}
}
