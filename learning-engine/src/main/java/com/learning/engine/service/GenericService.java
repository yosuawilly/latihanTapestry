package com.learning.engine.service;

import java.util.List;

import com.learning.engine.model.BaseObject;

/**
 * Generic Service that talks to GenericDao to CRUD POJOs.
 *
 * @author rk
 * @param <T> a type variable
 * @param <PK> the primary key for that type
 */
public interface GenericService<T extends BaseObject, PK> {
    /**
     * Generic method used to get all objects of a particular type. This
     * is the same as lookup up all rows in a table.
     * @return List of populated objects
     */
    List<T> getAll();

    /**
     * Generic method to get an object based on class and identifier.
     * If no object corresponding to specified PK is exists, null value will be returned
     *
     * @param id the identifier (primary key) of the object to get
     * @return a populated object
     */
    T get(PK id);

    /**
     * Checks for existence of an object of type T using the id arg.
     * @param id the identifier (primary key) of the object to get
     * @return - true if it exists, false if it doesn't
     */
    boolean exists(PK id);

    /**
     * Generic method to save an object - handles both update and insert.
     * @param object the object to save
     * @return the updated object
     */
    T save(T object);

    /**
     * Generic method to delete an object based on class and id
     * @param id the identifier (primary key) of the object to remove
     * @param userId user who remove the object
     */
    void remove(PK id, Long userId);

    /**
     * Get current page rows after sort and restriction being applied.
     * @param startIndex starting index from resultset
     * @param pageSize how many rows to be retrieved
     * @param restrictions valid sql predicate
     * @param orders valid order by column
     * @param params bind variable value
     */
    List<T> getCurrentPageRows(int startIndex, int pageSize, List<String> restrictions,
                               List<String> orders, Object... params);

    /**
     * Get total row from resultset after restrictions being applied.
     * @param restrictions valid sql predicate
     * @param params bind variable value
     * @return total rows in resultset
     */
    int getRowCount(List<String> restrictions, Object... params);
	
}
