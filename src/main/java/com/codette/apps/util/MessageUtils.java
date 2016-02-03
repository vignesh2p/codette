package com.codette.apps.util;


import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.stereotype.Component;

@Component
public class MessageUtils implements MessageSourceAware  {
	
	private static MessageSource messageSource;
	
    /**
     * Get message from resource properties based on key
     * @param key
     * @return
     */
    public static String getMessage(String key) {
        try {
            return messageSource.getMessage(key, null, null);
        }
        catch (Exception e) {
            return "Unresolved key: " + key;
        }
    }
    
    /**
     * Get message from resource properties based on key & args
     * @param key
     * @param args
     * @return
     */
    public static String getMessage(String key, Object[] args) {
        try {
            return messageSource.getMessage(key, args, null);
        }
        catch (Exception e) {
            return "Unresolved key: " + key;
        }
    }

	@SuppressWarnings("static-access")
	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}
}