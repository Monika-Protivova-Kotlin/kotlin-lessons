package com.motycka.edu.content.topics.backend

import com.motycka.edu.model.Topic
import com.motycka.edu.model.Slide
import com.motycka.edu.model.kotlinPlayground
import com.motycka.edu.model.highlight
import kotlinx.html.*
import com.motycka.edu.model.highlight

object BasicAuthTopic : Topic(
    title = "Basic Authentication",
    slides = listOf(
        BasicAuthIntroSlide,
        BasicAuthMechanismSlide,
        BasicAuthImplementationSlide,
        BasicAuthSecuritySlide
    )
)

object BasicAuthIntroSlide : Slide(
    header = "Basic Authentication",
    summary = {
        +"Basic Authentication is a simple authentication scheme built into the HTTP protocol. "
        +"It uses base64-encoded username and password credentials."
    },
    content = {
        p {
            +"Basic Authentication is defined in RFC 7617 and is one of the simplest authentication mechanisms available."
        }
        p {
            +"Key characteristics:"
        }
        ul {
            li { +"Built into HTTP protocol" }
            li { +"Simple to implement and understand" }
            li { +"Widely supported by browsers and HTTP clients" }
            li { +"Credentials sent with every request" }
            li { +"No additional handshake or setup required" }
        }
        p {
            +"The credentials are encoded using Base64, which provides encoding but "
            strong { +"NOT encryption" }
            +" - the credentials can be easily decoded."
        }
    }
)

object BasicAuthMechanismSlide : Slide(
    header = "How Basic Auth Works",
    summary = {
        +"Basic Authentication follows a simple challenge-response mechanism between client and server."
    },
    content = {
        p {
            +"Authentication flow:"
        }
        ol {
            li {
                +"Client makes a request to a protected resource"
            }
            li {
                +"Server responds with "
                code { +"401 Unauthorized" }
                +" and includes "
                code { +"WWW-Authenticate: Basic realm=\"Protected Area\"" }
                +" header"
            }
            li {
                +"Client prompts user for credentials (or uses stored ones)"
            }
            li {
                +"Client sends request with "
                code { +"Authorization: Basic <encoded-credentials>" }
                +" header"
            }
            li {
                +"Server decodes and validates credentials"
            }
            li {
                +"Server responds with requested resource or "
                code { +"403 Forbidden" }
            }
        }
        p {
            +"The encoded credentials format is: "
            code { +"base64(username:password)" }
        }
    }
)

object BasicAuthImplementationSlide : Slide(
    header = "Basic Auth Implementation",
    summary = {
        +"Example of how Basic Authentication headers are constructed and processed."
    },
    content = {
        p {
            +"Creating the Authorization header:"
        }
        kotlinPlayground(
            code = """
                val username = "john.doe"
                val password = "secretpassword"

                // Combine username and password
                val credentials = "${DOLLAR}username:${DOLLAR}password"

                // Encode using Base64
                val encodedCredentials = Base64.getEncoder().encodeToString(credentials.toByteArray())

                // Create authorization header
                val authHeader = "Basic ${DOLLAR}encodedCredentials"
                // Result: "Basic am9obi5kb2U6c2VjcmV0cGFzc3dvcmQ="
            """.trimIndent(),
            executable = false
        )
        p {
            +"Processing the Authorization header:"
        }
        kotlinPlayground(
            code = """
                fun extractCredentials(authHeader: String): Pair<String, String>? {
                    if (!authHeader.startsWith("Basic ")) return null

                    val encoded = authHeader.removePrefix("Basic ")
                    val decoded = String(Base64.getDecoder().decode(encoded))

                    val parts = decoded.split(":", limit = 2)
                    return if (parts.size == 2) {
                        Pair(parts[0], parts[1])
                    } else null
                }
            """.trimIndent(),
            executable = false
        )
    }
)

object BasicAuthSecuritySlide : Slide(
    header = "Basic Auth Security Considerations",
    summary = {
        +"While simple to implement, Basic Authentication has several security limitations that must be addressed."
    },
    content = {
        p {
            highlight("Security vulnerabilities:")
        }
        ul {
            li {
                strong { +"No Encryption" }
                +" - Base64 encoding is easily reversible"
            }
            li {
                strong { +"Credentials in Every Request" }
                +" - Increases exposure window"
            }
            li {
                strong { +"No Token Expiration" }
                +" - Credentials remain valid until changed"
            }
            li {
                strong { +"Browser Storage" }
                +" - Browsers may cache credentials"
            }
            li {
                strong { +"Logout Limitations" }
                +" - Difficult to implement proper logout"
            }
        }
        p {
            highlight("Security best practices:")
        }
        ul {
            li {
                strong { +"HTTPS Only" }
                +" - Always use HTTPS to encrypt transmission"
            }
            li {
                strong { +"Strong Passwords" }
                +" - Enforce strong password policies"
            }
            li {
                strong { +"Rate Limiting" }
                +" - Implement request rate limiting"
            }
            li {
                strong { +"Account Lockout" }
                +" - Lock accounts after failed attempts"
            }
            li {
                strong { +"Monitor and Log" }
                +" - Track authentication attempts"
            }
        }
        p {
            em { +"Basic Auth is suitable for simple use cases but consider more secure alternatives for production applications." }
        }
    }
)
