package com.mwronski.jsql.jpql;

import com.mwronski.jsql.common.QueryTest;
import com.mwronski.jsql.grammar.SqlGrammar;
import com.mwronski.jsql.grammar.jpql.JPQL;

public class JPQLQueryTest extends QueryTest {

    @Override
    public SqlGrammar getSqlGrammar() {
        return new JPQL();
    }

}
