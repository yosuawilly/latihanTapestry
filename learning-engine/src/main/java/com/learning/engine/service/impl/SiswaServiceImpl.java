package com.learning.engine.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.engine.dao.SiswaDao;
import com.learning.engine.model.Siswa;
import com.learning.engine.service.SiswaService;

@Service("siswaService")
public class SiswaServiceImpl extends GenericServiceImpl<Siswa, String> implements SiswaService{
	
	private SiswaDao siswaDao;

	@Autowired
	public SiswaServiceImpl(SiswaDao siswaDao) {
		super(siswaDao);
		this.siswaDao = siswaDao;
	}

	@Override
	public Siswa getByUsername(String username) {
		return siswaDao.getByUsername(username);
	}

}
