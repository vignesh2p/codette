package com.codette.apps.frontend.model;

import java.util.List;

public class Class {

	private Section Section;
	private Staff classTeacher;
	private Standard standard;
	private Integer count;
	private List<Student> students;
	private List<Staff> staffsList;
	
	
	public Staff getClassTeacher() {
		return classTeacher;
	}
	public List<Staff> getStaffsList() {
		return staffsList;
	}
	public void setStaffsList(List<Staff> staffsList) {
		this.staffsList = staffsList;
	}
	public void setClassTeacher(Staff classTeacher) {
		this.classTeacher = classTeacher;
	}
	public Standard getStandard() {
		return standard;
	}
	public void setStandard(Standard standard) {
		this.standard = standard;
	}
	public Section getSection() {
		return Section;
	}
	public void setSection(Section section) {
		Section = section;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public List<Student> getStudents() {
		return students;
	}
	public void setStudents(List<Student> students) {
		this.students = students;
	}
	

}
