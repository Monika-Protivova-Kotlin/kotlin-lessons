package com.motycka.edu.content.topics.backend.ktor

import com.motycka.edu.model.Slide
import com.motycka.edu.model.inlineCode
import com.motycka.edu.model.kotlinPlayground
import kotlinx.html.p

object ErrorHandlingExampleSlide : Slide(
    header = "Application Error Handling Example",
    content = {
        p {
            +"I have defined a few custom exceptions to handle common error conditions in my application. "
            +"They all extend "
            inlineCode("ApplicationException")
            +" class, because I want all of them to contain a "
            inlineCode("traceId")
            +" and "
            inlineCode("time")
            +" properties."
        }
        kotlinPlayground(
            code = """
                open class ApplicationException(
                    val applicationMessage: String,
                    val traceId: UUID = UUID.randomUUID(),
                    val time: ZonedDateTime = ZonedDateTime.now(),
                ) : RuntimeException("[${DOLLAR}traceId] ${DOLLAR}applicationMessage")

                class UnauthorizedAccessException(message: String) : ApplicationException(message)

                class InvalidCredentialsException(userName: String) : ApplicationException("Login failed: ${DOLLAR}userName")

                class ResourceNotFoundException(message: String) : ApplicationException(message)
            """,
            executable = false
        )
        p {
            +"I have also defined convenience functions to throw these exceptions:"
        }
        kotlinPlayground(
            code = """
                fun unauthorizedAccess(message: String): Nothing {
                    throw UnauthorizedAccessException(message)
                }

                fun menuItemNotFound(id: MenuItemId): Nothing {
                    throw ResourceNotFoundException("Menu item with id: ${DOLLAR}id not found")
                }

                fun customerNotFound(userId: UserId): Nothing {
                    throw ResourceNotFoundException("User ${DOLLAR}userId doesn't have a customer account")
                }
            """,
            executable = false
        )
    }
)
