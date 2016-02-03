package com.codette.apps.frontend.model;

import java.util.List;

public class Client {
	private String count;
	private String imageUrl;
	private List<User> clientUsersList;
	
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String name) {
		this.imageUrl = name;
	}
	public List<User> getClientUsersList() {
		return clientUsersList;
	}
	public void setClientUsersList(List<User> clientUsersList) {
		this.clientUsersList = clientUsersList;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	
	
}
