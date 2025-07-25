package com.motycka.edu.content.topics.basics.arrays

import com.motycka.edu.model.Slide
import com.motycka.edu.model.kotlinPlayground
import kotlinx.html.code
import kotlinx.html.div
import com.motycka.edu.model.inlineCode

object ArrayOperationsSlide : Slide(
    header = "Array Operations",
    summary = {
        +"Given and array, some of the common operations on arrays include ..."
    },
    content = {
        div {
            +"Iterating an array using a "; inlineCode("for"); " loop or "; inlineCode("forEach"); " function."
            kotlinPlayground(
                code = """
                    for (element in array) {
                        println(element)
                    }
                """.trimIndent(),
                executable = false
            )
            kotlinPlayground(
                code = """
                    array.forEach { println(it) }
                """.trimIndent(),
                executable = false
            )
            +"Filtering an array"
            kotlinPlayground(
                code = """
                    val filtered = array.filter { it % 2 == 0 }
                """.trimIndent(),
                executable = false
            )
        }
    }
)
