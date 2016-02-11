package com.mwronski.jsql.model.dql;

import com.mwronski.jsql.model.Table;
import com.mwronski.jsql.model.expressions.ExpressionChain;

/**
 * Class holds information about JOIN
 * 
 * @author Michal Wronski
 * @date 27.09.13 12:34
 */
public final class JoinStatement {

    public enum Direction {
        NONE, LEFT, RIGHT
    }

    public enum Type {
        NONE, INNER, OUTER
    }

    private Table table;
    private ExpressionChain on;
    private Type type;
    private Direction direction;

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public ExpressionChain getOn() {
        return on;
    }

    public void setOn(ExpressionChain on) {
        this.on = on;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}
