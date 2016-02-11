package com.mwronski.jsql.parser.dql;

import com.mwronski.jsql.model.dql.SelectStatement;
import com.mwronski.jsql.recording.SqlRecorder;

/**
 * Parser for ORDER BY statement
 * 
 * @author Michal Wronski
 * @date 27.09.13 12:21
 */
public final class Order {

    private final SqlRecorder recorder;
    private final SelectStatement statement;

    /**
     * Group by given columns
     * 
     * @return group by parser
     */
    public GroupBy groupBy() {
        return new GroupBy(recorder, statement);
    }

    /**
     * Sort by given column in ascending order
     * 
     * @param column
     * @return this instance
     */
    public Order asc(final Object column) {
        statement.getOrder().put(recorder.nextVariable(), SelectStatement.ASC);
        return this;
    }

    /**
     * Sort by given column in descending order
     * 
     * @param column
     * @return this instance
     */
    public Order desc(final Object column) {
        statement.getOrder().put(recorder.nextVariable(), SelectStatement.DESC);
        return this;
    }

    /**
     * Create instance
     * 
     * @param recorder
     *            that allows recording of select tokens
     * @param select
     *            where order will be attached
     */
    Order(SqlRecorder recorder, SelectStatement select) {
        this.recorder = recorder;
        this.statement = select;
    }

}
