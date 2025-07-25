package com.motycka.edu.content.topics.basics.functions

import kotlinx.html.*
import com.motycka.edu.model.Topic
import com.motycka.edu.model.Slide
import com.motycka.edu.model.kotlinPlayground
import com.motycka.edu.model.inlineCode

object ScopeFunctionsTopic : Topic(
    title = "Scope Functions",
    slides = listOf(
        ScopeFunctionsIntroSlide,
        ScopeFunctionLetSlide,
        ScopeFunctionRunSlide,
        ScopeFunctionWithSlide,
        ScopeFunctionApplySlide,
        ScopeFunctionAlsoSlide,
    )
)

object ScopeFunctionsIntroSlide : Slide(
    header = "Scope Functions",
    summary = {
        +"Scope functions allow you to execute a block of code within the context of an object."
    },
    content = {
        p {
            +"When you use a scope function, you can access the object's properties and functions without having to use the object's name."
        }
        p {
            +"The scope functions in Kotlin are "
            inlineCode("let")
            +", "
            inlineCode("run")
            +", "
            inlineCode("with")
            +", "
            inlineCode("apply")
            +", and "
            inlineCode("also")
            +"."
        }
        p {
            +"Each scope function has a different context object and return value, which makes them useful for different use cases."
        }
        br()
        table {
            style = "font-size: 80%"
            thead {
                tr {
                    td { strong { +"Function" } }
                    td { strong { +"Context object" } }
                    td { strong { +"Return value" } }
                    td { strong { +"Usage" } }
                }
            }
            tbody {
                tr {
                    td { inlineCode("let") }
                    td { strong { +"it" } }
                    td { +"Result of the lambda expression" }
                    td { +"Execute a block of code on the result of a call chain or to work with nullable objects" }
                }
                tr {
                    td { inlineCode("run") }
                    td { strong { +"this" } }
                    td { +"Result of the lambda expression" }
                    td { +"Often used when you want to perform multiple operations on an object and return a result" }
                }
                tr {
                    td { inlineCode("with") }
                    td { strong { +"this" } }
                    td { +"Result of the lambda expression" }
                    td { +"Execute a block of code on an object" }
                }
                tr {
                    td { inlineCode("apply") }
                    td { strong { +"this" } }
                    td { +"The context object itself" }
                    td { +"Typically used for initializing or configuring an object." }
                }
                tr {
                    td { inlineCode("also") }
                    td { strong { +"it" } }
                    td { +"The context object itself" }
                    td { +"Perform additional operations on an object without changing the object itself" }
                }
            }
        }
    }
)

object ScopeFunctionLetSlide : Slide(
    header = "let",
    summary = {
        +"Execute a block of code on the result of a call chain or to work with nullable objects."
    },
    content = {
        p {
            +"The context object of the "
            inlineCode("let")
            +" function is referred to as "
            inlineCode("it")
            +" and the return value is the result of the lambda expression."
        }
        p {
            +"The "
            inlineCode("let")
            +" function is particularly useful when used with a nullable types, "
            +"because it allows us to chain multiple operations on a nullable object."
        }
        kotlinPlayground(
            code = """
                data class Message(
                    val text: String?,
                    var acknowledged: Boolean = false
                ) {
                    fun send(): Message {
                        return Message(text = "Got it!", acknowledged = true)
                    }
                }

                fun main() {
                    val message = Message(text = "Hello")

                    val response = message.send()

                    val text = response.text?.let {
                        println("Received message: ${DOLLAR}it")
                    }
                }
                """,
            executable = true
        )
    }
)

object ScopeFunctionRunSlide : Slide(
    header = "run",
    summary = {
        +"Often used when you want to perform multiple operations on an object and return a result."
    },
    content = {
        p {
            +"The context object of the "
            inlineCode("run")
            +" function is referred to as "
            inlineCode("this")
            +" and the return value is the result of the lambda expression."
        }
        kotlinPlayground(
            code = """
                data class Message(
                    val text: String?,
                    var acknowledged: Boolean = false
                ) {
                    fun send(): Message {
                        return Message(text = "Got it!", acknowledged = true)
                    }
                }

                fun main() {
                    val message = Message(text = "Hello").run {
                        if (send().acknowledged) {
                            println("Message acknowledged")
                        } else {
                            println("Message not acknowledged")
                        }
                        this
                    }
                }
                """,
            executable = true
        )
    }
)

object ScopeFunctionWithSlide : Slide(
    header = "with",
    summary = {
        +"User when you want to execute a block of code on an object."
    },
    content = {
        p {
            +"The context object of the "
            inlineCode("with")
            +" function is referred to as "
            inlineCode("this")
            +" and it has no return value."
        }
        kotlinPlayground(
            code = """
                data class Message(
                    val text: String?,
                    var acknowledged: Boolean = false
                ) {
                    fun send(): Message {
                        return Message(text = "Got it!", acknowledged = true)
                    }
                }

                fun main() {
                    val message =  Message(text = "Hello")

                    with(message) {
                        if (send().acknowledged) {
                            println("Message acknowledged")
                        } else {
                            println("Message not acknowledged")
                        }
                    }
                }
                """,
            executable = true
        )
    }
)

object ScopeFunctionApplySlide : Slide(
    header = "apply",
    summary = {
        +"Typically used for initializing or configuring an object."
    },
    content = {
        p {
            +"The context object of the "
            inlineCode("apply")
            +" function is referred to as "
            inlineCode("this")
            +" and it returns the modified context object."
        }
        kotlinPlayground(
            code = """
                data class Message(
                    val text: String?,
                    var acknowledged: Boolean = false
                ) {
                    fun send(): Message {
                        return Message(text = "Got it!", acknowledged = true)
                    }
                }

                fun main() {
                       val message =  Message(text = "Hello").apply {
                        acknowledged = true
                    }
                }
                """,
            executable = true
        )
    }
)

object ScopeFunctionAlsoSlide : Slide(
    header = "also",
    summary = {
        +"used when you want to perform additional operations on an object without changing the object itself."
    },
    content = {
        p {
            +"The context object of the "
            inlineCode("also")
            +" function is referred to as "
            inlineCode("it")
            +" and it returns the unmodified context object."
        }
        kotlinPlayground(
            code = """
                data class Message(
                    val text: String?,
                    var acknowledged: Boolean = false
                ) {
                    fun send(): Message {
                        return Message(text = "Got it!", acknowledged = true)
                    }
                }

                fun main() {
                    val message = Message(text = "Hello")
                        .also { println(it) }

                    val response = message.send()
                        .also { println(it)  }
                }
                """,
            executable = true
        )
    }
)

object ScopeFunctionsExerciseSlide : Slide(
    header = "Exercise",
    content = {
        p {
            +"You have two data classes:"
        }
        kotlinPlayground(
            code = """
                    data class Person(
                        val name: String,
                        val contact: Contact
                    )
                """,
            executable = false
        )
        kotlinPlayground(
            code = """
                    data class Contact(
                        var email: String? = null,
                        var phone: String? = null
                    )
                """,
            executable = false
        )
        p {
            +"Write a code that will create an instance of a person, for example:"
            kotlinPlayground(
                code = """
                        val person = Person(
                            name = "John",
                            contact = Contact()
                        )
                    """,
                executable = false
            )
        }
        p {
            +"Then use the scope functions to update the person's contact information. You can do all this in a main function."
        }
    }
)
