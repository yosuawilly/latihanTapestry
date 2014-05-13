package com.learning.engine.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Component;

import com.learning.engine.model.Materi;

@Component("materiMapper")
public class MateriMapper extends ChainedRowMapper<Materi>{

	@Override
	public Materi chainRow(ResultSet rs, int index) throws SQLException {
		Materi materi = new Materi();
		
		materi.setId(rs.getLong(++index));
		materi.setJudul(rs.getString(++index));
		materi.setIsiMateri(rs.getString(++index));
		materi.setSemester(rs.getInt(++index));
		materi.setUrl(rs.getString(++index));
		
		return materi;
	}

}
