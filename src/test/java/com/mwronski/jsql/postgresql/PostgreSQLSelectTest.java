package com.mwronski.jsql.postgresql;

import com.mwronski.jsql.JSql;
import com.mwronski.jsql.common.SQLSelectTest;
import com.mwronski.jsql.grammar.SqlGrammar;
import com.mwronski.jsql.grammar.postgresql.PostgreSQL;

import static com.mwronski.jsql.test.TestUtil.assertQueryExecutable;
import static junit.framework.Assert.assertEquals;

public class PostgreSQLSelectTest extends SQLSelectTest {

    @Override
    public SqlGrammar getSqlGrammar() {
        return new PostgreSQL();
    }

    @Override
    protected void verifySelectManyTables(final JSql sql) {
        assertEquals("SELECT e1.*, e2.* FROM Entity e1, Entity e2", sql.toString());
        // no verification made whether query can be executed as hibernate
        // doesn't allow to execute this kind of native queries
    }

    @Override
    protected void verifySelectColumnsFromManyTables_ColumnsFromSingleTable(JSql sql) {
        assertEquals("SELECT e1.* FROM Entity e1, Entity e2", sql.toString());
        assertQueryExecutable(sql, em);
    }
}
