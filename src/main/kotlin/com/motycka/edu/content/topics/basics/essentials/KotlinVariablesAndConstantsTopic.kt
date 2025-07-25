package com.motycka.edu.content.topics.basics.essentials

import com.motycka.edu.model.Slide
import com.motycka.edu.model.Topic
import com.motycka.edu.model.inlineCode
import com.motycka.edu.model.kotlinPlayground
import com.motycka.edu.model.twoColumns
import kotlinx.html.br
import kotlinx.html.div
import kotlinx.html.strong

object KotlinVariablesAndConstantsTopic : Topic(
    title = "Variables and Constants",
    slides = listOf(
        VariablesAndConstantsSlide
    )
)

object VariablesAndConstantsSlide : Slide(
    header = "Variables and constants",
    content = {
        kotlinPlayground(
            code = """
                val number = 3
                val text = "Hello"
                val date = LocalDate()
            """.trimIndent()
        )
        +"Variables and constants can be declared as "
        strong { +"read-only" }
        +", using "
        inlineCode("val")
        +" keyword, or "
        strong { +"mutable" }
        +", using "
        inlineCode("var")
        +" keyword."
        br()
        twoColumns(
            left = {
                kotlinPlayground(
                    code = """
                        var name: String = "John"
                        name = "Jane"
                        println(name)
                    """.trimIndent()
                )
                div {
                    +"Variable "
                    strong { +"can" }
                    +" be changed after declaration."
                }
            },
            right = {
                kotlinPlayground(
                    code = """
                        val name: String = "John"
                        name = "John"
                        println(name)
                    """.trimIndent()
                )
                div {
                    +"Variable "
                    strong { +"cannot" }
                    +" be changed after declaration."
                }
            }
        )
    }
)
