package com.motycka.edu.content.topics.oop

import kotlinx.html.*
import com.motycka.edu.model.Topic
import com.motycka.edu.model.Slide
import com.motycka.edu.model.kotlinPlayground
import com.motycka.edu.model.inlineCode

object DataClassesTopic : Topic(
    title = "Data Classes",
    slides = listOf(DataClassesSlide)
)

object DataClassesSlide : Slide(
    header = "Data Classes",
    summary = {
        +"Data classes are a special type of class in Kotlin that are used to represent data."
    },
    content = {
        p {
            +"For a data class, Kotlin compiler automatically generates convenience methods for copying, "
            +"comparing, and printing objects and more."
        }
        p {
            +"Data classes are marked with the "
            inlineCode("data")
            +" keyword."
        }
        kotlinPlayground(
            code = """
                data class UniversityCourse(
                    val subject: String,
                    val startDate: LocalDate,
                    val endDate: LocalDate = startDate.plusDays(30),
                    val students: MutableList<String> = mutableListOf()
                )
            """,
            executable = false
        )
        p {
            +"There are few rules for data classes:"
        }
        ul {
            li { +"Primary constructor must have at least one parameter." }
            li { +"Primary constructor must have "; inlineCode("val"); +" or "; inlineCode("var"); +" keyword." }
            li { +"Data classes cannot be abstract, open, sealed, or inner." }
            li { +"Data classes cannot inherit from other classes." }
        }
    }
)
