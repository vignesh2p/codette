package com.codette.apps.dto;

public class ClassesDTO extends BaseDTO{

	private StandardDTO standard;
	private SectionDTO section;
	private Integer isAttendanceEnable;
	
	public StandardDTO getStandard() {
		return standard;
	}
	public void setStandard(StandardDTO standard) {
		this.standard = standard;
	}
	public SectionDTO getSection() {
		return section;
	}
	public Integer getIsAttendanceEnable() {
		return isAttendanceEnable;
	}
	public void setIsAttendanceEnable(Integer isAttendanceEnable) {
		this.isAttendanceEnable = isAttendanceEnable;
	}
	public void setSection(SectionDTO section) {
		this.section = section;
	}
	
	
	
}
