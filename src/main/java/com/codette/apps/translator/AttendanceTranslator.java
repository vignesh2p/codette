package com.codette.apps.translator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.codette.apps.dto.AttendenceDTO;
import com.codette.apps.tbl.Attendence;

@Component
public class AttendanceTranslator {

	public List<AttendenceDTO> translateAttendencedtoToAttendence(
			List<Attendence> attendence) {
		// TODO Auto-generated method stu
		List<AttendenceDTO> attendenceDto = new ArrayList<AttendenceDTO>();
		return attendenceDto;
	}

}
