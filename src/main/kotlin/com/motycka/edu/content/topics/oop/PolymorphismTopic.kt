package com.motycka.edu.content.topics.oop

import com.motycka.edu.model.Slide
import com.motycka.edu.model.Topic
import com.motycka.edu.model.kotlinPlayground
import kotlinx.html.*

object PolymorphismTopic : Topic(
    title = "Polymorphism",
    subtitle = "OOP Principle #3",
    slides = listOf(
        PolymorphismOverviewSlide,
        CompileTimePolymorphismSlide,
        RuntimePolymorphismSlide
    )
)

object PolymorphismOverviewSlide : Slide(
    header = "What is polymorphism",
    summary = {
        +"In programming, polymorphism allows us to define one interface or method that can have multiple implementations. "
        +"It means that the same method or property could exhibit different behavior in different instances of object implementing given interface."
    },
    textAlign = "center",
    content = {
        p {
            em { +"There are two types of polymorphism:" }
        }
        ol {
            li {
                h4 { +"Compile-Time polymorphism" }
                em { +"also known as static polymorphism" }
            }
            li {
                h4 { +"Run-Time polymorphism" }
                em { +"also known as dynamic method dispatch" }
            }
        }
    }
)

object CompileTimePolymorphismSlide : Slide(
    header = "Compile-time polymorphism",
    content = {
        p {
            +"Compile-time polymorphism is achieved through "
            strong { +"method overloading" }
            +". The correct method to call is determined by the compiler at compile time based on the method signature."
        }
        kotlinPlayground(
            code = """
                object Calculator {

                    // method with 2 parameters
                    fun add(a: Int, b: Int): Int {
                        return a + b
                    }
                
                    // overloaded method with 3 parameters
                    fun add(a: Int, b: Int, c: Int): Int {
                        return a + b + c
                    }
                }
                
                fun main() {
                    val result1 = Calculator.add(10, 20)
                    println(result1)
                    
                    val result2 = Calculator.add(10, 20, 30)
                    println(result2)
                }
            """,
            executable = true
        )
        p {
            span("highlight") { +"Method overloading" }
            +" = defining two or more methods in a class with the same name but different "
            strong { +"signature" }
            +"."
        }
        p {
            span("highlight") { +"Method signature" }
            +" = combination of the method name, return type and the parameters."
        }
    }
)

object RuntimePolymorphismSlide : Slide(
    header = "Runtime polymorphism",
    content = {
        p {
            +"Runtime polymorphism is a process in which a call to an overridden method is resolved at runtime rather than at compile-time. "
            +"This mechanism allows the Java Virtual Machine (JVM) to decide which method to invoke from the class hierarchy at runtime, "
            +"based on the type of object."
        }
        kotlinPlayground(
            code = """
                open class Animal {
                    open fun makeSound() {
                        println("(silence)")
                    }
                }
                
                class Dog : Animal() {
                    override fun makeSound() {
                        println("woof")
                    }
                }
                
                class Cat : Animal() {
                    override fun makeSound() {
                        println("meow")
                    }
                }
                
                fun main() {
                    val animal0 = Animal()
                
                    val animal1: Animal = Dog() // Animal reference but Dog object
                    val animal2: Animal = Cat() // Animal reference but Cat object
                
                    animal0.makeSound() // prints "(silence)"
                    animal1.makeSound() // prints "woof"
                    animal2.makeSound() // prints "meow"
                }
            """,
            selectLines = 19 .. 28,
            executable = true
        )
        table {
            style = "width: 100%"
            tbody {
                tr {
                    td {
                        +"Superclass"
                        kotlinPlayground(
                            code = """
                                open class Animal {
                                    open fun makeSound() {
                                        println("(silence)")
                                    }
                                }
                            """,
                            executable = false
                        )
                    }
                    td {
                        +"Subclasses"
                        kotlinPlayground(
                            code = """
                                class Dog : Animal() {
                                    override fun makeSound() {
                                        println("woof")
                                    }
                                }
                                
                                class Cat : Animal() {
                                    override fun makeSound() {
                                        println("meow")
                                    }
                                }
                            """,
                            executable = false
                        )
                    }
                }
            }
        }
    }
)
