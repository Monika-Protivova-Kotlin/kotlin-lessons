package com.motycka.edu.content.topics.basics.functions

import com.motycka.edu.model.Slide
import com.motycka.edu.model.Topic
import com.motycka.edu.model.inlineCode
import kotlinx.html.*

object ExtensionFunctionsExerciseTopic : Topic(
    title = "Exercise: Extension Functions",
    slides = listOf(
        ExtensionFunctionsExerciseSlide,
    )
)

object ExtensionFunctionsExerciseSlide : Slide(
    header = "Exercise: Extension Functions on Pair and Triple",
    summary = {
        +"Create extension functions for Pair and Triple to calculate area and volume."
    },
    content = {
        p {
            +"Create extension functions for Kotlin's built-in "
            inlineCode("Pair")
            +" and "
            inlineCode("Triple")
            +" classes to perform geometric calculations."
        }

        p { strong { +"Task 1: Pair Extension Function" } }
        p {
            +"Create an extension function "
            inlineCode("area()")
            +" returning "
            inlineCode("Double")
            +" on "
            inlineCode("Pair<Double, Double>")
            +" that calculates the area of a rectangle."
        }
        ul {
            li { +"The "; inlineCode("first"); +" value represents width" }
            li { +"The "; inlineCode("second"); +" value represents height" }
        }

        p { strong { +"Task 2: Triple Extension Function" } }
        p {
            +"Create an extension function "
            inlineCode("volume()")
            +" returning "
            inlineCode("Double")
            +" on "
            inlineCode("Triple<Double, Double, Double>")
            +" that calculates the volume of a of a rectangular prism."
        }
        ul {
            li { +"The "; inlineCode("first"); +" value represents width"}
            li { +"The "; inlineCode("second"); +" value represents length" }
            li { +"The "; inlineCode("third"); +" value represents height" }
        }

        p {
            strong {
                +"You can use "
                a(href = "https://pl.kotl.in/w5WsO_vh3") { +"Kotlin Playground" }
            }
        }
    }
)
