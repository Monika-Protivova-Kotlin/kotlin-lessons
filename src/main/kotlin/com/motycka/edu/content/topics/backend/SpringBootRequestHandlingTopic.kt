package com.motycka.edu.content.topics.backend

import com.motycka.edu.model.Slide
import com.motycka.edu.model.Topic
import com.motycka.edu.model.highlight
import com.motycka.edu.model.inlineCode
import com.motycka.edu.model.kotlinPlayground
import kotlinx.html.*

object SpringBootRequestHandlingTopic : Topic(
    title = "Spring Boot Request Handling",
    slides = listOf(
        RequestHandlingIntroSlide,
        PathVariableSlide,
        RequestParamSlide,
        RequestBodySlide,
        ResponseHandlingSlide,
        ResponseEntitySlide,
        CompleteRequestHandlingExampleSlide
    )
)

object RequestHandlingIntroSlide : Slide(
    header = "Request Handling in Spring Boot",
    summary = {
        +"Spring Boot provides annotations to extract data from different parts of HTTP requests."
    },
    content = {
        p {
            +"HTTP requests can contain data in multiple places:"
        }
        ul {
            li {
                strong { +"Path (URL)" }
                +" - e.g., "
                inlineCode("/api/characters/123")
                +" where "
                inlineCode("123")
                +" is the character ID"
            }
            li {
                strong { +"Query Parameters" }
                +" - e.g., "
                inlineCode("/api/characters?type=WARRIOR&level=5")
            }
            li {
                strong { +"Request Body" }
                +" - JSON or XML data sent with POST/PUT/PATCH requests"
            }
            li {
                strong { +"Headers" }
                +" - metadata like "
                inlineCode("Content-Type")
                +" or "
                inlineCode("Authorization")
            }
        }
        p {
            +"Spring Boot provides annotations to easily extract this data and pass it to your handler methods."
        }
    }
)

object PathVariableSlide : Slide(
    header = "@PathVariable",
    summary = {
        +""
        inlineCode("@PathVariable")
        +" extracts values from the URL path."
    },
    content = {
        p {
            +"Use "
            inlineCode("@PathVariable")
            +" to capture dynamic parts of the URL path."
        }
        p {
            +"Path variables are typically used to identify "
            strong { +"specific resources" }
            +", like a character ID or battle ID."
        }
        kotlinPlayground(
            code = """
                @RestController
                @RequestMapping("/api")
                class CharacterController(
                    private val characterService: CharacterService
                ) {

                    // Single path variable
                    @GetMapping("/characters/{id}")
                    fun getCharacterById(@PathVariable id: Long): CharacterDTO {
                        return characterService.getCharacterById(id)
                    }

                    // Multiple path variables
                    @GetMapping("/characters/{characterId}/battles/{battleId}")
                    fun getCharacterBattle(
                        @PathVariable characterId: Long,
                        @PathVariable battleId: Long
                    ): BattleDTO {
                        return characterService.getCharacterBattle(characterId, battleId)
                    }

                    // Path variable with different name
                    @GetMapping("/characters/{id}/stats")
                    fun getCharacterStats(@PathVariable("id") characterId: Long): StatsDTO {
                        return characterService.getCharacterStats(characterId)
                    }

                    // Path variable with enum
                    @GetMapping("/characters/type/{type}")
                    fun getCharactersByType(@PathVariable type: CharacterType): List<CharacterDTO> {
                        return characterService.getCharactersByType(type)
                    }
                }
            """.trimIndent(),
            executable = false
        )
        p {
            +"Spring Boot automatically converts path variables to the target type (Int, Long, String, Enum, etc.)."
        }
    }
)

object RequestParamSlide : Slide(
    header = "@RequestParam",
    summary = {
        +""
        inlineCode("@RequestParam")
        +" extracts query parameters from the URL."
    },
    content = {
        p {
            +"Use "
            inlineCode("@RequestParam")
            +" to capture query parameters from the URL."
        }
        p {
            +"Query parameters are typically used for "
            strong { +"filtering, sorting, pagination" }
            +", or passing optional data."
        }
        kotlinPlayground(
            code = """
                @RestController
                @RequestMapping("/api/characters")
                class CharacterController(
                    private val characterService: CharacterService
                ) {

                    // Simple query parameter
                    // GET /api/characters?name=Aragorn
                    @GetMapping
                    fun searchCharacters(@RequestParam name: String): List<CharacterDTO> {
                        return characterService.searchByName(name)
                    }

                    // Optional query parameter with default value
                    // GET /api/characters?page=2&size=20 or just /api/characters
                    @GetMapping
                    fun getAllCharacters(
                        @RequestParam(defaultValue = "0") page: Int,
                        @RequestParam(defaultValue = "10") size: Int
                    ): List<CharacterDTO> {
                        return characterService.getAllCharacters(page, size)
                    }

                    // Multiple query parameters with different types
                    // GET /api/characters?type=WARRIOR&minLevel=5&maxLevel=10&includeInactive=true
                    @GetMapping
                    fun filterCharacters(
                        @RequestParam(required = false) type: CharacterType?,
                        @RequestParam(required = false) minLevel: Int?,
                        @RequestParam(required = false) maxLevel: Int?,
                        @RequestParam(defaultValue = "false") includeInactive: Boolean
                    ): List<CharacterDTO> {
                        return characterService.filterCharacters(type, minLevel, maxLevel, includeInactive)
                    }

                    // Query parameter with custom name
                    @GetMapping("/search")
                    fun search(@RequestParam("q") query: String): List<CharacterDTO> {
                        return characterService.search(query)
                    }
                }
            """.trimIndent(),
            executable = false
        )
    }
)

object RequestBodySlide : Slide(
    header = "@RequestBody",
    summary = {
        +""
        inlineCode("@RequestBody")
        +" deserializes the request body into a Kotlin object."
    },
    content = {
        p {
            +"Use "
            inlineCode("@RequestBody")
            +" to receive and automatically deserialize JSON (or XML) data from the request body."
        }
        p {
            +"Request bodies are used with "
            strong { +"POST, PUT, and PATCH" }
            +" requests to send data to create or update resources."
        }
        kotlinPlayground(
            code = """
                data class CreateCharacterRequest(
                    val name: String,
                    val type: CharacterType,
                    val level: Int,
                    val health: Int,
                    val attack: Int,
                    val defense: Int
                )

                data class UpdateCharacterRequest(
                    val name: String?,
                    val level: Int?,
                    val health: Int?
                )

                data class InitiateBattleRequest(
                    val character1Id: Long,
                    val character2Id: Long,
                    val battleType: BattleType
                )

                @RestController
                @RequestMapping("/api")
                class GameController(
                    private val characterService: CharacterService,
                    private val battleService: BattleService
                ) {

                    // POST request with request body
                    // Request: POST /api/characters
                    // Body: {"name": "Aragorn", "type": "WARRIOR", "level": 10, ...}
                    @PostMapping("/characters")
                    @ResponseStatus(HttpStatus.CREATED)
                    fun createCharacter(@RequestBody request: CreateCharacterRequest): CharacterDTO {
                        return characterService.createCharacter(request)
                    }

                    // PUT request with path variable and request body
                    // Request: PUT /api/characters/123
                    // Body: {"name": "Aragorn II", "level": 11, "health": 150}
                    @PutMapping("/characters/{id}")
                    fun updateCharacter(
                        @PathVariable id: Long,
                        @RequestBody request: UpdateCharacterRequest
                    ): CharacterDTO {
                        return characterService.updateCharacter(id, request)
                    }

                    // POST request with request body
                    // Request: POST /api/battles
                    // Body: {"character1Id": 1, "character2Id": 2, "battleType": "DUEL"}
                    @PostMapping("/battles")
                    @ResponseStatus(HttpStatus.CREATED)
                    fun initiateBattle(@RequestBody request: InitiateBattleRequest): BattleDTO {
                        return battleService.initiateBattle(request)
                    }
                }
            """.trimIndent(),
            executable = false
        )
        p {
            +"Spring Boot automatically deserializes the JSON request body into the specified Kotlin data class using Jackson."
        }
    }
)

object ResponseHandlingSlide : Slide(
    header = "Response Handling",
    summary = {
        +"Controllers can return different types of responses with appropriate HTTP status codes."
    },
    content = {
        p {
            +"Spring Boot provides several ways to handle responses:"
        }
        ul {
            li {
                strong { +"Direct return" }
                +" - Return data directly; Spring automatically serializes to JSON with 200 OK"
            }
            li {
                strong { inlineCode("@ResponseStatus") }
                +" - Specify custom HTTP status code"
            }
            li {
                strong { inlineCode("ResponseEntity<T>") }
                +" - Full control over status, headers, and body"
            }
        }
        kotlinPlayground(
            code = """
                @RestController
                @RequestMapping("/api/characters")
                class CharacterController(
                    private val characterService: CharacterService
                ) {

                    // Direct return - automatic 200 OK
                    @GetMapping("/{id}")
                    fun getCharacter(@PathVariable id: Long): CharacterDTO {
                        return characterService.getCharacterById(id)
                    }

                    // Custom status code with @ResponseStatus
                    @PostMapping
                    @ResponseStatus(HttpStatus.CREATED)  // Returns 201 Created
                    fun createCharacter(@RequestBody request: CreateCharacterRequest): CharacterDTO {
                        return characterService.createCharacter(request)
                    }

                    // No content response for DELETE
                    @DeleteMapping("/{id}")
                    @ResponseStatus(HttpStatus.NO_CONTENT)  // Returns 204 No Content
                    fun deleteCharacter(@PathVariable id: Long) {
                        characterService.deleteCharacter(id)
                    }
                }
            """.trimIndent(),
            executable = false
        )
    }
)

object ResponseEntitySlide : Slide(
    header = "ResponseEntity<T>",
    summary = {
        +""
        inlineCode("ResponseEntity<T>")
        +" provides full control over the HTTP response."
    },
    content = {
        p {
            +"Use "
            inlineCode("ResponseEntity<T>")
            +" when you need:"
        }
        ul {
            li { +"Dynamic status codes based on business logic" }
            li { +"Custom response headers" }
            li { +"Conditional responses (e.g., return different status for different scenarios)" }
        }
        kotlinPlayground(
            code = """
                @RestController
                @RequestMapping("/api/characters")
                class CharacterController(
                    private val characterService: CharacterService
                ) {

                    // ResponseEntity with dynamic status
                    @GetMapping("/{id}")
                    fun getCharacter(@PathVariable id: Long): ResponseEntity<CharacterDTO> {
                        val character = characterService.findCharacterById(id)
                        return if (character != null) {
                            ResponseEntity.ok(character)  // 200 OK
                        } else {
                            ResponseEntity.notFound().build()  // 404 Not Found
                        }
                    }

                    // ResponseEntity with custom headers
                    @PostMapping
                    fun createCharacter(@RequestBody request: CreateCharacterRequest): ResponseEntity<CharacterDTO> {
                        val character = characterService.createCharacter(request)
                        return ResponseEntity
                            .status(HttpStatus.CREATED)  // 201 Created
                            .header("Location", "/api/characters/${DOLLAR}{character.id}")
                            .body(character)
                    }

                    // ResponseEntity with conditional logic
                    @PutMapping("/{id}")
                    fun updateCharacter(
                        @PathVariable id: Long,
                        @RequestBody request: UpdateCharacterRequest
                    ): ResponseEntity<CharacterDTO> {
                        return try {
                            val updated = characterService.updateCharacter(id, request)
                            ResponseEntity.ok(updated)  // 200 OK
                        } catch (e: CharacterNotFoundException) {
                            ResponseEntity.notFound().build()  // 404 Not Found
                        } catch (e: InvalidRequestException) {
                            ResponseEntity.badRequest().build()  // 400 Bad Request
                        }
                    }

                    // No content response
                    @DeleteMapping("/{id}")
                    fun deleteCharacter(@PathVariable id: Long): ResponseEntity<Unit> {
                        characterService.deleteCharacter(id)
                        return ResponseEntity.noContent().build()  // 204 No Content
                    }
                }
            """.trimIndent(),
            executable = false
        )
    }
)

object CompleteRequestHandlingExampleSlide : Slide(
    header = "Complete Request Handling Example",
    summary = {
        +"A complete example demonstrating all request handling techniques."
    },
    content = {
        kotlinPlayground(
            code = """
                @RestController
                @RequestMapping("/api/battles")
                class BattleController(
                    private val battleService: BattleService
                ) {

                    // GET with query parameters
                    // GET /api/battles?status=COMPLETED&limit=10
                    @GetMapping
                    fun getBattles(
                        @RequestParam(required = false) status: BattleStatus?,
                        @RequestParam(defaultValue = "10") limit: Int
                    ): ResponseEntity<List<BattleDTO>> {
                        val battles = battleService.getBattles(status, limit)
                        return ResponseEntity.ok(battles)
                    }

                    // GET with path variable
                    // GET /api/battles/123
                    @GetMapping("/{id}")
                    fun getBattleById(@PathVariable id: Long): ResponseEntity<BattleDTO> {
                        val battle = battleService.findBattleById(id)
                        return if (battle != null) {
                            ResponseEntity.ok(battle)
                        } else {
                            ResponseEntity.notFound().build()
                        }
                    }

                    // POST with request body
                    // POST /api/battles
                    // Body: {"character1Id": 1, "character2Id": 2, "battleType": "DUEL"}
                    @PostMapping
                    fun initiateBattle(@RequestBody request: InitiateBattleRequest): ResponseEntity<BattleDTO> {
                        val battle = battleService.initiateBattle(request)
                        return ResponseEntity
                            .status(HttpStatus.CREATED)
                            .header("Location", "/api/battles/${DOLLAR}{battle.id}")
                            .body(battle)
                    }

                    // GET with multiple path variables
                    // GET /api/battles/123/rounds/5
                    @GetMapping("/{battleId}/rounds/{roundNumber}")
                    fun getBattleRound(
                        @PathVariable battleId: Long,
                        @PathVariable roundNumber: Int
                    ): ResponseEntity<BattleRoundDTO> {
                        val round = battleService.getBattleRound(battleId, roundNumber)
                        return ResponseEntity.ok(round)
                    }

                    // GET with path variable and query parameters
                    // GET /api/battles/character/123?includeOngoing=true
                    @GetMapping("/character/{characterId}")
                    fun getCharacterBattles(
                        @PathVariable characterId: Long,
                        @RequestParam(defaultValue = "false") includeOngoing: Boolean
                    ): ResponseEntity<List<BattleDTO>> {
                        val battles = battleService.getCharacterBattles(characterId, includeOngoing)
                        return ResponseEntity.ok(battles)
                    }
                }
            """.trimIndent(),
            executable = false
        )
    }
)
