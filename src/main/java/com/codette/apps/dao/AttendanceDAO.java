package com.codette.apps.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.codette.apps.dto.AttendenceDTO;
import com.codette.apps.dto.ResponseBean;

@Component
public interface AttendanceDAO {

	public Object enableAttendance(Integer orgId, Integer userId,
			Integer accessId);

	public Object getAttendance(Integer orgId, Integer userId);

	public Object updateAttendance(Integer orgId, List<Integer> userIds,
			Integer accessId);

	public Object createAttendanceProfile(Integer orgId, Integer userId,
			Integer accessId);

}
