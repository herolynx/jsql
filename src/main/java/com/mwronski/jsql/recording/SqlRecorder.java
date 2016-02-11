package com.mwronski.jsql.recording;

import com.mwronski.jsql.model.Table;
import com.mwronski.jsql.model.Variable;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.*;

/**
 * Class records invocations on SQL sources
 * 
 * @date 25-02-2013
 * @author Michal Wronski
 * 
 */
public final class SqlRecorder implements MethodInterceptor {

    private final List<Variable> invocations = new LinkedList<Variable>();
    private final Map<Object, Table> sourceTokens = new HashMap<Object, Table>();

    @Override
    public Object intercept(final Object o, final Method method, final Object[] args, final MethodProxy methodProxy)
            throws Throwable {
        if (method.getName().equals("hashCode") || method.getName().equals("equals")) {
            return methodProxy.invokeSuper(o, args);
        } else if (sourceTokens.containsKey(o)) {
            invocations.add(new Variable(sourceTokens.get(o), method));
        }
        return methodProxy.invokeSuper(o, args);
    }

    /**
     * Get next recorded variable
     * 
     * @return
     */
    public Variable nextVariable() {
        return !invocations.isEmpty() ? invocations.remove(0) : null;
    }

    /**
     * Get recorded variables
     * 
     * @return
     */
    public List<Variable> variables() {
        List<Variable> tokens = new ArrayList<Variable>(invocations);
        invocations.clear();
        return tokens;
    }

    /**
     * Get SQL tables based on recorded objects
     * 
     * @param o
     *            object recorded by this instance
     * @param others
     *            other objects recorded by this instance
     * @return SQL table sources
     */
    public List<Table> tables(final Object o, final Object... others) {
        List<Table> tokens = new ArrayList<Table>();
        loadToken(tokens, o);
        tokens.addAll(tables(others));
        return tokens;
    }

    /**
     * Get SQL tables based on recorded objects
     * 
     * @param objects
     *            object recorder by this instance
     * @return SQL table sources
     */
    public List<Table> tables(final Object[] objects) {
        List<Table> tokens = new ArrayList<Table>();
        for (Object object : objects) {
            loadToken(tokens, object);
        }
        return tokens;
    }

    /**
     * Find table for given object and put it into tokens
     * 
     * @param tokens
     * @param o
     *            recorded object
     */
    private void loadToken(final List<Table> tokens, final Object o) {
        Table token = sourceTokens.get(o);
        if (token != null) {
            tokens.add(token);
        }
    }

    /**
     * Record invocations on given class
     * 
     * @param clazz
     *            source class to be recorded
     * @param alias
     * @return recordable object that can be used while building SQL commands
     */
    @SuppressWarnings("unchecked")
    public <T> T record(final Class<T> clazz, final String alias) {
        Enhancer e = new Enhancer();
        e.setSuperclass(clazz);
        e.setCallback(this);
        T recorableObject = (T) e.create();
        sourceTokens.put(recorableObject, new Table(clazz, alias));
        return recorableObject;
    }

}
