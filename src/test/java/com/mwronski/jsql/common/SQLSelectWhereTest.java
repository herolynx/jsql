package com.mwronski.jsql.common;

import com.mwronski.jsql.AbstractSelectWhereTestSuite;
import com.mwronski.jsql.JSql;

import static com.mwronski.jsql.test.TestUtil.assertQueryExecutable;
import static junit.framework.Assert.assertEquals;

/**
 * Test cases check common conditions of SQL statements between grammars.
 * 
 * @date 27-06-2013
 * @author Michal Wronski
 * 
 */
public abstract class SQLSelectWhereTest extends AbstractSelectWhereTestSuite {

    @Override
    protected void verifySelectWhere(final JSql sql) {
        assertEquals("SELECT id, string FROM Entity WHERE id=?1 AND string=?2", sql.toString());
        assertQueryExecutable(sql, em);
    }

    @Override
    protected void verifySelectWhereWithNullParam(final JSql sql) {
        assertEquals("SELECT id, string FROM Entity WHERE id=?1 AND string IS NULL", sql.toString());
        assertQueryExecutable(sql, em);
    }

    @Override
    protected void verifySelectWhereWithNullParamToSkip(final JSql sql) {
        assertEquals("SELECT id, string FROM Entity", sql.toString());
        assertQueryExecutable(sql, em);
    }

    @Override
    protected void verifySelectWhereWithSkipableParams(final JSql sql) {
        assertEquals("SELECT id, string FROM Entity WHERE id=?1", sql.toString());
        assertQueryExecutable(sql, em);
    }

    @Override
    protected void verifySelectWhereFirstParamNull(final JSql sql) {
        assertEquals("SELECT id, string FROM Entity WHERE string=?1", sql.toString());
        assertQueryExecutable(sql, em);
    }

    @Override
    protected void verifySelectWhereWithAlias(final JSql sql) {
        assertEquals("SELECT e.id, e.string FROM Entity e WHERE e.id=?1 AND e.string=?2", sql.toString());
        assertQueryExecutable(sql, em);
    }

    @Override
    protected void verifySelectWhereNested(final JSql sql) {
        assertEquals("SELECT id, string FROM Entity WHERE id=?1 AND (string=?2 OR string=?3)", sql.toString());
        assertQueryExecutable(sql, em);
    }

    @Override
    protected void verifySelectWhereNestedWithSkipableParms(final JSql sql) {
        assertEquals("SELECT id, string FROM Entity WHERE id=?1", sql.toString());
        assertQueryExecutable(sql, em);
    }

    @Override
    protected void verifySelectWhereWithNestedAliases(final JSql sql) {
        assertEquals("SELECT e.id, e.string FROM Entity e WHERE e.id=?1 AND (e.string=?2 OR e.string=?3)",
                sql.toString());
        assertQueryExecutable(sql, em);
    }

    @Override
    protected void verifySelectColumnsFromManyTablesWithWhere(final JSql sql) {
        assertEquals("SELECT e1.id, e2.string FROM Entity e1, Entity e2 WHERE e1.id=e2.id", sql.toString());
        assertQueryExecutable(sql, em);
    }

    @Override
    protected void verifySelectWhereWithInCondition(final JSql sql) {
        assertEquals("SELECT id, string FROM Entity WHERE id IN (?1)", sql.toString());
        assertQueryExecutable(sql, em);
    }

    @Override
    protected void verifySelectWhereWithInSkipableCondition(final JSql sql) {
        assertEquals("SELECT id, string FROM Entity", sql.toString());
        assertQueryExecutable(sql, em);
    }

    @Override
    protected void verifySelectWhereWithInNotSkipableCondition(final JSql sql) {
        assertEquals("SELECT id, string FROM Entity WHERE id IS NULL", sql.toString());
        assertQueryExecutable(sql, em);
    }

    @Override
    protected void verifySelectWhereRelations(JSql sql) {
        assertEquals("SELECT id, string FROM Entity WHERE id=?1 AND id>?2 AND id<?3 AND id>=?4 AND id<=?5",
                sql.toString());
        assertQueryExecutable(sql, em);
    }

}
