package com.motycka.edu.content.topics.basics.collections

import kotlinx.html.*
import com.motycka.edu.model.Slide
import com.motycka.edu.model.Topic
import com.motycka.edu.model.inlineCode

object CollectionMappingExercisesTopic : Topic(
    title = "Collection Exercises",
    slides = listOf(
        MappingExercise1Slide
    )
)

object MappingExercise1Slide : Slide(
    header = "Exercise: Shape Analysis with Collections",
    summary = {
        +"Create a shape analysis program using map, filter, groupBy, and aggregation operations."
    },
    content = {
        p { strong { +"Part 1: Data Model" } }
        ol {
            li {
                +"Create a "
                inlineCode("ShapeType")
                +" enum with values: CIRCLE, SQUARE, RECTANGLE, TRIANGLE"
            }
            li {
                +"Create a "; inlineCode("Shape")
                +" data class with "
                inlineCode("type: ShapeType")
                +" and "
                inlineCode("dimensions: List<Double>")
            }
            li {
                +"Add an "; inlineCode("area()"); +" method to "; inlineCode("Shape")
                +" that calculates area based on "
                inlineCode("ShapeType")
                +"."
            }
        }

        p { strong { +"Part 2: Data Processing" } }
        p { +"Given this list of shape data:" }
        ol {
            li { +"Convert objects to shapes" }
            li { +"Filter shapes with area <= 10.0" }
            li { +"Calculate total area of all shapes" }
            li { +"Group shapes by type" }
        }
    }
)
