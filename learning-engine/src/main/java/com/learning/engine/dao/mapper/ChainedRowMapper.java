package com.learning.engine.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Extension mapper to standard <code>RowMapper</code> for simplifying resultset
 * mapping of nested object.
 *
 * @author rk
 * @param <T> a type variable
 */
public abstract class ChainedRowMapper<T> {
	
	/**
     * Mapping <code>ResultSet</code> to an object starting from <code>index</code>.
     * @param rs ResultSet object
     * @param index start pointer (column numbered from ZERO)
     * @return result object from the current row
     * @throws SQLException if a SQLException is encountered getting
     * column values (that is, there's no need to catch SQLException)
     */
    public abstract T chainRow(ResultSet rs, int index) throws SQLException;

    /**
     * Implementing default mapRow of ParameterizedRowMapper interface.
     * @param rs ResultSet object
     * @param rowNum the number of the current row, omitted here.
     * @return the result object for the current row
     * @throws SQLException if a SQLException is encountered getting
     * column values (that is, there's no need to catch SQLException)
     */
    public final T mapRow(ResultSet rs, int rowNum) throws SQLException {
        return chainRow(rs, 0);
    }
	
}
