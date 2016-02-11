package com.mwronski.jsql.postgresql;

import com.mwronski.jsql.JSql;
import com.mwronski.jsql.common.SQLSelectWhereTest;
import com.mwronski.jsql.grammar.SqlGrammar;
import com.mwronski.jsql.grammar.postgresql.PostgreSQL;

import static com.mwronski.jsql.test.TestUtil.assertQueryExecutable;
import static junit.framework.Assert.assertEquals;

public class PostgreSQLSelectWhereTest extends SQLSelectWhereTest {

    @Override
    public SqlGrammar getSqlGrammar() {
        return new PostgreSQL();
    }

    @Override
    protected void verifySelectRegexp(final JSql sql) {
        assertEquals("SELECT e.string FROM Entity e WHERE e.string~*?1", sql.toString());
        assertQueryExecutable(sql, em);
    }

    @Override
    protected void verifySelectWhereInsensitiveValue(final JSql sql) {
        assertEquals("SELECT id, string FROM Entity WHERE id=?1 AND lower(string)=?2", sql.toString());
        assertQueryExecutable(sql, em);
    }

    @Override
    protected void verifySelectWhereInsensitiveVar(final JSql sql) {
        assertEquals(
                "SELECT e1.id, e1.string FROM Entity e1, Entity e2 WHERE e1.id=?1 AND lower(e1.string)=lower(e2.string)",
                sql.toString());
        assertQueryExecutable(sql, em);
    }

}
