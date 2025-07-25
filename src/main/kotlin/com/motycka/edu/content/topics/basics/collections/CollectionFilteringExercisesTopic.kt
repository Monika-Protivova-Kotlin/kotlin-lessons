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
    header = "Exercise #5: Filtering by Fraction",
    summary = {
        +"Filter match results based on character fractions using "; inlineCode("filter"); "."
    },
    content = {
        p {
            +"Now that you have a flattened list of all match results from Exercise #4, let's analyze them by filtering."
        }
        p {
            strong { highlight("Task:") }
            +" Use the "; inlineCode("filter"); " function to separate matches into two groups:"
        }
        ol {
            li {
                strong { +"Light Side matches" }
                +" - where "; strong { +"either fighter" }; " is from "
                inlineCode("JEDI"); " or "; inlineCode("REBEL"); " fractions"
            }
            li {
                strong { +"Dark Side matches" }
                +" - where "; strong { +"both fighters" }; " are from "
                inlineCode("SITH"); ", "; inlineCode("EMPIRE"); ", or "
                inlineCode("BOUNTY_HUNTER"); " fractions"
            }
        }
        br()
        p {
            +"Create two separate lists:"
        }
        kotlinPlayground(
            code = """
                val lightSideMatches: List<MatchResult> = // ... your filter here
                val darkSideMatches: List<MatchResult> = // ... your filter here
            """.trimIndent(),
            executable = false
        )
        br()
        p {
            strong { highlight("Additional Challenge:") }
            +" Create a third list containing only matches where:"
        }
        ul {
            li { +"The winner is from the "; inlineCode("JEDI"); " fraction" }
            li { strong { +"AND" } }
            li { +"The match number is 3 (the final match)" }
        }
        kotlinPlayground(
            code = """
                val jediVictories: List<MatchResult> = // ... your filter here
            """.trimIndent(),
            executable = false
        )
        blockQuote {
            em {
                +"Hint: You can chain multiple conditions in a filter using && (AND) or || (OR). "
                +"For checking if a fraction is in a set, you can use: "
                inlineCode("fraction in setOf(Fraction.JEDI, Fraction.REBEL)")
            }
        }
        br()
        p {
            +"This exercise demonstrates how "; inlineCode("filter"); " allows you to extract subsets of data "
            +"based on complex conditions, which is essential for data analysis tasks."
        }
    }
)
