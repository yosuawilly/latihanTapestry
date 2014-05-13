package com.learning.engine.dao;

import java.util.List;

import com.learning.engine.model.Bab;

public interface BabDao extends GenericDao<Bab, Long>{
	
	public Bab getBabWithMateri(String labelBab);
	public Bab getBabWithMateri2(String judulBab);
	public List<Bab> getAllBabWithMateri(String labelBab);

}
