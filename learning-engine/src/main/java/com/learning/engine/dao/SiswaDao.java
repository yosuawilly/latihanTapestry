package com.learning.engine.dao;

import com.learning.engine.model.Siswa;

public interface SiswaDao extends GenericDao<Siswa, String>{
	
	public Siswa getByUsername(String username);

}
