package com.mwronski.jsql.model.expressions;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Class holds information about single chain of conditions
 * 
 * @author Michal Wronski
 * @date 27.09.13 12:36
 */
public class ExpressionChain implements Expression {

    public enum Type {
        AND, OR
    }

    private final Map<Expression, Type> conditions = new LinkedHashMap<Expression, Type>();

    public void add(Type type, Expression expression) {
        conditions.put(expression, type);
    }

    @Override
    public boolean isNullOmittable() {
        return true;
    }

    public Map<Expression, Type> getConditions() {
        return conditions;
    }

    /**
     * Check whether chain have attached any expressions
     * 
     * @return
     */
    public boolean isEmpty() {
        return conditions.isEmpty();
    }

    @Override
    public boolean isNull() {
        if (isEmpty()) {
            return true;
        }
        for (Expression expression : conditions.keySet()) {
            if (!expression.isNull()) {
                return false;
            }
        }
        return true;
    }
}
