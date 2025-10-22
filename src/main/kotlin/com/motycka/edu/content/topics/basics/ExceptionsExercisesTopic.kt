package com.motycka.edu.content.topics.basics

import kotlinx.html.*
import com.motycka.edu.model.ExerciseTopic
import com.motycka.edu.model.Slide
import com.motycka.edu.model.Topic
import com.motycka.edu.model.inlineCode

object ExceptionsExercisesTopic : Topic(
    title = "Exceptions Exercise",
    subtitle = "Hands-on Practice",
    slides = listOf(
        ExceptionsExerciseSlide
    )
)

object ExceptionsExerciseSlide : Slide(
    header = "Exercise: Exceptions",
    summary = {
        +"In this exercise, you will create a custom exception and a class that uses it. "
    },
    content = {
        ol {
            li {
                +"Create a class "
                inlineCode("OutOfFuelException")
                +" that extends "
                inlineCode("Throwable")
                +" and sets the message to "
                inlineCode("\"Car is out of fuel.\"")
            }
            li {
                +"Create a class "
                inlineCode("Car")
                +" with the following properties and methods:"
                ul {
                    li { inlineCode("private var fuelKm: Int") }
                    li {
                        inlineCode("fun drive(distance: Int)")
                        +" that will check if car has enough fuel to drive the distance and reduce the "
                        +" that will check if car has enough fuel to drive the distance and reduce the fuelKm by the distance"
                        inlineCode("fuelKm")
                        +" by the distance"
                    }
                }
            }
            li {
                +"Create an instance of the "
                inlineCode("Car")
                +" class and test the drive method with a distance that is greater than the fuelKm."
            }
            li {
                +"Use a "
                inlineCode("try-catch-finally")
                +" block to catch the "
                inlineCode("OutOfFuelException")
                +" and print the message."
            }
            li {
                +"Add another catch block to catch any other Exception and print the message."
            }
        }
    }
)
