package com.codette.apps.dto;


public class StaffClassDTO extends BaseDTO{
    private UserDTO user;
    private Integer isClassTeacher; 
    private StandardDTO standard;
    private SectionDTO section;
    private SubjectDTO subject;
    private YearDTO year;
    
    

	public UserDTO getUser() {
		return user;
	}
	public void setUser(UserDTO user) {
		this.user = user;
	}
	public Integer getIsClassTeacher() {
		return isClassTeacher;
	}
	public void setIsClassTeacher(Integer isClassTeacher) {
		this.isClassTeacher = isClassTeacher;
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
	public SubjectDTO getSubject() {
		return subject;
	}
	public void setSubject(SubjectDTO subject) {
		this.subject = subject;
	}
	public YearDTO getYear() {
		return year;
	}
	public void setYear(YearDTO year) {
		this.year = year;
	}
    
    
    
}
