package com.codette.apps.frontend.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletInputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.googlecode.ehcache.annotations.Cacheable;
import com.googlecode.ehcache.annotations.KeyGenerator;
import com.googlecode.ehcache.annotations.PartialCacheKey;
import com.codette.apps.frontend.model.APIConfig;
import com.codette.apps.frontend.model.RoleMenu;
import com.codette.apps.frontend.translator.BaseTranslator;
import com.codette.apps.util.CommonConstants;
import com.codette.apps.util.CommonUtil;

@Component
public class BaseService {

	
	public static final Gson gson = new GsonBuilder().setDateFormat(CommonConstants.ISO_DATE_FORMAT).create();

	@Autowired
	public RestTemplate restTemplate;

	@Resource
	private BaseTranslator baseTranslator;
	
	@Resource
	CommonUtil commonUtil;
	
	public HttpEntity<String> prepareGet(HttpSession session) throws  IOException {
		HttpEntity<String> entity = new HttpEntity<String>(prepareHeader(session));
		return entity;
	}

	public HttpEntity<String> preparePost(String body, HttpSession session) throws  IOException {
		HttpEntity<String> entity = new HttpEntity<String>(body, prepareHeader(session));
		return entity;
	}
	public HttpEntity<String> preparePut(String body, HttpSession session) throws  IOException {
		HttpEntity<String> entity = new HttpEntity<String>(body, prepareHeader(session));
		return entity;
	}

	public HttpEntity<String> prepareUpdate(String body, HttpSession session) throws IOException {
		HttpEntity<String> entity = new HttpEntity<String>(body, prepareHeader(session));
		return entity;
	}

	public HttpEntity<String> prepareDelete(HttpSession session) throws  IOException {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", getAuthorizationHeader());
		if(session.getAttribute(CommonConstants.SESSION_TOKEN) != null && session.getAttribute(CommonConstants.SESSION_ORG_ID) != null){
			headers.add(CommonConstants.X_AUTH_TOKEN, session.getAttribute(CommonConstants.SESSION_TOKEN).toString());
			headers.add(CommonConstants.XORG_ID, session.getAttribute(CommonConstants.SESSION_ORG_ID).toString());
		}
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		return entity;
	}
	
	public HttpHeaders prepareHeader(HttpSession session) throws  IOException {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json");
	//	headers.add("Accept", "application/x-gzip");
	//	headers.add("Authorization", getAuthorizationHeader());
		if(session.getAttribute(CommonConstants.SESSION_USER_ID)!= null)
			headers.add(CommonConstants.SESSION_USER_ID, session.getAttribute(CommonConstants.SESSION_USER_ID).toString());
		if(session.getAttribute(CommonConstants.SESSION_USERROLE)!= null)	
			headers.add(CommonConstants.SESSION_USERROLE, session.getAttribute(CommonConstants.SESSION_USERROLE).toString());
		if(session.getAttribute(CommonConstants.SESSION_TOKEN) != null && session.getAttribute(CommonConstants.SESSION_ORG_ID) != null){
			headers.add(CommonConstants.X_AUTH_TOKEN, session.getAttribute(CommonConstants.SESSION_TOKEN).toString());
			headers.add(CommonConstants.XORG_ID, session.getAttribute(CommonConstants.SESSION_ORG_ID).toString());
		}
		return headers;
	}
	
	/**
	 * Get organization id from api-config properties
	 * @return organizationId A not null {@link String}
	 * @throws Exception
	 */
	public String getOrganizationId() throws IOException {
		APIConfig fan360api = getApiConfig();
		return fan360api.getOrganization();
	}

	public String getAPIBaseURL() throws  IOException  {
		APIConfig dtsapi = getApiConfig();
		return dtsapi.getApiUrl();
	}
	
	public String getContentURL() throws IOException {
		APIConfig dtsapi = getApiConfig();
		return dtsapi.getContentUrl();
	}
	
	public String getAuthorizationHeader() throws  IOException {
		APIConfig dtsapi = getApiConfig();
		if(dtsapi.getAuthorization()!=null)
			return dtsapi.getAuthorization();
		else
			return "Basic YWRtaW46cGFzc3dvcmQ=";
	}

	/**
	 * To get APIConfig details
	 * @return {@link APIConfig}.
	 */
	public APIConfig getApiConfig() throws IOException{
		File file = new File("api-config.json");
		InputStream inputStream = null;

		if (!file.exists()) {
			/* if not exists, reading file from appln file path */
			inputStream = this
					.getClass()
					.getClassLoader()
					.getResourceAsStream(
							"com/codette/apps/properties/api-config.json");
		} else {
			/* file exists, reading file */
			try {
				inputStream = new FileInputStream("api-config.json");
			} catch (FileNotFoundException exception) {
				throw exception;
			}
		}
		
		APIConfig apiConfig = gson.fromJson(commonUtil.getStringFromInputStream(inputStream), APIConfig.class);
		return apiConfig;
	}

	/**
	 * To get Json Config.
	 * @param fileName the String value of file name.
	 * @return String
	 * @throws Exception
	 */
	public String getJsonConfig(String fileName) throws IOException {
		File file = new File(fileName);
		InputStream inputStream = null;

		if (!file.exists()) {
			/* if not exists, reading file from appln file path */
			inputStream = this
					.getClass()
					.getClassLoader()
					.getResourceAsStream(
							"com/codette/apps/properties/" + fileName);
		} else {
			/* file exists, reading file */
			try {
				inputStream = new FileInputStream(fileName);
			} catch (FileNotFoundException exception) {
				throw exception;
			}

		}
		String jsonString = commonUtil.getStringFromInputStream(inputStream);
		return jsonString;
	}
	
	/**
	 * Get Organization id from Session 
	 * @param session
	 * @return {@link String}
	 * @throws Exception
	 */
	public String getOrganizationId(HttpSession session) {
		return baseTranslator.getOrganizationId(session);
	}

	
	public String translateObjectToJson(Object obj) {
		return baseTranslator.translateObjectToJson(obj);
	}
	
	
	
	/**
	 * Translate Query parameters
	 * @param queryString The QueryString
	 * @return parametersList {@link String}
	 */
	public String translateQueryParams(Map<String, String> queryString) {
		String parameters = "";
		String parametersList = "";
		
		if(queryString != null && !queryString.isEmpty()){
					
			for(Map.Entry<String, String> entry : queryString.entrySet()){
				parameters =  parameters + splitData(entry.getKey(), entry.getValue());
			}
		}
		
		if(!parameters.isEmpty()){
			int index = parameters.lastIndexOf("&");
			parametersList = parameters.substring(0, index);
		}
		
		return parametersList;
	}
	
	/**
	 * To split Data.
	 * @param param the String value of param.
	 * @param data the String value of data.
	 * @return String
	 */
	public String splitData(String param,String data) {
		String paramName="";
		if(data != null){
			paramName = param+"="+data+"&";
			
		    if(data.indexOf(",") != -1){
				String[] splitedParamData=data.split(",");
				for(String paramString:splitedParamData) {
					paramName+=param+"="+paramString+"&";
					
				}
		    } else{
		    	paramName+=param+"="+data+"&";
		    }
		}
		return paramName;
	}
	
	/**
	 * Translate Query Parameters
	 * @param queryString
	 * @return
	 * @throws Exception
	 */
	public String translateSplitParams(Map<String, String> queryString){
		String parameters = "";
		String parametersList = "";
		
		if(queryString != null && !queryString.isEmpty()){
					
			for(Map.Entry<String, String> entry : queryString.entrySet()){
				parameters =  parameters + splitParams(entry.getKey(), entry.getValue());
			}
		}
		
		if(!parameters.isEmpty()){
			int index=parameters.lastIndexOf("&");
			parametersList= parameters.substring(0, index);
		}
		
		return parametersList;
	}
	
	/**
	 * To split Parameters.
	 * @param param the String value of param.
	 * @param data the String value of data.
	 * @return String
	 */
	public String splitParams(String param,String data) {
		String paramName="";
		if(data != null){
			paramName = param+"="+data+"&";
		}
		return paramName;
	}
	
	/**
	 * Get organization by mnemonic
	 * @param orgMnemonic
	 * @param session
	 * @return Object
	 * @throws Exception
	 */
	@Cacheable(cacheName="getOrganizationByMnemonic", keyGenerator = @KeyGenerator(name="HashCodeCacheKeyGenerator"))
	public String getOrganizationId(@PartialCacheKey String orgMnemonic, HttpSession session) throws IOException{
		try {
			HttpEntity<String> entity = prepareGet(session);
			
			String params = "?mnemonic=" + orgMnemonic;
					
			ResponseEntity<Object> response = 
							restTemplate.exchange(getAPIBaseURL() 
							+ CommonConstants.ORGANIZATION_BASE_URL + params,
							HttpMethod.GET, entity, Object.class);
			
		/*//	OrganizationDTO organizationDTO = baseTranslator.convertOrganizationDTO(response.getBody());
			if (organizationDTO != null) {
				return organizationDTO.getId().toString();
			}*/
			return null;
		}catch (IOException e) {
			throw e;
		} catch (JsonSyntaxException e) {
			throw e;
		} catch (HttpClientErrorException e) {
			throw e;
		}
	}
	 /* Translate to RoleMenu.
	 * @param object The Object.
	 * @return pagePermissions {@link RoleMenu}
	 */
	public RoleMenu convertRoleMenu(Object object) {
		return baseTranslator.convertRoleMenu(object);
	}
	
	
	public String getServiceURL(){
	HttpServletRequest httpServletRequest = null;
	String ip = httpServletRequest.getContextPath();
	System.out.println("ip>>>>>>>>"+ip);
	int portNo = httpServletRequest.getLocalPort();
	System.out.println("portNo>>>>>>>>>>"+portNo);
	
	return ip+"/"+portNo;
	}
}
