package com.codette.apps.frontend.translator;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.codette.apps.frontend.model.RoleMenu;
import com.codette.apps.frontend.service.BaseService;
import com.codette.apps.util.CommonConstants;
import com.codette.apps.util.CommonUtil;


@Component
public class BaseTranslator {
	
	public static final Gson gson = new GsonBuilder().setDateFormat(CommonConstants.ISO_DATE_FORMAT).create();
	
	@Resource
	public BaseService baseService;
	
	@Resource
	CommonUtil commonUtil;
	
	/**
	 * Get Organization id from Session 
	 * @param session
	 * @return {@link String}
	 * @throws Exception
	 */
	public String getOrganizationId(HttpSession session){
		String organizationId = null;
		if(session.getAttribute(CommonConstants.SESSION_ORG_ID) != null)
			organizationId = session.getAttribute(CommonConstants.SESSION_ORG_ID).toString();
		return organizationId;
	}
	

	
	/**
	 * Translate to I18nStringMap
	 * @param value the String value of value.
	 * @param locale the Locale of URI.
	 * @return Map<String, String>
	 */
	public Map<String, String> translateToI18nStringMap(String value, String locale) {
		Map<String, String> map = new HashMap<String, String>();
		map.put(locale, value);
		return map;
	}
	
	/**
	 * Translate to Currency Map.
	 * @param code the String value of code.
	 * @param price the String value of price.
	 * @return Map<String, String>.
	 */
	public Map<String, String> translateToCurrencyMap(String code, String price) {
		Map<String, String> map = new HashMap<String, String>();
		map.put(code, price);
		return map;
	}
	
	/**
	 * Translate Object to Json.
	 * @param obj a Object.
	 * @return String.
	 */
	public String translateObjectToJson(Object obj) {
		return gson.toJson(obj);
	}
	
	
	
	 /**Translate to RoleMenu.
	 * @param object The Object.
	 * @return menuOptions {@link RoleMenu}
	 */
	public RoleMenu convertRoleMenu(Object object) {
		RoleMenu menuOptions = gson.fromJson(object.toString(), RoleMenu.class);
		return menuOptions;
	}
 }
