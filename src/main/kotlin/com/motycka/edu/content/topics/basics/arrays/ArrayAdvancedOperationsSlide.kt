package com.motycka.edu.content.topics.basics.arrays

import com.motycka.edu.model.Slide
import com.motycka.edu.model.kotlinPlayground
import kotlinx.html.code
import kotlinx.html.div
import kotlinx.html.em
import com.motycka.edu.model.inlineCode

object ArrayAdvancedOperationsSlide : Slide(
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
            +"Checking if an array contains an element"
            kotlinPlayground(
                code = """
                    array.contains(3) // returns true
                """.trimIndent(),
                executable = false
            )
            +"Sorting, reversing, and shuffling an array"
            kotlinPlayground(
                code = """
                    val sorted = array.sort() // in ascending order

                    val reversed = array.reverse()

                    val shuffled = array.shuffle()
                """.trimIndent(),
                executable = false
            )
        }
        div {
            em {
                +"We will talk more about array and collection operations in the next lessons."
            }
        }
    }
)
