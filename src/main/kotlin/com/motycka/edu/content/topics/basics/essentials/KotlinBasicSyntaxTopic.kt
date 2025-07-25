package com.motycka.edu.content.topics.basics.essentials

import com.motycka.edu.model.Slide
import com.motycka.edu.model.Topic
import com.motycka.edu.model.inlineCode
import com.motycka.edu.model.kotlinPlayground
import kotlinx.html.em
import kotlinx.html.li
import kotlinx.html.ul

object KotlinBasicSyntaxTopic : Topic(
    title = "Kotlin Syntax",
    slides = listOf(
        ProgramEntryPointSlideV2,
        FunctionsSlideV2,
        VariablesSlide,
        ClassesSlide,
        PrintingSlide,
        CommentsSlide
    )
)

object ProgramEntryPointSlideV2 : Slide(
    header = "Program entry point",
    summary = { +"Program entry point is the first function executed when the program is run." },
    content = {
        ul {
            li {
                +"All Kotlin files should have "
                inlineCode(".kt")
                +" extension, for example "
                inlineCode("MyProgram.kt")
                +"."
            }
            li {
                +"In Kotlin, the main program entry point is defined as a top-level function, which means that it is not part of a "
                em { +"class" }
                +"."
                kotlinPlayground(
                    code = """
                        fun main() {
                            println("Hello world!")
                        }
                    """.trimIndent()
                )
            }
            li {
                +"The main function may accept an "
                em { +"array of strings" }
                +" as an argument, which can be used to pass command-line arguments to the program."
                kotlinPlayground(
                    code = """
                        fun main(args: Array<String>) {
                            println("Number of arguments: " + args.size)
                            for (arg in args) {
                                println("Argument: ${'$'}arg")
                            }
                        }
                    """.trimIndent()
                )
            }
        }
    }
)

object FunctionsSlideV2 : Slide(
    header = "Functions",
    summary = {
        +"Function is declared using the "
        inlineCode("fun")
        +" keyword, followed by the function name, parameters in parentheses, return type, and function body enclosed in curly braces."
    },
    content = {
        ul {
            li {
                +"Function may have zero or more parameters, and it may return a value, in which case the return type is specified."
                kotlinPlayground(
                    code = """
                        fun myFunction(arg1: Int, arg2: Int): Int {
                            return arg1 + arg2
                        }
                    """.trimIndent()
                )
            }
            li {
                +"If a function does not return a value, the return type is "
                inlineCode("Unit")
                +", but the return type can be omitted."
                kotlinPlayground(
                    code = """
                        fun myFunction(arg1: Int, arg2: Int) {
                            println(arg1 + arg2)
                        }
                    """.trimIndent()
                )
            }
        }
    }
)

object VariablesSlide : Slide(
    header = "Variables",
    summary = {
        +"Variables in Kotlin are declared using the "
        inlineCode("var")
        +" or "
        inlineCode("val")
        +" keyword, followed by the variable name, type, and optional value."
    },
    content = {
        ul {
            li {
                +"Variables declared with "
                inlineCode("var")
                +" are mutable = their value can be changed during the program execution."
            }
            li {
                +"Variables declared with "
                inlineCode("val")
                +" are immutable = their value cannot be changed once it is assigned = they are read-only."
            }
            li {
                +"You can omit type in case variable is declared and initialized at the same time, it will be inferred by the compiler."
                kotlinPlayground(
                    code = """
                        val myNumber = 42

                        val myText = "Hello, world!"
                    """.trimIndent()
                )
            }
            li {
                +"You have to specify the type if you don't initialize the variable at declaration time."
                kotlinPlayground(
                    code = """
                        var myNumber: Int

                        var myText: String
                    """.trimIndent()
                )
            }
        }
    }
)

object ClassesSlide : Slide(
    header = "Classes",
    summary = {
        +"Classes in Kotlin are declared using the "
        inlineCode("class")
        +" keyword, followed by the class name, an optional constructor, and the class body enclosed in curly braces."
    },
    content = {
        ul {
            li { +"Classes can have properties, functions, and multiple constructors." }
            li {
                +"Here is a simple example of a class:"
                kotlinPlayground(
                    code = """
                        package com.package.domain

                        class MyClass {
                            var myVariable: Int = 42
                            val foo: Foo = Foo()

                            fun myFunction() {
                                println("Hello from myFunction")
                            }

                            fun anotherFunction(parameter: String): String {
                                return "Hello ${'$'}parameter"
                            }
                        }
                    """.trimIndent()
                )
            }
        }
    }
)

object PrintingSlide : Slide(
    header = "Printing to console",
    summary = {
        +"You can use "
        inlineCode("print")
        +" function to print on the same line, and "
        inlineCode("println")
        +" function to print with a new line at the end."
    },
    content = {
        kotlinPlayground(
            code = """
                fun main() {
                    print("Hello, ")  // prints on one line
                    println("world!") // prints on with a new line at the end
                }
            """.trimIndent()
        )
    }
)

object CommentsSlide : Slide(
    header = "Comments",
    summary = {
        +"You can use "
        inlineCode("//")
        +" for single-line comments and "
        inlineCode("/* */")
        +" for multi-line comments."
    },
    content = {
        kotlinPlayground(
            code = """
                // single-line comment

                /*
                Multi-line comment
                */
            """.trimIndent()
        )
    }
)
