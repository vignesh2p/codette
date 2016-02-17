package com.codette.apps.tbl;

public class Marks {
   
	 private Integer id;
	 private Integer idStudent;
	 private Integer idStaff;
	 private Integer idSubject;
	 private Integer idStandard;
	 private Integer idSection;
	 private Integer idExam;
	 private Integer idYear;
	 private User student;
	 private User staff;
	 private Subject subject;
	 private Standard standard;
	 private Section section;
	 private Integer mark;
	 private Exam exam;
	 private Year year;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public User getStudent() {
		return student;
	}
	public void setStudent(User student) {
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
	
	public User getStaff() {
		return staff;
	}
	public void setStaff(User staff) {
		this.staff = staff;
	}
	public Subject getSubject() {
		return subject;
	}
	public void setSubject(Subject subject) {
		this.subject = subject;
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
	public Integer getMark() {
		return mark;
	}
	public void setMark(Integer mark) {
		this.mark = mark;
	}
	public Exam getExam() {
		return exam;
	}
	public void setExam(Exam exam) {
		this.exam = exam;
	}
	public Year getYear() {
		return year;
	}
	public void setYear(Year year) {
		this.year = year;
	}
	 
	 
}
