package com.motycka.edu.content.topics.basics.functions

import com.motycka.edu.model.Slide
import com.motycka.edu.model.kotlinPlayground
import kotlinx.html.code
import kotlinx.html.p
import com.motycka.edu.model.inlineCode

object VariableArgumentsSlide : Slide(
    header = "Variable Arguments",
    summary = {
        +"Variadic functions are functions that can take a variable number of arguments."
    },
    content = {
        p {
            +"You can define a function that takes a variable number of arguments by using the "
            inlineCode("vararg")
            +" keyword."
        }
        kotlinPlayground(
            code = """
                fun sayHello(vararg names: String) {
                    println("Hello, ${'$'}{names.joinToString(", ")}!")
                }
                
                fun main() {
                    sayHello("Alice", "Bob", "Charlie")
                }
            """,
            executable = true
        )
        p {
            +"If you need more than one argument, the "
            inlineCode("vararg")
            +" argument must be the last one."
        }
        kotlinPlayground(
            code = """
                fun sayHello(greeting: String, vararg names: String) {
                    println("Hello, ${'$'}{names.joinToString(", ")}!")
                }
                
                fun main() {
                    sayHello(greeting = "Hi", "Alice", "Bob", "Charlie")
                }
            """,
            executable = true
        )
    }
)
