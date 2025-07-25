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
    header = "Exercise #6: Grouping and Sorting - Scoreboard",
    summary = {
        +"Create a scoreboard by grouping results by fraction and calculating win statistics."
    },
    content = {
        p {
            +"Let's create a comprehensive scoreboard that shows how each fraction is performing!"
        }
        p {
            strong { highlight("Part 1: Group by Fraction") }
        }
        p {
            +"Using your flattened list of "; inlineCode("MatchResult"); " objects from Exercise #4:"
        }
        ol {
            li {
                +"Use "; inlineCode("groupBy"); " to group all match results by the winner's fraction"
            }
            li {
                +"This will give you a "; inlineCode("Map<Fraction, List<MatchResult>>")
            }
        }
        kotlinPlayground(
            code = """
                val resultsByFraction: Map<Fraction, List<MatchResult>> =
                    matchResults.groupBy { /* group by winner's fraction */ }
            """.trimIndent(),
            executable = false
        )
        br()
        p {
            strong { highlight("Part 2: Calculate Statistics") }
        }
        p {
            +"For each fraction, calculate:"
        }
        ul {
            li { +"Total number of wins ("; inlineCode("count()"); ")" }
            li { +"Percentage of matches won" }
        }
        p {
            +"Create a "; inlineCode("FractionStats"); " data class:"
        }
        kotlinPlayground(
            code = """
                data class FractionStats(
                    val fraction: Fraction,
                    val totalWins: Int,
                    val winPercentage: Double
                )
            """.trimIndent(),
            executable = false
        )
        p {
            +"Use "; inlineCode("map"); " on the grouped results to transform each entry into "
            inlineCode("FractionStats"); "."
        }
        br()
        p {
            strong { highlight("Part 3: Sort by Performance") }
        }
        p {
            +"Create a sorted scoreboard using "; inlineCode("sortedByDescending"); ":"
        }
        ol {
            li { +"Sort the list of "; inlineCode("FractionStats"); " by total wins (descending)" }
            li { +"Then create a secondary sort by win percentage (descending)" }
        }
        kotlinPlayground(
            code = """
                val scoreboard: List<FractionStats> = stats
                    .sortedByDescending { it.totalWins }
                    .sortedByDescending { it.winPercentage }

                // Or use sortedWith for multiple criteria:
                val scoreboard2 = stats.sortedWith(
                    compareByDescending<FractionStats> { it.totalWins }
                        .thenByDescending { it.winPercentage }
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
                    FractionStats(Fraction.JEDI, totalWins = 5, winPercentage = 55.5),
                    FractionStats(Fraction.SITH, totalWins = 3, winPercentage = 33.3),
                    FractionStats(Fraction.REBEL, totalWins = 1, winPercentage = 11.1)
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
