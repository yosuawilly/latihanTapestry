package com.learning.engine.model;

import java.io.Serializable;

public class SoalTugas implements Serializable{
	
	private static final long serialVersionUID = 1003880656497224546L;
	
	private long idSoal;
	private String isiSoal;
	private Tugas tugas;
	
	public SoalTugas() {
		// TODO Auto-generated constructor stub
	}

	public long getIdSoal() {
		return idSoal;
	}

	public void setIdSoal(long idSoal) {
		this.idSoal = idSoal;
	}

	public String getIsiSoal() {
		return isiSoal;
	}

	public void setIsiSoal(String isiSoal) {
		this.isiSoal = isiSoal;
	}

	public Tugas getTugas() {
		return tugas;
	}

	public void setTugas(Tugas tugas) {
		this.tugas = tugas;
	}
	
}
