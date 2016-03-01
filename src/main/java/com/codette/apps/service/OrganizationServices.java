package com.codette.apps.service;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.codette.apps.controller.OrganizationsController;
import com.codette.apps.dao.OrganizationDAO;
import com.codette.apps.dto.NewOrganizationDTO;
import com.codette.apps.util.CommonConstants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Component
public class OrganizationServices {
	final static Logger logger = Logger.getLogger(OrganizationsController.class);
	public static final Gson gson = new GsonBuilder().setDateFormat(CommonConstants.ISO_DATE_FORMAT).create();

	@Resource
	private OrganizationDAO organizationDAO;

	@Transactional
	public Object createOrganization(NewOrganizationDTO newOrgDTO) {
		// TODO Auto-generated method stub
		return organizationDAO.createOrganization(newOrgDTO);
	}

}
