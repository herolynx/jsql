package com.mwronski.jsql.builder;

import java.util.Map;

/**
 * Interface for builder of SQL statements
 * 
 * @date 02-04-2013
 * @author Michal Wronski
 * 
 */
public interface SqlCommandBuilder {

    /**
     * Get built SQL statement
     * 
     * @return
     */
    String asSQL();

    /**
     * Get SQL parameters given in built SQL statement
     * 
     * @return
     */
    Map<Integer, Object> getSQLParams();

}
