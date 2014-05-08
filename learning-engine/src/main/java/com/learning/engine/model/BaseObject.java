package com.learning.engine.model;

import java.io.Serializable;

public class BaseObject implements Serializable{
	
	private static final long serialVersionUID = 1129724749746593202L;
	
	private Long id;
	
	public BaseObject() {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
}
