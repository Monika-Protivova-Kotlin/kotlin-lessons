package com.motycka.edu.content.topics.oop

import com.motycka.edu.model.highlight
import kotlinx.html.*
import com.motycka.edu.model.Topic
import com.motycka.edu.model.Slide
import com.motycka.edu.model.highlight

object OopPrinciplesOverviewTopic : Topic(
    title = "Object-oriented Programming",
    subtitle = "Principles in Kotlin",
    slides = listOf(
        OopPrinciplesOverviewSlide
    )
)

object OopPrinciplesOverviewSlide : Slide(
    header = "Object-oriented Programming Principles in Kotlin",
    content = {
        div("content content-center content-100") {
            p { +"Remember, there are four main OOP principles:" }
            ol {
                li { highlight("Encapsulation") }
                li { highlight("Inheritance") }
                li { highlight("Polymorphism") }
                li { highlight("Abstraction") }
            }
            br()
            br()
            em { +"We will go more into detail of each of how these are handle in Kotlin on the following slides." }
        }
    },
    fontSize = "100%"
)
