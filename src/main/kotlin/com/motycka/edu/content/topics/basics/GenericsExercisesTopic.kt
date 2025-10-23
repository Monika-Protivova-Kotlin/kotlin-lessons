package com.motycka.edu.content.topics.basics

import kotlinx.html.*
import com.motycka.edu.model.ExerciseTopic
import com.motycka.edu.model.Slide
import com.motycka.edu.model.Topic
import com.motycka.edu.model.inlineCode

object GenericsExercisesTopic : Topic(
    title = "Generics Exercises",
    subtitle = "Hands-on Practice",
    slides = listOf(
        GenericsExercise1Slide,
        GenericsExercise2Slide
    )
)

object GenericsExercise1Slide : Slide(
    header = "Part 1: Generic Shape Selector",
    summary = {
        +"Create a generic function that selects the greater of two shapes based on their area."
    },
    content = {
        p { strong { +"Task:" } }
        ul {
            li { +"Create function "; inlineCode("selectGreater<T : Shape2D>(shape1: T, shape2: T): T") }
            li { +"Use the "; strong { +"bounded type parameter" }; +" "; inlineCode("T : Shape2D"); +" to ensure shapes have "; inlineCode("area()"); +" method" }
            li { +"Return the shape with the larger area" }
            li { +"If areas are equal, return the first shape" }
        }
        p { strong { +"Test your function:" } }
        ul {
            li { +"Create a "; inlineCode("Circle(3.0)"); +" and a "; inlineCode("Rectangle(2.0, 4.0)") }
            li { +"Call "; inlineCode("selectGreater"); +" to find which shape has greater area" }
        }
        p {
            +"This demonstrates "
            strong { +"bounded generics" }
            +" where the type parameter must implement a specific interface."
        }
    }
)

object GenericsExercise2Slide : Slide(
    header = "Part 2: ComparableShape Interface and Triangle",
    summary = {
        +"Create a generic interface for comparing shapes and implement it in a Triangle class."
    },
    content = {
        p { strong { +"Task 1: Create ComparableShape Interface" } }
        ul {
            li { +"Create generic interface "; inlineCode("ComparableShape<T>"); +" with methods:" }
            ul {
                li { inlineCode("fun isEqual(other: T): Boolean") }
                li { inlineCode("fun isGreater(other: T): Boolean") }
                li { inlineCode("fun isSmaller(other: T): Boolean") }
            }
        }
        p { strong { +"Task 2: Create Triangle Class" } }
        ul {
            li { +"Create class "; inlineCode("Triangle"); +" with properties "; inlineCode("a: Double, b: Double, c: Double"); +" (three sides)" }
            li { +"Implement "; strong { +"both" }; +" "; inlineCode("Shape2D"); +" and "; inlineCode("ComparableShape<Triangle>"); +" interfaces" }
            li { inlineCode("area()"); +" - use Heron's formula: "; inlineCode("s = (a+b+c)/2"); +", then "; inlineCode("sqrt(s*(s-a)*(s-b)*(s-c))") }
            li { inlineCode("perimeter()"); +" - returns "; inlineCode("a + b + c") }
            li { +"Implement comparison methods by comparing "; strong { +"areas" }; +" of triangles" }
        }
        p { strong { +"Test your implementation:" } }
        ul {
            li { +"Create "; inlineCode("Triangle(2.0, 4.0, 3.0)"); +" and "; inlineCode("Triangle(3.0, 3.0, 3.0)") }
            li { +"Print appropriate messages based on comparison results" }
        }
        p {
            +"This demonstrates "
            strong { +"generic interfaces" }
            +" where a class can specify itself as the type parameter."
        }
    }
)
