package com.motycka.edu.content.topics.oop

import com.motycka.edu.builders.CourseBuilder.ARROW_DOWN
import com.motycka.edu.model.Slide
import com.motycka.edu.model.Topic
import com.motycka.edu.model.inlineCode
import kotlinx.html.*

object ObjectsAndClassesExerciseTopic : Topic(
    title = "Exercise: Classes and Objects",
    slides = listOf(
        ClassesAndObjectsExercise1Slide,
        ClassesAndObjectsExercise2Slide,
        ClassesAndObjectsExercise3Slide
    )
)

object ClassesAndObjectsExercise1Slide : Slide(
    header = "Exercise 1: Circle Class",
    summary = {
        +"Create a Circle class with properties and methods for area and perimeter."
    },
    content = {
        p {
            +"Create a "
            inlineCode("Circle")
            +" class with a radius property."
        }

        p { strong { +"Task:" } }
        ul {
            li { +"Create a class "; inlineCode("Circle"); +" with property "; inlineCode("radius: Double") }
            li { +"Add method "; inlineCode("area()"); +" that returns π × radius²" }
            li { +"Add method "; inlineCode("perimeter()"); +" that returns 2 × π × radius" }
            li { +"Use PI = 3.14159" }
        }

        p { strong { +"Expected Output:" } }
        ul {
            li { +"For "; inlineCode("Circle(5.0)"); +":" }
            ul {
                li { +"Area: ~78.54" }
                li { +"Perimeter: ~31.42" }
            }
        }

//        p {
//            strong {
//                + "You can use "
//                a(href = "https://pl.kotl.in/a91gCQl5d") { +"Kotlin Playground" }
//            }
//        }
    }
)

object ClassesAndObjectsExercise2Slide : Slide(
    header = "Exercise 2: Rectangle with Init Block",
    summary = {
        +"Create a Rectangle class with dimension validation in the init block."
    },
    content = {
        p {
            +"Create a "
            inlineCode("Rectangle")
            +" class with width and height properties and validate dimensions."
        }

        p { strong { +"Task:" } }
        ul {
            li { +"Create a class "; inlineCode("Rectangle"); +" with properties "; inlineCode("width: Double"); +" and "; inlineCode("height: Double") }
            li { +"Add an "; inlineCode("init"); +" block to validate both dimensions are positive (> 0)" }
            li { +"Add method "; inlineCode("area()"); +" that returns width × height" }
            li { +"Add method "; inlineCode("perimeter()"); +" that returns 2 × (width + height)" }
        }

        p { strong { +"Expected Output:" } }
        ul {
            li { +"For "; inlineCode("Rectangle(5.0, 3.0)"); +":" }
            ul {
                li { +"Area: 15.0" }
                li { +"Perimeter: 16.0" }
            }
            li { inlineCode("Rectangle(-5.0, 3.0)"); +" should throw an error" }
        }

//        p {
//            em {
//                +"Playground $ARROW_DOWN"
//            }
//        }
    }
)

object ClassesAndObjectsExercise3Slide : Slide(
    header = "Exercise 3: Triangle with Secondary Constructor",
    summary = {
        +"Create a Triangle class with both primary and secondary constructors."
    },
    content = {
        p {
            +"Create a "
            inlineCode("Triangle")
            +" class with three sides and support for equilateral triangles."
        }

        p { strong { +"Task:" } }
        ul {
            li { +"Create primary constructor with: "; inlineCode("sideA: Double, sideB: Double, sideC: Double") }
            li { +"Add a secondary constructor "; inlineCode("constructor(side: Double)"); +" for equilateral triangles" }
            li { +"The secondary constructor should call the primary with "; inlineCode("side, side, side") }
            li { +"Add method "; inlineCode("perimeter()"); +" that returns sideA + sideB + sideC" }
        }

        p { strong { +"Expected Output:" } }
        ul {
            li { +"For "; inlineCode("Triangle(3.0, 4.0, 5.0)"); +": Perimeter = 12.0" }
            li { +"For "; inlineCode("Triangle(5.0)"); +" (equilateral): Perimeter = 15.0" }
        }

//        p {
//            em {
//                +"Playground $ARROW_DOWN"
//            }
//        }
    }
)
