package com.mwronski.jsql;

import com.mwronski.jsql.parser.dql.Select;
import com.mwronski.jsql.test.Entity;
import com.mwronski.jsql.test.JSqlTestCase;
import org.junit.Test;

/**
 * Test cases for checking join statements.
 * 
 * @date 27-06-2013
 * @author Michal Wronski
 * 
 */
public abstract class AbstractJoinTestSuite extends JSqlTestCase {

    @Test
    public final void testJoin() {
        Entity entity1 = sql.alias(Entity.class, "e1");
        Entity entity2 = sql.alias(Entity.class, "e2");
        Select select = sql.select(entity1.getId(), entity2.getString()).from(entity1);
        select.join(entity2).on(sql.cond().eq(entity1.getId(), entity2.getId()));
        verifyJoin(sql);
    }

    /**
     * Verify SQL statement for: <br>
     * SELECT e1.id, e1.string, e2.id, e2.string FROM Entity e1 JOIN Entity e2
     * ON e1.id=e2.id
     */
    protected abstract void verifyJoin(JSql sql);

    @Test
    public final void testJoinAll() {
        Entity entity1 = sql.alias(Entity.class, "e1");
        Entity entity2 = sql.alias(Entity.class, "e2");
        Select select = sql.select().from(entity1);
        select.join(entity2).on(sql.cond().eq(entity1.getId(), entity2.getId()));
        verifyJoinAll(sql);
    }

    /**
     * Verify SQL statement for: <br>
     * SELECT * FROM Entity e1 JOIN Entity e2 ON e1.id=e2.id
     */
    protected abstract void verifyJoinAll(JSql sql);

    @Test
    public final void testJoinAllFromTable() {
        Entity entity1 = sql.alias(Entity.class, "e1");
        Entity entity2 = sql.alias(Entity.class, "e2");
        Select select = sql.select(entity1).from(entity1);
        select.join(entity2).on(sql.cond().eq(entity1.getId(), entity2.getId()));
        verifyJoinAllFromTable(sql);
    }

    /**
     * Verify SQL statement for: <br>
     * SELECT e1.* FROM Entity e1 JOIN Entity e2 ON e1.id=e2.id
     */
    protected abstract void verifyJoinAllFromTable(JSql sql);

    @Test
    public final void testLeftJoin() {
        Entity entity1 = sql.alias(Entity.class, "e1");
        Entity entity2 = sql.alias(Entity.class, "e2");
        Select select = sql.select(entity1.getId(), entity2.getString()).from(entity1);
        select.leftJoin(entity2).on(sql.cond().eq(entity1.getId(), entity2.getId()));
        verifyLeftJoin(sql);
    }

    @Test
    public final void testLeftJoin_FlatAPI() {
        Entity entity1 = sql.alias(Entity.class, "e1");
        Entity entity2 = sql.alias(Entity.class, "e2");
        Select select = sql.select(entity1.getId(), entity2.getString()).from(entity1);
        select.join().left().table(entity2).on(sql.cond().eq(entity1.getId(), entity2.getId()));
        verifyLeftJoin(sql);
    }

    /**
     * Verify SQL statement for: <br>
     * SELECT e1.id, e2.string FROM Entity e1 LEFT JOIN Entity e2 ON e1.id=e2.id
     */
    protected abstract void verifyLeftJoin(JSql sql);

    @Test
    public final void testRightJoin() {
        Entity entity1 = sql.alias(Entity.class, "e1");
        Entity entity2 = sql.alias(Entity.class, "e2");
        Select select = sql.select(entity1.getId(), entity2.getString()).from(entity1);
        select.rightJoin(entity2).on(sql.cond().eq(entity1.getId(), entity2.getId()));
        verifyRightJoin(sql);
    }

    @Test
    public final void testRightJoin_FlatAPI() {
        Entity entity1 = sql.alias(Entity.class, "e1");
        Entity entity2 = sql.alias(Entity.class, "e2");
        Select select = sql.select(entity1.getId(), entity2.getString()).from(entity1);
        select.join().right().table(entity2).on(sql.cond().eq(entity1.getId(), entity2.getId()));
        verifyRightJoin(sql);
    }

    /**
     * Verify SQL statement for: <br>
     * SELECT e1.id, e2.string FROM Entity e1 RIGHT JOIN Entity e2 ON
     * e1.id=e2.id
     */
    protected abstract void verifyRightJoin(JSql sql);

    @Test
    public final void testLeftOuterJoin() {
        Entity entity1 = sql.alias(Entity.class, "e1");
        Entity entity2 = sql.alias(Entity.class, "e2");
        Select select = sql.select(entity1.getId(), entity2.getString()).from(entity1);
        select.leftOuterJoin(entity2).on(sql.cond().eq(entity1.getId(), entity2.getId()));
        verifyLeftOuterJoin(sql);
    }

    @Test
    public final void testLeftOuterJoin_FlatAPI() {
        Entity entity1 = sql.alias(Entity.class, "e1");
        Entity entity2 = sql.alias(Entity.class, "e2");
        Select select = sql.select(entity1.getId(), entity2.getString()).from(entity1);
        select.join().left().outer().table(entity2).on(sql.cond().eq(entity1.getId(), entity2.getId()));
        verifyLeftOuterJoin(sql);
    }

    /**
     * Verify SQL statement for: <br>
     * SELECT e1.id, e2.string FROM Entity e1 LEFT OUTER JOIN Entity e2 ON
     * e1.id=e2.id
     */
    protected abstract void verifyLeftOuterJoin(JSql sql);

    @Test
    public final void testLeftInnerJoin() {
        Entity entity1 = sql.alias(Entity.class, "e1");
        Entity entity2 = sql.alias(Entity.class, "e2");
        Select select = sql.select(entity1.getId(), entity2.getString()).from(entity1);
        select.leftInnerJoin(entity2).on(sql.cond().eq(entity1.getId(), entity2.getId()));
        verifyLeftInnerJoin(sql);
    }

    @Test
    public final void testLeftInnerJoin_FlatAPI() {
        Entity entity1 = sql.alias(Entity.class, "e1");
        Entity entity2 = sql.alias(Entity.class, "e2");
        Select select = sql.select(entity1.getId(), entity2.getString()).from(entity1);
        select.join().inner().left().table(entity2).on(sql.cond().eq(entity1.getId(), entity2.getId()));
        verifyLeftInnerJoin(sql);
    }

    /**
     * Verify SQL statement for: <br>
     * SELECT e1.id, e2.string FROM Entity e1 LEFT INNER JOIN Entity e2 ON
     * e1.id=e2.id
     */
    protected abstract void verifyLeftInnerJoin(JSql sql);

    @Test
    public final void testRightOuterJoin() {
        Entity entity1 = sql.alias(Entity.class, "e1");
        Entity entity2 = sql.alias(Entity.class, "e2");
        Select select = sql.select(entity1.getId(), entity2.getString()).from(entity1);
        select.rightOuterJoin(entity2).on(sql.cond().eq(entity1.getId(), entity2.getId()));
        verifyRightOuterJoin(sql);
    }

    /**
     * Verify SQL statement for: <br>
     * SELECT e1.id, e2.string FROM Entity e1 RIGHT OUTER JOIN Entity e2 ON
     * e1.id=e2.id
     */
    protected abstract void verifyRightOuterJoin(JSql sql);

    @Test
    public final void testRightInnerJoin() {
        Entity entity1 = sql.alias(Entity.class, "e1");
        Entity entity2 = sql.alias(Entity.class, "e2");
        Select select = sql.select(entity1.getId(), entity2.getString()).from(entity1);
        select.rightInnerJoin(entity2).on(sql.cond().eq(entity1.getId(), entity2.getId()));
        verifyRightInnerJoin(sql);
    }

    /**
     * Verify SQL statement for: <br>
     * SELECT e1.id, e2.string FROM Entity e1 RIGHT INNER JOIN Entity e2 ON
     * e1.id=e2.id
     */
    protected abstract void verifyRightInnerJoin(JSql sql);

    @Test
    public final void testJoinWhere() {
        Entity entity1 = sql.alias(Entity.class, "e1");
        Entity entity2 = sql.alias(Entity.class, "e2");
        Select select = sql.select(entity1.getId(), entity2.getString()).from(entity1);
        select.join(entity2).on(sql.cond().eq(entity1.getId(), entity2.getId()));
        select.where(sql.cond().eq(entity1.getString(), "aaa").and().eq(entity2.getString(), "bbb"));
        verifyJoinWhere(sql);
    }

    @Test
    public final void testJoinWhere_FlatAPI() {
        Entity entity1 = sql.alias(Entity.class, "e1");
        Entity entity2 = sql.alias(Entity.class, "e2");
        Select select = sql.select(entity1.getId(), entity2.getString()).from(entity1);
        select.join().table(entity2).on(sql.cond().eq(entity1.getId(), entity2.getId()));
        select.where().eq(entity1.getString(), "aaa").and().eq(entity2.getString(), "bbb");
        verifyJoinWhere(sql);
    }

    /**
     * Verify SQL statement for: <br>
     * SELECT e1.id, e2.string FROM Entity e1 JOIN Entity e2 ON e1.id=e2.id
     * WHERE e1.string=?1 AND e2.string=?2
     */
    protected abstract void verifyJoinWhere(JSql sql);
}
