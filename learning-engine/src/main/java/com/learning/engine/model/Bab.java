package com.learning.engine.model;

import java.io.Serializable;
import java.util.List;

public class Bab extends BaseObject implements Serializable{
	
	private static final long serialVersionUID = -451632702176978958L;
	
	private String labelBab;
	private String judulBab;
	private List<Materi> materis;
	
	public Bab() {
		// TODO Auto-generated constructor stub
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
	
	public List<Materi> getMateris() {
		return materis;
	}
	
	public void setMateris(List<Materi> materis) {
		this.materis = materis;
	}

}
