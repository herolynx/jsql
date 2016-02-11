package com.mwronski.jsql.grammar.jpql;

import com.mwronski.jsql.grammar.common.GrammarUtil;
import com.mwronski.jsql.grammar.common.Nouns;
import com.mwronski.jsql.grammar.common.SQLSelectBuilder;
import com.mwronski.jsql.model.Table;
import com.mwronski.jsql.model.Variable;
import com.mwronski.jsql.model.dql.JoinStatement;
import com.mwronski.jsql.model.expressions.ExpressionChain;

import java.util.ArrayList;
import java.util.List;

/**
 * Select statement for JP-QL grammar
 * 
 * @date 05-07-2013
 * @author Michal Wronski
 * 
 */
final class JPQLSelectBuilder extends SQLSelectBuilder {

    private final StringBuilder sqlJoinConditions = new StringBuilder();

    @Override
    protected String toLowerString(String string) {
        return "lower(" + string + ")";
    }

    @Override
    protected String getRegExpWildcardMark() {
        return " LIKE ";
    }

    @Override
    protected String getVariableName(Variable var) {
        return GrammarUtil.getVariableName(var, true, true);
    }

    @Override
    protected String getTableDefinitionName(Table table) {
        if (table.getAlias() != null) {
            return super.getTableDefinitionName(table);
        }
        return GrammarUtil.getTableName(table) + Nouns.SPACE + GrammarUtil.getTableName(table);
    }

    @Override
    protected String getTableName(Table table) {
        return table.getAlias() != null ? super.getTableName(table) : GrammarUtil.getTableName(table);
    }

    protected void appendSelectAllFromTable(final StringBuilder sql, final Table table) {
        appendElementBreak(sql);
        sql.append(getTableName(table));
    }

    @Override
    public String asSQL() {
        if (sqlJoinConditions.length() > 0) {
            // attach conditions from JOINs into WHERE
            StringBuilder joinWhereCondition = new StringBuilder();
            joinWhereCondition.append(Nouns.LEFT_BRACKET).append(sqlJoinConditions).append(Nouns.RIGHT_BRACKET);
            if (sqlWhere.length() > 0) {
                joinWhereCondition.append(Nouns.SPACE).append(Nouns.AND).append(Nouns.SPACE);
            }
            sqlWhere.insert(0, joinWhereCondition);
        }
        return super.asSQL();
    }

    @Override
    public void handleJoin(final Table joinedTable, JoinStatement.Direction direction, JoinStatement.Type type,
            ExpressionChain onCondition) {
        // emulate join statement
        // TODO change to real join when model and parser is changed
        List<Table> joinedTables = new ArrayList<Table>();
        joinedTables.add(joinedTable);
        handleFrom(joinedTables);
        appendConditions(sqlJoinConditions, onCondition);
    }

}
