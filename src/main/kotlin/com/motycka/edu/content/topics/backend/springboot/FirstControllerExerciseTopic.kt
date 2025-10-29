package com.motycka.edu.content.topics.backend.springboot

import com.motycka.edu.model.Slide
import com.motycka.edu.model.Topic
import com.motycka.edu.model.highlight
import com.motycka.edu.model.inlineCode
import com.motycka.edu.model.kotlinPlayground
import kotlinx.html.*

object FirstControllerExerciseTopic : Topic(
    title = "Exercise: REST API Controller",
    slides = listOf(
        ProjectSetupSlide,
        RestApiExerciseSlide
    )
)

object ProjectSetupSlide : Slide(
    header = "Part 1: Create Your Spring Boot Project",
    summary = {
        +"Use Spring Boot Initializer to create a new project with the necessary dependencies"
    },
    content = {
        h4 { +"Step 1: Visit Spring Initializr" }
        p { +"Go to "; code { +"https://start.spring.io" } }

        h4 { +"Step 2: Configure Your Project" }
        ul {
            li { strong { +"Project:" }; +" Gradle - Kotlin" }
            li { strong { +"Language:" }; +" Kotlin" }
            li { strong { +"Spring Boot:" }; +" 3.x.x (latest stable version)" }
            li { strong { +"Group:" }; +" com.example (or your choice)" }
            li { strong { +"Artifact:" }; +" task-api (or your choice)" }
            li { strong { +"Name:" }; +" TaskAPI" }
            li { strong { +"Package name:" }; +" com.example.taskapi" }
            li { strong { +"Packaging:" }; +" Jar" }
            li { strong { +"Java:" }; +" 21 or 17" }
        }

        h4 { +"Step 3: Add Dependencies" }
        p { +"Click 'ADD DEPENDENCIES' and select:" }
        ul {
            li { strong { +"Spring Web" }; +" - For building REST APIs" }
            li { strong { +"Spring Boot DevTools" }; +" - For automatic restarts during development" }
        }

        h4 { +"Step 4: Generate and Import" }
        ol {
            li { +"Click 'GENERATE' to download the project" }
            li { +"Extract the ZIP file" }
            li { +"Open the project in IntelliJ IDEA" }
            li { +"Wait for Gradle to download dependencies" }
            li { +"Run the application to verify it starts successfully" }
        }
    }
)

object RestApiExerciseSlide : Slide(
    header = "Part 2: Build Your REST API",
    summary = {
        +"Create a REST controller that manages tasks with full CRUD operations"
    },
    content = {
        h4 { +"Your Task" }
        p {
            +"Create a REST API to manage tasks with all HTTP methods. "
            +"You can adapt this to other domains (projects, notes, bookmarks, etc.)."
        }

        h4 { +"Required API Endpoints" }

        p { strong { +"1. GET /api/tasks" }; +" - Get all tasks" }
        ul {
            li { +"Returns: List of all tasks" }
            li { +"Example: "; code { +"[{\"id\": 1, \"title\": \"Task A\", \"priority\": \"HIGH\", ...}, ...]" } }
        }

        p { strong { +"2. GET /api/tasks/{id}" }; +" - Get single task by ID" }
        ul {
            li { +"Path variable: "; inlineCode("id"); +" (Long)" }
            li { +"Returns: Single task or 404 if not found" }
            li { +"Example: "; code { +"{\"id\": 1, \"title\": \"Task A\", \"priority\": \"HIGH\"}" } }
        }

        p { strong { +"3. POST /api/tasks" }; +" - Create new task" }
        ul {
            li { +"Request body: "; code { +"{\"title\": \"Task C\", \"priority\": \"MEDIUM\", \"status\": \"TODO\"}" } }
            li { +"Returns: Created task with generated ID and 201 status" }
            li { +"Example response: "; code { +"{\"id\": 3, \"title\": \"Task C\", \"priority\": \"MEDIUM\"}" } }
        }

        p { strong { +"4. PUT /api/tasks/{id}" }; +" - Update task (full update)" }
        ul {
            li { +"Path variable: "; inlineCode("id"); +" (Long)" }
            li { +"Request body: "; code { +"{\"title\": \"Updated Task\", \"priority\": \"URGENT\", \"status\": \"IN_PROGRESS\"}" } }
            li { +"Returns: Updated task or 404 if not found" }
        }

        p { strong { +"5. DELETE /api/tasks/{id}" }; +" - Delete task" }
        ul {
            li { +"Path variable: "; inlineCode("id"); +" (Long)" }
            li { +"Returns: 204 No Content on success or 404 if not found" }
        }

        p { strong { +"6. PATCH /api/tasks/{id}/status" }; +" - Update only status (partial update)" }
        ul {
            li { +"Path variable: "; inlineCode("id"); +" (Long)" }
            li { +"Request body: "; code { +"{\"status\": \"DONE\"}" } }
            li { +"Returns: Updated task with 200 status" }
        }

        blockQuote {
            +"💡 "; strong { +"Data Storage: " }
            +"Store tasks in memory using "; inlineCode("mutableListOf<Task>()")
            +". In Lesson 11 (Services) and Lesson 12 (Repositories), "
            +"you'll learn proper data management and database persistence."
        }

        h4 { +"Data Model" }
        kotlinPlayground(
            code = """
                data class Task(
                    val id: Long,
                    val title: String,
                    val description: String? = null,
                    val priority: TaskPriority,
                    val status: TaskStatus,
                    val dueDate: String? = null  // Use String for simplicity (e.g., "2025-12-31")
                )

                data class TaskRequest(
                    val title: String,
                    val description: String? = null,
                    val priority: TaskPriority,
                    val status: TaskStatus,
                    val dueDate: String? = null
                )

                data class StatusUpdateRequest(
                    val status: TaskStatus
                )

                enum class TaskPriority { LOW, MEDIUM, HIGH, URGENT }
                enum class TaskStatus { TODO, IN_PROGRESS, DONE, ARCHIVED }
            """.trimIndent(),
            executable = false
        )

        h4 { +"Implementation Tips" }
        ul {
            li { +"Use "; inlineCode("@RestController"); +" and "; inlineCode("@RequestMapping(\"/api/tasks\")") }
            li { +"Store tasks in: "; code { +"private val tasks = mutableListOf<Task>()" } }
            li { +"Generate IDs with: "; code { +"private val idCounter = AtomicLong(1)" } }
            li { +"Use "; inlineCode("@GetMapping"); +", "; inlineCode("@PostMapping"); +", ";
                inlineCode("@PutMapping"); +", "; inlineCode("@DeleteMapping"); +", "; inlineCode("@PatchMapping") }
            li { +"Use "; inlineCode("@PathVariable"); +" for URL parameters: ";
                code { +"@GetMapping(\"/{id}\") fun getTask(@PathVariable id: Long)" } }
            li { +"Use "; inlineCode("@RequestBody"); +" for request bodies: ";
                code { +"@PostMapping fun createTask(@RequestBody request: TaskRequest)" } }
            li { +"Return "; inlineCode("ResponseEntity<T>"); +" with proper status codes" }
        }

        h4 { +"HTTP Status Codes" }
        ul {
            li { code { +"200 OK" }; +" - GET, PUT, PATCH success" }
            li { code { +"201 Created" }; +" - POST success ("; inlineCode("ResponseEntity.status(HttpStatus.CREATED).body(item)"); +")" }
            li { code { +"204 No Content" }; +" - DELETE success ("; inlineCode("ResponseEntity.noContent().build()"); +")" }
            li { code { +"404 Not Found" }; +" - Item doesn't exist ("; inlineCode("ResponseEntity.notFound().build()"); +")" }
        }

        h4 { +"Testing Your API" }
        p { +"Test endpoints using:" }
        ul {
            li { +"Browser (GET only)" }
            li { strong { +"Postman" }; +" - Recommended for all HTTP methods" }
            li { +"IntelliJ IDEA HTTP Client" }
            li { +"curl command line tool" }
        }
    },
    fontSize = "75%"
)
