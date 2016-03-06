package com.codette.apps.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import com.codette.apps.dto.ClassesDTO;
import com.codette.apps.dto.SectionDTO;
import com.codette.apps.dto.StaffClassDTO;
import com.codette.apps.dto.StandardDTO;
import com.codette.apps.dto.UserDTO;


@Component
public class ClassRoomExtractor {
	
	public ResultSetExtractor<List<StaffClassDTO>> getHandlingClassesList(){
	
		return new ResultSetExtractor<List<StaffClassDTO>>(){

			public List<StaffClassDTO> extractData(ResultSet rs) throws SQLException,
					DataAccessException {

				List<StaffClassDTO> classes = new ArrayList<StaffClassDTO>();
				StaffClassDTO clases = null;
				UserDTO user = null;
				while(rs.next()){
					
				clases = new StaffClassDTO();
				user = new UserDTO();
				clases.setId(rs.getInt("ID_CLASS"));
				user.setId(rs.getInt("ID_USER"));
			    clases.setUser(user);
				clases.setIsClassTeacher(rs.getInt("IS_CLASS_TEACHER"));
				ClassesDTO classesDTO = new ClassesDTO();
				StandardDTO standard = new StandardDTO();
				standard.setId(rs.getInt("ID_STANDARD"));
				standard.setStandard(rs.getString("STANDARD"));
				classesDTO.setStandard(standard);
				SectionDTO section = new SectionDTO();
				section.setId(rs.getInt("ID_SECTION"));
				section.setSection(rs.getString("SECTION"));
				classesDTO.setSection(section);
                clases.setClassRoom(classesDTO);
					
					classes.add(clases);
				     
			}
			     return classes;

	      }
	      
	};
		
}

public ResultSetExtractor<List<ClassesDTO>> getAllClassList() {
		return new ResultSetExtractor<List<ClassesDTO>>(){

			@Override
			public List<ClassesDTO> extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				ClassesDTO clases = null;
				List<ClassesDTO> classes = new ArrayList<ClassesDTO>(); 
				while(rs.next()){
					
				 clases = new ClassesDTO();
				clases.setId(rs.getInt("ID"));
				StandardDTO standard = new StandardDTO();
				standard.setId(rs.getInt("ID_STANDARD"));
				standard.setStandard(rs.getString("STANDARD"));
				clases.setStandard(standard);
				SectionDTO section = new SectionDTO();
				section.setId(rs.getInt("ID_SECTION"));
				section.setSection(rs.getString("SECTION"));
				clases.setSection(section);
					
					classes.add(clases);
				     
			}
			     return classes;
			}
		};
	}
}
		