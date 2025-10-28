package com.motycka.edu.content.topics.design.patterns

import com.motycka.edu.builders.CourseBuilder.ARROW_DOWN
import com.motycka.edu.model.Slide
import com.motycka.edu.model.Topic
import com.motycka.edu.model.highlight
import com.motycka.edu.model.inlineCode
import com.motycka.edu.model.kotlinPlayground
import com.motycka.edu.model.twoColumns
import kotlinx.html.*

object FactoryPatternTopic : Topic(
    title = "Factory Pattern",
    subtitle = "with exercise",
    slides = listOf(
        FactoryPatternExplanationSlide,
        FactoryPatternExampleSlide,
        FactoryExercise1Slide,
        FactoryExercise2Slide
    )
)

object FactoryPatternExplanationSlide : Slide(
    header = "Factory Pattern (Creational)",
    summary = {
        +"Defines an interface for creating objects but lets subclasses decide which class to instantiate."
    },
    content = {
        p {
            +"The Factory pattern provides a way to create objects without specifying their exact class. "
            +"It encapsulates object creation logic and makes the code more flexible and maintainable."
        }

        p { highlight("When to use:") }
        ul {
            li { +"When object creation logic is complex" }
            li { +"When you want to decouple client code from concrete classes" }
            li { +"When you need to create different types based on input parameters" }
        }

        p { highlight("Pros & Cons:") }
        ul {
            li { +"✓ Decouples object creation from usage" }
            li { +"✓ Makes code more flexible and maintainable" }
            li { +"✗ Can introduce extra complexity" }
            li { +"✗ Requires additional classes/methods" }
        }
    }
)


object FactoryPatternExampleSlide : Slide(
    header = "Factory Pattern - Kotlin Implementation",
//    summary = {
//        +"Example: Shape factory with different shape types"
//    },
    content = {
        kotlinPlayground(
            code = """
                interface Transport {
                    fun deliver(destination: String)
                }
                
                class Truck : Transport {
                    override fun deliver(destination: String) =
                        println("Delivering by truck to ${'$'}destination")
                }
                
                class Ship : Transport {
                    override fun deliver(destination: String) =
                        println("Delivering by ship to ${'$'}destination")
                }
                
                object TransportFactory {
                    fun createTransport(type: String): Transport = when(type) {
                        "land" -> Truck()
                        "sea" -> Ship()
                        else -> throw IllegalArgumentException("Unknown type")
                    }
                }
                
                fun main() {
                    val transport1 = TransportFactory.createTransport("land")
                    transport1.deliver("New York")
                
                    val transport2 = TransportFactory.createTransport("sea")
                    transport2.deliver("London")
                }
            """.trimMargin(),
            executable = true
        )
    }
)

object FactoryExercise1Slide : Slide(
    header = "Exercise: Refactoring to Factory",
    summary = {
        +"Refactor code with hardcoded object instantiation to use the Factory pattern."
    },
    content = {
        p { +"The following code has hardcoded instantiation logic. Refactor it using the Factory pattern." }
        p { strong { +"Requirements:" } }
        ol {
            li { +"Create a "; inlineCode("PaymentFactory"); +" object" }
            li { +"Add "; inlineCode("fun create(type: String): Payment"); +" method" }
            li { +"Refactor "; inlineCode("checkout"); +" function to use the factory" }
            li { +"Make the code cleaner and easier to extend with new payment types" }
        }
        p { strong { +"Benefits of your refactoring:" } }
        ul {
            li { +"✓ "; inlineCode("checkout"); +" function no longer knows about concrete payment classes" }
            li { +"✓ Easy to add new payment methods without modifying "; inlineCode("checkout") }
            li { +"✓ Centralized creation logic" }
        }
        p {
            em { +"Playground is on the next slide $ARROW_DOWN" }
        }
    }
)

object FactoryExercise2Slide : Slide(
    header = "Exercise: Refactoring to Factory",
    content = {
        kotlinPlayground(
            code = """
                interface Payment {
                    fun processPayment(amount: Double)
                }
                
                class CreditCard : Payment {
                    override fun processPayment(amount: Double) =
                        println("Processing credit card payment: $${'$'}amount")
                }
                
                class PayPal : Payment {
                    override fun processPayment(amount: Double) =
                        println("Processing PayPal payment: $${'$'}amount")
                }
                
                class Bitcoin : Payment {
                    override fun processPayment(amount: Double) =
                        println("Processing Bitcoin payment: $${'$'}amount")
                }
                
                fun checkout(paymentType: String, amount: Double) {
                    val payment: Payment = when (paymentType) {
                        "credit" -> CreditCard()
                        "paypal" -> PayPal()
                        "bitcoin" -> Bitcoin()
                        else -> error("Unknown payment type")
                    }
                    payment.processPayment(amount)
                }
                
                fun main() {
                    checkout("credit", 100.0)
                    checkout("paypal", 50.0)
                    checkout("bitcoin", 200.0)
                }
            """,
            executable = true,
            selectLines = 5..34
        )
    }
)
