package com.codette.apps.dto;


public class StaffClassDTO extends BaseDTO{
    private Integer id;
    private Integer idYear;
    private UserDTO staff;
    private Integer isClassTeacher; 
    private StandardDTO standard;
    private SectionDTO section;
    private SubjectDTO subject;
    private YearDTO year;
    
    
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public UserDTO getStaff() {
		return staff;
	}
	public void setStaff(UserDTO staff) {
		this.staff = staff;
	}
	

	public Integer getIdYear() {
		return idYear;
	}
	public void setIdYear(Integer idYear) {
		this.idYear = idYear;
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
