package com.motycka.edu.content.topics.introductory

import com.motycka.edu.builders.*
import com.motycka.edu.model.Slide
import com.motycka.edu.model.Topic
import kotlinx.html.br
import kotlinx.html.div
import kotlinx.html.img
import kotlinx.html.li
import kotlinx.html.p
import kotlinx.html.strong
import kotlinx.html.style
import kotlinx.html.sub
import kotlinx.html.ul

object ApplicationArchitectureTopic : Topic(
    title = "Application Architecture",
    slides = listOf(
        CommonArchitecturePatternsSlide,
        MonolithicArchitectureSlide,
        MonolithicArchitectureDiagramSlide,
        MicroserviceArchitectureSlide,
        MicroserviceArchitectureDiagramSlide,
        APIGatewayArchitectureSlide,
        APIGatewayArchitectureDiagramSlide,
        ServerlessArchitectureSlide,
        EventDrivenArchitectureSlide,
        ArchitectureHowToDecideSlide
    )
)

object CommonArchitecturePatternsSlide : Slide(
    header = "Common Architecture Patterns",
    summary = { +"There are several common architecture patterns that are used in the development of software applications." },
    content = {
        p {
            +"These are high-level application architecture patterns used to define the structure of the application and the way in which the different components of the application interact with each other."
        }
        p {
            strong("Some")
            + "of the most common architecture patterns include:"
        }
        ul {
            li { +"Monolithic Architecture" }
            li { +"Microservice Architecture" }
            li { +"API Gateway Architecture" }
            li { +"Serverless Architecture" }
            li { +"Event-Driven Architecture" }
        }
    }
)

object MonolithicArchitectureSlide : Slide(
    header = "Monolithic Architecture",
    summary = {
        +"Monolithic architecture is a software architecture pattern where all the components of the application run as a single unit."
    },
    content = {
        p {
            +"Monolithic architecture tends to be tightly coupled, which can make it difficult to scale and maintain the application over time."
        }
        highlightOrderedList(
            "When to use" to {
                ul {
                    li { +"Large, complex systems." }
                    li { +"Early - stage startups." }
                    li { +"Teams with limited resources." }
                }
            },
            "Pros" to {
                ul {
                    li { +"Independent scaling and deployment." }
                    li { +"Fault isolation (failures in one service don't affect others)." }
                    li { +"Easier to adopt different tech stacks per service." }
                }
            },
            "Cons" to {
                ul {
                    li { +"Complex to manage (e.g., inter-service communication)." }
                    li { +"Potential for high latency between services." }
                    li { +"Requires solid observability (monitoring, logging)." }
                    li {
                        +"Difficult to develop and deploy. **"
                        sub { +"Monoliths can have many dependencies to for developers, that may mean a lot of setup." }
                    }
                }
            }
        )
    }
)

object MonolithicArchitectureDiagramSlide : Slide(
    header = "Monolithic Architecture Diagram",
    content = {
        img("Monolithic Architecture", "./src/img/monolith.png") {
            style = "max-width: 500px;"
        }
    }
)

object MicroserviceArchitectureSlide : Slide(
    header = "Microservice Architecture",
    summary = { +"An architecture that decomposes the system into smaller, (relatively) independent services." },
    content = {
        highlightOrderedList(
            "When to use" to {
                ul {
                    li { +"Small projects or MVPs."}
                    li { +"Early - stage startups."}
                    li { +"Teams with limited resources."}
                }
            },
            "Pros" to {
                ul {
                    li { +"Easier debugging with one codebase."}
                    li {
                        +"Simpler to develop and deploy. **"
                        sub { +"In terms of needing simper infrastructure." }
                    }
                }
            },
            "Cons" to {
                ul {
                    li { +"Difficult to scale certain parts independently." }
                    li { +"Harder to manage as the codebase grows." }
                    li { +"Limited error resilience." }
                }
            },
            "Pitfalls" to {
                ul {
                    li { strong("Be aware of distributed monoliths!") }
                }
            }
        )
    }
)

object MicroserviceArchitectureDiagramSlide : Slide(
    header = "Microservice Architecture Diagram",
    content = {
        img("Monolithic Architecture", "./src/img/microservice.png") {
            style = "max-width: 500px;"
        }
    }
)

object APIGatewayArchitectureSlide : Slide(
    header = "API Gateway Architecture",
    summary = { +"Centralizes all API traffic, often for microservices and serverless backends." },
    content = {
        highlightOrderedList(
            "When to use" to {
                ul {
                    li { +"Systems with multiple backend services."}
                    li { +"Applications that require centralized authentication, logging, or throttling."}
                }
            },
            "Pros" to {
                ul {
                    li { +"Centralized management for APIs."}
                    li { +"Reduced cross-cutting concerns (e.g., rate limiting, CORS)."}
                }
            },
            "Cons" to {
                ul {
                    li { +"Potential single point of failure." }
                    li { +"Latency and complexity if overloaded." }
                }
            }
        )
    }
)

object APIGatewayArchitectureDiagramSlide : Slide(
    header = "API Gateway Architecture Diagram",
    content = {
        img("Monolithic Architecture", "./api-gateway.png") {
            style = "max-width: 500px;"
        }
    }
)

object ServerlessArchitectureSlide : Slide(
    header = "Serverless Architecture",
    summary = { +"Uses cloud-managed services where you write and deploy functions instead of managing infrastructure." },
    content = {
        highlightOrderedList(
            "When to use" to {
                ul {
                    li { +"Event-driven systems (e.g., IoT data, notifications)."}
                    li { +"Applications with unpredictable or spiky traffic."}
                }
            },
            "Pros" to {
                ul {
                    li { +"Automatic scaling."}
                    li { +"Reduced operational complexity."}
                    li { +"Pay-per-use model."}
                }
            },
            "Cons" to {
                ul {
                    li { +"Cold-start latency." }
                    li { +"Vendor lock-in risks." }
                    li { +"Debugging and monitoring can be more complex." }
                }
            }
        )
    }
)

//object ServerlessArchitectureDiagramSlide : Slide(
//    header = "Serverless Architecture Diagram",
//    content = {
//        img("Monolithic Architecture", "./api-gateway.png") {
//            style = "max-width: 500px;"
//        }
//    }
//)

object EventDrivenArchitectureSlide : Slide(
    header = "Event-Driven Architecture",
    summary = { +"Systems that react to and propagate events across services (e.g., Kafka, RabbitMQ)." },
    content = {
        highlightOrderedList(
            "When to use" to {
                ul {
                    li { +"Systems with high-frequency event generation."}
                    li { +"IoT and real-time analytics."}
                    li { +"Applications with loosely coupled services."}
                }
            },
            "Pros" to {
                ul {
                    li { +"Real-time processing and responsiveness."}
                    li { +"Highly scalable and decoupled."}
                }
            },
            "Cons" to {
                ul {
                    li { +"Requires careful event design to avoid excessive coupling." }
                    li { +"Complex debugging and replaying of events." }
                }
            }
        )
    }
)

object ArchitectureHowToDecideSlide : Slide(
    header = "How to decide?",
    content = {
        div(classes = "content content-center content-100") {
            p(classes = "fragment fade-in") { +"You will get it wrong!" }
            p(classes = "fragment fade-in") { +"And you will re-do it." }
            p(classes = "fragment fade-in") { +"And you will still get it wrong again." }
            p(classes = "fragment fade-in") { +"And then you will re-do it again." }
            p(classes = "fragment fade-in") { +"And it will never be perfect." }
            p(classes = "fragment fade-in") { +"And that's OK." }
            br()
            p(classes = "fragment fade-in") { +"Embrace failure, learn from it, and go on." }
        }
    }
)
