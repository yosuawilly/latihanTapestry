package com.learning.engine;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@SuppressWarnings("serial")
@JsonIgnoreProperties({"cause", "localizedMessage", "stackTrace", "isoLanguage"})
public class BusinessException extends RuntimeException implements Serializable{
	private String errorCode;
    private String fullMessage;
    private Object[] arguments;       

    public BusinessException(String errorCode) {
        super();
        this.errorCode = errorCode;

        translateMessage();
    }

    public BusinessException(String errorCode, Object... arguments) {
        super();
        this.errorCode = errorCode;
        this.arguments = arguments;

        translateMessage();
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getFullMessage() {
        return fullMessage;
    }

    public void setFullMessage(String fullMessage) {
        this.fullMessage = fullMessage;
    }

    public Object[] getArguments() {
        return arguments;
    }

    public void setArguments(Object[] arguments) {
        this.arguments = arguments;
    }
    
    public void translateMessage(String isoLanguage) {    	
    	ResourceBundle bundle;

        try {
            bundle = ResourceBundle.getBundle("errors", new Locale(isoLanguage));
        } catch (MissingResourceException e) {
            e.printStackTrace();
            return;
        }
        
        try {        	
            String message = bundle.getString(this.errorCode);
            if (this.arguments != null && this.arguments.length > 0) {
                this.fullMessage = MessageFormat.format(message, this.arguments);
            }
            else {
            	this.fullMessage = message;
            }
            if ("RC-01".equals(errorCode) || "RC-03".equals(errorCode) || "RC-73".equals(errorCode) || "RC-19".equals(errorCode)) {
    			this.fullMessage = bundle.getString("RC-MM");
    		}
        } catch (MissingResourceException e) {
            if (this.arguments != null && this.arguments.length > 0) {
                this.fullMessage = MessageFormat.format(this.errorCode, this.arguments);
            }
            else {
                this.fullMessage = this.errorCode;
            }
        }

    }

    private void translateMessage() {
    	translateMessage("ind");
    }

    @Override
    public String getMessage() {
        return this.errorCode + " (" + this.fullMessage.trim() +
                (super.getMessage() == null ? "" : " " + super.getMessage().trim()) + ")" ;
    }
    
}
