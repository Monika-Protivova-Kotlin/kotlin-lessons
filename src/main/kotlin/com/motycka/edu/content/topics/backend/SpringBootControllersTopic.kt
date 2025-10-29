package com.motycka.edu.content.topics.backend

import com.motycka.edu.model.Slide
import com.motycka.edu.model.Topic
import com.motycka.edu.model.highlight
import com.motycka.edu.model.inlineCode
import com.motycka.edu.model.kotlinPlayground
import kotlinx.html.*

object SpringBootControllersTopic : Topic(
    title = "Spring Boot Controllers & Routing",
    slides = listOf(
        ControllersIntroSlide,
        RestControllerSlide,
        RequestMappingSlide,
        HTTPMethodAnnotationsSlide,
        ControllerExampleSlide,
        RoutingBestPracticesSlide
    )
)

object ControllersIntroSlide : Slide(
    header = "Controllers in Spring Boot",
    summary = {
        +"Controllers are the entry point for HTTP requests in Spring Boot applications."
    },
    content = {
        p {
            +"In Spring Boot, controllers are classes that handle incoming HTTP requests and return responses. "
            +"They act as the "
            strong { +"presentation layer" }
            +" of your application, translating HTTP requests into business logic calls."
        }
        p {
            +"Controllers are responsible for:"
        }
        ul {
            li { +"Receiving and validating HTTP requests" }
            li { +"Calling appropriate service layer methods" }
            li { +"Transforming data between DTOs and domain models" }
            li { +"Returning HTTP responses with appropriate status codes" }
        }
        p {
            +"Spring Boot provides annotations to easily define controllers and map HTTP endpoints to handler methods."
        }
    }
)

object RestControllerSlide : Slide(
    header = "@RestController",
    summary = {
        +""
        inlineCode("@RestController")
        +" marks a class as a REST API controller."
    },
    content = {
        p {
            +"The "
            inlineCode("@RestController")
            +" annotation combines "
            inlineCode("@Controller")
            +" and "
            inlineCode("@ResponseBody")
            +", indicating that:"
        }
        ul {
            li { +"This class will handle HTTP requests" }
            li { +"All methods will return data (not views)" }
            li { +"Responses will be automatically serialized to JSON" }
        }
        kotlinPlayground(
            code = """
                import org.springframework.web.bind.annotation.RestController
                import org.springframework.web.bind.annotation.RequestMapping

                @RestController
                @RequestMapping("/api/tasks")
                class TaskController {

                    // Handler methods go here

                }
            """.trimIndent(),
            executable = false
        )
        p {
            +"The "
            inlineCode("@RequestMapping")
            +" at the class level defines a "
            strong { +"base path" }
            +" for all endpoints in this controller."
        }
    }
)

object RequestMappingSlide : Slide(
    header = "@RequestMapping",
    summary = {
        +""
        inlineCode("@RequestMapping")
        +" maps HTTP requests to handler methods or controller classes."
    },
    content = {
        p {
            +"The "
            inlineCode("@RequestMapping")
            +" annotation can be used at:"
        }
        ul {
            li {
                strong { +"Class level" }
                +" - defines base path for all endpoints in the controller"
            }
            li {
                strong { +"Method level" }
                +" - defines specific endpoint path and HTTP method"
            }
        }
        p { +"Examples:" }
        kotlinPlayground(
            code = """
                @RestController
                @RequestMapping("/api/tasks")  // Base path
                class TaskController {

                    @RequestMapping(method = RequestMethod.GET)  // GET /api/tasks
                    fun getAllTasks(): List<Task> {
                        // Return list of tasks
                    }

                    @RequestMapping(value = "/{id}", method = RequestMethod.GET)  // GET /api/tasks/{id}
                    fun getTask(@PathVariable id: Long): Task {
                        // Return task by ID
                    }
                }
            """.trimIndent(),
            executable = false
        )
        p {
            +"However, "
            inlineCode("@RequestMapping")
            +" is verbose. Spring provides shorthand annotations for common HTTP methods."
        }
    }
)

object HTTPMethodAnnotationsSlide : Slide(
    header = "HTTP Method Annotations",
    summary = {
        +"Spring Boot provides shorthand annotations for each HTTP method."
    },
    content = {
        p {
            +"Instead of using "
            inlineCode("@RequestMapping(method = ...)")
            +", you can use these specialized annotations:"
        }
        ul {
            li {
                strong { inlineCode("@GetMapping") }
                +" - Handle GET requests (retrieve data)"
            }
            li {
                strong { inlineCode("@PostMapping") }
                +" - Handle POST requests (create new resources)"
            }
            li {
                strong { inlineCode("@PutMapping") }
                +" - Handle PUT requests (update entire resource)"
            }
            li {
                strong { inlineCode("@PatchMapping") }
                +" - Handle PATCH requests (partial update)"
            }
            li {
                strong { inlineCode("@DeleteMapping") }
                +" - Handle DELETE requests (remove resources)"
            }
        }
        kotlinPlayground(
            code = """
                @RestController
                @RequestMapping("/api/tasks")
                class TaskController {

                    @GetMapping  // GET /api/tasks
                    fun getAllTasks(): List<Task> = TODO()

                    @GetMapping("/{id}")  // GET /api/tasks/{id}
                    fun getTaskById(@PathVariable id: Long): Task = TODO()

                    @PostMapping  // POST /api/tasks
                    fun createTask(@RequestBody task: Task): Task = TODO()

                    @PutMapping("/{id}")  // PUT /api/tasks/{id}
                    fun updateTask(
                        @PathVariable id: Long,
                        @RequestBody task: Task
                    ): Task = TODO()

                    @DeleteMapping("/{id}")  // DELETE /api/tasks/{id}
                    fun deleteTask(@PathVariable id: Long): Unit = TODO()
                }
            """.trimIndent(),
            executable = false
        )
    }
)

object ControllerExampleSlide : Slide(
    header = "Complete Controller Example",
    content = {
        kotlinPlayground(
            code = """
                    @RestController
                    @RequestMapping("/api/tasks")
                    class TaskController(
                        private val taskService: TaskService
                    ) {
                        @GetMapping
                        fun getAllTasks(
                            @RequestParam(required = false) title: String?
                        ): ResponseEntity<List<TaskResponse>> {
                            val tasks = taskService.getAllTasks(title)
                            return ResponseEntity.ok(tasks)
                        }

                        @GetMapping("/{id}")
                        fun getTaskById(@PathVariable id: Long): ResponseEntity<TaskResponse> {
                            val task = taskService.getTaskById(id)
                            return ResponseEntity.ok(task)
                        }

                        @PostMapping
                        fun createTask(
                            @Valid @RequestBody request: TaskRequest
                        ): ResponseEntity<TaskResponse> {
                            val created = taskService.createTask(request)
                            return ResponseEntity.status(HttpStatus.CREATED).body(created)
                        }

                        @PutMapping("/{id}")
                        fun updateTask(
                            @PathVariable id: Long,
                            @Valid @RequestBody request: TaskRequest
                        ): ResponseEntity<TaskResponse> {
                            val updated = taskService.updateTask(id, request)
                            return ResponseEntity.ok(updated)
                        }

                        @DeleteMapping("/{id}")
                        fun deleteTask(@PathVariable id: Long): ResponseEntity<Void> {
                            taskService.deleteTask(id)
                            return ResponseEntity.noContent().build()
                        }
                    }
            """.trimIndent(),
            executable = false
        )
        p {
            +"Notice how the "
            inlineCode("TaskService")
            +" is injected via constructor injection, following Spring Boot's dependency injection pattern."
        }
    }
)

object RoutingBestPracticesSlide : Slide(
    header = "Routing Best Practices",
    summary = {
        +"Follow these best practices when designing REST API routes."
    },
    content = {
        ul {
            li {
                strong { +"Use nouns, not verbs" }
                br()
                +"Good: "
                inlineCode("/api/tasks")
                br()
                +"Bad: "
                inlineCode("/api/getTasks")
            }
            li {
                strong { +"Use plural nouns for collections" }
                br()
                inlineCode("/api/tasks")
                +" not "
                inlineCode("/api/task")
            }
            li {
                strong { +"Use proper HTTP methods" }
                br()
                +"GET for retrieval, POST for creation, PUT/PATCH for updates, DELETE for removal"
            }
            li {
                strong { +"Keep URLs hierarchical" }
                br()
                inlineCode("/api/tasks/{id}/subtasks")
                +" - subtasks for a specific task"
            }
            li {
                strong { +"Version your API" }
                br()
                inlineCode("/api/v1/tasks")
                +" or "
                inlineCode("/api/v2/tasks")
            }
            li {
                strong { +"Use consistent naming" }
                br()
                +"Stick to either camelCase or snake_case throughout your API"
            }
            li {
                strong { +"Return appropriate HTTP status codes" }
                br()
                +"200 OK, 201 Created, 204 No Content, 400 Bad Request, 404 Not Found, etc."
            }
        }
    }
)
