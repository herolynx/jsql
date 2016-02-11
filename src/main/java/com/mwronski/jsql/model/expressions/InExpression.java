package com.mwronski.jsql.model.expressions;

import com.mwronski.jsql.model.Variable;
import com.mwronski.jsql.recording.SqlRecorder;

import java.util.Arrays;
import java.util.List;

/**
 * Class representing collection of values, used in SQL expressions "IN" and
 * "NOT IN".
 * 
 * @author Michal Wronski
 * 
 */
public final class InExpression implements Expression {

    public enum CollectionType {
        IN, NOT_IN
    }

    private final Variable var;
    private final List<Object> values;
    private final CollectionType type;
    private final boolean omittable;

    public InExpression(final SqlRecorder recorder, final Object param, final CollectionType type,
            final boolean omittable, final Object[] values) {
        var = recorder.nextVariable();
        this.values = values != null && values.length > 0 ? Arrays.asList(values) : null;
        this.omittable = omittable;
        this.type = type;
    }

    public Variable getVar() {
        return var;
    }

    public CollectionType getType() {
        return type;
    }

    public List<Object> getValues() {
        return values;
    }

    @Override
    public boolean isNullOmittable() {
        return omittable;
    }

    public boolean shouldBeOmitted() {
        return isNullOmittable() && isNull();
    }

    @Override
    public boolean isNull() {
        return values == null || values.size() == 0;
    }

}
