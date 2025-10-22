package com.motycka.edu.content.topics.basics.functions

import kotlinx.html.*
import com.motycka.edu.model.ExerciseTopic
import com.motycka.edu.model.Slide
import com.motycka.edu.model.inlineCode
import com.motycka.edu.model.kotlinPlayground

object ScopeFunctionsExerciseTopic : ExerciseTopic(
    title = "Exercise: Scope Functions",
    slides = listOf(
        ScopeFunctionsExercise1Slide,
        ScopeFunctionsExercise2Slide,
        ScopeFunctionsExercise3Slide
    )
)

object ScopeFunctionsExercise1Slide : Slide(
    header = "Exercise 1: Circle with apply",
    summary = {
        +"Use "; inlineCode("apply"); +" to configure a Circle with a custom color property."
    },
    content = {
        p {
            +"Create a "
            inlineCode("Circle")
            +" class and use "
            inlineCode("apply")
            +" to configure its properties."
        }

        p { strong { +"Given Class:" } }
        kotlinPlayground(
            code = """
            data class Circle(
                val radius: Double,
                var color: String = "black",
                var filled: Boolean = false
            ) {
                fun area(): Double = Math.PI * radius * radius
            }
            """,
            executable = false
        )

        p { strong { +"Task:" } }
        ul {
            li { +"Create a "; inlineCode("Circle"); +" with radius 5.0" }
            li { +"Use "; inlineCode("apply"); +" to set color to \"red\" and filled to true" }
            li { +"Print the circle and its area" }
        }

        p { strong { +"Expected Output:" } }
        ul {
            li { inlineCode("Circle(radius=5.0, color=red, filled=true)") }
            li { +"Area: ~78.54" }
        }
        p {
            strong {
                +"You can use "
                a(href = "https://pl.kotl.in/Ebe-0R88d") { +"Kotlin Playground" }
            }
        }
    }
)

object ScopeFunctionsExercise2Slide : Slide(
    header = "Exercise 2: Rectangle with let and also",
    summary = {
        +"Use "; inlineCode("let"); +" and "; inlineCode("also"); +" for calculations and logging."
    },
    content = {
        p {
            +"Create a "
            inlineCode("Rectangle")
            +" and use scope functions for transformations and side effects."
        }

        p { strong { +"Given Class:" } }
        kotlinPlayground(
            code = """
            data class Rectangle(val width: Double, val height: Double) {
                fun area(): Double = width * height
                fun perimeter(): Double = 2 * (width + height)
            }
            """,
            executable = false
        )

        p { strong { +"Task:" } }
        ul {
            li { +"Create a "; inlineCode("Rectangle(4.0, 6.0)") }
            li { +"Use "; inlineCode("let"); +" to calculate and return a formatted string: \"Area: X, Perimeter: Y\"" }
            li { +"Use "; inlineCode("also"); +" to print \"Processing rectangle...\" before the calculation" }
            li { +"Chain these functions together" }
        }

        p { strong { +"Expected Output:" } }
        ul {
            li { +"Processing rectangle..." }
            li { +"Area: 24.0, Perimeter: 20.0" }
        }

        p { strong { +"Example structure:" } }
        kotlinPlayground(
            code = """
            val result = Rectangle(4.0, 6.0)
                .also { println("Processing rectangle...") }
                .let { "Area: ${DOLLAR}{it.area()}, Perimeter: ${DOLLAR}{it.perimeter()}" }
            """,
            executable = false
        )
        p {
            strong {
                +"You can use "
                a(href = "https://pl.kotl.in/Ebe-0R88d") { +"Kotlin Playground" }
            }
        }
    }
)

object ScopeFunctionsExercise3Slide : Slide(
    header = "Exercise 3: Triangle with run and with",
    summary = {
        +"Use "; inlineCode("run"); +" and "; inlineCode("with"); +" for complex shape calculations."
    },
    content = {
        p {
            +"Create a "
            inlineCode("Triangle")
            +" class and use "
            inlineCode("run")
            +" and "
            inlineCode("with")
            +" for calculations."
        }

        p { strong { +"Given Class:" } }
        kotlinPlayground(
            code = """
            data class Triangle(val a: Double, val b: Double, val c: Double) {
                fun perimeter(): Double = a + b + c
            
                // Heron's formula for area
                fun area(): Double {
                    val s = perimeter() / 2
                    return Math.sqrt(s * (s - a) * (s - b) * (s - c))
                }
            
                fun isValid(): Boolean = a + b > c && b + c > a && a + c > b
            }
            """,
            executable = false
        )

        p { strong { +"Task 1: Use run" } }
        ul {
            li { +"Create a "; inlineCode("Triangle(3.0, 4.0, 5.0)") }
            li { +"Use "; inlineCode("run"); +" to check if it's valid and return \"Valid triangle with area: X\" or \"Invalid triangle\"" }
        }

        p { strong { +"Task 2: Use with" } }
        ul {
            li { +"Given the same triangle, use "; inlineCode("with"); +" to calculate and print:" }
            ul {
                li { +"Perimeter" }
                li { +"Area" }
                li { +"Whether it's a right triangle (a² + b² = c² for sides 3, 4, 5)" }
            }
        }

        p { strong { +"Expected Output:" } }
        ul {
            li { +"Task 1: \"Valid triangle with area: 6.0\"" }
            li { +"Task 2:" }
            ul {
                li { +"Perimeter: 12.0" }
                li { +"Area: 6.0" }
                li { +"Is right triangle: true" }
            }
        }
        p {
            strong {
                +"You can use "
                a(href = "https://pl.kotl.in/Ebe-0R88d") { +"Kotlin Playground" }
            }
        }
    }
)
