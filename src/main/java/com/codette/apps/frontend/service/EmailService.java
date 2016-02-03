package com.codette.apps.frontend.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.codette.apps.util.CommonConstants;
 
/**
 * @author 
 * 
 */
@Component 
public class EmailService {
 
	@Autowired
	private MailSender emailService; // MailSender interface defines a strategy
										// for sending simple mails
 
	public String readyToSendEmail(String toAddress, String subject, String msgBody) {
		SimpleMailMessage crunchifyMsg = new SimpleMailMessage();
		crunchifyMsg.setFrom(CommonConstants.FROMADDRESS);
		crunchifyMsg.setTo(toAddress);
		crunchifyMsg.setSubject(subject);
		crunchifyMsg.setText(msgBody);
		emailService.send(crunchifyMsg);
		return "success";
	}
}
