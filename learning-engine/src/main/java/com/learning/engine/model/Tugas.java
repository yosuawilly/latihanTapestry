package com.learning.engine.model;

import java.io.Serializable;
import java.util.Set;

public class Tugas implements Serializable{
	
	private static final long serialVersionUID = 1805345791792559349L;
	
	private long idTugas;
	private String judulTugas;
	private String catatan;
	
	private Set<NilaiTugas> nilaiTugas;
	private Set<SoalTugas> soalTugas;
	private Set<UploadTugas> uploadTugas;
	
	public Tugas() {
		// TODO Auto-generated constructor stub
	}

	public long getIdTugas() {
		return idTugas;
	}

	public void setIdTugas(long idTugas) {
		this.idTugas = idTugas;
	}

	public String getJudulTugas() {
		return judulTugas;
	}

	public void setJudulTugas(String judulTugas) {
		this.judulTugas = judulTugas;
	}

	public String getCatatan() {
		return catatan;
	}

	public void setCatatan(String catatan) {
		this.catatan = catatan;
	}
	
	public Set<NilaiTugas> getNilaiTugas() {
		return nilaiTugas;
	}
	
	public void setNilaiTugas(Set<NilaiTugas> nilaiTugas) {
		this.nilaiTugas = nilaiTugas;
	}
	
	public Set<SoalTugas> getSoalTugas() {
		return soalTugas;
	}
	
	public void setSoalTugas(Set<SoalTugas> soalTugas) {
		this.soalTugas = soalTugas;
	}
	
	public Set<UploadTugas> getUploadTugas() {
		return uploadTugas;
	}
	
	public void setUploadTugas(Set<UploadTugas> uploadTugas) {
		this.uploadTugas = uploadTugas;
	}
	
}
