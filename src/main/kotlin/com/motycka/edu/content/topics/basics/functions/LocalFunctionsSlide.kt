package com.motycka.edu.content.topics.basics.functions

import com.motycka.edu.model.Slide
import com.motycka.edu.model.kotlinPlayground
import kotlinx.html.blockQuote

object LocalFunctionsSlide : Slide(
    header = "Local functions",
    summary = {
        +"Functions can be defined inside other functions, in which case they are called local functions."
    },
    content = {
        +"Defining a local functions is useful when you want to encapsulate some logic that is used multiple times "
        +"but only within the function in which it is defined."
        kotlinPlayground(
            code = """
                    // outer function
                    fun factorial(n: Int): Int {

                        // local function
                        fun fact(x: Int, acc: Int): Int {
                            return if (x <= 1) acc else fact(x - 1, x * acc) // tail recursion
                        }

                        // calls the local function
                        return fact(n, 1)
                    }
                    
                    // main entry point with a call to the outer function
                    fun main() {
                        println(factorial(5)) // prints 120
                    }
            """
        )
        blockQuote {
            +"Functions can be defined at the top level of a file, meaning they do not need to be part of a class."
        }
    }
)
