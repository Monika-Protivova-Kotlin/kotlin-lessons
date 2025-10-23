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
    header = "Exercise: Canvas Space Management",
    content = {
        ol {
            li {
                strong {+"Implement Shape Validation." }
                +" In the "; inlineCode("Shape"); +" init block, validate dimensions and throw "; inlineCode("InvalidShapeException"); +" if:"
                ul {
                    li { +"Wrong number of dimensions for the shape type" }
                    li { +"Any dimension is negative or zero" }
                    li { +"For triangles: sides don't satisfy triangle inequality (a + b > c for all combinations)" }
                }
            }
            li {
                strong {+"Implement Canvas.addShape()." }
                +" Complete the "; inlineCode("addShape"); +" method to:"
                ol {
                    li { +"Create a "; inlineCode("Shape"); +" from the type and dimensions" }
                    li { +"Calculate the shape's area" }
                    li { +"Check if there's enough space on the canvas" }
                    li { +"If not enough space, throw "; inlineCode("InsufficientSpaceException") }
                    li { +"If enough space, add the shape to the list and reduce "; inlineCode("availableSpace") }
                }
            }
            li {
                strong {+"Implement Exception Handling in main()." }
                +" Complete the main function to:"
                ol {
                    li { +"Iterate through "; inlineCode("testShapes") }
                    li {
                        +"For each shape, use a "
                        inlineCode("try-catch-finally")
                        +" block:"
                        ul {
                            li { +"Try to call "; inlineCode("canvas.addShape(type, dimensions)") }
                            li { +"Catch "; inlineCode("InvalidShapeException"); +" and print the error message" }
                            li { +"Catch "; inlineCode("InsufficientSpaceException"); +" and print the error message" }
                            li { +"In the "; inlineCode("finally"); +" block (only on last iteration), print canvas status" }
                        }
                    }
                }
            }
        }
//        p { strong { +"1. Implement Shape Validation" } }
//        p { +"In the "; inlineCode("Shape"); +" init block, validate dimensions and throw "; inlineCode("InvalidShapeException"); +" if:" }
//        ul {
//            li { +"Wrong number of dimensions for the shape type" }
//            li { +"Any dimension is negative or zero" }
//            li { +"For triangles: sides don't satisfy triangle inequality (a + b > c for all combinations)" }
//        }
//
//        p { strong { +"2. Implement Canvas.addShape()" } }
//        p { +"Complete the "; inlineCode("addShape"); +" method to:" }
//        ol {
//            li { +"Create a "; inlineCode("Shape"); +" from the type and dimensions" }
//            li { +"Calculate the shape's area" }
//            li { +"Check if there's enough space on the canvas" }
//            li { +"If not enough space, throw "; inlineCode("InsufficientSpaceException") }
//            li { +"If enough space, add the shape to the list and reduce "; inlineCode("availableSpace") }
//        }
//
//        p { strong { +"3. Implement Exception Handling in main()" } }
//        p { +"Complete the main function to:" }
//        ol {
//            li { +"Iterate through "; inlineCode("testShapes") }
//            li {
//                +"For each shape, use a "
//                inlineCode("try-catch-finally")
//                +" block:"
//                ul {
//                    li { +"Try to call "; inlineCode("canvas.addShape(type, dimensions)") }
//                    li { +"Catch "; inlineCode("InvalidShapeException"); +" and print the error message" }
//                    li { +"Catch "; inlineCode("InsufficientSpaceException"); +" and print the error message" }
//                    li { +"In the "; inlineCode("finally"); +" block (only on last iteration), print canvas status" }
//                }
//            }
//        }
        p {
            strong {
                +"Use the provided template: "
                a(href = "https://pl.kotl.in/fPxfDMkEo") { +"Kotlin Playground" }
            }
        }
    }
)
