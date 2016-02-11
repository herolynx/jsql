package com.mwronski.jsql.model.expressions;

import com.mwronski.jsql.model.Variable;
import com.mwronski.jsql.recording.SqlRecorder;

/**
 * Comparison of variable to chosen value/variable
 * 
 * @date 25-02-2013
 * @author Michal Wronski
 * 
 */
public final class Relation implements Expression {

    public enum RelationType {
        EQ, NEQ, EL, EG, GT, LT, REGEX
    }

    private final Variable var;
    private final RelationType relation;
    private final Object value;
    private final Variable varValue;
    private final boolean omittable;
    private final boolean caseInsensitive;

    public Relation(SqlRecorder recorder, Object var, final RelationType relation, final Object value,
            final boolean omittable, final boolean caseInsensitive) {
        this.var = recorder.nextVariable();
        this.relation = relation;
        varValue = recorder.nextVariable();
        this.value = value;
        this.omittable = omittable;
        this.caseInsensitive = caseInsensitive;
        validateArgumentsTypes();
    }

    public Variable getVar() {
        return var;
    }

    public RelationType getType() {
        return relation;
    }

    public Object getValue() {
        return value;
    }

    public Variable getVarValue() {
        return varValue;
    }

    public boolean hasVarValue() {
        return varValue != null;
    }

    @Override
    public boolean isNullOmittable() {
        return omittable;
    }

    @Override
    public boolean isNull() {
        return varValue ==null &&  value == null;
    }

    public boolean shouldBeOmitted() {
        return isNullOmittable() && getValue() == null && getVarValue() == null;
    }

    public boolean isCaseInsensitive() {
        return caseInsensitive;
    }

    /**
     * Check that proper argument types are used. Currently only checks that
     * String arguments are used for case insensitive relations.
     * 
     * @throws RuntimeException
     *             if argument types are incorrect
     */
    private void validateArgumentsTypes() {
        if (caseInsensitive) {
            if (this.var.getType() != String.class) {
                throw new RuntimeException("Parameter of case insensitive relation must be String, is: "
                        + this.var.getType());
            }
            if (this.value != null) {
                if (!(value instanceof String)) {
                    throw new RuntimeException("Value of case insensitive relation must be String, is: "
                            + this.value.getClass());
                }
            } else {
                if (this.varValue.getType() != String.class) {
                    throw new RuntimeException("Value of case insensitive relation must be String, is: "
                            + this.varValue.getType());
                }
            }
        }
    }
}
