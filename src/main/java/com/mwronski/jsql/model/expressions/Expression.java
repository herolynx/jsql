package com.mwronski.jsql.model.expressions;

/**
 * SQL expression
 * 
 * @date 25-02-2013
 * @author Michal Wronski
 * 
 */
public interface Expression {

    /**
     * Flag indicates whether expression should be omitted if it's value
     * evaluates to null.
     * 
     * @return
     */
    boolean isNullOmittable();

    /**
     * Check whether expression is evaluated as NULL
     * 
     * @return
     */
    boolean isNull();
}
