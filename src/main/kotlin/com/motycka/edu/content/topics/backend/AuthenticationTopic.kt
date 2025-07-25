package com.motycka.edu.content.topics.backend

import com.motycka.edu.model.Topic
import com.motycka.edu.model.Slide
import com.motycka.edu.model.highlight
import kotlinx.html.*
import com.motycka.edu.model.highlight

object AuthenticationTopic : Topic(
    title = "Authentication",
    slides = listOf(
        AuthenticationIntroSlide,
        AuthenticationMethodsSlide,
        UsernamePasswordAuthSlide,
        TokenBasedAuthSlide,
        OAuthSlide
    )
)

object AuthenticationIntroSlide : Slide(
    header = "Authentication",
    summary = {
        +"Authentication is the process of verifying the identity of a user, device, or system. "
        +"It answers the question: \"Who are you?\""
    },
    content = {
        p {
            +"Authentication is a fundamental security concept that ensures only legitimate users can access your system."
        }
        p {
            +"Key aspects of authentication:"
        }
        ul {
            li {
                strong { +"Identity Verification" }
                +" - Confirming that users are who they claim to be"
            }
            li {
                strong { +"Credential Validation" }
                +" - Checking provided credentials against stored data"
            }
            li {
                strong { +"Session Management" }
                +" - Maintaining authenticated state across requests"
            }
            li {
                strong { +"Security Measures" }
                +" - Protecting against common attack vectors"
            }
        }
        p {
            +"Authentication is different from "
            highlight("Authorization")
            +" - Authentication verifies identity, while Authorization determines what an authenticated user can do."
        }
    }
)

object AuthenticationMethodsSlide : Slide(
    header = "Authentication Methods",
    summary = {
        +"There are several common methods for implementing authentication, each with its own benefits and use cases."
    },
    content = {
        p {
            +"Common authentication methods include:"
        }
        ul {
            li {
                strong { +"Username/Password" }
                +" - Traditional credential-based authentication"
                ul {
                    li { +"Simple to implement and understand" }
                    li { +"Requires secure password storage (hashing)" }
                    li { +"Vulnerable to password-based attacks" }
                }
            }
            li {
                strong { +"Token-based Authentication" }
                +" - Using tokens to represent authenticated sessions"
                ul {
                    li { +"More secure than session-based auth" }
                    li { +"Stateless and scalable" }
                    li { +"Common implementations: JWT, API keys" }
                }
            }
            li {
                strong { +"OAuth/OAuth2" }
                +" - Delegated authorization framework"
                ul {
                    li { +"Allows third-party authentication" }
                    li { +"Used by social login providers" }
                    li { +"Complex but powerful" }
                }
            }
        }
    }
)

object UsernamePasswordAuthSlide : Slide(
    header = "Username/Password Authentication",
    summary = {
        +"The most common form of authentication where users provide a username and password combination."
    },
    content = {
        p {
            +"Implementation considerations:"
        }
        ul {
            li {
                strong { +"Password Hashing" }
                +" - Never store passwords in plain text"
                ul {
                    li { +"Use strong hashing algorithms (bcrypt, Argon2)" }
                    li { +"Include salt to prevent rainbow table attacks" }
                    li { +"Use appropriate work factors for computational cost" }
                }
            }
            li {
                strong { +"Password Policies" }
                +" - Enforce strong password requirements"
                ul {
                    li { +"Minimum length and complexity" }
                    li { +"Regular password rotation" }
                    li { +"Prevent password reuse" }
                }
            }
            li {
                strong { +"Security Measures" }
                ul {
                    li { +"Rate limiting to prevent brute force attacks" }
                    li { +"Account lockout after failed attempts" }
                    li { +"Secure transmission (HTTPS)" }
                    li { +"Multi-factor authentication (MFA)" }
                }
            }
        }
    }
)

object TokenBasedAuthSlide : Slide(
    header = "Token-based Authentication",
    summary = {
        +"Token-based authentication uses tokens to represent authenticated sessions, providing a stateless and scalable approach."
    },
    content = {
        p {
            +"How token-based authentication works:"
        }
        ol {
            li { +"User provides credentials (username/password)" }
            li { +"Server validates credentials" }
            li { +"Server generates a token and returns it to the client" }
            li { +"Client includes the token in subsequent requests" }
            li { +"Server validates the token for each request" }
        }
        p {
            +"Advantages:"
        }
        ul {
            li {
                strong { +"Stateless" }
                +" - Server doesn't need to store session information"
            }
            li {
                strong { +"Scalable" }
                +" - Easy to distribute across multiple servers"
            }
            li {
                strong { +"Cross-domain" }
                +" - Works across different domains and services"
            }
            li {
                strong { +"Mobile-friendly" }
                +" - Ideal for mobile applications and APIs"
            }
        }
        p {
            +"Common token formats:"
        }
        ul {
            li { +"JWT (JSON Web Tokens)" }
            li { +"Opaque tokens with server-side lookup" }
            li { +"API keys for service-to-service communication" }
        }
    }
)

object OAuthSlide : Slide(
    header = "OAuth 2.0",
    summary = {
        +"OAuth 2.0 is an authorization framework that enables applications to obtain limited access to user accounts on third-party services."
    },
    content = {
        p {
            +"OAuth 2.0 roles:"
        }
        ul {
            li {
                strong { +"Resource Owner" }
                +" - The user who authorizes access to their data"
            }
            li {
                strong { +"Client" }
                +" - The application requesting access to user data"
            }
            li {
                strong { +"Authorization Server" }
                +" - Server that authenticates the user and issues access tokens"
            }
            li {
                strong { +"Resource Server" }
                +" - Server hosting the protected user data"
            }
        }
        p {
            +"Common OAuth 2.0 grant types:"
        }
        ul {
            li {
                strong { +"Authorization Code" }
                +" - Most secure, suitable for web applications"
            }
            li {
                strong { +"Implicit" }
                +" - For client-side applications (less secure)"
            }
            li {
                strong { +"Client Credentials" }
                +" - For service-to-service communication"
            }
            li {
                strong { +"Resource Owner Password Credentials" }
                +" - Direct username/password (discouraged)"
            }
        }
        p {
            +"Benefits: Single sign-on (SSO), reduced password fatigue, leverages established identity providers."
        }
    }
)