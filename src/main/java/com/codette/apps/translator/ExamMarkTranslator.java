package com.codette.apps.translator;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.codette.apps.dto.ExamDTO;
import com.codette.apps.dto.StandardDTO;
import com.codette.apps.tbl.Exam;
import com.codette.apps.tbl.MarkSheet;
import com.codette.apps.util.CommonConstants;
import com.codette.apps.util.CommonUtil;

public class ExamMarkTranslator {
	@Resource
	CommonUtil commonUtil;

	public Exam translateExamdtoToexam(ExamDTO examDto,Integer orgId) throws ParseException {
		Exam exam = new Exam();
		exam.setExam(examDto.getExam());
		exam.setIsDeleted(CommonConstants.NOT_DELETED);
		exam.setOrgId(orgId);
		return exam;
		
	}
	

	public List<MarkSheet> translateExamdtoToMarksheet(List<StandardDTO> standardDtos,Integer orgId) {
		List<MarkSheet> markSheets = new ArrayList<MarkSheet>();
		
		for(StandardDTO standardDto: standardDtos){
			MarkSheet markSheet = new MarkSheet();
			markSheet.setOrgId(orgId);
			markSheet.setIdStandard(standardDto.getId());
			markSheet.setIsDeleted(CommonConstants.NOT_DELETED);
			markSheets.add(markSheet);
		}
		return markSheets;
		
	}

}
