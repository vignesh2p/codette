package com.codette.apps.dao;


public interface ClassRoomDAO {

	public Object getClassList(Integer orgId, Integer userId, String role);


	public Object createNewClassRoom(Integer orgId,Integer standardId, Integer sectionId,
			Integer userId, Integer accessId);

}
