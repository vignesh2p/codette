package com.codette.apps.frontend.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
@JsonInclude(value=Include.NON_EMPTY)
public class Organization {

	private String mnemonic;
	private String orgName;
	private String id;
	private String orgType;
	private List<Staff> staffList;
	private List<Address> addresses;
	private List<EmailAddress> emailAddresses;
    private List<PhoneNumber> phoneNumbers;
	private List<RelatedOrganization> relatedOrganizations;
	private List<Person> persons;
	private List<DropDownValue> relationships;
	private List<DropDownValue> countryList;
	private List<Asset> assets;
	private List<DropDownValue> orgTypeRT;
	private List<DropDownValue> addressTypeRT;
	private List<DropDownValue> phoneTypeRT;
	private List<DropDownValue> emailTypeRT;	
	private List<DropDownValue> stateList;
	private List<DropDownValue> userTypeRT;
	private List<DropDownValue> userGerderRT;
	private String[] fileStorageId;
	private List<DropDownValue> relationshipTypeRT;
	private String relationshipType;
	private List<Alias> alias;
	
	/**
	 * @return
	 */
	public String getMnemonic() {
		return mnemonic;
	}
	/**
	 * @param mnemonic
	 */
	public void setMnemonic(String mnemonic) {
		this.mnemonic = mnemonic;
	}
	/**
	 * @return
	 */
	public String getOrgName() {
		return orgName;
	}
	/**
	 * @param orgName
	 */
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	/**
	 * @return
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the orgType
	 */
	public String getOrgType() {
		return orgType;
	}
	/**
	 * @param orgType the orgType to set
	 */
	public void setOrgType(String orgType) {
		this.orgType = orgType;
	}
	/**
	 * @return the addresses
	 */
	public List<Address> getAddresses() {
		return addresses;
	}
	/**
	 * @param addresses the addresses to set
	 */
	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}
	/**
	 * @return the emailAddresses
	 */
	public List<EmailAddress> getEmailAddresses() {
		return emailAddresses;
	}
	/**
	 * @param emailAddresses the emailAddresses to set
	 */
	public void setEmailAddresses(List<EmailAddress> emailAddresses) {
		this.emailAddresses = emailAddresses;
	}
	/**
	 * @return the phoneNumbers
	 */
	public List<PhoneNumber> getPhoneNumbers() {
		return phoneNumbers;
	}
	/**
	 * @param phoneNumbers the phoneNumbers to set
	 */
	public void setPhoneNumbers(List<PhoneNumber> phoneNumbers) {
		this.phoneNumbers = phoneNumbers;
	}
	/**
	 * @return the relatedOrganization
	 */
	public List<RelatedOrganization> getRelatedOrganizations() {
		return relatedOrganizations;
	}
	/**
	 * @param relatedOrganization the relatedOrganization to set
	 */
	public void setRelatedOrganizations(List<RelatedOrganization> relatedOrganizations) {
		this.relatedOrganizations = relatedOrganizations;
	}
	/**
	 * @return the accountLink
	 */
	/**
	 * @return the persons
	 */
	public List<Person> getPersons() {
		return persons;
	}
	/**
	 * @param persons the persons to set
	 */
	public void setPersons(List<Person> persons) {
		this.persons = persons;
	}
	/**
	 * @return the relationships
	 */
	public List<DropDownValue> getRelationships() {
		return relationships;
	}
	/**
	 * @param relationships the relationships to set
	 */
	public void setRelationships(List<DropDownValue> relationships) {
		this.relationships = relationships;
	}
	/**
	 * @return the countryList
	 */
	public List<DropDownValue> getCountryList() {
		return countryList;
	}
	/**
	 * @param countryList the countryList to set
	 */
	public void setCountryList(List<DropDownValue> countryList) {
		this.countryList = countryList;
	}

	/**
	 * @return the assets
	 */
	public List<Asset> getAssets() {
		return assets;
	}
	/**
	 * @param assets the assets to set
	 */
	public void setAssets(List<Asset> assets) {
		this.assets = assets;
	}

	public List<DropDownValue> getOrgTypeRT() {
		return orgTypeRT;
	}
	public void setOrgTypeRT(List<DropDownValue> orgTypeRT) {
		this.orgTypeRT = orgTypeRT;
	}
	public List<DropDownValue> getAddressTypeRT() {
		return addressTypeRT;
	}
	public void setAddressTypeRT(List<DropDownValue> addressTypeRT) {
		this.addressTypeRT = addressTypeRT;
	}
	public List<DropDownValue> getPhoneTypeRT() {
		return phoneTypeRT;
	}
	public void setPhoneTypeRT(List<DropDownValue> phoneTypeRT) {
		this.phoneTypeRT = phoneTypeRT;
	}
	public List<DropDownValue> getEmailTypeRT() {
		return emailTypeRT;
	}
	public void setEmailTypeRT(List<DropDownValue> emailTypeRT) {
		this.emailTypeRT = emailTypeRT;
	}
	/**
	 * @return the stateList
	 */
	public List<DropDownValue> getStateList() {
		return stateList;
	}
	/**
	 * @param stateList the stateList to set
	 */
	public void setStateList(List<DropDownValue> stateList) {
		this.stateList = stateList;
	}
	
	public List<DropDownValue> getUserTypeRT() {
		return userTypeRT;
	}
	public void setUserTypeRT(List<DropDownValue> userTypeRT) {
		this.userTypeRT = userTypeRT;
	}
	public List<DropDownValue> getUserGerderRT() {
		return userGerderRT;
	}
	public void setUserGerderRT(List<DropDownValue> userGerderRT) {
		this.userGerderRT = userGerderRT;
	}
	/**
	 * @return the fileStorageId
	 */
	public String[] getFileStorageId() {
		return fileStorageId;
	}
	/**
	 * @param fileStorageId the fileStorageId to set
	 */
	public void setFileStorageId(String[] fileStorageId) {
		this.fileStorageId = fileStorageId;
	}
	/**
	 * @return the relationshipTypeRT
	 */
	public List<DropDownValue> getRelationshipTypeRT() {
		return relationshipTypeRT;
	}
	/**
	 * @param relationshipTypeRT the relationshipTypeRT to set
	 */
	public void setRelationshipTypeRT(List<DropDownValue> relationshipTypeRT) {
		this.relationshipTypeRT = relationshipTypeRT;
	}
	/**
	 * @return the relationshipType
	 */
	public String getRelationshipType() {
		return relationshipType;
	}
	/**
	 * @param relationshipType the relationshipType to set
	 */
	public void setRelationshipType(String relationshipType) {
		this.relationshipType = relationshipType;
	}
	/**
	 * @return the alias
	 */
	public List<Alias> getAlias() {
		return alias;
	}
	/**
	 * @param alias the alias to set
	 */
	public void setAlias(List<Alias> alias) {
		this.alias = alias;
	}
	public List<Staff> getStaffList() {
		return staffList;
	}
	public void setStaffList(List<Staff> staffList) {
		this.staffList = staffList;
	}

}
