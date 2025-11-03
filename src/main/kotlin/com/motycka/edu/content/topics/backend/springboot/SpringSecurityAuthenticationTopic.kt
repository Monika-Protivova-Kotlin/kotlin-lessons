package com.motycka.edu.content.topics.backend.springboot

import com.motycka.edu.model.Slide
import com.motycka.edu.model.Topic
import com.motycka.edu.model.contentCard
import com.motycka.edu.model.highlight
import com.motycka.edu.model.inlineCode
import com.motycka.edu.model.kotlinPlayground
import com.motycka.edu.model.twoColumns
import kotlinx.html.*

object SpringSecurityAuthenticationTopic : Topic(
    title = "Spring Security Authentication",
    slides = listOf(
        UserDetailsServiceSlide,
        CustomAuthenticationProviderSlide,
        AccessingAuthenticationSlide
    )
)

object UserDetailsServiceSlide : Slide(
    header = "UserDetailsService",
    summary = {
        +"UserDetailsService is the core interface for loading user-specific data during authentication"
    },
    content = {
        p {
            +"The "; inlineCode("UserDetailsService"); +" interface has a single method that loads a user by username. "
            +"Spring Security uses this to retrieve user information during authentication."
        }
        twoColumns(
            ratio = 3 to 2,
            left = {
                kotlinPlayground(
                    code = """
                @Service
                class CustomUserDetailsService(
                    private val userRepository: UserRepository
                ) : UserDetailsService {

                    override fun loadUserByUsername(username: String): UserDetails {
                        val user = userRepository.findByUsername(username)
                            ?: throw UsernameNotFoundException("User not found: ${"$"}username")

                        return User.builder()
                            .username(user.username)
                            .password(user.password)
                            .roles(*user.roles.toTypedArray())
                            .accountExpired(!user.isActive)
                            .accountLocked(user.isLocked)
                            .credentialsExpired(user.isPasswordExpired)
                            .disabled(!user.isEnabled)
                            .build()
                    }
                }
            """.trimIndent(),
                    executable = false
                )
            },
            right = {
                p { highlight("Key Points:") }
                ul {
                    li { +"Load user from your database or data source" }
                    li { +"Return a "; inlineCode("UserDetails"); +" object" }
                    li { +"Throw "; inlineCode("UsernameNotFoundException"); +" if user not found" }
                    li { +"Include roles and account status information" }
                }
            },
        )
    }
)

object CustomAuthenticationProviderSlide : Slide(
    header = "Custom Authentication Provider",
    summary = {
        +"For complex authentication logic, implement a custom AuthenticationProvider"
    },
    content = {
        p {
            +"While "; inlineCode("UserDetailsService"); +" is sufficient for most cases, you can implement "
            inlineCode("AuthenticationProvider"); +" for complete control over the authentication process."
        }

        kotlinPlayground(
            code = """
                @Component
                class CustomAuthenticationProvider(
                    private val userDetailsService: UserDetailsService,
                    private val passwordEncoder: PasswordEncoder
                ) : AuthenticationProvider {

                    override fun authenticate(authentication: Authentication): Authentication {
                        val password = authentication.credentials.toString()
                        val userDetails = userDetailsService.loadUserByUsername(authentication.name)

                        // Verify password
                        if (!passwordEncoder.matches(password, userDetails.password)) {
                            throw BadCredentialsException("Invalid credentials")
                        }

                        // Additional custom checks
                        if (!isAllowedToLogin(userDetails)) {
                            throw LockedException("User account is locked")
                        }

                        return UsernamePasswordAuthenticationToken(userDetails, password, userDetails.authorities)
                    }

                    override fun supports(authentication: Class<*>): Boolean {
                        return authentication == UsernamePasswordAuthenticationToken::class.java
                    }

                    private fun isAllowedToLogin(userDetails: UserDetails): Boolean = true
                }
            """.trimIndent(),
            executable = false,
            showLines = false
        )
    },
)

object AccessingAuthenticationSlide : Slide(
    header = "Accessing Authentication Information",
    summary = {
        +"Retrieve the currently authenticated user in your controllers and services"
    },
    content = {
        twoColumns(
            ratio = 3 to 2,
            left = {
                kotlinPlayground(
                    code = """
                @RestController
                @RequestMapping("/api")
                class UserController {

                    // 1. Using SecurityContextHolder (works anywhere)
                    @GetMapping("/me/v1")
                    fun getCurrentUserV1(): String {
                        val authentication = SecurityContextHolder.getContext().authentication
                        return authentication.name
                    }

                    // 2. Using @AuthenticationPrincipal annotation (recommended)
                    @GetMapping("/me/v2")
                    fun getCurrentUserV2(
                        @AuthenticationPrincipal userDetails: UserDetails
                    ): String {
                        return userDetails.username
                    }

                    // 3. Using Authentication parameter
                    @GetMapping("/me/v3")
                    fun getCurrentUserV3(authentication: Authentication): String {
                        return authentication.name
                    }

                    // 4. Using Principal parameter
                    @GetMapping("/me/v4")
                    fun getCurrentUserV4(principal: Principal): String {
                        return principal.name
                    }
                }
            """.trimIndent(),
                    executable = false
                )
            },
            right = {
                contentCard {
                    +"💡 "; strong { +"Best Practice: " }
                    br
                    +"Use "; inlineCode("@AuthenticationPrincipal"); +" in controllers for clean, type-safe access to user details."
                }
            },
        )
    }
)
