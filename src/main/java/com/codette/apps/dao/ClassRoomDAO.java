package com.codette.apps.dao;

import java.util.List;

import com.codette.apps.dto.ClassesDTO;
import com.codette.apps.dto.StaffClassDTO;

public interface ClassRoomDAO {

	List<StaffClassDTO> getClassList(Integer orgId, Integer userId, String role);

	List<ClassesDTO> getAllClassList(Integer orgId);

}
