package com.learning.engine.model;

import java.io.Serializable;
import java.util.Set;

public class Siswa extends BaseObject implements Serializable{
	
	private static final long serialVersionUID = -2536231833625929201L;
	
	private String idSiswa;
	private String username;
	private String password;
	private String nama;
	private String jenisKelamin;
	private String alamat;
	
	private Set<NilaiTugas> nilaiTugas;
	private Set<UploadTugas> uploadTugas;
	
	public Siswa() {
		// TODO Auto-generated constructor stub
	}
	
	public String getIdSiswa() {
		return idSiswa;
	}
	
	public void setIdSiswa(String idSiswa) {
		this.idSiswa = idSiswa;
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

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public String getJenisKelamin() {
		return jenisKelamin;
	}

	public void setJenisKelamin(String jenisKelamin) {
		this.jenisKelamin = jenisKelamin;
	}

	public String getAlamat() {
		return alamat;
	}

	public void setAlamat(String alamat) {
		this.alamat = alamat;
	}
	
	public Set<NilaiTugas> getNilaiTugas() {
		return nilaiTugas;
	}
	
	public void setNilaiTugas(Set<NilaiTugas> nilaiTugas) {
		this.nilaiTugas = nilaiTugas;
	}
	
	public Set<UploadTugas> getUploadTugas() {
		return uploadTugas;
	}
	
	public void setUploadTugas(Set<UploadTugas> uploadTugas) {
		this.uploadTugas = uploadTugas;
	}

}
