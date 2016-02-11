package com.mwronski.jsql.grammar.jpql;

import com.mwronski.jsql.builder.SqlSelectBuilder;
import com.mwronski.jsql.grammar.SqlGrammar;

/**
 * Grammar for JP-QL statements.
 * 
 * @date 05-07-2013
 * @author Michal Wronski
 * 
 */
public final class JPQL implements SqlGrammar {

    @Override
    public SqlSelectBuilder selectBuilder() {
        return new JPQLSelectBuilder();
    }

    @Override
    public boolean isNative() {
        return false;
    }

}
