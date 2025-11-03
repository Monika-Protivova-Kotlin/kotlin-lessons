package com.motycka.edu.content.topics.backend

import com.motycka.edu.model.Topic
import com.motycka.edu.model.Slide
import com.motycka.edu.model.contentCard
import com.motycka.edu.model.twoColumns
import com.motycka.edu.model.highlight
import kotlinx.html.*
import com.motycka.edu.model.highlight
import com.motycka.edu.model.inlineCode

object JWTTopic : Topic(
    title = "JWT (JSON Web Tokens)",
    slides = listOf(
        JWTIntroSlide,
        JWTStructureSlide,
        JWTClaimsSlide,
        JWTPlaygroundSlide,
        JWTFlowSlide,
        JWTAuthorizationSlide,
        JWTSecuritySlide
    )
)

object JWTIntroSlide : Slide(
    header = "JWT (JSON Web Tokens)",
    summary = {
        +"JSON Web Tokens (JWT) are a compact, URL-safe means of representing claims to be transferred between two parties."
    },
    content = {
        p {
            +"JWT is defined in RFC 7519 and is widely used for authentication and information exchange in modern web applications."
        }
        p {
            h4 { +"Key characteristics of JWT" }
            ul {
                li {
                    strong { +"Self-contained" }
                    +" - Contains all necessary information within the token"
                }
                li {
                    strong { +"Stateless" }
                    +" - Server doesn't need to store session information"
                }
                li {
                    strong { +"Compact" }
                    +" - Small size suitable for HTTP headers and URLs"
                }
                li {
                    strong { +"Secure" }
                    +" - Can be signed and optionally encrypted"
                }
                li {
                    strong { +"Cross-platform" }
                    +" - Language and platform independent"
                }
            }
        }
        p {
            h4 { +"Common use cases" }
            ul {
                li { +"Authentication and authorization" }
                li { +"Information exchange between services" }
                li { +"Single Sign-On (SSO) implementations" }
                li { +"API access control" }
            }
        }
    }
)

object JWTStructureSlide : Slide(
    header = "JWT Structure",
    summary = {
        +"A JWT consists of three parts separated by dots: Header.Payload.Signature"
    },
    content = {
        p {
            +"JWT structure: "
            inlineCode("xxxxx.yyyyy.zzzzz")
        }
        twoColumns(
            left = {
                p {
                    h4 { +"Header" }
                    ul {
                        li { +"Contains metadata about the token" }
                        li { +"Specifies the signing algorithm" }
                        li { +"Encoded in Base64URL" }
                    }
                    pre {
                        code {
                            classes = setOf("hljs", "json")
                            +"""
                            {
                              "alg": "HS256",
                              "typ": "JWT"
                            }
                        """.trimIndent()
                        }
                    }
                }
            },
            right = {
                p {
                    h4 { +"Payload" }
                    ul {
                        li { +"Contains the claims (user data)" }
                        li { +"Standard and custom claims" }
                        li { +"Encoded in Base64URL" }
                    }
                    pre {
                        code {
                            classes = setOf("hljs", "json")
                            +"""
                            {
                              "sub": "1234567890",
                              "name": "John Doe",
                              "iat": 1516239022
                            }
                        """.trimIndent()
                        }
                    }
                }
            }
        )
        p {
            h4 { +"Signature" }
            ul {
                li { +"Verifies the token hasn't been tampered with" }
                li { +"Created using header + payload + secret" }
                li { +"Algorithm specified in header" }
            }
            pre {
                code {
                    classes = setOf("hljs", "javascript")
                    +"""
                    HMACSHA256(
                      base64UrlEncode(header) + "." +
                      base64UrlEncode(payload),
                      secret
                    )
                """.trimIndent()
                }
            }
        }
    }
)

object JWTClaimsSlide : Slide(
    header = "JWT Claims",
    summary = {
        +"Claims are statements about an entity (typically the user) and additional data. "
        +"There are three types of claims: registered, public, and private."
    },
    content = {
        p {
            highlight("Registered Claims")
            +" - Standard claims with predefined meanings:"
        }
        ul {
            li {
                inlineCode("iss")
                +" (Issuer) - Who issued the token"
            }
            li {
                inlineCode("sub")
                +" (Subject) - Who the token is about"
            }
            li {
                inlineCode("aud")
                +" (Audience) - Who the token is intended for"
            }
            li {
                inlineCode("exp")
                +" (Expiration Time) - When the token expires"
            }
            li {
                inlineCode("nbf")
                +" (Not Before) - When the token becomes valid"
            }
            li {
                inlineCode("iat")
                +" (Issued At) - When the token was issued"
            }
            li {
                inlineCode("jti")
                +" (JWT ID) - Unique identifier for the token"
            }
        }
        p {
            highlight("Private Claims")
            +" - Custom claims for sharing information between parties, including authorization data:"
        }
        pre {
            code {
                classes = setOf("hljs", "json")
                +"""
                    {
                      "iss": "https://auth.example.com",
                      "sub": "user123",
                      "aud": "https://api.example.com",
                      "exp": 1516243022,
                      "iat": 1516239422,
                      "email": "user@example.com",
                      "roles": ["admin", "user"],
                      "permissions": ["read:users", "write:posts"],
                      "department": "engineering",
                      "organization": "acme-corp"
                    }
                """.trimIndent()
            }
        }
        p {
            +"Common authorization claims: "
            inlineCode("roles")
            +", "
            inlineCode("permissions")
            +", "
            inlineCode("department")
            +", "
            inlineCode("organization")
            +", "
            inlineCode("level")
        }
    }
)

object JWTPlaygroundSlide : Slide(
    header = "JWT Playground - Try It Yourself!",
    summary = {
        +"Use "
        a(href = "https://jwt.io/") { +"jwt.io" }
        +" to decode, verify, and generate JWTs interactively."
    },
    content = {
        twoColumns(
            left = {
                p {
                    strong { +"What is JWT.io?" }
                }
                p {
                    +"An online tool to decode and verify JSON Web Tokens."
                }
                p {
                    strong { +"What you can do:" }
                }
                ul {
                    li { +"Decode existing JWTs to see header and payload" }
                    li { +"Verify JWT signatures with your secret" }
                    li { +"Generate new JWTs with custom claims" }
                    li { +"Test different signing algorithms" }
                    li { +"Learn JWT structure interactively" }
                }
                p {
                    strong { +"Try it now:" }
                }
                p {
                    +"Visit "
                    a(href = "https://jwt.io/") {
                        target = "_blank"
                        +"https://jwt.io/"
                    }
                    +" and paste the example JWT from the right to see it decoded!"
                }
            },
            right = {
                p {
                    strong { +"Example JWT Token:" }
                }
                p {
                    +"Copy this token and paste it into jwt.io to see the decoded header and payload:"
                }
                pre {
                    code {
                        style = "font-size: 0.5em; word-wrap: break-word; white-space: pre-wrap;"
                        +"""
                            eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJ1c2VyMTIzIiwibmFtZSI6IkpvaG4gRG9lIiwiZW1haWwiOiJqb2huLmRvZUBleGFtcGxlLmNvbSIsInJvbGVzIjpbInVzZXIiLCJhZG1pbiJdLCJpYXQiOjE3MzY1NTk2MDAsImV4cCI6MTczNjU2MzIwMH0.Kl_VdXsqGqBT8JIqvvKj5R8XFqPqHZqvBqzWx9YqNhQ
                        """.trimIndent()
                    }
                }
                p {
                    strong { +"Decodes to:" }
                }
                pre {
                    code {
                        classes = setOf("hljs", "json")
                        style = "font-size: 0.6em;"
                        +"""
                            // Header
                            {
                              "alg": "HS256",
                              "typ": "JWT"
                            }

                            // Payload
                            {
                              "sub": "user123",
                              "name": "John Doe",
                              "email": "john.doe@example.com",
                              "roles": ["user", "admin"],
                              "iat": 1736559600,
                              "exp": 1736563200
                            }
                        """.trimIndent()
                    }
                }
            }
        )
    },
    fontSize = "70%"
)

object JWTFlowSlide : Slide(
    header = "JWT Authentication Flow",
    summary = {
        +"Typical flow of JWT-based authentication in a web application."
    },
    content = {
        p {
            h4 { +"JWT authentication process" }
            ol {
                li {
                    strong { +"User Login" }
                    +" - User submits credentials to authentication endpoint"
                }
                li {
                    strong { +"Credential Validation" }
                    +" - Server validates username and password"
                }
                li {
                    strong { +"Token Generation" }
                    +" - Server creates JWT with user information and claims"
                }
                li {
                    strong { +"Token Response" }
                    +" - Server returns JWT to client (usually in response body)"
                }
                li {
                    strong { +"Token Storage" }
                    +" - Client stores JWT (localStorage, sessionStorage, or httpOnly cookie)"
                }
                li {
                    strong { +"Authenticated Requests" }
                    +" - Client includes JWT in Authorization header for subsequent requests"
                }
                li {
                    strong { +"Token Validation" }
                    +" - Server validates JWT signature and claims for each request"
                }
                li {
                    strong { +"Token Expiration" }
                    +" - Token expires and user needs to re-authenticate or refresh token"
                }
            }
//            pre {
//                code {
//                    classes = setOf("hljs", "http")
//                    +"""
//                    // Including JWT in requests
//                    Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...
//                """.trimIndent()
//                }
//            }
        }
    }
)

object JWTAuthorizationSlide : Slide(
    header = "Authorization with JWT",
    summary = {
        +"JWT tokens can carry authorization information, making them ideal for stateless authorization decisions."
    },
    content = {
//        p {
//            +"Beyond authentication, JWT is commonly used for "
//            highlight("authorization")
//            +" - determining what actions an authenticated user can perform."
//        }
        twoColumns(
            ratio = 3 to 2,
            left = {
                p {
                    h4 { +"JWT-based authorization process" }
                    ol {
                        li { +"User authenticates and receives JWT with authorization claims (roles, permissions)" }
                        li { +"For each request, server extracts and validates JWT" }
                        li { +"Server checks claims against required permissions for the requested action" }
                        li { +"Access granted or denied based on authorization rules" }
                        li { +"Authorization logic typically implemented in the service layer" }
                    }
                }
                p {
                    h4 { +"Benefits of JWT authorization:" }
                    ul {
                        li {
                            strong { +"Stateless" }
                            +" - No server-side session storage needed for permissions"
                        }
                        li {
                            strong { +"Self-contained" }
                            +" - All authorization information embedded in token"
                        }
                        li {
                            strong { +"Scalable" }
                            +" - Works across distributed systems without shared state"
                        }
                        li {
                            strong { +"Fast" }
                            +" - No database lookups needed for basic authorization checks"
                        }
                    }
                }
            },
            right = {
                contentCard {
                    em { +"For highly dynamic permissions, consider hybrid approaches where JWT contains basic roles while detailed permissions are fetched as needed." }
                }
            },
        )
    }
)

object JWTSecuritySlide : Slide(
    header = "JWT Security Considerations",
    summary = {
        +"While JWTs provide many benefits, there are important security considerations to keep in mind."
    },
    content = {
        twoColumns(
            ratio = 3 to 2,
            left = {
                p {
                    h4 { +"Security Best Practices" }
                    ul {
                        li {
                            strong { +"Use Strong Secrets" }
                            +" - Use cryptographically strong signing keys"
                        }
                        li {
                            strong { +"Short Expiration Times" }
                            +" - Keep token lifetimes short (15-30 minutes)"
                        }
                        li {
                            strong { +"HTTPS Only" }
                            +" - Always transmit tokens over secure connections"
                        }
                        li {
                            strong { +"Validate All Claims" }
                            +" - Always validate issuer, audience, and expiration"
                        }
                        li {
                            strong { +"Implement Token Refresh" }
                            +" - Use refresh tokens for longer sessions"
                        }
                        li {
                            strong { +"Secure Storage" }
                            +" - Store tokens securely on the client side"
                        }
                    }
                }
                p {
                    h4 { +"Common Vulnerabilities:" }
                    ul {
                        li {
                            strong { +"None Algorithm Attack" }
                            +" - Always verify the algorithm"
                        }
                        li {
                            strong { +"Weak Signing Keys" }
                            +" - Use sufficiently long random keys"
                        }
                        li {
                            strong { +"Token Leakage" }
                            +" - Protect against XSS and injection attacks"
                        }
                        li {
                            strong { +"Replay Attacks" }
                            +" - Implement proper expiration and nonce handling"
                        }
                    }
                }
            },
            right = {
                contentCard {
                    h4 { +"Remeber!" }
                    p {
                        +"JWTs are signed, not encrypted by default. Sensitive data should be encrypted separately."
                    }
                }
            },
        )
    }
)
