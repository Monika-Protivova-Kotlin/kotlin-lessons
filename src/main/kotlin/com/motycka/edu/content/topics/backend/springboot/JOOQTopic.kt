package com.motycka.edu.content.topics.backend.springboot

import com.motycka.edu.model.Slide
import com.motycka.edu.model.Topic
import com.motycka.edu.model.highlight
import com.motycka.edu.model.kotlinPlayground
import kotlinx.html.*

object JOOQTopic : Topic(
    title = "Other Tools",
    slides = listOf(
        JOOQSlide
    )
)

object JOOQSlide : Slide(
    header = "JOOQ",
    content = {
        p {
            +"One of the popular alternatives to JPA and JDBC is "
            strong { highlight("JOOQ") }
            +"."
        }
        p {
            strong { highlight("JOOQ") }
            +" is a type-safe SQL query builder for Java/Kotlin."
        }
        p {
            +"With "
            strong { highlight("JOOQ") }
            +", you can write SQL queries in a type-safe way, using a fluent API."
        }
        p {
            +"It also comes with the code generation tool that generates Java/Kotlin classes from the database schema."
        }
        kotlinPlayground(
            """
            |val context = DSL.using(dataSource, SQLDialect.H2)
            |
            |val tasks = context.selectFrom(TASKS)
            |    .where(
            |        TASKS.STATUS.eq("NEW"),
            |        TASKS.CREATED_BY.eq(1L)
            |    )
            |    .fetch(Records.mapping(::TaskEntity.java))
            """,
            executable = false
        )
    }
)
