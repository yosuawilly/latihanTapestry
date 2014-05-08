package com.learning.engine.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.stereotype.Component;

import com.learning.engine.model.Siswa;

@Component("siswaMapper")
public class SiswaMapper extends ChainedRowMapper<Siswa> implements ParameterizedRowMapper<Siswa>{

	@Override
	public Siswa chainRow(ResultSet rs, int index) throws SQLException {
		Siswa siswa = new Siswa();
		
		siswa.setIdSiswa(rs.getString(++index));
		siswa.setUsername(rs.getString(++index));
		siswa.setPassword(rs.getString(++index));
		siswa.setNama(rs.getString(++index));
		siswa.setJenisKelamin(rs.getString(++index));
		siswa.setAlamat(rs.getString(++index));
		
		return siswa;
	}

}
