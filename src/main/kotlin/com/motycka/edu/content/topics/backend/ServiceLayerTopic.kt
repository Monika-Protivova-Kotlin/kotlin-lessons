package com.motycka.edu.content.topics.backend

import com.motycka.edu.model.Topic
import com.motycka.edu.model.Slide
import com.motycka.edu.model.highlight
import com.motycka.edu.model.inlineCode
import com.motycka.edu.model.kotlinPlayground
import com.motycka.edu.model.twoColumns
import kotlinx.html.*

object ServiceLayerTopic : Topic(
    title = "Service Layer",
    slides = listOf(
        ServiceLayerIntroSlide,
        ServiceLayerResponsibilitiesSlide,
        BusinessLogicHandlingSlide,
        DomainModelMappingSlide,
        TransactionManagementSlide,
        AuthorizationSlide,
        DataValidationSlide,
        ErrorHandlingSlide
    )
)

object ServiceLayerIntroSlide : Slide(
    header = "Service Layer",
    summary = {
        +"The Service Layer is a logical layer that sits between the controller and the data access layer. "
        +"It encapsulates business logic and orchestrates various operations."
    },
    content = {
        p {
            +"The Service Layer acts as a bridge between the presentation layer (controllers) and the data access layer (repositories). "
            +"It's where the core business logic resides and where complex operations are coordinated."
        }
        p {
            +"Key characteristics of the Service Layer:"
        }
        ol {
            li { highlight("Contains business logic and rules") }
            li { highlight("Orchestrates multiple data access operations") }
            li { highlight("Provides transaction boundaries") }
            li { highlight("Handles data transformation and validation") }
            li { highlight("Implements authorization and security checks") }
        }
    }
)

object ServiceLayerResponsibilitiesSlide : Slide(
    header = "Service Layer Responsibilities",
    summary = {
        +"The Service Layer is responsible for several key functions that ensure the proper operation of the application."
    },
    content = {
        ol {
            li {
                highlight("Business Logic Handling")
                +" - Implements complex business rules and workflows"
            }
            li {
                highlight("Domain Model Mapping")
                +" - Converts between DTOs and domain models"
            }
            li {
                highlight("Transaction Management")
                +" - Ensures data consistency across multiple operations"
            }
            li {
                highlight("Authorization")
                +" - Enforces access control and permissions"
            }
            li {
                highlight("Data Validation")
                +" - Validates business rules beyond basic input validation"
            }
            li {
                highlight("Error Handling")
                +" - Manages exceptions and provides meaningful error messages"
            }
        }
    }
)

object BusinessLogicHandlingSlide : Slide(
    header = "Business Logic Handling",
    summary = {
        +"The Service Layer is responsible for implementing complex business rules and workflows that span multiple entities or operations."
    },
    content = {
        p {
            strong { +"Business logic includes:" }

        }
        ul {
            li { +"Validation of business rules" }
            li { +"Complex calculations and computations" }
            li { +"Workflow orchestration" }
            li { +"Integration with external services" }
            li { +"State management and transitions" }
        }
        p {
            strong { +"Example: An order processing service might:" }
        }
        ul {
            li { +"Validate inventory availability" }
            li { +"Calculate discounts and taxes" }
            li { +"Process payment through external gateway" }
            li { +"Update inventory levels" }
            li { +"Send confirmation emails" }
            li { +"Log audit information" }
        }
    }
)

object DomainModelMappingSlide : Slide(
    header = "Domain Model Mapping",
    summary = {
        +"The Service Layer handles the conversion between Data Transfer Objects (DTOs) and domain models, "
        +"ensuring proper separation of concerns between layers."
    },
    content = {
        p {
            +"Mapping responsibilities include:"
        }
        ul {
            li {
                strong { +"DTO to Domain Model" }
                +" - Converting incoming requests to internal domain objects"
            }
            li {
                strong { +"Domain Model to DTO" }
                +" - Converting internal objects to response formats"
            }
            li {
                strong { +"Data Enrichment" }
                +" - Adding computed fields or related data"
            }
            li {
                strong { +"Data Filtering" }
                +" - Removing sensitive or unnecessary information"
            }
        }
        p {
            +"This mapping ensures that:"
        }
        ul {
            li { +"Internal domain models remain clean and focused" }
            li { +"External interfaces can evolve independently" }
            li { +"Security is maintained by controlling data exposure" }
            li { +"Performance is optimized by transferring only necessary data" }
        }
    }
)

object TransactionManagementSlide : Slide(
    header = "Transaction Management",
    summary = {
        +"The Service Layer defines transaction boundaries to ensure data consistency across multiple operations."
    },
    content = {
        p {
            +"Transaction management ensures:"
        }
        ul {
            li {
                strong { +"ACID Properties" }
                +" - Atomicity, Consistency, Isolation, and Durability"
            }
            li {
                strong { +"Data Consistency" }
                +" - All related operations succeed or fail together"
            }
            li {
                strong { +"Rollback Capability" }
                +" - Ability to undo changes when errors occur"
            }
            li {
                strong { +"Isolation Levels" }
                +" - Control over concurrent access to data"
            }
        }
        p {
            +"Common transaction patterns in services:"
        }
        ul {
            li { +"Single service method = Single transaction" }
            li { +"Nested transactions for complex workflows" }
            li { +"Distributed transactions for microservices" }
            li { +"Compensating actions for saga patterns" }
        }
    }
)

object AuthorizationSlide : Slide(
    header = "Authorization in Service Layer",
    summary = {
        +"The Service Layer enforces access control and permissions, ensuring users can only perform actions they're authorized for."
    },
    content = {
        twoColumns(
            ratio = 2 to 3,
            left = {
                p {
                    +"Authorization responsibilities in the service layer:"
                }
                ul {
                    li {
                        strong { +"Resource-level authorization" }
                        +" - Check if user can access specific resources (e.g., only view their own tasks)"
                    }
                    li {
                        strong { +"Operation-level authorization" }
                        +" - Check if user can perform specific actions (e.g., only managers can delete tasks)"
                    }
                    li {
                        strong { +"Data filtering" }
                        +" - Return only data the user is authorized to see"
                    }
                    li {
                        strong { +"Business-rule authorization" }
                        +" - Complex permission logic beyond simple roles (e.g., task creator or assigned user can edit)"
                    }
                }
                p {
                    strong { +"Why in the service layer?" }
                }
                ul {
                    li { +"Controllers handle authentication (who you are)" }
                    li { +"Services handle authorization (what you can do)" }
                    li { +"Business rules often require data from the database" }
                    li { +"Authorization logic can be complex and reusable across endpoints" }
                }
            },
            right = {
//                p {
//                    strong { +"Example: Task authorization" }
//                }
                kotlinPlayground(
                    code = """
                @Service
                class TaskService(
                    private val taskRepository: TaskRepository,
                    private val authenticationContext: AuthenticationContext
                ) {
                    fun updateTask(taskId: Long, request: UpdateTaskRequest): Task {
                        val task = taskRepository.findById(taskId)
                            ?: throw TaskNotFoundException(taskId)

                        val currentUser = authenticationContext.getCurrentUser()

                        // Authorization check in service layer
                        if (!canUserModifyTask(currentUser, task)) {
                            throw UnauthorizedException("You are not authorized to modify this task")
                        }

                        // Update task...
                        return task.toDomain()
                    }

                    private fun canUserModifyTask(user: User, task: Task): Boolean {
                        return user.isAdmin() ||
                               user.id == task.createdBy ||
                               user.id == task.assignedTo
                    }
                }
            """.trimIndent(),
                    executable = false
                )
            },
        )
    }
)

object DataValidationSlide : Slide(
    header = "Data Validation in Service Layer",
    summary = {
        +"The Service Layer validates business rules that go beyond basic input validation performed at the controller level."
    },
    content = {
        twoColumns(
            ratio = 2 to 3,
            left = {
                p {
                    +"Service layer validation includes:"
                }
                ul {
                    li {
                        strong { +"Business rule validation" }
                        +" - Complex rules that involve multiple fields or entities"
                    }
                    li {
                        strong { +"Cross-entity validation" }
                        +" - Rules that require checking related data"
                    }
                    li {
                        strong { +"State validation" }
                        +" - Ensuring operations are valid for the current state"
                    }
                    li {
                        strong { +"Uniqueness constraints" }
                        +" - Checking database for existing records"
                    }
                }

                p {
                    strong { +"Controller vs Service validation:" }
                }
                ul {
                    li {
                        strong { +"Controller validation:" }
                        +" Format, required fields, basic constraints (using "; inlineCode("@Valid"); +")"
                    }
                    li {
                        strong { +"Service validation:" }
                        +" Business rules, database constraints, state transitions"
                    }
                }
            },
            right = {
//                p {
//                    strong { +"Example: Task validation" }
//                }
                kotlinPlayground(
                    code = """
                @Service
                class TaskService(
                    private val taskRepository: TaskRepository,
                    private val userRepository: UserRepository
                ) {
                    fun assignTask(taskId: Long, userId: Long): Task {
                        val task = taskRepository.findById(taskId) ?: throw TaskNotFoundException(taskId)

                        // Business rule validation
                        if (task.status == TaskStatus.COMPLETED) throw ValidationException("Cannot reassign a completed task")
                        if (task.status == TaskStatus.ARCHIVED) throw ValidationException("Cannot reassign an archived task")

                        // Cross-entity validation
                        val user = userRepository.findById(userId) ?: throw UserNotFoundException(userId)
                        if (!user.isActive) throw ValidationException("Cannot assign task to inactive user")
                        if (user.currentTaskCount >= user.maxTaskLimit) throw ValidationException("User has reached maximum task limit")

                        task.assignedTo = userId
                        return taskRepository.save(task).toDomain()
                    }
                }
            """.trimIndent(),
                    executable = false
                )
            },
        )
    },
    fontSize = "75%"
)

object ErrorHandlingSlide : Slide(
    header = "Error Handling in Service Layer",
    summary = {
        +"The Service Layer manages exceptions and translates technical errors into meaningful business errors."
    },
    content = {
        twoColumns(
            ratio = 2 to 3,
            left = {
                p {
                    +"Service layer error handling responsibilities:"
                }
                ul {
                    li {
                        strong { +"Exception translation" }
                        +" - Convert technical errors (SQL, network) to business exceptions"
                    }
                    li {
                        strong { +"Meaningful error messages" }
                        +" - Provide context-specific error messages for the user"
                    }
                    li {
                        strong { +"Resource cleanup" }
                        +" - Ensure proper cleanup of resources on errors"
                    }
                    li {
                        strong { +"Logging and monitoring" }
                        +" - Log errors with appropriate context for debugging"
                    }
                }

                p {
                    strong { +"Common exception types:" }
                }
                ul {
                    li { inlineCode("NotFoundException"); +" - Resource not found (404)" }
                    li { inlineCode("ValidationException"); +" - Business rule violation (400)" }
                    li { inlineCode("UnauthorizedException"); +" - Permission denied (403)" }
                    li { inlineCode("ConflictException"); +" - Resource state conflict (409)" }
                }
            },
            right = {
//                p {
//                    strong { +"Example: Service error handling" }
//                }
                kotlinPlayground(
                    code = """
                @Service
                class TaskService(
                    private val taskRepository: TaskRepository,
                    private val logger: Logger
                ) {
                    fun deleteTask(taskId: Long) {
                        try {
                            val task = taskRepository.findById(taskId)
                                ?: throw TaskNotFoundException("Task with ID ${'$'}taskId not found")

                            if (task.status == TaskStatus.IN_PROGRESS) {
                                throw ConflictException(
                                    "Cannot delete task that is currently in progress. " +
                                    "Please mark it as completed or cancelled first."
                                )
                            }

                            taskRepository.deleteById(taskId)
                            logger.info("Task ${'$'}taskId deleted successfully")

                        } catch (ex: DataAccessException) {
                            logger.error("Database error while deleting task ${'$'}taskId", ex)
                            throw ServiceException("Failed to delete task due to a database error")
                        }
                    }
                }

                class TaskNotFoundException(message: String) : RuntimeException(message)
                class ConflictException(message: String) : RuntimeException(message)
                class ServiceException(message: String) : RuntimeException(message)
            """.trimIndent(),
                    executable = false
                )
            },
        )
    },
    fontSize = "70%"
)
