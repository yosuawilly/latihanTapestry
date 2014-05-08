package com.learning.engine.model;

import java.io.Serializable;

public class JawabanQuiz implements Serializable{
	
	private static final long serialVersionUID = 986140528531604839L;
	
	private long idJawaban;
	private String jawaban;
	private Quiz quiz;
	private boolean benar;
	
	public JawabanQuiz() {
		// TODO Auto-generated constructor stub
	}

	public long getIdJawaban() {
		return idJawaban;
	}

	public void setIdJawaban(long idJawaban) {
		this.idJawaban = idJawaban;
	}

	public String getJawaban() {
		return jawaban;
	}

	public void setJawaban(String jawaban) {
		this.jawaban = jawaban;
	}

	public Quiz getQuiz() {
		return quiz;
	}
	
	public void setQuiz(Quiz quiz) {
		this.quiz = quiz;
	}

	public boolean isBenar() {
		return benar;
	}

	public void setBenar(boolean benar) {
		this.benar = benar;
	}
	
}
