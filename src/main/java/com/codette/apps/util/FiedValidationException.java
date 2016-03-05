package com.codette.apps.util;

import org.apache.http.HttpStatus;
import org.springframework.web.client.HttpStatusCodeException;

public class FiedValidationException extends Exception {

	private String message;
	private HttpStatus status;
	public FiedValidationException(String msg){
		this.message = msg;
	}
	
	public FiedValidationException(String msg, HttpStatus status){
		this.message = msg;
		this.status = status;
	}
	
	public void FiedValidationException(){
		this.message = "Invalid input Json";
	}
	
	public HttpStatus getStatus(){
		return status;
	}
	
	@Override
	public String getMessage(){
	 return message;
	}
}
