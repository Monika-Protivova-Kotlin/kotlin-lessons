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
            +"Given two lists of match statistics:"
        }
        kotlinPlayground(
            code = """
                val fractions = listOf(Fraction.JEDI, Fraction.SITH, Fraction.REBEL)
                val totalWins = listOf(5, 3, 2)
            """.trimIndent(),
            executable = false
        )
        p {
            strong { +"Task:" }
            +" Use "; inlineCode("zip"); " to combine these two lists into pairs. "
            +"Then use "; inlineCode("map"); " to convert each pair into a "
            inlineCode("FractionStats"); " object."
        }
        kotlinPlayground(
            code = """
                val stats: List<Pair<Fraction, Int>> = fractions.zip(totalWins)
                // Result: [(JEDI, 5), (SITH, 3), (REBEL, 2)]
            """.trimIndent(),
            executable = false
        )
        br()

        h4 { strong { highlight("Part 2: unzip - Separating Pairs") } }
        p {
            +"Given a list of character pairs from earlier exercises:"
        }
        kotlinPlayground(
            code = """
                val characterPairs: List<Pair<StarWarsCharacter, StarWarsCharacter>> = // ... from Exercise #2
            """.trimIndent(),
            executable = false
        )
        p {
            strong { +"Task:" }
            +" Use "; inlineCode("unzip"); " to separate the pairs into two lists: "
            +"one containing all first characters, and one containing all second characters."
        }
        kotlinPlayground(
            code = """
                val (firstFighters, secondFighters) = characterPairs.unzip()
                // firstFighters: List<StarWarsCharacter>
                // secondFighters: List<StarWarsCharacter>
            """.trimIndent(),
            executable = false
        )
        br()

        h4 { strong { highlight("Part 3: zipWithNext - Sequential Pairs") } }
        p {
            +"Given a list of match results sorted by match number:"
        }
        p {
            strong { +"Task:" }
            +" Use "; inlineCode("zipWithNext"); " to create pairs of consecutive matches. "
            +"This is useful for comparing how the same fighters performed across multiple rounds."
        }
        kotlinPlayground(
            code = """
                val matchSequence = listOf(match1, match2, match3, match4)
                val consecutiveMatches = matchSequence.zipWithNext()
                // Result: [(match1, match2), (match2, match3), (match3, match4)]
            """.trimIndent(),
            executable = false
        )
        br()

        h4 { strong { highlight("Part 4: reduce - Aggregating Values") } }
        p {
            +"Given a list of total wins for a fraction across different tournaments:"
        }
        kotlinPlayground(
            code = """
                val jediWinsPerTournament = listOf(5, 7, 3, 8, 6)
            """.trimIndent(),
            executable = false
        )
        p {
            strong { +"Task:" }
            +" Use "; inlineCode("reduce"); " to calculate the total wins across all tournaments."
        }
        kotlinPlayground(
            code = """
                val totalJediWins = jediWinsPerTournament.reduce { acc, wins -> acc + wins }
                // Result: 29 (5 + 7 + 3 + 8 + 6)
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
            +" Calculate the total score where Jedi faction started the tournament with a 10-point bonus:"
        }
        kotlinPlayground(
            code = """
                val totalWithBonus = jediWinsPerTournament.fold(10) { acc, wins -> acc + wins }
                // Result: 39 (10 + 5 + 7 + 3 + 8 + 6)
            """.trimIndent(),
            executable = false
        )
        br()
        p {
            strong { +"Advanced Challenge:" }
            +" Use "; inlineCode("fold"); " to create a summary string of all match results:"
        }
        kotlinPlayground(
            code = """
                val matchResults = listOf(match1, match2, match3)
                val summary = matchResults.fold("Match Summary:\\n") { acc, match ->
                    acc + "Match ${'$'}{match.matchNumber}: ${'$'}{match.winner.name} wins\\n"
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
