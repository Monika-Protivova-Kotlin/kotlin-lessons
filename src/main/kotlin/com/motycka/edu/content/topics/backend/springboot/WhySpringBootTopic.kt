package com.motycka.edu.content.topics.backend.springboot

import com.motycka.edu.model.Slide
import com.motycka.edu.model.Topic
import com.motycka.edu.model.highlight
import com.motycka.edu.model.inlineCode
import com.motycka.edu.model.kotlinPlayground
import kotlinx.html.*

object WhySpringBootTopic : Topic(
    title = "Why Spring Boot?",
    slides = listOf(
        BuildingWebApplicationsSlide,
        FrameworksSolveProblemsSlide,
        ManualVsSpringBootSlide
    )
)

object BuildingWebApplicationsSlide : Slide(
    header = "Building Web Applications in Kotlin",
    summary = {
        +"You've already built applications with dependency injection - what's next?"
    },
    content = {
        p {
            +"In Assignment 08, you created a well-architected application with:"
        }
        ul {
            li { strong { +"Service interfaces" }; +" - Separated business logic" }
            li { strong { +"Constructor injection" }; +" - Managed dependencies manually" }
            li { strong { +"SOLID principles" }; +" - Clean, maintainable code" }
        }

        p { +"But what if you need to build a "; strong { +"web application" }; +"?" }

        h4 { +"New Challenges" }
        ul {
            li { +"How do you handle HTTP requests and responses?" }
            li { +"Who creates and manages all your service instances?" }
            li { +"How do you connect to databases?" }
            li { +"How do you handle JSON serialization?" }
            li { +"How do you secure your application?" }
        }

        blockQuote {
            +"Building all of this from scratch would take weeks. This is where ";
            strong { +"frameworks" }; +" come in."
        }
    }
)

object FrameworksSolveProblemsSlide : Slide(
    header = "Frameworks Solve Common Problems",
    summary = {
        +"Spring Boot is a framework that handles the repetitive work so you can focus on business logic"
    },
    content = {
        p { highlight("What Spring Boot Provides:") }

        ul {
            li {
                strong { +"Automatic Dependency Injection" }
                br()
                +"No more manual wiring - Spring Boot creates and injects your services automatically"
            }
            li {
                strong { +"HTTP Server" }
                br()
                +"Built-in web server to handle requests and responses"
            }
            li {
                strong { +"JSON Serialization" }
                br()
                +"Automatic conversion between Kotlin objects and JSON"
            }
            li {
                strong { +"Database Integration" }
                br()
                +"Simple database access with minimal configuration"
            }
            li {
                strong { +"Security" }
                br()
                +"Authentication and authorization out of the box"
            }
        }

        blockQuote {
            +"Spring Boot lets you write business logic using the same patterns you learned in Assignment 08, "
            +"but handles all the web application infrastructure for you."
        }
    }
)

object ManualVsSpringBootSlide : Slide(
    header = "From Manual DI to Spring Boot",
    summary = {
        +"See how Spring Boot simplifies the code you've already written"
    },
    content = {
        p { strong { +"Manual Dependency Injection (Assignment 08):" } }
        kotlinPlayground(
            code = """
                // Define interface
                interface TaskService {
                    fun getTasks(): List<String>
                }

                // Implement service
                class TaskServiceImpl : TaskService {
                    override fun getTasks() = listOf("Task A", "Task B", "Task C")
                }

                // Create and inject manually in main()
                fun main() {
                    val taskService: TaskService = TaskServiceImpl()
                    val taskManager = TaskManagerApp(taskService)
                    taskManager.start()
                }
            """.trimIndent(),
            executable = false
        )

        p { strong { +"With Spring Boot:" } }
        kotlinPlayground(
            code = """
                // Add @Service annotation
                @Service
                class TaskService {
                    fun getTasks() = listOf("Task A", "Task B", "Task C")
                }

                // Spring Boot creates and injects automatically!
                @RestController
                class TaskController(
                    private val taskService: TaskService  // Injected by Spring Boot
                ) {
                    @GetMapping("/tasks")
                    fun getTasks() = taskService.getTasks()
                }

                // No manual wiring needed - Spring Boot handles it
            """.trimIndent(),
            executable = false
        )

        blockQuote {
            strong { +"Same patterns, less boilerplate. " }
            +"Spring Boot automates what you did manually in Assignment 08."
        }
    },
    fontSize = "75%"
)
