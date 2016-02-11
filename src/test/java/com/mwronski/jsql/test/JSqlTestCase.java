package com.mwronski.jsql.test;

import com.mwronski.jsql.JSql;
import com.mwronski.jsql.grammar.SqlGrammar;
import org.junit.After;
import org.junit.Before;

import javax.persistence.EntityManager;

/**
 * Abstract test case for functionality of {@link JSql} in chosen grammar
 * 
 * @date 27-06-2013
 * @author Michal Wronski
 * 
 */
public abstract class JSqlTestCase {

    protected JSql sql;
    protected EntityManager em;

    public abstract SqlGrammar getSqlGrammar();

    @Before
    public void setUp() {
        sql = new JSql(getSqlGrammar());
        em = TestDB.getEntityManager();
    }

    @After
    public void tearDown() {
        em.close();
        em = null;
    }

}
