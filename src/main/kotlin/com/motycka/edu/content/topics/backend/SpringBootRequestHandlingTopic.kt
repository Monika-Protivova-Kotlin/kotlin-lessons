package com.motycka.edu.content.topics.backend

import com.motycka.edu.model.Slide
import com.motycka.edu.model.Topic
import com.motycka.edu.model.highlight
import com.motycka.edu.model.inlineCode
import com.motycka.edu.model.kotlinPlayground
import com.motycka.edu.model.twoColumns
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
//    summary = {
//        +""
//        inlineCode("@PathVariable")
//        +" extracts values from the URL path."
//    },
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
            executable = false,
            selectLines = 7..32
        )
        p {
            +"Spring Boot automatically converts path variables to the target type (Int, Long, String, Enum, etc.)."
        }
    }
)

object RequestParamSlide : Slide(
    header = "@RequestParam",
//    summary = {
//        +""
//        inlineCode("@RequestParam")
//        +" extracts query parameters from the URL."
//    },
    content = {
        twoColumns(
            ratio = 2 to 3,
            left = {
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
            },
            right = {
                kotlinPlayground(
                    code = """
                @RestController
                @RequestMapping("/api/tasks")
                class TaskController(
                    private val taskService: TaskService
                ) {

                    @GetMapping
                    fun searchTasks(@RequestParam title: String): List<TaskDTO> {
                        return taskService.searchByTitle(title)
                    }

                    @GetMapping
                    fun getAllTasks(
                        @RequestParam(defaultValue = "0") page: Int,
                        @RequestParam(defaultValue = "10") size: Int
                    ): List<TaskDTO> {
                        return taskService.getAllTasks(page, size)
                    }

                    @GetMapping
                    fun filterTasks(
                        @RequestParam(required = false) priority: TaskPriority?,
                        @RequestParam(required = false) status: TaskStatus?,
                        @RequestParam(required = false) assignee: String?,
                        @RequestParam(defaultValue = "false") includeArchived: Boolean
                    ): List<TaskDTO> {
                        return taskService.filterTasks(priority, status, assignee, includeArchived)
                    }

                    @GetMapping("/search")
                    fun search(@RequestParam("q") query: String): List<TaskDTO> {
                        return taskService.search(query)
                    }
                }
            """.trimIndent(),
                    executable = false,
                )
            }
        )
    }
)

object RequestBodySlide : Slide(
    header = "@RequestBody",
//    summary = {
//        +""
//        inlineCode("@RequestBody")
//        +" deserializes the request body into a Kotlin object."
//    },
    content = {
        twoColumns(
            ratio = 2 to 3,
            left = {
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
                p {
                    +"Spring Boot automatically deserializes the JSON request body into the specified Kotlin data class using Jackson."
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
            """.trimIndent(),
                    executable = false,
                )
            },
            right = {
                kotlinPlayground(
                    code = """
                @RestController
                @RequestMapping("/api")
                class TaskController(
                    private val taskService: TaskService
                ) {

                    @PostMapping("/tasks")
                    @ResponseStatus(HttpStatus.CREATED)
                    fun createTask(@RequestBody request: CreateTaskRequest): TaskDTO {
                        return taskService.createTask(request)
                    }

                    @PutMapping("/tasks/{id}")
                    fun updateTask(
                        @PathVariable id: Long,
                        @RequestBody request: UpdateTaskRequest
                    ): TaskDTO {
                        return taskService.updateTask(id, request)
                    }

                    @PostMapping("/tasks/assign")
                    @ResponseStatus(HttpStatus.OK)
                    fun assignTask(@RequestBody request: AssignTaskRequest): TaskDTO {
                        return taskService.assignTask(request)
                    }
                }
            """.trimIndent(),
                    executable = false,
                )
            },
        )
    }
)

object ResponseHandlingSlide : Slide(
    header = "Response Handling",
    summary = {
        +"Controllers can return different types of responses with appropriate HTTP status codes."
    },
    content = {
        twoColumns(
            ratio = 2 to 3,
            left = {
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
            },
            right = {
                kotlinPlayground(
                    code = """
                @RestController
                @RequestMapping("/api/tasks")
                class TaskController(
                    private val taskService: TaskService
                ) {

                    @GetMapping("/{id}")
                    fun getTask(@PathVariable id: Long): TaskDTO {
                        return taskService.getTaskById(id)
                    }

                    @PostMapping
                    @ResponseStatus(HttpStatus.CREATED)  // Returns 201 Created
                    fun createTask(@RequestBody request: CreateTaskRequest): TaskDTO {
                        return taskService.createTask(request)
                    }

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
        twoColumns(
            ratio = 2 to 3,
            left = {
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
            },
            right = {
                kotlinPlayground(
                    code = """
                @RestController
                @RequestMapping("/api/tasks")
                class TaskController(
                    private val taskService: TaskService
                ) {

                    @GetMapping("/{id}")
                    fun getTask(@PathVariable id: Long): ResponseEntity<TaskDTO> {
                        val task = taskService.findTaskById(id)
                        return if (task != null) {
                            ResponseEntity.ok(task)  // 200 OK
                        } else {
                            ResponseEntity.notFound().build()  // 404 Not Found
                        }
                    }

                    @PostMapping
                    fun createTask(@RequestBody request: CreateTaskRequest): ResponseEntity<TaskDTO> {
                        val task = taskService.createTask(request)
                        return ResponseEntity
                            .status(HttpStatus.CREATED)  // 201 Created
                            .header("Location", "/api/tasks/${DOLLAR}{task.id}")
                            .body(task)
                    }

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
