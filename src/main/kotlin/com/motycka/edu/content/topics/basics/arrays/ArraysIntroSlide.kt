package com.motycka.edu.content.topics.basics.arrays

import com.motycka.edu.model.Slide
import kotlinx.html.br
import kotlinx.html.code
import kotlinx.html.div
import kotlinx.html.h4
import kotlinx.html.li
import kotlinx.html.span
import kotlinx.html.sub
import kotlinx.html.ul
import com.motycka.edu.model.inlineCode

object ArraysIntroSlide : Slide(
    header = "Arrays",
    summary = {
        +"Array is a "
        span("highlight") { +"fixed-size" }
        +" sequential collection of elements of the same type."
    },
    content = {
        div {
            h4("highlight") { +"Declaration and Initialization" }
            ul {
                li { +"Arrays can be declared using the "; inlineCode("arrayOf<Type>()"); +" function." }
                li { +"Arrays are fixed-size, meaning their size cannot be changed once created." }
            }
        }
        br
        div {
            h4("highlight") { +"Type-Safety" }
            ul {
                li {
                    +"Arrays in Kotlin are type-safe - they can only hold elements of the specified type (and its subtypes)."
                    br
                    sub {
                        +"If array contains elements of different types, the type of the array is inferred to be the least common supertype of the elements,"
                        br
                        +"or "; inlineCode("Any"); +" in case of no common supertype."
                    }
                }
                li { +"The type declaration can be omitted if the type of the array can be inferred from the elements passed to the function." }
            }
        }
        br
        div {
            h4("highlight") { +"Access and Modification" }
            ul {
                li {
                    +"Elements can be accessed or modified using their index and "; inlineCode("[]"); +" operator. Arrays are zero-based"
                    br
                    sub { +"For example "; inlineCode("array[0]"); +" will access first element." }
                }
                li { +"Modifying an element using an index that is out of bounds will throw an "; inlineCode("ArrayIndexOutOfBoundsException"); "." }
                li { +"Arrays can be iterated using loops." }
            }
        }
    }
)
