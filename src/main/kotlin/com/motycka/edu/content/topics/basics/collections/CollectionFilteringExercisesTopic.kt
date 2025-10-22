package com.motycka.edu.content.topics.basics.collections

import kotlinx.html.*
import com.motycka.edu.model.ExerciseTopic
import com.motycka.edu.model.Slide
import com.motycka.edu.model.kotlinPlayground
import com.motycka.edu.model.highlight
import com.motycka.edu.model.inlineCode

object CollectionFilteringExercisesTopic : ExerciseTopic(
    title = "Collection Filtering Exercises",
    slides = listOf(
        FilteringExerciseSlide
    )
)

object FilteringExerciseSlide : Slide(
    header = "Exercise #5: Filtering by Shape Type",
    summary = {
        +"Filter comparison results based on shape types using "; inlineCode("filter"); "."
    },
    content = {
        p {
            +"Now that you have a flattened list of all comparison results from Exercise #4, let's analyze them by filtering."
        }
        p {
            strong { highlight("Task:") }
            +" Use the "; inlineCode("filter"); " function to separate comparisons into different groups:"
        }
        ol {
            li {
                strong { +"Circular comparisons" }
                +" - where "; strong { +"either shape" }; " is a "
                inlineCode("CIRCLE")
            }
            li {
                strong { +"Rectangular comparisons" }
                +" - where "; strong { +"both shapes" }; " are "
                inlineCode("SQUARE"); " or "; inlineCode("RECTANGLE")
            }
        }
        br()
        p {
            +"Create two separate lists:"
        }
        kotlinPlayground(
            code = """
                val circularComparisons: List<ComparisonResult> = // ... your filter here
                val rectangularComparisons: List<ComparisonResult> = // ... your filter here
            """.trimIndent(),
            executable = false
        )
        br()
        p {
            strong { highlight("Additional Challenge:") }
            +" Create a third list containing only comparisons where:"
        }
        ul {
            li { +"The larger shape is a "; inlineCode("CIRCLE") }
            li { strong { +"AND" } }
            li { +"The scale factor is 2.0" }
        }
        kotlinPlayground(
            code = """
                val largeCircles: List<ComparisonResult> = // ... your filter here
            """.trimIndent(),
            executable = false
        )
        blockQuote {
            em {
                +"Hint: You can chain multiple conditions in a filter using && (AND) or || (OR). "
                +"For checking if a type is in a set, you can use: "
                inlineCode("type in setOf(ShapeType.SQUARE, ShapeType.RECTANGLE)")
            }
        }
        br()
        p {
            +"This exercise demonstrates how "; inlineCode("filter"); " allows you to extract subsets of data "
            +"based on complex conditions, which is essential for data analysis tasks."
        }
    }
)
