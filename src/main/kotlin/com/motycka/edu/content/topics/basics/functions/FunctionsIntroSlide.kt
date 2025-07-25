package com.motycka.edu.content.topics.basics.functions

import com.motycka.edu.model.Slide
import com.motycka.edu.model.inlineCode
import com.motycka.edu.model.kotlinPlayground
import com.motycka.edu.model.twoColumns
import kotlinx.html.br

object FunctionsIntroSlide : Slide(
    header = "Function",
    summary = {
        +"Kotlin is a modern language, supporting both traditional functions and object-oriented programming "
        +"and functional programming paradigms."
    },
    content = {
        twoColumns(
            left = {
                +"Functions are defined using the "
                inlineCode("fun")
                +" keyword, followed by the function name, arguments, and return type."
            },
            right = {
                kotlinPlayground(
                    code = """
                        fun add(a: Int, b: Int): Int {
                            return a + b
                        }

                        val sum = add(2, 3)
                    """,
                    executable = false
                )
            },
            ratio = 2 to 3
        )
        twoColumns(
            left = {
                +"If the function does not return anything, the return type is "
                inlineCode("Unit")
                +", and does not need to be specified explicitly."
            },
            right = {
                kotlinPlayground(
                    code = """
                        fun greet(name: String) {
                            println("Hello, ${'$'}name!")
                        }

                        greet("Alice")
                    """,
                    executable = false
                )
            },
            ratio = 2 to 3
        )
        twoColumns(
            left = {
                br
                +"Functions can be written in a single expression, in which case the return type can be omitted."
            },
            right = {
                kotlinPlayground("fun multiply(a: Int, b: Int) = a * b", executable = false)
            },
            ratio = 2 to 3
        )
        twoColumns(
            left = {
                br
                +"Functions can be passed as arguments to other functions and returned from functions."
            },
            right = {
                kotlinPlayground(
                    code = """
                        fun operation(a: Int, b: Int, operation: (Int, Int) -> Int): Int {
                            return operation(a, b)
                        }

                        val sum = operation(2, 3) { a, b -> a + b }
                    """,
                    executable = false
                )
            },
            ratio = 2 to 3
        )
    }
)
