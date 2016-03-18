package com.codette.apps.frontend.service;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;

import com.google.gson.JsonSyntaxException;
import com.codette.apps.dto.LeaveManagementDTO;
import com.codette.apps.frontend.model.LeaveManagement;
import com.codette.apps.frontend.translator.LMSTransator;
import com.codette.apps.util.CommonConstants;

@Component
public class LMSService extends BaseService {

	@Resource
	LMSTransator lMSTransator;
	
	public List<LeaveManagement> getLMSByStatus(Map<String, String> queryString, HttpSession session) throws Exception{
	List<LeaveManagementDTO> leaveManagementDTOList = null; 
	List<LeaveManagement> leaveManagementList = null; 
	String parameters = "";
	try {
		System.out.println("queryString------"+gson.toJson(queryString));
		HttpEntity<String> requestEntity = prepareGet(session); 
		parameters = translateSplitParams(queryString);
		ResponseEntity<Object> response = restTemplate.exchange( getAPIBaseURL()
						+ CommonConstants.LMS_BASE_URL + CommonConstants.LIST_URL + "?" + parameters,
						HttpMethod.GET, requestEntity, Object.class);
		
		System.out.println("response.getBody()-----"+gson.toJson(response.getBody()));
		leaveManagementDTOList = lMSTransator.convertToListOfLMSDTO(response.getBody()); 
		leaveManagementList = lMSTransator.translateToLMSList(leaveManagementDTOList);
	} catch (RestClientException | IOException e) {
		throw e;
	}
	return leaveManagementList;
	}
	
	/**
	 * 
	 * @param leaveManagement
	 * @param session
	 * @return
	 * @throws IOException 
	 * @throws ParseException 
	 */
	public Object createLMSRequest(LeaveManagement leaveManagement, Integer userId,	HttpSession session) throws Exception {
		LeaveManagementDTO leaveManagementDTO = lMSTransator.translateToLMSDTO(leaveManagement, CommonConstants.STATUS_PENDING);
		String postString = gson.toJson(leaveManagementDTO);
		ResponseEntity<Object> response = null;
		try {
			HttpEntity<String> entity = preparePost(postString, session);
			
			response = restTemplate.exchange( getAPIBaseURL()
					+ CommonConstants.LMS_BASE_URL + CommonConstants.CREATE,
					HttpMethod.POST, entity, Object.class);

			return response.getStatusCode();
		}catch (IOException e) {
			throw e;
		}catch (JsonSyntaxException e) {
			throw e;
		} catch (HttpClientErrorException e) {
			throw e;
		}  
	}

	/**
	 * 
	 * @param leaveManagementList
	 * @param session
	 * @return
	 * @throws ParseException
	 */
	public Object updateLMS(List<LeaveManagement> leaveManagementList,	HttpSession session) throws ParseException {
		List<LeaveManagementDTO> leaveManagementDTOList = lMSTransator.translateToLMSDTOList(leaveManagementList);
		String postString = gson.toJson(leaveManagementDTOList);
		System.out.println(postString);
		ResponseEntity<Object> response = null;
		try {
			HttpEntity<String> entity = preparePut(postString, session);
			response = restTemplate.exchange(getAPIBaseURL() + CommonConstants.LMS_BASE_URL + CommonConstants.UPDATE,
					HttpMethod.PUT, entity, Object.class);

		return response.getStatusCode();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
