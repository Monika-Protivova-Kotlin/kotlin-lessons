package com.motycka.edu.content.topics.basics.concurrency

import com.motycka.edu.model.Slide
import com.motycka.edu.model.Topic
import com.motycka.edu.model.highlight
import kotlinx.html.*

object CoffeeShopExerciseTopic : Topic(
    title = "Practice",
    slides = listOf(
        CoffeeShopExerciseIntroSlide,
    )
)

object CoffeeShopExerciseIntroSlide : Slide(
    header = "Practice: Coffee Shop",
    summary = {
        +"In this assignment, you will implement a simulation of a coffee shop using Kotlin coroutines. "
    },
    fontSize = "70%",
    content = {
        p {
            +"The simulation consists of the following components:"
            h4 { +"Order Generation" }
            ul {
                li { +"Orders are randomly generated at regular intervals while the coffee shop is open" }
                li { +"The shop only accepts orders if they can be completed before closing" }
                li { +"When the shop closes, new orders are rejected" }
            }
        }
        p {
            h4 { +"Order Processing" }
            ul {
                li { +"The coffee shop has multiple baristas who process orders in parallel" }
                li { +"Each barista picks up one order at a time from a shared queue" }
                li { +"The barista must use a shared coffee machine, which only one person can use at a time" }
                li { +"Once an order is completed, the barista moves on to the next available order" }
            }
        }
        p {
            h4 { +"Concurrency Requirements" }
            ul {
                li {
                    +"Use "
                    highlight("coroutines")
                    +" for parallel processing"
                }
                li {
                    +"Use "
                    highlight("channels")
                    +" for order distribution among baristas"
                }
                li {
                    +"Use "
                    highlight("mutex")
                    +" for shared resource access (coffee machine)"
                }
                li {
                    +"Use "
                    highlight("AtomicInteger")
                    +" for thread-safe counters"
                }
                li { +"Handle proper shutdown when the shop closes" }
            }
        }
    }
)
