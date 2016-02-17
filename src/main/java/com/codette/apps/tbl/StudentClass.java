package com.codette.apps.tbl;

public class StudentClass extends Base{
   
	 private Integer id;
	 private Integer idStudent;
	 private Integer idStandard;
	 private Integer idSection;
	 private Integer idYear;
	 private User user;
	 private Standard standard;
	 private Section section;
	 private Year year;
	 
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getIdStudent() {
		return idStudent;
	}
	public void setIdStudent(Integer idStudent) {
		this.idStudent = idStudent;
	}
	public Integer getIdStandard() {
		return idStandard;
	}
	public void setIdStandard(Integer idStandard) {
		this.idStandard = idStandard;
	}
	public Integer getIdSection() {
		return idSection;
	}
	public void setIdSection(Integer idSection) {
		this.idSection = idSection;
	}
	public Integer getIdYear() {
		return idYear;
	}
	public void setIdYear(Integer idYear) {
		this.idYear = idYear;
	}

	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
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
	public Year getYear() {
		return year;
	}
	public void setYear(Year year) {
		this.year = year;
	}
	 
}
