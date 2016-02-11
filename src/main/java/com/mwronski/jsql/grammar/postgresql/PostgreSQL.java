package com.mwronski.jsql.grammar.postgresql;

import com.mwronski.jsql.builder.SqlSelectBuilder;
import com.mwronski.jsql.grammar.SqlGrammar;

/**
 * Grammar for PostgreSQL. <br>
 * Note: tested only on PostgreSQL 8.4
 * 
 * @date 28-06-2013
 * @author Michal Wronski
 * 
 */
public final class PostgreSQL implements SqlGrammar {

    @Override
    public SqlSelectBuilder selectBuilder() {
        return new PostgreSQLSelectBuilder();
    }

    @Override
    public boolean isNative() {
        return true;
    }

}
