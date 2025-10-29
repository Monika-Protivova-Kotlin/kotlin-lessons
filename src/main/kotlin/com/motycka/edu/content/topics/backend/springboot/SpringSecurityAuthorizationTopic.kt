package com.motycka.edu.content.topics.backend.springboot

import com.motycka.edu.model.Slide
import com.motycka.edu.model.Topic
import com.motycka.edu.model.highlight
import com.motycka.edu.model.inlineCode
import com.motycka.edu.model.kotlinPlayground
import kotlinx.html.*

object SpringSecurityAuthorizationTopic : Topic(
    title = "Spring Security Authorization",
    slides = listOf(
        AuthorizationBasicsSlide,
        MethodLevelSecuritySlide,
        CustomAuthorizationSlide
    )
)

object AuthorizationBasicsSlide : Slide(
    header = "Authorization in Spring Security",
    summary = {
        +"Authorization determines what authenticated users are allowed to do"
    },
    content = {
        p {
            +"Spring Security provides multiple ways to configure authorization rules, "
            +"from URL-based patterns to fine-grained method-level security."
        }

        p { strong { +"URL-Based Authorization (in SecurityFilterChain):" } }
        kotlinPlayground(
            code = """
                @Bean
                fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
                    http {
                        authorizeHttpRequests {
                            // Public endpoints
                            authorize("/api/public/**", permitAll)
                            authorize("/api/health", permitAll)

                            // Role-based authorization
                            authorize("/api/user/**", hasRole("USER"))
                            authorize("/api/admin/**", hasRole("ADMIN"))
                            authorize("/api/moderator/**", hasAnyRole("ADMIN", "MODERATOR"))

                            // Authority-based authorization
                            authorize("/api/reports/**", hasAuthority("REPORT_READ"))

                            // All other requests require authentication
                            authorize(anyRequest, authenticated)
                        }
                    }
                    return http.build()
                }
            """.trimIndent(),
            executable = false
        )

        p { highlight("Authorization Matchers:") }
        ul {
            li { inlineCode("permitAll"); +" - Allow everyone (no authentication needed)" }
            li { inlineCode("authenticated"); +" - Require any authentication" }
            li { inlineCode("hasRole(\"ROLE\")"); +" - Require specific role" }
            li { inlineCode("hasAnyRole(\"ROLE1\", \"ROLE2\")"); +" - Require any of the roles" }
            li { inlineCode("hasAuthority(\"AUTH\")"); +" - Require specific authority" }
        }
    }
)

object MethodLevelSecuritySlide : Slide(
    header = "Method-Level Security",
    summary = {
        +"Secure individual methods with annotations for fine-grained access control"
    },
    content = {
        p {
            +"Enable method-level security with "; inlineCode("@EnableMethodSecurity"); +" and use annotations "
            +"to protect specific methods."
        }

        kotlinPlayground(
            code = """
                @Configuration
                @EnableMethodSecurity(prePostEnabled = true, securedEnabled = true)
                class SecurityConfiguration

                @Service
                class UserService {

                    // Only users with USER role can call this
                    @PreAuthorize("hasRole('USER')")
                    fun getUserProfile(userId: Long): UserProfile {
                        // ...
                    }

                    // Only the owner or admin can access
                    @PreAuthorize("#userId == authentication.principal.id or hasRole('ADMIN')")
                    fun updateUser(userId: Long, data: UserUpdateRequest) {
                        // ...
                    }

                    // Multiple conditions
                    @PreAuthorize("hasRole('ADMIN') and hasAuthority('DELETE_USER')")
                    fun deleteUser(userId: Long) {
                        // ...
                    }

                    // Simple role check
                    @Secured("ROLE_ADMIN")
                    fun adminOnlyOperation() {
                        // ...
                    }

                    // Check return value
                    @PostAuthorize("returnObject.ownerId == authentication.principal.id")
                    fun getDocument(docId: Long): Document {
                        // ...
                    }
                }
            """.trimIndent(),
            executable = false,
            showLines = false
        )

        p { highlight("Security Annotations:") }
        ul {
            li { inlineCode("@PreAuthorize"); +" - Check before method execution (most common)" }
            li { inlineCode("@PostAuthorize"); +" - Check after method execution (can access return value)" }
            li { inlineCode("@Secured"); +" - Simpler role-based checks" }
            li { inlineCode("@RolesAllowed"); +" - JSR-250 standard annotation" }
        }
    },
    fontSize = "75%"
)

object CustomAuthorizationSlide : Slide(
    header = "Custom Authorization Logic",
    summary = {
        +"Implement custom authorization logic for complex business rules"
    },
    content = {
        p {
            +"Create custom permission evaluators for complex authorization scenarios."
        }

        kotlinPlayground(
            code = """
                @Component("customSecurity")
                class CustomSecurityService {

                    fun canAccessResource(authentication: Authentication, resourceId: Long): Boolean {
                        val userId = (authentication.principal as UserDetails).username
                        // Custom business logic
                        return checkOwnership(userId, resourceId) || hasAdminRole(authentication)
                    }

                    fun hasPermission(authentication: Authentication, permission: String): Boolean {
                        // Check permissions from database or external service
                        return true
                    }

                    private fun checkOwnership(userId: String, resourceId: Long): Boolean {
                        // Check if user owns the resource
                        return true
                    }

                    private fun hasAdminRole(authentication: Authentication): Boolean {
                        return authentication.authorities.any {
                            it.authority == "ROLE_ADMIN"
                        }
                    }
                }

                // Usage in methods
                @Service
                class DocumentService {

                    @PreAuthorize("@customSecurity.canAccessResource(authentication, #documentId)")
                    fun getDocument(documentId: Long): Document {
                        // ...
                    }

                    @PreAuthorize("@customSecurity.hasPermission(authentication, 'DOCUMENT_DELETE')")
                    fun deleteDocument(documentId: Long) {
                        // ...
                    }
                }
            """.trimIndent(),
            executable = false,
            showLines = false
        )

        blockQuote {
            +"💡 "; strong { +"Tip: " }
            +"Use custom security beans for complex authorization logic that involves database queries "
            +"or external service calls."
        }
    },
    fontSize = "75%"
)
