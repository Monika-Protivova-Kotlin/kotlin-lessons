package com.motycka.edu.content.topics.basics.conditionals.loops

import com.motycka.edu.model.Slide
import com.motycka.edu.model.inlineCode
import com.motycka.edu.model.kotlinPlayground
import kotlinx.html.br
import kotlinx.html.div
import kotlinx.html.p

object WhileLoopSlide : Slide(
    header = "While Loop / Do While Loop",
    summary = {
        +"While loop is used to execute a block of code repeatedly as long as a given condition is true."
    },
    content = {
        div {
            p {
                +"The "
                inlineCode("while")
                +" evaluates condition at the beginning of the loop block, before any code is executed."
            }
            kotlinPlayground(
                code = WHILE_LOOP,
                executable = true
            )
        }
        br()
        div {
            p {
                +"The "
                inlineCode("do while")
                +" first executes code block once, and evaluates condition the condition."
            }
            kotlinPlayground(
                code = DO_WHILE_LOOP,
                executable = true
            )
        }
    }
)

const val WHILE_LOOP = """
fun main() {
    var counter = 1
    while (counter <= 5) {
        println("Counter: ${'$'}counter")
        counter++
    }
}
"""

const val DO_WHILE_LOOP = """
fun main() {
    var number = 1
    do {
        println("Number: ${'$'}number")
        number++
    } while (number <= 3)
}
"""
