package com.learning.engine.model;

import java.io.Serializable;
import java.util.Set;

public class Bab implements Serializable{
	
	private static final long serialVersionUID = -451632702176978958L;
	
	private long idBab;
	private String labelBab;
	private String judulBab;
	private Set<Materi> materis;
	
	public Bab() {
		// TODO Auto-generated constructor stub
	}

	public long getIdBab() {
		return idBab;
	}

	public void setIdBab(long idBab) {
		this.idBab = idBab;
	}

	public String getLabelBab() {
		return labelBab;
	}

	public void setLabelBab(String labelBab) {
		this.labelBab = labelBab;
	}

	public String getJudulBab() {
		return judulBab;
	}

	public void setJudulBab(String judulBab) {
		this.judulBab = judulBab;
	}
	
	public Set<Materi> getMateris() {
		return materis;
	}
	
	public void setMateris(Set<Materi> materis) {
		this.materis = materis;
	}

}
