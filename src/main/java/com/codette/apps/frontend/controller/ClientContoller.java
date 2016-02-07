/**
 * 
 */
package com.codette.apps.frontend.controller;
import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.codette.apps.frontend.model.Subject;
import com.codette.apps.frontend.service.ClientService;

/**
 * @author periyvi
 *
 */


@Controller
@RequestMapping("/client")
public class ClientContoller extends BaseController{

	@Resource
	ClientService clientService; 
	
	/***
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	 public ResponseEntity<?> getClientLists() throws Exception{
		Subject clientList = clientService.getClientList();
		return new ResponseEntity<Subject>(clientList,HttpStatus.OK);
	 }
	
	/***
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value ="/getClientProfile")
	public ResponseEntity<?> getClientDetails() throws Exception{
		Subject clientList = clientService.getClientList();
		return new ResponseEntity<Subject>(clientList,HttpStatus.OK);
	 }
	
	
}
