package com.motycka.edu.content.topics.backend

import com.motycka.edu.model.Topic
import com.motycka.edu.model.Slide
import kotlinx.html.*

object ApplicationDevelopmentPracticeTopic : Topic(
    title = "Application Development Practice",
    slides = listOf(
        ApplicationDevelopmentPracticeSlide
    )
)

object ApplicationDevelopmentPracticeSlide : Slide(
    header = "Assignment",
    summary = {
        +"This lesson focuses on applying the concepts learned in the previous lessons to implement a simple application using Kotlin and Spring Boot."
    },
    content = {
        p {
            +"The assignment will be provided in the form of a GitHub classroom repository."
        }
    }
)