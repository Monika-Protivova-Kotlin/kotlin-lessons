package com.motycka.edu.content.topics.backend

import com.motycka.edu.model.Topic
import com.motycka.edu.model.Slide
import com.motycka.edu.model.highlight
import kotlinx.html.*
import com.motycka.edu.model.highlight

object ApplicationLifecycleTopic : Topic(
    title = "Application Lifecycle",
    slides = listOf(
        ApplicationLifecycleSlide1,
        ApplicationLifecycleSlide2
    )
)

object ApplicationLifecycleSlide1 : Slide(
    header = "Application Lifecycle",
    summary = {
        +"Develop → Build → Package → Deploy → Run → Maintain → (back to Develop)"
    },
    content = {
        ol {
            li {
                highlight("Development")
                ul {
                    li { +"Code is written and tested locally, focussing on features, correctness, and unit testing." }
                    li { +"This phase involves development tools such as IDEs (like IntelliJ), local databases, and mocks." }
                }
            }
            li {
                highlight("Build, Test, Integration")
                ul {
                    li { +"Code is built (compiled) and tests are run (often automated) to ensure the changes don't break existing behavior" }
                    li { +"This usually happens on an integration environment or CI." }
                }
            }
            li {
                highlight("Packaging")
                ul {
                    li { +"Application is packaged for deployment." }
                    li { +"Deployment artifacts are created, such as JAR files or Docker images." }
                    li { +"Configuration is externalized (env vars, files)." }
                    li { +"The app must be self-contained, portable, and environment-aware." }
                }
            }
            li {
                highlight("Deployment")
                ul {
                    li { +"Application is pushed to an environment (dev/staging/prod)." }
                    li { +"This may be manual or automated (CI/CD) and may involve some orchestration (e.g., Kubernetes, Docker Compose)." }
                }
            }
        }
    }
)

object ApplicationLifecycleSlide2 : Slide(
    header = "Application Lifecycle",
    summary = {
        +"Develop → Build → Package → Deploy → Run → Maintain → (back to Develop)"
    },
    content = {
        ol {
            attributes["start"] = "5"
            li {
                highlight("Runtime & Operation")
                ul {
                    li { +"App is now serving users in production." }
                    li { +"Needs to be observable (logs, metrics, traces)." }
                    li { +"Must handle failures gracefully." }
                }
            }
            li {
                highlight("Maintenance & Evolution")
                ul {
                    li { +"Bugs are fixed, features are added." }
                    li { +"Technical debt may be addressed." }
                    li { +"App may be refactored or deprecated." }
                }
            }
        }
        p {
            em { +"The lifecycle is cyclical — each change goes through the same phases." }
        }
    }
)