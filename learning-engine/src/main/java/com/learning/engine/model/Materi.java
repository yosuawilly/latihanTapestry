package com.learning.engine.model;

import java.io.Serializable;
import java.util.Set;

public class Materi implements Serializable{
	
	private static final long serialVersionUID = 8210937082613070619L;
	
	private long idMateri;
	private String judul;
	private String isiMateri;
	private Bab bab;
	private int semester;
	private String url;
	private Set<MateriLinkVideo> materiLinkVideos;
	
	public Materi() {
		// TODO Auto-generated constructor stub
	}

	public long getIdMateri() {
		return idMateri;
	}

	public void setIdMateri(long idMateri) {
		this.idMateri = idMateri;
	}

	public String getJudul() {
		return judul;
	}

	public void setJudul(String judul) {
		this.judul = judul;
	}

	public String getIsiMateri() {
		return isiMateri;
	}

	public void setIsiMateri(String isiMateri) {
		this.isiMateri = isiMateri;
	}

	public Bab getBab() {
		return bab;
	}

	public void setBab(Bab bab) {
		this.bab = bab;
	}

	public int getSemester() {
		return semester;
	}

	public void setSemester(int semester) {
		this.semester = semester;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	public Set<MateriLinkVideo> getMateriLinkVideos() {
		return materiLinkVideos;
	}
	
	public void setMateriLinkVideos(Set<MateriLinkVideo> materiLinkVideos) {
		this.materiLinkVideos = materiLinkVideos;
	}
	
}
