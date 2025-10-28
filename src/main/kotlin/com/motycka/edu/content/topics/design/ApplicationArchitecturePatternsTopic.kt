package com.motycka.edu.content.topics.design

import com.motycka.edu.model.Slide
import com.motycka.edu.model.Topic
import com.motycka.edu.model.highlight
import com.motycka.edu.model.svgDiagram
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
        ServerlessArchitectureDiagramSlide,
        EventDrivenArchitectureSlide,
        EventDrivenArchitectureDiagramSlide,
        HexagonalArchitectureSlide,
        HexagonalArchitectureDiagramSlide,
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
            li { +"Hexagonal Architecture" }
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
        svgDiagram(width = 700, height = 450, svgContent = """
            <!-- User Interface Layer -->
            <rect x="150" y="20" width="400" height="70" rx="5" fill="#88DCC0" stroke="#333" stroke-width="2"/>
            <text x="350" y="45" text-anchor="middle" fill="#333" font-size="15" font-weight="bold">User Interface Layer</text>
            <text x="350" y="65" text-anchor="middle" fill="#333" font-size="12">(Web UI, Mobile, Desktop)</text>

            <!-- Arrow UI to BL -->
            <line x1="350" y1="90" x2="350" y2="120" stroke="#333" stroke-width="2" marker-end="url(#arrowhead)"/>

            <!-- Business Logic Layer -->
            <rect x="150" y="130" width="400" height="130" rx="5" fill="#CFB8FF" stroke="#333" stroke-width="2"/>
            <text x="350" y="155" text-anchor="middle" fill="#333" font-size="15" font-weight="bold">Business Logic Layer</text>
            <text x="350" y="180" text-anchor="middle" fill="#333" font-size="12">- User Management</text>
            <text x="350" y="200" text-anchor="middle" fill="#333" font-size="12">- Product Catalog</text>
            <text x="350" y="220" text-anchor="middle" fill="#333" font-size="12">- Order Processing</text>
            <text x="350" y="240" text-anchor="middle" fill="#333" font-size="12">- Payment Processing</text>

            <!-- Arrow BL to DAL -->
            <line x1="350" y1="260" x2="350" y2="290" stroke="#333" stroke-width="2" marker-end="url(#arrowhead)"/>

            <!-- Data Access Layer -->
            <rect x="150" y="300" width="400" height="50" rx="5" fill="#CFB8FF" stroke="#333" stroke-width="2"/>
            <text x="350" y="320" text-anchor="middle" fill="#333" font-size="15" font-weight="bold">Data Access Layer</text>
            <text x="350" y="338" text-anchor="middle" fill="#333" font-size="12">(ORM, Repository Pattern)</text>

            <!-- Arrow DAL to DB -->
            <line x1="350" y1="350" x2="350" y2="380" stroke="#333" stroke-width="2" marker-end="url(#arrowhead)"/>

            <!-- Database -->
            <ellipse cx="350" cy="415" rx="100" ry="35" fill="#B5B8BF" stroke="#333" stroke-width="2"/>
            <text x="350" y="415" text-anchor="middle" fill="#333" font-size="14" font-weight="bold">Database</text>
            <text x="350" y="432" text-anchor="middle" fill="#333" font-size="11">(Single shared database)</text>
        """.trimIndent())
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
        svgDiagram(width = 900, height = 500, svgContent = """
            <!-- Client -->
            <rect x="400" y="20" width="100" height="40" rx="5" fill="#88DCC0" stroke="#333" stroke-width="2"/>
            <text x="450" y="45" text-anchor="middle" fill="#333" font-size="14" font-weight="bold">Client</text>

            <!-- Arrow from Client to API Gateway -->
            <line x1="450" y1="60" x2="450" y2="90" stroke="#333" stroke-width="2" marker-end="url(#arrowhead)"/>

            <!-- API Gateway -->
            <rect x="350" y="100" width="200" height="50" rx="5" fill="#CFB8FF" stroke="#333" stroke-width="2"/>
            <text x="450" y="130" text-anchor="middle" fill="#333" font-size="16" font-weight="bold">API Gateway</text>

            <!-- Arrows from API Gateway to Services -->
            <line x1="380" y1="150" x2="130" y2="220" stroke="#333" stroke-width="2" marker-end="url(#arrowhead)"/>
            <line x1="420" y1="150" x2="310" y2="220" stroke="#333" stroke-width="2" marker-end="url(#arrowhead)"/>
            <line x1="480" y1="150" x2="490" y2="220" stroke="#333" stroke-width="2" marker-end="url(#arrowhead)"/>
            <line x1="520" y1="150" x2="670" y2="220" stroke="#333" stroke-width="2" marker-end="url(#arrowhead)"/>

            <!-- User Service -->
            <rect x="50" y="230" width="160" height="80" rx="5" fill="#CFB8FF" stroke="#333" stroke-width="2"/>
            <text x="130" y="255" text-anchor="middle" fill="#333" font-size="14" font-weight="bold">User Service</text>
            <text x="130" y="275" text-anchor="middle" fill="#333" font-size="12">- Auth</text>
            <text x="130" y="295" text-anchor="middle" fill="#333" font-size="12">- Registration</text>

            <!-- Product Service -->
            <rect x="230" y="230" width="160" height="80" rx="5" fill="#CFB8FF" stroke="#333" stroke-width="2"/>
            <text x="310" y="255" text-anchor="middle" fill="#333" font-size="14" font-weight="bold">Product Service</text>
            <text x="310" y="275" text-anchor="middle" fill="#333" font-size="12">- CRUD</text>
            <text x="310" y="295" text-anchor="middle" fill="#333" font-size="12">- Search</text>

            <!-- Order Service -->
            <rect x="410" y="230" width="160" height="80" rx="5" fill="#CFB8FF" stroke="#333" stroke-width="2"/>
            <text x="490" y="255" text-anchor="middle" fill="#333" font-size="14" font-weight="bold">Order Service</text>
            <text x="490" y="275" text-anchor="middle" fill="#333" font-size="12">- Create</text>
            <text x="490" y="295" text-anchor="middle" fill="#333" font-size="12">- Update</text>

            <!-- Payment Service -->
            <rect x="590" y="230" width="160" height="80" rx="5" fill="#CFB8FF" stroke="#333" stroke-width="2"/>
            <text x="670" y="255" text-anchor="middle" fill="#333" font-size="14" font-weight="bold">Payment Service</text>
            <text x="670" y="275" text-anchor="middle" fill="#333" font-size="12">- Pay</text>
            <text x="670" y="295" text-anchor="middle" fill="#333" font-size="12">- Verify</text>

            <!-- Arrows from Services to Databases -->
            <line x1="130" y1="310" x2="130" y2="350" stroke="#333" stroke-width="2" marker-end="url(#arrowhead)"/>
            <line x1="310" y1="310" x2="310" y2="350" stroke="#333" stroke-width="2" marker-end="url(#arrowhead)"/>
            <line x1="490" y1="310" x2="490" y2="350" stroke="#333" stroke-width="2" marker-end="url(#arrowhead)"/>
            <line x1="670" y1="310" x2="670" y2="350" stroke="#333" stroke-width="2" marker-end="url(#arrowhead)"/>

            <!-- Databases -->
            <ellipse cx="130" cy="380" rx="70" ry="30" fill="#B5B8BF" stroke="#333" stroke-width="2"/>
            <text x="130" y="385" text-anchor="middle" fill="#333" font-size="12" font-weight="bold">User DB</text>

            <ellipse cx="310" cy="380" rx="70" ry="30" fill="#B5B8BF" stroke="#333" stroke-width="2"/>
            <text x="310" y="385" text-anchor="middle" fill="#333" font-size="12" font-weight="bold">Product DB</text>

            <ellipse cx="490" cy="380" rx="70" ry="30" fill="#B5B8BF" stroke="#333" stroke-width="2"/>
            <text x="490" y="385" text-anchor="middle" fill="#333" font-size="12" font-weight="bold">Order DB</text>

            <ellipse cx="670" cy="380" rx="70" ry="30" fill="#B5B8BF" stroke="#333" stroke-width="2"/>
            <text x="670" y="385" text-anchor="middle" fill="#333" font-size="12" font-weight="bold">Payment DB</text>

            <!-- Description -->
            <text x="450" y="450" text-anchor="middle" fill="#333" font-size="13" font-style="italic">
                Each service is independently deployable, scalable, and has its own database
            </text>
        """.trimIndent())
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
        svgDiagram(width = 900, height = 550, svgContent = """
            <!-- Clients -->
            <rect x="100" y="20" width="100" height="40" rx="5" fill="#88DCC0" stroke="#333" stroke-width="2"/>
            <text x="150" y="45" text-anchor="middle" fill="#333" font-size="13" font-weight="bold">Web</text>

            <rect x="250" y="20" width="100" height="40" rx="5" fill="#88DCC0" stroke="#333" stroke-width="2"/>
            <text x="300" y="45" text-anchor="middle" fill="#333" font-size="13" font-weight="bold">Mobile</text>

            <rect x="400" y="20" width="100" height="40" rx="5" fill="#88DCC0" stroke="#333" stroke-width="2"/>
            <text x="450" y="45" text-anchor="middle" fill="#333" font-size="13" font-weight="bold">IoT</text>

            <rect x="550" y="20" width="100" height="40" rx="5" fill="#88DCC0" stroke="#333" stroke-width="2"/>
            <text x="600" y="45" text-anchor="middle" fill="#333" font-size="13" font-weight="bold">Desktop</text>

            <!-- Arrows from Clients to API Gateway -->
            <line x1="150" y1="60" x2="250" y2="110" stroke="#333" stroke-width="2" marker-end="url(#arrowhead)"/>
            <line x1="300" y1="60" x2="350" y2="110" stroke="#333" stroke-width="2" marker-end="url(#arrowhead)"/>
            <line x1="450" y1="60" x2="450" y2="110" stroke="#333" stroke-width="2" marker-end="url(#arrowhead)"/>
            <line x1="600" y1="60" x2="550" y2="110" stroke="#333" stroke-width="2" marker-end="url(#arrowhead)"/>

            <!-- API Gateway -->
            <rect x="200" y="120" width="400" height="180" rx="5" fill="#CFB8FF" stroke="#333" stroke-width="3"/>
            <text x="400" y="145" text-anchor="middle" fill="#333" font-size="16" font-weight="bold">API Gateway</text>

            <!-- API Gateway Features -->
            <text x="220" y="170" fill="#333" font-size="12">• Authentication / OAuth</text>
            <text x="220" y="190" fill="#333" font-size="12">• Rate Limiting / Throttling</text>
            <text x="220" y="210" fill="#333" font-size="12">• Request Routing</text>
            <text x="220" y="230" fill="#333" font-size="12">• Response Aggregation</text>
            <text x="220" y="250" fill="#333" font-size="12">• Caching</text>
            <text x="220" y="270" fill="#333" font-size="12">• Monitoring & Logging</text>
            <text x="220" y="290" fill="#333" font-size="12">• Protocol Translation</text>

            <!-- Arrows from API Gateway to Services -->
            <line x1="270" y1="300" x2="150" y2="370" stroke="#333" stroke-width="2" marker-end="url(#arrowhead)"/>
            <line x1="350" y1="300" x2="310" y2="370" stroke="#333" stroke-width="2" marker-end="url(#arrowhead)"/>
            <line x1="450" y1="300" x2="470" y2="370" stroke="#333" stroke-width="2" marker-end="url(#arrowhead)"/>
            <line x1="530" y1="300" x2="630" y2="370" stroke="#333" stroke-width="2" marker-end="url(#arrowhead)"/>

            <!-- Backend Services -->
            <rect x="80" y="380" width="140" height="60" rx="5" fill="#CFB8FF" stroke="#333" stroke-width="2"/>
            <text x="150" y="415" text-anchor="middle" fill="#333" font-size="14" font-weight="bold">User Service</text>

            <rect x="240" y="380" width="140" height="60" rx="5" fill="#CFB8FF" stroke="#333" stroke-width="2"/>
            <text x="310" y="415" text-anchor="middle" fill="#333" font-size="14" font-weight="bold">Product Service</text>

            <rect x="400" y="380" width="140" height="60" rx="5" fill="#CFB8FF" stroke="#333" stroke-width="2"/>
            <text x="470" y="415" text-anchor="middle" fill="#333" font-size="14" font-weight="bold">Order Service</text>

            <rect x="560" y="380" width="140" height="60" rx="5" fill="#CFB8FF" stroke="#333" stroke-width="2"/>
            <text x="630" y="415" text-anchor="middle" fill="#333" font-size="14" font-weight="bold">Payment Service</text>

            <!-- Description -->
            <text x="450" y="480" text-anchor="middle" fill="#333" font-size="13" font-style="italic">
                API Gateway acts as single entry point, handling cross-cutting concerns
            </text>
            <text x="450" y="500" text-anchor="middle" fill="#333" font-size="13" font-style="italic">
                and routing requests to appropriate backend services
            </text>
        """.trimIndent())
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

object ServerlessArchitectureDiagramSlide : Slide(
    header = "Serverless Architecture Diagram",
    content = {
        svgDiagram(width = 900, height = 500, svgContent = """
            <!-- Event Sources -->
            <rect x="50" y="50" width="140" height="50" rx="5" fill="#88DCC0" stroke="#333" stroke-width="2"/>
            <text x="120" y="80" text-anchor="middle" fill="#333" font-size="13" font-weight="bold">S3 Upload</text>

            <rect x="50" y="130" width="140" height="50" rx="5" fill="#88DCC0" stroke="#333" stroke-width="2"/>
            <text x="120" y="160" text-anchor="middle" fill="#333" font-size="13" font-weight="bold">API Gateway</text>

            <rect x="50" y="210" width="140" height="50" rx="5" fill="#88DCC0" stroke="#333" stroke-width="2"/>
            <text x="120" y="240" text-anchor="middle" fill="#333" font-size="13" font-weight="bold">EventBridge</text>

            <rect x="50" y="290" width="140" height="50" rx="5" fill="#88DCC0" stroke="#333" stroke-width="2"/>
            <text x="120" y="320" text-anchor="middle" fill="#333" font-size="13" font-weight="bold">DynamoDB</text>

            <!-- Arrows to Lambda Functions -->
            <line x1="190" y1="75" x2="280" y2="120" stroke="#333" stroke-width="2" marker-end="url(#arrowhead)"/>
            <line x1="190" y1="155" x2="280" y2="180" stroke="#333" stroke-width="2" marker-end="url(#arrowhead)"/>
            <line x1="190" y1="235" x2="280" y2="240" stroke="#333" stroke-width="2" marker-end="url(#arrowhead)"/>
            <line x1="190" y1="315" x2="280" y2="300" stroke="#333" stroke-width="2" marker-end="url(#arrowhead)"/>

            <!-- Lambda Functions -->
            <rect x="290" y="100" width="160" height="250" rx="5" fill="#CFB8FF" stroke="#333" stroke-width="3"/>
            <text x="370" y="130" text-anchor="middle" fill="#333" font-size="16" font-weight="bold">Lambda Functions</text>

            <rect x="310" y="150" width="120" height="35" rx="3" fill="#9DC0FA" stroke="#fff" stroke-width="1"/>
            <text x="370" y="173" text-anchor="middle" fill="#333" font-size="11">Image Processor</text>

            <rect x="310" y="195" width="120" height="35" rx="3" fill="#9DC0FA" stroke="#fff" stroke-width="1"/>
            <text x="370" y="218" text-anchor="middle" fill="#333" font-size="11">User Handler</text>

            <rect x="310" y="240" width="120" height="35" rx="3" fill="#9DC0FA" stroke="#fff" stroke-width="1"/>
            <text x="370" y="263" text-anchor="middle" fill="#333" font-size="11">Event Processor</text>

            <rect x="310" y="285" width="120" height="35" rx="3" fill="#9DC0FA" stroke="#fff" stroke-width="1"/>
            <text x="370" y="308" text-anchor="middle" fill="#333" font-size="11">Data Transformer</text>

            <!-- Arrows to Backend Services -->
            <line x1="450" y1="150" x2="540" y2="105" stroke="#333" stroke-width="2" marker-end="url(#arrowhead)"/>
            <line x1="450" y1="200" x2="540" y2="165" stroke="#333" stroke-width="2" marker-end="url(#arrowhead)"/>
            <line x1="450" y1="260" x2="540" y2="225" stroke="#333" stroke-width="2" marker-end="url(#arrowhead)"/>
            <line x1="450" y1="300" x2="540" y2="285" stroke="#333" stroke-width="2" marker-end="url(#arrowhead)"/>

            <!-- Backend Services -->
            <rect x="550" y="70" width="140" height="50" rx="5" fill="#B5B8BF" stroke="#333" stroke-width="2"/>
            <text x="620" y="100" text-anchor="middle" fill="#333" font-size="13" font-weight="bold">DynamoDB</text>

            <rect x="550" y="140" width="140" height="50" rx="5" fill="#B5B8BF" stroke="#333" stroke-width="2"/>
            <text x="620" y="170" text-anchor="middle" fill="#333" font-size="13" font-weight="bold">S3</text>

            <rect x="550" y="210" width="140" height="50" rx="5" fill="#B5B8BF" stroke="#333" stroke-width="2"/>
            <text x="620" y="240" text-anchor="middle" fill="#333" font-size="13" font-weight="bold">SQS</text>

            <rect x="550" y="280" width="140" height="50" rx="5" fill="#B5B8BF" stroke="#333" stroke-width="2"/>
            <text x="620" y="310" text-anchor="middle" fill="#333" font-size="13" font-weight="bold">SNS</text>

            <!-- Description -->
            <text x="450" y="380" text-anchor="middle" fill="#333" font-size="13" font-weight="bold">Event-driven, auto-scaling functions</text>
            <text x="450" y="400" text-anchor="middle" fill="#333" font-size="12" font-style="italic">Pay only for execution time • No server management</text>

            <!-- Labels -->
            <text x="120" y="25" text-anchor="middle" fill="#666" font-size="12" font-weight="bold">Event Sources</text>
            <text x="620" y="25" text-anchor="middle" fill="#666" font-size="12" font-weight="bold">Managed Services</text>
        """.trimIndent())
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

object EventDrivenArchitectureDiagramSlide : Slide(
    header = "Event-Driven Architecture Diagram",
    content = {
        svgDiagram(width = 900, height = 550, svgContent = """
            <!-- Publisher Services (Top) -->
            <rect x="50" y="40" width="140" height="60" rx="5" fill="#CFB8FF" stroke="#333" stroke-width="2"/>
            <text x="120" y="65" text-anchor="middle" fill="#333" font-size="13" font-weight="bold">Order Service</text>
            <text x="120" y="85" text-anchor="middle" fill="#333" font-size="11">(Publisher)</text>

            <rect x="230" y="40" width="140" height="60" rx="5" fill="#CFB8FF" stroke="#333" stroke-width="2"/>
            <text x="300" y="65" text-anchor="middle" fill="#333" font-size="13" font-weight="bold">User Service</text>
            <text x="300" y="85" text-anchor="middle" fill="#333" font-size="11">(Publisher)</text>

            <rect x="410" y="40" width="140" height="60" rx="5" fill="#CFB8FF" stroke="#333" stroke-width="2"/>
            <text x="480" y="65" text-anchor="middle" fill="#333" font-size="13" font-weight="bold">Payment Service</text>
            <text x="480" y="85" text-anchor="middle" fill="#333" font-size="11">(Publisher)</text>

            <!-- Arrows from Publishers to Event Bus -->
            <line x1="120" y1="100" x2="200" y2="180" stroke="#333" stroke-width="2" marker-end="url(#arrowhead)"/>
            <line x1="300" y1="100" x2="300" y2="180" stroke="#333" stroke-width="2" marker-end="url(#arrowhead)"/>
            <line x1="480" y1="100" x2="400" y2="180" stroke="#333" stroke-width="2" marker-end="url(#arrowhead)"/>

            <!-- Event Bus / Message Broker -->
            <rect x="150" y="190" width="400" height="150" rx="5" fill="#B5B8BF" stroke="#333" stroke-width="3"/>
            <text x="350" y="220" text-anchor="middle" fill="#333" font-size="18" font-weight="bold">Event Bus / Message Broker</text>
            <text x="350" y="245" text-anchor="middle" fill="#333" font-size="13">(Kafka / RabbitMQ / EventBridge)</text>

            <!-- Event Topics -->
            <rect x="170" y="265" width="160" height="30" rx="3" fill="#88DCC0" stroke="#fff" stroke-width="1"/>
            <text x="250" y="285" text-anchor="middle" fill="#333" font-size="11">Topic: OrderCreated</text>

            <rect x="360" y="265" width="160" height="30" rx="3" fill="#88DCC0" stroke="#fff" stroke-width="1"/>
            <text x="440" y="285" text-anchor="middle" fill="#333" font-size="11">Topic: UserRegistered</text>

            <rect x="170" y="300" width="160" height="30" rx="3" fill="#88DCC0" stroke="#fff" stroke-width="1"/>
            <text x="250" y="320" text-anchor="middle" fill="#333" font-size="11">Topic: PaymentProcessed</text>

            <!-- Arrows from Event Bus to Subscribers -->
            <line x1="200" y1="330" x2="120" y2="410" stroke="#333" stroke-width="2" marker-end="url(#arrowhead)"/>
            <line x1="300" y1="330" x2="300" y2="410" stroke="#333" stroke-width="2" marker-end="url(#arrowhead)"/>
            <line x1="400" y1="330" x2="480" y2="410" stroke="#333" stroke-width="2" marker-end="url(#arrowhead)"/>

            <!-- Subscriber Services (Bottom) -->
            <rect x="50" y="420" width="140" height="60" rx="5" fill="#CFB8FF" stroke="#333" stroke-width="2"/>
            <text x="120" y="445" text-anchor="middle" fill="#333" font-size="13" font-weight="bold">Email Service</text>
            <text x="120" y="465" text-anchor="middle" fill="#333" font-size="11">(Subscriber)</text>

            <rect x="230" y="420" width="140" height="60" rx="5" fill="#CFB8FF" stroke="#333" stroke-width="2"/>
            <text x="300" y="445" text-anchor="middle" fill="#333" font-size="13" font-weight="bold">Analytics Service</text>
            <text x="300" y="465" text-anchor="middle" fill="#333" font-size="11">(Subscriber)</text>

            <rect x="410" y="420" width="140" height="60" rx="5" fill="#CFB8FF" stroke="#333" stroke-width="2"/>
            <text x="480" y="445" text-anchor="middle" fill="#333" font-size="13" font-weight="bold">Inventory Service</text>
            <text x="480" y="465" text-anchor="middle" fill="#333" font-size="11">(Subscriber)</text>

            <rect x="590" y="420" width="140" height="60" rx="5" fill="#CFB8FF" stroke="#333" stroke-width="2"/>
            <text x="660" y="445" text-anchor="middle" fill="#333" font-size="13" font-weight="bold">Audit Service</text>
            <text x="660" y="465" text-anchor="middle" fill="#333" font-size="11">(Subscriber)</text>

            <line x1="520" y1="330" x2="660" y2="410" stroke="#333" stroke-width="2" marker-end="url(#arrowhead)"/>

            <!-- Description -->
            <text x="450" y="520" text-anchor="middle" fill="#333" font-size="13" font-weight="bold">Asynchronous, loosely coupled communication</text>
            <text x="450" y="540" text-anchor="middle" fill="#333" font-size="12" font-style="italic">Services publish events without knowing subscribers • High scalability</text>
        """.trimIndent())
    }
)

object HexagonalArchitectureSlide : Slide(
    header = "Hexagonal Architecture",
    summary = {
        +"Also known as Ports and Adapters. Isolates the core business logic from external concerns."
    },
    content = {
        ol {
            li {
                highlight("When to use")
                br()
                ul {
                    li { +"Complex domain logic that needs to be tested in isolation." }
                    li { +"Applications that need to support multiple interfaces (UI, API, CLI)." }
                    li { +"Systems where business rules change frequently." }
                }
            }
            li {
                highlight("Pros")
                br()
                ul {
                    li { +"High testability - business logic isolated from infrastructure." }
                    li { +"Flexibility - easy to swap adapters (databases, frameworks)." }
                    li { +"Clear separation of concerns." }
                }
            }
            li {
                highlight("Cons")
                br()
                ul {
                    li { +"Initial complexity and learning curve." }
                    li { +"More boilerplate code (interfaces, adapters)." }
                    li { +"Can be overkill for simple CRUD applications." }
                }
            }
        }
    }
)

object HexagonalArchitectureDiagramSlide : Slide(
    header = "Hexagonal Architecture Diagram",
    content = {
        svgDiagram(width = 900, height = 600, svgContent = """
            <!-- Central Hexagon (Domain) -->
            <polygon points="450,120 570,180 570,300 450,360 330,300 330,180"
                     fill="#CFB8FF" stroke="#333" stroke-width="3"/>
            <text x="450" y="220" text-anchor="middle" fill="#333" font-size="18" font-weight="bold">Domain / Core</text>
            <text x="450" y="245" text-anchor="middle" fill="#333" font-size="14">Business Logic</text>
            <text x="450" y="270" text-anchor="middle" fill="#333" font-size="12">• Use Cases</text>
            <text x="450" y="290" text-anchor="middle" fill="#333" font-size="12">• Domain Models</text>
            <text x="450" y="310" text-anchor="middle" fill="#333" font-size="12">• Business Rules</text>

            <!-- Primary Adapters (Left - Driving) -->
            <text x="150" y="100" text-anchor="middle" fill="#666" font-size="13" font-weight="bold">Primary Adapters (Driving)</text>

            <rect x="50" y="130" width="140" height="50" rx="5" fill="#88DCC0" stroke="#333" stroke-width="2"/>
            <text x="120" y="160" text-anchor="middle" fill="#333" font-size="13" font-weight="bold">REST API</text>

            <rect x="50" y="210" width="140" height="50" rx="5" fill="#88DCC0" stroke="#333" stroke-width="2"/>
            <text x="120" y="240" text-anchor="middle" fill="#333" font-size="13" font-weight="bold">Web UI</text>

            <rect x="50" y="290" width="140" height="50" rx="5" fill="#88DCC0" stroke="#333" stroke-width="2"/>
            <text x="120" y="320" text-anchor="middle" fill="#333" font-size="13" font-weight="bold">CLI</text>

            <!-- Arrows from Primary Adapters to Ports -->
            <line x1="190" y1="155" x2="320" y2="190" stroke="#333" stroke-width="2" marker-end="url(#arrowhead)"/>
            <line x1="190" y1="235" x2="320" y2="235" stroke="#333" stroke-width="2" marker-end="url(#arrowhead)"/>
            <line x1="190" y1="315" x2="320" y2="280" stroke="#333" stroke-width="2" marker-end="url(#arrowhead)"/>

            <!-- Primary Ports (Left) -->
            <circle cx="310" cy="190" r="12" fill="#9DC0FA" stroke="#fff" stroke-width="2"/>
            <circle cx="310" cy="240" r="12" fill="#9DC0FA" stroke="#fff" stroke-width="2"/>
            <circle cx="310" cy="290" r="12" fill="#9DC0FA" stroke="#fff" stroke-width="2"/>

            <!-- Secondary Adapters (Right - Driven) -->
            <text x="750" y="100" text-anchor="middle" fill="#666" font-size="13" font-weight="bold">Secondary Adapters (Driven)</text>

            <rect x="710" y="130" width="140" height="50" rx="5" fill="#B5B8BF" stroke="#333" stroke-width="2"/>
            <text x="780" y="160" text-anchor="middle" fill="#333" font-size="13" font-weight="bold">PostgreSQL</text>

            <rect x="710" y="210" width="140" height="50" rx="5" fill="#B5B8BF" stroke="#333" stroke-width="2"/>
            <text x="780" y="240" text-anchor="middle" fill="#333" font-size="13" font-weight="bold">Email Service</text>

            <rect x="710" y="290" width="140" height="50" rx="5" fill="#B5B8BF" stroke="#333" stroke-width="2"/>
            <text x="780" y="320" text-anchor="middle" fill="#333" font-size="13" font-weight="bold">External API</text>

            <!-- Arrows from Ports to Secondary Adapters -->
            <line x1="580" y1="190" x2="700" y2="155" stroke="#333" stroke-width="2" marker-end="url(#arrowhead)"/>
            <line x1="580" y1="240" x2="700" y2="235" stroke="#333" stroke-width="2" marker-end="url(#arrowhead)"/>
            <line x1="580" y1="290" x2="700" y2="315" stroke="#333" stroke-width="2" marker-end="url(#arrowhead)"/>

            <!-- Secondary Ports (Right) -->
            <circle cx="590" cy="190" r="12" fill="#9DC0FA" stroke="#fff" stroke-width="2"/>
            <circle cx="590" cy="240" r="12" fill="#9DC0FA" stroke="#fff" stroke-width="2"/>
            <circle cx="590" cy="290" r="12" fill="#9DC0FA" stroke="#fff" stroke-width="2"/>

            <!-- Port Labels -->
            <text x="260" y="170" text-anchor="end" fill="#666" font-size="10">Port</text>
            <text x="260" y="225" text-anchor="end" fill="#666" font-size="10">Port</text>
            <text x="260" y="275" text-anchor="end" fill="#666" font-size="10">Port</text>

            <text x="640" y="170" text-anchor="start" fill="#666" font-size="10">Port</text>
            <text x="640" y="225" text-anchor="start" fill="#666" font-size="10">Port</text>
            <text x="640" y="275" text-anchor="start" fill="#666" font-size="10">Port</text>

            <!-- Description -->
            <text x="450" y="420" text-anchor="middle" fill="#333" font-size="13" font-weight="bold">Ports define interfaces, Adapters implement them</text>
            <text x="450" y="440" text-anchor="middle" fill="#333" font-size="12" font-style="italic">Business logic has no dependencies on external frameworks or infrastructure</text>

            <!-- Legend -->
            <rect x="330" y="470" width="15" height="15" fill="#CFB8FF" stroke="#333" stroke-width="1"/>
            <text x="355" y="482" fill="#333" font-size="11">Business Logic</text>

            <rect x="460" y="470" width="15" height="15" fill="#88DCC0" stroke="#333" stroke-width="1"/>
            <text x="485" y="482" fill="#333" font-size="11">User-Facing</text>

            <rect x="580" y="470" width="15" height="15" fill="#B5B8BF" stroke="#333" stroke-width="1"/>
            <text x="605" y="482" fill="#333" font-size="11">Infrastructure</text>

            <circle cx="714" cy="477" r="7" fill="#9DC0FA" stroke="#fff" stroke-width="1"/>
            <text x="729" y="482" fill="#333" font-size="11">Ports</text>
        """.trimIndent())
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
