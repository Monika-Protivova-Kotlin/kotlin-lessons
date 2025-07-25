package com.motycka.edu.content.topics.backend.springboot

import com.motycka.edu.model.Slide
import com.motycka.edu.model.Topic
import com.motycka.edu.model.highlight
import kotlinx.html.*

object SpringFrameworkTopic : Topic(
    title = "Spring Framework",
    slides = listOf(
        SpringFrameworkSlide,
        SpringBootSlide
    )
)

object SpringFrameworkSlide : Slide(
    header = "Spring Framework",
    summary = {
        +"Spring Framework is a whole ecosystem of tools and libraries for Java application development."
    },
    content = {
        p {
            +"The ecosystem includes Spring Boot, Spring Cloud, Spring Data, Spring Security, Spring Integration, Spring Batch, "
            +"and many other projects, that help developers deal with common application development tasks, "
            +"such as data access, security, messaging, and more."
        }
        p {
            +"Spring Framework relies on the "
            strong { highlight("Dependency Injection (DI)") }
            +" design pattern to achieve Inversion of Control (IoC). "
            +"This means, the responsibility of creating and managing the objects is shifted from the application code to the Spring Framework."
        }
        p {
            +"It is open-source."
        }
        p {
            a(href = "https://spring.io/") { +"Spring.io" }
        }
    }
)

object SpringBootSlide : Slide(
    header = "Spring Boot",
    summary = {
        +"Spring Boot is a project built on top of the Spring Framework. "
        +"It was designed to simplify the bootstrapping and development of new Spring applications."
    },
    content = {
        p {
            +"Here are some of the features of Spring Boot ..."
        }
        ul {
            li {
                +"It can be run as a standalone application on variety of platforms."
                br()
                sub {
                    em {
                        +"There is no need for separate web container. It bundles Tomcat, Jetty, or Undertow directly."
                    }
                }
            }
            li { +"Externalized configuration and auto-configuration support, profiles" }
            li { +"Production-ready features, logging, such as metrics, health checks" }
            li { +"Web development support, REST, JSON, ..." }
            li { +"Security features and configuration" }
            li { +"Caching support" }
            li { +"Messaging support" }
            li { +"Testing support" }
        }
    }
)
