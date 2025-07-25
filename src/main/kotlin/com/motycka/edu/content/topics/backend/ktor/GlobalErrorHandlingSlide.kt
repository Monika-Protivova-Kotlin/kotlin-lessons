package com.motycka.edu.content.topics.backend.ktor

import com.motycka.edu.model.Slide
import com.motycka.edu.model.highlight
import com.motycka.edu.model.kotlinPlayground
import kotlinx.html.p
import kotlinx.html.strong

object GlobalErrorHandlingSlide : Slide(
    header = "Global Error Handling",
    summary = {
        +"Handling exceptions individually is possible but impractical. "
        +"Application frameworks usually provide a way to handle exceptions globally."
    },
    content = {
        p {
            +"It is also a good idea to provide error responses in a standard format with a meaningful error message and maybe some additional information to help trace the error."
        }
        p {
            +"At the same time, it also a good idea to log all error messages in the application logs."
        }
        p {
            +"This can be later used to tie client errors to server logs."
        }
        p {
            strong { highlight("Ktor") }
            +" allows us to use a "
            strong { highlight("StatusPages") }
            +" plugin to handle exceptions globally."
        }
        kotlinPlayground(
            code = """
                install(StatusPages) {

                    exception<BadRequestException> { call, cause ->
                        call.respond(HttpStatusCode.BadRequest, mapOf("error" to cause.message))
                    }

                    exception<NotFoundException> { call, cause ->
                        call.respond(HttpStatusCode.NotFound, mapOf("error" to cause.message))
                    }

                    exception<Throwable> { call, cause ->
                        call.respond(HttpStatusCode.InternalServerError, mapOf("error" to "Something went wrong"))
                    }

                }
            """,
            executable = false
        )
    }
)
