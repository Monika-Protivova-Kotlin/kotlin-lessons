package com.motycka.edu.content.topics.backend

import com.motycka.edu.model.Topic
import com.motycka.edu.model.Slide
import com.motycka.edu.model.kotlinPlayground
import com.motycka.edu.model.highlight
import com.motycka.edu.model.inlineCode
import kotlinx.html.*
import com.motycka.edu.model.highlight
import com.motycka.edu.model.twoColumns

object SpringBootExceptionHandlingTopic : Topic(
    title = "Exception Handling in Spring Boot",
    slides = listOf(
        SpringApplicationErrorsIntroSlide,
        SpringCustomExceptionsSlide,
        SpringControllerAdviceSlide,
        SpringExceptionHandlerSlide,
//        SpringErrorHandlingCompleteExampleSlide,
        SpringErrorResponseFormatSlide,
        SpringErrorResponseExampleSlide
    )
)

object SpringApplicationErrorsIntroSlide : Slide(
    header = "Application Errors - General Concepts",
    summary = {
        +"Applications can encounter various types of errors during their execution. "
        +"These errors can be due to user input, system state, or business logic violations."
    },
    content = {
        p {
            +"So far, we have not paid great attention to the errors that can occur in our applications."
        }
        p {
            +"We know how to respond with different HTTP status codes based on expected service returned values, "
            +"but we didn't deal with error states that can occur in our applications."
        }
        p {
            +"Under normal operation, most application calls will not result in an exception. "
            +"But there are valid reasons why an exception might be thrown:"
        }
        ul {
            li {
                strong { highlight("Input validation") }
                br()
                +"For example, we may want to validate user inputs, such as valid JSON request body, or valid query parameter values. "
                +"Example: non-empty task description, valid status values, valid priority levels, etc."
            }
            li {
                strong { highlight("State validation") }
                br()
                +"For example, we may want to validate the state of the application, such as whether a user is authenticated, "
                +"or whether a task exists before updating it."
            }
            li {
                strong { highlight("Business logic validation") }
                br()
                +"For example, we may want to validate the business logic, such as whether a task can be assigned to a user, "
                +"or whether a user has permission to delete another user's task."
            }
        }
    }
)

object SpringCustomExceptionsSlide : Slide(
    header = "Defining Custom Exceptions",
    summary = {
        +"It is often a good idea to define custom exceptions for specific error conditions in your application."
    },
    content = {
        twoColumns(
            ratio = 2 to 3,
            left = {
                p {
                    +"Defining custom exceptions allows you to provide more meaningful error messages and handle specific error conditions in a more granular way. "
                    +"It also allows you to treat the same class of errors consistently across your application."
                }
                p { +"Here are a few examples of custom exceptions for a task management application:" }
                ul {
                    li {
                        inlineCode("TaskNotFoundException")
                        br()
                        +"Thrown when a requested task is not found."
                    }
                    li {
                        inlineCode("InvalidTaskDataException")
                        br()
                        +"Thrown when the input provided for creating/updating a task is invalid."
                    }
                    li {
                        inlineCode("TaskAlreadyExistsException")
                        br()
                        +"Thrown when trying to create a task that would result in a duplication."
                    }
                    li {
                        inlineCode("UnauthorizedAccessException")
                        br()
                        +"Thrown when a user is not authorized to perform an action."
                    }
                    li {
                        inlineCode("InvalidCredentialsException")
                        br()
                        +"Thrown when a user fails to authenticate."
                    }
                }
            },
            right = {
                kotlinPlayground(
                    code = """
                // Base exception for all application exceptions
                open class ApplicationException(
                    val applicationMessage: String,
                    val traceId: UUID = UUID.randomUUID(),
                    val timestamp: LocalDateTime = LocalDateTime.now()
                ) : RuntimeException("[${DOLLAR}traceId] ${DOLLAR}applicationMessage")

                // Specific exception types
                class TaskNotFoundException(taskId: Long) :
                    ApplicationException("Task with id ${DOLLAR}taskId not found")

                class InvalidTaskDataException(message: String) :
                    ApplicationException(message)

                class TaskAlreadyExistsException(taskName: String) :
                    ApplicationException("Task with name ${DOLLAR}taskName already exists")

                class TaskStateException(message: String) :
                    ApplicationException(message)

                class UnauthorizedAccessException(message: String) :
                    ApplicationException(message)
            """.trimIndent(),
                    executable = false
                )
            },
        )
    }
)

object SpringControllerAdviceSlide : Slide(
    header = "@ControllerAdvice",
    summary = {
        +""
        inlineCode("@ControllerAdvice")
        +" allows you to handle exceptions globally across all controllers."
    },
    content = {
        twoColumns(
            ratio = 2 to 3,
            left = {
                p {
                    +"Handling exceptions individually in each controller method is possible but impractical. "
                    +"Spring Boot provides "
                    inlineCode("@ControllerAdvice")
                    +" to handle exceptions globally."
                }
                p {
                    +"Benefits of using "
                    inlineCode("@ControllerAdvice")
                    +":"
                }
                ul {
                    li { +"Centralized exception handling logic" }
                    li { +"Consistent error responses across all endpoints" }
                    li { +"Clean controller code without try-catch blocks" }
                    li { +"Easy to maintain and update error handling" }
                    li { +"Can log all exceptions in one place" }
                }
                p {
                    +"A "
                    inlineCode("@ControllerAdvice")
                    +" class acts as a global exception handler that intercepts exceptions thrown by any controller in your application."
                }
            },
            right = {
                kotlinPlayground(
                    code = """
                import org.springframework.web.bind.annotation.ControllerAdvice
                import org.springframework.web.bind.annotation.ExceptionHandler
                import org.springframework.http.HttpStatus
                import org.springframework.http.ResponseEntity

                @ControllerAdvice
                class GlobalExceptionHandler {

                    // Handle specific exception types with @ExceptionHandler

                }
            """.trimIndent(),
                    executable = false
                )
            },
        )
    }
)

object SpringExceptionHandlerSlide : Slide(
    header = "@ExceptionHandler",
    summary = {
        +""
        inlineCode("@ExceptionHandler")
        +" methods define how specific exception types should be handled."
    },
    content = {
        twoColumns(
            ratio = 2 to 3,
            left = {
                p {
                    +"Inside your "
                    inlineCode("@ControllerAdvice")
                    +" class, you use "
                    inlineCode("@ExceptionHandler")
                    +" to define methods that handle specific exception types."
                }
                p {
                    +"Each handler method:"
                }
                ul {
                    li { +"Is annotated with "
                        inlineCode("@ExceptionHandler(ExceptionType::class)")
                    }
                    li { +"Takes the exception as a parameter" }
                    li { +"Returns a "
                        inlineCode("ResponseEntity<T>")
                        +" with appropriate status and body"
                    }
                    li { +"Can access request information if needed" }
                }
            },
            right = {
                kotlinPlayground(
                    code = """
                    @ExceptionHandler(TaskNotFoundException::class)
                    fun handleTaskNotFound(ex: TaskNotFoundException): ResponseEntity<ErrorResponse> {
                        val errorResponse = ErrorResponse(traceId = ex.traceId, message = ex.applicationMessage, timestamp = ex.timestamp)
                        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse)
                    }

                    @ExceptionHandler(InvalidTaskDataException::class)
                    fun handleInvalidTaskData(ex: InvalidTaskDataException): ResponseEntity<ErrorResponse> {
                        val errorResponse = ErrorResponse(traceId = ex.traceId, message = ex.applicationMessage, timestamp = ex.timestamp)
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse)
                    }

                    @ExceptionHandler(UnauthorizedAccessException::class)
                    fun handleUnauthorizedAccess(ex: UnauthorizedAccessException): ResponseEntity<ErrorResponse> {
                        val errorResponse = ErrorResponse(traceId = ex.traceId, message = ex.applicationMessage, timestamp = ex.timestamp)
                        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorResponse)
                    }

                    // Catch-all handler for unexpected exceptions
                    @ExceptionHandler(Exception::class)
                    fun handleGenericException(ex: Exception): ResponseEntity<ErrorResponse> {
                        val errorResponse = ErrorResponse(traceId = ex.traceId, message = ex.applicationMessage, timestamp = ex.timestamp)
                        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse)
                    }
            """.trimIndent(),
                    executable = false
                )
            },
        )
    }
)

object SpringErrorHandlingCompleteExampleSlide : Slide(
    header = "Complete Exception Handling Example",
    summary = {
        +"A complete example showing custom exceptions, "
        inlineCode("@ControllerAdvice")
        +", and service usage."
    },
    content = {
        p { +"Define convenience functions to throw exceptions:" }
        kotlinPlayground(
            code = """
                // Convenience functions for throwing exceptions
                fun taskNotFound(id: Long): Nothing {
                    throw TaskNotFoundException(id)
                }

                fun invalidTaskData(message: String): Nothing {
                    throw InvalidTaskDataException(message)
                }

                fun taskStateError(message: String): Nothing {
                    throw TaskStateException(message)
                }

                fun unauthorizedAccess(message: String): Nothing {
                    throw UnauthorizedAccessException(message)
                }
            """.trimIndent(),
            executable = false
        )
        p { +"Use exceptions in your service layer:" }
        kotlinPlayground(
            code = """
                @Service
                class TaskService(
                    private val taskRepository: TaskRepository,
                    private val userRepository: UserRepository
                ) {

                    fun getTaskById(id: Long): Task {
                        val taskEntity = taskRepository.findById(id)
                            ?: taskNotFound(id)  // Throws exception if not found
                        return taskEntity.toDomain()
                    }

                    fun createTask(request: NewTaskRequest, userId: Long): Task {
                        // Validate input
                        if (request.description.isBlank()) {
                            invalidTaskData("Task description cannot be empty")
                        }
                        if (request.description.length > 1000) {
                            invalidTaskData("Task description cannot exceed 1000 characters")
                        }

                        val taskEntity = TaskEntity(
                            description = request.description,
                            status = TaskStatus.TODO,
                            createdBy = userId
                        )
                        return taskRepository.save(taskEntity).toDomain()
                    }

                    fun deleteTask(taskId: Long, userId: Long) {
                        val task = taskRepository.findById(taskId)
                            ?: taskNotFound(taskId)

                        // Check authorization
                        if (task.createdBy != userId) {
                            unauthorizedAccess("You can only delete your own tasks")
                        }

                        // Check state
                        if (task.status == TaskStatus.IN_PROGRESS) {
                            taskStateError("Cannot delete a task that is in progress")
                        }

                        taskRepository.delete(task)
                    }
                }
            """.trimIndent(),
            executable = false
        )
        p {
            +"The controller doesn't need try-catch blocks - exceptions are automatically caught by "
            inlineCode("@ControllerAdvice")
            +":"
        }
        kotlinPlayground(
            code = """
                @RestController
                @RequestMapping("/api/tasks")
                class TaskController(
                    private val taskService: TaskService
                ) {

                    @GetMapping("/{id}")
                    fun getTask(@PathVariable id: Long): Task {
                        // If task not found, exception is thrown and caught by @ControllerAdvice
                        return taskService.getTaskById(id)
                    }

                    @PostMapping
                    @ResponseStatus(HttpStatus.CREATED)
                    fun createTask(
                        @RequestBody request: NewTaskRequest,
                        @AuthenticationPrincipal user: User
                    ): Task {
                        // If validation fails, exception is thrown and caught by @ControllerAdvice
                        return taskService.createTask(request, user.id)
                    }

                    @DeleteMapping("/{id}")
                    @ResponseStatus(HttpStatus.NO_CONTENT)
                    fun deleteTask(
                        @PathVariable id: Long,
                        @AuthenticationPrincipal user: User
                    ) {
                        // If task not found or unauthorized, exception is thrown and caught by @ControllerAdvice
                        taskService.deleteTask(id, user.id)
                    }
                }
            """.trimIndent(),
            executable = false
        )
    }
)

object SpringErrorResponseFormatSlide : Slide(
    header = "Consistent Error Response Format",
    summary = {
        +"Define a standard error response format for all API errors."
    },
    content = {
        p {
            +"It's a good practice to provide error responses in a standard format with meaningful error messages "
            +"and traceId to help debug issues."
        }
        p {
            +"Define a common "
            inlineCode("ErrorResponse")
            +" data class:"
        }
        kotlinPlayground(
            code = """
                data class ErrorResponse(
                    val traceId: UUID,
                    val message: String,
                    val timestamp: LocalDateTime
                )
            """.trimIndent(),
            executable = false
        )
        p {
            +"Extension function to convert exceptions to error responses:"
        }
        kotlinPlayground(
            code = """
                fun ApplicationException.toErrorResponse() = ErrorResponse(
                    traceId = this.traceId,
                    message = this.applicationMessage,
                    timestamp = this.timestamp
                )
            """.trimIndent(),
            executable = false
        )
        p {
            +"This provides consistent error handling across your entire API with proper HTTP status codes "
            +"and traceable error messages."
        }
    }
)

object SpringErrorResponseExampleSlide : Slide(
    header = "Consistent Error Response Format",
    content = {
        p {
            +"Complete "
            inlineCode("@ControllerAdvice")
            +" with consistent error responses:"
        }
        kotlinPlayground(
            code = """
                @ControllerAdvice
                class GlobalExceptionHandler {

                    private val logger = LoggerFactory.getLogger(this::class.java)

                    @ExceptionHandler(ApplicationException::class)
                    fun handleApplicationException(ex: ApplicationException): ResponseEntity<ErrorResponse> {
                        logger.error("Application exception: ${DOLLAR}{ex.message}", ex)

                        val status = when (ex) {
                            is TaskNotFoundException -> HttpStatus.NOT_FOUND
                            is InvalidTaskDataException -> HttpStatus.BAD_REQUEST
                            is UnauthorizedAccessException -> HttpStatus.FORBIDDEN
                            is TaskAlreadyExistsException -> HttpStatus.CONFLICT
                            is TaskStateException -> HttpStatus.CONFLICT
                            else -> HttpStatus.INTERNAL_SERVER_ERROR
                        }

                        return ResponseEntity.status(status).body(ex.toErrorResponse())
                    }

                    @ExceptionHandler(Exception::class)
                    fun handleGenericException(ex: Exception): ResponseEntity<ErrorResponse> {
                        logger.error("Unexpected exception: ${DOLLAR}{ex.message}", ex)

                        val errorResponse = ErrorResponse(
                            traceId = UUID.randomUUID(),
                            message = "An unexpected error occurred",
                            timestamp = LocalDateTime.now()
                        )
                        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse)
                    }
                }
            """.trimIndent(),
            executable = false
        )
    }
)
