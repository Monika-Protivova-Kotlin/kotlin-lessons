package com.motycka.edu.content.topics.backend.springboot

import com.motycka.edu.model.Slide
import com.motycka.edu.model.Topic
import com.motycka.edu.model.highlight
import kotlinx.html.*

object KotlinApplicationsTopic : Topic(
    title = "Kotlin Applications",
    slides = listOf(
        KotlinApplicationsSlide
    )
)

object KotlinApplicationsSlide : Slide(
    header = "Kotlin Applications",
    summary = {
        +"Java is one of the most popular programming languages for "
        strong { highlight("WEB APPLICATION DEVELOPMENT") }
        +", and since Java and Kotlin are interoperable, Kotlin can be used with Java applications frameworks."
    },
    content = {
        p {
            +"Java/Kotlin is so popular, because of its core features like platform independence, automatic memory management, garbage collection, and security, "
            +"which makes it suitable for building small or large scale enterprise applications that can run on any platform."
        }
        p {
            +"A very common Java application is for developing so-called "
            strong { highlight("APPLICATION BACKENDS") }
            +","
            br()
            +"which are the server-side applications that power client-side applications, such as web applications, "
            +"mobile apps, and other web services, by providing business logic, database access, user authentication, and other services."
        }
        p {
            +"Client-side applications are usually developed using JavaScript, HTML,"
            br()
            +"and CSS and are also called "
            strong { highlight("APPLICATION FRONTENDS") }
            +"."
        }
    }
)
