package com.motycka.edu.content.topics.design

import com.motycka.edu.model.Slide
import com.motycka.edu.model.Topic
import com.motycka.edu.model.highlight
import com.motycka.edu.model.mermaidDiagram
import kotlinx.html.*

object ApplicationArchitecturePatternsTopic : Topic(
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
        HowToDecideSlide
    )
)

object CommonArchitecturePatternsSlide : Slide(
    header = "Common Architecture Patterns",
    summary = {
        +"There are several common architecture patterns that are used in the development of software applications."
    },
    content = {
        p {
            +"These are high-level application architecture patterns used to define the structure of the application "
            +"and the way in which the different components of the application interact with each other."
        }
        p {
            strong(classes = "Some")
            +"of the most common architecture patterns include:"
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
        ol {
            li {
                highlight("When to use")
                br()
                ul {
                    li { +"Large, complex systems." }
                    li { +"Early - stage startups." }
                    li { +"Teams with limited resources." }
                }
            }
            li {
                highlight("Pros")
                br()
                ul {
                    li { +"Independent scaling and deployment." }
                    li { +"Fault isolation (failures in one service don't affect others)." }
                    li { +"Easier to adopt different tech stacks per service." }
                }
            }
            li {
                highlight("Cons")
                br()
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
        }
    }
)

object MonolithicArchitectureDiagramSlide : Slide(
    header = "Monolithic Architecture Diagram",
    content = {
        mermaidDiagram("""
            flowchart TB
                subgraph mono["Monolithic Architecture"]
                    UI["User Interface Layer<br/>(Web UI, Mobile, Desktop)"]
                    BL["Business Logic Layer<br/>- User Management<br/>- Product Catalog<br/>- Order Processing<br/>- Payment Processing<br/>- Notification Service"]
                    DAL["Data Access Layer<br/>(ORM, Repository Pattern)"]
                    DB[("Database<br/>(Single shared database)")]

                    UI --> BL
                    BL --> DAL
                    DAL --> DB
                end

                style mono fill:#f9f9f9,stroke:#7F52FF,stroke-width:3px
                style UI fill:#E87E04,stroke:#333,color:#fff
                style BL fill:#7F52FF,stroke:#333,color:#fff
                style DAL fill:#7F52FF,stroke:#333,color:#fff
                style DB fill:#E87E04,stroke:#333,color:#fff
        """)
    }
)

object MicroserviceArchitectureSlide : Slide(
    header = "Microservice Architecture",
    summary = {
        +"An architecture that decomposes the system into smaller, (relatively) independent services."
    },
    content = {
        ol {
            li {
                highlight("When to use")
                br()
                ul {
                    li { +"Small projects or MVPs." }
                    li { +"Early - stage startups." }
                    li { +"Teams with limited resources." }
                }
            }
            li {
                highlight("Pros")
                br()
                ul {
                    li { +"Easier debugging with one codebase." }
                    li {
                        +"Simpler to develop and deploy. **"
                        sub { +"In terms of needing simper infrastructure." }
                    }
                }
            }
            li {
                highlight("Cons")
                br()
                ul {
                    li { +"Difficult to scale certain parts independently." }
                    li { +"Harder to manage as the codebase grows." }
                    li { +"Limited error resilience." }
                }
            }
            li {
                highlight("Pitfalls")
                br()
                ul {
                    li {
                        strong(classes = "Be aware of distributed monoliths!")
                    }
                }
            }
        }
    }
)

object MicroserviceArchitectureDiagramSlide : Slide(
    header = "Microservice Architecture Diagram",
    content = {
        pre {
            style = "font-size: 65%; line-height: 1.3em; text-align: left;"
            +"""
┌─────────────────────────────────────────────────────────────┐
│              Microservice Architecture                      │
│                                                             │
│   Client                                                    │
│     │                                                       │
│     ├──────────────────────────────────────────────────┐   │
│     │                API Gateway                        │   │
│     └──────────────────────────────────────────────────┘   │
│          │          │           │           │               │
│   ┌──────┴───┐  ┌───┴─────┐  ┌─┴────────┐  ┌──┴──────┐   │
│   │  User    │  │ Product │  │  Order   │  │ Payment │   │
│   │ Service  │  │ Service │  │ Service  │  │ Service │   │
│   │          │  │         │  │          │  │         │   │
│   │  - Auth  │  │ - CRUD  │  │ - Create │  │ - Pay   │   │
│   │  - Reg   │  │ - Search│  │ - Update │  │ - Verify│   │
│   └────┬─────┘  └────┬────┘  └────┬─────┘  └────┬────┘   │
│        │             │              │             │         │
│   ┌────┴─────┐  ┌───┴─────┐   ┌───┴──────┐  ┌───┴─────┐  │
│   │  User    │  │ Product │   │  Order   │  │ Payment │  │
│   │    DB    │  │   DB    │   │    DB    │  │   DB    │  │
│   └──────────┘  └─────────┘   └──────────┘  └─────────┘  │
│                                                             │
│   Each service is independently deployable,                │
│   scalable, and has its own database                       │
└─────────────────────────────────────────────────────────────┘
            """.trimIndent()
        }
    }
)

object APIGatewayArchitectureSlide : Slide(
    header = "API Gateway Architecture",
    summary = {
        +"Centralizes all API traffic, often for microservices and serverless backends."
    },
    content = {
        ol {
            li {
                highlight("When to use")
                br()
                ul {
                    li { +"Systems with multiple backend services." }
                    li { +"Applications that require centralized authentication, logging, or throttling." }
                }
            }
            li {
                highlight("Pros")
                br()
                ul {
                    li { +"Centralized management for APIs." }
                    li { +"Reduced cross-cutting concerns (e.g., rate limiting, CORS)." }
                }
            }
            li {
                highlight("Cons")
                br()
                ul {
                    li { +"Potential single point of failure." }
                    li { +"Latency and complexity if overloaded." }
                }
            }
        }
    }
)

object APIGatewayArchitectureDiagramSlide : Slide(
    header = "API Gateway Architecture Diagram",
    content = {
        pre {
            style = "font-size: 65%; line-height: 1.3em; text-align: left;"
            +"""
┌───────────────────────────────────────────────────────────────┐
│               API Gateway Architecture                        │
│                                                               │
│   Clients (Web, Mobile, IoT)                                 │
│        │           │           │                              │
│        └───────────┴───────────┘                              │
│                    │                                          │
│      ┌─────────────────────────────────┐                     │
│      │        API Gateway              │                     │
│      │  - Authentication / OAuth       │                     │
│      │  - Rate Limiting / Throttling   │                     │
│      │  - Request Routing              │                     │
│      │  - Response Aggregation         │                     │
│      │  - Caching                      │                     │
│      │  - Monitoring & Logging         │                     │
│      │  - Protocol Translation         │                     │
│      └─────────────────────────────────┘                     │
│           │         │         │         │                     │
│    ┌──────┴───┐ ┌───┴────┐ ┌─┴──────┐ ┌┴────────┐           │
│    │  User    │ │Product │ │ Order  │ │Payment  │           │
│    │ Service  │ │Service │ │Service │ │Service  │           │
│    └──────────┘ └────────┘ └────────┘ └─────────┘           │
│                                                               │
│   API Gateway acts as single entry point,                    │
│   handling cross-cutting concerns and routing                │
└───────────────────────────────────────────────────────────────┘
            """.trimIndent()
        }
    }
)

object ServerlessArchitectureSlide : Slide(
    header = "Serverless Architecture",
    summary = {
        +"Uses cloud-managed services where you write and deploy functions instead of managing infrastructure."
    },
    content = {
        ol {
            li {
                highlight("When to use")
                br()
                ul {
                    li { +"Event-driven systems (e.g., IoT data, notifications)." }
                    li { +"Applications with unpredictable or spiky traffic." }
                }
            }
            li {
                highlight("Pros")
                br()
                ul {
                    li { +"Automatic scaling." }
                    li { +"Reduced operational complexity." }
                    li { +"Pay-per-use model." }
                }
            }
            li {
                highlight("Cons")
                br()
                ul {
                    li { +"Cold-start latency." }
                    li { +"Vendor lock-in risks." }
                    li { +"Debugging and monitoring can be more complex." }
                }
            }
        }
    }
)

object EventDrivenArchitectureSlide : Slide(
    header = "Event-Driven Architecture",
    summary = {
        +"Systems that react to and propagate events across services (e.g., Kafka, RabbitMQ)."
    },
    content = {
        ol {
            li {
                highlight("When to use")
                br()
                ul {
                    li { +"Systems with high-frequency event generation." }
                    li { +"IoT and real-time analytics." }
                    li { +"Applications with loosely coupled services." }
                }
            }
            li {
                highlight("Pros")
                br()
                ul {
                    li { +"Real-time processing and responsiveness." }
                    li { +"Highly scalable and decoupled." }
                }
            }
            li {
                highlight("Cons")
                br()
                ul {
                    li { +"Requires careful event design to avoid excessive coupling." }
                    li { +"Complex debugging and replaying of events." }
                }
            }
        }
    }
)

object HowToDecideSlide : Slide(
    header = "How to decide?",
    content = {
        div("content content-center content-100") {
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
