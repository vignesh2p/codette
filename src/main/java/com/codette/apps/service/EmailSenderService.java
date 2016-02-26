package com.codette.apps.service;
import javax.annotation.Resource;

import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

import com.codette.apps.util.CommonConstants;
 
/**
 * @author 
 * 
 */
@Component 
public class EmailSenderService {
 
	@Resource
	private MailSender mailSender; // MailSender interface defines a strategy
										// for sending simple mails
 
	public String emailNotification(String toAddress, String subject, String msgBody) {
		SimpleMailMessage crunchifyMsg = new SimpleMailMessage();
		crunchifyMsg.setFrom(CommonConstants.FROMADDRESS);
		crunchifyMsg.setTo(toAddress);
		crunchifyMsg.setSubject(subject);
		crunchifyMsg.setText(msgBody);
		mailSender.send(crunchifyMsg);
		return "success";
	}
}
