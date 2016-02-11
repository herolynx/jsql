package com.mwronski.jsql;

import com.mwronski.jsql.builder.SqlCommandBuilder;
import com.mwronski.jsql.builder.SqlSelectBuilder;
import com.mwronski.jsql.builder.SqlSelectTreeWalker;
import com.mwronski.jsql.grammar.SqlGrammar;
import com.mwronski.jsql.parser.dql.Condition;
import com.mwronski.jsql.parser.dql.Select;
import com.mwronski.jsql.recording.SqlRecorder;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.Map.Entry;

/**
 * SQL command manager that build SQL statements in chosen grammar.
 * 
 * @see SqlCommandBuilder
 * @date 25-02-2013
 * @author Michal Wronski
 * 
 */
public final class JSql {

    private final SqlGrammar grammar;
    private final SqlRecorder recorder = new SqlRecorder();
    private Select select;

    /**
     * Create instance
     * 
     * @param grammar
     *            chosen grammar for SQL statements
     */
    public JSql(final SqlGrammar grammar) {
        this.grammar = grammar;
    }

    /**
     * Build select query
     * 
     * @param objects
     *            tables or columns
     * @return
     */
    public Select select(final Object... objects) {
        Select select = new Select(recorder, objects);
        this.select = select;
        return select;
    }

    /**
     * Create new condition statement
     * 
     * @return
     */
    public Condition cond() {
        return new Condition(recorder);
    }

    /**
     * Record SQL command from given class
     * 
     * @param clazz
     *            to be recorder in SQL
     * @param alias
     *            class alias
     * @return
     */
    public <T> T alias(final Class<T> clazz, final String alias) {
        return recorder.record(clazz, alias);
    }

    /**
     * Record SQL command from given class
     * 
     * @param clazz
     *            to be recorder in SQL
     * @return
     */
    public <T> T alias(final Class<T> clazz) {
        return alias(clazz, null);
    }

    /**
     * Get string representation of SQL statement in chosen grammar
     * 
     * @return query string representation
     */
    @Override
    public String toString() {
        return asSQL(grammar.selectBuilder());
    }

    /**
     * Get string representation of SQL statement
     * 
     * @param sqlCommandBuilder
     *            handler for building statement according to chosen grammar
     * @return
     */
    private <T, H extends SqlSelectBuilder> String asSQL(final H sqlCommandBuilder) {
        new SqlSelectTreeWalker().walk(select.getStatement(), sqlCommandBuilder);
        return sqlCommandBuilder.asSQL().trim();
    }

    /**
     * Get native query for current SQL statement
     * 
     * @param entityManager
     * @return
     */
    public <T> Query getQuery(final EntityManager entityManager) {
        return getQuery(entityManager, null);
    }

    /**
     * Get query for current SQL statement
     * 
     * @param entityManager
     * @param clazz
     *            result class
     * @return
     */
    public <T> Query getQuery(final EntityManager entityManager, final Class<T> clazz) {
        return getQuery(entityManager, clazz, grammar.selectBuilder());
    }

    /**
     * Get query for current SQL statement
     * 
     * @param entityManager
     * @param clazz
     * @param sqlCommandBuilder
     *            handler for building statement according to chosen grammar
     * @return
     */
    private <T, H extends SqlSelectBuilder> Query getQuery(final EntityManager entityManager, final Class<T> clazz,
            final H sqlCommandBuilder) {
        // build command
        new SqlSelectTreeWalker().walk(select.getStatement(), sqlCommandBuilder);
        // build query
        Query query = null;
        if (grammar.isNative()) {
            if (clazz != null) {
                query = entityManager.createNativeQuery(sqlCommandBuilder.asSQL().trim(), clazz);
            } else {
                query = entityManager.createNativeQuery(sqlCommandBuilder.asSQL().trim());
            }
        } else {
            query = entityManager.createQuery(sqlCommandBuilder.asSQL().trim());
        }
        // set query params
        for (Entry<Integer, Object> queryParam : sqlCommandBuilder.getSQLParams().entrySet()) {
            if (queryParam.getValue() != null) {
                query.setParameter(queryParam.getKey(), queryParam.getValue());
            }
        }
        return query;
    }

    /**
     * Check whether build statements in chosen grammar are in native SQL form
     * or in SQL supported by entities managers.
     * 
     * @return
     */
    public boolean isNative() {
        return grammar.isNative();
    }

}
