package com.mwronski.jsql.grammar.common;

/**
 * Nouns used by SQL
 * 
 * @author Michal Wronski
 * @date 27.09.13 14:31
 */
public enum Nouns {
    SELECT("SELECT"), FROM("FROM"), WHERE("WHERE"), AND("AND"), OR("OR"), JOIN("JOIN"), ON("ON"), DISTINCT("DISTINCT"), SPACE(
            " "), COMMA(","), ALL("*"), DOT("."), COUNT_ALL("COUNT(*)"), ORDER_BY("ORDER BY"), DESC("DESC"), GROUP_BY(
            "GROUP BY"), LEFT_BRACKET("("), RIGHT_BRACKET(")"), PARAM("?"), IS_NULL("IS NULL"), IS_NOT_NULL(
            "IS NOT NULL"), EQUALS("="), NOT_EQUALS("!="), LESS("<"), LESS_EQAULS("<="), GREATER(">"), GREATER_EQUALS(
            ">=");

    private final String sql;

    private Nouns(String sql) {
        this.sql = sql;
    }

    @Override
    public String toString() {
        return sql;
    }
}
