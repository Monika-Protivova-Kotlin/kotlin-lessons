package com.motycka.edu.content.topics.backend

import com.motycka.edu.model.Topic
import com.motycka.edu.model.Slide
import com.motycka.edu.model.contentCard
import com.motycka.edu.model.kotlinPlayground
import com.motycka.edu.model.highlight
import kotlinx.html.*
import com.motycka.edu.model.highlight
import com.motycka.edu.model.inlineCode
import com.motycka.edu.model.twoColumns

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
        h4 {
            +"Key characteristics"
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
            highlight("NOT encryption")
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
            h4 { +"Authentication flow" }
            ol {
                li {
                    +"Client makes a request to a protected resource"
                }
                li {
                    +"Server responds with "
                    inlineCode("401 Unauthorized")
                    +" and includes "
                    inlineCode("WWW-Authenticate: Basic realm=\"Protected Area\"")
                    +" header"
                }
                li {
                    +"Client prompts user for credentials (or uses stored ones)"
                }
                li {
                    +"Client sends request with "
                    inlineCode("Authorization: Basic <encoded-credentials>")
                    +" header"
                }
                li {
                    +"Server decodes and validates credentials"
                }
                li {
                    +"Server responds with requested resource or "
                    inlineCode("403 Forbidden")
                }
            }
        }
        p {
            +"The encoded credentials format is: "
            inlineCode("base64(username:password)")
        }
    }
)

object BasicAuthImplementationSlide : Slide(
    header = "Basic Auth Implementation",
//    summary = {
//        +"Example of how Basic Authentication headers are constructed and processed."
//    },
    content = {
        p {
            h4 {
                +"Creating the Authorization header"
            }
            ol {
                li {
                    +"Combine username and password with a colon:"
                    br
                    inlineCode("val credentials = \"${DOLLAR}username:${DOLLAR}password\"")
                }
                li {
                    +"Encode the combined string using Base64:"
                    br
                    inlineCode("val encodedCredentials = Base64.getEncoder().encodeToString(credentials.toByteArray())")
                }
                li {
                    +"Create the Authorization header value:"
                    br
                    inlineCode("val authHeader = \"Basic ${DOLLAR}encodedCredentials\"")
                }
                li {
                    +"The resulting Authorization header will look like this:"
                    br
                    inlineCode("Basic am9obi5kb2U6c2VjcmV0cGFzc3dvcmQ=")
                }
            }
        }
        p {
            h4 {
                +"Processing the Authorization header"
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
    }
)

object BasicAuthSecuritySlide : Slide(
    header = "Basic Auth Security Considerations",
    summary = {
        +"While simple to implement, Basic Authentication has several security limitations that must be addressed."
    },
    content = {
        twoColumns(
            ratio = 3 to 2,
            left = {
                p {
                    h4 { +"Security vulnerabilities" }
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
                }
                p {
                    h4 { +"Security best practices" }
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
                }
            },
            right = {
                contentCard {
                    +"Basic Auth is suitable for simple use cases but consider more secure alternatives for production applications."
                }
            },
        )
    }
)
