package com.motycka.edu.content.topics.basics.conditionals.`when`

import com.motycka.edu.model.Slide
import com.motycka.edu.model.inlineCode
import com.motycka.edu.model.kotlinPlayground
import com.motycka.edu.model.twoColumns
import kotlinx.html.br

object WhenExampleSlide : Slide(
    header = "when",
    content = {
        +"Given ..."
        kotlinPlayground(
            code = """val randomInt = arrayOf(0, 1, 2, 3, 4, 5).random()""",
            executable = false
        )
        twoColumns(
            left = {
                +"You can use "
                inlineCode("when")
                +" as a statement or as an expression."
                br()
            },
            right = {
                +"You can check if a value is within a range or in a collection using "
                inlineCode("in")
                +" keyword."
            }
        )
        twoColumns(
            left = {
                kotlinPlayground(
                    code = """
                            val result = when (randomInt) {
                                0 -> "Zero"
                                1 -> "One"
                                2 -> "Two"
                                3 -> "Three"
                                4 -> "Four"
                                5 -> "Five"
                                else -> "Too much"
                            }
                        """,
                    executable = false
                )
            },
            right = {
                kotlinPlayground(
                    code = """
                        val result = when (randomInt) {
                            0 -> "Zero"
                            in 1..3 -> "Between 1 and 3"
                            in 3..5 -> "Between 3 and 5"
                            else -> "Too much"
                        }
                    """,
                    executable = false
                )
            }
        )
        twoColumns(
            left = {
                +"You can use "
                inlineCode("when")
                +" without an argument, which allows you to evaluate arbitrary conditions."
            },
            right = {
                +"You can also check the type of a variable using "
                inlineCode("is")
                +" keyword."
            }
        )
        twoColumns(
            left = {
                kotlinPlayground(
                    code = """
                            val result = when {
                                randomInt < 0 -> "Less than 0"
                                randomInt == 0 -> "Zero"
                                else -> "Greater than 0"
                            }
                        """,
                    executable = false
                )
            },
            right = {
                kotlinPlayground(
                    code = """
                            fun describe(obj: Any): String = when (obj) {
                                1 -> "One"
                                "Hello" -> "Greeting"
                                is Long -> "Long"
                                !is String -> "Not a string"
                                else -> "Unknown"
                            }
                        """,
                    executable = false
                )
            }
        )
    }
)
