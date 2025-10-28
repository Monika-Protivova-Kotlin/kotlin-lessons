package com.motycka.edu.content.topics.basics.concurrency

import com.motycka.edu.model.Slide
import com.motycka.edu.model.Topic
import com.motycka.edu.model.kotlinPlayground
import com.motycka.edu.model.highlight
import com.motycka.edu.model.inlineCode
import kotlinx.html.*

object ThreadExercisesTopic : Topic(
    title = "Thread Exercises",
    slides = listOf(
        ThreadBasicsExerciseSlide,
        RunnableExerciseSlide,
        SynchronizationExerciseSlide,
        AtomicVariablesExerciseSlide
    )
)

object ThreadBasicsExerciseSlide : Slide(
    header = "Exercise 1: Download Manager",
    summary = {
        +"Simulate downloading multiple files concurrently using threads."
    },
    content = {
        p {
            +"Create a simple download simulator that:"
            ul {
                li {
                    +"Simulates downloading 2-3 files concurrently using "
                    inlineCode("Thread")
                }
                li { +"Each file has a different size (number of chunks to download)" }
                li { +"Print progress for each file (e.g., \"File1: Downloaded chunk 1/5\")" }
                li {
                    +"Use "
                    inlineCode("Thread.sleep(100)")
                    +" to simulate download time per chunk"
                }
                li { +"Start all downloads and observe concurrent progress" }
            }
        }
        p {
            highlight("Expected behavior:")
            +" All downloads run in parallel, and progress messages are interleaved."
        }
    }
)

object RunnableExerciseSlide : Slide(
    header = "Exercise 2: Marathon Race",
    summary = {
        +"Create a "
        inlineCode("Runner")
        +" class that simulates runners with different speeds and rest strategies racing to a finish line."
    },
    content = {
        p {
            +"Write a race simulation where:"
            ul {
                li {
                    +"Create a "
                    inlineCode("Runner")
                    +" class that implements "
                    inlineCode("Runnable")
                }
                li {
                    +"Constructor parameters: "
                    inlineCode("name")
                    +", "
                    inlineCode("speed")
                    +" (ms per meter), "
                    inlineCode("maxDistance")
                    +", "
                    inlineCode("pauseEvery")
                    +" (meters), "
                    inlineCode("pauseDuration")
                    +" (ms)"
                }
                li { +"Runners move meter by meter, taking breaks when needed" }
                li { +"Create at least 2 runners: one fast with breaks, one slow without breaks" }
                li {
                    +"Use "
                    inlineCode("join()")
                    +" to wait for all runners to finish"
                }
            }
        }
        p {
            highlight("Example:")
            +" Fast runner: speed=50ms, pauses every 5m for 300ms | Slow runner: speed=100ms, no pauses"
        }
        kotlinPlayground(
            code = """
                class Runner(
                    private val name: String,
                    private val speed: Long,
                    private val maxDistance: Int,
                    private val pauseEvery: Int = 0,
                    private val pauseDuration: Long = 0
                ) : Runnable {
                    
                    // Implement the runner logic ...
                }
            """.trimIndent(),
            executable = false
        )
    }
)

object SynchronizationExerciseSlide : Slide(
    header = "Exercise 3: Bank Account",
    summary = {
        +"Simulate concurrent deposits to a shared bank account and fix race conditions using "
        inlineCode("@Synchronized")
        +"."
    },
    content = {
        p {
            +"Create a bank account simulation where:"
            ul {
                li { +"A shared variable represents the account balance (starts at 0)" }
                li {
                    +"Create a "
                    inlineCode("deposit()")
                    +" function that adds money to the balance"
                }
                li { +"Multiple threads (customers) make 1000 deposits of $1 each" }
                li {
                    +"First, run "
                    highlight("without")
                    +" "
                    inlineCode("@Synchronized")
                    +" and observe money lost due to race conditions"
                }
                li {
                    +"Then add "
                    inlineCode("@Synchronized")
                    +" to the deposit function to fix it"
                }
            }
        }
        p {
            highlight("Expected:")
            +" Without sync, balance < $3000. With sync, balance = $3000 exactly."
        }
    }
)

object AtomicVariablesExerciseSlide : Slide(
    header = "Exercise 4: Ticket Booking System",
    summary = {
        +"Use "
        inlineCode("AtomicInteger")
        +" to manage a shared ticket inventory across multiple booking agents without explicit locks."
    },
    content = {
        p {
            +"Create a ticket booking system where:"
            ul {
                li {
                    +"Use an "
                    inlineCode("AtomicInteger")
                    +" to represent available tickets (e.g., 50 tickets)"
                }
                li { +"Multiple booking agents (threads) try to sell tickets concurrently" }
                li {
                    +"Each agent uses "
                    inlineCode("decrementAndGet()")
                    +" to book a ticket atomically"
                }
                li { +"Agents stop when no tickets remain (value reaches 0)" }
                li { +"Print which agent sold which ticket number" }
                li {
                    +"Add "
                    inlineCode("Thread.sleep()")
                    +" to simulate booking processing time"
                }
            }
        }
        p {
            highlight("Key point:")
            +" "
            inlineCode("AtomicInteger")
            +" ensures no two agents sell the same ticket, without needing "
            inlineCode("synchronized")
            +" blocks!"
        }
        p {
            em {
                +"Note: No "
                inlineCode("@Synchronized")
                +" needed! "
                inlineCode("AtomicInteger")
                +" guarantees atomicity."
            }
        }
    }
)
