package com.codette.apps.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import com.codette.apps.dto.AttendenceDTO;
import com.codette.apps.dto.ClassesDTO;
import com.codette.apps.dto.SectionDTO;
import com.codette.apps.dto.StandardDTO;
import com.codette.apps.dto.UserDTO;


@Component
public class AttendenceExtractor {

	public ResultSetExtractor<List<AttendenceDTO>> getAttendenceList() {
		return new ResultSetExtractor<List<AttendenceDTO>>(){

			public List<AttendenceDTO> extractData(ResultSet rs) throws SQLException,
					DataAccessException {

				List<AttendenceDTO> attendences = new ArrayList<AttendenceDTO>(); 
				while(rs.next()){		
					
					AttendenceDTO attencence = new AttendenceDTO();
					attencence.setIsAbsent(rs.getInt("IS_ABSENT"));
					
					
					
					UserDTO student = new UserDTO();
				student.setId(rs.getInt("ID")); 
				student.setFirstName(rs.getString("FIRST_NAME"));
				student.setLastName(rs.getString("LAST_NAME"));
				student.setDateOfBirth(rs.getString("DATE_OF_BIRTH").toString());
				student.setEmailAddresses(rs.getString("EMAIL_ADDRESS"));
				
				ClassesDTO classRoom = new ClassesDTO();	
				
					classRoom.setIsAttendanceEnable(rs.getInt("IS_ATTENDENCE_ENABLE"));
					StandardDTO standard = new StandardDTO();
					standard.setId(rs.getInt("ID_STANDARD"));
					standard.setStandard(rs.getString("STANDARD"));
					classRoom.setStandard(standard);
					
					SectionDTO section = new SectionDTO();
					section.setId(rs.getInt("ID_SECTION"));
					section.setSection(rs.getString("SECTION"));
					classRoom.setSection(section);
					
			   student.setClassRoom(classRoom);
				     
				     
					attencence.setUser(student); 
					attendences.add(attencence);
			    }
			     return attendences;
			}
		};
	}

}
