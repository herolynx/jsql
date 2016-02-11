package com.mwronski.jsql.grammar;

import com.mwronski.jsql.builder.SqlSelectBuilder;

/**
 * Grammar supported by JSQL.
 * 
 * @date 28-06-2013
 * @author Michal Wronski
 * 
 */
public interface SqlGrammar {

    /**
     * Get builder for select commands
     * 
     * @return
     */
    SqlSelectBuilder selectBuilder();

    /**
     * Check whether build statements are in native SQL form or in SQL supported
     * by entities managers.
     * 
     * @return
     */
    boolean isNative();
}
