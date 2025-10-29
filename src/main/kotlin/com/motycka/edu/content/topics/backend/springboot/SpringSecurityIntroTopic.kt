package com.motycka.edu.content.topics.backend.springboot

import com.motycka.edu.model.Slide
import com.motycka.edu.model.Topic
import com.motycka.edu.model.highlight
import com.motycka.edu.model.inlineCode
import kotlinx.html.*

object SpringSecurityIntroTopic : Topic(
    title = "Spring Security Introduction",
    slides = listOf(
        SpringSecurityOverviewSlide,
        SpringSecurityArchitectureSlide,
        SpringSecurityFeaturesSlide
    )
)

object SpringSecurityOverviewSlide : Slide(
    header = "Spring Security",
    summary = {
        +"Spring Security is a powerful and highly customizable authentication and access control framework for Spring applications"
    },
    content = {
        p {
            +"Spring Security provides comprehensive security services for Java EE-based enterprise software applications. "
            +"It is the de facto standard for securing Spring-based applications."
        }

        p { strong { +"What Spring Security Provides:" } }
        ul {
            li {
                strong { +"Authentication" }
                +" - Verifying the identity of users"
            }
            li {
                strong { +"Authorization" }
                +" - Controlling access to resources"
            }
            li {
                strong { +"Protection Against Exploits" }
                +" - CSRF, session fixation, clickjacking, etc."
            }
            li {
                strong { +"Integration" }
                +" - LDAP, OAuth 2.0, SAML, JWT, and more"
            }
        }

        blockQuote {
            +"Spring Security integrates seamlessly with Spring Boot, providing auto-configuration and sensible defaults that can be customized as needed."
        }
    }
)

object SpringSecurityArchitectureSlide : Slide(
    header = "Spring Security Architecture",
    summary = {
        +"Understanding the core components of Spring Security's authentication and authorization architecture"
    },
    content = {
        p { highlight("Key Components:") }
        ul {
            li {
                strong { inlineCode("SecurityContext") }
                +" - Holds security information about the current thread"
            }
            li {
                strong { inlineCode("Authentication") }
                +" - Represents the token for an authentication request or authenticated principal"
            }
            li {
                strong { inlineCode("AuthenticationManager") }
                +" - Processes authentication requests"
            }
            li {
                strong { inlineCode("UserDetailsService") }
                +" - Core interface for loading user-specific data"
            }
            li {
                strong { inlineCode("SecurityFilterChain") }
                +" - Defines security filter configurations for different URL patterns"
            }
        }

        p { strong { +"How It Works:" } }
        ol {
            li { +"Request enters the application" }
            li { +"Security filters intercept the request" }
            li { +"Authentication filters extract credentials" }
            li { +"AuthenticationManager validates credentials" }
            li { +"SecurityContext is populated with Authentication" }
            li { +"Authorization checks are performed" }
            li { +"Request proceeds to controller or is rejected" }
        }
    }
)

object SpringSecurityFeaturesSlide : Slide(
    header = "Spring Security Features",
    content = {
        p { highlight("Authentication Methods:") }
        ul {
            li { +"Form-based login" }
            li { +"HTTP Basic authentication" }
            li { +"OAuth 2.0 / OpenID Connect" }
            li { +"LDAP authentication" }
            li { +"JWT (JSON Web Tokens)" }
            li { +"Remember-me authentication" }
        }

        p { highlight("Authorization Strategies:") }
        ul {
            li { +"URL-based security ("; inlineCode("requestMatchers"); +")" }
            li { +"Method-level security ("; inlineCode("@PreAuthorize"); +", "; inlineCode("@Secured"); +")" }
            li { +"Role-based access control (RBAC)" }
            li { +"Expression-based access control" }
        }

        p { highlight("Protection Features:") }
        ul {
            li { +"CSRF (Cross-Site Request Forgery) protection" }
            li { +"Session management" }
            li { +"Password encoding" }
            li { +"Security headers (X-Frame-Options, CSP, etc.)" }
            li { +"CORS configuration" }
        }
    }
)
