package com.mwronski.jsql.common;

import com.mwronski.jsql.AbstractSelectTestSuite;
import com.mwronski.jsql.JSql;

import static com.mwronski.jsql.test.TestUtil.assertQueryExecutable;
import static junit.framework.Assert.assertEquals;

/**
 * Test cases check basic and common SQL statements between grammars.
 * 
 * @date 27-06-2013
 * @author Michal Wronski
 * 
 */
public abstract class SQLSelectTest extends AbstractSelectTestSuite {

    @Override
    protected void verifySelectAll(final JSql sql) {
        assertEquals("SELECT Entity.* FROM Entity", sql.toString());
        assertQueryExecutable(sql, em);
    }

    @Override
    protected void verifySelectAllWithAlias(final JSql sql) {
        assertEquals("SELECT e.* FROM Entity e", sql.toString());
        assertQueryExecutable(sql, em);
    }

    @Override
    protected void verifySelectWithColumns(final JSql sql) {
        assertEquals("SELECT id, string FROM Entity", sql.toString());
        assertQueryExecutable(sql, em);
    }

    @Override
    protected void verifySelectWithColumnsAndAlias(final JSql sql) {
        assertEquals("SELECT e.id, e.string FROM Entity e", sql.toString());
        assertQueryExecutable(sql, em);
    }

    @Override
    protected void verifySelectCheckColumnNames(final JSql sql) {
        assertEquals("SELECT id, string, secondString FROM Entity", sql.toString());
        assertQueryExecutable(sql, em);
    }

    @Override
    protected void verifySelectColumnsFromManyTables(final JSql sql) {
        assertEquals("SELECT e1.id, e2.string FROM Entity e1, Entity e2", sql.toString());
        assertQueryExecutable(sql, em);
    }

    @Override
    protected void verifySelectCount(final JSql sql) {
        assertEquals("SELECT COUNT(*) FROM Entity", sql.toString());
        assertQueryExecutable(sql, em);
    }

    @Override
    protected void verifySelectCountWithColumns(final JSql sql) {
        assertEquals("SELECT id, COUNT(*) FROM Entity GROUP BY id", sql.toString());
        assertQueryExecutable(sql, em);
    }

    @Override
    protected void verifySelectOrder(final JSql sql) {
        assertEquals("SELECT e.* FROM Entity e ORDER BY e.string", sql.toString());
        assertQueryExecutable(sql, em);
    }

    @Override
    protected void verifySelectOrderByManyColumns(final JSql sql) {
        assertEquals("SELECT e.* FROM Entity e ORDER BY e.string, e.id", sql.toString());
        assertQueryExecutable(sql, em);
    }

    @Override
    protected void verifySelectOrderByManyColumnsDesc(final JSql sql) {
        assertEquals("SELECT e.* FROM Entity e ORDER BY e.string DESC, e.id", sql.toString());
        assertQueryExecutable(sql, em);
    }

    @Override
    protected void verifySelectOrderByNull(final JSql sql) {
        assertEquals("SELECT e.* FROM Entity e", sql.toString());
        assertQueryExecutable(sql, em);
    }

    @Override
    protected void verifySelectDistinct(final JSql sql) {
        assertEquals("SELECT DISTINCT e.string FROM Entity e", sql.toString());
        assertQueryExecutable(sql, em);
    }

    @Override
    protected void verifySelectGroupBy(final JSql sql) {
        assertEquals("SELECT e.id, e.string FROM Entity e GROUP BY e.string, e.id", sql.toString());
        assertQueryExecutable(sql, em);
    }

    @Override
    protected void verifySelectGroupByWithColumns(final JSql sql) {
        assertEquals("SELECT e.string, e.id FROM Entity e GROUP BY e.string, e.id", sql.toString());
        assertQueryExecutable(sql, em);
    }

    @Override
    protected void verifySelectWhereGroupByOrderBy(JSql sql) {
        assertEquals("SELECT e.string FROM Entity e WHERE e.id=?1 GROUP BY e.string ORDER BY e.string", sql.toString());
        assertQueryExecutable(sql, em);
    }

}
