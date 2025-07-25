package com.motycka.edu.content.topics.basics.io

import kotlinx.html.*
import com.motycka.edu.model.Topic
import com.motycka.edu.model.Slide
import com.motycka.edu.model.kotlinPlayground
import com.motycka.edu.model.inlineCode

object CommandLineInputTopic : Topic(
    title = "The main method",
    slides = listOf(
        CommandLineArgumentsSlide,
        CommandLineExerciseSlide
    )
)

object CommandLineArgumentsSlide : Slide(
    header = "Command line arguments",
    summary = {
        +"Command line arguments are passed to the program when it is executed from the command line."
    },
    content = {
        p {
            +"We have been using main method with "
            inlineCode("args: Array<String>")
            +" parameter, but we have not explained what they are and how they are used."
        }
        hr()
        p {
            +"When you run a Kotlin program from the command line, you can pass arguments to the program. "
            +"These arguments are treated as strings and are stored in the "
            inlineCode("args: Array<String>")
            +" array."
        }
        p {
            +"We can also read input from console using "
            inlineCode("readLine()")
            +" function."
        }
        kotlinPlayground(
            code = """
                fun main(args: Array<String>) {
                    println("Received ${'$'}{args.size} arguments:")

                    for ((index, arg) in args.withIndex()) {
                        println(" ${'$'}index = ${'$'}arg")
                    }

                    println("\nEnter your name:")
                    val name = readLine()
                    println("\nHello, ${'$'}name!")
                }
            """.trimIndent(),
            executable = true
        )
    }
)

object CommandLineExerciseSlide : Slide(
    header = "Exercise",
    content = {
        p {
            +"Update your main method for one of the previous exercises to accept number of rounds as a command line argument."
        }
        p {
            +"If the number of rounds is not provided, ask the user to enter it."
        }
        p {
            +"Make sure you handle invalid inputs!"
        }
    }
)
