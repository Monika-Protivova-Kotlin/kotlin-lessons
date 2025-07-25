package com.motycka.edu.content.topics.basics.concurrency

import com.motycka.edu.model.Slide
import com.motycka.edu.model.ExerciseTopic
import com.motycka.edu.model.kotlinPlayground
import com.motycka.edu.model.highlight
import com.motycka.edu.model.inlineCode
import kotlinx.html.*

object CoffeeShopExerciseTopic : ExerciseTopic(
    title = "Exercise",
    slides = listOf(
        CoffeeShopExerciseIntroSlide,
        CoffeeShopExerciseComponentsSlide,
        CoffeeShopExerciseCoffeeShopSlide,
        CoffeeShopExerciseOrderGeneratorSlide,
        CoffeeShopExerciseMainFunctionSlide
    )
)

object CoffeeShopExerciseIntroSlide : Slide(
    header = "Exercise: Coffee Shop",
    summary = {
        +"In this assignment, you will implement a simulation of a coffee shop using Kotlin coroutines. "
        +"The goal is to model a simple ordering and processing system where customers place coffee orders, "
        +"and baristas prepare them in parallel."
    },
    content = {
        ol {
            li {
                p {
                    +"Create an "
                    inlineCode("MenuItem")
                    +" enum with different coffee types time to prepare. You can use this example:"
                }
                kotlinPlayground(
                    code = """
                        enum class MenuItem(val time: Long) {
                            AMERICANO(200),
                            ESPRESSO(100),
                            DOUBLE_ESPRESSO(200),
                            CAPUCCINO(400),
                            FLAT_WHITE(400),
                        }
                    """.trimIndent()
                )
            }
            li {
                p {
                    +"Create an "
                    inlineCode("Order")
                    +" data class with properties for order ID, customer name, and menu item."
                }
            }
            li {
                p {
                    +"Create a "
                    inlineCode("CoffeeShop")
                    +" class that manages baristas and processes orders."
                }
            }
            li {
                p {
                    +"Create an "
                    inlineCode("OrderGenerator")
                    +" class that generates random orders."
                }
            }
        }
    }
)

object CoffeeShopExerciseComponentsSlide : Slide(
    header = "Exercise: Coffee Shop",
    summary = { +"The simulation consists of the following key components" },
    content = {
        ul {
            li {
                highlight("Order Generation")
                ol {
                    li { +"Orders should be randomly generated at regular intervals while the coffee shop is open." }
                    li { +"The shop should only accept orders if they can be completed before closing." }
                }
            }
            li {
                highlight("Order Processing")
                ol {
                    li { +"The coffee shop has multiple baristas who process orders in parallel." }
                    li { +"Each barista picks up one order at a time from a shared queue." }
                    li { +"The barista must use a shared coffee machine, which only one person can use at a time." }
                    li { +"Once an order is completed, the barista moves on to the next available order." }
                }
            }
            li {
                highlight("Concurrency Requirements")
                ol {
                    li { +"Use coroutines for parallel processing." }
                    li { +"Use channels for order distribution." }
                    li { +"Use mutex for shared resource access (coffee machine)." }
                    li { +"Handle proper shutdown when the shop closes." }
                }
            }
        }
    }
)

object CoffeeShopExerciseCoffeeShopSlide : Slide(
    header = "Exercise: Coffee Shop",
    summary = {
        +"The "
        inlineCode("CoffeeShop")
    },
    content = {
        div {
            style = "font-size: 60%"
            p {
                ol {
                    li { +"Manages the baristas and the coffee-making process." }
                    li { +"Keeps track of the opening and closing times." }
                    li {
                        +"Uses a "
                        inlineCode("Channel")
                        +" to queue orders."
                    }
                    li { +"Launches multiple coroutines (one per barista) to process orders concurrently." }
                    li {
                        +"Ensures the coffee machine is accessed safely using a "
                        inlineCode("Mutex")
                        +"."
                    }
                    li { +"Stops accepting orders after closing but allows baristas to finish processing remaining ones." }
                }
            }
            p {
                inlineCode("CoffeeShop")
                +" should have the following functions and properties:"
                ul {
                    li {
                        +"Constructor"
                        ul {
                            li {
                                inlineCode("numberOfBaristas: Int")
                                +" - number of baristas working"
                            }
                            li {
                                inlineCode("openDurationMs: Long")
                                +" - how long the shop stays open"
                            }
                        }
                    }
                    li {
                        inlineCode("suspend fun placeOrder(order: Order): Boolean")
                        +" - places an order, returns true if accepted"
                    }
                    li {
                        inlineCode("suspend fun start()")
                        +" - starts the coffee shop operations"
                    }
                }
            }
        }
    }
)

object CoffeeShopExerciseOrderGeneratorSlide : Slide(
    header = "Exercise: Coffee Shop",
    summary = {
        +"The "
        inlineCode("OrderGenerator")
    },
    content = {
        ul {
            li {
                +"This class generates coffee orders randomly and places order with "
                inlineCode("CoffeeShop")
                +" using the "
                inlineCode("placeOrder")
                +" function."
            }
            li { +"Orders should be placed at regular intervals (e.g., every 500ms)." }
            li { +"The generator should stop creating new orders when the shop is about to close." }
        }
        p {
            +"Required functions:"
        }
        ul {
            li {
                +"Constructor: "
                inlineCode("OrderGenerator(coffeeShop: CoffeeShop)")
            }
            li {
                inlineCode("suspend fun start()")
                +" - starts generating orders"
            }
            li {
                inlineCode("private fun generateRandomOrder(): Order")
                +" - creates a random order"
            }
        }
    }
)

object CoffeeShopExerciseMainFunctionSlide : Slide(
    header = "Exercise: Coffee Shop",
    summary = {
        +"The "
        inlineCode("main")
        +" function"
    },
    content = {
        p {
            +"The main function should create and instance of "
            inlineCode("CoffeeShop")
            +" and "
            inlineCode("OrderGenerator")
            +" and start the simulation. Use launch to start both the "
            inlineCode("OrderGenerator")
            +" and "
            inlineCode("CoffeeShop")
            +" coroutines."
        }
        p {
            highlight("Ensure Proper Concurrency Handling")
            ul {
                li { +"Use launch to start multiple coroutines for parallel order processing." }
                li { +"Use a Channel to distribute orders among baristas." }
                li { +"Use a Mutex to ensure only one barista uses the coffee machine at a time." }
                li {
                    +"Use logging (println or KotlinLogging) to"
                    ul {
                        li { +"show when orders are placed, prepared, and completed" }
                        li { +"show when the shop opens and closes" }
                        li { +"show when baristas are waiting for the coffee machine" }
                        li { +"show when the order queue is empty and baristas are waiting" }
                    }
                }
            }
        }
        kotlinPlayground(
            code = """
                fun main() = runBlocking {
                    val coffeeShop = CoffeeShop(numberOfBaristas = 3, openDurationMs = 10000)
                    val orderGenerator = OrderGenerator(coffeeShop)

                    launch { coffeeShop.start() }
                    launch { orderGenerator.start() }

                    delay(12000) // Wait for simulation to complete
                }
            """.trimIndent()
        )
    }
)
