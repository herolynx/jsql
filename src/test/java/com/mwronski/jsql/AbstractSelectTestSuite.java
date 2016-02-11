package com.mwronski.jsql;

import com.mwronski.jsql.test.Entity;
import com.mwronski.jsql.test.JSqlTestCase;
import org.junit.Test;

/**
 * Test cases for basic select statements without where condition. Test cases
 * focus on selecting columns, order, count, distinct etc.
 * 
 * @date 27-06-2013
 * @author Michal Wronski
 * 
 */
public abstract class AbstractSelectTestSuite extends JSqlTestCase {

    @Test
    public final void testSelectAll() {
        Entity entity = sql.alias(Entity.class);
        sql.select().from(entity);
        verifySelectAll(sql);
    }

    /**
     * Verify SQL statement for: <br>
     * SELECT * FROM Entity
     */
    protected abstract void verifySelectAll(JSql sql);

    @Test
    public final void testSelectAll_Alias() {
        Entity entity = sql.alias(Entity.class, "e");
        sql.select().from(entity);
        verifySelectAllWithAlias(sql);
    }

    /**
     * Verify SQL statement for: <br>
     * SELECT e.* FROM Entity e
     */
    protected abstract void verifySelectAllWithAlias(JSql sql);

    @Test
    public final void testSelectWithColumns() {
        Entity entity = sql.alias(Entity.class);
        sql.select(entity.getId(), entity.getString()).from(entity);
        verifySelectWithColumns(sql);
    }

    /**
     * Verify SQL statement for: <br>
     * SELECT id, string FROM Entity
     */
    protected abstract void verifySelectWithColumns(JSql sql);

    @Test
    public final void testSelectWithColumns_Alias() {
        Entity entity = sql.alias(Entity.class, "e");
        sql.select(entity.getId(), entity.getString()).from(entity);
        verifySelectWithColumnsAndAlias(sql);
    }

    /**
     * Verify SQL statement for: <br>
     * SELECT e.id, e.string FROM Entity e
     */
    protected abstract void verifySelectWithColumnsAndAlias(JSql sql);

    @Test
    public final void testSelectCheckColumnNames() {
        Entity entity = sql.alias(Entity.class);
        sql.select(entity.getId(), entity.getString(), entity.getSecondString()).from(entity);
        verifySelectCheckColumnNames(sql);
    }

    /**
     * Verify SQL statement for: <br>
     * SELECT e.id, e.string, e.secondString FROM Entity e
     */
    protected abstract void verifySelectCheckColumnNames(JSql sql);

    @Test
    public final void testSelectManyTables() {
        Entity entity1 = sql.alias(Entity.class, "e1");
        Entity entity2 = sql.alias(Entity.class, "e2");
        sql.select().from(entity1, entity2);
        verifySelectManyTables(sql);
    }

    /**
     * Verify SQL statement for: <br>
     * SELECT e1.*, e2.* FROM Entity e1, Entity e2
     */
    protected abstract void verifySelectManyTables(JSql sql);

    @Test
    public final void testSelectColumnsFromManyTables() {
        Entity entity1 = sql.alias(Entity.class, "e1");
        Entity entity2 = sql.alias(Entity.class, "e2");
        sql.select(entity1.getId(), entity2.getString()).from(entity1, entity2);
        verifySelectColumnsFromManyTables(sql);
    }

    /**
     * Verify SQL statement for: <br>
     * SELECT e1.id, e2.string FROM Entity e1, Entity e2
     */
    protected abstract void verifySelectColumnsFromManyTables(JSql sql);

    @Test
    public final void testSelectColumnsFromManyTables_ColumnsFromSingleTable() {
        Entity entity1 = sql.alias(Entity.class, "e1");
        Entity entity2 = sql.alias(Entity.class, "e2");
        sql.select(entity1).from(entity1, entity2);
        verifySelectColumnsFromManyTables_ColumnsFromSingleTable(sql);
    }

    /**
     * Verify SQL statement for: <br>
     * SELECT e1.* FROM Entity e1, Entity e2
     */
    protected abstract void verifySelectColumnsFromManyTables_ColumnsFromSingleTable(JSql sql);

    @Test
    public final void testSelectCount() {
        Entity entity = sql.alias(Entity.class);
        sql.select().count().from(entity);
        verifySelectCount(sql);
    }

    /**
     * Verify SQL statement for: <br>
     * SELECT COUNT(*) FROM Entity
     */
    protected abstract void verifySelectCount(JSql sql);

    @Test
    public final void testSelectCountWithColumns() {
        Entity entity = sql.alias(Entity.class);
        sql.select(entity.getId()).count().from(entity).groupBy().column(entity.getId());
        verifySelectCountWithColumns(sql);
    }

    /**
     * Verify SQL statement for: <br>
     * SELECT id, COUNT(*) FROM Entity
     */
    protected abstract void verifySelectCountWithColumns(JSql sql);

    @Test
    public final void testSelectOrderBy() {
        Entity entity = sql.alias(Entity.class, "e");
        sql.select().from(entity).orderBy().asc(entity.getString());
        verifySelectOrder(sql);
    }

    /**
     * Verify SQL statement for: <br>
     * SELECT e.* FROM Entity e order by e.string
     */
    protected abstract void verifySelectOrder(JSql sql);

    @Test
    public final void testSelectOrderByManyParameters() {
        Entity entity = sql.alias(Entity.class, "e");
        sql.select().from(entity).orderBy().asc(entity.getString()).asc(entity.getId());
        verifySelectOrderByManyColumns(sql);
    }

    /**
     * Verify SQL statement for: <br>
     * SELECT e.* FROM Entity e order by e.string, e.id
     */
    protected abstract void verifySelectOrderByManyColumns(JSql sql);

    @Test
    public final void testSelectOrderByManyParametersDesc() {
        Entity entity = sql.alias(Entity.class, "e");
        sql.select().from(entity).orderBy().desc(entity.getString()).asc(entity.getId());
        verifySelectOrderByManyColumnsDesc(sql);
    }

    /**
     * Verify SQL statement for: <br>
     * SELECT e.* FROM Entity e order by e.string desc, e.id
     */
    protected abstract void verifySelectOrderByManyColumnsDesc(JSql sql);

    @Test
    public final void testSelectOrderByNull() {
        Entity entity = sql.alias(Entity.class, "e");
        sql.select().from(entity).orderBy();
        verifySelectOrderByNull(sql);
    }

    /**
     * Verify SQL statement for: <br>
     * SELECT e.* FROM Entity e order by <br>
     * Note: order by clause should not appear in statement.
     */
    protected abstract void verifySelectOrderByNull(JSql sql);

    @Test
    public final void testSelectDistinct() {
        Entity entity = sql.alias(Entity.class, "e");
        sql.select(entity.getString()).distinct().from(entity);
        verifySelectDistinct(sql);
    }

    /**
     * Verify SQL statement for: <br>
     * SELECT DISTINCT e.string FROM Entity e
     */
    protected abstract void verifySelectDistinct(JSql sql);

    @Test
    public final void testSelectGroupBy() {
        Entity entity = sql.alias(Entity.class, "e");
        sql.select(entity.getId(), entity.getString()).from(entity).groupBy().column(entity.getString())
                .column(entity.getId());
        verifySelectGroupBy(sql);
    }

    /**
     * Verify SQL statement for: <br>
     * SELECT e.* FROM Entity e GROUP BY e.string, e.id
     */
    protected abstract void verifySelectGroupBy(JSql sql);

    @Test
    public final void testSelectGroupByWithColumns() {
        Entity entity = sql.alias(Entity.class, "e");
        sql.select(entity.getString(), entity.getId()).from(entity).groupBy().column(entity.getString())
                .column(entity.getId());
        verifySelectGroupByWithColumns(sql);
    }

    /**
     * Verify SQL statement for: <br>
     * SELECT e.string, e.id FROM Entity e GROUP BY e.string, e.id
     */
    protected abstract void verifySelectGroupByWithColumns(JSql sql);

    @Test
    public final void testSelectWhereGroupByOrderBy() {
        Entity entity = sql.alias(Entity.class, "e");
        sql.select(entity.getString()).from(entity).where(sql.cond().eq(entity.getId(), 5l)).groupBy()
                .column(entity.getString()).orderBy().asc(entity.getString());
        verifySelectWhereGroupByOrderBy(sql);
    }

    @Test
    public final void testSelectWhereGroupByOrderBy_FlatAPI() {
        Entity entity = sql.alias(Entity.class, "e");
        sql.select(entity.getString()).from(entity).where().eq(entity.getId(), 5l).groupBy().column(entity.getString())
                .orderBy().asc(entity.getString());
        verifySelectWhereGroupByOrderBy(sql);
    }

    @Test
    public final void testSelectWhereOrderByGroupBy() {
        Entity entity = sql.alias(Entity.class, "e");
        sql.select(entity.getString()).from(entity).where(sql.cond().eq(entity.getId(), 5l)).orderBy()
                .asc(entity.getString()).groupBy().column(entity.getString());
        verifySelectWhereGroupByOrderBy(sql);
    }

    @Test
    public final void testSelectWhereOrderByGroupBy_FlatAPI() {
        Entity entity = sql.alias(Entity.class, "e");
        sql.select(entity.getString()).from(entity).where().eq(entity.getId(), 5l).orderBy().asc(entity.getString())
                .groupBy().column(entity.getString());
        verifySelectWhereGroupByOrderBy(sql);
    }

    /**
     * Verify SQL statement for: <br>
     * SELECT e.string FROM Entity e GROUP BY e.string ORDER BY string
     */
    protected abstract void verifySelectWhereGroupByOrderBy(JSql sql);

}
