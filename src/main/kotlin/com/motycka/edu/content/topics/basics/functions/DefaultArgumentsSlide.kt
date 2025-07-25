package com.motycka.edu.content.topics.basics.functions

import com.motycka.edu.model.Slide
import com.motycka.edu.model.kotlinPlayground
import com.motycka.edu.model.twoColumns
import kotlinx.html.br

object DefaultArgumentsSlide : Slide(
    header = "Default arguments",
    summary = {
        +"Kotlin allows you to specify default values for function arguments, making them optional."
    },
    content = {
        twoColumns(
            left = {
                br
                +"Given a function with a default argument ..."
            },
            right = {
                kotlinPlayground(
                    code = """
                        fun round(
                            value: Double,
                            decimals: Int = 2 // argument with default value od 2
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
                +"When the argument is not provided when calling the function, the default value is used."
            },
            right = {
                kotlinPlayground(
                    code = """
                        import kotlin.math.pow
                        import kotlin.math.roundToInt
                        
                        fun round(
                            value: Double,
                            decimals: Int = 2 // argument with default value od 2
                        ): Double {
                            val factor = 10.0.pow(decimals.toDouble())
                            return (value * factor).roundToInt() / factor
                        }
                        
                        fun main() {
                            val result = round(3.14159)
                            println(result) // prints 3.14
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
                +"When the argument is provided when calling the function, the provided value is used."
            },
            right = {
                kotlinPlayground(
                    code = """
                        import kotlin.math.pow
                        import kotlin.math.roundToInt
                        
                        fun round(
                            value: Double,
                            decimals: Int = 2 // argument with default value od 2
                        ): Double {
                            val factor = 10.0.pow(decimals.toDouble())
                            return (value * factor).roundToInt() / factor
                        }
                        
                        fun main() {
                            val result = round(3.14159, 4)
                            println(result) // prints 3.1416
                        }
                    """,
                    executable = true,
                    selectLines = 13..14
                )
            }
        )
    }
)
