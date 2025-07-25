package com.motycka.edu.content.topics.backend

import com.motycka.edu.model.Topic
import com.motycka.edu.model.Slide
import com.motycka.edu.model.twoColumns
import com.motycka.edu.model.highlight
import kotlinx.html.*
import com.motycka.edu.model.highlight

object AuthorizationTopic : Topic(
    title = "Authorization",
    slides = listOf(
        AuthorizationIntroSlide,
        AuthorizationVsAuthenticationSlide,
        AuthorizationMechanismsSlide,
        JWTAuthorizationSlide,
        AuthorizationClaimsSlide
    )
)

object AuthorizationIntroSlide : Slide(
    header = "Authorization",
    summary = {
        +"Authorization is the process of determining what actions an authenticated user is allowed to perform. "
        +"It answers the question: \"What can you do?\""
    },
    content = {
        p {
            +"Authorization occurs after authentication and determines the permissions and access rights of an authenticated user."
        }
        p {
            +"Key concepts in authorization:"
        }
        ul {
            li {
                strong { +"Permissions" }
                +" - Specific actions a user can perform (read, write, delete)"
            }
            li {
                strong { +"Roles" }
                +" - Groups of permissions assigned to users (admin, user, guest)"
            }
            li {
                strong { +"Resources" }
                +" - Objects or services being protected (files, APIs, data)"
            }
            li {
                strong { +"Policies" }
                +" - Rules that determine access based on various conditions"
            }
        }
        p {
            +"Authorization ensures that users can only access resources and perform actions that they are explicitly allowed to."
        }
    }
)

object AuthorizationVsAuthenticationSlide : Slide(
    header = "Authorization vs Authentication",
    summary = {
        +"Understanding the difference between authentication and authorization is crucial for implementing proper security."
    },
    content = {
        twoColumns(
            left = {
                p {
                    strong { +"Authentication" }
                }
                ul {
                    li { +"\"Who are you?\"" }
                    li { +"Verifies user identity" }
                    li { +"Validates credentials" }
                    li { +"Happens first" }
                    li { +"Examples:" }
                    ul {
                        li { +"Username/password" }
                        li { +"Biometric verification" }
                        li { +"Two-factor authentication" }
                    }
                }
            },
            right = {
                p {
                    strong { +"Authorization" }
                }
                ul {
                    li { +"\"What can you do?\"" }
                    li { +"Determines user permissions" }
                    li { +"Controls access to resources" }
                    li { +"Happens after authentication" }
                    li { +"Examples:" }
                    ul {
                        li { +"Role-based access control" }
                        li { +"Permission checking" }
                        li { +"Resource-level security" }
                    }
                }
            }
        )
        p {
            highlight("Analogy:")
            +" Think of a hotel - authentication is showing your ID at check-in (proving who you are), "
            +"while authorization is your room key working only for your specific room (what you can access)."
        }
    }
)

object AuthorizationMechanismsSlide : Slide(
    header = "Authorization Mechanisms",
    summary = {
        +"Different approaches to implementing authorization, each suitable for different scenarios and requirements."
    },
    content = {
        p {
            +"Common authorization mechanisms:"
        }
        ul {
            li {
                strong { +"Role-Based Access Control (RBAC)" }
                ul {
                    li { +"Users are assigned roles" }
                    li { +"Roles have specific permissions" }
                    li { +"Simple and widely used" }
                    li { +"Example: admin, editor, viewer" }
                }
            }
            li {
                strong { +"Attribute-Based Access Control (ABAC)" }
                ul {
                    li { +"Permissions based on attributes" }
                    li { +"More flexible and granular" }
                    li { +"Considers user, resource, and environment attributes" }
                    li { +"Example: time of day, location, department" }
                }
            }
            li {
                strong { +"Access Control Lists (ACL)" }
                ul {
                    li { +"Permissions attached to specific resources" }
                    li { +"Fine-grained control" }
                    li { +"Can become complex to manage" }
                    li { +"Example: file system permissions" }
                }
            }
        }
    }
)

object JWTAuthorizationSlide : Slide(
    header = "Authorization with JWT",
    summary = {
        +"JWT tokens can carry authorization information, making them ideal for stateless authorization decisions."
    },
    content = {
        p {
            +"The authorization mechanism is almost exclusively implemented in the service layer."
        }
        p {
            +"In case JWT is used, the authorization can be done by checking the claims in the token."
        }
        p {
            +"JWT-based authorization process:"
        }
        ol {
            li { +"User authenticates and receives JWT" }
            li { +"JWT contains user roles and permissions as claims" }
            li { +"For each request, server extracts and validates JWT" }
            li { +"Server checks claims against required permissions" }
            li { +"Access granted or denied based on authorization rules" }
        }
        p {
            +"Benefits of JWT authorization:"
        }
        ul {
            li { +"Stateless - no server-side session storage needed" }
            li { +"Self-contained - all authorization info in token" }
            li { +"Scalable - works across distributed systems" }
            li { +"Fast - no database lookups for basic authorization" }
        }
    }
)

object AuthorizationClaimsSlide : Slide(
    header = "Authorization Claims in JWT",
    summary = {
        +"Examples of claims that can be used for authorization decisions in JWT tokens."
    },
    content = {
        p {
            +"Example of authorization claims in JWT:"
        }
        pre {
            code {
                classes = setOf("hljs", "json")
                +"""
                    {
                      "sub": "user123",
                      "name": "John Doe",
                      "roles": ["admin", "user"],
                      "permissions": ["read:users", "write:posts", "delete:comments"],
                      "department": "engineering",
                      "organization": "acme-corp",
                      "level": "senior",
                      "exp": 1516243022
                    }
                """.trimIndent()
            }
        }
        p {
            +"Common types of authorization claims:"
        }
        ul {
            li {
                strong { +"Role or permissions" }
                +" - Determine what actions the user is allowed to perform"
                ul {
                    li { +"Examples: admin, editor, read:users, write:posts" }
                }
            }
            li {
                strong { +"Organization or department" }
                +" - Restrict access based on user's affiliation"
                ul {
                    li { +"Examples: engineering, marketing, acme-corp" }
                }
            }
            li {
                strong { +"Permissions within organization" }
                +" - Restrict access based on user's role within the organization"
                ul {
                    li { +"Examples: team-lead, senior, junior, contractor" }
                }
            }
        }
        p {
            em { +"Note: Keep JWT tokens reasonably sized by including only essential authorization information." }
        }
    }
)
