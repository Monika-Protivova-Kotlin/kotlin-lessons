package com.motycka.edu.content.topics.basics.conditionals.loops

import com.motycka.edu.model.Slide
import com.motycka.edu.model.Slide.Companion.DOLLAR
import com.motycka.edu.model.kotlinPlayground
import com.motycka.edu.model.twoColumns
import kotlinx.html.classes
import kotlinx.html.div
import kotlinx.html.em
import kotlinx.html.h4
import kotlinx.html.p

object WhileLoopExamplesSlide : Slide(
    header = "Practice: While Loop / Do While Loop",
    summary = {
        + "What's the difference between these two loops?"
    },
    content = {
        twoColumns(
            left = {
                h4 { +"Example 1 - while loop:" }
                em { +"What will this print?" }
                kotlinPlayground(
                    code = WHILE_LOOP_EXAMPLE,
                    executable = false
                )
            },
            right = {
                h4 { +"Example 2 - do-while loop:" }
                em { +"What will this print?" }
                kotlinPlayground(
                    code = DO_WHILE_LOOP_EXAMPLE,
                    executable = false
                )
            },
        )
        div {
            classes = setOf("fragment", "fade-in")
            em {
                h4 { +"Answer: " }
                +"The while loop won't execute at all (condition is false from start), "
                +"but the do-while loop will execute once before checking the condition."
            }
        }
    }
)

const val WHILE_LOOP_EXAMPLE = """
fun main() {
    
    var counter1 = 10
    
    while (counter1 < 5) {
        println("Counter: ${DOLLAR}counter1")
        counter1++
    }
    
    println("Final counter: ${DOLLAR}counter1")
}
"""

const val DO_WHILE_LOOP_EXAMPLE = """
fun main() {

    var counter2 = 10
    
    do {
        println("Counter: ${DOLLAR}counter2")
        counter2++
    } while (counter2 < 5)
    
    println("Final counter: ${DOLLAR}counter2")
}
"""
