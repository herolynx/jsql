package com.mwronski.jsql.jpql;

import com.mwronski.jsql.JSql;
import com.mwronski.jsql.common.SQLSelectWhereTest;
import com.mwronski.jsql.grammar.SqlGrammar;
import com.mwronski.jsql.grammar.jpql.JPQL;

import static com.mwronski.jsql.test.TestUtil.assertQueryExecutable;
import static junit.framework.Assert.assertEquals;

public class JPQLSelectWhereTest extends SQLSelectWhereTest {

    @Override
    public SqlGrammar getSqlGrammar() {
        return new JPQL();
    }

    @Override
    protected void verifySelectWhere(final JSql sql) {
        assertEquals("SELECT Entity.id, Entity.string FROM Entity Entity WHERE Entity.id=?1 AND Entity.string=?2",
                sql.toString());
        assertQueryExecutable(sql, em);
    }

    @Override
    protected void verifySelectWhereWithNullParam(final JSql sql) {
        assertEquals("SELECT Entity.id, Entity.string FROM Entity Entity WHERE Entity.id=?1 AND Entity.string IS NULL",
                sql.toString());
        assertQueryExecutable(sql, em);
    }

    @Override
    protected void verifySelectWhereWithNullParamToSkip(final JSql sql) {
        assertEquals("SELECT Entity.id, Entity.string FROM Entity Entity", sql.toString());
        assertQueryExecutable(sql, em);
    }

    @Override
    protected void verifySelectWhereWithSkipableParams(final JSql sql) {
        assertEquals("SELECT Entity.id, Entity.string FROM Entity Entity WHERE Entity.id=?1", sql.toString());
        assertQueryExecutable(sql, em);
    }

    @Override
    protected void verifySelectWhereFirstParamNull(final JSql sql) {
        assertEquals("SELECT Entity.id, Entity.string FROM Entity Entity WHERE Entity.string=?1", sql.toString());
        assertQueryExecutable(sql, em);
    }

    @Override
    protected void verifySelectWhereNested(final JSql sql) {
        assertEquals(
                "SELECT Entity.id, Entity.string FROM Entity Entity WHERE Entity.id=?1 AND (Entity.string=?2 OR Entity.string=?3)",
                sql.toString());
        assertQueryExecutable(sql, em);
    }

    @Override
    protected void verifySelectWhereNestedWithSkipableParms(final JSql sql) {
        assertEquals("SELECT Entity.id, Entity.string FROM Entity Entity WHERE Entity.id=?1", sql.toString());
        assertQueryExecutable(sql, em);
    }

    @Override
    protected void verifySelectWhereWithInCondition(final JSql sql) {
        assertEquals("SELECT Entity.id, Entity.string FROM Entity Entity WHERE Entity.id IN (?1)", sql.toString());
        assertQueryExecutable(sql, em);
    }

    @Override
    protected void verifySelectWhereWithInSkipableCondition(final JSql sql) {
        assertEquals("SELECT Entity.id, Entity.string FROM Entity Entity", sql.toString());
        assertQueryExecutable(sql, em);
    }

    @Override
    protected void verifySelectWhereWithInNotSkipableCondition(final JSql sql) {
        assertEquals("SELECT Entity.id, Entity.string FROM Entity Entity WHERE Entity.id IS NULL", sql.toString());
        assertQueryExecutable(sql, em);
    }

    @Override
    protected void verifySelectWhereRelations(JSql sql) {
        assertEquals(
                "SELECT Entity.id, Entity.string FROM Entity Entity WHERE Entity.id=?1 AND Entity.id>?2 AND Entity.id<?3 AND Entity.id>=?4 AND Entity.id<=?5",
                sql.toString());
        assertQueryExecutable(sql, em);
    }

    @Override
    protected void verifySelectRegexp(JSql sql) {
        assertEquals("SELECT e.string FROM Entity e WHERE e.string LIKE ?1", sql.toString());
        assertQueryExecutable(sql, em);
    }

    @Override
    protected void verifySelectWhereInsensitiveValue(JSql sql) {
        // TODO Auto-generated method stub
        assertEquals(
                "SELECT Entity.id, Entity.string FROM Entity Entity WHERE Entity.id=?1 AND lower(Entity.string)=?2",
                sql.toString());
        assertQueryExecutable(sql, em);
    }

    @Override
    protected void verifySelectWhereInsensitiveVar(JSql sql) {
        // TODO Auto-generated method stub
        assertEquals(
                "SELECT e1.id, e1.string FROM Entity e1, Entity e2 WHERE e1.id=?1 AND lower(e1.string)=lower(e2.string)",
                sql.toString());
        assertQueryExecutable(sql, em);
    }
}
