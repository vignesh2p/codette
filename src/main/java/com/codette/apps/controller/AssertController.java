package com.codette.apps.controller;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.codette.apps.dto.ImageDTO;
import com.codette.apps.service.AssertService;
import com.codette.apps.service.CommonService;
import com.codette.apps.util.CommonConstants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Controller
@RequestMapping("/assert")
public class AssertController extends CommonBaseController{

	final static Logger logger = Logger.getLogger(AssertController.class);
	public static final Gson gson = new GsonBuilder().setDateFormat(CommonConstants.ISO_DATE_FORMAT).create();
	@Resource
	private CommonService commonService;
	@Resource
	private AssertService assertService;

    @RequestMapping(value = "/create",
    		method = RequestMethod.POST,
    		produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Object createDisplayPicture(@RequestBody ImageDTO image) throws Exception {
        Object object = assertService.createDisplayPicture(image,getOrganizationId(),getAccessId());
        return object;
    }
    
}
