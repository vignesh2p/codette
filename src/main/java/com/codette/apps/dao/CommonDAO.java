package com.codette.apps.dao;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public interface CommonDAO {

	public Object getCommunity();
	
	public Object getReligion();

    public Object getDesignation(Integer orgId);

	public Object getId(String entity, String type);

	public Object getStandard(Integer orgId);

	public Object getSection(Integer orgId);

	public Integer getAcademinYearId(Date date);

}
