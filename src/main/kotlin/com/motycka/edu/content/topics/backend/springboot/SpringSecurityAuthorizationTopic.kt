package com.motycka.edu.content.topics.backend.springboot

import com.motycka.edu.model.Slide
import com.motycka.edu.model.Topic
import com.motycka.edu.model.contentCard
import com.motycka.edu.model.highlight
import com.motycka.edu.model.inlineCode
import com.motycka.edu.model.kotlinPlayground
import com.motycka.edu.model.twoColumns
import kotlinx.html.*

object SpringSecurityAuthorizationTopic : Topic(
    title = "Spring Security Authorization",
    slides = listOf(
        AuthorizationBasicsSlide,
        ResourceLevelSecuritySlide,
        MethodLevelSecuritySlide,
        OverridingAuthorizationSlide,
        CustomAuthorizationSlide
    )
)

object AuthorizationBasicsSlide : Slide(
    header = "Authorization in Spring Security",
    summary = {
        +"Spring Security provides multiple ways to configure authorization rules, "
        +"from URL-based patterns to fine-grained method-level security."
    },
    content = {
        twoColumns(
            ratio = 3 to 2,
            left = {
                p { highlight("URL-Based Authorization (in SecurityFilterChain):") }
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
            },
            right = {
                p { highlight("Authorization Matchers:") }
                ul {
                    li { inlineCode("permitAll"); +" - Allow everyone (no authentication needed)" }
                    li { inlineCode("authenticated"); +" - Require any authentication" }
                    li { inlineCode("hasRole(\"ROLE\")"); +" - Require specific role" }
                    li { inlineCode("hasAnyRole(\"ROLE1\", \"ROLE2\")"); +" - Require any of the roles" }
                    li { inlineCode("hasAuthority(\"AUTH\")"); +" - Require specific authority" }
                }
            },
        )
    }
)

object ResourceLevelSecuritySlide : Slide(
    header = "Controller-Level Authorization",
    summary = {
        +"Apply authorization rules at the controller class level using "
        inlineCode("@PreAuthorize")
        +" to secure all endpoints in that controller."
    },
    content = {
        p {
            +"Place "
            inlineCode("@PreAuthorize")
            +" on the "
            highlight("controller class itself")
            +" to apply the same authorization rule to all methods. This is useful when all endpoints in a controller require the same level of access."
        }

        twoColumns(
            left = {
                p { h4 { +"Example 1: Admin-Only Controller" } }
                kotlinPlayground(
                    code = """
                // All methods require ADMIN role
                @RestController
                @RequestMapping("/api/admin/users")
                @PreAuthorize("hasRole('ADMIN')")
                class AdminUserController(
                    private val userService: UserService
                ) {
                    @GetMapping
                    fun getAllUsers(): List<User> = userService.getAll()

                    @DeleteMapping("/{id}")
                    fun deleteUser(@PathVariable id: Long) = userService.delete(id)

                    @PutMapping("/{id}/role")
                    fun updateUserRole(@PathVariable id: Long, @RequestBody role: String) {
                        userService.updateRole(id, role)
                    }
                }
            """.trimIndent(),
                    executable = false,
                    showLines = false
                )
            },
            right = {
                p { h4 { +"Example 2: Authenticated Users Only" } }
                kotlinPlayground(
                    code = """
                // All methods require authentication
                @RestController
                @RequestMapping("/api/profile")
                @PreAuthorize("isAuthenticated()")
                class ProfileController(
                    private val profileService: ProfileService
                ) {
                    @GetMapping
                    fun getProfile(): Profile = profileService.getCurrentUserProfile()

                    @PutMapping
                    fun updateProfile(@RequestBody profile: ProfileRequest) {
                        profileService.update(profile)
                    }
                }
            """.trimIndent(),
                    executable = false,
                    showLines = false
                )
            }
        )

        contentCard {
            highlight("Use class-level authorization when: ")
            +"All endpoints in a controller require the same level of access. This keeps your code DRY and makes security requirements explicit."
        }
    }
)

object MethodLevelSecuritySlide : Slide(
    header = "Method-Level Security",
    summary = {
        +"Enable method-level security with "; inlineCode("@EnableMethodSecurity"); +" and use annotations "
        +"to protect specific methods."
    },
    content = {
        twoColumns(
            ratio = 3 to 2,
            left = {
                kotlinPlayground(
                    code = """
                @Configuration
                @EnableMethodSecurity(prePostEnabled = true, securedEnabled = true)
                class SecurityConfiguration

                @Service
                class UserService {

                    @PreAuthorize("hasRole('USER')")
                    fun getUserProfile(userId: Long): UserProfile {
                        // ...
                    }

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
            },
            right = {
                p { highlight("Security Annotations:") }
                ul {
                    li { inlineCode("@PreAuthorize"); +" - Check before method execution (most common)" }
                    li { inlineCode("@PostAuthorize"); +" - Check after method execution (can access return value)" }
                    li { inlineCode("@Secured"); +" - Simpler role-based checks" }
                    li { inlineCode("@RolesAllowed"); +" - JSR-250 standard annotation" }
                }
            }
        )
    },
)

object OverridingAuthorizationSlide : Slide(
    header = "Combining Class and Method Level Authorization",
    summary = {
        +"Use class-level "
        inlineCode("@PreAuthorize")
        +" as a default, then override specific methods with different authorization rules when needed."
    },
    content = {
        p {
            +"When most endpoints in a controller need the same authorization but a few require different access levels, "
            +"you can combine "
            highlight("class-level and method-level")
            +" annotations. Method-level annotations override class-level ones."
        }

        kotlinPlayground(
            code = """
                @RestController
                @RequestMapping("/api/reports")
                @PreAuthorize("hasRole('MANAGER')")  // Default for all methods
                class ReportController(
                    private val reportService: ReportService
                ) {
                    // Inherits MANAGER requirement from class
                    @GetMapping
                    fun getReports(): List<Report> {
                        return reportService.getAll()
                    }

                    // Inherits MANAGER requirement from class
                    @GetMapping("/{id}")
                    fun getReport(@PathVariable id: Long): Report {
                        return reportService.getById(id)
                    }

                    // Override: Only ADMIN can delete reports
                    @DeleteMapping("/{id}")
                    @PreAuthorize("hasRole('ADMIN')")
                    fun deleteReport(@PathVariable id: Long) {
                        reportService.delete(id)
                    }

                    // Override: Public endpoint, no authentication required
                    @GetMapping("/summary")
                    @PreAuthorize("permitAll()")
                    fun getPublicSummary(): ReportSummary {
                        return reportService.getSummary()
                    }

                    // Override: Multiple roles allowed
                    @PostMapping
                    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER', 'SUPERVISOR')")
                    fun createReport(@RequestBody request: ReportRequest): Report {
                        return reportService.create(request)
                    }
                }
            """.trimIndent(),
            executable = false,
            showLines = false
        )

        contentCard {
            h4 { +"Authorization Hierarchy" }
            ol {
                li { strong { +"Method-level annotations" }; +" take precedence (highest priority)" }
                li { strong { +"Class-level annotations" }; +" apply to methods without their own annotations" }
                li { strong { +"SecurityFilterChain URL patterns" }; +" apply as a baseline (lowest priority)" }
            }
        }
    }
)

object CustomAuthorizationSlide : Slide(
    header = "Custom Authorization Logic",
    summary = {
        +"Create custom permission evaluators for complex authorization scenarios."
    },
    content = {
        twoColumns(
//            ratio = 3 to 2,
            left = {
                kotlinPlayground(
                    code = """
                        @Component("customSecurity")
                        class CustomSecurityService {
        
                            fun canAccessResource(
                                authentication: Authentication, 
                                resourceId: Long
                            ): Boolean {
                                val userId = (authentication.principal as UserDetails).username
                                // Custom business logic
                                return checkOwnership(userId, resourceId) || hasAdminRole(authentication)
                            }
        
                            fun hasPermission(
                                authentication: Authentication, 
                                permission: String
                            ): Boolean {
                                // Check permissions from database or external service
                                return true
                            }
        
                            private fun checkOwnership(
                                userId: String, 
                                resourceId: Long
                            ): Boolean {
                                // Check if user owns the resource
                                return true
                            }
        
                            private fun hasAdminRole(authentication: Authentication): Boolean {
                                return authentication.authorities.any {
                                    it.authority == "ROLE_ADMIN"
                                }
                            }
                        }
                    """.trimIndent(),
                    executable = false,
                    showLines = false
                )
            },
            right = {
                kotlinPlayground(
                    code = """
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
                contentCard {
                    +"💡 "; highlight("Tip: ")
                    +"Use custom security beans for complex authorization logic that involves database queries "
                    +"or external service calls."
                }
            }
        )



    },
)
