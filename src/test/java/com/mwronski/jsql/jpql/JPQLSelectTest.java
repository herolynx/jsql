package com.mwronski.jsql.jpql;

import com.mwronski.jsql.JSql;
import com.mwronski.jsql.common.SQLSelectTest;
import com.mwronski.jsql.grammar.SqlGrammar;
import com.mwronski.jsql.grammar.jpql.JPQL;

import static com.mwronski.jsql.test.TestUtil.assertQueryExecutable;
import static junit.framework.Assert.assertEquals;

public class JPQLSelectTest extends SQLSelectTest {

    @Override
    public SqlGrammar getSqlGrammar() {
        return new JPQL();
    }

    @Override
    protected void verifySelectAll(final JSql sql) {
        assertEquals("SELECT Entity FROM Entity Entity", sql.toString());
        assertQueryExecutable(sql, em);
    }

    @Override
    protected void verifySelectAllWithAlias(final JSql sql) {
        assertEquals("SELECT e FROM Entity e", sql.toString());
        assertQueryExecutable(sql, em);
    }

    @Override
    protected void verifySelectManyTables(final JSql sql) {
        assertEquals("SELECT e1, e2 FROM Entity e1, Entity e2", sql.toString());
        assertQueryExecutable(sql, em);
    }

    @Override
    protected void verifySelectColumnsFromManyTables_ColumnsFromSingleTable(JSql sql) {
        assertEquals("SELECT e1 FROM Entity e1, Entity e2", sql.toString());
        assertQueryExecutable(sql, em);
    }

    @Override
    protected void verifySelectWithColumns(final JSql sql) {
        assertEquals("SELECT Entity.id, Entity.string FROM Entity Entity", sql.toString());
        assertQueryExecutable(sql, em);
    }

    @Override
    protected void verifySelectCheckColumnNames(final JSql sql) {
        assertEquals("SELECT Entity.id, Entity.string, Entity.secondString FROM Entity Entity", sql.toString());
        assertQueryExecutable(sql, em);
    }

    @Override
    protected void verifySelectCount(final JSql sql) {
        assertEquals("SELECT COUNT(*) FROM Entity Entity", sql.toString());
        assertQueryExecutable(sql, em);
    }

    @Override
    protected void verifySelectCountWithColumns(final JSql sql) {
        assertEquals("SELECT Entity.id, COUNT(*) FROM Entity Entity GROUP BY Entity.id", sql.toString());
        assertQueryExecutable(sql, em);
    }

    @Override
    protected void verifySelectOrder(final JSql sql) {
        assertEquals("SELECT e FROM Entity e ORDER BY e.string", sql.toString());
        assertQueryExecutable(sql, em);
    }

    @Override
    protected void verifySelectOrderByManyColumns(final JSql sql) {
        assertEquals("SELECT e FROM Entity e ORDER BY e.string, e.id", sql.toString());
        assertQueryExecutable(sql, em);
    }

    @Override
    protected void verifySelectOrderByManyColumnsDesc(final JSql sql) {
        assertEquals("SELECT e FROM Entity e ORDER BY e.string DESC, e.id", sql.toString());
        assertQueryExecutable(sql, em);
    }

    @Override
    protected void verifySelectOrderByNull(final JSql sql) {
        assertEquals("SELECT e FROM Entity e", sql.toString());
        assertQueryExecutable(sql, em);
    }

}
