package com.learning.engine.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Component;

import com.learning.engine.model.Admin;

@Component("adminMapper")
public class AdminMapper extends ChainedRowMapper<Admin>{

	@Override
	public Admin chainRow(ResultSet rs, int index) throws SQLException {
		Admin admin = new Admin();
		
		admin.setId(rs.getLong(++index));
		admin.setUsername(rs.getString(++index));
		admin.setPassword(rs.getString(++index));
		
		return admin;
	}

}
