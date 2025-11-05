package com.motycka.edu.content.topics.backend

import com.motycka.edu.model.*
import kotlinx.html.*

object DevelopmentAndEnvironmentsTopic : Topic(
    title = "Development & Environments",
    slides = listOf(
        EnvironmentProgressionSlide,
        EnvironmentConfigurationSlide,
        DevelopmentToolsSlide
    )
)

object EnvironmentProgressionSlide : Slide(
    header = "Environment Progression",
    summary = {
        +"Applications move through multiple environments from development to production, each serving a specific purpose in the delivery pipeline."
    },
    content = {
        svgDiagram(
            width = 900,
            height = 250,
            svgContent = """
                <!-- Local Environment -->
                <rect x="20" y="70" width="180" height="100" rx="10"
                      fill="#E3F2FD" stroke="#1976D2" stroke-width="2"/>
                <text x="110" y="110" text-anchor="middle" font-size="18" font-weight="bold" fill="#1565C0">
                    Local
                </text>
                <text x="110" y="135" text-anchor="middle" font-size="14" fill="#424242">
                    Developer's Machine
                </text>

                <!-- Arrow 1 -->
                <line x1="200" y1="120" x2="250" y2="120" stroke="#666" stroke-width="2" marker-end="url(#arrowhead)"/>

                <!-- Dev Environment -->
                <rect x="250" y="70" width="180" height="100" rx="10"
                      fill="#E8F5E9" stroke="#388E3C" stroke-width="2"/>
                <text x="340" y="110" text-anchor="middle" font-size="18" font-weight="bold" fill="#2E7D32">
                    Dev
                </text>
                <text x="340" y="135" text-anchor="middle" font-size="14" fill="#424242">
                    Integration Testing
                </text>

                <!-- Arrow 2 -->
                <line x1="430" y1="120" x2="480" y2="120" stroke="#666" stroke-width="2" marker-end="url(#arrowhead)"/>

                <!-- Staging Environment -->
                <rect x="480" y="70" width="180" height="100" rx="10"
                      fill="#FFF9C4" stroke="#F57C00" stroke-width="2"/>
                <text x="570" y="110" text-anchor="middle" font-size="18" font-weight="bold" fill="#E65100">
                    Staging
                </text>
                <text x="570" y="135" text-anchor="middle" font-size="14" fill="#424242">
                    Pre-Production
                </text>

                <!-- Arrow 3 -->
                <line x1="660" y1="120" x2="710" y2="120" stroke="#666" stroke-width="2" marker-end="url(#arrowhead)"/>

                <!-- Production Environment -->
                <rect x="710" y="70" width="180" height="100" rx="10"
                      fill="#FFEBEE" stroke="#D32F2F" stroke-width="2"/>
                <text x="800" y="110" text-anchor="middle" font-size="18" font-weight="bold" fill="#C62828">
                    Production
                </text>
                <text x="800" y="135" text-anchor="middle" font-size="14" fill="#424242">
                    Live Users
                </text>
            """.trimIndent()
        )

        ul {
            li {
                strong { +"Local (Development)" }
                +" - Individual developer's machine. Fast feedback loop, debugging enabled, mock services and data. Rapid iteration and experimentation."
            }
            li {
                strong { +"Development (Dev)" }
                +" - Shared integration environment. Latest code from all developers, real database instances (dev), integration with other services. Testing feature integration."
            }
            li {
                strong { +"Staging (QA/Pre-Production)" }
                +" - Production-like environment. Final testing before release, performance and load testing, QA validation. Should mirror production configuration."
            }
            li {
                strong { +"Production" }
                +" - Live environment serving real users. Highest security and stability requirements, comprehensive monitoring and alerting, automated backups and disaster recovery."
            }
        }
    }
)

object EnvironmentConfigurationSlide : Slide(
    header = "Environment Configuration & Best Practices",
    summary = {
        +"Each environment requires different configuration and careful management to ensure safety and consistency."
    },
    content = {
        twoColumns(
            left = {
                h4 { +"Environment-Specific Configuration" }
                p { +"Each environment has different:" }
                ul {
                    li {
                        strong { +"Database connections" }
                        br()
                        +"Separate databases or schemas for each environment"
                    }
                    li {
                        strong { +"API endpoints" }
                        br()
                        +"Different URLs for external services (payment, email, etc.)"
                    }
                    li {
                        strong { +"Feature flags" }
                        br()
                        +"Enable/disable features per environment"
                    }
                    li {
                        strong { +"Logging levels" }
                        br()
                        +"DEBUG in dev, INFO/WARN in production"
                    }
                    li {
                        strong { +"Resource limits" }
                        br()
                        +"Memory, CPU, connection pools scaled per environment"
                    }
                    li {
                        strong { +"Security settings" }
                        br()
                        +"SSL/TLS, CORS policies, authentication requirements"
                    }
                }
            },
            right = {
                h4 { +"Best Practices" }
                ul {
                    li {
                        strong { +"Environment parity" }
                        br()
                        +"Keep environments as similar as possible. \"Works on my machine\" should mean \"works in production\""
                    }
                    li {
                        strong { +"Configuration as code" }
                        br()
                        +"Store environment configs in version control (not secrets!)"
                    }
                    li {
                        strong { +"Secrets management" }
                        br()
                        +"Never commit secrets. Use environment variables, vault services (HashiCorp Vault, AWS Secrets Manager)"
                    }
                    li {
                        strong { +"Infrastructure as code" }
                        br()
                        +"Define infrastructure with tools like Terraform, CloudFormation"
                    }
                    li {
                        strong { +"Immutable infrastructure" }
                        br()
                        +"Deploy new versions, don't modify running systems"
                    }
                }
            }
        )

        warningCard {
            +"Never use production data in development or staging environments. Use anonymized data or synthetic test data to protect user privacy and comply with regulations (GDPR, CCPA)."
        }
    }
)

object DevelopmentToolsSlide : Slide(
    header = "Development Tools & Practices",
    summary = {
        +"Modern Kotlin development relies on a robust toolchain for productivity and code quality."
    },
    content = {
        twoColumns(
            left = {
                h4 { +"Core Development Tools" }
                ul {
                    li {
                        strong { +"IntelliJ IDEA" }
                        br()
                        +"Primary IDE with excellent Kotlin support, debugging, refactoring"
                    }
                    li {
                        strong { +"Gradle" }
                        br()
                        +"Build automation tool (Kotlin DSL preferred over Groovy)"
                    }
                    li {
                        strong { +"Git" }
                        br()
                        +"Version control with feature branches and pull requests"
                    }
                    li {
                        strong { +"Docker" }
                        br()
                        +"Local containerization matching production environment"
                    }
                }

                h4 { +"Testing Tools" }
                ul {
                    li { inlineCode("Kotest"); +" or "; inlineCode("JUnit 5"); +" - Testing frameworks" }
                    li { inlineCode("MockK"); +" - Mocking library" }
                    li { inlineCode("Testcontainers"); +" - Integration testing with real databases" }
                }
            },
            right = {
                h4 { +"Code Quality Tools" }
                ul {
                    li {
                        inlineCode("ktlint")
                        br()
                        +"Code formatting and style checking"
                    }
                    li {
                        inlineCode("detekt")
                        br()
                        +"Static code analysis for Kotlin"
                    }
                    li {
                        inlineCode("SonarQube")
                        br()
                        +"Continuous code quality inspection"
                    }
                }

                h4 { +"Development Practices" }
                ul {
                    li { +"Write tests alongside code (TDD)" }
                    li { +"Use hot reload for faster development" }
                    li { +"Run linters pre-commit" }
                    li { +"Keep dependencies up to date" }
                    li { +"Use environment-specific profiles" }
                }
            }
        )

        hintCard {
            +"Enable Spring Boot DevTools for automatic restart and live reload during development. Add dependency: "
            inlineCode("org.springframework.boot:spring-boot-devtools")
        }
    }
)
