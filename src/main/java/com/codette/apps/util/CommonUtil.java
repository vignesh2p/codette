package com.codette.apps.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class CommonUtil {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CommonUtil.class);
	
	
	
	/*
	 * Convert String to Date.
	 */
	public static Date convertStrToDateFormat(String date, String dateFormat) {
		Date outputDate = null;
		try {
			DateFormat formatter = new SimpleDateFormat(dateFormat);
			outputDate = (Date) formatter.parse(date);
		} catch (Exception ex) {
			LOGGER.error("Error getting convertStrToDateFormat " + ex);
		}
		return outputDate;
	}
	/**
	 * Read API configuration details from api-config file
	 * @param is
	 * @return sb A not null {@link String}
	 */
	public String getStringFromInputStream(InputStream is) throws IOException{
		 
		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();
 
		String line;
		try {
 
			br = new BufferedReader(new InputStreamReader(is));
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
 
		} catch (IOException e) {
			LOGGER.error("IOException in getStringFromInputStream "+e.getMessage());
			throw e;
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					LOGGER.error("IOException in getStringFromInputStream "+e.getMessage());
					throw e;
				}
			}
		}
		return sb.toString();
	}
	
	
	/**
	 * Gets locale value from map
	 * @param maps
	 * @param locale
	 * @return {@link String}
	 */
	public String getLocaleValue(Map<String, String> maps, String locale) {
		TreeMap<String, String> tempMaps = new TreeMap<String, String>(maps);
		String value = "";
		for(Map.Entry<String, String> entry : tempMaps.entrySet()){
			if(entry.getKey().equalsIgnoreCase(locale)){
				value = entry.getValue();
				break;
			}
		}
		if(value.isEmpty()){
			for(Map.Entry<String, String> entry : tempMaps.entrySet()){
				if(entry.getKey().contains("en")){
					value = entry.getValue();
					break;
				}
			}
			if(value.isEmpty()){
				for(Map.Entry<String, String> entry : tempMaps.entrySet()){
					value = entry.getValue();
					break;
				}
			}
		}
		if(value!=null){
			if(value.isEmpty())
				return null;
			else if(value.toString().equalsIgnoreCase("null"))
				return null;
		}
		return value;
	}
	
	/**
	 * Convert Date format
	 * @param strDate
	 * @param from
	 * @param to
	 * @return 
	 */
	public String formatDateTogiven(Date date,  String toFormat) throws ParseException{
			SimpleDateFormat formatter2 = new SimpleDateFormat(toFormat);
			String dateStr = formatter2.format(date);
			return dateStr;
	}
	
	
	
	public  String stringFeilds(String str){
		return "'"+str+"'";
	}
	/**
	 * 
	 * @param strDate
	 * @param fromFormat
	 * @param toFormat
	 * @return
	 * @throws ParseException
	 */
	public String formatgivenStringToDate(String strDate, String fromFormat, String toFormat) throws ParseException{
		SimpleDateFormat formatter = new SimpleDateFormat(fromFormat);
		try {
			Date date = formatter.parse(strDate);
			System.out.println(date);
			SimpleDateFormat formatter2 = new SimpleDateFormat(toFormat);
			String dateStr = formatter2.format(date);
			System.out.println("dateStr>>>>>>>>"+dateStr);
			return dateStr;
		} catch (ParseException e) {
			LOGGER.error("Exception in formatDateTogiven{} ", e.getMessage());
			throw e;
		}
	}
	
	/**
	 * Convert Timestamp to Date in String format
	 * @param strDate
	 * @param toFormat
	 * @return
	 * @throws ParseException 
	 */
	public String formatTimeStampToDateString(String timestamp, String fromFormat, String toFormat) throws ParseException{
		System.out.println("input timestamp---------"+timestamp);
		SimpleDateFormat formatter = new SimpleDateFormat(fromFormat);
		Date date = formatter.parse(timestamp);
		
		System.out.println("dateStr----------"+date.getTime());
		SimpleDateFormat toFormatter = new SimpleDateFormat(toFormat);
		String returnString = toFormatter.format(date);
		System.out.println("returnString===="+returnString);
		return returnString;
	}
	
	public String formatgivenStringToTimestamp(String dateStr, String timeStr, String fromFormat, String toFormat) throws ParseException{
		SimpleDateFormat formatter = new SimpleDateFormat(fromFormat);
		SimpleDateFormat toformatter = new SimpleDateFormat(toFormat);
		String date = formatter.format(dateStr);
		System.out.println("date--------------------"+date);
		String dateTime = toformatter.format(date);
		System.out.println("dateTime--------------"+dateTime);
		return dateTime;
	}
	
	
	  public String findRedirectedURL(String url) throws Exception{
			try {
				URL obj = new URL(url);
				HttpURLConnection conn;
				conn = (HttpURLConnection) obj.openConnection();
				conn.setReadTimeout(5000);
				conn.setInstanceFollowRedirects(false);
				boolean redirect = false;
				int status = conn.getResponseCode();
				if (status != HttpURLConnection.HTTP_OK) {
					if (status == HttpURLConnection.HTTP_MOVED_TEMP
						|| status == HttpURLConnection.HTTP_MOVED_PERM
							|| status == HttpURLConnection.HTTP_SEE_OTHER
							)
					redirect = true;
				}
				if (redirect) {
					String newUrl = conn.getHeaderField("Location");
					if(newUrl!=null){
						url = newUrl;
					}
				}
			} catch (MalformedURLException e) {
				LOGGER.error("findRedirectedURL MalformedURLException "+e.getMessage());
				throw e;
			} catch (IOException e) {
				LOGGER.error("findRedirectedURL IOException "+e.getMessage());
				throw e;
			}
			 
	    	 return url;
	     }
	     
	/**
	 * Converting String to date by given format.
	 * @param date The Date.
	 * @param toFormat The format of Date. 
	 * @return formatedDate {@link Date}
	 */
	public Date convertStringToDate(String date, String toFormat) throws ParseException{
		try{
			SimpleDateFormat formatter = new SimpleDateFormat(toFormat);
			Date formatedDate = formatter.parse(date);
			return formatedDate;
		}catch(ParseException e){
			LOGGER.error("Exception in convertStringToDate{} ", e.getMessage());
			throw e;		
		}
	}
	
	/**
	 * gets calculated percentage
	 * @param dividend
	 * @param divisor
	 * @return {@link percentage}
	 */
	public double getPercentage(double dividend, double divisor) throws Exception{
		Double percentage = 0d;
		try{
			if(divisor != 0d){
				percentage = (dividend / divisor) * 100;
			    percentage = roundPercentage(percentage);
			}
		}catch (Exception ex) {
			LOGGER.error("Error getting percentage"+ex.getMessage());
			throw ex;
		}
		return percentage;
	}
	
	/**
 	 * Gets rounded hole value
 	 * @param value
 	 * @return {@link Integer}
 	 */
 	public double roundPercentage(Double value) throws Exception{
 		double roundedValue = Math.round(value);
 		return roundedValue;
 	}
 	
 	/**
 	 * Gets rounded decimal value.
 	 * @param value The Double value.
 	 * @param n The maximum number of Fraction digits.
 	 * @return {@link Double}
 	 */
	public Double roundDecimal(Double value, int n) {
		DecimalFormat decimalFormat = new DecimalFormat();
		decimalFormat.setMaximumFractionDigits(n);
		Double result = Double.valueOf(decimalFormat.format(value));
		return result;
	}
	
	public String getEntityValue(Map<String, String> maps, String locale) {
		TreeMap<String, String> tempMaps = new TreeMap<String, String>(maps);
		String value = "";
		for(Map.Entry<String, String> entry : tempMaps.entrySet()){
			if(entry.getKey().equalsIgnoreCase(locale)){
				value = entry.getValue();
				break;
			}
		}
		if(value.isEmpty()){
			for(Map.Entry<String, String> entry : tempMaps.entrySet()){
				if(entry.getKey().contains("en")){
					value = entry.getValue();
					break;
				}
			}
			if(value.isEmpty()){
				for(Map.Entry<String, String> entry : tempMaps.entrySet()){
					value = entry.getValue();
					break;
				}
			}
		}
		if(value!=null){
			if(value.isEmpty())
				return null;
			else if(value.toString().equalsIgnoreCase("null"))
				return null;
		}
		return value;
	}
	public String getCurrentTimeStampInString() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		String string  = dateFormat.format(new Date());
		return string;
	}
	
}