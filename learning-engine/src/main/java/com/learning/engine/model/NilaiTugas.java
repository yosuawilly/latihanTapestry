package com.learning.engine.model;

import java.io.Serializable;

public class NilaiTugas implements Serializable{
	
	private static final long serialVersionUID = -6614533844853100346L;
	
	private Siswa siswa;
	private Tugas tugas;
	private int nilai;
	
	public NilaiTugas() {
		// TODO Auto-generated constructor stub
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

	public int getNilai() {
		return nilai;
	}

	public void setNilai(int nilai) {
		this.nilai = nilai;
	}
	
}
