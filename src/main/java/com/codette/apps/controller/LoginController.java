package com.codette.apps.controller;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.codette.apps.dao.impl.StaffDAOImpl;
import com.codette.apps.dto.UserAuthenticationDTO;
import com.codette.apps.dto.UserDTO;
import com.codette.apps.service.LoginService;
import com.codette.apps.util.CommonConstants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Controller
@RequestMapping("/password")
public class LoginController {
	
	final static Logger logger = Logger.getLogger(StaffDAOImpl.class);
	public static final Gson gson = new GsonBuilder().setDateFormat(CommonConstants.ISO_DATE_FORMAT).create();
	@Resource
	LoginService loginService;

    @RequestMapping(value = "/authentication", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<UserDTO>  authentication(@RequestBody UserAuthenticationDTO userAuthenticationDTO) throws Exception {
        UserDTO user = new UserDTO();
        user = loginService.authentication(userAuthenticationDTO);
        return new ResponseEntity<UserDTO>(user, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/resetpassword", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<UserDTO>  resetPassword(
    		@RequestBody UserAuthenticationDTO userAuthenticationDTO) throws Exception {
        UserDTO user = new UserDTO();
        user = loginService.resetPassword(userAuthenticationDTO);
        return new ResponseEntity<UserDTO>(user, HttpStatus.OK);
    }

    @RequestMapping(value = "/changepassword/{newPassword}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<UserDTO>  changePassword(
    		@RequestBody UserAuthenticationDTO userAuthenticationDTO,
    		@PathVariable(value ="newPassword") String newPassword) throws Exception {
        UserDTO user = new UserDTO();
        user = loginService.changePassword(userAuthenticationDTO,newPassword);
        return new ResponseEntity<UserDTO>(user, HttpStatus.OK);
    }
}
