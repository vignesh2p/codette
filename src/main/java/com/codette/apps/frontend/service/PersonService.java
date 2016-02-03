/**
 * 
 */
package com.codette.apps.frontend.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.codette.apps.frontend.model.EvaluationStatus;
import com.codette.apps.frontend.model.Person;
import com.codette.apps.frontend.model.PersonList;
import com.codette.apps.frontend.model.WorkerProfile;
import com.codette.apps.frontend.translator.PersonTranslator;

/**
 * @author bashelu
 *
 */
@Component
public class PersonService extends BaseService {

	@Resource
	PersonTranslator personTranslator;
	
	
	
	public WorkerProfile getPersonProfile() throws IOException {
		File file = new File("worker-profile.json");
		InputStream inputStream = null;
		WorkerProfile workerProfile = null;
		if (!file.exists()) {
			/* if not exists, reading file from appln file path */
			inputStream = this
					.getClass()
					.getClassLoader()
					.getResourceAsStream(
							"com/vernal/is/properties/worker-profile.json");
		} else {
			/* file exists, reading file */
			try {
				inputStream = new FileInputStream("worker-profile.json");
			} catch (FileNotFoundException exception) {
				// LOGGER.error("Error getting getApiConfig:",
				// exception.getMessage());
				throw exception;
			}
		}
		workerProfile = gson.fromJson(
				commonUtil.getStringFromInputStream(inputStream),
				WorkerProfile.class);
		return workerProfile;
	}
	
	public EvaluationStatus getEvaluationStatus() throws IOException {
		File file = new File("evaluation-status.json");
		InputStream inputStream = null;
		EvaluationStatus evaluationStatus = null;
		if (!file.exists()) {
			/* if not exists, reading file from appln file path */
			inputStream = this
					.getClass()
					.getClassLoader()
					.getResourceAsStream(
							"com/vernal/is/properties/evaluation-status.json");
		} else {
			/* file exists, reading file */
			try {
				inputStream = new FileInputStream("evaluation-status.json");
			} catch (FileNotFoundException exception) {
				// LOGGER.error("Error getting getApiConfig:",
				// exception.getMessage());
				throw exception;
			}
		}
		evaluationStatus = gson.fromJson(
				commonUtil.getStringFromInputStream(inputStream),
				EvaluationStatus.class);
		return evaluationStatus;
	}
	
	public PersonList getPersonList() throws IOException {
		File file = new File("person-list.json");
		InputStream inputStream = null;
		PersonList personList = null;
		if (!file.exists()) {
			/* if not exists, reading file from appln file path */
			inputStream = this
					.getClass()
					.getClassLoader()
					.getResourceAsStream(
							"com/vernal/is/properties/person-list.json");
		} else {
			/* file exists, reading file */
			try {
				inputStream = new FileInputStream("person-list.json");
			} catch (FileNotFoundException exception) {
				// LOGGER.error("Error getting getApiConfig:",
				// exception.getMessage());
				throw exception;
			}
		}
		personList = gson.fromJson(
				commonUtil.getStringFromInputStream(inputStream),
				PersonList.class);
		return personList;
	}

	
	}
