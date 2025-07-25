package com.motycka.edu.content.topics.basics.conditionals.loops

import com.motycka.edu.model.Slide
import com.motycka.edu.model.Slide.Companion.DOLLAR
import com.motycka.edu.model.kotlinPlayground
import com.motycka.edu.model.twoColumns

object BasicForLoopSlide : Slide(
    header = "For Loop",
//    summary = {
//        +"The for loop is used to iterate over anything that provides an iterator, such as a range, array, or a collection."
//    },
    content = {
        twoColumns(
            left = {
                mapOf(
                    "Basic for loop syntax" to FOR_LOOP,
                    "Iterating over arrays" to ARRAY_LOOP,
                    "Range expressions with downTo and step" to RANGE_LOOP,
                ).forEach { (title, code) ->
                    +title
                    kotlinPlayground(
                        code = code,
                        executable = true
                    )
                }
            },
            right = {
                mapOf(
                    "Iterating with indices" to INDEX_LOOP,
                    "Using withIndex() for both index and value" to WITH_INDEX_LOOP,
                    "Iterating over characters in a string" to CHARACTER_LOOP
                ).forEach { (title, code) ->
                    +title
                    kotlinPlayground(
                        code = code,
                        executable = true
                    )
                }
            }
        )
    }
)

const val FOR_LOOP = """
fun main() {
    for (i in 1..5) {
        println("Number: ${DOLLAR}i")
    }
}
"""

const val ARRAY_LOOP = """
fun main() {
    val fruits = arrayOf("apple", "banana", "orange")
    for (fruit in fruits) {
        println("Fruit: ${DOLLAR}fruit")
    }
}
"""

const val RANGE_LOOP = """
fun main() {
    for (i in 10 downTo 1 step 2) {
        println("Countdown: ${DOLLAR}i")
    }
}
"""

const val INDEX_LOOP = """
fun main() {
    val fruits = arrayOf("apple", "banana", "orange")
    for (index in fruits.indices) {
        println("${DOLLAR}index: ${DOLLAR}{fruits[index]}")
    }
}
"""

const val WITH_INDEX_LOOP = """
fun main() {
    val fruits = arrayOf("apple", "banana", "orange")
    for ((index, value) in fruits.withIndex()) {
        println("${DOLLAR}index -> ${DOLLAR}value")
    }
}
"""

const val CHARACTER_LOOP = """
fun main() {
    for (char in "Hello") {
        print("${DOLLAR}char ")
    }
}
"""
