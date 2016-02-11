package com.mwronski.jsql.grammar.common;

import com.mwronski.jsql.model.Table;
import com.mwronski.jsql.model.Variable;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.lang.reflect.Method;

/**
 * Util related with grammar and SQL tokens.
 * 
 * @date 28-06-2013
 * @author Michal Wronski
 * 
 */
public final class GrammarUtil {

    private GrammarUtil() {
        // no instances
    }

    /**
     * Get name of given variable
     * 
     * @param variable
     * @param skipAnnotations
     *            flag indicates whether meta-data of variable should be skipped
     *            event if it's available
     * @return name from Column annotation if defined. If Column annotation is
     *         not defined name is taken from getter method name.
     */
    private static String getFieldName(final Variable variable, final boolean skipAnnotations) {
        Method method = variable.getMethod();
        Column columnAnnotation = method.getAnnotation(Column.class);
        if (!skipAnnotations && columnAnnotation != null && !columnAnnotation.name().isEmpty()) {
            return columnAnnotation.name().toLowerCase();
        }
        String fieldName = null;
        if (method.getName().startsWith("get")) {
            fieldName = method.getName().replace("get", "");
        } else if (method.getName().startsWith("is")) {
            fieldName = method.getName().replace("is", "");
        } else {
            fieldName = method.getName();
        }
        return normalizeSpelling(fieldName);
    }

    /**
     * Normalize spelling of string to meet JAVA conventions
     * 
     * @param string
     * @return
     */
    private static String normalizeSpelling( String string) {
        char firstLetterLower = Character.toLowerCase(string.charAt(0));
        String restString = string.length() > 1 ? string.substring(1) : "";
        return firstLetterLower + restString;
    }

    /**
     * Get variable name
     * 
     * @param variable
     * @param showTableName
     *            flag indicates whether table name should appear before
     *            variable name
     * @param skipAnnotations
     *            flag indicates whether meta-data of variable should be skipped
     *            event if it's available
     * @return
     */
    public static String getVariableName(final Variable variable, final boolean skipAnnotations,
            final boolean showTableName) {
        StringBuilder varName = new StringBuilder();
        if (variable.getTable().getAlias() != null) {
            varName.append(variable.getTable().getAlias()).append(Nouns.DOT);
        } else if (showTableName) {
            varName.append(getTableName(variable.getTable())).append(Nouns.DOT);
        }
        varName.append(getFieldName(variable, skipAnnotations));

        return varName.toString();
    }

    /**
     * Get table name.
     * 
     * 
     * @return name Entity annotation or class name if entity annotation is not
     *         defined
     */
    public static String getTableName(final Table table) {
        Entity entityAnno = table.getTableClass().getAnnotation(Entity.class);
        if (entityAnno != null && !entityAnno.name().isEmpty()) {
            return entityAnno.name();
        }
        return table.getTableClass().getSimpleName();
    }

    /**
     * Get name of table definition
     * 
     * @param table
     * @return table name with its alias if set
     */
    public static String getTableDefinitionName(final Table table) {
        StringBuilder tableName = new StringBuilder(getTableName(table));
        if (table.getAlias() != null) {
            tableName.append(Nouns.SPACE).append(table.getAlias());
        }
        return tableName.toString();
    }

}
