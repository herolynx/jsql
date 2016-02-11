package com.mwronski.jsql.grammar.postgresql;

import com.mwronski.jsql.grammar.common.SQLSelectBuilder;

/**
 * Class builds SQL SELECT commands for PostgreSQL. <br>
 * Note: tested only on PostgreSQL 8.4
 * 
 * @date 02-04-2013
 * @author Michal Wronski
 * 
 */
final class PostgreSQLSelectBuilder extends SQLSelectBuilder {

    @Override
    protected String toLowerString(String string) {
        return "lower(" + string + ")";
    }

    @Override
    protected String getRegExpWildcardMark() {
        return "~*";
    }

}
