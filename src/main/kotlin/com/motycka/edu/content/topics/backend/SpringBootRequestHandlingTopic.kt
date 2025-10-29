package com.motycka.edu.content.topics.backend

import com.motycka.edu.model.Slide
import com.motycka.edu.model.Topic
import com.motycka.edu.model.highlight
import com.motycka.edu.model.inlineCode
import com.motycka.edu.model.kotlinPlayground
import kotlinx.html.*

object SpringBootRequestHandlingTopic : Topic(
    title = "Spring Boot Request Handling",
    slides = listOf(
        RequestHandlingIntroSlide,
        PathVariableSlide,
        RequestParamSlide,
        RequestBodySlide,
        ResponseHandlingSlide,
        ResponseEntitySlide,
//        CompleteRequestHandlingExampleSlide
    )
)

object RequestHandlingIntroSlide : Slide(
    header = "Request Handling in Spring Boot",
    summary = {
        +"Spring Boot provides annotations to extract data from different parts of HTTP requests."
    },
    content = {
        p {
            +"HTTP requests can contain data in multiple places:"
        }
        ul {
            li {
                strong { +"Path (URL)" }
                +" - e.g., "
                inlineCode("/api/tasks/123")
                +" where "
                inlineCode("123")
                +" is the task ID"
            }
            li {
                strong { +"Query Parameters" }
                +" - e.g., "
                inlineCode("/api/tasks?priority=HIGH&status=TODO")
            }
            li {
                strong { +"Request Body" }
                +" - JSON or XML data sent with POST/PUT/PATCH requests"
            }
            li {
                strong { +"Headers" }
                +" - metadata like "
                inlineCode("Content-Type")
                +" or "
                inlineCode("Authorization")
            }
        }
        p {
            +"Spring Boot provides annotations to easily extract this data and pass it to your handler methods."
        }
    }
)

object PathVariableSlide : Slide(
    header = "@PathVariable",
    summary = {
        +""
        inlineCode("@PathVariable")
        +" extracts values from the URL path."
    },
    content = {
        p {
            +"Use "
            inlineCode("@PathVariable")
            +" to capture dynamic parts of the URL path."
        }
        p {
            +"Path variables are typically used to identify "
            strong { +"specific resources" }
            +", like a task ID or project ID."
        }
        kotlinPlayground(
            code = """
                @RestController
                @RequestMapping("/api")
                class TaskController(
                    private val taskService: TaskService
                ) {

                    // Single path variable
                    @GetMapping("/tasks/{id}")
                    fun getTaskById(@PathVariable id: Long): TaskDTO {
                        return taskService.getTaskById(id)
                    }

                    // Multiple path variables
                    @GetMapping("/tasks/{taskId}/subtasks/{subtaskId}")
                    fun getSubtask(
                        @PathVariable taskId: Long,
                        @PathVariable subtaskId: Long
                    ): SubtaskDTO {
                        return taskService.getSubtask(taskId, subtaskId)
                    }

                    // Path variable with different name
                    @GetMapping("/tasks/{id}/details")
                    fun getTaskDetails(@PathVariable("id") taskId: Long): TaskDetailsDTO {
                        return taskService.getTaskDetails(taskId)
                    }

                    // Path variable with enum
                    @GetMapping("/tasks/priority/{priority}")
                    fun getTasksByPriority(@PathVariable priority: TaskPriority): List<TaskDTO> {
                        return taskService.getTasksByPriority(priority)
                    }
                }
            """.trimIndent(),
            executable = false
        )
        p {
            +"Spring Boot automatically converts path variables to the target type (Int, Long, String, Enum, etc.)."
        }
    }
)

object RequestParamSlide : Slide(
    header = "@RequestParam",
    summary = {
        +""
        inlineCode("@RequestParam")
        +" extracts query parameters from the URL."
    },
    content = {
        p {
            +"Use "
            inlineCode("@RequestParam")
            +" to capture query parameters from the URL."
        }
        p {
            +"Query parameters are typically used for "
            strong { +"filtering, sorting, pagination" }
            +", or passing optional data."
        }
        kotlinPlayground(
            code = """
                @RestController
                @RequestMapping("/api/tasks")
                class TaskController(
                    private val taskService: TaskService
                ) {

                    // Simple query parameter
                    // GET /api/tasks?title=Report
                    @GetMapping
                    fun searchTasks(@RequestParam title: String): List<TaskDTO> {
                        return taskService.searchByTitle(title)
                    }

                    // Optional query parameter with default value
                    // GET /api/tasks?page=2&size=20 or just /api/tasks
                    @GetMapping
                    fun getAllTasks(
                        @RequestParam(defaultValue = "0") page: Int,
                        @RequestParam(defaultValue = "10") size: Int
                    ): List<TaskDTO> {
                        return taskService.getAllTasks(page, size)
                    }

                    // Multiple query parameters with different types
                    // GET /api/tasks?priority=HIGH&status=TODO&includeArchived=false
                    @GetMapping
                    fun filterTasks(
                        @RequestParam(required = false) priority: TaskPriority?,
                        @RequestParam(required = false) status: TaskStatus?,
                        @RequestParam(required = false) assignee: String?,
                        @RequestParam(defaultValue = "false") includeArchived: Boolean
                    ): List<TaskDTO> {
                        return taskService.filterTasks(priority, status, assignee, includeArchived)
                    }

                    // Query parameter with custom name
                    @GetMapping("/search")
                    fun search(@RequestParam("q") query: String): List<TaskDTO> {
                        return taskService.search(query)
                    }
                }
            """.trimIndent(),
            executable = false
        )
    }
)

object RequestBodySlide : Slide(
    header = "@RequestBody",
    summary = {
        +""
        inlineCode("@RequestBody")
        +" deserializes the request body into a Kotlin object."
    },
    content = {
        p {
            +"Use "
            inlineCode("@RequestBody")
            +" to receive and automatically deserialize JSON (or XML) data from the request body."
        }
        p {
            +"Request bodies are used with "
            strong { +"POST, PUT, and PATCH" }
            +" requests to send data to create or update resources."
        }
        kotlinPlayground(
            code = """
                data class CreateTaskRequest(
                    val title: String,
                    val description: String?,
                    val priority: TaskPriority,
                    val status: TaskStatus,
                    val dueDate: String?
                )

                data class UpdateTaskRequest(
                    val title: String?,
                    val description: String?,
                    val priority: TaskPriority?,
                    val status: TaskStatus?
                )

                data class AssignTaskRequest(
                    val taskId: Long,
                    val assigneeId: Long
                )

                @RestController
                @RequestMapping("/api")
                class TaskController(
                    private val taskService: TaskService
                ) {

                    // POST request with request body
                    // Request: POST /api/tasks
                    // Body: {"title": "Write Report", "priority": "HIGH", "status": "TODO", ...}
                    @PostMapping("/tasks")
                    @ResponseStatus(HttpStatus.CREATED)
                    fun createTask(@RequestBody request: CreateTaskRequest): TaskDTO {
                        return taskService.createTask(request)
                    }

                    // PUT request with path variable and request body
                    // Request: PUT /api/tasks/123
                    // Body: {"title": "Write Final Report", "priority": "URGENT", "status": "IN_PROGRESS"}
                    @PutMapping("/tasks/{id}")
                    fun updateTask(
                        @PathVariable id: Long,
                        @RequestBody request: UpdateTaskRequest
                    ): TaskDTO {
                        return taskService.updateTask(id, request)
                    }

                    // POST request with request body
                    // Request: POST /api/tasks/assign
                    // Body: {"taskId": 1, "assigneeId": 5}
                    @PostMapping("/tasks/assign")
                    @ResponseStatus(HttpStatus.OK)
                    fun assignTask(@RequestBody request: AssignTaskRequest): TaskDTO {
                        return taskService.assignTask(request)
                    }
                }
            """.trimIndent(),
            executable = false
        )
        p {
            +"Spring Boot automatically deserializes the JSON request body into the specified Kotlin data class using Jackson."
        }
    }
)

object ResponseHandlingSlide : Slide(
    header = "Response Handling",
    summary = {
        +"Controllers can return different types of responses with appropriate HTTP status codes."
    },
    content = {
        p {
            +"Spring Boot provides several ways to handle responses:"
        }
        ul {
            li {
                strong { +"Direct return" }
                +" - Return data directly; Spring automatically serializes to JSON with 200 OK"
            }
            li {
                strong { inlineCode("@ResponseStatus") }
                +" - Specify custom HTTP status code"
            }
            li {
                strong { inlineCode("ResponseEntity<T>") }
                +" - Full control over status, headers, and body"
            }
        }
        kotlinPlayground(
            code = """
                @RestController
                @RequestMapping("/api/tasks")
                class TaskController(
                    private val taskService: TaskService
                ) {

                    // Direct return - automatic 200 OK
                    @GetMapping("/{id}")
                    fun getTask(@PathVariable id: Long): TaskDTO {
                        return taskService.getTaskById(id)
                    }

                    // Custom status code with @ResponseStatus
                    @PostMapping
                    @ResponseStatus(HttpStatus.CREATED)  // Returns 201 Created
                    fun createTask(@RequestBody request: CreateTaskRequest): TaskDTO {
                        return taskService.createTask(request)
                    }

                    // No content response for DELETE
                    @DeleteMapping("/{id}")
                    @ResponseStatus(HttpStatus.NO_CONTENT)  // Returns 204 No Content
                    fun deleteTask(@PathVariable id: Long) {
                        taskService.deleteTask(id)
                    }
                }
            """.trimIndent(),
            executable = false
        )
    }
)

object ResponseEntitySlide : Slide(
    header = "ResponseEntity<T>",
    summary = {
        +""
        inlineCode("ResponseEntity<T>")
        +" provides full control over the HTTP response."
    },
    content = {
        p {
            +"Use "
            inlineCode("ResponseEntity<T>")
            +" when you need:"
        }
        ul {
            li { +"Dynamic status codes based on business logic" }
            li { +"Custom response headers" }
            li { +"Conditional responses (e.g., return different status for different scenarios)" }
        }
        kotlinPlayground(
            code = """
                @RestController
                @RequestMapping("/api/tasks")
                class TaskController(
                    private val taskService: TaskService
                ) {

                    // ResponseEntity with dynamic status
                    @GetMapping("/{id}")
                    fun getTask(@PathVariable id: Long): ResponseEntity<TaskDTO> {
                        val task = taskService.findTaskById(id)
                        return if (task != null) {
                            ResponseEntity.ok(task)  // 200 OK
                        } else {
                            ResponseEntity.notFound().build()  // 404 Not Found
                        }
                    }

                    // ResponseEntity with custom headers
                    @PostMapping
                    fun createTask(@RequestBody request: CreateTaskRequest): ResponseEntity<TaskDTO> {
                        val task = taskService.createTask(request)
                        return ResponseEntity
                            .status(HttpStatus.CREATED)  // 201 Created
                            .header("Location", "/api/tasks/${DOLLAR}{task.id}")
                            .body(task)
                    }

                    // ResponseEntity with conditional logic
                    @PutMapping("/{id}")
                    fun updateTask(
                        @PathVariable id: Long,
                        @RequestBody request: UpdateTaskRequest
                    ): ResponseEntity<TaskDTO> {
                        return try {
                            val updated = taskService.updateTask(id, request)
                            ResponseEntity.ok(updated)  // 200 OK
                        } catch (e: TaskNotFoundException) {
                            ResponseEntity.notFound().build()  // 404 Not Found
                        } catch (e: InvalidRequestException) {
                            ResponseEntity.badRequest().build()  // 400 Bad Request
                        }
                    }

                    // No content response
                    @DeleteMapping("/{id}")
                    fun deleteTask(@PathVariable id: Long): ResponseEntity<Unit> {
                        taskService.deleteTask(id)
                        return ResponseEntity.noContent().build()  // 204 No Content
                    }
                }
            """.trimIndent(),
            executable = false
        )
    }
)

object CompleteRequestHandlingExampleSlide : Slide(
    header = "Complete Request Handling Example",
    summary = {
        +"A complete example demonstrating all request handling techniques."
    },
    content = {
        kotlinPlayground(
            code = """
                @RestController
                @RequestMapping("/api/battles")
                class BattleController(
                    private val battleService: BattleService
                ) {

                    // GET with query parameters
                    // GET /api/battles?status=COMPLETED&limit=10
                    @GetMapping
                    fun getBattles(
                        @RequestParam(required = false) status: BattleStatus?,
                        @RequestParam(defaultValue = "10") limit: Int
                    ): ResponseEntity<List<BattleDTO>> {
                        val battles = battleService.getBattles(status, limit)
                        return ResponseEntity.ok(battles)
                    }

                    // GET with path variable
                    // GET /api/battles/123
                    @GetMapping("/{id}")
                    fun getBattleById(@PathVariable id: Long): ResponseEntity<BattleDTO> {
                        val battle = battleService.findBattleById(id)
                        return if (battle != null) {
                            ResponseEntity.ok(battle)
                        } else {
                            ResponseEntity.notFound().build()
                        }
                    }

                    // POST with request body
                    // POST /api/battles
                    // Body: {"character1Id": 1, "character2Id": 2, "battleType": "DUEL"}
                    @PostMapping
                    fun initiateBattle(@RequestBody request: InitiateBattleRequest): ResponseEntity<BattleDTO> {
                        val battle = battleService.initiateBattle(request)
                        return ResponseEntity
                            .status(HttpStatus.CREATED)
                            .header("Location", "/api/battles/${DOLLAR}{battle.id}")
                            .body(battle)
                    }

                    // GET with multiple path variables
                    // GET /api/battles/123/rounds/5
                    @GetMapping("/{battleId}/rounds/{roundNumber}")
                    fun getBattleRound(
                        @PathVariable battleId: Long,
                        @PathVariable roundNumber: Int
                    ): ResponseEntity<BattleRoundDTO> {
                        val round = battleService.getBattleRound(battleId, roundNumber)
                        return ResponseEntity.ok(round)
                    }

                    // GET with path variable and query parameters
                    // GET /api/battles/character/123?includeOngoing=true
                    @GetMapping("/character/{characterId}")
                    fun getCharacterBattles(
                        @PathVariable characterId: Long,
                        @RequestParam(defaultValue = "false") includeOngoing: Boolean
                    ): ResponseEntity<List<BattleDTO>> {
                        val battles = battleService.getCharacterBattles(characterId, includeOngoing)
                        return ResponseEntity.ok(battles)
                    }
                }
            """.trimIndent(),
            executable = false
        )
    }
)
