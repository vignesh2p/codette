package com.codette.apps.frontend.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.codette.apps.frontend.model.Subject;
import com.codette.apps.util.CommonConstants;
import com.codette.apps.util.CommonUtil;

@Component
public class ClientService {

	@Resource
	CommonUtil commonUtil;
	public static final Gson gson = new GsonBuilder().setDateFormat(CommonConstants.ISO_DATE_FORMAT).create();
	
	@Resource
	PersonService personService;
	
	public Subject getClientList() throws JsonSyntaxException, IOException{
		File file = new File("clientList.json");
		InputStream inputStream = null;
		Subject client =null;
		if (!file.exists()) {
			/* if not exists, reading file from appln file path */
			inputStream =  this
					.getClass()
					.getClassLoader()
					.getResourceAsStream(
							"com/vernal/is/properties/clientList.json");
		} else {
			/* file exists, reading file */
			try {
				inputStream = new FileInputStream("clientList.json");
			} catch (FileNotFoundException exception) {
				//LOGGER.error("Error getting Evaluations from json file:", exception.getMessage());
				throw exception;
			}
		}
		client = gson.fromJson(commonUtil.getStringFromInputStream(inputStream), Subject.class);
		
		return client;
	}
	
}
