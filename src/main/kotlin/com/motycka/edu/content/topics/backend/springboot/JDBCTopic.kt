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
            |class TaskRepository(
            |    private val jdbcTemplate: JdbcTemplate
            |) {
            |
            |    fun findAll(): List<TaskEntity> {
            |        return jdbcTemplate.query("SELECT * FROM tasks") { resultSet, index ->
            |            TaskEntity(
            |                id = resultSet.getLong("id"),
            |                description = resultSet.getString("description"),
            |                status = TaskStatus.valueOf(resultSet.getString("status")),
            |                createdBy = resultSet.getLong("created_by")
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
            |fun findById(id: Long): TaskEntity? {
            |    return jdbcTemplate.query(
            |        "SELECT * FROM tasks WHERE id = ? LIMIT 1",
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
            |fun save(task: NewTaskRequest): TaskEntity? {
            |    return jdbcTemplate.query(
            |        "SELECT * FROM FINAL TABLE (INSERT INTO tasks (description, status, created_by) VALUES (?, ?, ?))",
            |        ::rowMapper,
            |        task.description,
            |        "NEW",
            |        task.createdBy
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
            |private fun rowMapper(rs: ResultSet, i: Int): TaskEntity {
            |    return TaskEntity(
            |        id = rs.getLong("id"),
            |        description = rs.getString("description"),
            |        status = TaskStatus.valueOf(rs.getString("status")),
            |        createdBy = rs.getLong("created_by")
            |    )
            |}
            """,
            executable = false
        )
    }
)
