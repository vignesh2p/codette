/**
 * 
 */
package com.codette.apps.frontend.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;

import com.google.gson.JsonSyntaxException;
import com.codette.apps.frontend.model.Organization;
import com.codette.apps.frontend.translator.OrganizationTranslator;
import com.codette.apps.util.CommonConstants;

/**
 * @author bashelu
 *
 */
@Component
public class OrganizationService extends BaseService{
	
	@Resource
	OrganizationTranslator organizationTranslator;
	
	
	 public Object createOrganization(String startOrgId,HttpSession session) throws IOException {
		//sOrganization organization = organizationTranslator.convertOrganization();
	//	OrganizationDTO organizationDTO = organizationTranslator.translateToOrganizationDTO(organization, startOrgId);
	//	String postString = gson.toJson(organizationDTO);
		String postString = null;
		try {
			HttpEntity<String> entity = preparePost(postString,session);
			ResponseEntity<Object> response = restTemplate.exchange(
					getAPIBaseURL() + CommonConstants.ORGANIZATION_BASE_URL,
							 HttpMethod.POST, entity,Object.class);
			return response.getStatusCode();
		}catch (IOException e) {
			throw e;
		} catch (JsonSyntaxException e) {
			throw e;
		} catch (HttpClientErrorException e) {
			throw e;
		}
	}
	 
	
}
