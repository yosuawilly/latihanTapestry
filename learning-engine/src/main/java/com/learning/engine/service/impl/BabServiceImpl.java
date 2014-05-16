package com.learning.engine.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.engine.dao.BabDao;
import com.learning.engine.model.Bab;
import com.learning.engine.service.BabService;

@Service("babService")
public class BabServiceImpl extends GenericServiceImpl<Bab, Long> implements BabService{
	
	private BabDao babDao;

	@Autowired
	public BabServiceImpl(BabDao babDao) {
		super(babDao);
		this.babDao = babDao;
	}

	@Override
	public Bab getBabWithMateri(String labelBab) {
		return babDao.getBabWithMateri(labelBab);
	}

	@Override
	public Bab getBabWithMateri2(String judulBab) {
		return babDao.getBabWithMateri2(judulBab);
	}

	@Override
	public List<Bab> getAllBabWithMateri() {
		return babDao.getAllBabWithMateri();
	}

}
