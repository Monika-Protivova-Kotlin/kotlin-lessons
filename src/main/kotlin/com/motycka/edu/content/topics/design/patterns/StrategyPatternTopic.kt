package com.motycka.edu.content.topics.design.patterns

import com.motycka.edu.builders.CourseBuilder.ARROW_DOWN
import com.motycka.edu.model.Slide
import com.motycka.edu.model.Topic
import com.motycka.edu.model.highlight
import com.motycka.edu.model.inlineCode
import com.motycka.edu.model.kotlinPlayground
import kotlinx.html.*

object StrategyPatternTopic : Topic(
    title = "Strategy Pattern",
    subtitle = "with exercise",
    slides = listOf(
        StrategyPatternExplanationSlide,
        StrategyPatternExampleSlide,
        StrategyExercise1Slide,
        StrategyExercise2Slide
    )
)

object StrategyPatternExplanationSlide : Slide(
    header = "Strategy Pattern (Behavioral)",
    summary = {
        +"Defines a family of algorithms, encapsulates each one, and makes them interchangeable."
    },
    content = {
        p {
            +"The Strategy pattern lets you define a family of algorithms, put each of them into a separate class, "
            +"and make their objects interchangeable. Clients can choose which algorithm to use at runtime."
        }

        p { highlight("When to use:") }
        ul {
            li { +"Multiple related classes differ only in their behavior" }
            li { +"You need different variants of an algorithm" }
            li { +"You want to avoid conditional statements for selecting behavior" }
        }

        p { highlight("Pros & Cons:") }
        ul {
            li { +"✓ Easy to switch algorithms at runtime" }
            li { +"✓ Isolates implementation details" }
            li { +"✗ Clients must be aware of different strategies" }
            li { +"✗ Increases number of classes" }
        }
    }
)

object StrategyPatternExampleSlide : Slide(
    header = "Strategy Pattern - Kotlin Implementation",
//    summary = {
//        +"Example: Sorting strategies with runtime algorithm selection"
//    },
    content = {
        kotlinPlayground(
            code = """
                interface SortingStrategy {
                    fun sort(data: MutableList<Int>)
                }

                class BubbleSort : SortingStrategy {
                    override fun sort(data: MutableList<Int>) {
                        println("Sorting using Bubble Sort")
                        // Simplified bubble sort
                        for (i in 0 until data.size) {
                            for (j in 0 until data.size - 1 - i) {
                                if (data[j] > data[j + 1]) {
                                    val temp = data[j]
                                    data[j] = data[j + 1]
                                    data[j + 1] = temp
                                }
                            }
                        }
                    }
                }

                class QuickSort : SortingStrategy {
                    override fun sort(data: MutableList<Int>) {
                        println("Sorting using Quick Sort")
                        data.sort() // Using built-in sort
                    }
                }

                class DataProcessor(private var strategy: SortingStrategy) {
                    fun setStrategy(strategy: SortingStrategy) {
                        this.strategy = strategy
                    }

                    fun process(data: MutableList<Int>) {
                        strategy.sort(data)
                        println("Result: ${'$'}data")
                    }
                }

                fun main() {
                    val processor = DataProcessor(BubbleSort())
                    processor.process(mutableListOf(5, 2, 8, 1, 9))

                    processor.setStrategy(QuickSort())
                    processor.process(mutableListOf(7, 3, 6, 4, 2))
                }
            """.trimMargin(),
            executable = true
        )
    }
)

object StrategyExercise1Slide : Slide(
    header = "Exercise: Payment Strategies",
    summary = {
        +"Implement different payment strategies that can be used interchangeably in a checkout system."
    },
    content = {
        p {
            +"Create a payment processing system that supports multiple payment methods using the Strategy pattern."
        }

        p { strong { +"Requirements:" } }
        ol {
            li { +"Create "; inlineCode("interface PaymentStrategy"); +" with "; inlineCode("fun pay(amount: Double): Boolean") }
            li { +"Implement "; inlineCode("CreditCardPayment(cardNumber: String)"); +" strategy" }
            ul {
                li { +"Validates card number (must be 16 digits)" }
                li { +"Prints \"Paid \$amount using Credit Card ending in ****\${cardNumber.takeLast(4)}\"" }
            }
            li { +"Implement "; inlineCode("PayPalPayment(email: String)"); +" strategy" }
            ul {
                li { +"Validates email (must contain '@')" }
                li { +"Prints \"Paid \$amount via PayPal account \$email\"" }
            }
            li { +"Implement "; inlineCode("CryptoPayment(walletAddress: String)"); +" strategy" }
            ul {
                li { +"Prints \"Paid \$amount to crypto wallet \$walletAddress\"" }
            }
            li { +"Create "; inlineCode("class ShoppingCart"); +" that uses "; inlineCode("PaymentStrategy") }
        }
        p {
            em { +"Playground is on the next slide $ARROW_DOWN" }
        }
    }
)

object StrategyExercise2Slide : Slide(
    header = "Exercise: Payment Strategies",
    content = {
        kotlinPlayground(
            code = """
                // Your implementation here
                interface PaymentStrategy {
                    fun pay(amount: Double): Boolean
                }

                // TODO: Implement payment strategies

                class ShoppingCart {
                    private val items = mutableListOf<Pair<String, Double>>()
                    private var paymentStrategy: PaymentStrategy? = null

                    fun addItem(name: String, price: Double) {
                        items.add(name to price)
                    }

                    fun setPaymentMethod(strategy: PaymentStrategy) {
                        this.paymentStrategy = strategy
                    }

                    fun checkout(): Boolean {
                        val total = items.sumOf { it.second }
                        println("Total: $${'$'}{"%.2f".format(total)}")
                        return paymentStrategy?.pay(total) ?: false
                    }
                }

                fun main() {
                    val cart = ShoppingCart()
                    cart.addItem("Laptop", 999.99)
                    cart.addItem("Mouse", 29.99)

                    println("=== Paying with Credit Card ===")
                    cart.setPaymentMethod(CreditCardPayment("1234567890123456"))
                    cart.checkout()

                    println("\n=== Paying with PayPal ===")
                    cart.setPaymentMethod(PayPalPayment("user@example.com"))
                    cart.checkout()

                    println("\n=== Paying with Crypto ===")
                    cart.setPaymentMethod(CryptoPayment("0x742d35Cc6634C0532925a3b844Bc9e7595f0bEb7"))
                    cart.checkout()
                }
            """.trimMargin(),
            executable = true
        )
    }
)

//object StrategyExercise2Slide : Slide(
//    header = "Exercise 2: Refactoring Conditionals",
//    summary = {
//        +"Refactor code with nested conditional statements to use the Strategy pattern."
//    },
//    content = {
//        p { +"The following code uses many conditional statements. Refactor it using the Strategy pattern:" }
//
//        p { strong { +"Original Code (Problematic):" } }
//        kotlinPlayground(
//            code = """
//                class TextFormatter {
//                    fun format(text: String, format: String): String {
//                        return if (format == "uppercase") {
//                            text.uppercase()
//                        } else if (format == "lowercase") {
//                            text.lowercase()
//                        } else if (format == "capitalize") {
//                            text.split(" ").joinToString(" ") {
//                                it.replaceFirstChar { c -> c.uppercase() }
//                            }
//                        } else if (format == "reverse") {
//                            text.reversed()
//                        } else {
//                            text
//                        }
//                    }
//                }
//
//                fun main() {
//                    val formatter = TextFormatter()
//                    val text = "hello world"
//
//                    println(formatter.format(text, "uppercase"))
//                    println(formatter.format(text, "capitalize"))
//                    println(formatter.format(text, "reverse"))
//                }
//            """.trimMargin(),
//            executable = true
//        )
//
//        p { strong { +"Your Task:" } }
//        ol {
//            li { +"Create "; inlineCode("interface FormatStrategy"); +" with "; inlineCode("fun format(text: String): String") }
//            li { +"Create strategy classes: "; inlineCode("UppercaseStrategy"); +", "; inlineCode("LowercaseStrategy"); +", "; inlineCode("CapitalizeStrategy"); +", "; inlineCode("ReverseStrategy") }
//            li { +"Refactor "; inlineCode("TextFormatter"); +" to accept and use "; inlineCode("FormatStrategy") }
//            li { +"Make the code easier to extend with new formatting strategies" }
//        }
//
//        p { strong { +"Benefits of refactoring:" } }
//        ul {
//            li { +"✓ Eliminates conditional logic" }
//            li { +"✓ Open/Closed Principle: open for extension, closed for modification" }
//            li { +"✓ Easy to add new formatting strategies without changing existing code" }
//            li { +"✓ Each strategy is testable independently" }
//        }
//    }
//)
