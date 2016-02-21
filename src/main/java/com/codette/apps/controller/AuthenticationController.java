package com.codette.apps.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.codette.apps.dto.ResponseBean;
import com.codette.apps.dto.UserAuthenticationDTO;
import com.codette.apps.service.CommonService;
import com.codette.apps.service.AuthenticationService;
import com.codette.apps.util.CommonConstants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Controller
@RequestMapping("/password")
public class AuthenticationController {
	
	final static Logger logger = Logger.getLogger(AuthenticationController.class);
	public static final Gson gson = new GsonBuilder().setDateFormat(CommonConstants.ISO_DATE_FORMAT).create();
	@Resource
	private CommonService commonService;
	@Resource
	private AuthenticationService loginService;

    @RequestMapping(value = "/authentication", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Object authentication(@RequestBody UserAuthenticationDTO userAuthenticationDTO,
    		HttpServletRequest request) throws Exception {
        Object object = loginService.authentication(userAuthenticationDTO,commonService.getAccessId(request));
        return object;
    }
    
    
    @RequestMapping(value = "/resetpassword", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Object  resetPassword(
    		@RequestBody UserAuthenticationDTO userAuthenticationDTO,HttpServletRequest request) throws Exception {
    	Object object = null;
    	try{
    	 object= loginService.resetPassword(userAuthenticationDTO,commonService.getAccessId(request));
    	 if(object instanceof ResponseBean){
    		 
    	 }
    	}catch(Exception e){
    		
    	}
		
		return object;
    }

    @RequestMapping(value = "/changepassword/{newPassword}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Object  changePassword(
    		@RequestBody UserAuthenticationDTO userAuthenticationDTO,
    		@PathVariable(value ="newPassword") String newPassword,HttpServletRequest request) throws Exception {
       Object object = loginService.changePassword(userAuthenticationDTO,newPassword,commonService.getAccessId(request));
        return object;
    }
}
