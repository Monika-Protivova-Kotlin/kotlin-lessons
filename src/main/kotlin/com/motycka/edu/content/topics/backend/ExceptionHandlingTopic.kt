package com.motycka.edu.content.topics.backend

import com.motycka.edu.builders.codeOrderedList
import com.motycka.edu.builders.highlightOrderedList
import com.motycka.edu.model.Topic
import com.motycka.edu.model.Slide
import com.motycka.edu.model.kotlinPlayground
import com.motycka.edu.model.twoColumns
import com.motycka.edu.model.highlight
import kotlinx.html.*
import com.motycka.edu.model.inlineCode
import com.motycka.edu.model.highlight

object ExceptionHandlingTopic : Topic(
    title = "Exception Handling",
    slides = listOf(
        ExceptionIntroSlide,
        CheckedVsUncheckedExceptionsSlide,
        ThrowingHandlingExceptionsSlide,
        CommonExceptionsSlide
    )
)

object ExceptionIntroSlide : Slide(
    header = "What is an Exception",
    summary = {
        +"Exceptions are events that disrupt the normal flow of program execution."
    },
    content = {
        ul {
            li {
                +"They can arise due to various types of errors such as IO errors, arithmetic errors, null pointer access, etc."
            }
            li {
                +"Exception is just another type of Kotlin object:"
                ul {
                    li {
                        +"Exception is an instance of a "
                        inlineCode("Exception")
                        +" class or one of its "
                        strong { +"subclasses" }
                        +"."
                    }
                    li {
                        +"There are several subclasses of Exception provided in Kotlin by default, but we can create our own by extending these superclasses."
                    }
                    li {
                        +"There are two types of exceptions: "
                        strong { +"Checked" }
                        +" or "
                        strong { +"Unchecked" }
                    }
                }
            }
            li {
                +"The Exception object usually carries information about the error that occurred."
            }
            li {
                +"Exception handling allows us to control the program flow and prevent the program from terminating abruptly, which leads to a more robust and fault-tolerant software."
            }
        }
        p {
            +"Kotlin provides standard mechanisms to handle exceptions using "
            strong { +"try" }
            +", "
            strong { +"catch" }
            +", "
            strong { +"finally" }
            +" blocks:"
        }
        kotlinPlayground(
            code = """
                try {
                    // code that might throw an exception
                } catch (ex: ExceptionType) {
                    // code to handle the exception
                } finally {
                    // code that will execute irrespective of an exception occurred or not
                }
            """,
            executable = false
        )
    }
)

object CheckedVsUncheckedExceptionsSlide : Slide(
    header = "Checked vs Unchecked Exceptions",
    content = {
        twoColumns(
            left = {
                p {
                    strong { +"Checked Exceptions" }
                }
                p {
                    +"These are exceptional conditions that a well-written application should anticipate and recover from."
                }
                ul {
                    li { +"Classes that extend Throwable except RuntimeException and Error" }
                    li { +"Checked at compile-time" }
                    li { +"Compiler forces programmer to handle them" }
                    li { +"Must use try-catch or throws declaration" }
                    li {
                        +"Example: "
                        inlineCode("FileNotFoundException")
                    }
                }
            },
            right = {
                p {
                    strong { +"Unchecked Exceptions" }
                }
                p {
                    +"These represent defects in the program (bugs), often invalid arguments passed to a non-private method."
                }
                ul {
                    li { +"Classes that extend RuntimeException and Error" }
                    li { +"Checked at runtime, not compile-time" }
                    li { +"Not required to be handled explicitly" }
                    li { +"Can still be caught with try-catch" }
                    li {
                        +"Examples: "
                        inlineCode("NullPointerException")
                        +", "
                        inlineCode("ArithmeticException")
                    }
                }
            }
        )
    }
)

object ThrowingHandlingExceptionsSlide : Slide(
    header = "Throwing and Handling Exceptions",
    summary = {
        +"\"Throwing an exception\" refers to the process of creating an instance of an Exception and handing it off to the runtime system to handle."
    },
    content = {
        kotlinPlayground(
            code = """
                class NoFuelException : Throwable("The car is out of fuel!")

                class Car(private var fuelKm: Int) {

                    fun drive(driveKm: Int) {
                        var remaining = driveKm
                        while (remaining > 0) {
                            if (fuelKm <= 0) {
                                throw NoFuelException()
                            } else {
                                println("drove 1 km")
                                fuelKm--
                                remaining--
                            }
                        }
                    }
                }
                
                fun main() {
                    val car = Car(3)

                    try {
                        car.drive(4)
                    } catch (e: NoFuelException) {
                        println(e.message)
                        // handle car out of fuel situation
                    }
                }
            """,
            executable = true
        )
    }
)

object CommonExceptionsSlide : Slide(
    header = "Common Exceptions",
    summary = {
        +"Java and Kotlin provide a rich set of built-in exceptions that can be used to handle common error conditions."
    },
    content = {
        codeOrderedList(
            "NullPointerException" to {
                +"Thrown when an application attempts to use "
                inlineCode("null")
                +" in a case where an object is required."
            },
            "IllegalArgumentException" to {
                +"Thrown to indicate that a method has been passed an illegal or inappropriate argument."
            },
            "IllegalStateException" to {
                +"Thrown to indicate that program reached an illegal state, such as illegal combination of parameters or illegal sequence of method calls."
            },
            "IndexOutOfBoundsException" to {
                +"Thrown to indicate that an index of some sort (such as an array or string) is out of range."
            },
            "NumberFormatException" to {
                +"Thrown when an attempt is made to convert a string to a numeric type, but the string does not have the appropriate format."
            },
            "IOException" to {
                +"Thrown when an I/O operation fails or is interrupted."
            }
        )
    }
)
