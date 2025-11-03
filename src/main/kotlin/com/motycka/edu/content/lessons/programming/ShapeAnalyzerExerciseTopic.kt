package com.motycka.edu.content.lessons.programming

import com.motycka.edu.model.Slide
import com.motycka.edu.model.Topic
import com.motycka.edu.model.inlineCode
import kotlinx.html.*

object ShapeAnalyzerExercise1Topic : Topic(
    title = "Exercise: Shape Dimension Analyzer",
    slides = listOf(
        ShapeAnalyzerExercise1Slide
    )
)

object ShapeAnalyzerExercise2Topic : Topic(
    title = "Exercise: Shape Analyzer with Functions",
    slides = listOf(
        ShapeAnalyzerExercise2Slide
    )
)

object ShapeAnalyzerExercise1Slide : Slide(
    header = "Exercise 1: Shape Dimension Analyzer",
    summary = {
        +"Determine 2D and 3D shapes from dimensions array, then calculate area and volume."
    },
    content = {
        p {
            +"Write a program that takes dimensions as command-line arguments and interprets them as both 2D and 3D shapes."
        }

        p { strong { +"Task:" } }
        ol {
            li { +"Parse command-line arguments into a dimensions array" }
            li { +"Determine 2D shape based on array size (1=circle, 2=rectangle, 3=triangle)" }
            li { +"Calculate area for the 2D shape" }
            li { +"Determine 3D shape based on array size (1=sphere, 2=cylinder, 3=box)" }
            li { +"Calculate volume for the 3D shape" }
        }

        p { strong { +"Formulas:" } }
        ul {
            li {
                strong { +"Area:" }
                +" Circle: π×r², Rectangle: w×h, Triangle: Heron's formula"
            }
            li {
                strong { +"Volume:" }
                +" Sphere: (4/3)×π×r³, Cylinder: π×r²×h, Box: l×w×h"
            }
        }
        p {
            strong {
                + "You can use "
                a(href = "https://pl.kotl.in/a91gCQl5d") { +"Kotlin Playground" }
            }
        }
    }
)

object ShapeAnalyzerExercise2Slide : Slide(
    header = "Exercise 2: Shape Analyzer with Functions",
    summary = {
        +"Refactor Exercise 1 by extracting logic into reusable functions."
    },
    content = {
        p {
            +"Build on Exercise 1 by organizing code into functions. This improves code reusability and readability."
        }

        p { strong { +"Task:" } }
        ol {
            li { +"Create "; inlineCode("determineShape2D)"); +" function" }
            li { +"Create "; inlineCode("determineShape3D)"); +" function" }
            li { +"Create "; inlineCode("calculateArea)"); +" function" }
            li { +"Create "; inlineCode("calculateVolume)"); +" function" }
            li { +"Update "; inlineCode("main)"); +" to use these functions" }
        }

        p { strong { +"Function Signatures:" } }
        ul {
            li { inlineCode("fun determineShape2D(dimensions: Array<Double>): String") }
            li { inlineCode("fun determineShape3D(dimensions: Array<Double>): String") }
            li { inlineCode("fun calculateArea(shape: String, dimensions: Array<Double>): Double") }
            li { inlineCode("fun calculateVolume(shape: String, dimensions: Array<Double>): Double") }
        }
        p {
            strong {
                + "You can use "
                a(href = "https://pl.kotl.in/kGlf2IJ9H") { +"Kotlin Playground" }
            }
        }
    }
)
