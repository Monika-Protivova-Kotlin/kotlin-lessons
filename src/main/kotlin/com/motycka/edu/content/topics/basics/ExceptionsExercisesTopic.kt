package com.motycka.edu.content.topics.basics

import kotlinx.html.*
import com.motycka.edu.model.ExerciseTopic
import com.motycka.edu.model.Slide
import com.motycka.edu.model.inlineCode

object ExceptionsExercisesTopic : ExerciseTopic(
    title = "Exceptions Exercise",
    subtitle = "Hands-on Practice",
    slides = listOf(
        ExceptionsExerciseSlide
    )
)

object ExceptionsExerciseSlide : Slide(
    header = "Exercise",
    content = {
        div("content exercise-formatting") {
            p {
                +"Create a class "
                strong { +"OutOfFuelException" }
                +" that extends "
                strong { +"Throwable" }
                +" and sets the message to "
                inlineCode("\"Car is out of fuel.\"")
            }
            p {
                +"Create a class "
                strong { +"Car" }
                +" with the following properties and methods:"
            }
            ul {
                li { inlineCode("private var fuelKm: Int") }
                li { inlineCode("fun drive(distance: Int)"); +" that will check if car has enough fuel to drive the distance and reduce the fuelKm by the distance" }
            }
            p {
                +"Create an instance of the "
                strong { +"Car" }
                +" class and test the drive method with a distance that is greater than the fuelKm."
            }
            p {
                +"Use a "
                inlineCode("try-catch-finally")
                +" block to catch the "
                strong { +"OutOfFuelException" }
                +" and print the message."
            }
            p {
                +"Add another catch block to catch any other Exception and print the message."
            }
        }
    }
)