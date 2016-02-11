package com.mwronski.jsql;

import com.mwronski.jsql.parser.dql.Condition;
import com.mwronski.jsql.parser.dql.Select;
import com.mwronski.jsql.test.Entity;
import com.mwronski.jsql.test.JSqlTestCase;
import org.junit.Test;

/**
 * Test cases for checking conditions in where clause.
 * 
 * @date 27-06-2013
 * @author Michal Wronski
 * 
 */
public abstract class AbstractSelectWhereTestSuite extends JSqlTestCase {

    @Test
    public final void testSelectWhere() {
        Entity entity = sql.alias(Entity.class);
        sql.select(entity.getId(), entity.getString()).from(entity)
                .where(sql.cond().eq(entity.getId(), 5l).and().eq(entity.getString(), "aaa"));
        verifySelectWhere(sql);
    }

    @Test
    public final void testSelectWhere_FlatAPI() {
        Entity entity = sql.alias(Entity.class);
        sql.select(entity.getId(), entity.getString()).from(entity).where().eq(entity.getId(), 5l).and()
                .eq(entity.getString(), "aaa");
        verifySelectWhere(sql);
    }

    /**
     * Verify SQL statement for: <br>
     * SELECT id, string FROM Entity WHERE id=5 AND string='aaa'
     */
    protected abstract void verifySelectWhere(JSql sql);

    @Test
    public final void testSelectWhereNullValue() {
        Entity entity = sql.alias(Entity.class);
        sql.select(entity.getId(), entity.getString()).from(entity)
                .where(sql.cond().eq(entity.getId(), 5l).and().eq(entity.getString(), null));
        verifySelectWhereWithNullParam(sql);
    }

    @Test
    public final void testSelectWhereNullValue_FlatAPI() {
        Entity entity = sql.alias(Entity.class);
        sql.select(entity.getId(), entity.getString()).from(entity).where().eq(entity.getId(), 5l).and()
                .eq(entity.getString(), null);
        verifySelectWhereWithNullParam(sql);
    }

    /**
     * Verify SQL statement for: <br>
     * SELECT id, string FROM Entity WHERE id=5 AND string is null
     */
    protected abstract void verifySelectWhereWithNullParam(JSql sql);

    @Test
    public final void testSelectWhereNullValueSkipable() {
        Entity entity = sql.alias(Entity.class);
        sql.select(entity.getId(), entity.getString()).from(entity).where(sql.cond().eq(entity.getId(), null, true));
        verifySelectWhereWithNullParamToSkip(sql);
    }

    @Test
    public final void testSelectWhereNullValueSkipable_FlatAPI() {
        Entity entity = sql.alias(Entity.class);
        sql.select(entity.getId(), entity.getString()).from(entity).where().eq(entity.getId(), null, true);
        verifySelectWhereWithNullParamToSkip(sql);
    }

    /**
     * Verify SQL statement for: <br>
     * SELECT id, string FROM Entity where id is null.<br>
     * Note: ID will be skipped as it's null.
     */
    protected abstract void verifySelectWhereWithNullParamToSkip(JSql sql);

    @Test
    public final void testSelectWhereSkipable() {
        Entity entity = sql.alias(Entity.class);
        sql.select(entity.getId(), entity.getString()).from(entity)
                .where(sql.cond().eq(entity.getId(), 5l).and().eq(entity.getString(), null, true));
        verifySelectWhereWithSkipableParams(sql);
    }

    /**
     * Verify SQL statement for: <br>
     * SELECT id, string FROM Entity WHERE id=5 and string=null.<br>
     * Note: String will be skipped as it's null.
     */
    protected abstract void verifySelectWhereWithSkipableParams(JSql sql);

    @Test
    public final void testSelectWhereSkipable_FirstRelationNull() {
        Entity entity = sql.alias(Entity.class);
        sql.select(entity.getId(), entity.getString()).from(entity)
                .where(sql.cond().eq(entity.getId(), null, true).and().eq(entity.getString(), "aaa"));
        verifySelectWhereFirstParamNull(sql);
    }

    /**
     * Verify SQL statement for: <br>
     * SELECT id, string FROM Entity WHERE id is null and string='aaa'. <br>
     * Note: ID will be skipped as it's null.
     */
    protected abstract void verifySelectWhereFirstParamNull(JSql sql);

    @Test
    public final void testSelectWhere_Alias() {
        Entity entity = sql.alias(Entity.class, "e");
        sql.select(entity.getId(), entity.getString()).from(entity)
                .where(sql.cond().eq(entity.getId(), 5l).and().eq(entity.getString(), "aaa"));
        verifySelectWhereWithAlias(sql);
    }

    /**
     * Verify SQL statement for: <br>
     * SELECT e.id, e.string FROM Entity e WHERE e.id=5 AND e.string='aaa'
     */
    protected abstract void verifySelectWhereWithAlias(JSql sql);

    @Test
    public final void testSelectWhere_Nested() {
        Entity entity = sql.alias(Entity.class);
        Condition subCondition = sql.cond().eq(entity.getString(), "aaa").or().eq(entity.getString(), "bbb");
        sql.select(entity.getId(), entity.getString()).from(entity)
                .where(sql.cond().eq(entity.getId(), 5l).and(subCondition));
        verifySelectWhereNested(sql);
    }

    /**
     * Verify SQL statement for: <br>
     * SELECT id, string FROM Entity WHERE id=5 AND ( string='aaa' OR
     * string='bbb' )
     */
    protected abstract void verifySelectWhereNested(JSql sql);

    @Test
    public final void testSelectWhere_NestedSkipable() {
        Entity entity = sql.alias(Entity.class);
        Condition subCondition = sql.cond().eq(entity.getString(), null, true).or().eq(entity.getString(), null, true);
        sql.select(entity.getId(), entity.getString()).from(entity)
                .where(sql.cond().eq(entity.getId(), 5l).and(subCondition));
        verifySelectWhereNestedWithSkipableParms(sql);
    }

    @Test
    public final void testSelectWhere_NestedSkipable_FlatAPI() {
        Entity entity = sql.alias(Entity.class);
        Condition subCondition = sql.cond().eq(entity.getString(), null, true).or().eq(entity.getString(), null, true);
        sql.select(entity.getId(), entity.getString()).from(entity).where().eq(entity.getId(), 5l).and(subCondition);
        verifySelectWhereNestedWithSkipableParms(sql);
    }

    /**
     * Verify SQL statement for: <br>
     * SELECT id, string FROM Entity WHERE id=5 AND ( string=null OR string=null
     * ). <br>
     * Note: String params will be skipped.
     */
    protected abstract void verifySelectWhereNestedWithSkipableParms(JSql sql);

    @Test
    public final void testSelectWhere_NestedAlias() {
        Entity entity = sql.alias(Entity.class, "e");
        Condition subCondition = sql.cond().eq(entity.getString(), "aaa").or().eq(entity.getString(), "bbb");
        sql.select(entity.getId(), entity.getString()).from(entity)
                .where(sql.cond().eq(entity.getId(), 5l).and(subCondition));
        verifySelectWhereWithNestedAliases(sql);
    }

    /**
     * Verify SQL statement for: <br>
     * SELECT e.id, e.string FROM Entity e WHERE e.id=5 AND ( e.string='aaa' OR
     * e.string='bbb' )
     */
    protected abstract void verifySelectWhereWithNestedAliases(JSql sql);

    @Test
    public final void testSelectColumnsFromManyTables_Where() {
        Entity entity1 = sql.alias(Entity.class, "e1");
        Entity entity2 = sql.alias(Entity.class, "e2");
        Select select = sql.select(entity1.getId(), entity2.getString()).from(entity1, entity2);
        select.where(sql.cond().eq(entity1.getId(), entity2.getId(), true));
        verifySelectColumnsFromManyTablesWithWhere(sql);
    }

    /**
     * Verify SQL statement for: <br>
     * SELECT e1.id, e2.string FROM Entity e1, Entity e2 WHERE e1.id=e2.id
     */
    protected abstract void verifySelectColumnsFromManyTablesWithWhere(JSql sql);

    @Test
    public final void testSelectIn() {
        Entity entity = sql.alias(Entity.class);
        sql.select(entity.getId(), entity.getString()).from(entity).where(sql.cond().in(entity.getId(), 1l, 3l, 5l));
        verifySelectWhereWithInCondition(sql);
    }

    /**
     * Verify SQL statement for: <br>
     * SELECT id, string FROM Entity WHERE id IN (1,3,5)
     */
    protected abstract void verifySelectWhereWithInCondition(JSql sql);

    @Test
    public final void testSelectInNullSkipable() {
        Entity entity = sql.alias(Entity.class);
        sql.select(entity.getId(), entity.getString()).from(entity)
                .where(sql.cond().in(entity.getId(), true, (Object[]) null));
        verifySelectWhereWithInSkipableCondition(sql);
    }

    /**
     * Verify SQL statement for: <br>
     * SELECT id, string FROM Entity WHERE id IN (null). <br>
     * Note: ID will be skipped.
     */
    protected abstract void verifySelectWhereWithInSkipableCondition(JSql sql);

    @Test
    public final void testSelectInNullNotSkipable() {
        Entity entity = sql.alias(Entity.class);
        sql.select(entity.getId(), entity.getString()).from(entity)
                .where(sql.cond().in(entity.getId(), (Object[]) null));
        verifySelectWhereWithInNotSkipableCondition(sql);
    }

    /**
     * Verify SQL statement for: <br>
     * SELECT id, string FROM Entity WHERE id IN (null).
     */
    protected abstract void verifySelectWhereWithInNotSkipableCondition(JSql sql);

    @Test
    public final void testSelectRegexp() {
        Entity entity = sql.alias(Entity.class, "e");
        sql.select(entity.getString()).from(entity).where(sql.cond().regex(entity.getString(), "aaa"));
        verifySelectRegexp(sql);
    }

    /**
     * Verify SQL statement for: <br>
     * SELECT e.string FROM Entity e WHERE e.string=regexp('aaa')
     */
    protected abstract void verifySelectRegexp(JSql sql);

    @Test
    public final void testSelectWhereCaseInsensitiveValue() {
        Entity entity = sql.alias(Entity.class);
        sql.select(entity.getId(), entity.getString()).from(entity)
                .where(sql.cond().eq(entity.getId(), 5l).and().eqi(entity.getString(), "AAA"));
        verifySelectWhereInsensitiveValue(sql);
    }

    /**
     * Verify SQL statement for: <br>
     * SELECT id, string FROM Entity WHERE id=5 AND string=ignoreCase('AAA')
     */
    protected abstract void verifySelectWhereInsensitiveValue(JSql sql);

    @Test
    public final void testSelectWhereCaseInsensitiveVar() {
        Entity entity = sql.alias(Entity.class, "e1");
        Entity otherEntity = sql.alias(Entity.class, "e2");
        sql.select(entity.getId(), entity.getString()).from(entity, otherEntity)
                .where(sql.cond().eq(entity.getId(), 5l).and().eqi(entity.getString(), otherEntity.getString()));
        verifySelectWhereInsensitiveVar(sql);
    }

    /**
     * Verify SQL statement for: <br>
     * SELECT id, string FROM Entity WHERE id=5 AND string=ignoreCase(e2.string)
     */
    protected abstract void verifySelectWhereInsensitiveVar(JSql sql);

    @Test
    public final void testSelectWhereRelations() {
        Entity entity = sql.alias(Entity.class);
        Condition cond = sql.cond();
        cond.eq(entity.getId(), 5l);
        cond.and().gt(entity.getId(), 6l);
        cond.and().lt(entity.getId(), 7l);
        cond.and().eg(entity.getId(), 8l);
        cond.and().el(entity.getId(), 9l);
        sql.select(entity.getId(), entity.getString()).from(entity).where(cond);
        verifySelectWhereRelations(sql);
    }

    /**
     * Verify SQL statement for: <br>
     * SELECT id, string FROM Entity WHERE id=5 AND id>6 and id<7 and id>=8 and
     * id<=9
     */
    protected abstract void verifySelectWhereRelations(JSql sql);
}
