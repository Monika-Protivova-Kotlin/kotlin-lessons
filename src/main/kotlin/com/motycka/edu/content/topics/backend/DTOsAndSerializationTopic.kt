package com.motycka.edu.content.topics.backend

import com.motycka.edu.model.Slide
import com.motycka.edu.model.Topic
import com.motycka.edu.model.highlight
import com.motycka.edu.model.inlineCode
import com.motycka.edu.model.kotlinPlayground
import kotlinx.html.*

object DTOsAndSerializationTopic : Topic(
    title = "DTOs & Serialization",
    slides = listOf(
        WhatAreDTOsSlide,
        WhyUseDTOsSlide,
        DTOExamplesSlide,
        JSONSerializationSlide,
        MappingDomainToDTOSlide,
        DTOBestPracticesSlide
    )
)

object WhatAreDTOsSlide : Slide(
    header = "What are DTOs?",
    summary = {
        +"DTO stands for "
        strong { +"Data Transfer Object" }
        +"."
    },
    content = {
        p {
            +"A DTO is a simple object that carries data between processes, typically between the "
            strong { +"presentation layer" }
            +" (controllers) and the "
            strong { +"client" }
            +" (frontend, mobile app, etc.)."
        }
        p {
            +"DTOs are designed specifically for "
            strong { +"data transfer" }
            +" and contain no business logic - only data and accessors."
        }
        p {
            +"Key characteristics:"
        }
        ul {
            li { +"Simple data structures (usually data classes in Kotlin)" }
            li { +"No business logic or behavior" }
            li { +"Easily serializable to JSON/XML" }
            li { +"Contain only the data needed for a specific use case" }
            li { +"Can be different from internal domain models" }
        }
        kotlinPlayground(
            code = """
                // DTO example - simple data class with no logic
                data class CharacterDTO(
                    val id: Long,
                    val name: String,
                    val type: String,
                    val level: Int,
                    val health: Int,
                    val attack: Int,
                    val defense: Int,
                    val createdAt: String
                )

                // Request DTO - used for creating new resources
                data class CreateCharacterRequest(
                    val name: String,
                    val type: String,
                    val level: Int
                )

                // Response DTO - used for returning data to client
                data class CharacterSummaryDTO(
                    val id: Long,
                    val name: String,
                    val type: String,
                    val level: Int
                )
            """.trimIndent(),
            executable = false
        )
    }
)

object WhyUseDTOsSlide : Slide(
    header = "Why Use DTOs?",
    summary = {
        +"DTOs provide separation between your API contract and internal domain models."
    },
    content = {
        p {
            +"Using DTOs provides several benefits:"
        }
        ul {
            li {
                strong { +"API Stability" }
                br()
                +"Changes to internal domain models don't force API changes, maintaining backward compatibility"
            }
            li {
                strong { +"Security" }
                br()
                +"Control exactly what data is exposed to clients, hiding sensitive or internal fields"
            }
            li {
                strong { +"Flexibility" }
                br()
                +"Different endpoints can return different views of the same data"
            }
            li {
                strong { +"Performance" }
                br()
                +"Send only the data clients need, reducing payload size"
            }
            li {
                strong { +"Versioning" }
                br()
                +"Support multiple API versions with different DTOs for the same domain model"
            }
            li {
                strong { +"Validation" }
                br()
                +"Validate input separately from domain logic using annotations"
            }
        }
        p {
            strong { +"Example:" }
            +" Your "
            inlineCode("Character")
            +" domain model might have 20 fields including database IDs, timestamps, and internal state. "
            +"Your "
            inlineCode("CharacterDTO")
            +" might only expose 8 relevant fields to the client."
        }
    }
)

object DTOExamplesSlide : Slide(
    header = "DTO Examples",
    summary = {
        +"Different types of DTOs for different use cases."
    },
    content = {
        p {
            +"Common DTO patterns:"
        }
        kotlinPlayground(
            code = """
                // Response DTO - data returned to client
                data class CharacterDTO(
                    val id: Long,
                    val name: String,
                    val type: CharacterType,
                    val level: Int,
                    val health: Int,
                    val attack: Int,
                    val defense: Int,
                    val experiencePoints: Int,
                    val createdAt: LocalDateTime
                )

                // Request DTO - data received from client to create resource
                data class CreateCharacterRequest(
                    val name: String,
                    val type: CharacterType,
                    val level: Int = 1
                )

                // Request DTO - data received from client to update resource
                data class UpdateCharacterRequest(
                    val name: String?,
                    val level: Int?,
                    val health: Int?
                )

                // Summary DTO - minimal data for lists
                data class CharacterSummaryDTO(
                    val id: Long,
                    val name: String,
                    val type: CharacterType,
                    val level: Int
                )

                // Nested DTO - combines multiple related entities
                data class BattleDTO(
                    val id: Long,
                    val character1: CharacterSummaryDTO,
                    val character2: CharacterSummaryDTO,
                    val status: BattleStatus,
                    val winner: CharacterSummaryDTO?,
                    val startedAt: LocalDateTime,
                    val completedAt: LocalDateTime?
                )

                // Error DTO - standardized error response
                data class ErrorResponse(
                    val timestamp: LocalDateTime,
                    val status: Int,
                    val error: String,
                    val message: String,
                    val path: String
                )
            """.trimIndent(),
            executable = false
        )
    }
)

object JSONSerializationSlide : Slide(
    header = "JSON Serialization",
    summary = {
        +"Spring Boot automatically serializes DTOs to JSON using Jackson."
    },
    content = {
        p {
            +"Spring Boot uses the "
            strong { +"Jackson" }
            +" library to automatically convert Kotlin objects to JSON and vice versa."
        }
        p {
            +"This happens automatically when:"
        }
        ul {
            li { +"You return a DTO from a controller method → serialized to JSON response" }
            li { +"You use "
                inlineCode("@RequestBody")
                +" → JSON request deserialized to DTO"
            }
        }
        p { +"Jackson annotations for customization:" }
        kotlinPlayground(
            code = """
                import com.fasterxml.jackson.annotation.*

                data class CharacterDTO(
                    val id: Long,
                    val name: String,
                    val type: CharacterType,

                    // Rename field in JSON
                    @JsonProperty("lvl")
                    val level: Int,

                    // Exclude field from serialization
                    @JsonIgnore
                    val internalId: String,

                    // Custom date format
                    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
                    val createdAt: LocalDateTime,

                    // Include only if not null
                    @JsonInclude(JsonInclude.Include.NON_NULL)
                    val deletedAt: LocalDateTime? = null
                )

                // Serialized JSON example:
                // {
                //   "id": 123,
                //   "name": "Aragorn",
                //   "type": "WARRIOR",
                //   "lvl": 10,
                //   "createdAt": "2024-10-01 15:30:00"
                // }
            """.trimIndent(),
            executable = false
        )
        p {
            +"By default, Jackson uses field names as JSON keys, but you can customize this behavior with annotations."
        }
    }
)

object MappingDomainToDTOSlide : Slide(
    header = "Mapping Domain Models to DTOs",
    summary = {
        +"Convert between domain models and DTOs using extension functions or mapper classes."
    },
    content = {
        p {
            +"You need to convert between your internal domain models and DTOs. Common approaches:"
        }
        p { strong { +"1. Extension functions (simple mapping):" } }
        kotlinPlayground(
            code = """
                // Domain model
                data class Character(
                    val id: Long,
                    val name: String,
                    val type: CharacterType,
                    val level: Int,
                    val health: Int,
                    val maxHealth: Int,
                    val attack: Int,
                    val defense: Int,
                    val experiencePoints: Int,
                    val createdAt: LocalDateTime,
                    val updatedAt: LocalDateTime,
                    val isDeleted: Boolean
                )

                // Extension function to convert to DTO
                fun Character.toDTO(): CharacterDTO {
                    return CharacterDTO(
                        id = this.id,
                        name = this.name,
                        type = this.type,
                        level = this.level,
                        health = this.health,
                        attack = this.attack,
                        defense = this.defense,
                        experiencePoints = this.experiencePoints,
                        createdAt = this.createdAt
                    )
                }

                // Extension function for summary DTO
                fun Character.toSummaryDTO(): CharacterSummaryDTO {
                    return CharacterSummaryDTO(
                        id = this.id,
                        name = this.name,
                        type = this.type,
                        level = this.level
                    )
                }

                // Usage in service
                class CharacterService {
                    fun getCharacterById(id: Long): CharacterDTO {
                        val character = characterRepository.findById(id)
                        return character.toDTO()  // Convert to DTO
                    }

                    fun getAllCharacters(): List<CharacterSummaryDTO> {
                        val characters = characterRepository.findAll()
                        return characters.map { it.toSummaryDTO() }
                    }
                }
            """.trimIndent(),
            executable = false
        )
    }
)

object DTOBestPracticesSlide : Slide(
    header = "DTO Best Practices",
    summary = {
        +"Follow these best practices when working with DTOs."
    },
    content = {
        ul {
            li {
                strong { +"Keep DTOs simple" }
                br()
                +"DTOs should contain only data, no business logic or validation beyond basic constraints"
            }
            li {
                strong { +"Use different DTOs for different operations" }
                br()
                +"CreateRequest, UpdateRequest, Response DTOs serve different purposes"
            }
            li {
                strong { +"Validate DTOs at the controller level" }
                br()
                +"Use validation annotations like "
                inlineCode("@NotNull")
                +", "
                inlineCode("@Size")
                +", "
                inlineCode("@Min")
                +", "
                inlineCode("@Max")
            }
            li {
                strong { +"Use consistent naming" }
                br()
                +"Suffix with DTO, Request, Response, or similar to clearly indicate purpose"
            }
            li {
                strong { +"Version your DTOs" }
                br()
                +"When breaking changes are needed, create new DTO versions rather than modifying existing ones"
            }
            li {
                strong { +"Document your DTOs" }
                br()
                +"Add KDoc comments explaining purpose and constraints, especially for public APIs"
            }
            li {
                strong { +"Keep mapping logic separate" }
                br()
                +"Use extension functions or mapper classes to convert between domain and DTOs"
            }
            li {
                strong { +"Don't expose internal implementation details" }
                br()
                +"Database IDs, internal timestamps, audit fields should be carefully considered"
            }
        }
        p { +"Example with validation:" }
        kotlinPlayground(
            code = """
                import javax.validation.constraints.*

                data class CreateCharacterRequest(
                    @field:NotBlank(message = "Name is required")
                    @field:Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
                    val name: String,

                    @field:NotNull(message = "Character type is required")
                    val type: CharacterType,

                    @field:Min(value = 1, message = "Level must be at least 1")
                    @field:Max(value = 100, message = "Level cannot exceed 100")
                    val level: Int = 1
                )

                @RestController
                @RequestMapping("/api/characters")
                class CharacterController {

                    @PostMapping
                    fun createCharacter(@Valid @RequestBody request: CreateCharacterRequest): CharacterDTO {
                        // @Valid triggers validation; if validation fails, returns 400 Bad Request
                        return characterService.createCharacter(request)
                    }
                }
            """.trimIndent(),
            executable = false
        )
    }
)
