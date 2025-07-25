package com.motycka.edu.content.topics.basics.conditionals.`if`

import com.motycka.edu.model.Slide
import com.motycka.edu.model.inlineCode
import com.motycka.edu.model.kotlinPlayground
import com.motycka.edu.model.twoColumns
import kotlinx.html.p

object IfElseKotlinFeaturesSlide : Slide(
    header = "if ... else if ... else",
    fontSize = "70%",
    summary = {
        +"Kotlin allows you to return value from if else statement."
    },
    content = {
        twoColumns(
            left = {
                p {
                    +"Traditionally, you would write this code ..."
                }
                kotlinPlayground(
                    code = """
                        var result: String? = null
         
                        if (a < b) {
                            result = "a is less than b"
                        } else if (a == b) {
                            result = "a is equal to b"
                        } else {
                            result = "a is greater than b"
                        }
                        """,
                    executable = false
                )
            },
            right = {
                p {
                    +"Kotlin allows you to write this in a more concise way."
                }
                kotlinPlayground(
                    code = """
                        val result = if (a < b) {
                            "a is less than b"
                        } else if (a == b) {
                            "a is equal to b"
                        } else {
                            "a is greater than b"
                        }
                        """,
                    executable = false
                )
            }
        )
        p {
            +"Kotlin has no ternary operator like Java ("
            inlineCode("condition ? value1 : value2")
            +"), but you can use if else as a replacement."
        }
        kotlinPlayground(
            code = """
                val result = if (a < b) "a is less than b" else "a is greater than or equal to b"
                """,
            executable = false
        )
    }
)
