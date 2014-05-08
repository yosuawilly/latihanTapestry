package com.learning.engine.model;

import java.io.Serializable;

public class Admin implements Serializable{
	
	private static final long serialVersionUID = -5962132341328447017L;
	
	private long idAdmin;
	private String username;
	private String password;
	
	public Admin() {
		// TODO Auto-generated constructor stub
	}

	public long getIdAdmin() {
		return idAdmin;
	}

	public void setIdAdmin(long idAdmin) {
		this.idAdmin = idAdmin;
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
