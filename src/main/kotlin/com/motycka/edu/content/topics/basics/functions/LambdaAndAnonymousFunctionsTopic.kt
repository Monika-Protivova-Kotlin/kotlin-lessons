package com.motycka.edu.content.topics.basics.functions

import kotlinx.html.*
import com.motycka.edu.model.Topic
import com.motycka.edu.model.Slide
import com.motycka.edu.model.highlight
import com.motycka.edu.model.kotlinPlayground
import com.motycka.edu.model.inlineCode

object LambdaAndAnonymousFunctionsTopic : Topic(
    title = "Anonymous Function & Lambda Expression",
    slides = listOf(
        LambdaAndAnonymousFunctionsIntroSlide,
        LambdaExpressionExamplesSlide,
        AnonymousFunctionExamplesSlide,
        LambdaAndAnonymousFunctionsUsageSlide,
        LambdaAndAnonymousFunctionsAdvancedUsageSlide,
        LambdaAndAnonymousFunctionsReturningFunctionsSlide,
        LambdaAndAnonymousFunctionsExerciseSlide
    )
)

object LambdaAndAnonymousFunctionsIntroSlide : Slide(
    header = "Anonymous Function & Lambda Expression",
    summary = {
        +"Anonymous functions and lambda expressions are used to define functions without names."
    },
    content = {
        h4 { +"Lambda Expression" }
        p {
            +"Lambda expressions are typically used for short, concise functions that are passed as arguments to higher-order functions. "
            +"They are commonly used in collection operations like "
            inlineCode("map")
            +", "
            inlineCode("filter")
            +", and "
            inlineCode("forEach")
            +"."
        }
        kotlinPlayground(
            code = """
                    val lambdaName: (Type) -> ReturnType = { argument: Type -> body }
                """,
            executable = false
        )
        blockQuote {
            +"If lambda expression has a single parameter, you can use the default name "
            inlineCode("it")
            +"."
        }
        br()
        h4 { +"Anonymous Function" }
        p {
            +"Anonymous functions are used when you need more control over the function's return type or when you need to use the "
            inlineCode("return")
            +" statement to exit the function itself rather than the enclosing function."
        }
        kotlinPlayground(
            code = """
                    val lambdaName = fun(name: Type): Type {
                        return value
                    }
                """,
            executable = false
        )
        blockQuote {
            +"Opposite of anonymous function is called a named function."
        }
    }
)

object LambdaExpressionExamplesSlide : Slide(
    header = "Examples: Lambda Expression",
    content = {
        p { +"Function with one parameter and no return value:" }
        kotlinPlayground(
            code = """
                    val greet: (String) -> Unit = { name -> println("Hello, ${DOLLAR}name!") }
                    
                    greet("World")
                """,
            executable = false
        )
        p {
            +"If lambda expression has a single parameter, you can use the default name "
            inlineCode("it")
            +":"
        }
        kotlinPlayground(
            code = """
                    val greet: (String) -> Unit = { println("Hello, ${DOLLAR}it!") }
                    
                    greet("World")
                """,
            executable = false
        )
        p { +"Function with two parameters and a return value:" }
        kotlinPlayground(
            code = """
                    val multiply: (Int, Int) -> Int = { a, b -> a * b }
                    
                    val result = multiply(10, 20)
                """,
            executable = false
        )
        p {
            +"Common examples of lambda function is the "
            inlineCode("forEach")
            +":"
        }
        kotlinPlayground(
            code = """
                    listOf("Bangkok", "Barcelona", "Tokyo", "London", "New York").forEach { city ->
                        println(city)
                    }
                """,
            executable = false
        )
    }
)

object AnonymousFunctionExamplesSlide : Slide(
    header = "Examples: Anonymous Function",
    content = {
        +"Anonymous function with one parameter and no return value:"
        kotlinPlayground(
            code = """
                    val greet = fun(name: String) {
                        println("Hello, ${DOLLAR}name!")
                    }

                    greet("World")
                """,
            executable = false
        )
        +"Anonymous function with two parameters and a return value:"
        kotlinPlayground(
            code = """
                    val multiply = fun(a: Int, b: Int): Int {
                        return a * b
                    }

                    val result = multiply(10, 20)
                """,
            executable = false
        )
    }
)

object LambdaAndAnonymousFunctionsUsageSlide : Slide(
    header = "Anonymous Function & Lambda Expression",
    summary = {
        +"Usage"
    },
    content = {
        p {
            +"In summary, lambda expressions are more concise and are typically used for simpler functions, "
            +"while anonymous functions provide more flexibility with explicit return types and return behavior."
        }
        p {
            +"There are few use cases for lambda expressions and anonymous functions:"
        }
        ul {
            li { +"Passing functions as arguments to higher-order functions" }
            li { +"Returning functions from other functions" }
            li { +"Defining local functions that are not needed outside the scope of the enclosing function" }
        }
    }
)

object LambdaAndAnonymousFunctionsAdvancedUsageSlide : Slide(
    header = "Anonymous Function & Lambda Expression",
    summary = {
        +"Usage"
    },
    content = {
        p {
            +"In this example, the "
            inlineCode("operation")
            +" function takes two integers and a lambda function as arguments."
        }
        kotlinPlayground(
            code = """
                    fun operation(x: Int, y: Int, func: (Int, Int) -> Int): Int {
                        return func(x, y)
                    }
                """,
            executable = false
        )
        p {
            +"The most common use way pass the function argument is:"
        }
        kotlinPlayground(
            code = """
                    val result = operation(10, 20) { x, y -> x + y }
                """,
            executable = false
        )
        p {
            +"Another possibility is to pass a function reference (it can be a named function or a member function):"
        }
        kotlinPlayground(
            code = """
                    val multiply: (Int, Int) -> Int = { a, b -> a * b }

                    val result = operation(10, 20, multiply)
                """,
            executable = false
        )
    }
)

object LambdaAndAnonymousFunctionsReturningFunctionsSlide : Slide(
    header = "Anonymous Function & Lambda Expression",
    summary = {
        +"Usage"
    },
    content = {
        p {
            +"You can also return functions from other functions."
        }
        kotlinPlayground(
            code = """
                    fun getCalculator(): (Int, Long, Double) -> Double {
                        return { a, b, c -> a + b + c }
                    }
                """,
            executable = false
        )
        kotlinPlayground(
            code = """
                    val calculator = getCalculator()

                    val result = calculator(1, 2, 3.0)
                """,
            executable = false
        )
    }
)

object LambdaAndAnonymousFunctionsExerciseSlide : Slide(
    header = "Exercise",
    content = {
        div {
            +"Create a function named "
            highlight("updateAtIndex")
            +" that takes the following parameters:"
            ul {
                li {
                    +"An array of strings ("
                    inlineCode("Array<String>")
                    +")."
                }
                li {
                    +"A variable number of integer indices ("
                    inlineCode("vararg atIndex: Int")
                    +")."
                }
                li {
                    +"A lambda function ("
                    inlineCode("func: (String) -> String")
                    +") that takes a string as an argument and returns a string."
                }
            }
        }
        div {
            p {
                +"The function should return a new array (copy) of "
                inlineCode("Array<String>")
                +" where the elements at the specified indices are updated using the provided lambda function. If any of the specified indices are out of bounds, the function should throw an error with the message \"Index out of bounds\"."
            }
            strong { +"Example" }
            p { +"Given the following input:" }
            ul {
                li {
                    inlineCode("array = [\"a\", \"b\", \"c\", \"d\", \"e\"]")
                }
                li {
                    inlineCode("atIndex: = 1, 3")
                }
                li {
                    inlineCode("func = { it.uppercase() }")
                }
            }
            p { +"The function should return:" }
            inlineCode("[\"a\", \"B\", \"c\", \"D\", \"e\"]")
        }
    }
)
