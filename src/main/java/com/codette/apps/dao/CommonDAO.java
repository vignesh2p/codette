package com.codette.apps.dao;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public interface CommonDAO {

	public Object getCommunity(Integer orgId);
	
	public Object getReligion(Integer orgId);

    public Object getDesignation(Integer orgId);

	public Object getId(String entity, String type, Integer orgId);

	public Object getStandard(Integer orgId);

	public Object getSection(Integer orgId);

	public Object getAcademinYearId(Date date, Integer orgId);

	public Object getSubject(Integer orgId);

}
