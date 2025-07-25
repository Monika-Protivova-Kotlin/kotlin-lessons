package com.motycka.edu.content.topics.basics.essentials

import com.motycka.edu.model.Slide
import com.motycka.edu.model.Topic
import com.motycka.edu.model.inlineCode
import com.motycka.edu.model.kotlinPlayground
import kotlinx.html.em
import kotlinx.html.li
import kotlinx.html.ul

object KotlinLanguageSyntaxTopic : Topic(
    title = "Kotlin Language Syntax",
    subtitle = "Overview",
    slides = listOf(
        ProgramEntryPointSlide,
    ),
)

object ProgramEntryPointSlide : Slide(
    header = "Program entry point",
    summary = { +"Program entry point is the first function executed when the program is run." },
    content = {
        ul {
            li {
                +"All Kotlin files should have "
                inlineCode(".kt")
                +" extension, for example "
                inlineCode("MyProgram.kt")
                +"."
            }
            li {
                +"In Kotlin, the main program entry point is defined as a top-level function, which means that it is not part of a "
                inlineCode("class")
                +"."
                kotlinPlayground(
                    code = """
                        fun main() {
                            println("Hello world!")
                        }
                    """.trimIndent()
                )
            }
            li {
                +"The main function may accept an "
                em { +"array of strings" }
                +" as an argument,"
                +"which can be used to pass command-line arguments to the program."
                kotlinPlayground(
                    code = """
                        fun main(args: Array<String>) {
                            println("Number of arguments: " + args.size)
                            for (arg in args) {
                                println(arg)
                            }
                        }
                    """.trimIndent(),
                    arguments = arrayOf("Hello", "Kotlin")
                )
            }
        }
    }
)

object FunctionsSlide : Slide(
    header = "Functions",
    summary = {
        +"Function is declared using the "
        inlineCode("fun")
        +" keyword, followed by the function name, parameters in parentheses, return type, and function body enclosed in curly braces."
    },
    content = {
        ul {
            li {
                +"Function may have zero or more parameters, and it may return a value, in which case the return type is specified."
//                kotlinPlayground(src = "https://pl.kotl.in/L3UuWzKu-")
            }
            li {
                +"If a function does not return a value, the return type is "
                inlineCode("Unit")
                +", but the return type can be omitted."
//                kotlinPlayground(src = "https://pl.kotl.in/mQ8yxKTxO")
            }
        }
    }
)
