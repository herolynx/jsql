package com.mwronski.jsql.common;

import com.mwronski.jsql.AbstractSelectWhereTestSuite;
import com.mwronski.jsql.JSql;
import com.mwronski.jsql.test.Entity;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

/**
 * Test cases check whether parameters in query are set properly. <br>
 * Test cases check independent grammar stuff.
 * 
 * @date 27-06-2013
 * @author Michal Wronski
 * 
 */
public abstract class QueryTest extends AbstractSelectWhereTestSuite {

    protected EntityManager mockEntityManager;
    protected Query mockQuery;

    @Override
    public void setUp() {
        super.setUp();
        mockEntityManager = mock(EntityManager.class);
        mockQuery = mock(Query.class);
        when(mockEntityManager.createNativeQuery(any(String.class), any(Class.class))).thenReturn(mockQuery);
        when(mockEntityManager.createNativeQuery(any(String.class))).thenReturn(mockQuery);
        when(mockEntityManager.createQuery(any(String.class))).thenReturn(mockQuery);
    }

    @Override
    protected void verifySelectWhere(final JSql sql) {
        sql.getQuery(mockEntityManager, Entity.class);
        verify(mockQuery).setParameter(1, 5l);
        verify(mockQuery).setParameter(2, "aaa");
        verifyNoMoreInteractions(mockQuery);
    }

    @Override
    protected void verifySelectWhereWithNullParam(final JSql sql) {
        sql.getQuery(mockEntityManager, Entity.class);
        verify(mockQuery).setParameter(1, 5l);
        verifyNoMoreInteractions(mockQuery);
    }

    @Override
    protected void verifySelectWhereWithNullParamToSkip(final JSql sql) {
        sql.getQuery(mockEntityManager, Entity.class);
        verifyNoMoreInteractions(mockQuery);
    }

    @Override
    protected void verifySelectWhereWithSkipableParams(final JSql sql) {
        sql.getQuery(mockEntityManager, Entity.class);
        verify(mockQuery).setParameter(1, 5l);
        verifyNoMoreInteractions(mockQuery);
    }

    @Override
    protected void verifySelectWhereFirstParamNull(final JSql sql) {
        sql.getQuery(mockEntityManager, Entity.class);
        verify(mockQuery).setParameter(1, "aaa");
        verifyNoMoreInteractions(mockQuery);
    }

    @Override
    protected void verifySelectWhereWithAlias(final JSql sql) {
        sql.getQuery(mockEntityManager, Entity.class);
        verify(mockQuery).setParameter(1, 5l);
        verify(mockQuery).setParameter(2, "aaa");
        verifyNoMoreInteractions(mockQuery);
    }

    @Override
    protected void verifySelectWhereNested(final JSql sql) {
        sql.getQuery(mockEntityManager, Entity.class);
        verify(mockQuery).setParameter(1, 5l);
        verify(mockQuery).setParameter(2, "aaa");
        verify(mockQuery).setParameter(3, "bbb");
        verifyNoMoreInteractions(mockQuery);
    }

    @Override
    protected void verifySelectWhereNestedWithSkipableParms(final JSql sql) {
        sql.getQuery(mockEntityManager, Entity.class);
        verify(mockQuery).setParameter(1, 5l);
        verifyNoMoreInteractions(mockQuery);
    }

    @Override
    protected void verifySelectWhereWithNestedAliases(final JSql sql) {
        sql.getQuery(mockEntityManager, Entity.class);
        verify(mockQuery).setParameter(1, 5l);
        verify(mockQuery).setParameter(2, "aaa");
        verify(mockQuery).setParameter(3, "bbb");
        verifyNoMoreInteractions(mockQuery);
    }

    @Override
    protected void verifySelectColumnsFromManyTablesWithWhere(final JSql sql) {
        sql.getQuery(mockEntityManager, Entity.class);
        verifyNoMoreInteractions(mockQuery);
    }

    @Override
    protected void verifySelectWhereWithInCondition(final JSql sql) {
        sql.getQuery(mockEntityManager, Entity.class);
        List<Long> inParams = new ArrayList<Long>();
        inParams.add(1l);
        inParams.add(3l);
        inParams.add(5l);
        verify(mockQuery).setParameter(1, inParams);
        verifyNoMoreInteractions(mockQuery);
    }

    @Override
    protected void verifySelectWhereWithInSkipableCondition(final JSql sql) {
        sql.getQuery(mockEntityManager, Entity.class);
        verifyNoMoreInteractions(mockQuery);
    }

    @Override
    protected void verifySelectWhereWithInNotSkipableCondition(final JSql sql) {
        sql.getQuery(mockEntityManager, Entity.class);
        verifyNoMoreInteractions(mockQuery);
    }

    @Override
    protected void verifySelectRegexp(final JSql sql) {
        sql.getQuery(mockEntityManager, Entity.class);
        verify(mockQuery).setParameter(1, "aaa");
        verifyNoMoreInteractions(mockQuery);
    }

    @Override
    protected void verifySelectWhereInsensitiveValue(final JSql sql) {
        sql.getQuery(mockEntityManager, Entity.class);
        verify(mockQuery).setParameter(1, 5l);
        verify(mockQuery).setParameter(2, "aaa");
        verifyNoMoreInteractions(mockQuery);
    }

    @Override
    protected void verifySelectWhereInsensitiveVar(final JSql sql) {
        sql.getQuery(mockEntityManager, Entity.class);
        verify(mockQuery).setParameter(1, 5l);
        verifyNoMoreInteractions(mockQuery);
    }

    @Override
    protected void verifySelectWhereRelations(JSql sql) {
        sql.getQuery(mockEntityManager, Entity.class);
        verify(mockQuery).setParameter(1, 5l);
        verify(mockQuery).setParameter(2, 6l);
        verify(mockQuery).setParameter(3, 7l);
        verify(mockQuery).setParameter(4, 8l);
        verify(mockQuery).setParameter(5, 9l);
        verifyNoMoreInteractions(mockQuery);
    }

}
