package com.motycka.edu.content.topics.basics.functions

import com.motycka.edu.model.Slide
import com.motycka.edu.model.kotlinPlayground
import com.motycka.edu.model.twoColumns
import kotlinx.html.br
import kotlinx.html.p

object NamedArgumentsSlide : Slide(
    header = "Named arguments",
    summary = {
        +"Kotlin allows you to specify the name of the arguments when calling a function."
    },
    content = {
        p {
            +"This is useful when you have a function with many arguments, and you want to make the code more readable, "
            +"for example when you have a function with many arguments."
        }
        p {
            +"It also allows you to specify arguments in any order, as long as you specify the name. "
            +"This often comes in handy when refactoring the code, adding or changing order of arguments."
        }
        twoColumns(
            left = {
                br
                +"Given function ..."
            },
            right = {
                kotlinPlayground(
                    code = """
                        fun round(
                            value: Double,
                            decimals: Int = 2
                        ): Double {
                            val factor = 10.0.pow(decimals.toDouble())
                            return (value * factor).roundToInt() / factor
                        }
                    """,
                    executable = false
                )
            }
        )

        twoColumns(
            left = {
                br
                +"You may specify the argument name."
            },
            right = {
                kotlinPlayground(
                    code = """
                        import kotlin.math.pow
                        import kotlin.math.roundToInt
                        
                        fun round(
                            value: Double,
                            decimals: Int = 2
                        ): Double {
                            val factor = 10.0.pow(decimals.toDouble())
                            return (value * factor).roundToInt() / factor
                        }
                        
                        fun main() {
                            val result = round(value = 3.14159)
                            println(result)
                        }
                    """,
                    executable = true,
                    selectLines = 13..14
                )
            }
        )
        twoColumns(
            left = {
                br
                +"You may specify the argument names in any order."
            },
            right = {
                kotlinPlayground(
                    code = """
                        import kotlin.math.pow
                        import kotlin.math.roundToInt
                        
                        fun round(
                            value: Double,
                            decimals: Int = 2
                        ): Double {
                            val factor = 10.0.pow(decimals.toDouble())
                            return (value * factor).roundToInt() / factor
                        }
                        
                        fun main() {
                            val result = round(
                                decimals = 4, // notice the changed order of arguments
                                value = 3.14159
                            )
                            println(result)
                        }
                    """,
                    executable = true,
                    selectLines = 13..17
                )
            }
        )
    }
)
