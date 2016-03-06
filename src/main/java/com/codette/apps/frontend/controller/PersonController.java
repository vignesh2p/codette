package com.codette.apps.frontend.controller;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.codette.apps.frontend.model.Person;
import com.codette.apps.frontend.service.PersonService;
import com.codette.apps.util.CommonConstants;
import com.codette.apps.util.MessageUtils;


@Controller
@RequestMapping("/person")
public class PersonController extends BaseController{
	
	
	@Resource
	PersonService personService;
	
	@Resource
	BaseController baseController;
	
	@RequestMapping(value = "/person",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?>  createPerson(){
		Object result=null;
		try{
	//		result= personService.createPerson(locale);
		}catch(Exception ex){
			return new ResponseEntity<>(baseController.setResponse(MessageUtils.getMessage("error.creating.person"),
					HttpStatus.INTERNAL_SERVER_ERROR.toString()),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return  new ResponseEntity<>(result,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getperson",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?>  getPerson(){
		Object result=null;
		try{
		//sss	result= personService.getPerson(locale);
		}catch(Exception ex){
			return new ResponseEntity<>(baseController.setResponse(MessageUtils.getMessage("error.creating.person"),
					HttpStatus.INTERNAL_SERVER_ERROR.toString()),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return  new ResponseEntity<>(result,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getPersonProfile",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?>  getPersonProfile(){
		Object result=null;
		try{
			result= personService.getPersonProfile();
		}catch(Exception ex){
			return new ResponseEntity<>(baseController.setResponse(MessageUtils.getMessage("error.creating.person"),
					HttpStatus.INTERNAL_SERVER_ERROR.toString()),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return  new ResponseEntity<>(result,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getEvaluationStatus",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?>  getEvaluationStatus(){
		Object result=null;
		try{
			result= personService.getEvaluationStatus();
		}catch(Exception ex){
			return new ResponseEntity<>(baseController.setResponse(MessageUtils.getMessage("error.creating.person"),
					HttpStatus.INTERNAL_SERVER_ERROR.toString()),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return  new ResponseEntity<>(result,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getPersonList",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?>  getPersonList(){
		Object result=null;
		try{
			result= personService.getPersonList();
		}catch(Exception ex){
			return new ResponseEntity<>(baseController.setResponse(MessageUtils.getMessage("error.creating.person"),
					HttpStatus.INTERNAL_SERVER_ERROR.toString()),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return  new ResponseEntity<>(result,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{orgId}/roles",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getPersonsByOrgIdAndRoleType(@PathVariable(value = "orgId")String organizationId,
			@RequestParam(value = "roleType", required=false)String roleType,HttpSession session) throws Exception {
		List<Person> personList = null;
		Map<String, String> queryString = new TreeMap<String, String>();
		try	{
				if(roleType!=null && !roleType.isEmpty()){
					queryString.put(CommonConstants.ROLE_TYPE,roleType);
				}
			//sspersonList = personService.getPersonsByOrgIdAndRoleType(organizationId,roleType,queryString,session);
		}catch(Exception ex){
			return new ResponseEntity<>(baseController.setResponse(MessageUtils.getMessage("error.getting.person.roles"),
					HttpStatus.INTERNAL_SERVER_ERROR.toString()),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<List<Person>>(personList,HttpStatus.OK);
	}
}
