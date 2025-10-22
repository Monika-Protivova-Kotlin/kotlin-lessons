package com.motycka.edu.content.topics.basics.collections

import kotlinx.html.*
import com.motycka.edu.model.ExerciseTopic
import com.motycka.edu.model.Slide
import com.motycka.edu.model.kotlinPlayground
import com.motycka.edu.model.highlight
import com.motycka.edu.model.inlineCode

object CollectionOtherTransformationsExercisesTopic : ExerciseTopic(
    title = "Other Collection Transformation Exercises",
    slides = listOf(
        OtherTransformationsExerciseSlide
    )
)

object OtherTransformationsExerciseSlide : Slide(
    header = "Exercise #7: Other Transformations",
    summary = {
        +"Practice using "; inlineCode("zip"); ", "; inlineCode("unzip"); ", "
        inlineCode("zipWithNext"); ", "; inlineCode("reduce"); ", and "; inlineCode("fold"); "."
    },
    content = {
        p {
            +"Let's explore some powerful transformation functions that are useful for combining and aggregating data."
        }

        h4 { strong { highlight("Part 1: zip - Combining Two Lists") } }
        p {
            +"Given two lists of shape statistics:"
        }
        kotlinPlayground(
            code = """
                val shapeTypes = listOf(ShapeType.CIRCLE, ShapeType.SQUARE, ShapeType.RECTANGLE)
                val averageAreas = listOf(78.5, 25.0, 18.0)
            """.trimIndent(),
            executable = false
        )
        p {
            strong { +"Task:" }
            +" Use "; inlineCode("zip"); " to combine these two lists into pairs. "
            +"Then use "; inlineCode("map"); " to convert each pair into a "
            inlineCode("ShapeStats"); " object."
        }
        kotlinPlayground(
            code = """
                val stats: List<Pair<ShapeType, Double>> = shapeTypes.zip(averageAreas)
                // Result: [(CIRCLE, 78.5), (SQUARE, 25.0), (RECTANGLE, 18.0)]
            """.trimIndent(),
            executable = false
        )
        br()

        h4 { strong { highlight("Part 2: unzip - Separating Pairs") } }
        p {
            +"Given a list of shape pairs from earlier exercises:"
        }
        kotlinPlayground(
            code = """
                val shapePairs: List<Pair<Shape, Shape>> = // ... from Exercise #2
            """.trimIndent(),
            executable = false
        )
        p {
            strong { +"Task:" }
            +" Use "; inlineCode("unzip"); " to separate the pairs into two lists: "
            +"one containing all first shapes, and one containing all second shapes."
        }
        kotlinPlayground(
            code = """
                val (firstShapes, secondShapes) = shapePairs.unzip()
                // firstShapes: List<Shape>
                // secondShapes: List<Shape>
            """.trimIndent(),
            executable = false
        )
        br()

        h4 { strong { highlight("Part 3: zipWithNext - Sequential Pairs") } }
        p {
            +"Given a list of comparison results sorted by scale factor:"
        }
        p {
            strong { +"Task:" }
            +" Use "; inlineCode("zipWithNext"); " to create pairs of consecutive comparisons. "
            +"This is useful for seeing how shapes grow relative to each other as scale increases."
        }
        kotlinPlayground(
            code = """
                val comparisons = listOf(comp1, comp2, comp3, comp4)
                val consecutiveComparisons = comparisons.zipWithNext()
                // Result: [(comp1, comp2), (comp2, comp3), (comp3, comp4)]
            """.trimIndent(),
            executable = false
        )
        br()

        h4 { strong { highlight("Part 4: reduce - Aggregating Values") } }
        p {
            +"Given a list of circle areas from different measurements:"
        }
        kotlinPlayground(
            code = """
                val circleAreas = listOf(78.5, 50.3, 95.0, 113.1, 63.6)
            """.trimIndent(),
            executable = false
        )
        p {
            strong { +"Task:" }
            +" Use "; inlineCode("reduce"); " to calculate the total area of all circles combined."
        }
        kotlinPlayground(
            code = """
                val totalCircleArea = circleAreas.reduce { acc, area -> acc + area }
                // Result: 400.5 (78.5 + 50.3 + 95.0 + 113.1 + 63.6)
            """.trimIndent(),
            executable = false
        )
        br()

        h4 { strong { highlight("Part 5: fold - Aggregating with Initial Value") } }
        p {
            +"Use "; inlineCode("fold"); " when you need to start with an initial value."
        }
        p {
            strong { +"Task:" }
            +" Calculate the total area where we start with a base area of 10.0:"
        }
        kotlinPlayground(
            code = """
                val totalWithBase = circleAreas.fold(10.0) { acc, area -> acc + area }
                // Result: 410.5 (10.0 + 78.5 + 50.3 + 95.0 + 113.1 + 63.6)
            """.trimIndent(),
            executable = false
        )
        br()
        p {
            strong { +"Advanced Challenge:" }
            +" Use "; inlineCode("fold"); " to create a summary string of all shape comparisons:"
        }
        kotlinPlayground(
            code = """
                val comparisons = listOf(comp1, comp2, comp3)
                val summary = comparisons.fold("Shape Comparison Summary:\\n") { acc, comp ->
                    acc + "Scale ${'$'}{comp.scaleFactor}: ${'$'}{comp.largerShape.type} is larger\\n"
                }
            """.trimIndent(),
            executable = false
        )
        blockQuote {
            em {
                +"These transformation functions are incredibly powerful for data processing. "
                +"zip and unzip are great for combining and separating related data, "
                +"while reduce and fold are essential for aggregation operations like summing, "
                +"finding maximums, or building complex accumulated results."
            }
        }
    },
    fontSize = "75%"
)
