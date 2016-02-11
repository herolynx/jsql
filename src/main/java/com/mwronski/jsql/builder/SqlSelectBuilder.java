package com.mwronski.jsql.builder;

import com.mwronski.jsql.model.Table;
import com.mwronski.jsql.model.Variable;
import com.mwronski.jsql.model.dql.JoinStatement;
import com.mwronski.jsql.model.expressions.ExpressionChain;

import java.util.List;
import java.util.Map;

/**
 * Builder for SELECT statement
 * 
 * @date 28-06-2013
 * @author Michal Wronski
 * 
 */
public interface SqlSelectBuilder extends SqlCommandBuilder {

    /**
     * Handle SELECT part of the statement
     * 
     * @param tables
     *            from which all columns should be taken
     * @param selectColumns
     *            columns to be selected
     * @param distinct
     *            flag indicates whether select is distinct
     * @param count
     *            flag indicates whether generic counting is made
     */
    void handleSelect(List<Table> tables, List<Variable> selectColumns, boolean distinct, boolean count);

    /**
     * Handle FROM part of the statement
     * 
     * @param tables
     *            tables from which data is taken
     */
    void handleFrom(List<Table> tables);

    /**
     * Handle JOIN part of the statement
     * 
     * @param joinedTable
     * @param direction
     *            direction of joined table
     * @param type
     *            type of made join
     * @param onCondition
     *            joining conditions
     */
    void handleJoin(Table joinedTable, JoinStatement.Direction direction, JoinStatement.Type type,
            ExpressionChain onCondition);

    /**
     * Handle WHERE part of the statement
     * 
     * @param where
     *            select conditions
     */
    void handleWhere(ExpressionChain where);

    /**
     * Handle ORDER BY part of the statement
     * 
     * @param variables
     *            variables with set order
     */
    void handleOrderBy(Map<Variable, Boolean> variables);

    /**
     * Handle GROUP BY part of the statement
     * 
     * @param variables
     *            variables that results are grouped by
     */
    void handleGroupBy(List<Variable> variables);

}
