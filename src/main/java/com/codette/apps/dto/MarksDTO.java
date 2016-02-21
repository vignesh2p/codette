package com.codette.apps.dto;

public class MarksDTO extends BaseDTO{
   
	 private Integer idStudent;
	 private Integer idStaff;
	 private Integer idSubject;
	 private Integer idStandard;
	 private Integer idSection;
	 private Integer idExam;
	 private Integer idYear;
	 private UserDTO student;
	 private UserDTO staff;
	 private SubjectDTO subject;
	 private StandardDTO standard;
	 private SectionDTO section;
	 private Integer mark;
	 private ExamDTO exam;
	 private YearDTO year;
	
	
	public UserDTO getStudent() {
		return student;
	}
	public void setStudent(UserDTO student) {
		this.student = student;
	}
	public Integer getIdStudent() {
		return idStudent;
	}
	public void setIdStudent(Integer idStudent) {
		this.idStudent = idStudent;
	}
	public Integer getIdStaff() {
		return idStaff;
	}
	public void setIdStaff(Integer idStaff) {
		this.idStaff = idStaff;
	}
	public Integer getIdSubject() {
		return idSubject;
	}
	public void setIdSubject(Integer idSubject) {
		this.idSubject = idSubject;
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
	public Integer getIdExam() {
		return idExam;
	}
	public void setIdExam(Integer idExam) {
		this.idExam = idExam;
	}
	public Integer getIdYear() {
		return idYear;
	}
	public void setIdYear(Integer idYear) {
		this.idYear = idYear;
	}
	
	public UserDTO getStaff() {
		return staff;
	}
	public void setStaff(UserDTO staff) {
		this.staff = staff;
	}
	public SubjectDTO getSubject() {
		return subject;
	}
	public void setSubject(SubjectDTO subject) {
		this.subject = subject;
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
	public Integer getMark() {
		return mark;
	}
	public void setMark(Integer mark) {
		this.mark = mark;
	}
	public ExamDTO getExam() {
		return exam;
	}
	public void setExam(ExamDTO exam) {
		this.exam = exam;
	}
	public YearDTO getYear() {
		return year;
	}
	public void setYear(YearDTO year) {
		this.year = year;
	}
	 
	 
}
