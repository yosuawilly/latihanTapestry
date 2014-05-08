package com.learning.engine.model;

import java.io.Serializable;
import java.util.Date;

public class UploadTugas implements Serializable{

	private static final long serialVersionUID = -3314239148892943773L;
	
	private long idUpload;
	private Siswa siswa;
	private Tugas tugas;
	private String namaFile;
	private Date tglUpload;
	
	public UploadTugas() {
		// TODO Auto-generated constructor stub
	}

	public long getIdUpload() {
		return idUpload;
	}

	public void setIdUpload(long idUpload) {
		this.idUpload = idUpload;
	}

	public Siswa getSiswa() {
		return siswa;
	}

	public void setSiswa(Siswa siswa) {
		this.siswa = siswa;
	}

	public Tugas getTugas() {
		return tugas;
	}

	public void setTugas(Tugas tugas) {
		this.tugas = tugas;
	}

	public String getNamaFile() {
		return namaFile;
	}

	public void setNamaFile(String namaFile) {
		this.namaFile = namaFile;
	}

	public Date getTglUpload() {
		return tglUpload;
	}

	public void setTglUpload(Date tglUpload) {
		this.tglUpload = tglUpload;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
