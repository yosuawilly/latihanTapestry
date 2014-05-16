package com.learning.engine.service;

import java.util.List;

import com.learning.engine.model.Bab;

public interface BabService extends GenericService<Bab, Long>{
	public Bab getBabWithMateri(String labelBab);
	public Bab getBabWithMateri2(String judulBab);
	public List<Bab> getAllBabWithMateri();
}
