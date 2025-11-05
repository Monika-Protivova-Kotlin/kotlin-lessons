package com.motycka.edu.content.topics.backend

import com.motycka.edu.model.*
import kotlinx.html.*

object BuildAndPackagingTopic : Topic(
    title = "Build & Packaging",
    slides = listOf(
        BuildProcessSlide,
        PackagingSpringBootSlide,
        ApplicationConfigurationSlide,
        ConfigurationPrioritySlide,
        BuildAutomationSlide
    )
)

object BuildProcessSlide : Slide(
    header = "Build Process & Gradle",
    summary = {
        +"Gradle orchestrates the entire build lifecycle from compilation to testing to packaging."
    },
    content = {
        svgDiagram(
            width = 1200,
            height = 250,
            svgContent = """
                <!-- Clean (separate starting point) -->
                <rect x="20" y="20" width="150" height="75" rx="8" fill="#FFEBEE" stroke="#D32F2F" stroke-width="2"/>
                <text x="95" y="42" text-anchor="middle" font-size="13" font-weight="bold" fill="#C62828">clean</text>
                <text x="95" y="58" text-anchor="middle" font-size="9" fill="#666">Remove artifacts</text>
                <text x="95" y="72" text-anchor="middle" font-size="8" fill="#888" font-family="monospace">./gradlew clean</text>

                <!-- Main build flow -->
                <!-- compileKotlin -->
                <rect x="20" y="110" width="150" height="75" rx="8" fill="#E3F2FD" stroke="#1976D2" stroke-width="2"/>
                <text x="95" y="132" text-anchor="middle" font-size="13" font-weight="bold" fill="#0D47A1">compile</text>
                <text x="95" y="148" text-anchor="middle" font-size="9" fill="#666">Source → Classes</text>
                <text x="95" y="162" text-anchor="middle" font-size="8" fill="#888" font-family="monospace">./gradlew compileKotlin</text>

                <line x1="170" y1="147" x2="195" y2="147" stroke="#666" stroke-width="2" marker-end="url(#arrowhead)"/>

                <!-- test -->
                <rect x="195" y="110" width="150" height="75" rx="8" fill="#FFF9C4" stroke="#F57C00" stroke-width="2"/>
                <text x="270" y="132" text-anchor="middle" font-size="13" font-weight="bold" fill="#E65100">test</text>
                <text x="270" y="148" text-anchor="middle" font-size="9" fill="#666">Run tests</text>
                <text x="270" y="162" text-anchor="middle" font-size="8" fill="#888" font-family="monospace">./gradlew test</text>

                <line x1="345" y1="147" x2="370" y2="147" stroke="#666" stroke-width="2" marker-end="url(#arrowhead)"/>

                <!-- check -->
                <rect x="370" y="110" width="150" height="75" rx="8" fill="#F3E5F5" stroke="#7B1FA2" stroke-width="2"/>
                <text x="445" y="132" text-anchor="middle" font-size="13" font-weight="bold" fill="#4A148C">check</text>
                <text x="445" y="148" text-anchor="middle" font-size="9" fill="#666">Quality checks</text>
                <text x="445" y="162" text-anchor="middle" font-size="8" fill="#888" font-family="monospace">./gradlew check</text>

                <line x1="520" y1="147" x2="545" y2="147" stroke="#666" stroke-width="2" marker-end="url(#arrowhead)"/>

                <!-- build -->
                <rect x="545" y="110" width="150" height="75" rx="8" fill="#E8F5E9" stroke="#388E3C" stroke-width="2"/>
                <text x="620" y="132" text-anchor="middle" font-size="13" font-weight="bold" fill="#1B5E20">build</text>
                <text x="620" y="148" text-anchor="middle" font-size="9" fill="#666">Full build</text>
                <text x="620" y="162" text-anchor="middle" font-size="8" fill="#888" font-family="monospace">./gradlew build</text>

                <line x1="695" y1="147" x2="720" y2="147" stroke="#666" stroke-width="2" marker-end="url(#arrowhead)"/>

                <!-- bootJar -->
                <rect x="720" y="110" width="150" height="75" rx="8" fill="#FFE0B2" stroke="#E65100" stroke-width="2"/>
                <text x="795" y="132" text-anchor="middle" font-size="13" font-weight="bold" fill="#BF360C">bootJar</text>
                <text x="795" y="148" text-anchor="middle" font-size="9" fill="#666">Create JAR</text>
                <text x="795" y="162" text-anchor="middle" font-size="8" fill="#888" font-family="monospace">./gradlew bootJar</text>

                <!-- bootRun (separate branch from build) -->
                <line x1="620" y1="110" x2="620" y2="57" stroke="#666" stroke-width="2"/>
                <line x1="620" y1="57" x2="910" y2="57" stroke="#666" stroke-width="2" marker-end="url(#arrowhead)"/>

                <rect x="910" y="20" width="150" height="75" rx="8" fill="#C8E6C9" stroke="#2E7D32" stroke-width="2"/>
                <text x="985" y="42" text-anchor="middle" font-size="13" font-weight="bold" fill="#1B5E20">bootRun</text>
                <text x="985" y="58" text-anchor="middle" font-size="9" fill="#666">Run app</text>
                <text x="985" y="72" text-anchor="middle" font-size="8" fill="#888" font-family="monospace">./gradlew bootRun</text>

                <!-- Failure path -->
                <path d="M 270 190 Q 270 220, 95 220 Q 95 220, 95 190"
                      stroke="#D32F2F" stroke-width="2" fill="none" stroke-dasharray="5,5"/>
                <text x="182" y="240" text-anchor="middle" font-size="10" fill="#C62828">← Test failures stop build</text>
            """.trimIndent()
        )

        p {
            strong { +"Key files in Gradle projects:" }
        }
        ul {
            li {
                inlineCode("build.gradle.kts")
                +" - Build configuration (dependencies, plugins, tasks)"
            }
            li {
                inlineCode("settings.gradle.kts")
                +" - Project structure and modules"
            }
            li {
                inlineCode("gradle.properties")
                +" - Build properties and JVM options"
            }
            li {
                inlineCode("gradlew")
                +" - Gradle wrapper (ensures consistent version)"
            }
        }

        importantCard {
            +"Always use the Gradle wrapper ("
            inlineCode("./gradlew")
            +") instead of a globally installed Gradle. This ensures everyone uses the same Gradle version."
        }
    }
)

object PackagingSpringBootSlide : Slide(
    header = "Packaging Spring Boot Applications",
    content = {
        p {
            +"Spring Boot applications are usually packaged as executable Fat JAR files that bundle everything needed to run the application."
        }
        p{
            strong { +"JAR" }
            + " stand for Java ARchive - a package format for Java/Kotlin applications, but it is essentially a ZIP file with a specific structure and metadata."
        }
        twoColumns(
            left = {
                h4 { +"Executable JAR" }
                p {
                   +"Also called a \"Fat JAR\" or \"Uber JAR\", it contains:"
                }
                ul {
                    li {
                        strong { +"Application code" }
                        br()
                        +"Your compiled Kotlin/Java classes"
                    }
                    li {
                        strong { +"All dependencies" }
                        br()
                        +"Every library bundled inside (Spring Boot, Kotlin stdlib, Jackson, etc.)"
                    }
                    li {
                        strong { +"Embedded web server" }
                        br()
                        +"Tomcat, Jetty, or Undertow - no external server needed"
                    }
                    li {
                        strong { +"Configuration & resources" }
                        br()
                        +"application.yml, static files, templates"
                    }
                    li { +"It is felf-contained and portable (ideal for containerization)" }
                    li { +"You cam run it with: "; inlineCode("java -jar app.jar") }
                }
            },
            right = {
                svgDiagram(
                    width = 500,
                    height = 400,
                    svgContent = """
                        <!-- Outer JAR Container -->
                        <rect x="30" y="20" width="440" height="360" rx="10" fill="#F5F5F5" stroke="#333" stroke-width="3"/>
                        <text x="250" y="45" text-anchor="middle" font-size="14" font-weight="bold" fill="#333">myapp.jar</text>
                        <text x="250" y="60" text-anchor="middle" font-size="11" fill="#666">(Executable Fat JAR)</text>

                        <!-- Application Code Layer (Top) -->
                        <rect x="50" y="80" width="400" height="65" rx="5" fill="#E3F2FD" stroke="#1976D2" stroke-width="2"/>
                        <text x="250" y="105" text-anchor="middle" font-size="12" font-weight="bold" fill="#0D47A1">Application Code</text>
                        <text x="250" y="125" text-anchor="middle" font-size="10" fill="#424242">Your Kotlin classes, controllers, services</text>

                        <!-- Dependencies Layer (Middle) -->
                        <rect x="50" y="150" width="400" height="90" rx="5" fill="#FFF9C4" stroke="#F57C00" stroke-width="2"/>
                        <text x="250" y="175" text-anchor="middle" font-size="12" font-weight="bold" fill="#E65100">Dependency JARs</text>
                        <text x="250" y="195" text-anchor="middle" font-size="9" fill="#424242">spring-boot.jar, kotlin-stdlib.jar,</text>
                        <text x="250" y="210" text-anchor="middle" font-size="9" fill="#424242">jackson.jar, hibernate.jar, ...</text>
                        <text x="250" y="225" text-anchor="middle" font-size="9" fill="#424242">and all other dependencies</text>

                        <!-- Embedded Server Layer (Bottom) -->
                        <rect x="50" y="245" width="400" height="60" rx="5" fill="#C8E6C9" stroke="#388E3C" stroke-width="2"/>
                        <text x="250" y="268" text-anchor="middle" font-size="12" font-weight="bold" fill="#1B5E20">Embedded Web Server</text>
                        <text x="250" y="288" text-anchor="middle" font-size="10" fill="#424242">Tomcat / Jetty / Undertow</text>

                        <!-- Configuration Layer (Very Bottom) -->
                        <rect x="50" y="310" width="400" height="50" rx="5" fill="#F3E5F5" stroke="#7B1FA2" stroke-width="2"/>
                        <text x="250" y="330" text-anchor="middle" font-size="10" font-weight="bold" fill="#4A148C">Configuration & Resources</text>
                        <text x="250" y="348" text-anchor="middle" font-size="9" fill="#666">(application.yml, static/*, templates/*)</text>
                    """.trimIndent()
                )
            }
        )

//        kotlinPlayground(
//            code = """
//                // build.gradle.kts
//                plugins {
//                    kotlin("jvm") version "1.9.20"
//                    id("org.springframework.boot") version "3.2.0"
//                    kotlin("plugin.spring") version "1.9.20"
//                }
//
//                tasks.named<Jar>("jar") {
//                    enabled = false // Disable plain jar
//                }
//
//                tasks.named<BootJar>("bootJar") {
//                    archiveFileName.set("myapp.jar")
//                    mainClass.set("com.example.ApplicationKt")
//                }
//            """.trimIndent(),
//            executable = false
//        )
//
//        hintCard {
//            +"The Spring Boot Gradle plugin automatically creates an executable JAR with "
//            inlineCode("./gradlew bootJar")
//            +". The resulting JAR can be run with "
//            inlineCode("java -jar build/libs/myapp.jar")
//        }
    }
)

object ApplicationConfigurationSlide : Slide(
    header = "Application Configuration",
    summary = {
        +"Spring Boot supports multiple configuration formats and environment-specific profiles for flexible deployment."
    },
    content = {
        twoColumns(
            left = {
                h4 { +"Configuration Files" }
                p { inlineCode("application.properties"); +" (traditional)" }
                kotlinPlayground(
                    code = """
                        server.port=8080
                        spring.datasource.url=jdbc:postgresql://localhost/mydb
                        spring.datasource.username=user
                        logging.level.root=INFO
                    """.trimIndent(),
                    executable = false
                )

                p { inlineCode("application.yml"); +" (preferred - more readable)" }
                kotlinPlayground(
                    code = """
                        server:
                          port: 8080
                        spring:
                          datasource:
                            url: jdbc:postgresql://localhost/mydb
                            username: user
                        logging:
                          level:
                            root: INFO
                    """.trimIndent(),
                    executable = false
                )
            },
            right = {
                h4 { +"Spring Profiles" }
                p { +"Environment-specific configuration:" }
                ul {
                    li { inlineCode("application-dev.yml"); +" - Development" }
                    li { inlineCode("application-staging.yml"); +" - Staging" }
                    li { inlineCode("application-prod.yml"); +" - Production" }
                }

                p { +"Activate with:" }
                kotlinPlayground(
                    code = """
                        # Environment variable
                        SPRING_PROFILES_ACTIVE=prod

                        # Command line
                        java -jar app.jar --spring.profiles.active=prod

                        # In application.yml
                        spring:
                          profiles:
                            active: dev
                    """.trimIndent(),
                    executable = false
                )
            }
        )
        warningCard("Never commit sensitive data") {
            +"Use environment variables or secret management tools (Vault, AWS Secrets Manager) for passwords, API keys, and tokens."
        }
    }
)

object ConfigurationPrioritySlide : Slide(
    header = "Configuration Priority & Overrides",
    summary = {
        +"Spring Boot uses a well-defined priority hierarchy to resolve configuration values. Higher priority sources override lower priority ones."
    },
    content = {
//        p {
//            +"When the same property is defined in multiple places, Spring Boot follows this priority order. "
//            +"Think of it as layers: each layer can override the values from layers below it."
//        }

        twoColumns(
            left = {
//                h4 { +"Priority Order (Highest → Lowest)" }
                svgDiagram(
                    width = 500,
                    height = 420,
                    svgContent = """
                        <!-- Arrow indicating direction -->
                        <text x="250" y="25" text-anchor="middle" font-size="13" font-weight="bold" fill="#15803D">↓ HIGHEST PRIORITY ↓</text>

                        <!-- 1. Command Line -->
                        <rect x="30" y="40" width="440" height="55" rx="8" fill="#DCFCE7" stroke="#22C55E" stroke-width="3"/>
                        <text x="50" y="62" font-size="18" font-weight="bold" fill="#15803D">1</text>
                        <text x="90" y="62" font-size="14" font-weight="bold" fill="#15803D">Command Line Arguments</text>
                        <text x="90" y="80" font-size="11" fill="#166534">--server.port=8080, --spring.profiles.active=prod</text>

                        <!-- 2. Environment Variables -->
                        <rect x="30" y="105" width="440" height="55" rx="8" fill="#DBEAFE" stroke="#3B82F6" stroke-width="2"/>
                        <text x="50" y="127" font-size="18" font-weight="bold" fill="#1E40AF">2</text>
                        <text x="90" y="127" font-size="14" font-weight="bold" fill="#1E40AF">OS Environment Variables</text>
                        <text x="90" y="145" font-size="11" fill="#1E3A8A">SPRING_DATASOURCE_URL, DATABASE_PASSWORD</text>

                        <!-- 3. Profile-specific -->
                        <rect x="30" y="170" width="440" height="55" rx="8" fill="#FEF3C7" stroke="#F59E0B" stroke-width="2"/>
                        <text x="50" y="192" font-size="18" font-weight="bold" fill="#B45309">3</text>
                        <text x="90" y="192" font-size="14" font-weight="bold" fill="#B45309">Profile-Specific Files</text>
                        <text x="90" y="210" font-size="11" fill="#92400E">application-prod.yml, application-dev.yml</text>

                        <!-- 4. application.yml -->
                        <rect x="30" y="235" width="440" height="55" rx="8" fill="#E0F2FE" stroke="#0EA5E9" stroke-width="2"/>
                        <text x="50" y="257" font-size="18" font-weight="bold" fill="#0369A1">4</text>
                        <text x="90" y="257" font-size="14" font-weight="bold" fill="#0369A1">application.yml</text>
                        <text x="90" y="275" font-size="11" fill="#075985">Base configuration file</text>

                        <!-- 5. @PropertySource -->
                        <rect x="30" y="300" width="440" height="55" rx="8" fill="#E0E7FF" stroke="#6366F1" stroke-width="2"/>
                        <text x="50" y="322" font-size="18" font-weight="bold" fill="#4338CA">5</text>
                        <text x="90" y="322" font-size="14" font-weight="bold" fill="#4338CA">@PropertySource Annotations</text>
                        <text x="90" y="340" font-size="11" fill="#3730A3">Custom property files in code</text>

                        <!-- 6. Defaults -->
                        <rect x="30" y="365" width="440" height="55" rx="8" fill="#F3F4F6" stroke="#9CA3AF" stroke-width="2"/>
                        <text x="50" y="387" font-size="18" font-weight="bold" fill="#6B7280">6</text>
                        <text x="90" y="387" font-size="14" font-weight="bold" fill="#6B7280">Default Values in Code</text>
                        <text x="90" y="405" font-size="11" fill="#6B7280">Hardcoded defaults in @Value annotations</text>

                        <text x="250" y="440" text-anchor="middle" font-size="13" font-weight="bold" fill="#6B7280">↑ LOWEST PRIORITY ↑</text>
                    """.trimIndent()
                )
            },
            right = {
                p {
                    h4 { +"Common Use Cases" }
                    ul {
                        li {
                            strong { +"Temporary overrides" }
//                            br()
//                            +"Use command line: "
//                            inlineCode("java -jar app.jar --server.port=8081")
                            br()
                            +"Quick testing without changing files"
                        }
                        li {
                            strong { +"Production secrets" }
                            br()
                            +"Store in environment variables or secret managers (AWS Secrets, Kubernetes secrets)"
//                            br()
//                            +"Never commit sensitive values"
                        }
                        li {
                            strong { +"Environment-specific settings" }
                            br()
                            +"Database URLs, API endpoints, feature flags in "
                            inlineCode("application-prod.yml")
                        }
                        li {
                            strong { +"Developer defaults" }
                            br()
                            +"Sensible defaults in "
                            inlineCode("application.yml")
                            +" that work for local development"
                        }
                    }
                }

//                p {
//                    h4 { +"Real-World Example" }
//                    kotlinPlayground(
//                        code = """
//                        # application.yml (default)
//                        server:
//                          port: 8080
//
//                        # application-prod.yml (profile-specific)
//                        server:
//                          port: 80
//
//                        # Environment variable (production)
//                        # SERVER_PORT=443
//
//                        # Command line (temporary override)
//                        # --server.port=9090
//
//                        # Result in production with all set:
//                        # Command line (9090) wins!
//                    """.trimIndent(),
//                        executable = false
//                    )
//                }
            }
        )

        importantCard("Configuration Best Practice") {
            +"Start with good defaults in "
            inlineCode("application.yml")
            +". Use profile files for environment differences. "
            +"Store secrets in environment variables or secret management tools. "
            +"Use command line arguments only for temporary overrides during testing."
        }
    }
)

object BuildAutomationSlide : Slide(
    header = "Build Automation & CI/CD",
    summary = {
        +"Continuous Integration and Continuous Deployment automate building, testing, and deploying applications, catching issues early and enabling rapid, reliable releases."
    },
    fontSize = "70%",
    content = {
//        h4 { +"Why Automate Builds?" }
//        p {
//            +"Manual builds are time-consuming, error-prone, and don't scale with team growth. "
//            +"Developers might forget to run tests, use different build configurations, or deploy inconsistently. "
//            +"CI/CD solves these problems by automating the entire pipeline from code commit to production deployment."
//        }

        twoColumns(
            left = {
                h4 { +"Problems CI/CD Solves" }
                ul {
                    li { strong { +"\"Works on my machine\"" }; +" - Everyone uses the same build environment" }
                    li { strong { +"Forgotten tests" }; +" - Tests run automatically on every commit" }
                    li { strong { +"Integration issues" }; +" - Code is integrated and tested continuously" }
                    li { strong { +"Manual deployment errors" }; +" - Deployments are automated and consistent" }
                    li { strong { +"Slow feedback" }; +" - Developers get immediate feedback on code quality" }
                }
            },
            right = {
                h4 { +"Key Benefits" }
                ul {
                    li { +"Catch bugs early before they reach production" }
                    li { +"Ship features faster with confidence" }
                    li { +"Reduce manual work and human error" }
                    li { +"Enforce quality standards automatically" }
                    li { +"Enable multiple deployments per day" }
                    li { +"Improve team collaboration" }
                }
            }
        )

//        h4 { +"CI/CD Pipeline Flow" }
        svgDiagram(
            width = 1000,
            height = 200,
            svgContent = """
                <!-- Source Control -->
                <rect x="20" y="50" width="130" height="80" rx="8" fill="#E8F5E9" stroke="#2E7D32" stroke-width="2"/>
                <text x="85" y="85" text-anchor="middle" font-size="14" font-weight="bold" fill="#1B5E20">Source</text>
                <text x="85" y="105" text-anchor="middle" font-size="14" font-weight="bold" fill="#1B5E20">Control</text>
                <text x="85" y="125" text-anchor="middle" font-size="11" fill="#424242">Git Push</text>

                <line x1="150" y1="90" x2="180" y2="90" stroke="#666" stroke-width="2" marker-end="url(#arrowhead)"/>

                <!-- Build -->
                <rect x="180" y="50" width="130" height="80" rx="8" fill="#E3F2FD" stroke="#1976D2" stroke-width="2"/>
                <text x="245" y="90" text-anchor="middle" font-size="14" font-weight="bold" fill="#0D47A1">Build</text>
                <text x="245" y="115" text-anchor="middle" font-size="11" fill="#424242">Compile & Resolve</text>

                <line x1="310" y1="90" x2="340" y2="90" stroke="#666" stroke-width="2" marker-end="url(#arrowhead)"/>

                <!-- Test -->
                <rect x="340" y="50" width="130" height="80" rx="8" fill="#FFF9C4" stroke="#F57C00" stroke-width="2"/>
                <text x="405" y="90" text-anchor="middle" font-size="14" font-weight="bold" fill="#E65100">Test</text>
                <text x="405" y="115" text-anchor="middle" font-size="11" fill="#424242">Unit & Integration</text>

                <line x1="470" y1="90" x2="500" y2="90" stroke="#666" stroke-width="2" marker-end="url(#arrowhead)"/>

                <!-- Package -->
                <rect x="500" y="50" width="130" height="80" rx="8" fill="#F3E5F5" stroke="#7B1FA2" stroke-width="2"/>
                <text x="565" y="90" text-anchor="middle" font-size="14" font-weight="bold" fill="#4A148C">Package</text>
                <text x="565" y="115" text-anchor="middle" font-size="11" fill="#424242">Create Artifacts</text>

                <line x1="630" y1="90" x2="660" y2="90" stroke="#666" stroke-width="2" marker-end="url(#arrowhead)"/>

                <!-- Deploy -->
                <rect x="660" y="50" width="130" height="80" rx="8" fill="#FFE0B2" stroke="#E65100" stroke-width="2"/>
                <text x="725" y="90" text-anchor="middle" font-size="14" font-weight="bold" fill="#BF360C">Deploy</text>
                <text x="725" y="115" text-anchor="middle" font-size="11" fill="#424242">To Environment</text>

                <line x1="790" y1="90" x2="820" y2="90" stroke="#666" stroke-width="2" marker-end="url(#arrowhead)"/>

                <!-- Verify -->
                <rect x="820" y="50" width="130" height="80" rx="8" fill="#C8E6C9" stroke="#388E3C" stroke-width="2"/>
                <text x="885" y="90" text-anchor="middle" font-size="14" font-weight="bold" fill="#1B5E20">Verify</text>
                <text x="885" y="115" text-anchor="middle" font-size="11" fill="#424242">Health Checks</text>

                <!-- Failure feedback loop -->
                <path d="M 405 135 Q 405 160, 245 160 Q 85 160, 85 135"
                      stroke="#D32F2F" stroke-width="2" fill="none" stroke-dasharray="5,5"/>
                <text x="245" y="175" text-anchor="middle" font-size="10" fill="#C62828">← Failure triggers rebuild</text>
            """.trimIndent()
        )

        twoColumns(
            left = {
                h4 { +"Common CI/CD Tools" }
                ul {
                    li {
                        strong { +"GitHub Actions" }
                        +" - Built into GitHub, YAML-based workflows"
                    }
                    li {
                        strong { +"GitLab CI/CD" }
                        +" - Integrated with GitLab, powerful pipelines"
                    }
                    li {
                        strong { +"Jenkins" }
                        +" - Self-hosted, highly customizable"
                    }
                    li {
                        strong { +"CircleCI / Travis CI" }
                        +" - Cloud-based CI/CD platforms"
                    }
                }
            },
            right = {
                importantCard("Artifacts & Version Management") {
                    +"Artifacts (often docker image) are stored in artifact repositories and are versioned using semantic versioning (1.2.3)."
                    br
                    +"This allows for re-deploying specific versions if needed."
                }
            }
        )
    }
)
