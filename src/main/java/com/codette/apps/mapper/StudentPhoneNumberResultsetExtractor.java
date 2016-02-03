package com.codette.apps.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import com.codette.apps.dto.StudentPhoneNumberDTO;
import com.codette.apps.dto.StudentRelationDTO;

public class StudentPhoneNumberResultsetExtractor implements ResultSetExtractor {

	@Override
	public Object extractData(ResultSet rs) throws SQLException,
			DataAccessException {
		StudentPhoneNumberDTO studentphoneDTO = new StudentPhoneNumberDTO();
		studentphoneDTO.setId(rs.getInt("ID"));
		StudentRelationDTO relation = new StudentRelationDTO();
		relation.setId(rs.getInt("ID_STUDENT_RELATION"));
		relation.setRelation(rs.getString("STUDENT_RELATION"));
		studentphoneDTO.setPhoneNumber(String.valueOf(rs.getInt("PHONE_NUMBER")));
		studentphoneDTO.setIsPrimary(rs.getInt("IS_PRIMARY"));
		return studentphoneDTO;
		
	}

}
