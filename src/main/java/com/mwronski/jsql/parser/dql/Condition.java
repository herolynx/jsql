package com.mwronski.jsql.parser.dql;

import com.mwronski.jsql.model.expressions.ExpressionChain;
import com.mwronski.jsql.model.expressions.InExpression;
import com.mwronski.jsql.model.expressions.InExpression.CollectionType;
import com.mwronski.jsql.model.expressions.Relation;
import com.mwronski.jsql.model.expressions.Relation.RelationType;
import com.mwronski.jsql.recording.SqlRecorder;

/**
 * SQL parser for SQL conditions. Conditions are built in the chain based on
 * current instance of condition. <br>
 * Note: conditions marked as "omittable" will be skipped if they are evaluated
 * as null.
 * 
 * @date 25-02-2013
 * @author Michal Wronski
 * 
 */
public class Condition {

    private final SqlRecorder recorder;
    private final ExpressionChain chain = new ExpressionChain();
    private ExpressionChain.Type nextConditionType = ExpressionChain.Type.AND;

    /**
     * Create instance
     * 
     * @param recorder
     *            that allows recording of new conditions in statement
     * 
     */
    public Condition(final SqlRecorder recorder) {
        this.recorder = recorder;
    }

    /**
     * Prepare for appending new condition after AND
     * 
     * @return the same instance
     */
    public Condition and() {
        nextConditionType = ExpressionChain.Type.AND;
        return this;
    }

    /**
     * Append new sub-condition after AND
     * 
     * @param subCondition
     * @return the same instance
     */
    public Condition and(final Condition subCondition) {
        chain.add(ExpressionChain.Type.AND, subCondition.chain);
        return this;
    }

    /**
     * Prepare for appending new condition after OR
     * 
     * @return the same instance
     */
    public Condition or() {
        nextConditionType = ExpressionChain.Type.OR;
        return this;
    }

    /**
     * Append new sub-condition after OR
     * 
     * @param subCondition
     * @return the same instance
     */
    public Condition or(final Condition subCondition) {
        chain.add(ExpressionChain.Type.OR, subCondition.chain);
        return this;
    }

    /**
     * Build condition: param equals value
     * 
     * @param param
     * @param value
     * @return the same instance
     */
    public Condition eq(final Object param, final Object value) {
        return addRelation(param, value, RelationType.EQ, false);
    }

    /**
     * Case insensitive equals
     * 
     * @param param
     * @param value
     * @return the same instance
     */
    public Condition eqi(final Object param, final String value) {
        return addRelation(param, value, RelationType.EQ, false, true);
    }

    /**
     * Case insensitive equals
     * 
     * @param param
     * @param value
     * @param omittable
     *            flag indicates whether condition can be omitted if it's
     *            evaluated as null
     * @return the same instance
     */
    public Condition eqi(final Object param, final String value, final boolean omittable) {
        return addRelation(param, value, RelationType.EQ, omittable, true);
    }

    /**
     * Build condition: param equals value
     * 
     * @param param
     * @param value
     * @param omittable
     *            flag indicates whether condition can be omitted if it's
     *            evaluated as null
     * @return the same instance
     */
    public Condition eq(final Object param, final Object value, final boolean omittable) {
        return addRelation(param, value, RelationType.EQ, omittable);
    }

    /**
     * Build condition: param not equals value
     * 
     * @param param
     * @param value
     * @return the same instance
     */
    public Condition neq(final Object param, final Object value) {
        return addRelation(param, value, RelationType.NEQ, false);
    }

    /**
     * Build condition: param not equals value
     * 
     * @param param
     * @param value
     * @param omittable
     *            flag indicates whether condition can be omitted if it's
     *            evaluated as null
     * @return the same instance
     */
    public Condition neq(final Object param, final Object value, final boolean omittable) {
        return addRelation(param, value, RelationType.NEQ, omittable);
    }

    /**
     * Case insensitive not equals
     * 
     * @param param
     * @param value
     * @return the same instance
     */
    public Condition neqi(final Object param, final String value) {
        return addRelation(param, value, RelationType.NEQ, false, true);
    }

    /**
     * Case insensitive not equals
     * 
     * @param param
     * @param value
     * @param omittable
     *            flag indicates whether condition can be omitted if it's
     *            evaluated as null
     * @return the same instance
     */
    public Condition neqi(final Object param, final String value, final boolean omittable) {
        return addRelation(param, value, RelationType.NEQ, omittable, true);
    }

    /**
     * Build condition: param equal or greater value
     * 
     * @param param
     * @param value
     * @return the same instance
     */
    public Condition eg(final Object param, final Object value) {
        return addRelation(param, value, RelationType.EG, false);
    }

    /**
     * Build condition: param equal or greater value
     * 
     * @param param
     * @param value
     * @param omittable
     *            flag indicates whether condition can be omitted if it's
     *            evaluated as null
     * @return the same instance
     */
    public Condition eg(final Object param, final Object value, final boolean omittable) {
        return addRelation(param, value, RelationType.EG, omittable);
    }

    /**
     * Build condition: param equal or less value
     * 
     * @param param
     * @param value
     * @return the same instance
     */
    public Condition el(final Object param, final Object value) {
        return addRelation(param, value, RelationType.EL, false);
    }

    /**
     * Build condition: param equal or less value
     * 
     * @param param
     * @param value
     * @param omittable
     *            flag indicates whether condition can be omitted if it's
     *            evaluated as null
     * @return the same instance
     */
    public Condition el(final Object param, final Object value, final boolean omittable) {
        return addRelation(param, value, RelationType.EL, omittable);
    }

    /**
     * Build condition: param greater than value
     * 
     * @param param
     * @param value
     * @return the same instance
     */
    public Condition gt(final Object param, final Object value) {
        return addRelation(param, value, RelationType.GT, false);
    }

    /**
     * Build condition: param greater than value
     * 
     * @param param
     * @param value
     * @param omittable
     *            flag indicates whether condition can be omitted if it's
     *            evaluated as null
     * @return the same instance
     */
    public Condition gt(final Object param, final Object value, final boolean omittable) {
        return addRelation(param, value, RelationType.GT, omittable);
    }

    /**
     * Build condition: param less than value
     * 
     * @param param
     * @param value
     * @return the same instance
     */
    public Condition lt(final Object param, final Object value) {
        return addRelation(param, value, RelationType.LT, false);
    }

    /**
     * Build condition: param less than value
     * 
     * @param param
     * @param value
     * @param omittable
     *            flag indicates whether condition can be omitted if it's
     *            evaluated as null
     * @return the same instance
     */
    public Condition lt(final Object param, final Object value, final boolean omittable) {
        return addRelation(param, value, RelationType.LT, omittable);
    }

    /**
     * Build condition: param equals value using regular expression
     * 
     * @param param
     * @param value
     * @return the same instance
     */
    public Condition regex(final Object param, final Object value) {
        return addRelation(param, value, RelationType.REGEX, false);
    }

    /**
     * Build condition: param equals value using regular expression
     * 
     * @param param
     * @param value
     * @param omittable
     *            flag indicates whether condition can be omitted if it's
     *            evaluated as null
     * @return the same instance
     */
    public Condition regex(final Object param, final Object value, final boolean omittable) {
        return addRelation(param, value, RelationType.REGEX, omittable);
    }

    /**
     * Add new relation condition
     * 
     * @param param
     * @param value
     * @param relationType
     * @param omittable
     *            flag indicates whether condition can be omitted if it's
     *            evaluated as null
     * @return the same instance
     */
    private Condition addRelation(final Object param, final Object value, final RelationType relationType,
            final boolean omittable) {
        return addRelation(param, value, relationType, omittable, false);
    }

    /**
     * Add new relation condition
     * 
     * @param param
     * @param value
     * @param relationType
     * @param omittable
     *            flag indicates whether condition can be omitted if it's
     *            evaluated as null
     * @param caseInsensitive
     *            flag indicates whether comparison is case insensitive
     * @return
     */
    private Condition addRelation(final Object param, final Object value, final RelationType relationType,
            final boolean omittable, final boolean caseInsensitive) {
        Relation relation = new Relation(recorder, param, relationType, value, omittable, caseInsensitive);
        chain.add(nextConditionType, relation);
        return this;
    }

    /**
     * Build condition: param in values
     * 
     * @param param
     * @param values
     * @return the same instance
     */
    public <T> Condition in(final Object param, final T... values) {
        return addCollection(param, false, CollectionType.IN, values);
    }

    /**
     * Build condition: param in values
     * 
     * @param param
     * @param values
     * @param omittable
     *            flag indicates whether condition can be omitted if it's
     *            evaluated as null
     * @return the same instance
     */
    public <T> Condition in(final Object param, final boolean omittable, final T... values) {
        return addCollection(param, omittable, CollectionType.IN, values);
    }

    /**
     * Build condition: param not in values
     * 
     * @param param
     * @param values
     * @return the same instance
     */
    public <T> Condition notIn(final Object param, final T... values) {
        return addCollection(param, false, CollectionType.NOT_IN, values);
    }

    /**
     * Build condition: param not in values
     * 
     * @param param
     * @param values
     * @param omittable
     *            flag indicates whether condition can be omitted if it's
     *            evaluated as null
     * @return the same instance
     */
    public Condition notIn(final Object param, final boolean omittable, final Object... values) {
        return addCollection(param, omittable, CollectionType.NOT_IN, values);
    }

    /**
     * Add new multi-value condition
     * 
     * @param param
     * @param omittable
     *            flag indicates whether condition can be omitted if it's
     *            evaluated as null
     * @param collectionType
     * @param values
     * @return the same instance
     */
    private Condition addCollection(final Object param, final boolean omittable, final CollectionType collectionType,
            final Object... values) {
        InExpression collection = new InExpression(recorder, param, collectionType, omittable, values);
        chain.add(nextConditionType, collection);
        return this;
    }

    ExpressionChain getChain() {
        return chain;
    }

    SqlRecorder getRecorder() {
        return recorder;
    }

}
