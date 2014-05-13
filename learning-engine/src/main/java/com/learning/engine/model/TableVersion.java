package com.learning.engine.model;

import java.io.Serializable;

public class TableVersion extends BaseObject implements Serializable{

	private static final long serialVersionUID = -7470138132170532791L;
	
	private String namaTable;
	private String version;
	
	public TableVersion() {
		// TODO Auto-generated constructor stub
	}

	public String getNamaTable() {
		return namaTable;
	}

	public void setNamaTable(String namaTable) {
		this.namaTable = namaTable;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
