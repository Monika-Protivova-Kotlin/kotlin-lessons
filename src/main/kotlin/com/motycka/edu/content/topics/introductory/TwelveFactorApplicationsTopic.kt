package com.motycka.edu.content.topics.introductory

import com.motycka.edu.model.Slide
import com.motycka.edu.model.Topic
import com.motycka.edu.model.inlineCode
import kotlinx.html.br
import kotlinx.html.div
import kotlinx.html.ol
import kotlinx.html.p
import kotlinx.html.strong
import kotlinx.html.blockQuote
import kotlinx.html.li

object TwelveFactorApplicationsTopic : Topic(
    title = "12-Factor Applications",
    slides = listOf(
        TwelveFactorApplicationsIntroSlide,
        CodebaseSlide,
        DependenciesSlide,
        ConfigSlide,
        BackingServicesSlide,
        BuildReleaseRunSlide,
        ProcessesSlide,
        PortBindingSlide,
        ConcurrencySlide,
        DisposabilitySlide,
        DevProdParitySlide,
        LogsSlide,
        AdminProcessesSlide
    )
)

object TwelveFactorApplicationsIntroSlide : Slide(
    header = "12-Factor Applications",
    summary = { +"The 12-Factor App is a methodology for building modern web applications that are scalable, maintainable, and portable." },
    content = {
        p {
            +"It was introduced by Heroku in 2011 and has become a widely accepted best practice for building cloud-native applications. Although it's been around for a while, it remains relevant (with some updates). Most of the principles have become industry standards."
        }
        p {
            strong { +"The 12 factors are:" }
        }
        div(classes = "row") {
            div(classes = "column") {
                ol {
                    li { +"Codebase" }
                    li { +"Dependencies" }
                    li { +"Config" }
                    li { +"Backing Services" }
                    li { +"Build, Release, Run" }
                    li { +"Processes" }
                }
            }
            div(classes = "column") {
                ol {
                    attributes["start"] = "7"
                    li { +"Port Binding" }
                    li { +"Concurrency" }
                    li { +"Disposability" }
                    li { +"Dev/Prod Parity" }
                    li { +"Logs" }
                    li { +"Admin Processes" }
                }
            }
        }
        blockQuote {
            +"We will go through each of these briefly and then discuss how they apply to Kotlin applications as we build our backend."
            br()
            +"You don't need to memorize them all right now, just be aware of them."
        }
    }
)

object CodebaseSlide : Slide(
    header = "1. Codebase",
    summary = { +"One codebase tracked in revision control, many deploys." },
    content = {
        p {
            +"This means that there should be a single codebase for the application, which is stored in a version control system (e.g., Git). The same codebase can be deployed to multiple environments (e.g., development, staging, production)."
        }
        p {
            +"This allows for easier collaboration, code review, and deployment processes."
        }
    }
)

object DependenciesSlide : Slide(
    header = "2. Dependencies",
    summary = { +"Explicitly declare and isolate dependencies." },
    content = {
        p {
            +"This means that all dependencies of the application should be explicitly declared in a dependency management file (e.g., "
            inlineCode("build.gradle.kts")
            +" for Kotlin). The application should not rely on any implicit dependencies that are not declared."
        }
        p {
            +"This allows for easier management of dependencies and ensures that the application can be built and run consistently across different environments."
        }
    }
)

object ConfigSlide : Slide(
    header = "3. Config",
    summary = { +"Store configuration in the environment." },
    content = {
        p {
            +"This means that all configuration settings for the application should be stored in environment variables or configuration files that are not part of the codebase. This allows for easier management of configuration settings and ensures that sensitive information (e.g., API keys, database credentials) is not stored in the codebase."
        }
        p {
            +"This also allows for different configurations to be used in different environments (e.g., development, staging, production)."
        }
        p {
            +"Secret management tools (e.g., HashiCorp Vault, AWS Secrets Manager) can be used to manage sensitive configuration settings securely."
        }
    }
)

object BackingServicesSlide : Slide(
    header = "4. Backing Services",
    summary = { +"Treat backing services as attached resources." },
    content = {
        p {
            +"This means that all external services that the application relies on (e.g., databases, message queues, caching systems) should be treated as attached resources that can be easily swapped out or replaced."
        }
        p {
            +"This allows for easier management of external services and ensures that the application can be deployed to different environments without being tightly coupled to specific services."
        }
    }
)

object BuildReleaseRunSlide : Slide(
    header = "5. Build, Release, Run",
    summary = { +"Strictly separate build and run stages." },
    content = {
        p {
            +"This means that the build process (compiling code, packaging dependencies) should be separate from the release process (deploying the application). The build process should produce a deployable artifact (e.g., a JAR file) that can be deployed to different environments."
        }
        p {
            +"This allows for easier management of the deployment process and ensures that the same artifact can be deployed to different environments."
        }
    }
)

object ProcessesSlide : Slide(
    header = "6. Processes",
    summary = { +"Execute the app as one or more stateless processes." },
    content = {
        p {
            +"This means that the application should be designed to run as one or more stateless processes that can be easily scaled horizontally. The application should not rely on any local state not stored in a backing service (e.g., database, cache)."
        }
        p {
            +"This allows for easier scaling and ensures that the application can handle increased the load without losing state."
        }
    }
)

object PortBindingSlide : Slide(
    header = "7. Port Binding",
    summary = { +"Export services via port binding." },
    content = {
        p {
            +"This means that the application should be designed to run as a self-contained service that listens on a specific port. The application should not rely on any external web server or container to run."
        }
        p {
            +"This allows for easier deployment and ensures that the application can be run in different environments without relying on external infrastructure."
        }
    }
)

object ConcurrencySlide : Slide(
    header = "8. Concurrency",
    summary = { +"Scale out via the process model." },
    content = {
        p {
            +"This means that the application should be designed to handle concurrent requests and scale out by running multiple instances of the application. The application should not rely on any shared state between instances."
        }
        p {
            +"This allows for easier scaling and ensures that the application can handle increased the load without losing performance."
        }
    }
)

object DisposabilitySlide : Slide(
    header = "9. Disposability",
    summary = { +"Enable fast startup and graceful shutdown." },
    content = {
        p {
            +"This means that the application should be designed to start up quickly and shut down gracefully. The application should not rely on any long-running processes or background tasks that can block the shutdown process."
        }
        p {
            +"This allows for easier deployment and ensures that the application can be restarted quickly in case of failures."
        }
    }
)

object DevProdParitySlide : Slide(
    header = "10. Dev/Prod Parity",
    summary = { +"Maintain development, staging, and production parity." },
    content = {
        p {
            +"This means that the development, staging, and production environments should be as similar as possible. The application should not rely on any environment-specific configurations or dependencies."
        }
        p {
            +"This allows for easier testing and ensures that the application behaves consistently across different environments."
        }
    }
)

object LogsSlide : Slide(
    header = "11. Logs",
    summary = { +"Treat logs as event streams." },
    content = {
        p {
            +"This means that the application should be designed to produce logs that can be easily consumed and processed by external systems. The application should not rely on any local log files or logging infrastructure."
        }
        p {
            +"This allows for easier monitoring and ensures that the application can be debugged and analyzed in real-time."
        }
        p {
            +"In modern systems, this rule can be updated to observability, which includes not only logs but also metrics and traces."
        }
    }
)

object AdminProcessesSlide : Slide(
    header = "12. Admin Processes",
    summary = { +"Run admin/management tasks as one-off processes." },
    content = {
        p {
            +"This means that the application should be designed to run administrative or management tasks as one-off processes that can be executed independently of the main application. The application should not rely on any long-running administrative processes or background tasks."
        }
        p {
            +"This allows for easier management and ensures that administrative tasks can be executed without affecting the main application."
        }
    }
)
