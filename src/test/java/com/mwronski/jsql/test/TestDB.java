package com.mwronski.jsql.test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.HashMap;
import java.util.Map;

/**
 * Test DB functions for testing purposes
 * 
 * @author Michal Wronski
 */
public final class TestDB {

    // test DB credentials
    private static final String DB_NAME = "jsql";
    private static final String DB_PORT = "5432";
    private static final String DB_USERNAME = "jsql";
    private static final String DB_PASSWORD = "jsql";
    // test DB settings
    private static final String DB_DRIVER = "org.postgresql.Driver";
    private static final String DB_URL = "jdbc:postgresql://localhost:" + DB_PORT + "/" + DB_NAME;
    private static final String DB_DIALECT = "org.hibernate.dialect.PostgreSQLDialect";
    private static final String DB_HBM_DDL = "create-drop";
    // entity manager and connection
    private static EntityManagerFactory entityManagerFactory;

    static {
        initEntityManagerFactory();
    }

    public static EntityManager getEntityManager() {
        if (entityManagerFactory == null) {
            throw new RuntimeException("Entity manager factory not build for - database URL: " + DB_URL + ", user: "
                    + DB_USERNAME + ", password: " + DB_PASSWORD);
        }
        return entityManagerFactory.createEntityManager();
    }

    private static void initEntityManagerFactory() {
        if (entityManagerFactory == null) {
            Map<String, String> factoryProperties = new HashMap<String, String>();
            factoryProperties.put("hibernate.connection.driver_class", DB_DRIVER);
            factoryProperties.put("hibernate.connection.url", DB_URL);
            factoryProperties.put("hibernate.connection.username", DB_USERNAME);
            factoryProperties.put("hibernate.connection.password", DB_PASSWORD);
            factoryProperties.put("hibernate.dialect", DB_DIALECT);
            factoryProperties.put("hibernate.hbm2ddl.auto", DB_HBM_DDL);
            entityManagerFactory = Persistence.createEntityManagerFactory("dtsTestPersistenceUnit", factoryProperties);
        }
    }

}
