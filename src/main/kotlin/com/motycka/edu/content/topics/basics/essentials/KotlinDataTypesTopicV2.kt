package com.motycka.edu.content.topics.basics.essentials

import com.motycka.edu.model.Slide
import com.motycka.edu.model.Topic
import com.motycka.edu.model.inlineCode
import com.motycka.edu.model.kotlinPlayground
import com.motycka.edu.model.twoColumns
import kotlinx.html.*

object KotlinDataTypesTopicV2 : Topic(
    title = "Data Types in Kotlin",
    slides = listOf(
        DataTypesOverviewSlideV2,
        IntegerTypesSlide,
        IntegerExamplesSlide,
        TwosComplementSlide,
        BinaryHexadecimalSlide,
        OtherPrimitiveTypesSlide,
        AnyTypeSlideV2,
        NullSafetySlide,
        TypeInferenceSlideV2,
        SmartCastsSlide,
        TypeConversionsSlide
    )
)

object DataTypesOverviewSlideV2 : Slide(
    header = "Data Types",
    summary = {
        +"Kotlin is a statically typed language, which means that every variable and expression has a type that is known at compile time."
    },
    content = {
        twoColumns(
            left = {
                div {
                    +"In Kotlin, there are two main categories of data types:"
                    ul {
                        li {
                            +"Primitive types"
                            div {
                                ul {
                                    li {
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
                                    }
                                }
                            }
                        }
                        li {
                            +"Non-primitive types"
                            div {
                                ul {
                                    li {
                                        inlineCode("String")
                                        +", "
                                        inlineCode("Arrays")
                                        +", "
                                        inlineCode("Classes")
                                        +", ..."
                                    }
                                }
                            }
                        }
                    }
                }
            },
            right = {
                div {
                    +"Unlike Java, in Kotlin all types are objects, which means that you can call member functions and properties on any variable."
                    br()
                    br()
                    +"Every value has methods and properties that can be called on it:"
                    kotlinPlayground(
                        code = """
                            fun main() {
                                val number = 42
                                val textRepresentation = number.toString()

                                println(textRepresentation)
                            }
                        """.trimIndent()
                    )
                }
            }
        )
    }
)

object IntegerTypesSlide : Slide(
    header = "Integer types",
    content = {
        h4 { +"Signed Integer types" }
        table {
            tbody {
                tr {
                    td { inlineCode("Byte") }
                    td { +"1 byte" }
                    td { +"whole number from -128 to 127" }
                }
                tr {
                    td { inlineCode("Short") }
                    td { +"2 bytes" }
                    td { +"whole number from -32768 to 32767" }
                }
                tr {
                    td { inlineCode("Int") }
                    td { +"4 bytes" }
                    td { +"whole number from -2147483648 to 2147483647" }
                }
                tr {
                    td { inlineCode("Long") }
                    td { +"8 bytes" }
                    td { +"whole number from -9223372036854775808 to 9223372036854775807" }
                }
            }
        }
        br()
        br()
        h4 { +"Unsigned Integer types" }
        table {
            tbody {
                tr {
                    td { inlineCode("UByte") }
                    td { +"1 byte" }
                    td { +"whole number from 0 to 255" }
                }
                tr {
                    td { inlineCode("UShort") }
                    td { +"2 bytes" }
                    td { +"whole number from 0 to 65535" }
                }
                tr {
                    td { inlineCode("UInt") }
                    td { +"4 bytes" }
                    td { +"whole number from 0 to 4,294,967,295 (2³² - 1)" }
                }
                tr {
                    td { inlineCode("ULong") }
                    td { +"8 bytes" }
                    td { +"whole number from 0 to 18,446,744,073,709,551,615 (2⁶⁴ - 1)" }
                }
            }
        }
        br()
        br()
        h4 { +"Floating-point types" }
        table {
            tbody {
                tr {
                    td { inlineCode("Float") }
                    td { +"4 bytes" }
                    td { +"fractional number up to 7 decimal digits" }
                }
                tr {
                    td { inlineCode("Double") }
                    td { +"8 bytes" }
                    td { +"fractional number up to 15 decimal digits" }
                }
            }
        }
    }
)

object IntegerExamplesSlide : Slide(
    header = "Examples of integer types",
    content = {
        kotlinPlayground(
            code = """
                val index: Byte = 127
                val smallNumber: Short = 32767
                val number: Int = 2147483647
                val bigNumber: Long = 9223372036854775807L // notice L at the end
            """.trimIndent()
        )
        kotlinPlayground(
            code = """
                val uIndex: UByte = 255u // u indicates unsigned type
                val uSmallNumber: UShort = 65535u
                val uNumber: UInt = 4294967295u
                val uBigNumber: ULong = 18446744073709551615u
            """.trimIndent()
        )
        kotlinPlayground(
            code = """
                val decimalNumber: Float = 123.12346f // f indicates float type
                val preciseNumber: Double = 123.12345886230469
            """.trimIndent()
        )
    }
)

object TwosComplementSlide : Slide(
    header = "Two's complement",
    summary = {
        +"Signed integer types use two's complement representation to handle negative numbers."
    },
    content = {
        div {
            +"Two's complement is a mathematical operation to reverse the sign of integer types."
            div {
                +"To get binary representation of negative number:"
                ol {
                    li { +"The binary representation of absolute value of -5 (=5) is "; inlineCode("0101") }
                    li { +"Inverting each bit gives you "; inlineCode("1010") }
                    li { +"Adding 1 to the least significant bit gives you the binary representation of -5, which is: "; inlineCode("1011") }
                }
            }
        }
    }
)

object BinaryHexadecimalSlide : Slide(
    header = "Binary and hexadecimal representation",
    summary = {
        p {
            +"You can initialize integers using binary or hexadecimal representation."
            br()
            +"You do that by prefixing the number with "
            inlineCode("0b")
            +" followed by 1s and 0s for binary representation,"
            br()
            +"or "
            inlineCode("0x")
            +" followed by characters or hexadecimal representation."
        }
    },
    content = {
        table {
            style = "width: 100%"
            tbody {
                tr {
                    td {
                        kotlinPlayground(
                            code = """
                                fun main() {
                                    val binaryInt = 0b00001010
                                    println(binaryInt) 			// prints 10
                                    println(binaryInt == 10) 	// prints true
                                }
                            """.trimIndent()
                        )
                    }
                    td {
                        inlineCode("0b0")
                        +" = 0"
                        br()
                        inlineCode("0b1")
                        +" = 1"
                        br()
                        inlineCode("0b00001010")
                        +" = 10"
                    }
                }
                tr {
                    td {
                        kotlinPlayground(
                            code = """
                                fun main() {
                                    val hexInt = 0x00AA
                                    println(hexInt) 		// prints 170
                                    println(hexInt == 170) 	// prints true
                                }
                            """.trimIndent()
                        )
                    }
                    td {
                        inlineCode("0x0")
                        +" = 0"
                        br()
                        inlineCode("0xF")
                        +" = 15"
                        br()
                        inlineCode("0x00AA")
                        +" = 170"
                    }
                }
            }
        }
    }
)

object OtherPrimitiveTypesSlide : Slide(
    header = "Other primitive types",
    content = {
        table {
            tbody {
                tr {
                    td { inlineCode("Boolean") }
                    td { +"1 byte" }
                    td { +"value of true or false" }
                }
                tr {
                    td { inlineCode("Char") }
                    td { +"2 bytes" }
                    td { +"a single 16-bit Unicode character" }
                }
                tr {
                    td { inlineCode("String") }
                    td { +"approximately 2 bytes per character" }
                    td { +"UTF-16 encoded string of characters" }
                }
                tr {
                    td { inlineCode("Array") }
                    td { +"depends" }
                    td { +"Fixed number of values of the same type or its subtypes." }
                }
            }
        }
        kotlinPlayground(
            code = """
                val isTrue: Boolean = true
                val character: Char = 'A'
                val text: String = "Hello, world!"

                val arrayOfStrings = arrayOf("hello", "world", "kotlin", "is", "fun")
            """.trimIndent()
        )
    }
)

object AnyTypeSlideV2 : Slide(
    header = "Any type",
    summary = {
        inlineCode("Any")
        +" is the root of the Kotlin class hierarchy."
        br()
        +"This means that all type classes in Kotlin are subclasses of "
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
        kotlinPlayground(
            code = """
                val someValue: Any = "This is a string"

                if (someValue is String) {
                    println("The value is a string")
                } else {
                    println("The value is not a string")
                }
            """.trimIndent()
        )
    }
)

object NullSafetySlide : Slide(
    header = "Null Safety",
    summary = {
        +"Any type can be nullable by adding a "
        inlineCode("?")
        +" after the type."
        br()
        br()
        +"When working with nullable types, you need to handle null values to avoid NullPointerException."
    },
    content = {
        twoColumns(
            left = {
                kotlinPlayground(
                    code = """
                        val nullableNumber: Int? = null
                        val nullableText: String? = null
                    """.trimIndent()
                )
            },
            right = { +"" }
        )
        twoColumns(
            left = {
                div {
                    +"You can use the "
                    inlineCode("?.")
                    +" operator to safely access properties or methods of nullable types."
                }
            },
            right = {
                kotlinPlayground(
                    code = """
                        nullableNumber?.toString()
                    """.trimIndent()
                )
            }
        )
        twoColumns(
            left = {
                div {
                    +"You can use the "
                    inlineCode("!!")
                    +" operator to tell the compiler that you are sure the value is not null."
                }
            },
            right = {
                kotlinPlayground(
                    code = """
                        nullableNumber!!.toString()
                    """.trimIndent()
                )
            }
        )
        twoColumns(
            left = {
                div {
                    +"You can also use the "
                    inlineCode("?:")
                    +" elvis operator to provide a default value if the value is null."
                }
            },
            right = {
                kotlinPlayground(
                    code = """
                        val text = nullableText ?: "default"
                    """.trimIndent()
                )
            }
        )
        twoColumns(
            left = {
                div {
                    +"Use the "
                    inlineCode("let")
                    +" function to execute a block of code only if the value is not null."
                }
            },
            right = {
                kotlinPlayground(
                    code = """
                        nullableText?.let { printText(it) }
                    """.trimIndent()
                )
            }
        )
    }
)

object TypeInferenceSlideV2 : Slide(
    header = "Type inference",
    content = {
        p {
            +"Kotlin has type inference, which means that the compiler can deduce the type of a variable from the value assigned to it."
            kotlinPlayground(
                code = """
                    val number = 42             // type inferred as Int
                    val text = "Hello, world!"  // type inferred as String
                """.trimIndent()
            )
        }
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
            """.trimIndent()
        )
        kotlinPlayground(
            code = """
                val something = getSomething()

                println(something is Int)			 // true
                println(something is Long)			 // false
                println(something::class.simpleName) // Int
            """.trimIndent()
        )
    }
)

object SmartCastsSlide : Slide(
    header = "Smart casts",
    summary = {
        +"Kotlin has smart casts, which means that the compiler can automatically cast a variable to a specific type if it knows that the variable is of that type."
    },
    content = {
        kotlinPlayground(
            code = """
                fun getSomething(): Any {
                    return 1234567890
                }
            """.trimIndent()
        )
        kotlinPlayground(
            code = """
                val something = getSomething()

                // Explicit type casting, only works if the value is of the same type
                val number = something as Int
            """.trimIndent()
        )
        kotlinPlayground(
            code = """
                val number = 1234567890
                val bigNumber = number.toLong() // Type conversion, no data loss
                val smallNumber = number.toShort() // Type conversion with DATA LOSS!
            """.trimIndent()
        )
    }
)

object TypeConversionsSlide : Slide(
    header = "Type conversions",
    summary = {
        +"Kotlin provides explicit type conversion functions for converting between different numeric types."
    },
    content = {
        p { +"Use conversion functions like "; inlineCode("toInt()"); +", "; inlineCode("toLong()"); +", "; inlineCode("toDouble()"); +", etc." }
        kotlinPlayground(
            code = """
                val intValue = 42
                val longValue = intValue.toLong()
                val doubleValue = intValue.toDouble()
                val stringValue = intValue.toString()
            """.trimIndent()
        )
    }
)
