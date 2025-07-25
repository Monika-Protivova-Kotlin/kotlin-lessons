package com.motycka.edu.content.topics.backend.ktor

import com.motycka.edu.model.Slide
import com.motycka.edu.model.inlineCode
import com.motycka.edu.model.kotlinPlayground
import kotlinx.html.p

object ErrorResponseFormatSlide : Slide(
    header = "Consistent Error Response Format",
    content = {
        p {
            +"Because I want application errors to be reported consistently and with enough information to trace the error "
            +"in application logs, I have defined a common serializable "
            inlineCode("ErrorResponse")
            +" data class, and an extension function to convert my custom exceptions to this response object."
        }
        kotlinPlayground(
            code = """
                @Serializable
                data class ErrorResponse(
                    @Contextual
                    val traceId: UUID,
                    val message: String,
                    @Contextual
                    val time: ZonedDateTime,
                )

                fun ApplicationException.toResponse() = ErrorResponse(
                    traceId = traceId,
                    message = applicationMessage,
                    time = time
                )
            """,
            executable = false
        )
        p {
            +"In my services, whenever I recognize an error condition, I throw one of the custom exceptions "
            +"and the "
            inlineCode("StatusPages")
            +" plugin will handle it globally:"
        }
        kotlinPlayground(
            code = """
                class MenuService(
                    private val menuRepository: MenuRepository
                ) {

                    suspend fun getMenuItem(id: MenuItemId): MenuItemResponse {
                        logger.info { "Getting menu item with id: ${DOLLAR}id" }
                        return menuRepository.selectMenuItemById(id)?.toResponse() ?: menuItemNotFound(id)
                    }

                    suspend fun createMenuItem(identity: IdentityDTO, request: MenuItemRequest): MenuItemResponse {
                        logger.info { "Creating menu item: ${DOLLAR}{request.name} by user: ${DOLLAR}{identity.userId}" }
                        return when (identity.role) {
                            UserRole.STAFF -> menuRepository.insertMenuItem(request.toDTO()).toResponse()
                            UserRole.CUSTOMER -> unauthorizedAccess("Only admin users can create menu items")
                        }
                    }
                }
            """,
            executable = false
        )
    }
)
