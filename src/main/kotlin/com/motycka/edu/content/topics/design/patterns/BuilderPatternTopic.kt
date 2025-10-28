package com.motycka.edu.content.topics.design.patterns

import com.motycka.edu.builders.CourseBuilder.ARROW_DOWN
import com.motycka.edu.model.Slide
import com.motycka.edu.model.Topic
import com.motycka.edu.model.highlight
import com.motycka.edu.model.inlineCode
import com.motycka.edu.model.kotlinPlayground
import kotlinx.html.*

object BuilderPatternTopic : Topic(
    title = "Builder Pattern",
    subtitle = "with exercise",
    slides = listOf(
        BuilderPatternExplanationSlide,
        BuilderPatternExampleSlide,
//        BuilderExercise1Slide,
        BuilderExercise2Slide,
        BuilderExercise3Slide
    )
)

object BuilderPatternExplanationSlide : Slide(
    header = "Builder Pattern (Creational)",
    summary = {
        +"Constructs complex objects step by step, separating construction from representation."
    },
    content = {
        p {
            +"The Builder pattern is useful when you need to create complex objects with many optional parameters. "
            +"It provides a fluent interface for building objects incrementally."
        }

        p { highlight("When to use:") }
        ul {
            li { +"Objects with many optional parameters" }
            li { +"Complex object construction that requires multiple steps" }
            li { +"Creating different representations of the same object" }
        }

        p { highlight("Pros & Cons:") }
        ul {
            li { +"✓ Fluent, readable API" }
            li { +"✓ Control over construction process" }
            li { +"✓ Can create different representations" }
            li { +"✗ Requires extra builder class" }
        }
    }
)


object BuilderPatternExampleSlide : Slide(
    header = "Builder Pattern - Kotlin Implementation",
//    summary = {
//        +"Example: Email builder with fluent API using apply"
//    },
    content = {
        kotlinPlayground(
            code = """
                data class Email(
                    val to: String,
                    val subject: String = "",
                    val body: String = "",
                    val cc: List<String> = emptyList(),
                    val attachments: List<String> = emptyList()
                )
                
                class EmailBuilder {
                    private var to: String = ""
                    private var subject: String = ""
                    private var body: String = ""
                    private var cc: MutableList<String> = mutableListOf()
                    private var attachments: MutableList<String> = mutableListOf()
                
                    fun to(address: String) = apply { this.to = address }
                    fun subject(text: String) = apply { this.subject = text }
                    fun body(text: String) = apply { this.body = text }
                    fun cc(address: String) = apply { this.cc.add(address) }
                    fun attach(file: String) = apply { this.attachments.add(file) }
                
                    fun build() = Email(to, subject, body, cc, attachments)
                }
                
                fun main() {
                    val email = EmailBuilder()
                        .to("john@example.com")
                        .subject("Meeting")
                        .body("See you at 3pm")
                        .cc("jane@example.com")
                        .attach("agenda.pdf")
                        .build()
                
                    println(email)
                }
            """.trimMargin(),
            executable = true
        )
    }
)

object BuilderExercise1Slide : Slide(
    header = "Exercise 1: User Builder",
    summary = {
        +"Create a User object with many optional fields using the Builder pattern."
    },
    content = {
        p { +"Build a "; inlineCode("UserBuilder"); +" for creating User objects with required and optional fields." }

        p { strong { +"Requirements:" } }
        ol {
            li { +"Create "; inlineCode("data class User"); +" with:" }
            ul {
                li { inlineCode("username: String"); +" (required)" }
                li { inlineCode("email: String"); +" (required)" }
                li { inlineCode("firstName: String = \"\""); +" (optional)" }
                li { inlineCode("lastName: String = \"\""); +" (optional)" }
                li { inlineCode("age: Int? = null"); +" (optional)" }
                li { inlineCode("phone: String? = null"); +" (optional)" }
            }
            li { +"Create "; inlineCode("class UserBuilder"); +" with fluent methods for each field" }
            li { +"Use "; inlineCode("apply"); +" to return "; inlineCode("this"); +" for method chaining" }
            li { +"Add "; inlineCode("fun build(): User"); +" to create the final User object" }
        }

        p { strong { +"Example Usage:" } }
        kotlinPlayground(
            code = """
                // Your implementation here
                data class User(/* TODO */)
                
                class UserBuilder {
                    // TODO: Implement builder
                
                    fun build(): User {
                        // TODO: Return User instance
                        TODO()
                    }
                }
                
                fun main() {
                    val user1 = UserBuilder()
                        .username("john_doe")
                        .email("john@example.com")
                        .firstName("John")
                        .lastName("Doe")
                        .age(30)
                        .build()
                
                    val user2 = UserBuilder()
                        .username("jane_smith")
                        .email("jane@example.com")
                        .build()
                
                    println(user1)
                    println(user2)
                }
            """.trimMargin(),
            executable = true
        )

        p { strong { +"Expected Output:" } }
        pre {
            +"""
                |User(username=john_doe, email=john@example.com, firstName=John, lastName=Doe, age=30, phone=null)
                |User(username=jane_smith, email=jane@example.com, firstName=, lastName=, age=null, phone=null)
            """.trimMargin()
        }
    }
)

object BuilderExercise2Slide : Slide(
    header = "Exercise: Pizza Builder",
    summary = {
        +"Build a customizable Pizza using the Builder pattern with multiple toppings."
    },
    content = {
        p { +"Create a "; inlineCode("PizzaBuilder"); +" that allows customers to customize their pizza." }
        ol {
            li { +"Create "; inlineCode("data class Pizza"); +" with:" }
            ul {
                li { inlineCode("size: String"); +" (\"small\", \"medium\", \"large\")" }
                li { inlineCode("cheese: Boolean = true") }
                li { inlineCode("toppings: List<String> = emptyList()") }
            }
            li { +"Create "; inlineCode("class PizzaBuilder"); +" with:" }
            ul {
                li { inlineCode("fun size(s: String)"); +" - sets size" }
                li { inlineCode("fun noCheese()"); +" - removes cheese" }
                li { inlineCode("fun addTopping(topping: String)"); +" - adds one topping" }
                li { inlineCode("fun build(): Pizza"); +" - creates pizza" }
            }
            li { +"Calculate price: Base ($10) + $2 per topping + size multiplier" }
            li {
                strong { +"Bonus:" }
                br
                ul {
                    li { +"Use enums instead of strings" }
                    li { +"Limit maximum toppings to 5" }
                    li { +"Create a "; inlineCode("fun reset()"); +" method to start a new pizza" }
                }
            }
        }
        p {
            em { +"Playground is on the next slide $ARROW_DOWN" }
        }
    }
)

object BuilderExercise3Slide : Slide(
    header = "Exercise: Pizza Builder",
    content = {
        kotlinPlayground(
            code = """
                data class Pizza(/* TODO */) {
                    fun calculatePrice(): Double {
                        val base = 10.0
                        val toppingCost = toppings.size * 2.0
                        val sizeMultiplier = when(size) {
                            "small" -> 1.0
                            "medium" -> 1.5
                            "large" -> 2.0
                            else -> 1.0
                        }
                        return (base + toppingCost) * sizeMultiplier
                    }
                }
                
                class PizzaBuilder {
                    // TODO: Implement builder
                }
                
                fun main() {
                    val margherita = PizzaBuilder()
                        .size("medium")
                        .addTopping("basil")
                        .build()
                
                    val meatLovers = PizzaBuilder()
                        .size("large")
                        .addTopping("pepperoni")
                        .addTopping("sausage")
                        .addTopping("bacon")
                        .addTopping("ham")
                        .build()
                
                    println("${DOLLAR}margherita - Price: ${DOLLAR}{margherita.calculatePrice()}")
                    println("${DOLLAR}meatLovers - Price: ${DOLLAR}{meatLovers.calculatePrice()}")
                }
            """,
            executable = true,
            selectLines = 19..36
        )
    }
)
