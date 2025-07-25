package com.motycka.edu.content.topics.testing

import kotlinx.html.*
import com.motycka.edu.model.Topic
import com.motycka.edu.model.Slide

object DependencyManagementTopic : Topic(
    title = "Dependency Management",
    slides = listOf(
        DependencyManagementOverviewSlide,
        DependencyManagementDetailsSlide
    )
)

object DependencyManagementOverviewSlide : Slide(
    header = "Dependency Management in Java",
    summary = {
        +"Kotlin (JDK) comes with a set of libraries that allow us to do some basic development tasks. "
        +"However, kotlin programmes often require more."
    },
    content = {
        p {
            +"As with any modern language, you can extend your code by using libraries, in Java, they are called "
            +"dependencies."
        }
        p {
            +"You could manage your dependencies manually, by adding their "
            strong { +"jar" }
            +" files to the project. Or you can use a tool to help you do that."
        }
        p {
            +"There are two major tools for project and dependency management:"
            ul {
                li { a(href = "https://maven.apache.org/") { +"Maven" } }
                li { a(href = "https://gradle.org/") { +"Gradle" } }
            }
        }
    }
)

object DependencyManagementDetailsSlide : Slide(
    header = "Dependency Management in Java/Kotlin",
    summary = {
        +"Besides managing dependencies, these tools also take care of setting up your "
        strong { +"project" }
        +", "
        strong { +"modules" }
        +", "
        strong { +"plugins" }
        +" and more."
    },
    content = {
        ol {
            li { +"Java version management" }
            li {
                +"Dependency and version management (in scope)"
                ul {
                    li { +"development, test, runtime" }
                }
            }
            li { +"Project structure" }
            li {
                +"Task configurations"
                ul {
                    li { +"build, publishing, testing" }
                    li { +"documentation, code generation, data migrations" }
                }
            }
            li {
                +"Plugins"
                ul {
                    li { +"developer tools, code quality, ..." }
                }
            }
        }
        p {
            +"Our project is using "
            a(href = "https://gradle.org/") { +"Gradle" }
            +"."
        }
    }
)