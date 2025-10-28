package com.motycka.edu.content.topics.design.patterns

import com.motycka.edu.builders.CourseBuilder.ARROW_DOWN
import com.motycka.edu.model.Slide
import com.motycka.edu.model.Topic
import com.motycka.edu.model.highlight
import com.motycka.edu.model.inlineCode
import com.motycka.edu.model.kotlinPlayground
import com.motycka.edu.model.twoColumns
import kotlinx.html.*

object SingletonPatternTopic : Topic(
    title = "Singleton Pattern",
    subtitle = "with exercises",
    slides = listOf(
        SingletonPatternExplanationSlide,
        SingletonPatternExplanationExampleSlide,
        SingletonExercise1Slide,
        SingletonExercise2Slide
    )
)

object SingletonPatternExplanationSlide : Slide(
    header = "Singleton Pattern (Creational)",
    summary = {
        +"Ensures a class has only one instance and provides a global point of access to it."
    },
    content = {
        p {
            +"The Singleton pattern restricts the instantiation of a class to a single instance. "
            +"This is useful when exactly one object is needed to coordinate actions across the system."
        }
        p {
            +"In Kotlin, the Singleton pattern can be easily implemented using the "; inlineCode("object"); +" declaration, "
            +"which automatically ensures that only one instance of the object exists."
        }

        p { highlight("When to use:") }
        ul {
            li { +"Managing shared resources (database connections, configuration, logging)" }
            li { +"Caching and thread pools" }
            li { +"Coordinating system-wide actions" }
        }

        p { highlight("Pros & Cons:") }
        ul {
            li { +"✓ Controlled access to single instance" }
            li { +"✓ Reduced namespace pollution" }
//            li { +"✗ Violates Single Responsibility Principle" }
            li { +"✗ Global state can lead to hidden dependencies" }
            li { +"✗ Difficulties in unit testing due to tight coupling" }
            li { +"✗ Potential issues in multi-threaded environments if not handled properly" }
            li { +"✗ Memory leaks if the singleton holds onto resources longer than necessary" }
        }
    }
)

object SingletonPatternExplanationExampleSlide : Slide(
    header = "Singleton Pattern (Creational)",
//    summary = {
//        +"Example"
//    },
    content = {
        kotlinPlayground(
            code = """
                object DatabaseConnection {
                    private var connectionCount = 0
                
                    fun connect() {
                        connectionCount++
                        println("Database connected. Total connections: ${'$'}connectionCount")
                    }
                
                    fun getConnectionCount() = connectionCount
                }
                
                fun main() {
                    DatabaseConnection.connect()
                    DatabaseConnection.connect()
                    println("Count: ${DOLLAR}{DatabaseConnection.getConnectionCount()}")
                }
            """,
            executable = true
        )
    }
)

object SingletonExercise1Slide : Slide(
    header = "Exercise: Application Logger",
    summary = {
        +"Implement a singleton Logger that tracks all application log messages."
    },
    content = {
        p { +"Create a singleton "; inlineCode("Logger"); +" object that centralizes application logging." }

        p { strong { +"Requirements:" } }
        ol {
            li { +"Create a singleton object "; inlineCode("Logger") }
            li { +"Add a private mutable list "; inlineCode("logs"); +" to store log messages" }
            li { +"Implement "; inlineCode("fun info(message: String)"); +" - adds \"[INFO] message\" to logs" }
            li { +"Implement "; inlineCode("fun error(message: String)"); +" - adds \"[ERROR] message\" to logs" }
            li { +"Implement "; inlineCode("fun getAllLogs(): List<String>"); +" - returns all logged messages" }
            li { +"Implement "; inlineCode("fun getErrorCount(): Int"); +" - counts error messages" }
        }
        p {
            em { +"Playground is on the next slide $ARROW_DOWN" }
        }
    }
)

object SingletonExercise2Slide : Slide(
    header = "Exercise: Application Logger",
//    summary = {
//        +"Implement a singleton Logger that tracks all application log messages."
//    },
    content = {
        p { strong { +"Playground:" } }
        kotlinPlayground(
            code = """
                object Logger {
                    // TODO: Add your code
                }
                
                fun main() {
                    Logger.info("Application started")
                    Logger.info("User logged in")
                    Logger.error("Failed to load data")
                    Logger.info("Processing request")
                    Logger.error("Network timeout")
                
                    println("All logs:")
                    Logger.getAllLogs().forEach { println(it) }
                    println("\nTotal errors: ${'$'}{Logger.getErrorCount()}")
                }
            """,
//            selectLines = 1..3,
            executable = true
        )
        em { +"Expand to see full playground code." }
        p { strong { +"Expected Output:" } }
        pre {
            +"""
                |All logs:
                |[INFO] Application started
                |[INFO] User logged in
                |[ERROR] Failed to load data
                |[INFO] Processing request
                |[ERROR] Network timeout
                |
                |Total errors: 2
            """.trimMargin()
        }
    }
)
