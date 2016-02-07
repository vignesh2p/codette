package com.codette.apps.dao;

import java.util.List;

import com.codette.apps.dto.ExamDTO;
import com.codette.apps.dto.MarkSheetDTO;
import com.codette.apps.dto.ResponseBean;

public interface ExamMarkDAO {

	public ResponseBean createExam(ExamDTO exam, Integer userId);

	public List<MarkSheetDTO> getMarkSheet(Integer userId);

}
