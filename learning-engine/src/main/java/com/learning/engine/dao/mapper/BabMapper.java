package com.learning.engine.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Component;

import com.learning.engine.model.Bab;

@Component("babMapper")
public class BabMapper extends ChainedRowMapper<Bab>{

	@Override
	public Bab chainRow(ResultSet rs, int index) throws SQLException {
		Bab bab = new Bab();
		
		bab.setId(rs.getLong(++index));
		bab.setLabelBab(rs.getString(++index));
		bab.setJudulBab(rs.getString(++index));
		
		return bab;
	}

}
