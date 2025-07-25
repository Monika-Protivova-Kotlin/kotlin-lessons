package com.motycka.edu.content.topics.basics

import kotlinx.html.*
import com.motycka.edu.model.Topic
import com.motycka.edu.model.Slide
import com.motycka.edu.model.kotlinPlayground
import com.motycka.edu.model.inlineCode
import com.motycka.edu.model.highlight
import com.motycka.edu.model.twoColumns

object SequencesTopic : Topic(
    title = "Sequences",
    slides = listOf(
        SequencesIntroSlide,
        CreatingSequencesSlide,
        SequencesVsCollectionsSlide,
        WhenToUseSequencesSlide
    )
)

object SequencesIntroSlide : Slide(
    header = "Sequences",
    summary = {
        +"Kotlin standard library provides a "
        span("highlight") { +"Sequence" }
        +" additional to collections."
    },
    content = {
        p {
            +"Unlike collections, sequences don't contain elements, they produce them while iterating. "
            +"This is useful when you need to perform multi-step operations on a collection."
        }
        p {
            highlight("Operations on collections are executed eagerly")
            +", meaning they perform all operations on all elements immediately."
            br
            highlight("Operations sequences are executed lazily")
            +", meaning they perform operations on elements only when needed."
        }
        p {
            +"This can be beneficial for large collections or when you need to perform complex operations on elements."
            br
            +"On the other hand, sequences may be less efficient for small collections or simple operations."
        }
        p {
            +"Sequences offer the similar functions as collections, such as "; inlineCode("forEach"); ", "; inlineCode("map"); ", "; inlineCode("filter"); ", etc."
        }
        p {
            +"The main difference is that when working with sequences, we distinguish between "; span("highlight") { +"intermediate and terminal operations" }; ", "
            +"where intermediate operations return a new sequence and terminal operations return a result."
        }
        p {
            +"What that means is that "; span("highlight") { +"when you call a terminal operation, all intermediate operations are executed and the collection is so called \"consumed\"" }; "."
        }
        blockQuote {
            strong { +"!!!" }; +"   "
            +"Keep this in mind because if you call a terminal operation prematurely, you may either end up with unexpected results, "
            +"or at least with a performance hit."
            +"   "; strong { +"!!!" }
        }
    }
)

object CreatingSequencesSlide : Slide(
    header = "Creating Sequences",
    content = {
        +"From elements:"; br
        kotlinPlayground(
            code = """
                val sequence = sequenceOf(1, 2, 3, 4, 5)
            """,
            executable = false
        )
        +"From an Iterable:"; br
        kotlinPlayground(
            code = """
                val sequence = listOf(1, 2, 3, 4, 5).asSequence()
            """,
            executable = false
        )
        +"From a function:"; br
        kotlinPlayground(
            code = """
                val sequence = generateSequence(1) { it + 1 }
            """,
            executable = false
        )
        +"From chunks:"; br
        kotlinPlayground(
            code = """
                val sequence = sequence {
                    for (i in 1..5) {
                        yield(i)
                    }
                }
            """,
            executable = false
        )
    }
)

object SequencesVsCollectionsSlide : Slide(
    header = "Sequences vs Collections",
    summary = {
        +"Understanding when to use sequences versus collections is important for performance."
    },
    content = {
        p {
            +"Here's an example that demonstrates the difference between eager (collections) and lazy (sequences) evaluation:"
        }
        kotlinPlayground(
            code = """
                val numbers = (1..1000000).toList()

                // Collection approach (eager evaluation)
                val resultCollection = numbers
                    .filter { it % 2 == 0 }  // Creates intermediate list
                    .map { it * it }         // Creates another intermediate list
                    .take(5)                 // Creates final list with 5 elements

                // Sequence approach (lazy evaluation)
                val resultSequence = numbers.asSequence()
                    .filter { it % 2 == 0 }  // No intermediate collection created
                    .map { it * it }         // No intermediate collection created
                    .take(5)                 // Only processes first 10 elements to get 5 results
                    .toList()                // Terminal operation - converts to list
            """,
            executable = false
        )
        p {
            +"In the collection approach, each operation processes all 1,000,000 elements and creates intermediate collections."
        }
        p {
            +"In the sequence approach, only the first 10 elements are processed (to find 5 even numbers), "
            +"and no intermediate collections are created until the terminal "; inlineCode("toList()"); +" operation."
        }
    }
)

object WhenToUseSequencesSlide : Slide(
    header = "Sequences vs Collections",
    summary = {
        +"When to use sequences and when to use collections?"
    },
    content = {
        twoColumns(
            left = {
                h4 { +"Use sequences for" }
                ul {
                    li { +"Large collections with multiple chained operations" }
                    li { +"When you need only a subset of the final result" }
                    li { +"Memory-constrained environments" }
                    li { +"Potentially infinite data streams" }
                }
            },
            right = {
                h4 { +"Use collections for" }
                ul {
                    li { +"Small collections" }
                    li { +"Single operations" }
                    li { +"When you need random access to elements" }
                    li { +"When simplicity is more important than performance" }
                }
            }
        )
    }
)
