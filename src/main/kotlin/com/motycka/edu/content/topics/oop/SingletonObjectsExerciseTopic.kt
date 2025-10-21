package com.motycka.edu.content.topics.oop

import com.motycka.edu.model.Slide
import com.motycka.edu.model.Topic
import com.motycka.edu.model.inlineCode
import kotlinx.html.*

object SingletonObjectsExerciseTopic : Topic(
    title = "Exercise: Singleton Objects",
    slides = listOf(
        SingletonObjectsExercise1Slide
    )
)

object SingletonObjectsExercise1Slide : Slide(
    header = "Exercise 1: Shape Utilities",
    summary = {
        +"Create a singleton object with utility functions for shape calculations."
    },
    content = {
        p {
            +"Create a singleton object called "
            inlineCode("ShapeUtils")
            +" with shape utility functions."
        }

        p { strong { +"Task:" } }
        ul {
            li { +"Create a singleton object "; inlineCode("ShapeUtils") }
            li { +"Add constant "; inlineCode("PI = 3.14159") }
            li { +"Add function "; inlineCode("circleArea(radius: Double): Double"); +" → π × radius²" }
            li { +"Add function "; inlineCode("rectangleArea(a: Double, b: Double): Double"); +" → width × height" }
            li { +"Add function "; inlineCode("triangleArea(base: Double, height: Double): Double"); +" → base × height / 2" }
        }

        p { strong { +"Expected Output:" } }
        ul {
            li { inlineCode("ShapeUtils.circleArea(5.0)"); +" → ~78.54" }
            li { inlineCode("ShapeUtils.rectangleArea(4.0, 6.0)"); +" → 24.0" }
            li { inlineCode("ShapeUtils.triangleArea(5.0, 3.0)"); +" → 7.5" }
            li { inlineCode("ShapeUtils.PI"); +" → 3.14159" }
        }
        p {
            strong {
                + "You can use "
                a(href = "https://pl.kotl.in/_UCwgrasL") { +"Kotlin Playground" }
            }
        }
    }
)
