jSQL
====

DSL library for building SQL queries in Java in similar way as it's done in LINQ for .NET. 

Only DQL (Data Query Language) is supported as modifications on entities should be made using entity manager. 

**Advantages of jSQL are:** 

* no string SQL statements (queries kept as code and managed by IDE) 

* simplicity 

* no additional classes to be generated 

* no static and thread-safe pattern usage what improves performance significantly 

* support of any grammar by using AST (currently by default grammars of PostgreSQL 8.4 and JPQL are supported) 

* optimizing of conditions in queries 

# Sample of usage: 

## 1. POJO entity definition:
```java
@Entity
public class Entity {

    private Long id;
    private String string;
    private String secondString;

    public Long getId() {
        return id;
    }

    public String getString() {
        return string;
    }

    public String getSecondString() {
        return secondString;
    }

}
```
## 2. Bulding query using jSQL:
```java
//create jSQL for PostgreSQL 8.4
JSql sql = new JSql(new PostgreSQL());
//prepare recording on entity
Entity entity = sql.alias(Entity.class);
//build query
sql.select(entity.getId(), entity.getString()).from(entity)
   .where(sql.cond().eq(entity.getId(), 5).and().eq(entity.getString(), "aaa"));
//build JPA query
EntityManager em = ... //provide your entity manager here
Query query = sql.getQuery(em , Entity.class);
query.getResultList();
```

# Build 

jSQL is using test DB to check whether all built SQL statements are correct.

Therefore before making build, PostgreSQL 8.4 database must be created with following credentials:

```
Database: jsql
Port: 5432
Username: jsql
Password: jsql
```

After setting up database just run maven:

```
mvn clean install
```






