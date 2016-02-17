package com.codette.apps.tbl;


public class StaffClass extends Base{
    private Integer id;
    private Integer idYear;
    private User staff;
    private Integer isClassTeacher; 
    private Standard standard;
    private Section section;
    private Subject subject;
    private Year year;
    
    
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public User getStaff() {
		return staff;
	}
	public void setStaff(User staff) {
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
	public Standard getStandard() {
		return standard;
	}
	public void setStandard(Standard standard) {
		this.standard = standard;
	}
	public Section getSection() {
		return section;
	}
	public void setSection(Section section) {
		this.section = section;
	}
	public Subject getSubject() {
		return subject;
	}
	public void setSubject(Subject subject) {
		this.subject = subject;
	}
	public Year getYear() {
		return year;
	}
	public void setYear(Year year) {
		this.year = year;
	}
    
    
    
}
