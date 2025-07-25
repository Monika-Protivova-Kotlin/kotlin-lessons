package com.motycka.edu.content.topics.backend.springboot

import com.motycka.edu.model.Slide
import com.motycka.edu.model.Topic
import com.motycka.edu.model.highlight
import com.motycka.edu.model.inlineCode
import com.motycka.edu.model.kotlinPlayground
import kotlinx.html.*

object EventBasedArchitectureTopic : Topic(
    title = "Event Based Architecture",
    slides = listOf(
        EventBasedArchitectureIntroSlide,
        EventBasedArchitectureDetailsSlide,
        SpringApplicationEventsSlide
    )
)

object EventBasedArchitectureIntroSlide : Slide(
    header = "Event Based Architecture",
    summary = {
        +"Event based architecture is a software architecture pattern where the different components "
        +"of the application communicate with each other by sending and receiving events."
    },
    content = {
        p {
            +"Event based architecture is a popular choice for building distributed systems because "
            +"it allows for loose coupling between the different components of the system."
        }
        blockQuote {
            +"It is not only limited to service-to-service communication, but can also be used to communicate within a single service."
        }
        p {
            +"It can often be described as a "
            strong { highlight("fire and forget") }
            +" model, where the sender of the event does not need to wait for a response from the receiver."
        }
        p {
            +"There are multiple ways to implement this architecture:"
        }
        ul {
            li {
                +"Using "
                strong { highlight("Publish/Subscribe") }
                +" model"
                br()
                em { +"Where events are published to a topic and multiple subscribers can receive the event." }
            }
            li {
                +"Using "
                strong { highlight("Message Queues") }
                br()
                em { +"Where events are sent to a queue and processed by a single consumer." }
            }
            li {
                +"Using "
                strong { highlight("Event Sourcing") }
                br()
                em { +"Where the state of the system is determined by a sequence of events." }
            }
        }
        p {
            +"Message brokers like "
            strong { highlight("Kafka") }
            +", "
            strong { highlight("RabbitMQ") }
            +", and "
            strong { highlight("Amazon SQS") }
            +" are commonly used to implement event based architectures."
        }
    }
)

object EventBasedArchitectureDetailsSlide : Slide(
    header = "Event Based Architecture",
    content = {
        p {
            strong { highlight("When to use") }
        }
        ul {
            li { +"Applications that need to be highly scalable." }
            li { +"Applications that need to be highly available." }
            li { +"Applications that need to be fault-tolerant." }
        }
        p {
            strong { highlight("Pros") }
        }
        ul {
            li { +"Loose coupling between components." }
            li { +"Highly scalable and available." }
            li { +"Fault-tolerant." }
        }
        p {
            strong { highlight("Cons") }
        }
        ul {
            li { +"Complexity of managing events." }
            li { +"Potential for event loss." }
            li { +"Potential for event duplication." }
        }
    }
)

object SpringApplicationEventsSlide : Slide(
    header = "Spring Application Events",
    content = {
        p {
            +"Spring Framework provides a mechanism for implementing event-based architectures using the "
            inlineCode("ApplicationEvent")
            +" class and the "
            inlineCode("ApplicationEventPublisher")
            +" interface."
        }
        p {
            +"You can create custom events by extending the "
            inlineCode("ApplicationEvent")
            +" class and publish them using the "
            inlineCode("ApplicationEventPublisher")
            +" interface."
        }
        kotlinPlayground(
            """
            |@Component
            |class MyEventPublisher(
            |    private val applicationEventPublisher: ApplicationEventPublisher
            |) {
            |
            |    fun publishEvent() {
            |        applicationEventPublisher.publishEvent(MyCustomEvent(this, "Hello, World!"))
            |    }
            |
            |}
            """,
            executable = false
        )
        kotlinPlayground(
            """
            |class MyCustomEvent(source: Any, val message: String) : ApplicationEvent(source)
            """,
            executable = false
        )
        p {
            +"You can listen for custom events by implementing the "
            inlineCode("ApplicationListener")
            +" interface and annotating the listener with "
            inlineCode("@Component")
            +"."
        }
        kotlinPlayground(
            """
            |@Component
            |class MyEventListener : ApplicationListener<MyCustomEvent> {
            |
            |    override fun onApplicationEvent(event: MyCustomEvent) {
            |        println("Received event: ${'$'}{event.message}")
            |    }
            |
            |}
            """,
            executable = false
        )
    }
)
