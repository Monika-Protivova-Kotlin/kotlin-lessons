package com.motycka.edu.content.topics.basics

import com.motycka.edu.model.highlight
import com.motycka.edu.builders.*
import kotlinx.html.*
import com.motycka.edu.model.Topic
import com.motycka.edu.model.Slide
import com.motycka.edu.model.inlineCode
import com.motycka.edu.model.kotlinPlayground
import com.motycka.edu.model.twoColumns
import com.motycka.edu.model.highlight

object RangesAndProgressionsTopic : Topic(
    title = "Ranges and Progressions",
    subtitle = "Additional Data Types",
    slides = listOf(
        RangesAndProgressionsIntroSlide,
        RangesSlide,
        ProgressionsSlide,
    )
)

object PairsAndTriplesTopic : Topic(
    title = "Pairs and Triples",
    subtitle = "Additional Data Types",
    slides = listOf(
        PairsAndTriplesSlide,
        PairSlide,
        TripleSlide
    )
)

object RangesAndProgressionsIntroSlide : Slide(
    header = "Additional Data Types",
    summary = {
        em { +"Ranges and Progressions" }; br
        em { +"Pairs and Triples" }; br
    },
    content = {
        p {
            +"Kotlin provides additional data types beyond primitive types and collections that help "
            +"make code more expressive and concise."
        }
        p {
            +"These include:"
        }
        highlightOrderedList(
            "Ranges" to {
                ul {
                    li { +"Represent sequences of values with a defined start and end." }
                    li { +"Can be used in control structures like if, when, and for loops." }
                    li { +"Support arithmetic operations like "; highlight("sum()"); +", "; highlight("average()"); +", "; highlight("count()"); +", etc." }
                }
            },
            "Progressions" to {
                ul {
                    li { +"Represent ordered sequences of values with a common difference (step)." }
                    li { +"Can be used in for loops and other control structures." }
                    li { +"Support arithmetic operations like "; highlight("sum()"); +", "; highlight("average()"); +", "; highlight("count()"); +", etc." }
                }
            }
        )
    }
)

object RangesSlide : Slide(
    header = "Ranges",
    summary = {
        +"Ranges are used to represent sequences of values."
    },
    content = {
        p {
            +"Range represents an ordered set of values with a defined start and end. "
            +"By default, it increments by 1 at each step."
        }
        kotlinPlayground(
            code = """
                val intRange: IntRange = 1..10
                val longRange: LongRange = 1L..100L
                val charRange: CharRange = 'a'..'z'
            """,
            executable = false
        )
        p {
            +"Ranges can be used in "; inlineCode("if"); +", "; inlineCode("when"); +" statements, and other control structures."
        }
        kotlinPlayground(
            code = """
                when (intNumber) {
                    in 0..10 -> println("low")
                    in 11..20 -> println("medium")
                    in 21..30 -> println("high")
                    else -> println("out of range")
                }
            """,
            executable = false
        )
        p {
            +"Arithmetic operations can be performed on ranges, such as "; inlineCode("sum()"); +", "; inlineCode("average()"); +", "; inlineCode("count()"); +", and "; inlineCode("contains()"); +"."
        }
        kotlinPlayground(
            code = """
                val sum = intRange.sum()
                val average = intRange.average()
                val count = intRange.count()
                val max = intRange.maxOrNull()
                val min = intRange.minOrNull()
            """,
            executable = false
        )
    }
)

object ProgressionsSlide : Slide(
    header = "Progressions",
    summary = {
        +"Progressions are ordered sequences of values with a common difference."
    },
    content = {
        p {
            +"The ranges of integral types, such as "; inlineCode("Int"); +", "
            inlineCode("Long"); +", and "; inlineCode("Char"); +", "
            +"can be treated as arithmetic progressions, defined by "
            inlineCode("IntProgression"); +", "
            inlineCode("LongProgression"); +" and "
            inlineCode("CharProgression"); +" types."

            kotlinPlayground(
                code = """
                val intProgression: IntProgression = 1..10 step 2
            """,
                executable = false
            )
        }
        p {
            +"Progressions have three essential properties:"
            ul {
                li { inlineCode("first"); +" - the starting value of the progression." }
                li { inlineCode("last"); +" - the ending value of the progression." }
                li { inlineCode("step"); +" - a non-zero step which is a difference between consecutive values in the progression (positive or negative)" }
            }
            kotlinPlayground(
                code = """
                        val first = intProgression.first  // 1
                        val last = intProgression.last    // 9
                        val step = intProgression.step    // 2
                    """,
                executable = false
            )
        }
        twoColumns(
            left = {
                +"Progressions can be used in "; inlineCode("for"); +" loop with a step."
                kotlinPlayground(
                    code = """
                        for (index in 0..100 step 10) { /* ... */ }
                    """,
                    executable = false
                )
            },
            right = {
                +"Or use default step value of 1."
                kotlinPlayground(
                    code = """
                        for (index in 0..100) { /* ... */ }
                    """,
                    executable = false
                )
            }
        )
        p {
            +"Arithmetic operations can be performed on progressions as well."
        }
    }
)

object PairsAndTriplesSlide : Slide(
    header = "Pairs and Triples",
    summary = {
        +"Kotlin SDK provides two classes to represent pairs and triples of values."
    },
    content = {
        p {
            +"While you can implement your own classes to represent pairs and triples of values, it "
            +"is convenient to use the standard library classes "; inlineCode("Pair"); +" and "; inlineCode("Triple")
            +" as they already provide useful functionality."
        }
        p {
            +"Pairs and triples are useful when you need to return two or three values from a function "
            +"and are quite commonly used when working with collections."
        }
    }
)

object PairSlide : Slide(
    header = "Pair",
    summary = {
        inlineCode("Pair"); +" is a class that holds two values of same or different types."
    },
    content = {
        kotlinPlayground(
            code = """
                val duel: Pair<String, String> = Pair("Luke Skywalker", "Darth Vader")
            """,
            executable = false
        )
        p {
            +"As usual, type can be omitted if it can be inferred from the values."
        }
        kotlinPlayground(
            code = """
                val firstInEpisode = Pair("Luke Skywalker", 4)
            """,
            executable = false
        )
        p {
            +"You can access values of a pair by using the "; inlineCode("first"); +" and "; inlineCode("second"); +" properties."
        }
        kotlinPlayground(
            code = """
                val good = duel.first
                val evil = duel.second
            """,
            executable = false
        )
        p {
            +"Or you can use destructuring declarations to extract values from a pair."
        }
        kotlinPlayground(
            code = """
                val (good, evil) = duel
            """,
            executable = false
        )
        p {
            +"There is alternative (and preferred) syntax for creating pairs using the "; inlineCode("to"); " function."
        }
        kotlinPlayground(
            code = """
                val duel = "Luke Skywalker" to "Darth Vader"
            """,
            executable = false
        )
    }
)

object TripleSlide : Slide(
    header = "Triple",
    summary = {
        inlineCode("Triple"); " is a class that holds three values of same or different types."
    },
    content = {
        kotlinPlayground(
            code = """
                val firstDuelInEpisode = Triple("Luke Skywalker", "Darth Vader", 4)
            """,
            executable = false
        )
        p {
            +"You can access values of a triple by using the "; inlineCode("first"); ", "; inlineCode("second"); " and "; inlineCode("third"); " properties."
        }
        kotlinPlayground(
            code = """
                val good = firstDuelInEpisode.first
                val evil = firstDuelInEpisode.second
                val episode = firstDuelInEpisode.third
            """,
            executable = false
        )
        p {
            +"Or you can use destructuring declarations to extract values from a triple."
        }
        kotlinPlayground(
            code = """
                val (good, evil, episode) = firstDuelInEpisode
            """,
            executable = false
        )
        p {
            +"There is no alternative syntax for creating triples using the "; inlineCode("to"); " function. "
            +"In this example, it would result in "; inlineCode("Pair<Pair<String, String>, Int>"); "."
        }
        kotlinPlayground(
            code = """
                val firstDuelInEpisode: Pair<Pair<String, String>, Int> = "Luke Skywalker" to "Darth Vader" to 4
            """,
            executable = false
        )
    }
)
