package com.codette.apps.frontend.model;

import java.util.List;

public class EngagementQuestion {
	private String userName;
	private String company;
	private String connect;
	private Contact contact;
	private List<EngagementQuestions> engagementQuestions;
	private String linkedIn;
	private String twitter;
	private String description;
	private Invoice invoice;
	
	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * @return the company
	 */
	public String getCompany() {
		return company;
	}
	/**
	 * @param company the company to set
	 */
	public void setCompany(String company) {
		this.company = company;
	}
	/**
	 * @return the connect
	 */
	public String getConnect() {
		return connect;
	}
	/**
	 * @param connect the connect to set
	 */
	public void setConnect(String connect) {
		this.connect = connect;
	}
	/**
	 * @return the contact
	 */
	public Contact getContact() {
		return contact;
	}
	/**
	 * @param contact the contact to set
	 */
	public void setContact(Contact contact) {
		this.contact = contact;
	}
	/**
	 * @return the engagementQuestions
	 */
	public List<EngagementQuestions> getEngagementQuestions() {
		return engagementQuestions;
	}
	/**
	 * @param engagementQuestions the engagementQuestions to set
	 */
	public void setEngagementQuestions(List<EngagementQuestions> engagementQuestions) {
		this.engagementQuestions = engagementQuestions;
	}
	/**
	 * @return the linkedIn
	 */
	public String getLinkedIn() {
		return linkedIn;
	}
	/**
	 * @param linkedIn the linkedIn to set
	 */
	public void setLinkedIn(String linkedIn) {
		this.linkedIn = linkedIn;
	}
	/**
	 * @return the twitter
	 */
	public String getTwitter() {
		return twitter;
	}
	/**
	 * @param twitter the twitter to set
	 */
	public void setTwitter(String twitter) {
		this.twitter = twitter;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the invoice
	 */
	public Invoice getInvoice() {
		return invoice;
	}
	/**
	 * @param invoice the invoice to set
	 */
	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}
	

}
