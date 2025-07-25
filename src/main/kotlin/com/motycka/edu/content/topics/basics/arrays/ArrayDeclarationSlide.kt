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

object ArrayDeclarationSlide : Slide(
    header = "Array Declaration and Initialization",
    content = {
        ul {
            li {
                +"Arrays can be declared using the "; inlineCode("arrayOf<Type>()"); +" function."
                br
                sub { +"Type declaration can be omitted if the type of the array can be inferred from the elements passed to the "; inlineCode("arrayOf()"); +" function." }
            }
            li {
                +"Arrays in Kotlin are "; highlight("type-safe"); " - they can only hold elements of the specified type."
                br
                sub {
                    +"If array contains elements of different types, the type of the array is inferred to be the least common supertype of the elements,"
                    br
                    +"or "; inlineCode("Any"); +" in case of no common supertype."
                }
            }
            li { +"Arrays are "; highlight("fixed-size"); ", meaning their size cannot be changed once created." }
        }
        kotlinPlayground(
            code = """
                // Declaring an array of integers
                val numbers = arrayOf(1, 2, 3, 4, 5)

                // Declaring an array of strings
                val cities = arrayOf("Bangkok", "Beijing", "Tokyo", "London", "Paris")

                // Declaring an array of mixed types
                val mixed = arrayOf(1, "Bangkok", 3.14, 'A', true)

                val empty = emptyArray<String>() // size 0

                val arrayOfNulls = arrayOfNulls<String>(5) // size 5, all elements are null
            """.trimIndent(),
            executable = false
        )
    }
)
