package com.learning.engine.model.json;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Result<T> implements Serializable{
	
	private boolean status;
	private String fullMessage;
	private T object;
	
	public Result() {
		// TODO Auto-generated constructor stub
	}
	
	public Result(boolean status, String fullMessage, T object) {
		this.status = status;
		this.fullMessage = fullMessage;
		this.object = object;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getFullMessage() {
		return fullMessage;
	}

	public void setFullMessage(String fullMessage) {
		this.fullMessage = fullMessage;
	}

	public T getObject() {
		return object;
	}

	public void setObject(T object) {
		this.object = object;
	}

}
