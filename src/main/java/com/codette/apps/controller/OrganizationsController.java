package com.codette.apps.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.codette.apps.dto.NewOrganizationDTO;
import com.codette.apps.dto.UserDTO;
import com.codette.apps.service.OrganizationServices;
import com.codette.apps.util.CommonConstants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Controller
@RequestMapping("/organizations")
public class OrganizationsController extends CommonBaseController{
	
	final static Logger logger = Logger.getLogger(OrganizationsController.class);
	public static final Gson gson = new GsonBuilder().setDateFormat(CommonConstants.ISO_DATE_FORMAT).create();

	@Resource
	OrganizationServices organizationServices;
	

	@RequestMapping(value = "/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object createUser(@RequestBody NewOrganizationDTO newOrgDTO,
			HttpSession session, HttpServletRequest request) throws Exception {		
		Object object = organizationServices.createOrganization(newOrgDTO);
		return object;
	}

	

	@RequestMapping(value = "{orgId}/details", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object getOrganizationDetails(@RequestBody NewOrganizationDTO newOrgDTO,
			HttpSession session, HttpServletRequest request) throws Exception {		
		Object object = organizationServices.createOrganization(newOrgDTO);
		return object;
	}
}
