package com.learning.engine.model;

import java.io.Serializable;

public class Admin extends BaseObject implements Serializable{
	
	private static final long serialVersionUID = -5962132341328447017L;
	
	private String username;
	private String password;
	
	public Admin() {
		// TODO Auto-generated constructor stub
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
