package com.mwronski.jsql.parser.dql;

import com.mwronski.jsql.model.Table;
import com.mwronski.jsql.model.dql.JoinStatement;
import com.mwronski.jsql.recording.SqlRecorder;

import java.util.List;

/**
 * SQL JOIN command parser
 * 
 * @date 25-02-2013
 * @author Michal Wronski
 * 
 */
public final class Join {

    private final SqlRecorder recorder;
    private final JoinStatement statement = new JoinStatement();
    private final Select select;

    /**
     * Create instance and attach it to SELECT statement
     * 
     * @param recorder
     *            that allows recording of select tokens
     * @param select
     *            root SELECT statement
     */
    Join(SqlRecorder recorder, Select select) {
        this(recorder, select, JoinStatement.Direction.NONE, JoinStatement.Type.NONE);
    }

    /**
     * Create instance and attach it to SELECT statement
     * 
     * @param recorder
     *            that allows recording of select tokens
     * @param select
     *            root SELECT statement
     * @param direction
     *            direction of joined table
     * @param type
     *            type of made join
     */
    Join(SqlRecorder recorder, Select select, JoinStatement.Direction direction, JoinStatement.Type type) {
        this.recorder = recorder;
        this.select = select;
        statement.setDirection(direction);
        statement.setType(type);
        select.getStatement().getJoins().add(statement);
    }

    /**
     * Set table to be joined
     * 
     * @param o
     * @return this instance
     */
    public Join table(Object o) {
        List<Table> joinedTables = recorder.tables(o);
        if (joinedTables.size() != 1) {
            throw new RuntimeException("One joined table expected - got: " + joinedTables.size());
        }
        statement.setTable(joinedTables.get(0));
        return this;
    }

    /**
     * Make inner join
     * 
     * @return this instance
     */
    public Join inner() {
        statement.setType(JoinStatement.Type.INNER);
        return this;
    }

    /**
     * Make outer join
     * 
     * @return this instance
     */
    public Join outer() {
        statement.setType(JoinStatement.Type.OUTER);
        return this;
    }

    /**
     * Make left join
     * 
     * @return this instance
     */
    public Join left() {
        statement.setDirection(JoinStatement.Direction.LEFT);
        return this;
    }

    /**
     * Make right join
     * 
     * @return this instance
     */
    public Join right() {
        statement.setDirection(JoinStatement.Direction.RIGHT);
        return this;
    }

    /**
     * Add join condition
     * 
     * @param on
     * @return SELECT where this instance is JOINed
     */
    public Select on(final Condition on) {
        statement.setOn(on.getChain());
        return select;
    }

}
