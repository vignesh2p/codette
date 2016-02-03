package com.codette.apps.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.codette.apps.dto.StaffAddressDTO;
import com.codette.apps.dto.StudentAddressDTO;
import com.codette.apps.dto.StudentRelationDTO;

public class StudentAddressResultsetExtractor implements ResultSetExtractor {

	@Override
	public Object extractData(ResultSet rs) throws SQLException,
			DataAccessException {
		
		StudentAddressDTO studentAddressDTO = new StudentAddressDTO();
		studentAddressDTO.setId(rs.getInt("ID"));
		StudentRelationDTO relation = new StudentRelationDTO();
		relation.setId(rs.getInt("ID_STUDENT_RELATION"));
		relation.setRelation(rs.getString("STUDENT_RELATION"));
		studentAddressDTO.setAddress(rs.getString("ADDRESS"));
		studentAddressDTO.setIsPrimary(rs.getInt("IS_PRIMARY"));
		return studentAddressDTO;
		}
		
	}


