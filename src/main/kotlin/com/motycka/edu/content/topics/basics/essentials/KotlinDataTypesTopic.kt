package com.motycka.edu.content.topics.basics.essentials

import com.motycka.edu.model.highlight
import com.motycka.edu.model.Slide
import com.motycka.edu.model.Topic
import com.motycka.edu.model.inlineCode
import com.motycka.edu.model.kotlinPlayground
import com.motycka.edu.model.twoColumns
import kotlinx.html.*
import com.motycka.edu.model.highlight

object KotlinDataTypesTopic : Topic(
    title = "Kotlin Data Types",
    slides = listOf(
        DataTypesOverviewSlide,
        KotlinDataTypesSlide,
        NumericTypesSlide,
        NumericTypesExamplesSlide,
        NonNumericDataTypesSlide,
        AnyTypeSlide,
        NullSlide,
        NullablesSlide,
        NullablesExamplesSlide,
        TypeInferenceSlide,
        TypeConversionSlide
    )
)

object DataTypesOverviewSlide : Slide(
    header = "Data types",
    textAlign = "center",
    summary = {
        +"Java/Kotlin is a high-level programming language with automatic memory management and safe reference handling (unlike C/C++ pointer which are not safe)."
    },
    content = {
        br()
        br()
        h3 { highlight("Why is this still important for us to understand?") }
        br()
        br()
        em {
            +"Understanding memory management, garbage collection, and type systems is crucial for writing efficient code, avoiding memory leaks, and preventing issues like data loss from type mismatches."
        }
    }
)

object KotlinDataTypesSlide : Slide(
    header = "Kotlin Data Types",
    summary = {
        +"Kotlin data representation is based on Java data types, but with some key differences ..."
    },
    content = {
        div {
            h4 { +"Java:" }
            ul {
                li { +"There are two groups of data types in Java - primitive and non-primitive types" }
                li { +"Primitive types are the basic data types that are built into the language" }
                li {
                    +"Primitive types ("
                    inlineCode("byte")
                    +", "
                    inlineCode("short")
                    +", "
                    inlineCode("int")
                    +", "
                    inlineCode("long")
                    +", "
                    inlineCode("float")
                    +", "
                    inlineCode("double")
                    +", "
                    inlineCode("char")
                    +", "
                    inlineCode("boolean")
                    +")"
                }
                li {
                    +"Non-primitive types ("
                    inlineCode("String")
                    +", "
                    inlineCode("Arrays")
                    +", "
                    inlineCode("Classes")
                    +", ...)"
                }
            }
        }
        br()
        div {
            h4 { +"Kotlin:" }
            ul {
                li { +"Kotlin has representation corresponding to Java primitive types, but unlike Java, they are all objects" }
                li { +"Because they are objects, they have methods and properties" }
                li { +"When Kotlin code gets compiled, the compiler will convert these objects to Java primitive types" }
            }
        }
    }
)

object NumericTypesSlide : Slide(
    header = "Numeric Types",
    content = {
        div {
            h4 { +"Integer types:" }
            typesTable(
                rows = listOf(
                    Triple("Byte", "1 byte", "whole number from -128 to 127"),
                    Triple("Short", "2 bytes", "whole number from -32768 to 32767"),
                    Triple("Int", "4 bytes", "whole number from -2147483648 to 2147483647"),
                    Triple("Long", "8 bytes", "whole number from -9223372036854775808 to 9223372036854775807"),
                )
            )
        }
        br()
        div {
            h4 { +"Unsigned Integer types:" }
            typesTable(
                rows = listOf(
                    Triple("UByte", "1 byte", "whole number from 0 to 255"),
                    Triple("UShort", "2 bytes", "whole number from 0 to 65535"),
                    Triple("UInt", "4 bytes", "whole number from 0 to 4,294,967,295"),
                    Triple("ULong", "8 bytes", "whole number from 0 to 18,446,744,073,709,551,615"),
                )
            )
        }
        br()
        div {
            h4 { +"Floating-point types:" }
            typesTable(
                rows = listOf(
                    Triple("Float", "4 bytes", "fractional number up to 7 decimal digits"),
                    Triple("Double", "8 bytes", "fractional number up to 15 decimal digits"),
                )
            )
        }
    }
)

object NumericTypesExamplesSlide : Slide(
    header = "Numeric Types",
    summary = { +"Examples" },
    content = {
        h4 { +"Integer types" }
        kotlinPlayground(
            code = """
                val byteValue: Byte = 100
                val shortValue: Short = 1000
                val intValue: Int = 100000
                val longValue: Long = 100000L  // L suffix for Long
            """.trimIndent(),
            executable = false
        )
        h4 { +"Unsigned integer types" }
        kotlinPlayground(
            code = """
                val uByteValue: UByte = 200u   // u suffix for unsigned
                val uShortValue: UShort = 50000u
                val uIntValue: UInt = 3000000000u
                val uLongValue: ULong = 18000000000000000000u
            """.trimIndent(),
            executable = false
        )
        h4 { +"Floating-point types" }
        kotlinPlayground(
            code = """
                val floatValue: Float = 3.14f  // f suffix for Float
                val doubleValue: Double = 3.14159265359
            """.trimIndent(),
            executable = false
        )
    }
)

object NonNumericDataTypesSlide : Slide(
    header = "Non-numeric Data Types",
    content = {
        typesTable(
            rows = listOf(
                Triple("Boolean", "1 byte", "value of true or false"),
                Triple("Char", "2 bytes", "a single 16-bit Unicode character"),
                Triple("String", "approximately 2 bytes per character", "UTF-16 encoded string of characters"),
                Triple("Array", "depends", "Fixed number of values of the same type or its subtypes"),
            )
        )
        br()
        kotlinPlayground(
            code = """
                val charValue: Char = 'A'
                val stringValue: String = "Hello, Kotlin!"
                val arrayValue: Array<Int> = arrayOf(1, 2, 3, 4, 5)
            """.trimIndent(),
            executable = false
        )
        br()
        em { +"Unless there are specific memory performance requirements, you should prefer using Collections over Arrays." }
    }
)

object AnyTypeSlide : Slide(
    header = "Any type",
    summary = {
        inlineCode("Any")
        +" is the root of the Kotlin class hierarchy. This means that all type classes in Kotlin are subclasses of "
        inlineCode("Any")
        +"."
    },
    content = {
        p {
            +"Anytime we don't know the type of variable, parameter or return type, we can use "
            inlineCode("Any")
            +" to accept any type. However, this is not recommended and should be used with caution."
        }
        p {
            +"Any type is equivalent to Java's "
            inlineCode("Object")
            +" type."
        }
        br()
        kotlinPlayground(
            code = """
                fun checkType(value: Any) {
                    when (value) {
                        is String -> println("This is a String: ${'$'}value")
                        is Int -> println("This is an Int: ${'$'}value")
                        is Boolean -> println("This is a Boolean: ${'$'}value")
                        else -> println("Unknown type: ${'$'}{value::class.simpleName}")
                    }
                }

                fun main() {
                    checkType(123)
                }
            """.trimIndent()
        )
    }
)

object NullSlide : Slide(
    header = "null",
    summary = {
        +"In Java/Kotlin, null is a special value that represents the absence of an instance. It is used to indicate that a reference variable doesn't point to any memory location or object."
    },
    content = {
        p {
            +"Some key point to keep in mind when working with nulls in Java/Kotlin code ..."
        }
        ul {
            li { +"Since Kotlin and Java are interoperable, you may need to explicitly handle null values when calling Java methods from Kotlin." }
            li { +"In Java you can assign null to any reference variable (non-primitive types: objects, array, interface, etc)" }
            li { +"For object reference types, the default value is null when they are defined as class members and not explicitly initialized" }
            li {
                +"If you try to invoke a method or access a property on a reference variable with a null value, you will get a "
                inlineCode("NullPointerException")
                +". This is a runtime exception in Java."
            }
            li { +"In Kotlin, object references that may hold null values must be explicitly declared as nullable types." }
            li {
                +"You can use null in comparison operations. For example, to check if an object is null, you can use "
                inlineCode("if (object != null)" )
                +"."
            }
        }
    },
    fontSize = "70%"
)

object NullablesSlide : Slide(
    header = "Nullables",
    summary = {
        +"Nullables are Kotlin types that can hold a null value."
    },
    fontSize = "70%",
    content = {
        p {
            +"Any type can be nullable by adding a "
            inlineCode("?")
            +" after the type. When working with nullable types, you need to handle null values to avoid "
            inlineCode("NullPointerException")
            +"."
        }
        kotlinPlayground(
            code = """
                val nullableNumber: Int? = null
                val nullableText: String? = null
            """.trimIndent(),
            executable = false
        )
        twoColumns(
            left = {
                p {
                    +"You can use the "
                    highlight("?.")
                    +" operator to safely access properties or methods of nullable types."
                }
            },
            right = {
                kotlinPlayground(
                    code = "nullableNumber?.toString()",
                    executable = false
                )
            }
        )
        twoColumns(
            left = {
                p {
                    +"You can use the "
                    highlight("!!")
                    +" operator to tell the compiler that you are sure the value is not null."
                }
            },
            right = {
                kotlinPlayground(
                    code = "nullableNumber!!.toString()",
                    executable = false
                )
            }
        )
        twoColumns(
            left = {
                p {
                    +"You can also use the "
                    highlight("?:")
                    +" elvis operator to provide a default value if the value is null."
                }
            },
            right = {
                kotlinPlayground(
                    code = """val text = nullableText ?: "default"""",
                    executable = false
                )
            }
        )
        twoColumns(
            left = {
                p {
                    +"Use the let function to execute a block of code only if the value is not null."
                }
            },
            right = {
                kotlinPlayground(
                    code = "nullableText?.let { printText(it) }",
                    executable = false
                )
            }
        )
    }
)

object NullablesExamplesSlide : Slide(
    header = "Nullables",
    summary = {
        +"Example"
    },
    content = {
        kotlinPlayground(
            code = """
                fun processString(nullableString: String?) {

                    val length = nullableString?.length ?: 0 // Safe call and Elvis operator
                    println("String length: ${'$'}length")

                    nullableString?.let {   // Safe call with let
                        println("String is not null: ${'$'}it")
                    } ?: run {              // Safe call and Elvis operator
                        println("String is null")
                    }

                    val maybeNull = nullableString?.length // Safe call operator ? returns null instead of throwing exception
                    val notNull = nullableString!!.length // Not-null assertion operator !! throws exception if null
                }


                fun main() {

                    var nullableString: String? = "Hello"
                    processString(nullableString)

                    nullableString = null // This is allowed
                    processString(nullableString)
                }
            """
        )
    }
)

object TypeInferenceSlide : Slide(
    header = "Type inference & Type checks",
    summary = {
        +"Type inference is a feature that allows the compiler to automatically determine the data type of a variable based on the value assigned to it."
    },
    content = {
        p { +"For example, this is how you can declare a variable with type inference:" }
        kotlinPlayground(
            code = """
                fun getSomething(): Any {
                    return 1234567890
                }

                fun main() {
                    val something = getSomething()
                    println(something)
                }
            """,
            executable = true
        )
        p {
            +"You can check the type of variable using the "
            inlineCode("is")
            +" operator."
        }
        kotlinPlayground(
            code = """
                fun getSomething(): Any {
                    return 1234567890
                }

                fun main() {
                    val something = getSomething()

                    println(something is Int)
                    println(something is Long)
                    println(something::class.simpleName)
                }
            """,
            executable = true
        )
    }
)

object TypeConversionSlide : Slide(
    header = "Type Conversion",
    summary = {
        +"Type conversion is a method of converting one data type to another. Keep in mind, that type conversion may result in data loss!"
    },
    content = {
        div {
            h4 { +"Type Casting:" }
            kotlinPlayground(
                code = """
                    fun main() {
                        val obj: Any = "Hello"
                        val str = obj as String  // Explicit cast
                        val safeStr = obj as? String // Safe cast, returns null if fails
                    }
                """,
                executable = true
            )
        }
        br()
        div {
            h4 { +"Type Conversion:" }
            kotlinPlayground(
                code = """
                    fun main() {
                        val intValue = 100
                        val longValue = intValue.toLong()   // Convert to Long
                        val shortValue = intValue.toShort() // May lose data if too large!
                        val floatValue = intValue.toFloat()
                        val stringValue = intValue.toString()
                    }
                """,
                executable = true
            )
        }
        br()
        div {
            em { +"Assigning smaller data type to larger data type - generally doesn't result in data loss." }
            br()
            em { +"Assigning larger data type to smaller data type - may result in data loss." }
        }
    }
)


fun FlowContent.typesTable(
    rows: List<Triple<String, String, String>>
) {
    table {
        style = "width: 100%; height: 100%; margin: 0px;"
        tbody {
            rows.forEach {
                tr {
                    td { inlineCode(it.first) }
                    td { +it.second }
                    td { +it.third }
                }
            }
        }
    }
}
