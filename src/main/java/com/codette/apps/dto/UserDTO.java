package com.codette.apps.dto;


import java.util.List;



public class UserDTO extends BaseDTO {

	private OrganizationDTO organizationDTO;
	
	private RoleDTO role;
	
	private String registrationNumber;
	
    private String firstName;

    private String lastName;

    private String dateOfBirth;
    
    private String  emailAddresses;
    
    private Integer experience;
    
    private String  bioGraphy;
      
    private  String  dateOfJoining;
    
	private ClassesDTO classRoom;
	
	private DesignationDTO  designation;
	
    private GenderDTO gender;
    
	private YearDTO year;
	
	private String fatherName;
	
	private String motherName;
	
	private Integer age;
	
	private Integer idImage;
	
	private String qualification;
	
	private BloodGroupDTO bloodGroup;
    
    private CommunityDTO community;
    
    private ReligionDTO religion;
     
    private List<AddressDTO> addresses;
   
    private List<PhoneNumberDTO> phoneNumbers;

	public RoleDTO getRole() {
		return role;
	}

	public void setRole(RoleDTO role) {
		this.role = role;
	}

	public String getRegistrationNumber() {
		return registrationNumber;
	}

	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getEmailAddresses() {
		return emailAddresses;
	}

	public void setEmailAddresses(String emailAddresses) {
		this.emailAddresses = emailAddresses;
	}

	public Integer getExperience() {
		return experience;
	}

	public void setExperience(Integer experience) {
		this.experience = experience;
	}

	public String getBioGraphy() {
		return bioGraphy;
	}

	public void setBioGraphy(String bioGraphy) {
		this.bioGraphy = bioGraphy;
	}

	public String getDateOfJoining() {
		return dateOfJoining;
	}

	public void setDateOfJoining(String dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}


	public ClassesDTO getClassRoom() {
		return classRoom;
	}

	public void setClassRoom(ClassesDTO classRoom) {
		this.classRoom = classRoom;
	}

	public DesignationDTO getDesignation() {
		return designation;
	}

	public void setDesignation(DesignationDTO designation) {
		this.designation = designation;
	}

	public GenderDTO getGender() {
		return gender;
	}

	public void setGender(GenderDTO gender) {
		this.gender = gender;
	}

	public YearDTO getYear() {
		return year;
	}

	public void setYear(YearDTO year) {
		this.year = year;
	}

	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public String getMotherName() {
		return motherName;
	}

	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Integer getIdImage() {
		return idImage;
	}

	public void setIdImage(Integer idImage) {
		this.idImage = idImage;
	}

	public BloodGroupDTO getBloodGroup() {
		return bloodGroup;
	}

	public void setBloodGroup(BloodGroupDTO bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	public CommunityDTO getCommunity() {
		return community;
	}

	public void setCommunity(CommunityDTO community) {
		this.community = community;
	}

	public ReligionDTO getReligion() {
		return religion;
	}

	public void setReligion(ReligionDTO religion) {
		this.religion = religion;
	}

	public List<AddressDTO> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<AddressDTO> addresses) {
		this.addresses = addresses;
	}

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public List<PhoneNumberDTO> getPhoneNumbers() {
		return phoneNumbers;
	}

	public void setPhoneNumbers(List<PhoneNumberDTO> phoneNumbers) {
		this.phoneNumbers = phoneNumbers;
	}

	public OrganizationDTO getOrganizationDTO() {
		return organizationDTO;
	}

	public void setOrganizationDTO(OrganizationDTO organizationDTO) {
		this.organizationDTO = organizationDTO;
	}


 

}