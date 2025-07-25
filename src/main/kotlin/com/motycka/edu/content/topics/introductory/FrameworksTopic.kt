package com.motycka.edu.content.topics.introductory

import com.motycka.edu.model.Slide
import com.motycka.edu.model.Topic
import com.motycka.edu.model.highlight
import kotlinx.html.*

object FrameworksTopic : Topic(
    title = "Frameworks",
    slides = listOf(
        FrameworkSlide,
        PopularFrameworksSlide,
        ShouldYouUseFrameworkSlide
    )
)

object FrameworkSlide : Slide(
    header = "Framework",
    summary = { +"When we talk about frameworks, we usually mean a collection of libraries and tools that help developers build applications in a faster and more productive way." },
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
        blockQuote {
            +"Frameworks can also provide a set of conventions and best practices for building applications, which can help developers to write code that is more maintainable and scalable."
        }
    }
)

object PopularFrameworksSlide : Slide(
    header = "Popular Frameworks",
    summary = { +"There are some Kotlin-specific frameworks, but often times, Kotlin developers use Java frameworks, as Java and Kotlin are interoperable and can share ecosystem." },
    content = {
        div(classes = "row") {
            div(classes = "column") {
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
            }
            div(classes = "column") {
                h4 { +"Kotlin specific" }
                ul {
                    li { +"Ktor" }
                }
            }
        }
        p {
            +"We will use "
            highlight("Ktor")
            +" in this course, which is a modern, lightweight, idiomatic Kotlin-specific framework for building web applications and microservices."
        }
    }
)

object ShouldYouUseFrameworkSlide : Slide(
    header = "Should You Use a Framework?",
    summary = { +"Build everything from scratch or use battle-tested tools?" },
    content = {
        h4 { +"No framework" }
        div(classes = "row") {
            div(classes = "column") {
                ul {
                    attributes["style"] = "list-style-type:none; color: forestgreen"
                    li { +"+ Full control over every part of the system" }
                    li { +"+ Good for learning how things work under the hood" }
                    li { +"+ Lightweight, no hidden magic" }
                }
            }
            div(classes = "column") {
                ul {
                    attributes["style"] = "list-style-type:none; color: orangered"
                    li { +"- You have to build everything yourself (routing, HTTP handling, etc.)" }
                    li { +"- Reinventing the wheel is time-consuming and error-prone" }
                    li { +"- Hard to scale and maintain in real-world projects" }
                }
            }
        }
        br()
        br()
        h4 { +"With framework (like Ktor)" }
        div(classes = "row") {
            div(classes = "column") {
                ul {
                    attributes["style"] = "list-style-type:none; color: forestgreen"
                    li { +"+ Ready-to-use features (routing, middleware, JSON handling, authentication, etc.)" }
                    li { +"+ Speeds up development, especially for teams" }
                    li { +"+ Community support and documentation" }
                    li { +"+ Focus on business logic instead of boilerplate code" }
                    li { +"+ Encourages best practices and convention-over-configuration" }
                }
            }
            div(classes = "column") {
                ul {
                    attributes["style"] = "list-style-type:none; color: orangered"
                    li { +"- Adds abstraction layers — might feel \"magical\" " }
                    li { +"- Learning curve for framework-specific concepts" }
                    li { +"- Can feel limiting for unusual use cases" }
                }
            }
        }
    }
)
