package com.motycka.edu.content.topics.backend.springboot

import com.motycka.edu.model.Slide
import com.motycka.edu.model.Topic
import com.motycka.edu.model.highlight
import com.motycka.edu.model.inlineCode
import com.motycka.edu.model.kotlinPlayground
import kotlinx.html.*

object FirstControllerExerciseTopic : Topic(
    title = "Exercise: REST API Controller",
    slides = listOf(
        ProjectSetupSlide,
        AlternativeSetupSlide,
        RestApiExerciseSlide
    )
)

object ProjectSetupSlide : Slide(
    header = "Exercise: Coffee Shop REST API",
    summary = {
        +"Build a REST API for a coffee shop with full CRUD operations"
    },
    content = {
        h4 { +"Fork the Starter Project (Recommended)" }

        p { strong { +"1. Fork the repository:" } }
        p {
            +"Visit "
            a(href = "https://github.com/Monika-Protivova-Kotlin/harbour-space-coffee") {
                code { +"https://github.com/Monika-Protivova-Kotlin/harbour-space-coffee" }
            }
        }
        p { +"Click the 'Fork' button to create your own copy" }

        p { strong { +"2. Clone your fork:" } }
        kotlinPlayground(
            code = """
                git clone https://github.com/YOUR_USERNAME/harbour-space-coffee.git
                cd harbour-space-coffee
            """.trimIndent(),
            executable = false
        )

        p { strong { +"3. Open in IntelliJ IDEA:" } }
        ul {
            li { +"Open the project in IntelliJ IDEA" }
            li { +"Wait for Gradle to download dependencies" }
            li { +"Follow the instructions in the README.md" }
        }

        blockQuote {
            +"📖 All exercise details, requirements, and implementation tips are in the project's README.md"
        }
    }
)

object AlternativeSetupSlide : Slide(
    header = "Alternative: Start from Scratch",
    content = {
        p {
            +"If you prefer to start from scratch instead of forking the repository, "
            +"you can create a new Spring Boot project:"
        }

        h4 { +"Step 1: Visit Spring Initializr" }
        p {
            +"Go to "
            a(href = "https://start.spring.io") {
                code { +"https://start.spring.io" }
            }
        }

        h4 { +"Step 2: Configure Your Project" }
        ul {
            li { strong { +"Project:" }; +" Gradle - Kotlin" }
            li { strong { +"Language:" }; +" Kotlin" }
            li { strong { +"Spring Boot:" }; +" 3.x.x (latest stable version)" }
            li { strong { +"Dependencies:" }; +" Spring Web, Spring Boot DevTools" }
            li { strong { +"Java:" }; +" 21 or 17" }
        }

        h4 { +"Step 3: Generate and Import" }
        ol {
            li { +"Click 'GENERATE' to download the project" }
            li { +"Extract the ZIP file" }
            li { +"Open the project in IntelliJ IDEA" }
            li { +"Wait for Gradle to download dependencies" }
        }

        blockQuote {
            +"⚠️ If you choose this option, you'll need to implement everything from scratch. "
            +"The forked repository provides a starter structure and README with detailed requirements."
        }
    }
)

object RestApiExerciseSlide : Slide(
    header = "What You'll Build",
    summary = {
        +"A complete REST API with all HTTP methods, covering the concepts from today's lesson"
    },
    content = {
        h4 { +"Exercise Overview" }
        p {
            +"You will implement a REST API for a coffee shop that demonstrates:"
        }

        ul {
            li { strong { +"HTTP Methods" }; +" - GET, POST, PUT, PATCH, DELETE" }
            li { strong { +"Path Variables" }; +" - Extract IDs from URLs ("; inlineCode("/{id}"); +")" }
            li { strong { +"Query Parameters" }; +" - Filter and search ("; inlineCode("?name=Latte"); +")" }
            li { strong { +"Request Bodies" }; +" - Receive JSON data with "; inlineCode("@RequestBody") }
            li { strong { +"Response Codes" }; +" - 200 OK, 201 Created, 204 No Content, 404 Not Found" }
            li { strong { +"ResponseEntity" }; +" - Control HTTP responses" }
        }

        h4 { +"API Endpoints You'll Create" }
        p { +"Your coffee shop API will include:" }
        ul {
            li { code { +"GET /api/menu" }; +" - List all menu items" }
            li { code { +"GET /api/menu/{id}" }; +" - Get a specific item" }
            li { code { +"POST /api/menu" }; +" - Add a new item" }
            li { code { +"PUT /api/menu/{id}" }; +" - Update an item" }
            li { code { +"DELETE /api/menu/{id}" }; +" - Remove an item" }
            li { code { +"PATCH /api/menu/{id}/price" }; +" - Update just the price" }
        }

        blockQuote {
            +"📖 "; strong { +"Full Requirements: " }
            +"See the project's README.md for:"
            ul {
                li { +"Detailed endpoint specifications" }
                li { +"Data models and examples" }
                li { +"Implementation tips and best practices" }
                li { +"Testing instructions" }
            }
        }

        h4 { +"Testing Your API" }
        p { +"Test endpoints using:" }
        ul {
            li { strong { +"Postman" }; +" - Recommended (GUI for all HTTP methods)" }
            li { +"IntelliJ IDEA HTTP Client - Built-in testing tool" }
            li { +"Browser - For GET requests only" }
            li { +"curl - Command-line testing" }
        }

        blockQuote {
            +"💡 Start simple! Get the basic GET endpoints working first, then add POST, PUT, and DELETE."
        }
    }
)
