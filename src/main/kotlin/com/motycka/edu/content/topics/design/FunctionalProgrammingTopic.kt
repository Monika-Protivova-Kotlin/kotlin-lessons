package com.motycka.edu.content.topics.design

import com.motycka.edu.model.Topic
import com.motycka.edu.model.highlight
import com.motycka.edu.model.Slide
import com.motycka.edu.model.kotlinPlayground
import kotlinx.html.*
import com.motycka.edu.model.highlight

object FunctionalProgrammingTopic : Topic(
    title = "Functional Programming",
    slides = listOf(
        FunctionalProgrammingIntroSlide,
        FunctionalProgrammingPrinciplesSlide,
        PureFunctionExampleSlide,
        FunctionAsArgumentSlide,
        FunctionAsArgumentAdvancedSlide
    )
)

object FunctionalProgrammingIntroSlide : Slide(
    header = "Functional Programming",
    summary = {
        +"So far, we have been using mostly "
        strong { +"imperative programming" }
        +" style."
        br()
        +"There is another programming paradigm called "
        strong { +"functional programming" }
        +"."
    },
    content = {
        p {
            +"The "
            highlight("imperative programming")
            +" style is characterized by explicit statements that change a program's state."
        }
        p {
            highlight("Functional programming")
            +" is a programming paradigm where programs are constructed by applying and composing functions. "
            +"It emphasizes the use of "
            strong { +"pure functions" }
            +" that avoid changing state and mutable data."
        }
        p {
            highlight("Pure function")
            +" is a function where the return value is only determined by its input values, without observable "
            strong { +"side effects" }
            +"."
        }
        p {
            highlight("Side effects")
            +" are changes in the state of the program that are observable outside the called function other than the return value."
        }
        p {
            +"Kotlin has by design very good support for functional programming, "
            +"and we have already seen some examples of it."
        }
    }
)

object FunctionalProgrammingPrinciplesSlide : Slide(
    header = "Principles of functional programming",
    content = {
        ul {
            li {
                strong { +"Immutability" }
                ul {
                    li { +"Once an object is created, it cannot be changed." }
                    li { +"Instead of changing the object, a new object is created with the new value." }
                }
            }
            li {
                strong { +"Pure functions" }
                ul {
                    li { +"Functions that always return the same result for the same input." }
                    li { +"They do not produce or rely on side effects." }
                }
            }
            li {
                strong { +"First-class functions" }
                ul {
                    li { +"Functions are treated as first-class citizens, meaning they can be passed as arguments to other functions, returned as values from other functions, and assigned to variables." }
                }
            }
            li {
                strong { +"Higher-order functions" }
                ul {
                    li { +"Functions that take other functions as arguments or return them as results." }
                }
            }
            li {
                strong { +"Referential transparency" }
                ul {
                    li { +"It means that a function will always return the same result for the same input." }
                    li { +"This means that the function call can be replaced with its corresponding value without changing the program's behavior." }
                }
            }
            li {
                +"There may be other principles mentioned such as "
                strong { +"Recursion" }
                +" or "
                strong { +"Functional composition" }
            }
        }
    }
)

object PureFunctionExampleSlide : Slide(
    header = "Functional Programming",
    summary = {
        +"Example of a pure function and a function with side effects."
    },
    content = {
        p {
            +"Function with side effects:"
        }
        kotlinPlayground(
            code = """
                var counter = 0

                fun unPureFunction(increment: Int): Int {
                    return counter += increment
                }
            """.trimIndent(),
            executable = false
        )
        p {
            +"Pure function:"
        }
        kotlinPlayground(
            code = """
                fun pureFunction(counter: Int, increment: Int): Int {
                    return counter + increment
                }
            """.trimIndent(),
            executable = false
        )
    }
)

object FunctionAsArgumentSlide : Slide(
    header = "Function as an argument",
    summary = {
        +"You can pass a function as an argument to another function."
    },
    content = {
        p {
            +"Here is an example of a function that takes a function with a single parameter and returns an integer."
        }
        kotlinPlayground(
            code = """
                fun execute(input: String, function: (String) -> Int): Int {
                    return function(input)
                }
            """.trimIndent(),
            executable = false
        )
        kotlinPlayground(
            code = """
                val result = execute("Hello Function!") { input ->
                    println("Got input: ${'$'}input")
                    input.length
                }

                println(result)
            """.trimIndent(),
            executable = false
        )
    }
)

object FunctionAsArgumentAdvancedSlide : Slide(
    header = "Function as an argument - Advanced",
    summary = {
        +"Here is an example of a function that takes a function with two parameters and returns a boolean."
    },
    content = {
        kotlinPlayground(
            code = """
                fun execute(input1: String, input2: Double, function: (String, Double) -> Boolean): Boolean {
                    return function(input1, input2)
                }
            """.trimIndent(),
            executable = false
        )
        kotlinPlayground(
            code = """
                val result2 = execute("12.34", 12.34) { p1, p2 ->
                    p1.toDouble() == p2
                }

                println(result2)
            """.trimIndent(),
            executable = false
        )
    }
)
