package com.codette.apps.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.codette.apps.dto.ClassesDTO;
import com.codette.apps.dto.ResponseBean;
import com.codette.apps.dto.StaffClassDTO;
import com.codette.apps.dto.StudentDTO;
public interface StaffDAO {


	public ResponseBean deleteStudent(Integer studentId,Integer phoneNumberId,Integer addressId, Integer accessId);

	public ResponseBean createStudent(StudentDTO studentDTO, Integer accessId) ;
	
	public ResponseBean updateStudent(StudentDTO studentDTO, Integer accessId);
	
	public ResponseBean updateAttendence(List<Integer> idStudents, Integer accessId);

	public StudentDTO getStudent(Integer studentId);

	public List<StudentDTO> getStudents(String role, Integer standardId, Integer sectionId);

	public List<StaffClassDTO> getClassList(Integer staffId);

	public List<ClassesDTO> getAllClassList();

	public List<StudentDTO> getAttendence(Integer staffId);

}
