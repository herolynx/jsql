package com.mwronski.jsql.model.dql;

import com.mwronski.jsql.model.Table;
import com.mwronski.jsql.model.Variable;
import com.mwronski.jsql.model.expressions.ExpressionChain;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Class holds information about SELECT
 * 
 * @author Michal Wronski
 * @date 27.09.13 12:13
 */
public final class SelectStatement {

    public static final boolean ASC = false;
    public static final boolean DESC = true;
    private final List<Variable> selectedColumns = new ArrayList<Variable>();
    private final List<Table> selectedTables = new ArrayList<Table>();
    private final List<Table> from = new ArrayList<Table>();
    private final Map<Variable, Boolean> variablesOrder = new LinkedHashMap<Variable, Boolean>();
    private final List<Variable> groupedBy = new ArrayList<Variable>();
    private final List<JoinStatement> joins = new ArrayList<JoinStatement>();
    private boolean distinct = false;
    private ExpressionChain where;
    private boolean count = false;

    public boolean isCount() {
        return count;
    }

    public void setCount(boolean count) {
        this.count = count;
    }

    /**
     * Get condition of the statement
     * 
     * @return
     */
    public ExpressionChain getWhere() {
        return where;
    }

    public void setWhere(ExpressionChain where) {
        this.where = where;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * Get joined tables to the statement
     * 
     * @return
     */
    public List<JoinStatement> getJoins() {
        return joins;
    }

    /**
     * Get columns that data are grouped by
     * 
     * @return
     */
    public List<Variable> getGroupedBy() {
        return groupedBy;
    }

    /**
     * Get order of returned data
     * 
     * @return
     */
    public Map<Variable, Boolean> getOrder() {
        return variablesOrder;
    }

    /**
     * Get columns that will be returned by SELECT
     * 
     * @return
     */
    public List<Variable> getSelectedColumns() {
        return selectedColumns;
    }

    /**
     * Get table for which all columns will be returned by SELECT
     * 
     * @return
     */
    public List<Table> getSelectedTables() {
        return selectedTables;
    }

    /**
     * Get tables from which data will be taken
     * 
     * @return
     */
    public List<Table> getFrom() {
        return from;
    }

}
