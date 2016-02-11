package com.mwronski.jsql.postgresql;

import com.mwronski.jsql.common.QueryTest;
import com.mwronski.jsql.grammar.SqlGrammar;
import com.mwronski.jsql.grammar.postgresql.PostgreSQL;

public class PostgreSQLQueryTest extends QueryTest {

    @Override
    public SqlGrammar getSqlGrammar() {
        return new PostgreSQL();
    }

}
