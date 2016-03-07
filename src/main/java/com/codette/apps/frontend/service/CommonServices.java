package com.codette.apps.frontend.service;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;

import com.codette.apps.dto.CommunityDTO;
import com.codette.apps.dto.DesignationDTO;
import com.codette.apps.dto.ReligionDTO;
import com.codette.apps.dto.SectionDTO;
import com.codette.apps.dto.StandardDTO;
import com.codette.apps.frontend.model.DropDownValue;
import com.codette.apps.frontend.translator.CommonTranslator;
import com.codette.apps.util.CommonConstants;

@Component
public class CommonServices extends BaseService {

	@Resource
	EmailService emailService;
	
	@Resource
	CommonTranslator commonTranslator;
	
	public String verifyEmail(String email, HttpSession session) throws Exception{
		ResponseEntity<Object> response = null;
		try {
			/*	HttpEntity<String> requestEntity = prepareGet(session); 
		
		response = restTemplate.exchange( getAPIBaseURL() + CommonConstants.COMMON_BASE_URL
							+ CommonConstants.EMAIL_VERIFICATION + CommonConstants.SLASH + email ,
							HttpMethod.GET, requestEntity, Object.class);
			*/
			//if(response.getBody().equals("SUCCESS")){
				
			//}
			
		} catch (RestClientException e) {
			e.printStackTrace();
			throw e;
		}
		return emailService.emailNotification(email, CommonConstants.PASSWORD_RESET_SUBJECT, "Hello User,"+ CommonConstants.PASSWORD_RESET_CONTENT);
		}
	
	/**
	 * 
	 * @param session
	 * @return
	 */
	
	public List<DropDownValue> getCommunityList(HttpSession session){
		ResponseEntity<Object> response = null;
		List<DropDownValue> dropDownList = null;
		HttpEntity<String> requestEntity;
		try {
			requestEntity = prepareGet(session);
			
			response = restTemplate.exchange( getAPIBaseURL() + CommonConstants.COMMUNITY_BASE_URL ,
					HttpMethod.GET, requestEntity, Object.class);
			List<CommunityDTO> communityDTOList = commonTranslator.convertToCommunityDTOList(response.getBody());
			dropDownList = commonTranslator.translateToCommunityDropDownList(communityDTOList);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return dropDownList;
	}
	
	/**
	 * 
	 * @param session
	 * @return
	 */
	
	public List<DropDownValue> getReligionList(HttpSession session){
		ResponseEntity<Object> response = null;
		List<DropDownValue> dropDownList = null;
		HttpEntity<String> requestEntity;
		try {
			requestEntity = prepareGet(session);
			
			response = restTemplate.exchange( getAPIBaseURL() + CommonConstants.SLASH + "religion",
					HttpMethod.GET, requestEntity, Object.class);
			List<ReligionDTO> reigionDTOList = commonTranslator.convertToReligionDTOList(response.getBody());
			dropDownList = commonTranslator.translateToReligionDropDownList(reigionDTOList);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return dropDownList;
	}
	
	/**
	 * 
	 * @param session
	 * @return
	 */
	
	public List<DropDownValue> getDesignationList(HttpSession session){
		ResponseEntity<Object> response = null;
		List<DropDownValue> dropDownList = null;
		HttpEntity<String> requestEntity;
		try {
			requestEntity = prepareGet(session);
			
			response = restTemplate.exchange( getAPIBaseURL() + CommonConstants.DESIGNATION_BASE_URL ,
					HttpMethod.GET, requestEntity, Object.class);
			List<DesignationDTO> designationDTO = commonTranslator.convertToDesignationDTOList(response.getBody());
			dropDownList = commonTranslator.translateToDesignationDropDownList(designationDTO);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return dropDownList;
	}

	public List<DropDownValue> getStandardList(HttpSession session){
		ResponseEntity<Object> response = null;
		List<DropDownValue> dropDownList = null;
		HttpEntity<String> requestEntity;
		try {
			requestEntity = prepareGet(session);
			
			response = restTemplate.exchange( getAPIBaseURL() + CommonConstants.STANDARD_URL ,
					HttpMethod.GET, requestEntity, Object.class);
			List<StandardDTO> standardDTOList = commonTranslator.convertToStandardDTOList(response.getBody());
			dropDownList = commonTranslator.translateToStandardDropDownList(standardDTOList);
		} catch (IOException e) {
			e.printStackTrace();
		} 
		return dropDownList;
	}

	public List<DropDownValue> getSectionList(HttpSession session){
		ResponseEntity<Object> response = null;
		List<DropDownValue> dropDownList = null;
		HttpEntity<String> requestEntity;
		try {
			requestEntity = prepareGet(session);
			
			response = restTemplate.exchange( getAPIBaseURL() + CommonConstants.SECTION_URL ,
					HttpMethod.GET, requestEntity, Object.class);
			List<SectionDTO> standardDTOList = commonTranslator.convertToSectionDTOList(response.getBody());
			dropDownList = commonTranslator.translateToSectionDropDownList(standardDTOList);
		} catch (IOException e) {
			e.printStackTrace();
		} 
		return dropDownList;
	}


}
