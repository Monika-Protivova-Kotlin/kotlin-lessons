package com.motycka.edu.content.topics.basics

import com.motycka.edu.content.topics.backend.CheckedVsUncheckedExceptionsSlide
import com.motycka.edu.content.topics.backend.CommonExceptionsSlide
import com.motycka.edu.content.topics.backend.CustomExceptionsSlide
import kotlinx.html.*
import com.motycka.edu.model.Topic
import com.motycka.edu.model.Slide
import com.motycka.edu.model.kotlinPlayground
import com.motycka.edu.model.inlineCode

object ExceptionsTopic : Topic(
    title = "Exceptions",
    subtitle = "and error handling",
    slides = listOf(
        ExceptionOverviewSlide,
        CheckedVsUncheckedExceptionsSlide,
        CommonExceptionsSlide,
        CustomExceptionsSlide
    )
)

object ExceptionHandlingTopic : Topic(
    title = "Handling Exceptions",
    subtitle = "throwing and catching exceptions",
    slides = listOf(
        ExceptionHandlingSlide,
        ThrowingExceptionsSlide,
        ThrowingHandlingExampleSlide,
        UncheckedExceptionExampleSlide
    )
)

object ExceptionOverviewSlide : Slide(
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
                    li { +"Exception is an instance of a "; inlineCode("Exception"); +" class or one of its "; strong { +"subclasses" }; +"." }
                    li { +"There are several subclasses of Exception provided in Kotlin by default, but we can create our own by extending these superclases." }
                    li { +"There are two types of exceptions: "; strong { +"Checked" }; +" or "; strong { +"Unchecked" } }
                }
            }
            li {
                +"The Exception object usually carries information about the error that occurred."
            }
            li {
                +"Exception handling allows us to control the program flow and prevent the program from terminating abruptly, which leads to a more robust and fault-tolerant software."
            }
        }
    }
)

object CheckedExceptionsSlide : Slide(
    header = "Checked Exceptions",
    summary = {
        +"These are exceptional conditions that a well-written application should anticipate and recover from."
    },
    content = {
        p {
            +"Checked exceptions are the classes that extend Throwable class except RuntimeException and Error."
        }
        p {
            +"Checked exceptions are checked at compile-time. "
            +"The compiler forces the programmer to catch these exceptions, i.e., the programmer needs to provide an exception handling mechanism through a try-catch block or throws keyword for checked exceptions. "
            +"If not, the code will not compile."
        }
        p {
            +"For example, "
            inlineCode("FileNotFoundException")
            +" will be thrown when a file that needs to be opened cannot be found."
        }
    }
)

object UncheckedExceptionsSlide : Slide(
    header = "Unchecked Exceptions",
    summary = {
        +"These represent defects in the program (bugs), often invalid arguments passed to a non-private method."
    },
    content = {
        p {
            +"Unchecked exceptions are the classes that extend RuntimeException class and the Error class."
        }
        p {
            +"Unchecked exceptions are not checked at compile-time, but at runtime."
        }
        p {
            +"Examples are "
            inlineCode("ArrayIndexOutOfBoundsException")
            +", "
            inlineCode("NullPointerException")
            +", "
            inlineCode("ArithmeticException")
            +", "
            inlineCode("NumberFormatException")
            +" etc."
        }
    }
)

object ExceptionHandlingSlide : Slide(
    header = "Handling exceptions",
    summary = {
        +"Kotlin provides a standard mechanisms to handle exceptions using "
        code { strong { +"try" } }
        +", "
        code { strong { +"catch" } }
        +", "
        code { strong { +"finally" } }
        +" blocks."
    },
    content = {
        kotlinPlayground(
            code = """
                try {
                    // code that might throw an exception
                } catch (ex: ExceptionType) {
                    // code to handle the exception
                } finally {
                    // code that will execute irrespective of an exception occurred or not
                }
            """.trimIndent(),
            executable = false
        )
        ul {
            li {
                +"The "
                inlineCode("try")
                +" block contains the code that "
                strong { +"might" }
                +" throw an exception."
            }
            li {
                +"The "
                inlineCode("catch")
                +" block contains the code that is executed when an exception of "
                strong { +"given type" }
                +" occurs in the try block."
            }
            li {
                +"The "
                inlineCode("finally")
                +" block contains the code that is always executed, regardless of whether an exception occurs or not."
            }
        }
    }
)

object ThrowingExceptionsSlide : Slide(
    header = "Throwing exceptions",
    summary = {
        +"\"Throwing an exception\" refers to the process of creating an instance of an Exception (or its subclass) and handing it off to the runtime system to handle."
    },
    content = {
        p {
            +"It's a way of signaling that a method cannot complete its normal computation due to some kind of exceptional condition."
        }
        p {
            +"There are two keywords associated with throwing exceptions:"
        }
        ul {
            li {
                +"The "
                inlineCode("throw")
                +" keyword is used to \"emit\" an exception from any block of code. "
                +"We can throw either checked or unchecked exceptions."
            }
            li {
                +"If you want to declare that a method may throw an exception, "
                +"you can use the "
                inlineCode("@Throws")
                +" annotation."
            }
            li {
                +"Declaring that a method throws an exception is a way of signaling to the caller that the method may not complete normally, so that the caller can handle it."
            }
        }
    }
)

const val CAR_CLASS = """
// Class that throws exception
class Car(private var fuelKm: Int) {

    @Throws(NoFuelException::class)
    fun drive(driveKm: Int) {
        var driveKm = driveKm
        while (driveKm > 0) {
            if (fuelKm <= 0) {
                // exception in thrown on car out of fuel event
                throw NoFuelException()
            } else {
                println("drove 1 km")
                fuelKm--
                driveKm--
            }
        }
    }
}
"""

const val NO_FUEL_EXCEPTION_CLASS = """
// NoFuelException exception definition
class NoFuelException : Throwable("The car is out of fuel!")
"""

const val EXCEPTIONS_MAIN = """
$CAR_CLASS
$NO_FUEL_EXCEPTION_CLASS

fun main() {
    val car = Car(3)

    try {
        car.drive(4)
    } catch (e: NoFuelException) { // compiler will force catch block here
        println(e.message)
        // somehow handle car out of fuel situation
    }
}
"""

object ThrowingHandlingExampleSlide : Slide(
    header = "Throwing and handling exceptions",
    content = {
        kotlinPlayground(
            code = EXCEPTIONS_MAIN,
//            selectLines = 23..32,
            executable = true
        )
//        table {
//            style = "width: 100%"
//            tbody {
//                tr {
//                    td {
//                        +"Class that throws exception"
//                        kotlinPlaygroundCode(
//                            code = CAR_CLASS,
//                            executable = false
//                        )
//                    }
//                    td {
//                        +"NoFuelException exception definition"
//                        kotlinPlaygroundCode(
//                            code = NO_FUEL_EXCEPTION_CLASS,
//                            executable = false
//                        )
//                        p {
//                            +"Running this code will print"
//                            kotlinPlaygroundCode(
//                                code = """
//                                    drove 1 km
//                                    drove 1 km
//                                    drove 1 km
//                                    The car is out of fuel!
//                                """.trimIndent(),
//                                executable = false
//                            )
//                        }
//                    }
//                }
//            }
//        }
    }
)

object UncheckedExceptionExampleSlide : Slide(
    header = "Throwing and handling exceptions",
    content = {
        +"In this example we try to divide number by 0, which is illegal. "
        +"The compiler will let us compile this code, because there is no checked exception. "
        +"When executed, the program will end with:"
        br()
        br()
        inlineCode("Exception in thread \"main\" java.lang.ArithmeticException: / by zero")
        kotlinPlayground(
            code = """
                fun main() {
                    val number = 100 / 0 // will end with "Exception in thread "main" java.lang.ArithmeticException: / by zero"
                }
            """.trimIndent(),
            executable = true
        )
        p {
            +"However, we can still handle the unchecked exception too, we are just not warned by the compiler."
        }
        kotlinPlayground(
            code = """
                fun main() {
                    val dividend = 100
                    val divisor = 0

                    try {
                        val quotient = dividend / divisor
                    } catch (e: Exception) {
                        println(e.message)
                    }
                }
            """.trimIndent(),
            executable = true
        )
    }
)
