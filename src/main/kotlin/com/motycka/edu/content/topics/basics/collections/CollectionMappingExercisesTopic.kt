package com.motycka.edu.content.topics.basics.collections

import kotlinx.html.*
import com.motycka.edu.model.ExerciseTopic
import com.motycka.edu.model.Slide
import com.motycka.edu.model.kotlinPlayground
import com.motycka.edu.model.highlight
import com.motycka.edu.model.inlineCode

object CollectionMappingExercisesTopic : ExerciseTopic(
    title = "Collection Mapping Exercises",
    slides = listOf(
        MappingExercise1Slide,
        MappingExercise2Slide,
        MappingExercise3Slide,
        MappingExercise4Slide
    )
)

object MappingExercise1Slide : Slide(
    header = "Exercise #1: Mapping",
    summary = {
        +"Convert character names to StarWarsCharacter objects using "; inlineCode("map"); "."
    },
    content = {
        p {
            +"First, let's define our data model:"
        }
        kotlinPlayground(
            code = """
                enum class Fraction {
                    JEDI,
                    REBEL,
                    SITH,
                    EMPIRE,
                    BOUNTY_HUNTER
                }

                data class StarWarsCharacter(
                    val name: String,
                    val fraction: Fraction
                )
            """.trimIndent(),
            executable = false
        )
        p {
            +"Now, given a list of character names:"
        }
        kotlinPlayground(
            code = """
                val characterNames = listOf(
                    "Luke Skywalker",
                    "Darth Vader",
                    "Han Solo",
                    "Boba Fett",
                    "Princess Leia",
                    "Emperor Palpatine"
                )
            """.trimIndent(),
            executable = false
        )
        p {
            strong { highlight("Task:") }
            +" Use the "; inlineCode("map"); " function to convert each character name to a "
            inlineCode("StarWarsCharacter"); " object. You'll need to determine the fraction for each character:"
        }
        ul {
            li { +"Luke Skywalker → JEDI" }
            li { +"Darth Vader → SITH" }
            li { +"Han Solo → REBEL" }
            li { +"Boba Fett → BOUNTY_HUNTER" }
            li { +"Princess Leia → REBEL" }
            li { +"Emperor Palpatine → SITH" }
        }
        p {
            +"Your result should be a "; inlineCode("List<StarWarsCharacter>"); "."
        }
        blockQuote {
            em { +"Hint: You can use a when expression inside the map function to determine the fraction based on the name." }
        }
    }
)

object MappingExercise2Slide : Slide(
    header = "Exercise #2: Mapping with Index",
    summary = {
        +"Pair characters using "; inlineCode("mapIndexed"); " to create match-ups."
    },
    content = {
        p {
            +"Now that you have a list of "; inlineCode("StarWarsCharacter"); " objects, let's create some duels!"
        }
        p {
            strong { highlight("Task:") }
            +" Use "; inlineCode("mapIndexed"); " to pair up characters in a list. Each even-indexed character (0, 2, 4...) "
            +"should be paired with the next odd-indexed character (1, 3, 5...)."
        }
        p {
            +"For example, if you have the characters from Exercise #1:"
        }
        ul {
            li { +"Index 0 (Luke Skywalker) pairs with Index 1 (Darth Vader)" }
            li { +"Index 2 (Han Solo) pairs with Index 3 (Boba Fett)" }
            li { +"Index 4 (Princess Leia) pairs with Index 5 (Emperor Palpatine)" }
        }
        p {
            +"Create a "; inlineCode("Pair<StarWarsCharacter, StarWarsCharacter>"); " for each match-up."
        }
        kotlinPlayground(
            code = """
                // Your result should look like:
                val duels: List<Pair<StarWarsCharacter, StarWarsCharacter>> = listOf(
                    StarWarsCharacter("Luke Skywalker", Fraction.JEDI) to
                        StarWarsCharacter("Darth Vader", Fraction.SITH),
                    StarWarsCharacter("Han Solo", Fraction.REBEL) to
                        StarWarsCharacter("Boba Fett", Fraction.BOUNTY_HUNTER),
                    // ... etc
                )
            """.trimIndent(),
            executable = false
        )
        blockQuote {
            em {
                +"Hint: Use mapIndexed and filter for even indices, then create pairs by accessing the element at index + 1. "
                +"Or use chunked(2) to split the list into pairs, then map each chunk to a Pair."
            }
        }
    }
)

object MappingExercise3Slide : Slide(
    header = "Exercise #3: Nested Mapping",
    summary = {
        +"Create multiple matches per character pair with nested data structures."
    },
    content = {
        p {
            +"Let's make things more interesting! Each character pair should fight "
            strong { +"3 matches" }
            +", and we need to track the results."
        }
        p {
            +"First, define a "; inlineCode("MatchResult"); " data class:"
        }
        kotlinPlayground(
            code = """
                data class MatchResult(
                    val matchNumber: Int,
                    val fighter1: StarWarsCharacter,
                    val fighter2: StarWarsCharacter,
                    val winner: StarWarsCharacter
                )
            """.trimIndent(),
            executable = false
        )
        p {
            strong { highlight("Task:") }
            +" For each pair of characters from Exercise #2, create "; strong { +"3 match results" }; +"."
        }
        p {
            +"For determining the winner, use this logic:"
        }
        ul {
            li { +"Match 1: First character wins" }
            li { +"Match 2: Second character wins" }
            li { +"Match 3: First character wins (2-1 overall)" }
        }
        p {
            +"Your result should be: "; inlineCode("List<List<MatchResult>>")
        }
        blockQuote {
            em {
                +"Hint: Use map on your pairs, and for each pair, create a list of 3 MatchResult objects. "
                +"You can use (1..3).map { } inside your outer map to create the 3 matches."
            }
        }
    }
)

object MappingExercise4Slide : Slide(
    header = "Exercise #4: Flattening with flatMap",
    summary = {
        +"Flatten the nested match results using "; inlineCode("flatMap"); "."
    },
    content = {
        p {
            +"From Exercise #3, you have a "; inlineCode("List<List<MatchResult>>"); " structure."
            +" This nested structure isn't very convenient to work with."
        }
        p {
            strong { highlight("Task:") }
            +" Use "; inlineCode("flatMap"); " to flatten your nested list structure "
            +"from "; inlineCode("List<List<MatchResult>>"); " into a single "
            inlineCode("List<MatchResult>"); "."
        }
        p {
            +"The difference between "; inlineCode("map"); " and "; inlineCode("flatMap"); ":"
        }
        ul {
            li {
                inlineCode("map")
                +" transforms each element and keeps the structure: "
                inlineCode("List<A>"); " → "; inlineCode("List<B>")
            }
            li {
                inlineCode("flatMap")
                +" transforms each element to a collection and flattens: "
                inlineCode("List<A>"); " → "; inlineCode("List<List<B>>"); " → "
                inlineCode("List<B>")
            }
        }
        p {
            +"After flattening, you should have a single list containing all match results from all pairs."
        }
        kotlinPlayground(
            code = """
                // Example of flatMap:
                val nestedList = listOf(
                    listOf(1, 2, 3),
                    listOf(4, 5, 6),
                    listOf(7, 8, 9)
                )

                val flattened = nestedList.flatMap { it }
                // Result: [1, 2, 3, 4, 5, 6, 7, 8, 9]
            """.trimIndent(),
            executable = false
        )
        blockQuote {
            em {
                +"Hint: If you have the list from Exercise #3, you can simply call flatMap { it } to flatten one level, "
                +"or use flatten() which is equivalent to flatMap { it }."
            }
        }
    }
)
