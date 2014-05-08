package com.learning.engine.dao.impl;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.learning.engine.dao.SiswaDao;
import com.learning.engine.dao.mapper.SiswaMapper;
import com.learning.engine.model.Siswa;

@Repository("siswaDao")
public class SiswaDaoImpl extends GenericDaoImpl<Siswa, String> implements SiswaDao{

	@Autowired
	public SiswaDaoImpl(DataSource dataSource, SiswaMapper siswaMapper) {
		super("siswa", "id_siswa", dataSource);
		defaultMapper = siswaMapper;
		
		insertSql = new StringBuilder()
        .append("insert into siswa ( ")
        .append("   id_siswa, username, password, nama, jenis_kelamin, alamat ")
        .append(") ")
        .append("values ( ")
        .append("   :id, :username, :password, :nama, :jenisKelamin, :alamat ")
        .append(") ");

         updateSql = new StringBuilder()
        .append("update siswa ")
        .append("set ")
        .append("   username = :username, ")
        .append("   password = :password, nama = :nama, jenis_kelamin = :jenisKelamin, ")
        .append("   alamat = :alamat ")
        .append("where id_siswa = :id ");
	}

	@Override
	public Siswa getByUsername(String username) {
		StringBuilder sql = new StringBuilder()
        .append("select sis.* ")
        .append("from siswa sis ")
        .append("where sis.username = ? ");

        return getSimpleJdbcTemplate().queryForObject(sql.toString(), defaultMapper, username);
	}

}
