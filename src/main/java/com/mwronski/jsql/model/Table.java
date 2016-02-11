package com.mwronski.jsql.model;

/**
 * SQL table reference
 * 
 * @date 25-02-2013
 * @author Michal Wronski
 * 
 */
public final class Table {

    private final Class<?> tableClass;
    private final String alias;

    public Table(final Class<?> tableClass, final String alias) {
        this.tableClass = tableClass;
        this.alias = alias;
    }

    public Class<?> getTableClass() {
        return tableClass;
    }

    /**
     * Get table alias
     * 
     * @return
     */
    public String getAlias() {
        return alias;
    }

}
