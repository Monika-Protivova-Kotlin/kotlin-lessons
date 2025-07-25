package com.motycka.edu.content.topics.backend.springboot

import com.motycka.edu.model.Slide
import com.motycka.edu.model.Topic
import com.motycka.edu.model.twoColumns
import kotlinx.html.*

object FrameworksTopic : Topic(
    title = "Frameworks",
    slides = listOf(
        FrameworkSlide,
        PopularFrameworksSlide
    )
)

object FrameworkSlide : Slide(
    header = "Framework",
    summary = {
        +"When we talk about frameworks, we usually mean a collection of libraries and tools that help developers build applications in a faster and more productive way."
    },
    content = {
        p {
            +"For example, a framework can provide a set of libraries for ..."
        }
        ul {
            li { +"data access (databases)" }
            li { +"security (user authorization and authentication)" }
            li { +"network communication" }
            li { +"configuration management" }
            li { +"cloud integration" }
            li { +"monitoring and logging" }
            li { +"testing" }
        }
        p {
            +"... and other common tasks, so that developers don't have to write them from scratch."
        }
    }
)

object PopularFrameworksSlide : Slide(
    header = "Popular Frameworks",
    summary = {
        +"There are some Kotlin-specific frameworks, but often times, Kotlin developers use Java frameworks, "
        +"as Java and Kotlin are interoperable and can share ecosystem."
    },
    content = {
        twoColumns(
            left = {
                h4 { +"Java" }
                ul {
                    li { +"Spring Framework" }
                    li { +"Jakarta EE (formerly Java EE)" }
                    li { +"Hibernate" }
                    li { +"Play Framework" }
                    li { +"Apache Struts" }
                    li { +"Quarkus" }
                    li { +"MicroProfile" }
                }
            },
            right = {
                h4 { +"Kotlin specific" }
                ul {
                    li { +"Ktor" }
                }
            }
        )
    }
)
