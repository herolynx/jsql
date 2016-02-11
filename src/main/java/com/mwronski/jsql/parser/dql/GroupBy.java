package com.mwronski.jsql.parser.dql;

import com.mwronski.jsql.model.dql.SelectStatement;
import com.mwronski.jsql.recording.SqlRecorder;

/**
 * 
 * Parser for GROUP BY statement
 * 
 * @author Michal Wronski
 * @date 27.09.13 12:31
 */
public final class GroupBy {

    private final SqlRecorder recorder;
    private final SelectStatement statement;

    /**
     * Sort select by given criteria
     * 
     * @return order by parser
     */
    public Order orderBy() {
        return new Order(recorder, statement);
    }

    /**
     * Add grouped by column
     * 
     * @param column
     * @return the same instance
     */
    public GroupBy column(Object column) {
        statement.getGroupedBy().add(recorder.nextVariable());
        return this;
    }

    /**
     * Create instance
     * 
     * @param recorder
     *            that allows recording of select tokens
     * @param select
     *            select where grouping will be attached
     */
    GroupBy(SqlRecorder recorder, SelectStatement select) {
        this.recorder = recorder;
        this.statement = select;
    }

}
