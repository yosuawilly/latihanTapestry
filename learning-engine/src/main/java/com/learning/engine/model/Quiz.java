package com.learning.engine.model;

import java.io.Serializable;
import java.util.Set;

public class Quiz implements Serializable{
	
	private static final long serialVersionUID = 6777114020511256768L;
	
	private long idQuiz;
	private String soalQuiz;
	private Set<JawabanQuiz> jawabanQuizs;
	
	public Quiz() {
		// TODO Auto-generated constructor stub
	}

	public long getIdQuiz() {
		return idQuiz;
	}

	public void setIdQuiz(long idQuiz) {
		this.idQuiz = idQuiz;
	}

	public String getSoalQuiz() {
		return soalQuiz;
	}

	public void setSoalQuiz(String soalQuiz) {
		this.soalQuiz = soalQuiz;
	}

	public Set<JawabanQuiz> getJawabanQuizs() {
		return jawabanQuizs;
	}

	public void setJawabanQuizs(Set<JawabanQuiz> jawabanQuizs) {
		this.jawabanQuizs = jawabanQuizs;
	}
	
}
