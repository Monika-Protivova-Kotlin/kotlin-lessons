package com.motycka.edu.content.topics.backend.springboot

import com.motycka.edu.model.Slide
import com.motycka.edu.model.Topic
import com.motycka.edu.model.highlight
import com.motycka.edu.model.inlineCode
import com.motycka.edu.model.kotlinPlayground
import kotlinx.html.*

object JDBCTopic : Topic(
    title = "JDBC",
    slides = listOf(
        RepositoryIntroSlide,
        JDBCSlide,
        JdbcTemplateSlide,
        JdbcTemplateQueriesSlide
    )
)

object RepositoryIntroSlide : Slide(
    header = "Repository",
    summary = {
        +"The repository layer is responsible for managing the data access logic and performing CRUD "
        sub { +"(Create, Read, Update, Delete) " }
        +"operations on the database."
    },
    content = {
        p {
            +"There is a variety of ways and frameworks you can use to implement the repository layer."
        }
        ol {
            li {
                h4 { +"Java Database Connectivity (JDBC)" }
                p {
                    +"Probably simplest option is to use "
                    strong { highlight("JdbcTemplate") }
                    +", which is a simple JDBC-based template class provided by Spring."
                }
            }
            li {
                h4 { +"Java Persistence API (JPA)" }
                p {
                    +"Java Persistence API is a standard specification for object-relational mapping in Java. "
                    +"It is designed to simplify the development of applications that need to access and manipulate data from a relational database."
                }
                p {
                    +"Spring Data project offers "
                    strong { highlight("Spring Data JPA") }
                    +" which is and implementation of JPA."
                }
                p {
                    +"Another JPA implementation is "
                    strong { highlight("Hibernate") }
                    +"."
                }
            }
            li {
                h4 { +"Other" }
                p {
                    strong { highlight("JOOQ") }
                    +" is popular a type-safe SQL query builder for Java/Kotlin."
                }
            }
        }
    }
)

object JDBCSlide : Slide(
    header = "Using JdbcTemplate",
    summary = {
        +"The "
        strong { highlight("JdbcTemplate") }
        +" class provides a set of methods for performing CRUD operations on the database, "
        +"however you need to write the SQL queries yourself."
    },
    content = {
        p {
            +"If you want to use "
            strong { highlight("JdbcTemplate") }
            +", you should create a repository class and annotate it with "
            inlineCode("@Repository")
            +" annotation."
        }
        p {
            +"Then you need to inject the "
            strong { highlight("JdbcTemplate") }
            +" bean into the repository class."
        }
        kotlinPlayground(
            """
            |@Repository
            |class UserRepository(
            |    private val jdbcTemplate: JdbcTemplate
            |) {
            |
            |    fun selectAll(): List<User> {
            |        return jdbcTemplate.query("SELECT * FROM users") { resultSet, index ->
            |            User(
            |                resultSet.getLong("id"),
            |                resultSet.getString("name")
            |            )
            |        }
            |    }
            |}
            """,
            executable = false
        )
    }
)

object JdbcTemplateSlide : Slide(
    header = "Using JdbcTemplate",
    summary = {
        +"Writing queries"
    },
    content = {
        p { +"Select with parameters" }
        kotlinPlayground(
            """
            |fun selectById(id: Long): User? {
            |    return jdbcTemplate.query(
            |        "SELECT * FROM users WHERE id = ? LIMIT 1",
            |        ::rowMapper,
            |        id
            |    ).firstOrNull()
            |}
            """,
            executable = false
        )
        p { +"Insert/update with parameters and returning results" }
        kotlinPlayground(
            """
            |fun insert(user: NewUser): User? {
            |    return jdbcTemplate.query(
            |        "SELECT * FROM FINAL TABLE (INSERT INTO users (name) VALUES (?))",
            |        ::rowMapper,
            |        user.name
            |    ).firstOrNull()
            |}
            """,
            executable = false
        )
    }
)

object JdbcTemplateQueriesSlide : Slide(
    header = "Using JdbcTemplate",
    summary = {
        +"Row mapper function"
    },
    content = {
        kotlinPlayground(
            """
            |private fun rowMapper(rs: ResultSet, i: Int): User {
            |    return User(
            |        rs.getLong("id"),
            |        rs.getString("name")
            |    )
            |}
            """,
            executable = false
        )
    }
)
