package com.motycka.edu.content.topics.backend

import com.motycka.edu.model.Topic
import com.motycka.edu.model.Slide
import com.motycka.edu.model.kotlinPlayground
import com.motycka.edu.model.highlight
import com.motycka.edu.model.inlineCode
import kotlinx.html.*
import com.motycka.edu.model.highlight

object SpringBootExceptionHandlingTopic : Topic(
    title = "Exception Handling in Spring Boot",
    slides = listOf(
        SpringApplicationErrorsIntroSlide,
        SpringCustomExceptionsSlide,
        SpringControllerAdviceSlide,
        SpringExceptionHandlerSlide,
        SpringErrorHandlingCompleteExampleSlide,
        SpringErrorResponseFormatSlide
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
                +"Example: non-empty name, valid character type, level within range, etc."
            }
            li {
                strong { highlight("State validation") }
                br()
                +"For example, we may want to validate the state of the application, such as whether a user is authenticated, "
                +"or whether a character exists before starting a battle."
            }
            li {
                strong { highlight("Business logic validation") }
                br()
                +"For example, we may want to validate the business logic, such as whether a character has enough health to fight, "
                +"or whether a user has permission to delete another player's character."
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
        p {
            +"Defining custom exceptions allows you to provide more meaningful error messages and handle specific error conditions in a more granular way. "
            +"It also allows you to treat the same class of errors consistently across your application."
        }
        p { +"Here are a few examples of custom exceptions for Fantasy.Space:" }
        ul {
            li {
                inlineCode("CharacterNotFoundException")
                br()
                +"Thrown when a requested character is not found."
            }
            li {
                inlineCode("BattleNotFoundException")
                br()
                +"Thrown when a requested battle is not found."
            }
            li {
                inlineCode("InvalidCharacterDataException")
                br()
                +"Thrown when the input provided for creating/updating a character is invalid."
            }
            li {
                inlineCode("CharacterAlreadyInBattleException")
                br()
                +"Thrown when trying to start a battle with a character that's already fighting."
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
        kotlinPlayground(
            code = """
                // Base exception for all Fantasy.Space application exceptions
                open class ApplicationException(
                    val applicationMessage: String,
                    val traceId: UUID = UUID.randomUUID(),
                    val timestamp: LocalDateTime = LocalDateTime.now()
                ) : RuntimeException("[${DOLLAR}traceId] ${DOLLAR}applicationMessage")

                // Specific exception types
                class CharacterNotFoundException(characterId: Long) :
                    ApplicationException("Character with id ${DOLLAR}characterId not found")

                class BattleNotFoundException(battleId: Long) :
                    ApplicationException("Battle with id ${DOLLAR}battleId not found")

                class InvalidCharacterDataException(message: String) :
                    ApplicationException(message)

                class CharacterAlreadyInBattleException(characterId: Long) :
                    ApplicationException("Character ${DOLLAR}characterId is already in an active battle")

                class UnauthorizedAccessException(message: String) :
                    ApplicationException(message)
            """.trimIndent(),
            executable = false
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
        kotlinPlayground(
            code = """
                @ControllerAdvice
                class GlobalExceptionHandler {

                    @ExceptionHandler(CharacterNotFoundException::class)
                    fun handleCharacterNotFound(ex: CharacterNotFoundException): ResponseEntity<ErrorResponse> {
                        val errorResponse = ErrorResponse(
                            traceId = ex.traceId,
                            message = ex.applicationMessage,
                            timestamp = ex.timestamp
                        )
                        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse)
                    }

                    @ExceptionHandler(InvalidCharacterDataException::class)
                    fun handleInvalidCharacterData(ex: InvalidCharacterDataException): ResponseEntity<ErrorResponse> {
                        val errorResponse = ErrorResponse(
                            traceId = ex.traceId,
                            message = ex.applicationMessage,
                            timestamp = ex.timestamp
                        )
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse)
                    }

                    @ExceptionHandler(UnauthorizedAccessException::class)
                    fun handleUnauthorizedAccess(ex: UnauthorizedAccessException): ResponseEntity<ErrorResponse> {
                        val errorResponse = ErrorResponse(
                            traceId = ex.traceId,
                            message = ex.applicationMessage,
                            timestamp = ex.timestamp
                        )
                        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorResponse)
                    }

                    // Catch-all handler for unexpected exceptions
                    @ExceptionHandler(Exception::class)
                    fun handleGenericException(ex: Exception): ResponseEntity<ErrorResponse> {
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
                fun characterNotFound(id: Long): Nothing {
                    throw CharacterNotFoundException(id)
                }

                fun battleNotFound(id: Long): Nothing {
                    throw BattleNotFoundException(id)
                }

                fun invalidCharacterData(message: String): Nothing {
                    throw InvalidCharacterDataException(message)
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
                class CharacterService(
                    private val characterRepository: CharacterRepository
                ) {

                    fun getCharacterById(id: Long): CharacterDTO {
                        val character = characterRepository.findById(id)
                            ?: characterNotFound(id)  // Throws exception if not found
                        return character.toDTO()
                    }

                    fun createCharacter(request: CreateCharacterRequest, userId: Long): CharacterDTO {
                        // Validate input
                        if (request.name.isBlank()) {
                            invalidCharacterData("Character name cannot be empty")
                        }
                        if (request.level < 1 || request.level > 100) {
                            invalidCharacterData("Character level must be between 1 and 100")
                        }

                        val character = Character(
                            name = request.name,
                            type = request.type,
                            level = request.level,
                            ownerId = userId
                        )
                        return characterRepository.save(character).toDTO()
                    }

                    fun deleteCharacter(characterId: Long, userId: Long) {
                        val character = characterRepository.findById(characterId)
                            ?: characterNotFound(characterId)

                        // Check authorization
                        if (character.ownerId != userId) {
                            unauthorizedAccess("You can only delete your own characters")
                        }

                        characterRepository.delete(character)
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
                @RequestMapping("/api/characters")
                class CharacterController(
                    private val characterService: CharacterService
                ) {

                    @GetMapping("/{id}")
                    fun getCharacter(@PathVariable id: Long): CharacterDTO {
                        // If character not found, exception is thrown and caught by @ControllerAdvice
                        return characterService.getCharacterById(id)
                    }

                    @PostMapping
                    @ResponseStatus(HttpStatus.CREATED)
                    fun createCharacter(
                        @RequestBody request: CreateCharacterRequest,
                        @AuthenticationPrincipal user: User
                    ): CharacterDTO {
                        // If validation fails, exception is thrown and caught by @ControllerAdvice
                        return characterService.createCharacter(request, user.id)
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
                            is CharacterNotFoundException, is BattleNotFoundException -> HttpStatus.NOT_FOUND
                            is InvalidCharacterDataException -> HttpStatus.BAD_REQUEST
                            is UnauthorizedAccessException -> HttpStatus.FORBIDDEN
                            is CharacterAlreadyInBattleException -> HttpStatus.CONFLICT
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
        p {
            +"This provides consistent error handling across your entire API with proper HTTP status codes "
            +"and traceable error messages."
        }
    }
)
