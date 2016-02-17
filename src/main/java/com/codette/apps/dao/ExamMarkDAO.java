package com.codette.apps.dao;

import java.util.List;

import com.codette.apps.dto.ExamDTO;
import com.codette.apps.dto.MarkSheetDTO;
import com.codette.apps.dto.ResponseBean;
import com.codette.apps.tbl.Exam;

public interface ExamMarkDAO {

	public Object createExam(ExamDTO exam, Integer orgId, Integer userId, Integer accessId);
	
	public Object getMarkSheet(Integer orgId, Integer userId, String role);

}
