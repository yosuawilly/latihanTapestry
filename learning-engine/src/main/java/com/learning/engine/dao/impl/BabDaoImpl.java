package com.learning.engine.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.learning.engine.dao.BabDao;
import com.learning.engine.dao.mapper.BabMapper;
import com.learning.engine.model.Bab;
import com.learning.engine.model.Materi;

@Repository("babDao")
public class BabDaoImpl extends GenericDaoImpl<Bab, Long> implements BabDao{

	@Autowired
	public BabDaoImpl(DataSource dataSource, BabMapper babMapper) {
		super("bab", "id_bab", dataSource);
		defaultMapper = babMapper;
		
		insertSql = new StringBuilder()
        .append("insert into bab ( ")
        .append("   label_bab, judul_bab ")
        .append(") ")
        .append("values ( ")
        .append("   :labelBab, :judulBab ")
        .append(") ");

         updateSql = new StringBuilder()
        .append("update siswa ")
        .append("set ")
        .append("   label_bab = :labelBab, judul_bab = :judulBab ")
        .append("where id_bab = :id ");
	}

	@Override
	public Bab getBabWithMateri(String labelBab) {
		StringBuilder sql = new StringBuilder()
        .append("select b.*, m.id_materi,m.judul,m.isi_materi,m.semester,m.url ")
        .append("from bab b, materi m ")
        .append("where b.id_bab=m.id_bab ")
        .append("and label_bab = ? ");

		List<Map<String, Object>> resultMap = getJdbcTemplate().queryForList(sql.toString(), labelBab);
		if(resultMap.size() == 0) return null;
		
		return buildBabWithMateri(resultMap).get(0);
	}

	@Override
	public Bab getBabWithMateri2(String judulBab) {
		StringBuilder sql = new StringBuilder()
        .append("select b.*, m.id_materi,m.judul,m.isi_materi,m.semester,m.url ")
        .append("from bab b, materi m ")
        .append("where b.id_bab=m.id_bab ")
        .append("and judul_bab = ? ");

		List<Map<String, Object>> resultMap = getJdbcTemplate().queryForList(sql.toString(), judulBab);
		if(resultMap.size() == 0) return null;
		
		return buildBabWithMateri(resultMap).get(0);
	}

	@Override
	public List<Bab> getAllBabWithMateri() {
		List<Map<String, Object>> resultMap = getJdbcTemplate().queryForList(
				"select * from bab b, materi m where b.id_bab=m.id_bab order by b.id_bab");
		return buildBabWithMateri(resultMap);
	}
	
	private List<Bab> buildBabWithMateri(List<Map<String, Object>> resultMap) {
		List<Bab> babs = new ArrayList<Bab>();
		long idTempBab = -1;
		List<Materi> materis = null;
		
		Bab bab = null;
		int current = -1;
		for(Map<String, Object> map : resultMap) {
			long idBab = Long.parseLong( String.valueOf(map.get("id_bab")) );
			String label = (String) map.get("label_bab");
			String judul = (String) map.get("judul_bab");
			
			Long idMateri = Long.parseLong( String.valueOf(map.get("id_materi")) );
			String judulMateri = (String) map.get("judul");
			String isiMateri = (String) map.get("isi_materi");
			int semester = Integer.parseInt( String.valueOf(map.get("semester")) );
			String url = (String) map.get("url");
			
			if(idBab != idTempBab) {
				idTempBab = idBab;
				
				bab = new Bab();
				bab.setId(idBab);
				bab.setLabelBab(label);
				bab.setJudulBab(judul);
				
				materis = new ArrayList<Materi>();
				bab.setMateris(materis);
				babs.add(bab);
				
				Materi materi = new Materi();
				materi.setId(idMateri);
				materi.setJudul(judulMateri);
				materi.setIsiMateri(isiMateri);
				materi.setSemester(semester);
				materi.setUrl(url);
				
				current++;
				babs.get(current).getMateris().add(materi);
				
			} else {
				Materi materi = new Materi();
				materi.setId(idMateri);
				materi.setJudul(judulMateri);
				materi.setIsiMateri(isiMateri);
				materi.setSemester(semester);
				materi.setUrl(url);
				
				babs.get(current).getMateris().add(materi);
			}
		}
		
		return babs;
	}

}
