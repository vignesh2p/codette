package com.codette.apps.frontend.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;

import com.codette.apps.dto.ExamDTO;
import com.codette.apps.frontend.model.Exam;
import com.codette.apps.frontend.translator.ExamTranslator;
import com.codette.apps.util.CommonConstants;

@Component
public class ExamService extends BaseService {

	@Autowired
	ExamTranslator examTranslator;

	/**
	 * 
	 * @param session
	 * @return
	 */
	public List<Exam> getExamsList(HttpSession session){
		List<Exam> examList = null;
		HttpEntity<String> requestEntity;
		try {
			requestEntity = prepareGet(session);

			ResponseEntity<Object> response = restTemplate.exchange( getAPIBaseURL()
							+ CommonConstants.EXAMS_BASE_URL, HttpMethod.GET, requestEntity, Object.class);
	
			List<ExamDTO> examDTOList = examTranslator.convertToListofExamDTOs(response.getBody());
			examList = examTranslator.translateToExamList(examDTOList);
		} catch (IOException e) {
			e.printStackTrace();
		}  catch (RestClientException e) {
			e.printStackTrace();
		}
		return examList;
	}

	/**
	 * 
	 * @param exam
	 * @param session
	 * @return
	 */
	public Object createExam(Exam exam, HttpSession session) {
		ExamDTO examDTO = examTranslator.translateToExamDTO(exam);
		HttpEntity<String> requestEntity;
		String postString = gson.toJson(examDTO);
		try{
		requestEntity = preparePost(postString, session);
		ResponseEntity<Object> response = restTemplate.exchange( getAPIBaseURL() + CommonConstants.EXAMS_BASE_URL
				       +CommonConstants.CREATE_EXAM, HttpMethod.POST, requestEntity, Object.class);
		
		return response.getStatusCode();
		} catch (IOException e) {
			e.printStackTrace();
		}  catch (RestClientException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
