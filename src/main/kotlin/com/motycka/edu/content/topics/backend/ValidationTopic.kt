package com.motycka.edu.content.topics.backend

import com.motycka.edu.model.Topic
import com.motycka.edu.model.Slide
import com.motycka.edu.model.kotlinPlayground
import com.motycka.edu.model.twoColumns
import kotlinx.html.*
import com.motycka.edu.model.inlineCode

object ValidationTopic : Topic(
    title = "Kotlin Validation",
    slides = listOf(
        ValidationIntroSlide,
        ValidationUsageExamplesSlide
    )
)

object ValidationIntroSlide : Slide(
    header = "Kotlin Validation",
    summary = {
        +"Kotlin provides a way to validate data using the "
        inlineCode("require")
        +" and "
        inlineCode("check")
        +" functions."
    },
    content = {
        p {
            +"These functions are a convenient way to throw exceptions when a condition is not met."
        }
        twoColumns(
            left = {
                kotlinPlayground(
                    code = """
                        val condition = a > b

                        check(condition) { "Condition not met!" }
                    """.trimIndent(),
                    executable = false
                )
            },
            right = {
                ul {
                    li { +"Validates that a condition is true." }
                    li {
                        +"If the condition is false, throws an "
                        inlineCode("IllegalStateException")
                        +"."
                    }
                }
            }
        )
        twoColumns(
            left = {
                kotlinPlayground(
                    code = """
                        val condition = a > b

                        require(condition) { "Condition not met!" }
                    """.trimIndent(),
                    executable = false
                )
            },
            right = {
                ul {
                    li { +"Validates that a condition is true." }
                    li {
                        +"If the condition is false, throws an "
                        inlineCode("IllegalArgumentException")
                        +"."
                    }
                }
            }
        )
        twoColumns(
            left = {
                kotlinPlayground(
                    code = """
                        val nullableValue: String? = null

                        requireNotNull(nullableValue) { "Value must not be null!" }
                    """.trimIndent(),
                    executable = false
                )
            },
            right = {
                ul {
                    li { +"Validates that a value is not null." }
                    li {
                        +"If the value is null, throws an "
                        inlineCode("IllegalArgumentException")
                        +"."
                    }
                }
            }
        )
    }
)

object ValidationUsageExamplesSlide : Slide(
    header = "Usage Examples",
    content = {
        p {
            +"These functions are often used to validate input parameters, state of an object, or any other condition that must be true for the program to continue executing."
        }
        p {
            +"For example, you can use "
            inlineCode("require")
            +" to validate that a list is not empty:"
        }
        kotlinPlayground(
            code = """
                fun processOrder(items: List<MenuItem>) {
                    require(items.isNotEmpty()) { "Order must not be empty!" }
                    // Order processing logic ...
                }
            """.trimIndent(),
            executable = false
        )
        p {
            +"You can also use validation in data class constructors:"
        }
        kotlinPlayground(
            code = """
                @Serializable
                data class OrderRequest(
                    val items: List<OrderItemRequest>
                ) {
                    init {
                        require(items.isNotEmpty()) { "Order must not be empty!" }
                    }
                }
            """.trimIndent(),
            executable = false
        )
    }
)
