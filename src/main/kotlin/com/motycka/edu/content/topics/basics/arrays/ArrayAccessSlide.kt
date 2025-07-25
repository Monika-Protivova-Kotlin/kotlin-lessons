package com.motycka.edu.content.topics.basics.arrays

import com.motycka.edu.model.Slide
import com.motycka.edu.model.kotlinPlayground
import kotlinx.html.br
import kotlinx.html.code
import kotlinx.html.li
import kotlinx.html.strong
import kotlinx.html.sub
import kotlinx.html.ul
import com.motycka.edu.model.inlineCode
import com.motycka.edu.model.highlight

object ArrayAccessSlide : Slide(
    header = "Array Access and Modification",
    content = {
        ul {
            li {
                +"Elements can be accessed or modified using their index and "; inlineCode("[]"); +" operator."
                +"Arrays are "; highlight("zero-based"); "."
                br
                sub { +"For example "; inlineCode("array[0]"); " will access first element." }
            }
            li { +"Modifying an element using an index that is out of bounds will throw an "; inlineCode("ArrayIndexOutOfBoundsException"); "." }
        }
        kotlinPlayground(
            code = """
                val array = arrayOf(1, 2, 3, 4, 5)

                // updating an element on index 4 (5th element)
                array[4] = 42

                // accessing an element on index 4 (5th element)
                println(array[4])

                // accessing an element on index 5 (6th element) - will throw ArrayIndexOutOfBoundsException
                try {
                    println(array[5])
                } catch (e: ArrayIndexOutOfBoundsException) {
                    println(e.message)
                }
            """.trimIndent(),
            executable = false
        )
        +"You can get size of the array using "; inlineCode("size"); " property."
        kotlinPlayground(
            code = """
                println(array.size) // prints 5
            """.trimIndent(),
            executable = false
        )
    }
)
