package com.motycka.edu.content.topics.backend

import com.motycka.edu.model.Topic
import com.motycka.edu.model.Slide
import com.motycka.edu.model.twoColumns
import com.motycka.edu.model.highlight
import kotlinx.html.*
import com.motycka.edu.model.highlight

object JWTTopic : Topic(
    title = "JWT (JSON Web Tokens)",
    slides = listOf(
        JWTIntroSlide,
        JWTStructureSlide,
        JWTClaimsSlide,
        JWTFlowSlide,
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
            +"Key characteristics of JWT:"
        }
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
        p {
            +"Common use cases:"
        }
        ul {
            li { +"Authentication and authorization" }
            li { +"Information exchange between services" }
            li { +"Single Sign-On (SSO) implementations" }
            li { +"API access control" }
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
            code { +"xxxxx.yyyyy.zzzzz" }
        }
        twoColumns(
            left = {
                p {
                    strong { +"Header" }
                }
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
            },
            right = {
                p {
                    strong { +"Payload" }
                }
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
        )
        p {
            strong { +"Signature" }
        }
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
                code { +"iss" }
                +" (Issuer) - Who issued the token"
            }
            li {
                code { +"sub" }
                +" (Subject) - Who the token is about"
            }
            li {
                code { +"aud" }
                +" (Audience) - Who the token is intended for"
            }
            li {
                code { +"exp" }
                +" (Expiration Time) - When the token expires"
            }
            li {
                code { +"nbf" }
                +" (Not Before) - When the token becomes valid"
            }
            li {
                code { +"iat" }
                +" (Issued At) - When the token was issued"
            }
            li {
                code { +"jti" }
                +" (JWT ID) - Unique identifier for the token"
            }
        }
        p {
            highlight("Public Claims")
            +" - Claims defined in the JWT registry or as URIs"
        }
        p {
            highlight("Private Claims")
            +" - Custom claims for sharing information between parties"
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
                      "roles": ["admin", "user"],
                      "email": "user@example.com"
                    }
                """.trimIndent()
            }
        }
    }
)

object JWTFlowSlide : Slide(
    header = "JWT Authentication Flow",
    summary = {
        +"Typical flow of JWT-based authentication in a web application."
    },
    content = {
        p {
            +"JWT authentication process:"
        }
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
        pre {
            code {
                classes = setOf("hljs", "http")
                +"""
                    // Including JWT in requests
                    Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...
                """.trimIndent()
            }
        }
    }
)

object JWTSecuritySlide : Slide(
    header = "JWT Security Considerations",
    summary = {
        +"While JWTs provide many benefits, there are important security considerations to keep in mind."
    },
    content = {
        p {
            highlight("Security Best Practices:")
        }
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
        p {
            highlight("Common Vulnerabilities:")
        }
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
        p {
            em { +"Remember: JWTs are signed, not encrypted by default. Sensitive data should be encrypted separately." }
        }
    }
)
