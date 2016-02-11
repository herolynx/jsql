package com.mwronski.jsql.parser.dql;

import com.mwronski.jsql.model.dql.SelectStatement;
import com.mwronski.jsql.recording.SqlRecorder;

/**
 * Parser for WHERE statement
 * 
 * @author Michal Wronski
 * @date 27.09.13 12:53
 */
public final class Where extends Condition {

    private final SelectStatement select;

    /**
     * Sort select by given criteria
     * 
     * @return order by parser
     */
    public Order orderBy() {
        return new Order(getRecorder(), select);
    }

    /**
     * Group by given columns
     * 
     * @return group by parser
     */
    public GroupBy groupBy() {
        return new GroupBy(getRecorder(), select);
    }

    @Override
    public Where eq(Object param, Object value) {
        return (Where) super.eq(param, value);
    }

    @Override
    public Where eq(Object param, Object value, boolean omittable) {
        return (Where) super.eq(param, value, omittable);
    }

    @Override
    public Where and() {
        return (Where) super.and();
    }

    @Override
    public Where and(Condition subCondition) {
        return (Where) super.and(subCondition);
    }

    @Override
    public Where or() {
        return (Where) super.or();
    }

    @Override
    public Where or(Condition subCondition) {
        return (Where) super.or(subCondition);
    }

    @Override
    public Where eqi(Object param, String value) {
        return (Where) super.eqi(param, value);
    }

    @Override
    public Where eqi(Object param, String value, boolean omittable) {
        return (Where) super.eqi(param, value, omittable);
    }

    @Override
    public Where neq(Object param, Object value) {
        return (Where) super.neq(param, value);
    }

    @Override
    public Where neq(Object param, Object value, boolean omittable) {
        return (Where) super.neq(param, value, omittable);
    }

    @Override
    public Where neqi(Object param, String value) {
        return (Where) super.neqi(param, value);
    }

    @Override
    public Where neqi(Object param, String value, boolean omittable) {
        return (Where) super.neqi(param, value, omittable);
    }

    @Override
    public Where eg(Object param, Object value) {
        return (Where) super.eg(param, value);
    }

    @Override
    public Where eg(Object param, Object value, boolean omittable) {
        return (Where) super.eg(param, value, omittable);
    }

    @Override
    public Where el(Object param, Object value) {
        return (Where) super.el(param, value);
    }

    @Override
    public Where el(Object param, Object value, boolean omittable) {
        return (Where) super.el(param, value, omittable);
    }

    @Override
    public Where gt(Object param, Object value) {
        return (Where) super.gt(param, value);
    }

    @Override
    public Where gt(Object param, Object value, boolean omittable) {
        return (Where) super.gt(param, value, omittable);
    }

    @Override
    public Where lt(Object param, Object value) {
        return (Where) super.lt(param, value);
    }

    @Override
    public Where lt(Object param, Object value, boolean omittable) {
        return (Where) super.lt(param, value, omittable);
    }

    @Override
    public Where regex(Object param, Object value) {
        return (Where) super.regex(param, value);
    }

    @Override
    public Where regex(Object param, Object value, boolean omittable) {
        return (Where) super.regex(param, value, omittable);
    }

    @Override
    public <T> Where in(Object param, T... values) {
        return (Where) super.in(param, values);
    }

    @Override
    public <T> Where in(Object param, boolean omittable, T... values) {
        return (Where) super.in(param, omittable, values);
    }

    @Override
    public <T> Where notIn(Object param, T... values) {
        return (Where) super.notIn(param, values);
    }

    @Override
    public Where notIn(Object param, boolean omittable, Object... values) {
        return (Where) super.notIn(param, omittable, values);
    }

    /**
     * Create instance
     * 
     * @param recorder
     *            that allows recording of select tokens
     * @param select
     *            for which condition will be created
     */
    Where(SqlRecorder recorder, SelectStatement select) {
        super(recorder);
        this.select = select;
        select.setWhere(getChain());
    }

}
