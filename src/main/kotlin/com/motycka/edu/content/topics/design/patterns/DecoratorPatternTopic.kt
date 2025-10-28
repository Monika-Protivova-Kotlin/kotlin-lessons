package com.motycka.edu.content.topics.design.patterns

import com.motycka.edu.model.Slide
import com.motycka.edu.model.Topic
import com.motycka.edu.model.highlight
import com.motycka.edu.model.inlineCode
import com.motycka.edu.model.kotlinPlayground
import kotlinx.html.*

object DecoratorPatternTopic : Topic(
    title = "Decorator Pattern",
    slides = listOf(
        DecoratorPatternExplanationSlide,
        DecoratorPatternExampleSlide,
//        DecoratorExercise1Slide,
//        DecoratorExercise2Slide
    )
)

object DecoratorPatternExplanationSlide : Slide(
    header = "Decorator Pattern (Structural)",
    summary = {
        +"Attaches additional responsibilities to an object dynamically, providing a flexible alternative to subclassing."
    },
    content = {
        p {
            +"The Decorator pattern lets you add new functionality to objects by wrapping them in decorators. "
            +"Each decorator implements the same interface as the object it wraps, allowing decorators to be stacked."
        }

        p { highlight("When to use:") }
        ul {
            li { +"Add responsibilities to individual objects dynamically" }
            li { +"Avoid explosion of subclasses for every feature combination" }
            li { +"Responsibilities can be added and removed at runtime" }
        }

        p { highlight("Pros & Cons:") }
        ul {
            li { +"✓ More flexible than inheritance" }
            li { +"✓ Responsibilities can be added/removed at runtime" }
            li { +"✗ Can result in many small objects" }
            li { +"✗ Complex to remove specific decorators" }
        }
    }
)

object DecoratorPatternExampleSlide : Slide(
    header = "Decorator Pattern - Kotlin Implementation",
    summary = {
        +"Example: Coffee decorators using Kotlin delegation"
    },
    content = {
        kotlinPlayground(
            code = """
                interface Coffee {
                    fun cost(): Double
                    fun description(): String
                }

                class SimpleCoffee : Coffee {
                    override fun cost() = 5.0
                    override fun description() = "Simple Coffee"
                }

                // Decorator base class using delegation
                abstract class CoffeeDecorator(private val coffee: Coffee) : Coffee by coffee {
                    abstract override fun cost(): Double
                    abstract override fun description(): String
                }

                class MilkDecorator(private val coffee: Coffee) : CoffeeDecorator(coffee) {
                    override fun cost() = coffee.cost() + 1.5
                    override fun description() = coffee.description() + ", Milk"
                }

                class SugarDecorator(private val coffee: Coffee) : CoffeeDecorator(coffee) {
                    override fun cost() = coffee.cost() + 0.5
                    override fun description() = coffee.description() + ", Sugar"
                }

                fun main() {
                    var coffee: Coffee = SimpleCoffee()
                    println("${'$'}{coffee.description()} - $${'$'}{"%.2f".format(coffee.cost())}")

                    coffee = MilkDecorator(coffee)
                    println("${'$'}{coffee.description()} - $${'$'}{"%.2f".format(coffee.cost())}")

                    coffee = SugarDecorator(coffee)
                    println("${'$'}{coffee.description()} - $${'$'}{"%.2f".format(coffee.cost())}")
                }
            """.trimMargin(),
            executable = true
        )
    }
)

object DecoratorExercise1Slide : Slide(
    header = "Exercise 1: Pizza Decorator",
    summary = {
        +"Build a pizza ordering system where toppings are decorators that add cost and description."
    },
    content = {
        p {
            +"Create a pizza system where you can dynamically add toppings using the Decorator pattern."
        }

        p { strong { +"Requirements:" } }
        ol {
            li { +"Create "; inlineCode("interface Pizza"); +" with:" }
            ul {
                li { inlineCode("fun getPrice(): Double") }
                li { inlineCode("fun getDescription(): String") }
            }
            li { +"Create "; inlineCode("class PlainPizza"); +" - base pizza ($8.00)" }
            li { +"Create abstract "; inlineCode("class ToppingDecorator"); +" using Kotlin delegation" }
            li { +"Create topping decorators:" }
            ul {
                li { inlineCode("CheeseTopping"); +" - adds $1.50" }
                li { inlineCode("PepperoniTopping"); +" - adds $2.00" }
                li { inlineCode("OlivesTopping"); +" - adds $1.00" }
                li { inlineCode("MushroomsTopping"); +" - adds $1.50" }
            }
        }

        p { strong { +"Example Usage:" } }
        kotlinPlayground(
            code = """
                // Your implementation here
                interface Pizza {
                    fun getPrice(): Double
                    fun getDescription(): String
                }

                class PlainPizza : Pizza {
                    override fun getPrice() = 8.0
                    override fun getDescription() = "Plain Pizza"
                }

                // TODO: Implement ToppingDecorator and specific toppings

                fun main() {
                    var pizza: Pizza = PlainPizza()
                    println("${'$'}{pizza.getDescription()} - $${'$'}{"%.2f".format(pizza.getPrice())}")

                    pizza = CheeseTopping(pizza)
                    println("${'$'}{pizza.getDescription()} - $${'$'}{"%.2f".format(pizza.getPrice())}")

                    pizza = PepperoniTopping(pizza)
                    println("${'$'}{pizza.getDescription()} - $${'$'}{"%.2f".format(pizza.getPrice())}")

                    pizza = MushroomsTopping(pizza)
                    pizza = OlivesTopping(pizza)
                    println("${'$'}{pizza.getDescription()} - $${'$'}{"%.2f".format(pizza.getPrice())}")
                }
            """.trimMargin(),
            executable = true
        )

        p { strong { +"Expected Output:" } }
        pre {
            +"""
                |Plain Pizza - $8.00
                |Plain Pizza + Cheese - $9.50
                |Plain Pizza + Cheese + Pepperoni - $11.50
                |Plain Pizza + Cheese + Pepperoni + Mushrooms + Olives - $14.00
            """.trimMargin()
        }
    }
)

object DecoratorExercise2Slide : Slide(
    header = "Exercise 2: Text Processor Decorators",
    summary = {
        +"Identify and create decorators for text processing operations."
    },
    content = {
        p { +"Examine this code and answer the questions:" }

        p { strong { +"Example: Text Processor" } }
        kotlinPlayground(
            code = """
                interface TextProcessor {
                    fun process(text: String): String
                }

                class BasicText : TextProcessor {
                    override fun process(text: String) = text
                }

                class UppercaseDecorator(private val processor: TextProcessor) : TextProcessor {
                    override fun process(text: String) = processor.process(text).uppercase()
                }

                class TrimDecorator(private val processor: TextProcessor) : TextProcessor {
                    override fun process(text: String) = processor.process(text).trim()
                }

                class HtmlEscapeDecorator(private val processor: TextProcessor) : TextProcessor {
                    override fun process(text: String) = processor.process(text)
                        .replace("<", "&lt;")
                        .replace(">", "&gt;")
                }

                fun main() {
                    val text = "  <Hello World>  "

                    var processor: TextProcessor = BasicText()
                    processor = TrimDecorator(processor)
                    processor = HtmlEscapeDecorator(processor)
                    processor = UppercaseDecorator(processor)

                    println(processor.process(text))
                }
            """.trimMargin(),
            executable = true
        )

        p { strong { +"Questions:" } }
        ol {
            li { +"What is the output of this program?" }
            li { +"What happens if you change the order of decorators?" }
            li { +"Why is order important in the Decorator pattern?" }
            li { +"Create two more decorators:" }
            ul {
                li { inlineCode("ReverseDecorator"); +" - reverses the text" }
                li { inlineCode("RemoveSpacesDecorator"); +" - removes all spaces" }
            }
            li { +"How would you implement a decorator that logs each processing step?" }
        }

        p { strong { +"Bonus Challenge:" } }
        ul {
            li { +"Refactor the decorators to use Kotlin's "; inlineCode("by"); +" delegation" }
            li { +"Add a "; inlineCode("DecoratorChain"); +" class that applies decorators in sequence" }
            li { +"Implement "; inlineCode("EncryptDecorator"); +" that shifts characters by N positions (Caesar cipher)" }
        }
    }
)
