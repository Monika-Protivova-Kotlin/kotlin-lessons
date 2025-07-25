package com.motycka.edu.content.topics.backend

import com.motycka.edu.model.Topic
import com.motycka.edu.model.Slide
import com.motycka.edu.model.highlight
import com.motycka.edu.model.inlineCode
import kotlinx.html.*
import com.motycka.edu.model.highlight

object DeploymentTopic : Topic(
    title = "Development, Build, and Packaging",
    slides = listOf(
        DevelopmentSlide,
        BuildSlide
    )
)

object DevelopmentSlide : Slide(
    header = "Development",
    summary = {
        +"Kotlin development typically uses Gradle for builds and IntelliJ for local development."
    },
    content = {
        p { strong { +"Tools & Practices:" } }
        ul {
            li { +"Use "; highlight("IntelliJ IDEA"); +" with Kotlin support." }
            li { +"Build system: "; highlight("Gradle"); +" (Kotlin DSL preferred)." }
            li { +"Unit testing with "; inlineCode("JUnit 5"); +" or "; inlineCode("Kotest"); +"." }
            li { +"Mocking with "; inlineCode("MockK"); +"." }
            li { +"Code formatted with "; inlineCode("ktlint"); +" or "; inlineCode("detekt"); +" for linting." }
        }
        p {
            +"All logic should be testable independently from infrastructure (e.g., DB, HTTP)."
        }
    }
)

object BuildSlide : Slide(
    header = "Build",
    summary = {
        +"The build process compiles source code and runs tests."
    },
    content = {
        p { strong { +"Gradle Tasks:" } }
        ul {
            li { inlineCode("./gradlew build"); +" — compiles code and runs tests" }
            li { inlineCode("./gradlew test"); +" — runs unit tests" }
            li { inlineCode("./gradlew check"); +" — includes linting/formatting" }
        }
        p { strong { +"Dependencies:" }; +" Declared in "; inlineCode("build.gradle.kts") }
    }
)
