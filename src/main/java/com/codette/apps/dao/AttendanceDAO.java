package com.codette.apps.dao;

import java.util.List;

import com.codette.apps.dto.AttendenceDTO;
import com.codette.apps.dto.ResponseBean;

public interface AttendanceDAO {

	public Object enableAttendance(Integer orgId, Integer userId,
			Integer accessId);

	public Object getAttendance(Integer orgId, Integer userId);

	public Object updateAttendance(Integer orgId, List<Integer> userIds,
			Integer accessId);

	public Object createAttendanceProfile(Integer orgId, Integer userId,
			Integer accessId);

}
