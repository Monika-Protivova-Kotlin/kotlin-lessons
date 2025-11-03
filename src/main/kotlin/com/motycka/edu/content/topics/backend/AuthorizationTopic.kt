package com.motycka.edu.content.topics.backend

import com.motycka.edu.model.Topic
import com.motycka.edu.model.Slide
import com.motycka.edu.model.contentCard
import com.motycka.edu.model.twoColumns
import com.motycka.edu.model.highlight
import kotlinx.html.*
import com.motycka.edu.model.highlight

object AuthorizationTopic : Topic(
    title = "Authorization",
    slides = listOf(
        AuthorizationIntroSlide,
        AuthorizationVsAuthenticationSlide,
        AuthorizationMechanismsSlide
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
                h4 { +"Authentication" }
                p {
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
                }
            },
            right = {
                h4 { +"Authorization" }
                p {
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
            }
        )
        contentCard {
            h4 { +"Analogy" }
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
        twoColumns(
            left = {
                h4 {
                    +"Common authorization mechanisms"
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
            },
            right = {
                contentCard {
                    h4 { +"Implementation" }
                    p {
                        +" Authorization logic is typically implemented in the service layer. "
                        +"When using JWT for authentication, authorization can be done by checking claims in the token. "
                        +"See the JWT topic for more details on JWT-based authorization."
                    }
                }
            }
        )
    }
)
