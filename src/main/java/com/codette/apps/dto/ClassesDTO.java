package com.codette.apps.dto;

public class ClassesDTO extends BaseDTO{

	private Integer id;
	private StandardDTO standard;
	private SectionDTO section;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public StandardDTO getStandard() {
		return standard;
	}
	public void setStandard(StandardDTO standard) {
		this.standard = standard;
	}
	public SectionDTO getSection() {
		return section;
	}
	public void setSection(SectionDTO section) {
		this.section = section;
	}
	
	
}
