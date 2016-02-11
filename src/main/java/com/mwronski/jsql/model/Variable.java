package com.mwronski.jsql.model;

import java.lang.reflect.Method;

/**
 * SQL variable reference
 * 
 * @date 25-02-2013
 * @author Michal Wronski
 * 
 */
public final class Variable {

    private final Table table;
    private final Method method;

    public Variable(final Table table, final Method method) {
        this.table = table;
        this.method = method;
    }

    public Table getTable() {
        return table;
    }

    public Method getMethod() {
        return method;
    }

    public Class<?> getType() {
        return method.getReturnType();
    }

}
