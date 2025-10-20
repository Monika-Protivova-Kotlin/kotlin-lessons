package com.motycka.edu.content.topics.oop

import com.motycka.edu.builders.CourseBuilder.ARROW_DOWN
import com.motycka.edu.model.Slide
import com.motycka.edu.model.Topic
import com.motycka.edu.model.inlineCode
import kotlinx.html.*

object SingletonObjectsExerciseTopic : Topic(
    title = "Exercise: Singleton Objects",
    slides = listOf(
        SingletonObjectsExercise1Slide,
        SingletonObjectsExercise2Slide
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
            li { +"Add function "; inlineCode("rectangleArea(width: Double, height: Double): Double"); +" → width × height" }
            li { +"Add function "; inlineCode("triangleArea(base: Double, height: Double): Double"); +" → base × height / 2" }
        }

        p { strong { +"Expected Output:" } }
        ul {
            li { inlineCode("ShapeUtils.circleArea(5.0)"); +" → ~78.54" }
            li { inlineCode("ShapeUtils.rectangleArea(4.0, 6.0)"); +" → 24.0" }
            li { inlineCode("ShapeUtils.triangleArea(5.0, 3.0)"); +" → 7.5" }
            li { inlineCode("ShapeUtils.PI"); +" → 3.14159" }
        }

//        p {
//            em {
//                +"Playground $ARROW_DOWN"
//            }
//        }
    }
)

object SingletonObjectsExercise2Slide : Slide(
    header = "Exercise 2: Shape Factory",
    summary = {
        +"Create a Circle class with a companion object factory."
    },
    content = {
        p {
            +"Create a "
            inlineCode("Circle")
            +" class with radius property and a companion object that tracks instances."
        }

        p { strong { +"Task:" } }
        ul {
            li { +"Create a "; inlineCode("Circle"); +" class with:" }
            ul {
                li { +"Property "; inlineCode("radius: Double") }
                li { +"Method "; inlineCode("area(): Double"); +" → π × radius²" }
            }
            li { +"Add a companion object with:" }
            ul {
                li { +"Constant "; inlineCode("PI = 3.14159") }
                li { +"Variable "; inlineCode("circleCount: Int = 0"); +" to track created circles" }
                li { +"Factory function "; inlineCode("create(radius: Double): Circle"); +" that increments counter" }
                li { +"Function "; inlineCode("getCircleCount(): Int"); +" that returns the count" }
            }
        }

        p { strong { +"Expected Output:" } }
        ul {
            li { +"After creating 3 circles with "; inlineCode("Circle.create()"); +":" }
            ul {
                li { inlineCode("circle1.area()"); +" → ~78.54 (for radius 5.0)" }
                li { inlineCode("Circle.getCircleCount()"); +" → 3" }
                li { inlineCode("Circle.PI"); +" → 3.14159" }
            }
        }

//        p {
//            em {
//                +"Playground $ARROW_DOWN"
//            }
//        }
    }
)
