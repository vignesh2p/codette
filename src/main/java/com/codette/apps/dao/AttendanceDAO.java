package com.codette.apps.dao;

import java.util.List;

import com.codette.apps.dto.AttendenceDTO;
import com.codette.apps.dto.ResponseBean;

public interface AttendanceDAO {

	ResponseBean enableAttendance(Integer orgId, Integer userId,
			Integer accessId);

	List<AttendenceDTO> getAttendance(Integer orgId, Integer userId);

	ResponseBean updateAttendance(Integer orgId, List<Integer> userIds,
			Integer accessId);

}
