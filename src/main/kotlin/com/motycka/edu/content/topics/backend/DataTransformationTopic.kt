package com.motycka.edu.content.topics.backend

import com.motycka.edu.model.Topic
import com.motycka.edu.model.Slide
import com.motycka.edu.model.kotlinPlayground
import com.motycka.edu.model.twoColumns
import com.motycka.edu.model.highlight
import com.motycka.edu.model.inlineCode
import com.motycka.edu.model.svgDiagram
import kotlinx.html.*

object DataTransformationTopic : Topic(
    title = "Data Transformation",
    slides = listOf(
        DataTransformationIntroSlide,
        LayerModelsSlide,
        MappingWithExtensionsSlide,
        TransformationBestPracticesSlide,
        HexagonalArchitectureSlide
    )
)

object DataTransformationIntroSlide : Slide(
    header = "Data Transformation Between Layers",
    summary = {
        +"In a well-architected Spring Boot application, each layer uses its own data representation. "
        +"The service layer is responsible for transforming data between these representations."
    },
    content = {
        p {
            +"Different layers have different concerns:"
        }
        ul {
            li {
                strong { +"Database Layer (Entity)" }
                +" - Optimized for persistence (JPA annotations, nullable fields, auto-generated IDs)"
            }
            li {
                strong { +"Business Logic Layer (Domain)" }
                +" - Clean, type-safe models focused on business rules"
            }
            li {
                strong { +"API Layer (DTO)" }
                +" - Optimized for data transfer (serialization, versioning, documentation)"
            }
        }
        p {
            strong { +"Why separate models?" }
        }
        ul {
            li { +"Each layer can evolve independently" }
            li { +"Security - control what data is exposed" }
            li { +"Flexibility - API changes don't affect database schema" }
            li { +"Clean code - each model serves a single purpose" }
            li { +"Testability - business logic works with clean domain models" }
        }
    }
)

object LayerModelsSlide : Slide(
    header = "The Three Layer Models",
    summary = {
        +"Each layer has its own data representation with different responsibilities and characteristics."
    },
    content = {
        twoColumns(
            left = {
                p {
                    strong { +"1. DTOs (API Layer)" }
                }
                ul {
                    li { +"Request DTOs for incoming data" }
                    li { +"Response DTOs for outgoing data" }
                    li { +"Optimized for serialization" }
                    li { +"Can include computed fields" }
                }
            },
            right = {
                kotlinPlayground(
                    code = """
                        // Request DTO
                        data class NewTaskRequest(
                            val description: String
                        )

                        // Response DTO
                        data class TaskResponse(
                            val id: Int,
                            val description: String,
                            val status: TaskStatus
                        )
                    """.trimIndent(),
                    executable = false
                )
            },
        )
        twoColumns(
            left = {
                p {
                    strong { +"2. Domain Model (Business Logic)" }
                }
                ul {
                    li { +"Clean, immutable data classes" }
                    li { +"Non-null types where appropriate" }
                    li { +"Focused on business concepts" }
                    li { +"No framework dependencies" }
                }
            },
            right = {
                kotlinPlayground(
                    code = """
                        data class Task(
                            val id: Int,
                            val description: String,
                            val status: TaskStatus
                        )
                    """.trimIndent(),
                    executable = false
                )
            },
        )
        twoColumns(
            left = {
                p {
                    strong { +"3. Entity (Database)" }
                }
                ul {
                    li { +"JPA annotations (@Entity, @Id)" }
                    li { +"Nullable fields for DB compatibility" }
                    li { +"Auto-generated IDs" }
                    li { +"Mutable (var) for JPA" }
                }
            },
            right = {
                kotlinPlayground(
                    code = """
                        @Entity
                        @Table(name = "tasks")
                        data class TaskEntity(
                            @Id
                            @GeneratedValue(strategy = GenerationType.IDENTITY)
                            val id: Long? = null,

                            @Column(length = 1000)
                            var description: String? = null,

                            @Column(nullable = false)
                            var status: TaskStatus = TaskStatus.NEW
                        )
                    """.trimIndent(),
                    executable = false
                )
            },
        )
//        twoColumns(
//            ratio = 2 to 3,
//            left = {
//
//            },
//            right = {
//
//                p {
//                    strong { +"Data Flow:" }
//                }
//                kotlinPlayground(
//                    code = """
//                        // Controller receives Request DTO
//                        @PostMapping
//                        fun createTask(@RequestBody request: NewTaskRequest): TaskResponse {
//                            // Service works with Domain models
//                            val task = taskService.createTask(request)
//                            // Return Response DTO
//                            return task.toResponse()
//                        }
//                    """.trimIndent(),
//                    executable = false
//                )
//            }
//        )
    }
)

object MappingWithExtensionsSlide : Slide(
    header = "Mapping with Extension Functions",
    summary = {
        +"Use extension functions to convert between layer models. This keeps transformation logic clean and reusable."
    },
    content = {
        twoColumns(
            ratio = 2 to 3,
            left = {
                p {
                    +"Extension functions provide a clean, reusable way to transform data between layers:"
                }
                ul {
                    li { inlineCode("Entity.toDomain()"); +" - Database to business logic" }
                    li { inlineCode("Domain.toEntity()"); +" - Business logic to database" }
                    li { inlineCode("Domain.toResponse()"); +" - Business logic to API" }
                }
                p {
                    strong { +"Benefits:" }
                }
                ul {
                    li { +"Centralized transformation logic" }
                    li { +"Easy to test" }
                    li { +"Readable and discoverable" }
                    li { +"Can chain transformations" }
                }
            },
            right = {
                p {
                    strong { +"TaskMappers.kt" }
                }
                kotlinPlayground(
                    code = """
                        // Entity -> Domain
                        fun TaskEntity.toDomain(): Task {
                            return Task(
                                id = this.id?.toInt() ?: 0,
                                description = this.description ?: "",
                                status = this.status
                            )
                        }

                        // Domain -> Entity
                        fun Task.toEntity(): TaskEntity {
                            return TaskEntity(
                                id = if (this.id == 0) null else this.id.toLong(),
                                description = this.description,
                                status = this.status
                            )
                        }

                        // Domain -> Response DTO
                        fun Task.toResponse(): TaskResponse {
                            return TaskResponse(
                                id = this.id,
                                description = this.description,
                                status = this.status
                            )
                        }
                    """.trimIndent(),
                    executable = false
                )
            }
        )
    }
)

object TransformationBestPracticesSlide : Slide(
    header = "Layered Architecture (Common Approach)",
    summary = {
        +"The approach we've shown (extension functions with shared models) is common in Spring Boot applications."
    },
    content = {
        p {
            strong { +"The extension function approach is common" }
            +" in Spring Boot applications and works well for most projects. However, it creates some "
            strong { +"coupling between layers" }
            +"."
            +"In this architecture, the layers know a little bit about modules it depends on. This is OK, as long as it is not the other way around: the lower layers should not know about the upper layers."
        }

        svgDiagram(
            width = 600,
            height = 400,
            svgContent = """
                <!-- Controller Box -->
                <rect x="150" y="30" width="300" height="80" rx="8" fill="#E8F4F8" stroke="#333" stroke-width="2"/>
                <text x="300" y="60" text-anchor="middle" font-size="18" font-weight="bold" fill="#333">Controller</text>
                <text x="300" y="85" text-anchor="middle" font-size="14" fill="#666">works with DTOs</text>

                <!-- Service Box -->
                <rect x="150" y="160" width="300" height="80" rx="8" fill="#FFF4E6" stroke="#333" stroke-width="2"/>
                <text x="300" y="190" text-anchor="middle" font-size="18" font-weight="bold" fill="#333">Service</text>
                <text x="300" y="215" text-anchor="middle" font-size="14" fill="#666">works with Domain</text>

                <!-- Repository Box -->
                <rect x="150" y="290" width="300" height="80" rx="8" fill="#E8F5E9" stroke="#333" stroke-width="2"/>
                <text x="300" y="320" text-anchor="middle" font-size="18" font-weight="bold" fill="#333">Repository</text>
                <text x="300" y="345" text-anchor="middle" font-size="14" fill="#666">works with Entities</text>

                <!-- Arrow Controller -> Service -->
                <line x1="250" y1="110" x2="250" y2="160" stroke="#333" stroke-width="2" marker-end="url(#arrowhead)"/>
                <text x="260" y="135" font-size="12" fill="#333">knows about</text>
                <text x="260" y="148" font-size="12" fill="#333">Domain models</text>

                <!-- Arrow Service -> Repository -->
                <line x1="250" y1="240" x2="250" y2="290" stroke="#333" stroke-width="2" marker-end="url(#arrowhead)"/>
                <text x="260" y="265" font-size="12" fill="#333">knows about Entity models</text>
            """
        )

        p {
            strong { +"Characteristics:" }
        }
        ul {
            li {
                strong { +"Coupling between layers" }
                +" - Each layer imports and uses models from other layers"
            }
            li {
                strong { +"Simple and pragmatic" }
                +" - Easy to understand and implement"
            }
            li {
                strong { +"Extension functions for mapping" }
                +" - Clean syntax for transformations"
            }
            li {
                strong { +"Changes ripple" }
                +" - Modifying a model may require changes in multiple layers"
            }
        }

        p {
            strong { +"Pros:" }
            +" Simple, pragmatic, less boilerplate, suitable for most applications. "
            strong { +"Cons:" }
            +" Layers are coupled - changes ripple across layers, harder to test in isolation."
        }

        p {
            strong { +"Best for:" }
            +" Most Spring Boot applications, especially when rapid development is needed and the domain is relatively stable."
        }
    }
)

object HexagonalArchitectureSlide : Slide(
    header = "Hexagonal Architecture (True Separation)",
    summary = {
        +"For true separation, hexagonal architecture (Ports & Adapters) keeps the domain completely independent."
    },
    content = {
        p {
            +"In "
            strong { +"Hexagonal Architecture" }
            +" (also called Ports & Adapters), each layer knows "
            strong { +"nothing" }
            +" about the others. The domain is at the center, and adapters depend on the domain - "
            strong { +"never the reverse" }
            +"."
        }

        svgDiagram(
            width = 700,
            height = 450,
            svgContent = """
                <!-- Domain Core Group Background -->
                <rect x="220" y="120" width="260" height="220" rx="12" fill="#f0f0f0" stroke="#999" stroke-width="1" stroke-dasharray="5,5"/>
                <text x="350" y="145" text-anchor="middle" font-size="14" font-weight="bold" fill="#666">Domain Core</text>

                <!-- Domain Model (Center - Purple) -->
                <rect x="275" y="200" width="150" height="70" rx="8" fill="#7F52FF" stroke="#333" stroke-width="2"/>
                <text x="350" y="230" text-anchor="middle" font-size="16" font-weight="bold" fill="#fff">Domain Model</text>

                <!-- Input Port (Left side of core) -->
                <rect x="240" y="160" width="120" height="60" rx="6" fill="#E8F4F8" stroke="#333" stroke-width="2"/>
                <text x="300" y="185" text-anchor="middle" font-size="14" font-weight="bold" fill="#333">Input Port</text>
                <text x="300" y="205" text-anchor="middle" font-size="12" fill="#666">(Interface)</text>

                <!-- Output Port (Right side of core) -->
                <rect x="340" y="290" width="120" height="60" rx="6" fill="#E8F4F8" stroke="#333" stroke-width="2"/>
                <text x="400" y="315" text-anchor="middle" font-size="14" font-weight="bold" fill="#333">Output Port</text>
                <text x="400" y="335" text-anchor="middle" font-size="12" fill="#666">(Interface)</text>

                <!-- REST Controller Adapter (Top Left) -->
                <rect x="50" y="30" width="150" height="70" rx="8" fill="#FFF4E6" stroke="#333" stroke-width="2"/>
                <text x="125" y="55" text-anchor="middle" font-size="14" font-weight="bold" fill="#333">REST Controller</text>
                <text x="125" y="75" text-anchor="middle" font-size="12" fill="#666">(Adapter)</text>

                <!-- JPA Repository Adapter (Bottom Right) -->
                <rect x="500" y="350" width="150" height="70" rx="8" fill="#E8F5E9" stroke="#333" stroke-width="2"/>
                <text x="575" y="375" text-anchor="middle" font-size="14" font-weight="bold" fill="#333">JPA Repository</text>
                <text x="575" y="395" text-anchor="middle" font-size="12" fill="#666">(Adapter)</text>

                <!-- Arrow: REST -> Input Port -->
                <line x1="175" y1="100" x2="260" y2="170" stroke="#333" stroke-width="2" marker-end="url(#arrowhead)"/>
                <text x="200" y="125" font-size="11" fill="#333">depends on</text>

                <!-- Arrow: Input Port -> Domain -->
                <line x1="330" y1="220" x2="280" y2="235" stroke="#333" stroke-width="2" marker-end="url(#arrowhead)"/>

                <!-- Arrow: Domain -> Output Port -->
                <line x1="370" y1="270" x2="390" y2="290" stroke="#333" stroke-width="2" marker-end="url(#arrowhead)"/>

                <!-- Arrow: JPA -> Output Port -->
                <line x1="515" y1="370" x2="455" y2="345" stroke="#333" stroke-width="2" marker-end="url(#arrowhead)"/>
                <text x="470" y="355" font-size="11" fill="#333">implements</text>

                <!-- Labels -->
                <text x="50" y="20" font-size="13" font-weight="bold" fill="#666">Adapters</text>
            """
        )

        p {
            strong { +"Key characteristics:" }
        }
        ul {
            li {
                strong { +"Domain is independent" }
                +" - No knowledge of REST, DB, or any external concerns"
            }
            li {
                strong { +"Ports define contracts" }
                +" - Domain defines interfaces (ports) that adapters must implement"
            }
            li {
                strong { +"Adapters handle transformation" }
                +" - REST and DB adapters convert their models to/from domain models"
            }
            li {
                strong { +"Dependency inversion" }
                +" - Adapters depend on the domain, not the other way around"
            }
        }

        p {
            strong { +"Pros:" }
            +" True independence, easy to swap implementations, excellent testability, clear boundaries. "
            strong { +"Cons:" }
            +" More boilerplate, steeper learning curve, can be overkill for simple applications."
        }

        p {
            strong { +"Best for:" }
            +" Complex domains, applications with multiple adapters (REST + GraphQL + CLI), microservices with strict boundaries."
        }

        p {
            strong { +"For this course:" }
            +" We use the common layered approach. It's pragmatic and suitable for most Spring Boot applications. "
            +"Consider hexagonal architecture as your applications grow in complexity."
        }
    }
)
