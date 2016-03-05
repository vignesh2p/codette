package com.codette.apps.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import com.codette.apps.dto.AttendenceDTO;
import com.codette.apps.dto.UserDTO;
import com.codette.apps.dto.ClassesDTO;
import com.codette.apps.dto.SectionDTO;
import com.codette.apps.dto.StandardDTO;
import com.codette.apps.dto.UserDTO;
import com.codette.apps.frontend.model.Student;


@Component
public class AttendenceExtractor {

	public ResultSetExtractor<List<UserDTO>> getStudentList() {
		return new ResultSetExtractor<List<UserDTO>>(){

			public List<UserDTO> extractData(ResultSet rs) throws SQLException,
					DataAccessException {

				List<UserDTO> students = new ArrayList<UserDTO>(); 
				UserDTO student = null;
				AttendenceDTO attendance = null;
				while(rs.next()){		
					    student = new UserDTO();
						student.setId(rs.getInt("ID_USER")); 
						student.setFirstName(rs.getString("FIRST_NAME"));
						student.setLastName(rs.getString("LAST_NAME"));
						student.setDateOfBirth(rs.getString("DATE_OF_BIRTH").toString());
						student.setEmailAddresses(rs.getString("EMAIL_ADDRESS"));
						 attendance = new AttendenceDTO();
						 attendance.setIsAbsent(rs.getInt("IS_ABSENT"));
					    student.setAttendance(attendance);
					students.add(student);
			    }
			     return students;
			}
		};
	}

	public ResultSetExtractor<ClassesDTO> getClassAttendance( final List<UserDTO> students) {
		return new ResultSetExtractor<ClassesDTO>(){

			public ClassesDTO extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				
				   ClassesDTO classRoom = new ClassesDTO();	
				if(rs.next()){		
							classRoom.setIsAttendanceEnable(rs.getInt("IS_ATTENDANCE_ENABLE"));
							
							classRoom.setUsers(students);
			    }
			     return classRoom;
			}
		};
	}

}
