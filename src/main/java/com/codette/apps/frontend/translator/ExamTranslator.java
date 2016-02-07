package com.codette.apps.frontend.translator;

import java.lang.reflect.Type;
import java.util.List;

import org.springframework.stereotype.Component;

import com.codette.apps.dto.ExamDTO;
import com.codette.apps.frontend.model.Exam;
import com.google.gson.reflect.TypeToken;

@Component
public class ExamTranslator extends BaseTranslator{

	/**
	 * Converts to ExamDTO list
	 * @param object
	 * @return
	 */
	public List<ExamDTO> convertToListofExamDTOs(Object object){
		Type listType = new TypeToken<List<ExamDTO>>() {}.getType();
		List<ExamDTO> examDTOList = gson.fromJson(translateObjectToJson(object), listType);
		return examDTOList;
	}

	/**
	 * 
	 * @param examDTOList
	 * @return
	 */
	public List<Exam> translateToExamList(List<ExamDTO> examDTOList) {
		return null;
	}

	public ExamDTO translateToExamDTO(Exam exam) {

		return null;
	}
}
