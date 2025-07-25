package com.motycka.edu.content.topics.basics.essentials

import com.motycka.edu.model.Slide
import com.motycka.edu.model.Topic
import com.motycka.edu.model.inlineCode
import com.motycka.edu.model.kotlinPlayground
import kotlinx.html.*

object KotlinComparisonsConditionalsTopic : Topic(
    title = "Comparisons and Conditionals",
    slides = listOf(
        LogicalOperatorsSlideV2,
        IfElseSlideV2,
        WhenSlideV2
    )
)

object LogicalOperatorsSlideV2 : Slide(
    header = "Logical operators",
    summary = {
        +"Logical operators are used to combine boolean values and return a boolean result."
    },
    content = {
        table {
            tbody {
                tr {
                    td { inlineCode("&&") }
                    td { +"logical AND" }
                    td {
                        kotlinPlayground(
                            code = """
                                val result = true && false
                            """.trimIndent()
                        )
                    }
                    td { +"false" }
                }
                tr {
                    td { inlineCode("||") }
                    td { +"logical OR" }
                    td {
                        kotlinPlayground(
                            code = """
                                val result = true || false
                            """.trimIndent()
                        )
                    }
                    td { +"true" }
                }
                tr {
                    td { inlineCode("!") }
                    td { +"logical NOT" }
                    td {
                        kotlinPlayground(
                            code = """
                                val result = !true
                            """.trimIndent()
                        )
                    }
                    td { +"false" }
                }
            }
        }
    }
)

object IfElseSlideV2 : Slide(
    header = "If-else statements",
    summary = {
        +"If-else statements are used to execute different blocks of code based on a condition."
    },
    content = {
        kotlinPlayground(
            code = """
                fun main() {
                    val age = 18

                    if (age >= 18) {
                        println("You are an adult")
                    } else {
                        println("You are a minor")
                    }
                }
            """.trimIndent()
        )
        p { +"You can also use if-else as an expression:" }
        kotlinPlayground(
            code = """
                fun main() {
                    val age = 18
                    val message = if (age >= 18) "adult" else "minor"
                    println("You are an ${'$'}message")
                }
            """.trimIndent()
        )
        p { +"Multiple conditions can be chained with else-if:" }
        kotlinPlayground(
            code = """
                fun main() {
                    val grade = 85

                    val letterGrade = if (grade >= 90) {
                        "A"
                    } else if (grade >= 80) {
                        "B"
                    } else if (grade >= 70) {
                        "C"
                    } else if (grade >= 60) {
                        "D"
                    } else {
                        "F"
                    }

                    println("Your grade is: ${'$'}letterGrade")
                }
            """.trimIndent()
        )
    }
)

object WhenSlideV2 : Slide(
    header = "When expressions",
    summary = {
        +"When expressions are similar to switch statements in other languages, but more powerful and safer."
    },
    content = {
        kotlinPlayground(
            code = """
                fun main() {
                    val day = 3

                    val dayName = when (day) {
                        1 -> "Monday"
                        2 -> "Tuesday"
                        3 -> "Wednesday"
                        4 -> "Thursday"
                        5 -> "Friday"
                        6 -> "Saturday"
                        7 -> "Sunday"
                        else -> "Invalid day"
                    }

                    println("Day ${'$'}day is ${'$'}dayName")
                }
            """.trimIndent()
        )
        p { +"When can also match multiple values:" }
        kotlinPlayground(
            code = """
                fun main() {
                    val month = 12

                    val season = when (month) {
                        12, 1, 2 -> "Winter"
                        3, 4, 5 -> "Spring"
                        6, 7, 8 -> "Summer"
                        9, 10, 11 -> "Fall"
                        else -> "Invalid month"
                    }

                    println("Month ${'$'}month is in ${'$'}season")
                }
            """.trimIndent()
        )
        p { +"When can also use ranges and conditions:" }
        kotlinPlayground(
            code = """
                fun main() {
                    val score = 85

                    val grade = when (score) {
                        in 90..100 -> "A"
                        in 80..89 -> "B"
                        in 70..79 -> "C"
                        in 60..69 -> "D"
                        else -> "F"
                    }

                    println("Score ${'$'}score is grade ${'$'}grade")
                }
            """.trimIndent()
        )
    }
)
