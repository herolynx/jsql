package com.mwronski.jsql.parser.dql;

import com.mwronski.jsql.model.Table;
import com.mwronski.jsql.model.Variable;
import com.mwronski.jsql.model.dql.JoinStatement;
import com.mwronski.jsql.model.dql.SelectStatement;
import com.mwronski.jsql.recording.SqlRecorder;

import java.util.List;

/**
 * SQL SELECT command parser. Statement in built in the chain based on current
 * instance.
 * 
 * @date 25-02-2013
 * @author Michal Wronski
 * 
 */
public final class Select {

    private final SqlRecorder recorder;
    private final SelectStatement statement = new SelectStatement();

    /**
     * Create new select statement
     * 
     * @param recorder
     *            that allows recording of select tokens
     * @param tables
     *            from which all columns should be taken
     */
    public Select(final SqlRecorder recorder, Object... tables) {
        this.recorder = recorder;
        for (Table table : recorder.tables(tables)) {
            statement.getSelectedTables().add(table);
        }
        List<Variable> columns = recorder.variables();
        statement.getSelectedColumns().addAll(columns);
    }

    /**
     * FROM clause
     * 
     * @param o
     *            object from which data will be taken
     * @param others
     *            additional objects from which data will be taken
     * @return the same instance
     */
    public Select from(final Object o, final Object... others) {
        List<Table> fromTables = recorder.tables(o, others);
        if (fromTables.isEmpty()) {
            throw new RuntimeException("Didn't found SQL tables in FROM statement");
        }
        statement.getFrom().addAll(fromTables);
        return this;
    }

    /**
     * JOIN clause
     * 
     * @return join parser
     */
    public Join join() {
        return new Join(recorder, this);
    }

    /**
     * JOIN clause
     * 
     * @param table
     *            table to be joined
     * @return join parser
     */
    public Join join(Object table) {
        Join join = new Join(recorder, this);
        join.table(table);
        return join;
    }

    /**
     * JOIN clause
     * 
     * @param table
     *            table to be joined
     * @param direction
     *            direction of joined table
     * @param type
     *            type of made join
     * @return join parser
     */
    public Join join(Object table, JoinStatement.Direction direction, JoinStatement.Type type) {
        Join join = new Join(recorder, this, direction, type);
        join.table(table);
        return join;
    }

    /**
     * LEFT JOIN clause
     * 
     * @param o
     *            which data will be joined to SELECT
     * @return join parser
     */
    public Join leftJoin(final Object o) {
        return join(o, JoinStatement.Direction.LEFT, JoinStatement.Type.NONE);
    }

    /**
     * LEFT OUTER JOIN clause
     * 
     * @param o
     *            which data will be joined to SELECT
     * @return join parser
     */
    public Join leftOuterJoin(final Object o) {
        return join(o, JoinStatement.Direction.LEFT, JoinStatement.Type.OUTER);
    }

    /**
     * LEFT INNER JOIN clause
     * 
     * @param o
     *            which data will be joined to SELECT
     * @return join parser
     */
    public Join leftInnerJoin(final Object o) {
        return join(o, JoinStatement.Direction.LEFT, JoinStatement.Type.INNER);
    }

    /**
     * RIGHT JOIN clause
     * 
     * @param o
     *            which data will be joined to SELECT
     * @return join parser
     */
    public Join rightJoin(final Object o) {
        return join(o, JoinStatement.Direction.RIGHT, JoinStatement.Type.NONE);
    }

    /**
     * RIGHT OUTER JOIN clause
     * 
     * @param o
     *            which data will be joined to SELECT
     * @return join parser
     */
    public Join rightOuterJoin(final Object o) {
        return join(o, JoinStatement.Direction.RIGHT, JoinStatement.Type.OUTER);
    }

    /**
     * RIGHT OUTER JOIN clause
     * 
     * @param o
     *            which data will be joined to SELECT
     * @return join parser
     */
    public Join rightInnerJoin(final Object o) {
        return join(o, JoinStatement.Direction.RIGHT, JoinStatement.Type.INNER);
    }

    /**
     * WHERE clause
     * 
     * @return where parser
     */
    public Where where() {
        return new Where(recorder, statement);
    }

    /**
     * Add SELECT WHERE condition
     * 
     * @param whereCondition
     * @return the same instance
     */
    public Select where(Condition whereCondition) {
        statement.setWhere(whereCondition.getChain());
        return this;
    }

    /**
     * Sort select by given criteria
     * 
     * @return order by parser
     */
    public Order orderBy() {
        return new Order(recorder, statement);
    }

    /**
     * Group by given columns
     * 
     * @return group by parser
     */
    public GroupBy groupBy() {
        return new GroupBy(recorder, statement);
    }

    /**
     * SELECT COUNT clause
     * 
     * @return the same instance
     */
    public Select count() {
        statement.setCount(true);
        return this;
    }

    /**
     * Mark that results should be unique
     * 
     * @return the same instance
     */
    public Select distinct() {
        statement.setDistinct(true);
        return this;
    }

    public SelectStatement getStatement() {
        return statement;
    }

    SqlRecorder getRecorder() {
        return recorder;
    }

}
