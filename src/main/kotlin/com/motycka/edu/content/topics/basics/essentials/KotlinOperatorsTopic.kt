package com.motycka.edu.content.topics.basics.essentials

import com.motycka.edu.model.Slide
import com.motycka.edu.model.Topic
import com.motycka.edu.model.inlineCode
import com.motycka.edu.model.kotlinPlayground
import kotlinx.html.*

object KotlinOperatorsTopic : Topic(
    title = "Operators",
    slides = listOf(
        ArithmeticOperatorsSlideV2,
        ArithmeticExamplesSlide,
        ComparisonOperatorsSlideV2
    )
)

object ArithmeticOperatorsSlideV2 : Slide(
    header = "Arithmetic operators",
    summary = {
        +"Given two variables "
        inlineCode("var a = 5")
        +" and "
        inlineCode("var b = 2")
        +"."
    },
    content = {
        table {
            style = "font-size: 70%"
            tbody {
                tr {
                    td { inlineCode("+") }
                    td { +"addition" }
                    td {
                        style = "min-width: 300px"
                        kotlinPlayground(
                            code = """
                                val sum = a + b
                            """.trimIndent()
                        )
                    }
                    td { +"7" }
                }
                tr {
                    td { inlineCode("-") }
                    td { +"subtraction" }
                    td {
                        kotlinPlayground(
                            code = """
                                val difference = a - b
                            """.trimIndent()
                        )
                    }
                    td { +"3" }
                }
                tr {
                    td { inlineCode("*") }
                    td { +"multiplication" }
                    td {
                        kotlinPlayground(
                            code = """
                                val product = a * b
                            """.trimIndent()
                        )
                    }
                    td { +"10" }
                }
                tr {
                    td { inlineCode("/") }
                    td { +"division" }
                    td {
                        kotlinPlayground(
                            code = """
                                val quotient = a / b
                            """.trimIndent()
                        )
                    }
                    td { +"2" }
                }
                tr {
                    td { inlineCode("%") }
                    td { +"modulus" }
                    td {
                        kotlinPlayground(
                            code = """
                                val remainder = a % b
                            """.trimIndent()
                        )
                    }
                    td { +"1" }
                }
                tr {
                    td { inlineCode("++") }
                    td { +"increment (by 1)" }
                    td {
                        kotlinPlayground(
                            code = """
                                val increment1 = ++a
                                val increment2 = a++
                            """.trimIndent()
                        )
                    }
                    td { +"6, 6" }
                }
                tr {
                    td { inlineCode("--") }
                    td { +"decrement (by 1)" }
                    td {
                        kotlinPlayground(
                            code = """
                                val decrement1 = --b
                                val decrement2 = b--
                            """.trimIndent()
                        )
                    }
                    td { +"1, 1" }
                }
            }
        }
    }
)

object ArithmeticExamplesSlide : Slide(
    header = "Arithmetic operators examples",
    content = {
        kotlinPlayground(
            code = """
                var a = 0
                println(a++)
            """.trimIndent()
        )
        span(classes = "fragment fade-in") { +"0, because is incremented (by 1) after it is printed" }
        br()
        kotlinPlayground(
            code = """
                var a = 0
                println(++a)
            """.trimIndent()
        )
        span(classes = "fragment fade-in") { +"1, because is incremented (by 1) before it is printed" }
        br()
        kotlinPlayground(
            code = """
                val a: Int = 5
                val b: Int = 3

                println(a / b)
            """.trimIndent()
        )
        span(classes = "fragment fade-in") { +"1, because integer division truncates the result" }
        br()
        kotlinPlayground(
            code = """
                val a: Int = 5
                val b: Int = 3

                println(a % b)
            """.trimIndent()
        )
        span(classes = "fragment fade-in") { +"2, because 5 = 3 * 1 + 2" }
    }
)

object ComparisonOperatorsSlideV2 : Slide(
    header = "Comparison operators",
    summary = {
        +"Comparison operators are used to compare two values and return a boolean result."
    },
    content = {
        table {
            tbody {
                tr {
                    td { inlineCode("==") }
                    td { +"equals" }
                    td {
                        style = "min-width: 300px"
                        kotlinPlayground(
                            code = """
                                val isEqual = a == b
                            """.trimIndent()
                        )
                    }
                }
                tr {
                    td { inlineCode("!=") }
                    td { +"not equals" }
                    td {
                        kotlinPlayground(
                            code = """
                                val isNotEqual = a != b
                            """.trimIndent()
                        )
                    }
                }
                tr {
                    td { inlineCode(">") }
                    td { +"is greater" }
                    td {
                        kotlinPlayground(
                            code = """
                                val isGreater = a > b
                            """.trimIndent()
                        )
                    }
                }
                tr {
                    td { inlineCode("<") }
                    td { +"is less" }
                    td {
                        kotlinPlayground(
                            code = """
                                val isLess = a < b
                            """.trimIndent()
                        )
                    }
                }
                tr {
                    td { inlineCode(">=") }
                    td { +"is greater or equal" }
                    td {
                        kotlinPlayground(
                            code = """
                                val isGreaterOrEqual = a >= b
                            """.trimIndent()
                        )
                    }
                }
                tr {
                    td { inlineCode("<=") }
                    td { +"is less or equal" }
                    td {
                        kotlinPlayground(
                            code = """
                                val isLessOrEqual = a <= b
                            """.trimIndent()
                        )
                    }
                }
            }
        }
    }
)
