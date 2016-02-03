package com.codette.apps.frontend.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.codette.apps.frontend.service.OrganizationService;
import com.codette.apps.util.MessageUtils;

/**
 * @author Vignesh
 *
 */
@Controller
@RequestMapping("/organization")
public class OrganizationController extends BaseController{
	

	@Resource
	private BaseController baseController;
	
	@Resource
	private OrganizationService organizationService;
	
	@RequestMapping(value = "/organization",method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> createOrganization(HttpSession session)  throws Exception {
		Object result=null;
		try{
			String orgId ="ff22113c-4636-11e5-a151-feff819cd234";
			result=organizationService.createOrganization(orgId,session);
		}catch(Exception ex){
			ex.printStackTrace();
			return new ResponseEntity<>(baseController.setResponse(MessageUtils.getMessage("error.getting.creatingorganization"),HttpStatus.INTERNAL_SERVER_ERROR.toString()),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(result,HttpStatus.OK);
	}

}
