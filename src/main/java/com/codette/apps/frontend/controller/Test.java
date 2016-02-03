package com.codette.apps.frontend.controller;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.google.common.base.Preconditions;
import com.codette.apps.frontend.service.EmailService;
import com.codette.apps.util.CommonConstants;
import com.codette.apps.util.CommonUtil;


public class Test {
	static
	HttpServletRequest httpServletRequest;
	
	public static void main(String args[]) throws ParseException {
		   SimpleDateFormat displayFormat = new SimpleDateFormat("HH:mm:ss");
	       SimpleDateFormat parseFormat = new SimpleDateFormat("hh:mm a");
	       Date date = parseFormat.parse("10:30 PM");
	       System.out.println(parseFormat.format(date) + " = " + displayFormat.format(date));
			
	}
	public static void toBeginningOfTheDay(Calendar calendar) {
        Preconditions.checkNotNull(calendar, "Calendar");
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 30);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
    }
	public static Date formatgivenStringToDate(String strDate, String fromFormat, String toFormat){
		SimpleDateFormat formatter = new SimpleDateFormat(fromFormat);
		try {
			Date date = formatter.parse(strDate);
			System.out.println(date);
			System.out.println("date>>>>>>>>>>."+date);
			SimpleDateFormat formatter2 = new SimpleDateFormat(toFormat);
			String dateStr = formatter2.format(date);
			System.out.println("dateStr>>>>>>>>"+dateStr);
			Date last_date_date = new SimpleDateFormat(toFormat).parse(dateStr);
			String rF =	new SimpleDateFormat(toFormat).format(last_date_date);
			Date dateToFormat = formatter2.parse(dateStr);
			System.out.println("dateToFormat>>>>>>>>>>>>"+rF);
			return dateToFormat;
		} catch (ParseException e) {
			e.printStackTrace();;
		}
		return null;
	}
	
	/*
	public  static String checkDate(){
		    Date today = Calendar.getInstance().getTime();
		    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-hh.mm.ss");
		    String folderName = formatter.format(today);
		    System.out.println("Folder Name = " + folderName);
		return 
	}*/
	public static String stringFeilds(String str){
		return "'"+str+"'";
	}
	public static void dateFormatter() throws ParseException{
		CommonUtil common = new CommonUtil();
	//	Date d = common.formatgivenStringToDate("21 October, 2015", CommonConstants.DATE_DD_MMMM_YYYY, CommonConstants.DATE_FORMAT);
	//	System.out.println("date>>>>>>>>>>"+d);
	}
	
	
	@SuppressWarnings("resource")
	public void mailcheck(){
		// Spring Bean file you specified in /src/main/resources folder
				String crunchifyConfFile = "restview-servlet.xml";
				ConfigurableApplicationContext context = new ClassPathXmlApplicationContext(crunchifyConfFile);
		 
				// @Service("crunchifyEmail") <-- same annotation you specified in CrunchifyEmailAPI.java
				EmailService emailService =  (EmailService) context.getBean("emailService");
				String toAddr = "viki19nesh@gmail.com";
				String fromAddr = "viki19nesh@gmail.com";
		 
				// email subject
				String subject = "Hey.. This email sent by Crun chify's Spring MVC Tutorial";
		 
				// email body
				String body = "There you go.. You got an email.. Let's understand details on how Spring MVC works -- By Crunchify Admin";
				//emailService.readyToSendEmail(toAddr, fromAddr, subject, body);
	}
}
