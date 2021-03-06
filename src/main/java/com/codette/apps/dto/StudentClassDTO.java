package com.codette.apps.dto;

public class StudentClassDTO {
   
	 private Integer id;
	 private Integer idStudent;
	 private Integer idStandard;
	 private Integer idSection;
	 private Integer idYear;
	 private StudentDTO student;
	 private StandardDTO standard;
	 private SectionDTO section;
	 private YearDTO year;
	 
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
	public StudentDTO getStudent() {
		return student;
	}
	public void setStudent(StudentDTO student) {
		this.student = student;
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
	public YearDTO getYear() {
		return year;
	}
	public void setYear(YearDTO year) {
		this.year = year;
	}
	 
}
