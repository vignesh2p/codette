package com.codette.apps.frontend.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;



import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.codette.apps.frontend.model.Exam;
import com.codette.apps.frontend.service.ExamService;

@Controller
@RequestMapping("/exam")
public class ExamController extends BaseController {
	
	
	@Resource
	ExamService examService;

	@RequestMapping("/list")
	public ResponseEntity<?> getExamsList(HttpSession session){
		List<Exam> examList = examService.getExamsList(session);
		return new ResponseEntity<List<Exam>>(examList, HttpStatus.OK);
	}
	
	@RequestMapping("/create")
	public ResponseEntity<?> createExams(@RequestBody Exam exam, HttpSession session){
		Object object = examService.createExam(exam, session);
		return new ResponseEntity<Object>(object, HttpStatus.OK);
	}
	
}
