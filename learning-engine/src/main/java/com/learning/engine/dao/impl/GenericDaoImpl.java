package com.learning.engine.dao.impl;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.learning.engine.dao.GenericDao;
import com.learning.engine.model.BaseObject;

public class GenericDaoImpl<T extends BaseObject, PK> extends SimpleJdbcDaoSupport implements GenericDao<T, PK> {
	
	//-- sql related components
    protected final String tableName;

    protected StringBuilder insertSql;
    protected StringBuilder updateSql;
    private final String deleteSql;

    protected final String getSql;
    protected final String getAllSql;
    
    private final String primaryName;

    //-- default resultset to bean mapper
    protected RowMapper<T> defaultMapper;
    
    public GenericDaoImpl(String tableName, String primaryName, DataSource dataSource) {
        this.tableName = tableName;
        this.primaryName = primaryName;
        
        getAllSql = "select * from " + tableName;
        getSql = getAllSql + " where "+primaryName+" = ? ";
        deleteSql = "delete from " + tableName + " where "+primaryName+" = ? ";

        setDataSource(dataSource);
    }

	@Override
	public List<T> getAll() {
		return getSimpleJdbcTemplate().query(getAllSql, defaultMapper);
	}

	@Override
	public T get(PK id) {
		T object = null;

        try {
            object = getSimpleJdbcTemplate().queryForObject(getSql, defaultMapper, id);
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
        }

        return object;
	}

	@Override
	public Boolean exists(PK id) {
		Boolean result = true;
        String sql = "select "+primaryName+" from " + tableName + " where "+primaryName+" = ? ";
        try {
            getSimpleJdbcTemplate().queryForInt(sql, id);
        } catch (EmptyResultDataAccessException e) {
            result = false;
        }

        return result;
	}

	@Override
	public T save(T object) {
		if (object.getId() == null) {
            //-- insert
            KeyHolder keyHolder = new GeneratedKeyHolder();
            SqlParameterSource parameters = new BeanPropertySqlParameterSource(object);
            new NamedParameterJdbcTemplate(getDataSource()).update(insertSql.toString(), parameters, keyHolder);
            object.setId((Long) keyHolder.getKeyList().get(0).get("id"));
        }
        else {
            //-- update
            SqlParameterSource parameters = new BeanPropertySqlParameterSource(object);
            new NamedParameterJdbcTemplate(getDataSource()).update(updateSql.toString(), parameters);
        }

        return object;
	}

	@Override
	public void remove(PK id) {
		getSimpleJdbcTemplate().update(deleteSql, id);
	}

	@Override
	public List<T> getCurrentPageRows(int startIndex, int pageSize, 
			List<String> restrictions, List<String> orders, Object... params) {
		
		String sql = getAllSql + " ";

        if (restrictions != null && restrictions.size() != 0) {
            sql += "where ";
            for (String restriction : restrictions) {
                sql += restriction;
                sql += " and ";
            }

            sql = sql.substring(0, sql.length()-4);
        }

        if (orders != null && orders.size() != 0) {
            sql += "order by ";
            for (String order : orders) {
                sql += order;
                sql += ", ";
            }

            sql = sql.substring(0, sql.length()-2);
        }

        sql += " limit ? offset ?";
        int pageNo = startIndex / pageSize + (startIndex % pageSize == 0 ? 0 : 1) + 1;

        Object[] newParams;
        int index;

        if (params != null) {
            newParams = new Object[params.length + 2];
            index = params.length;

            System.arraycopy(params, 0, newParams, 0, params.length);
        }
        else {
            newParams = new Object[2];
            index = 0;
        }

        newParams[index++] = pageSize;
        newParams[index] = (pageNo - 1) * pageSize;

        return getSimpleJdbcTemplate().query(sql, defaultMapper, newParams);
	}

	@Override
	public int getRowCount(List<String> restrictions, Object... params) {
		String sql = "select count(*) from " + tableName + " ";

        if (restrictions != null && restrictions.size() != 0) {
            sql += "where ";
            for (String restriction : restrictions) {
                sql += restriction;
                sql += " and ";
            }

            sql = sql.substring(0, sql.length()-4);
        }

        return getSimpleJdbcTemplate().queryForInt(sql, params);
	}

}
