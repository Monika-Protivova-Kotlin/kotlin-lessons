package com.motycka.edu.content.topics.backend.springboot

import com.motycka.edu.model.Slide
import com.motycka.edu.model.Topic
import com.motycka.edu.model.highlight
import com.motycka.edu.model.inlineCode
import com.motycka.edu.model.kotlinPlayground
import kotlinx.html.*

object SpringSecurityConfigurationTopic : Topic(
    title = "Spring Security Configuration",
    slides = listOf(
        SecurityConfigurationBasicsSlide,
        SecurityFilterChainSlide,
        PasswordEncoderSlide
    )
)

object SecurityConfigurationBasicsSlide : Slide(
    header = "Configuring Spring Security",
    summary = {
        +"Spring Security configuration defines how your application handles authentication and authorization"
    },
    content = {
        p {
            +"In Spring Boot 3.x, security configuration is done through "; inlineCode("@Configuration"); +" classes "
            +"that define "; inlineCode("SecurityFilterChain"); +" beans."
        }

        p { strong { +"Basic Configuration Structure:" } }
        kotlinPlayground(
            code = """
                @Configuration
                @EnableWebSecurity
                class SecurityConfiguration {

                    @Bean
                    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
                        http {
                            authorizeHttpRequests {
                                authorize("/public/**", permitAll)
                                authorize("/admin/**", hasRole("ADMIN"))
                                authorize(anyRequest, authenticated)
                            }
                            formLogin { }
                            httpBasic { }
                            csrf { disable() }
                        }
                        return http.build()
                    }
                }
            """.trimIndent(),
            executable = false
        )

        p { highlight("Key Annotations:") }
        ul {
            li {
                inlineCode("@EnableWebSecurity"); +" - Enables Spring Security's web security support"
            }
            li {
                inlineCode("@Configuration"); +" - Indicates this class provides Spring beans"
            }
        }
    }
)

object SecurityFilterChainSlide : Slide(
    header = "SecurityFilterChain Configuration",
    summary = {
        +"The SecurityFilterChain defines URL-based security rules and authentication mechanisms"
    },
    content = {
        p { highlight("Common Configuration Options:") }

        kotlinPlayground(
            code = """
                @Bean
                fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
                    http {
                        // URL-based authorization
                        authorizeHttpRequests {
                            authorize("/api/public/**", permitAll)
                            authorize("/api/user/**", hasRole("USER"))
                            authorize("/api/admin/**", hasRole("ADMIN"))
                            authorize(anyRequest, authenticated)
                        }

                        // Authentication methods
                        httpBasic { }  // Enable HTTP Basic
                        formLogin {    // Enable form-based login
                            loginPage = "/login"
                            defaultSuccessUrl = "/dashboard"
                        }

                        // Logout configuration
                        logout {
                            logoutUrl = "/logout"
                            logoutSuccessUrl = "/login"
                        }

                        // CSRF protection (disable for stateless APIs)
                        csrf { disable() }

                        // Session management
                        sessionManagement {
                            sessionCreationPolicy = SessionCreationPolicy.STATELESS
                        }
                    }
                    return http.build()
                }
            """.trimIndent(),
            executable = false,
            showLines = false
        )
    },
    fontSize = "75%"
)

object PasswordEncoderSlide : Slide(
    header = "Password Encoding",
    summary = {
        +"Never store passwords in plain text - always use a secure password encoder"
    },
    content = {
        p {
            +"Spring Security provides several password encoders. "
            inlineCode("BCryptPasswordEncoder"); +" is the recommended choice for most applications."
        }

        p { strong { +"Password Encoder Configuration:" } }
        kotlinPlayground(
            code = """
                @Configuration
                class SecurityConfiguration {

                    @Bean
                    fun passwordEncoder(): PasswordEncoder {
                        return BCryptPasswordEncoder()
                    }

                    @Bean
                    fun userDetailsService(passwordEncoder: PasswordEncoder): UserDetailsService {
                        val user = User.builder()
                            .username("user")
                            .password(passwordEncoder.encode("password"))
                            .roles("USER")
                            .build()

                        val admin = User.builder()
                            .username("admin")
                            .password(passwordEncoder.encode("admin123"))
                            .roles("USER", "ADMIN")
                            .build()

                        return InMemoryUserDetailsManager(user, admin)
                    }
                }
            """.trimIndent(),
            executable = false
        )

        p { highlight("Available Password Encoders:") }
        ul {
            li { strong { inlineCode("BCryptPasswordEncoder") }; +" - Recommended (uses bcrypt hashing)" }
            li { inlineCode("Pbkdf2PasswordEncoder"); +" - Uses PBKDF2" }
            li { inlineCode("SCryptPasswordEncoder"); +" - Uses scrypt" }
            li { inlineCode("Argon2PasswordEncoder"); +" - Uses Argon2" }
        }

        blockQuote {
            +"⚠️ Never use "; inlineCode("NoOpPasswordEncoder"); +" in production!"
        }
    }
)
