package com.mwronski.jsql.test;

import com.mwronski.jsql.JSql;

import javax.persistence.EntityManager;

import static org.junit.Assert.assertNotNull;

/**
 * Generic functions related with tests
 * 
 * @author Michal Wronski
 */
public final class TestUtil {

    /**
     * Check whether query built in JSQL is executable
     * 
     * @param sql
     *            with built statement
     * @param em
     *            entity manager for building query
     */
    public static void assertQueryExecutable(JSql sql, EntityManager em) {
        assertNotNull(sql.getQuery(em).getResultList());
    }

    private TestUtil() {
        // no instances
    }
}
