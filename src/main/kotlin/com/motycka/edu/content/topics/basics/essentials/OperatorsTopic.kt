package com.motycka.edu.content.topics.basics.essentials

import com.motycka.edu.builders.*
import com.motycka.edu.model.Slide
import com.motycka.edu.model.Topic
import com.motycka.edu.model.inlineCode
import com.motycka.edu.model.kotlinPlayground
import com.motycka.edu.model.twoColumns
import kotlinx.html.*

object OperatorsTopic : Topic(
    title = "Operators",
    slides = listOf(
        OperatorsOverviewSlide,
        AssignmentOperatorsSlide,
        ArithmeticOperatorsSlide,
        ArithmeticOperatorsExamplesSlide,
        ComparisonOperatorsSlide,
        ComparisonOperatorsExamplesSlide,
        LogicalOperatorsSlide,
        LogicalOperatorsExamplesSlide,
        BitwiseOperatorsSlide,
        CompoundAssignmentSlide
    )
)

object OperatorsOverviewSlide : Slide(
    header = "Operators",
    textAlign = "center",
//    fontSize = "100%",
    summary = {
        +"Operators are special symbols that perform operations on variables and values."
    },
    content = {
        p { +"We will discuss these kotlin operators:" }
        highlightNumberedList(
            startFrom = 1,
            "Assignment operators" to {
                +"used to assign value to variable or constant"
            },
            "Arithmetic operators" to {
                +"used to perform mathematical operations"
            },
            "Comparison operators" to {
                +"used to compare two values"
            },
            "Logical operators" to {
                +"used to combine multiple boolean expressions"
            },
            "Bitwise operators" to {
                +"used to perform operations on binary representations of numbers"
            }
        )
    }
)

object AssignmentOperatorsSlide : Slide(
    header = "Assignment Operators",
    summary = {
        +"Assignment operators are used to assign value to variable or constant."
    },
    content = {
        p {
            +"Variables and constants don't need to have explicit type declaration, in case the compiler can infer the type from the value assigned to it."
        }
        kotlinPlayground(
            code = """
                val number = 3
                val text = "Hello"
                val date = LocalDate()
            """,
            executable = false
        )
        p {
            +"Variables and constants can be declared as "
            strong { +"read-only" }
            +" using "
            inlineCode("val")
            +" keyword, or mutable, using "
            inlineCode("var")
            +" keyword."
        }
        twoColumns(
            left = {
                +"Mutable variable value can be changed during the program execution."
            },
            right = {
                kotlinPlayground(
                    code = """
                        var name: String = "John"
                        name = "Jane"
                        println(name)
                    """,
                    executable = false
                )
            }
        )
        twoColumns(
            left = {
                +"This will throw an error, because name is declared as read-only."
            },
            right = {
                kotlinPlayground(
                    code = """
                        val name: String = "John"
                        name = "John"
                        println(name)
                    """,
                    executable = false
                )
            }
        )
        blockQuote { +"Can you think of reason why we would want to declare a variable as read-only and reasons why we would want to declare a variable as mutable?" }
    }
)

object ArithmeticOperatorsSlide : Slide(
    header = "Arithmetic Operators",
    summary = {
        +"Given two variables "
        inlineCode("var a = 5")
        +" and "
        inlineCode("var b = 2")
        +"."
    },
    content = {
        table {
            thead {
                tr {
                    th { +"Operator" }
                    th { +"Name" }
                    th { +"Example" }
                    th { +"Result" }
                }
            }
            tbody {
                tr {
                    td { inlineCode("+") }
                    td { +"Addition" }
                    td { inlineCode("a + b") }
                    td { inlineCode("7") }
                }
                tr {
                    td { inlineCode("-") }
                    td { +"Subtraction" }
                    td { inlineCode("a - b") }
                    td { inlineCode("3") }
                }
                tr {
                    td { inlineCode("*") }
                    td { +"Multiplication" }
                    td { inlineCode("a * b") }
                    td { inlineCode("10") }
                }
                tr {
                    td { inlineCode("/") }
                    td { +"Division" }
                    td { inlineCode("a / b") }
                    td { inlineCode("2") }
                }
                tr {
                    td { inlineCode("%") }
                    td { +"Modulus" }
                    td { inlineCode("a % b") }
                    td { inlineCode("1") }
                }
                tr {
                    td { inlineCode("++") }
                    td { +"Increment" }
                    td { inlineCode("a++") }
                    td { inlineCode("6") }
                }
                tr {
                    td { inlineCode("--") }
                    td { +"Decrement" }
                    td { inlineCode("a--") }
                    td { inlineCode("4") }
                }
            }
        }
    },
    fontSize = "80%"
)

object ArithmeticOperatorsExamplesSlide : Slide(
    header = "Arithmetic Operators",
    content = {
        kotlinPlayground(
            code = """
                fun main() {
                    var a = 10
                    var b = 3

                    println("a + b = ${'$'}{a + b}")  // 13
                    println("a - b = ${'$'}{a - b}")  // 7
                    println("a * b = ${'$'}{a * b}")  // 30
                    println("a / b = ${'$'}{a / b}")  // 3 (integer division)
                    println("a % b = ${'$'}{a % b}")  // 1 (remainder)

                    // Pre-increment vs Post-increment
                    println("a++ = ${DOLLAR}{a++}")      // 10 (returns old value, then increments)
                    println("a = ${'$'}a")            // 11
                    println("++a = ${'$'}{++a}")      // 12 (increments first, then returns new value)

                    // Floating point division
                    val c = 10.0
                    val d = 3.0
                    println("c / d = ${'$'}{c / d}")  // 3.3333333333333335
                }
            """,
            executable = true
        )
        br()
        p {
            strong { +"Question:" }
            +" What's the difference between "
            inlineCode("a++")
            +" and "
            inlineCode("++a")
            +"?"
        }
    }
)

object ComparisonOperatorsSlide : Slide(
    header = "Comparison Operators",
    summary = {
        +"As the name suggest, comparison operators are used for comparing values. They always yield a boolean value."
    },
    content = {
        table {
            thead {
                tr {
                    th { +"Operator" }
                    th { +"Name" }
                    th { +"Example" }
                }
            }
            tbody {
                tr {
                    td { inlineCode("==") }
                    td { +"Equals" }
                    td { inlineCode("a == b") }
                }
                tr {
                    td { inlineCode("!=") }
                    td { +"Not equals" }
                    td { inlineCode("a != b") }
                }
                tr {
                    td { inlineCode(">") }
                    td { +"Greater than" }
                    td { inlineCode("a > b") }
                }
                tr {
                    td { inlineCode("<") }
                    td { +"Less than" }
                    td { inlineCode("a < b") }
                }
                tr {
                    td { inlineCode(">=") }
                    td { +"Greater than or equal" }
                    td { inlineCode("a >= b") }
                }
                tr {
                    td { inlineCode("<=") }
                    td { +"Less than or equal" }
                    td { inlineCode("a <= b") }
                }
            }
        }
    }
)

object ComparisonOperatorsExamplesSlide : Slide(
    header = "Comparison Operators",
    content = {
        p {
            +"Given "
            inlineCode("a = 5")
            +", "
            inlineCode("b = 3")
            +", "
            inlineCode("c = 2")
        }
        br()
        kotlinPlayground(
            code = """
                fun main() {
                    val a = 5
                    val b = 3
                    val c = 2

                    println("a == b: ${'$'}{a == b}")  // false
                    println("a != b: ${'$'}{a != b}")  // true
                    println("a > b: ${'$'}{a > b}")    // true
                    println("b < a: ${'$'}{b < a}")    // true
                    println("a >= c: ${'$'}{a >= c}")  // true
                    println("b <= c: ${'$'}{b <= c}")  // false

                    // What will these print?
                    println("a + c == b + b: ${'$'}{a + c == b + b}")  // ?
                    println("a > b && b > c: ${'$'}{a > b && b > c}")  // ?
                }
            """,
            executable = true
        )
    }
)

object LogicalOperatorsSlide : Slide(
    header = "Logical Operators",
    summary = {
        +"Logical operators are used to evaluate logic between variables or values. They always yields boolean value."
    },
    content = {
        table {
            thead {
                tr {
                    th { +"Operator" }
                    th { +"Name" }
                    th { +"Description" }
                }
            }
            tbody {
                tr {
                    td { inlineCode("&&") }
                    td { +"Logical and" }
                    td { +"Returns true if both statements are true" }
                }
                tr {
                    td { inlineCode("||") }
                    td { +"Logical or" }
                    td { +"Returns true if either statement is true" }
                }
                tr {
                    td { inlineCode("!") }
                    td { +"Logical not" }
                    td { +"Reverses the result" }
                }
                tr {
                    td { inlineCode("and") }
                    td { +"Logical and" }
                    td { +"Works same as && but doesn't short-circuit" }
                }
                tr {
                    td { inlineCode("or") }
                    td { +"Logical or" }
                    td { +"Works same as || but doesn't short-circuit" }
                }
            }
        }
        br()
        p {
            strong { +"Short circuiting" }
            +" means that if the first part of the statement is false, the second part will not be evaluated."
        }
    },
    fontSize = "80%"
)

object LogicalOperatorsExamplesSlide : Slide(
    header = "Logical Operators",
    content = {
        kotlinPlayground(
            code = """
                fun main() {
                    val name: String? = null
                    val age: Int? = 25

                    // Logical AND
                    if (name != null && name.length > 0) {
                        println("Name is not empty: ${'$'}name")
                    } else {
                        println("Name is null or empty")
                    }

                    // Logical OR
                    if (name == null || name.isEmpty()) {
                        println("Name is missing")
                    }

                    // Logical NOT
                    val isNotNull = name != null
                    val isEmpty = !isNotNull
                    println("Is empty: ${'$'}isEmpty")

                    // Complex conditions
                    if ((age != null && age >= 18) && (name != null && name.isNotBlank())) {
                        println("Valid adult user")
                    } else {
                        println("Invalid user data")
                    }
                }
            """,
            executable = true
        )
    }
)

object BitwiseOperatorsSlide : Slide(
    header = "Bitwise Operators",
    content = {
        table {
            thead {
                tr {
                    th { +"Operator" }
                    th { +"Name" }
                    th { +"Example" }
                    th { +"Result" }
                }
            }
            tbody {
                tr {
                    td { inlineCode("and") }
                    td { +"Binary and" }
                    td { inlineCode("5 and 3") }
                    td { inlineCode("1") }
                }
                tr {
                    td { inlineCode("or") }
                    td { +"Binary or" }
                    td { inlineCode("5 or 3") }
                    td { inlineCode("7") }
                }
                tr {
                    td { inlineCode("xor") }
                    td { +"Binary xor" }
                    td { inlineCode("5 xor 3") }
                    td { inlineCode("6") }
                }
                tr {
                    td { inlineCode("shl") }
                    td { +"Signed left shift" }
                    td { inlineCode("5 shl 1") }
                    td { inlineCode("10") }
                }
                tr {
                    td { inlineCode("shr") }
                    td { +"Signed right shift" }
                    td { inlineCode("5 shr 1") }
                    td { inlineCode("2") }
                }
                tr {
                    td { inlineCode("ushr") }
                    td { +"Unsigned right shift" }
                    td { inlineCode("5 ushr 1") }
                    td { inlineCode("2") }
                }
                tr {
                    td { inlineCode(".inv()") }
                    td { +"Binary complement" }
                    td { inlineCode("5.inv()") }
                    td { inlineCode("-6") }
                }
            }
        }
        br()
        kotlinPlayground(
            code = """
                fun main() {
                    val a = 5   // Binary: 101
                    val b = 3   // Binary: 011

                    println("a and b = ${'$'}{a and b}")     // 1 (Binary: 001)
                    println("a or b = ${'$'}{a or b}")       // 7 (Binary: 111)
                    println("a xor b = ${'$'}{a xor b}")     // 6 (Binary: 110)
                    println("a shl 1 = ${'$'}{a shl 1}")     // 10 (Binary: 1010)
                    println("a.inv() = ${'$'}{a.inv()}")     // -6
                }
            """,
            executable = true
        )
    },
    fontSize = "70%"
)

object CompoundAssignmentSlide : Slide(
    header = "Compound Assignment",
    summary = {
        +"Combines assignment operators with arithmetic operators."
    },
    content = {
        table {
            thead {
                tr {
                    th { +"Operator" }
                    th { +"Example" }
                    th { +"Equivalent" }
                }
            }
            tbody {
                tr {
                    td { inlineCode("+=") }
                    td { inlineCode("a += b") }
                    td { inlineCode("a = a + b") }
                }
                tr {
                    td { inlineCode("-=") }
                    td { inlineCode("a -= b") }
                    td { inlineCode("a = a - b") }
                }
                tr {
                    td { inlineCode("*=") }
                    td { inlineCode("a *= b") }
                    td { inlineCode("a = a * b") }
                }
                tr {
                    td { inlineCode("/=") }
                    td { inlineCode("a /= b") }
                    td { inlineCode("a = a / b") }
                }
                tr {
                    td { inlineCode("%=") }
                    td { inlineCode("a %= b") }
                    td { inlineCode("a = a % b") }
                }
            }
        }
        br()
        kotlinPlayground(
            code = """
                fun main() {
                    var score = 100
                    score += 50    // score is now 150
                    score -= 25    // score is now 125
                    score *= 2     // score is now 250
                    score /= 5     // score is now 50
                    score %= 7     // score is now 1

                    println("Final score: ${'$'}score")  // 1
                }
            """,
            executable = true
        )
    }
)
