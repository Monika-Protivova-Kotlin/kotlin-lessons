package com.motycka.edu.content.topics.basics.collections

import kotlinx.html.*
import com.motycka.edu.model.ExerciseTopic
import com.motycka.edu.model.Slide
import com.motycka.edu.model.kotlinPlayground
import com.motycka.edu.model.highlight
import com.motycka.edu.model.inlineCode

object CollectionGroupingSortingExercisesTopic : ExerciseTopic(
    title = "Collection Grouping and Sorting Exercises",
    slides = listOf(
        GroupingSortingExerciseSlide
    )
)

object GroupingSortingExerciseSlide : Slide(
    header = "Exercise #6: Grouping and Sorting - Shape Analysis",
    summary = {
        +"Analyze shapes by grouping results by type and calculating statistics."
    },
    content = {
        p {
            +"Let's create a comprehensive analysis that shows statistics for each shape type!"
        }
        p {
            strong { highlight("Part 1: Group by Shape Type") }
        }
        p {
            +"Using your flattened list of "; inlineCode("ComparisonResult"); " objects from Exercise #4:"
        }
        ol {
            li {
                +"Use "; inlineCode("groupBy"); " to group all comparison results by the larger shape's type"
            }
            li {
                +"This will give you a "; inlineCode("Map<ShapeType, List<ComparisonResult>>")
            }
        }
        kotlinPlayground(
            code = """
                val resultsByType: Map<ShapeType, List<ComparisonResult>> =
                    comparisonResults.groupBy { /* group by larger shape's type */ }
            """.trimIndent(),
            executable = false
        )
        br()
        p {
            strong { highlight("Part 2: Calculate Statistics") }
        }
        p {
            +"For each shape type, calculate:"
        }
        ul {
            li { +"Total number of times it was larger ("; inlineCode("count()"); ")" }
            li { +"Average area of the larger shapes" }
        }
        p {
            +"Create a "; inlineCode("ShapeStats"); " data class:"
        }
        kotlinPlayground(
            code = """
                data class ShapeStats(
                    val shapeType: ShapeType,
                    val timesLarger: Int,
                    val averageArea: Double
                )
            """.trimIndent(),
            executable = false
        )
        p {
            +"Use "; inlineCode("map"); " on the grouped results to transform each entry into "
            inlineCode("ShapeStats"); "."
        }
        br()
        p {
            strong { highlight("Part 3: Sort by Performance") }
        }
        p {
            +"Create a sorted ranking using "; inlineCode("sortedByDescending"); ":"
        }
        ol {
            li { +"Sort the list of "; inlineCode("ShapeStats"); " by times larger (descending)" }
            li { +"Then create a secondary sort by average area (descending)" }
        }
        kotlinPlayground(
            code = """
                val ranking: List<ShapeStats> = stats
                    .sortedByDescending { it.timesLarger }
                    .sortedByDescending { it.averageArea }

                // Or use sortedWith for multiple criteria:
                val ranking2 = stats.sortedWith(
                    compareByDescending<ShapeStats> { it.timesLarger }
                        .thenByDescending { it.averageArea }
                )
            """.trimIndent(),
            executable = false
        )
        br()
        p {
            strong { highlight("Expected Output Structure:") }
        }
        kotlinPlayground(
            code = """
                // Example output:
                listOf(
                    ShapeStats(ShapeType.CIRCLE, timesLarger = 5, averageArea = 78.5),
                    ShapeStats(ShapeType.SQUARE, timesLarger = 3, averageArea = 25.0),
                    ShapeStats(ShapeType.RECTANGLE, timesLarger = 2, averageArea = 18.0)
                )
            """.trimIndent(),
            executable = false
        )
        blockQuote {
            em {
                +"This exercise combines multiple collection operations: groupBy for categorization, "
                +"map for transformation, and sortedBy for ordering. These patterns are extremely common "
                +"in real-world data processing tasks!"
            }
        }
    },
    fontSize = "85%"
)
