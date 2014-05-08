package com.learning.engine.service;

import com.learning.engine.model.Siswa;

public interface SiswaService extends GenericService<Siswa, String>{
	public Siswa getByUsername(String username);
}
