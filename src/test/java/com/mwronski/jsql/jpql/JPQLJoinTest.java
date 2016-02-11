package com.mwronski.jsql.jpql;

import com.mwronski.jsql.AbstractJoinTestSuite;
import com.mwronski.jsql.JSql;
import com.mwronski.jsql.grammar.SqlGrammar;
import com.mwronski.jsql.grammar.jpql.JPQL;

import static com.mwronski.jsql.test.TestUtil.assertQueryExecutable;
import static junit.framework.Assert.assertEquals;

public class JPQLJoinTest extends AbstractJoinTestSuite {

    @Override
    public SqlGrammar getSqlGrammar() {
        return new JPQL();
    }

    @Override
    protected void verifyJoin(JSql sql) {
        assertEquals("SELECT e1.id, e2.string FROM Entity e1, Entity e2 WHERE (e1.id=e2.id)", sql.toString());
        assertQueryExecutable(sql, em);
    }

    @Override
    protected void verifyJoinAll(JSql sql) {
        assertEquals("SELECT e1, e2 FROM Entity e1, Entity e2 WHERE (e1.id=e2.id)", sql.toString());
        assertQueryExecutable(sql, em);
    }

    @Override
    protected void verifyJoinAllFromTable(JSql sql) {
        assertEquals("SELECT e1 FROM Entity e1, Entity e2 WHERE (e1.id=e2.id)", sql.toString());
        assertQueryExecutable(sql, em);
    }

    @Override
    protected void verifyLeftJoin(final JSql sql) {
        assertEquals("SELECT e1.id, e2.string FROM Entity e1, Entity e2 WHERE (e1.id=e2.id)", sql.toString());
        assertQueryExecutable(sql, em);
    }

    @Override
    protected void verifyRightJoin(JSql sql) {
        assertEquals("SELECT e1.id, e2.string FROM Entity e1, Entity e2 WHERE (e1.id=e2.id)", sql.toString());
        assertQueryExecutable(sql, em);
    }

    @Override
    protected void verifyLeftOuterJoin(final JSql sql) {
        assertEquals("SELECT e1.id, e2.string FROM Entity e1, Entity e2 WHERE (e1.id=e2.id)", sql.toString());
        assertQueryExecutable(sql, em);
    }

    @Override
    protected void verifyLeftInnerJoin(JSql sql) {
        assertEquals("SELECT e1.id, e2.string FROM Entity e1, Entity e2 WHERE (e1.id=e2.id)", sql.toString());
        assertQueryExecutable(sql, em);
    }

    @Override
    protected void verifyRightOuterJoin(JSql sql) {
        assertEquals("SELECT e1.id, e2.string FROM Entity e1, Entity e2 WHERE (e1.id=e2.id)", sql.toString());
        assertQueryExecutable(sql, em);
    }

    @Override
    protected void verifyRightInnerJoin(JSql sql) {
        assertEquals("SELECT e1.id, e2.string FROM Entity e1, Entity e2 WHERE (e1.id=e2.id)", sql.toString());
        assertQueryExecutable(sql, em);
    }

    @Override
    protected void verifyJoinWhere(final JSql sql) {
        assertEquals(
                "SELECT e1.id, e2.string FROM Entity e1, Entity e2 WHERE (e1.id=e2.id) AND e1.string=?1 AND e2.string=?2",
                sql.toString());
        assertQueryExecutable(sql, em);
    }
}
