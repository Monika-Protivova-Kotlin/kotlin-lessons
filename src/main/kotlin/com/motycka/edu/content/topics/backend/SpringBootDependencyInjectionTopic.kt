package com.motycka.edu.content.topics.backend

import com.motycka.edu.model.Topic
import com.motycka.edu.model.Slide
import com.motycka.edu.model.kotlinPlayground
import com.motycka.edu.model.twoColumns
import com.motycka.edu.model.highlight
import com.motycka.edu.model.inlineCode
import kotlinx.html.*

object SpringBootDependencyInjectionTopic : Topic(
    title = "Dependency Injection in Spring Boot",
    slides = listOf(
//        SpringDIIntroSlide,
        ManualDIProblemsSlide,
        SpringIoCContainerSlide,
        ConstructorInjectionSlide,
        SpringStereotypeAnnotationsSlide,
//        CompleteDIExampleSlide
    )
)

object SpringDIIntroSlide : Slide(
    header = "Dependency Injection - What do we have so far?",
    summary = {
        +"So far, we have been using the "
        strong { +"Inversion of Control (IoC)" }
        +" principle with "
        strong { +"constructor injection" }
        +"."
    },
    content = {
        p {
            +"In our previous examples, we manually created instances of services and repositories, "
            +"and passed them as constructor parameters:"
        }
        kotlinPlayground(
            code = """
                fun main() {
                    // Manual dependency creation
                    val characterRepository = CharacterRepositoryImpl()
                    val characterService = CharacterService(characterRepository)

                    val battleRepository = BattleRepositoryImpl()
                    val battleService = BattleService(
                        characterRepository = characterRepository,
                        battleRepository = battleRepository
                    )

                    // Pass dependencies to controllers manually
                    val characterController = CharacterController(characterService)
                    val battleController = BattleController(battleService)

                    // Start application...
                }
            """.trimIndent(),
            executable = false
        )
        p {
            +"This approach works for small applications, but becomes unmanageable as the application grows."
        }
    }
)

object ManualDIProblemsSlide : Slide(
    header = "Limitations of Manual Dependency Injection",
    summary = {
        +"While dependency injection using constructor is a good start, it is not very practical for larger applications."
    },
    content = {
        p {
            +"Problems with manual dependency injection:"
        }
        ul {
            li {
                strong { +"Boilerplate Code" }
                +" - Lots of manual wiring in the main function"
            }
            li {
                strong { +"Dependency Order" }
                +" - Must create dependencies in the correct order"
            }
            li {
                strong { +"Complexity Growth" }
                +" - Becomes unmanageable as the application grows"
            }
            li {
                strong { +"Testing Difficulty" }
                +" - Hard to swap implementations for testing"
            }
            li {
                strong { +"Singleton Management" }
                +" - Manual management of object lifecycles"
            }
            li {
                strong { +"No Lifecycle Hooks" }
                +" - Can't easily initialize/cleanup resources"
            }
        }
        p {
            +"For larger applications, we need a dependency injection framework. "
            strong { +"Spring Boot" }
            +" provides a powerful built-in IoC container for this purpose."
        }
    }
)

object SpringIoCContainerSlide : Slide(
    header = "Spring IoC Container",
    summary = {
        +"Spring Boot's IoC container automatically manages object creation and dependency injection."
    },
    content = {
        p {
            +"Spring Boot provides an "
            strong { +"Inversion of Control (IoC) container" }
            +" that:"
        }
        ul {
            li { +"Automatically creates and manages objects (called \"beans\")" }
            li { +"Automatically injects dependencies where needed" }
            li { +"Manages bean lifecycles (creation, initialization, destruction)" }
            li { +"Provides different scopes (singleton, prototype, request, session)" }
            li { +"Enables easy testing with mock dependencies" }
        }
        p {
            +"Spring discovers beans through "
            strong { +"component scanning" }
            +" - it scans packages for classes annotated with stereotype annotations."
        }
        p {
            +"The "
            inlineCode("@SpringBootApplication")
            +" annotation on your main class enables:"
        }
        twoColumns(
            right = {
                ul {
                    li { inlineCode("@Configuration")
                        +" - marks this as a configuration class"
                    }
                    li { inlineCode("@EnableAutoConfiguration")
                        +" - enables Spring Boot's auto-configuration"
                    }
                    li { inlineCode("@ComponentScan")
                        +" - enables component scanning in this package and subpackages"
                    }
                }
            },
            left = {
                kotlinPlayground(
                    code = """
                        @SpringBootApplication
                        class FantasySpaceApplication
        
                        fun main(args: Array<String>) {
                            runApplication<FantasySpaceApplication>(*args)
                        }
                    """,
                    executable = false
                )
            }
        )
    }
)

object ConstructorInjectionSlide : Slide(
    header = "Constructor Injection in Spring Boot",
    summary = {
        +"Spring Boot automatically injects dependencies through constructors - the recommended approach."
    },
    content = {
        p {
            +"Spring Boot supports multiple injection methods:"
        }
        ul {
            li {
                strong { +"Constructor Injection" }
                +" (recommended) - Dependencies injected via constructor"
            }
            li {
                strong { +"Field Injection" }
                +" - Dependencies injected directly into fields with "
                inlineCode("@Autowired")
            }
            li {
                strong { +"Setter Injection" }
                +" - Dependencies injected via setter methods"
            }
        }
        p {
            strong { +"Constructor injection is preferred" }
            +" because:"
        }
        ul {
            li { +"Makes dependencies explicit and required" }
            li { +"Enables immutability (val instead of var)" }
            li { +"Easier to test (can pass mocks without Spring)" }
            li { +"Prevents circular dependencies at compile time" }
        }
//        p { +"In Kotlin, Spring automatically injects constructor parameters - no "
//            inlineCode("@Autowired")
//            +" needed:"
//        }
//        kotlinPlayground(
//            code = """
//                @Repository
//                class CharacterRepositoryImpl : CharacterRepository {
//                    // Repository implementation
//                }
//
//                @Service
//                class CharacterService(
//                    private val characterRepository: CharacterRepository  // Automatically injected!
//                ) {
//                    fun getCharacterById(id: Long): CharacterDTO {
//                        val character = characterRepository.findById(id)
//                        return character.toDTO()
//                    }
//                }
//
//                @RestController
//                @RequestMapping("/api/characters")
//                class CharacterController(
//                    private val characterService: CharacterService  // Automatically injected!
//                ) {
//                    @GetMapping("/{id}")
//                    fun getCharacter(@PathVariable id: Long): CharacterDTO {
//                        return characterService.getCharacterById(id)
//                    }
//                }
//            """.trimIndent(),
//            executable = false
//        )
//        p {
//            +"Spring Boot automatically discovers these classes through component scanning and wires them together!"
//        }
    }
)

object SpringStereotypeAnnotationsSlide : Slide(
    header = "Spring Stereotype Annotations",
    summary = {
        +"Spring uses stereotype annotations to identify and register beans in the IoC container."
    },
    content = {
        twoColumns(
            ratio = 2 to 3,
            left = {
                p {
                    +"These annotations enable "
                    strong { +"component scanning" }
                    +" - Spring automatically discovers and registers these classes as beans."
                }
                p {
                    +"Main stereotype annotations:"
                }
                ul {
                    li {
                        strong { inlineCode("@Component") }
                        +" - Generic stereotype for any Spring-managed component"
                    }
                    li {
                        strong { inlineCode("@Service") }
                        +" - Indicates that a class provides business logic (service layer)"
                    }
                    li {
                        strong { inlineCode("@Repository") }
                        +" - Indicates that a class provides data access (data layer)"
                    }
                    li {
                        strong { inlineCode("@Controller") }
                        +" or "
                        strong { inlineCode("@RestController") }
                        +" - Indicates that a class handles HTTP requests (presentation layer)"
                    }
                    li {
                        strong { inlineCode("@Configuration") }
                        +" - Indicates that a class provides bean definitions"
                    }
                }
            },
            right = {
//                p { strong { +"Controller Layer:" } }
                kotlinPlayground(
                    code = """
                        @RestController
                        @RequestMapping("/api/tasks")
                        class TaskController(
                            private val taskService: TaskService,
                        ) {
                            @GetMapping
                            fun getTasks(): List<TaskResponse> {
                                return taskService.getTasks().map { it.toResponse() }
                            }
                        }
                    """.trimIndent(),
                    executable = false
                )

//                p { strong { +"Service Layer:" } }
                kotlinPlayground(
                    code = """
                        @Service
                        class TaskService(
                            private val taskRepository: TaskRepository
                        ) {
                            fun getTasks(): List<Task> {
                                return taskRepository.findAll().map { it.toDomain() }
                            }
                        }
                    """.trimIndent(),
                    executable = false
                )

//                p { strong { +"Repository Layer:" } }
                kotlinPlayground(
                    code = """
                        @Repository
                        interface TaskRepository : JpaRepository<TaskEntity, Long> {
                            // Additional query methods can be defined here if needed
                        }
                    """.trimIndent(),
                    executable = false
                )
            }
        )
    }
)

object CompleteDIExampleSlide : Slide(
    header = "Complete Dependency Injection Example",
    summary = {
        +"A complete Fantasy.Space example showing Spring Boot's automatic dependency injection."
    },
    content = {
        p {
            +"Spring Boot automatically manages the entire dependency graph:"
        }
        kotlinPlayground(
            code = """
                // 1. Data Layer - Repository
                @Repository
                class CharacterRepositoryImpl : CharacterRepository {
                    override fun findById(id: Long): Character? {
                        // Database query
                    }

                    override fun save(character: Character): Character {
                        // Database save
                    }
                }

                // 2. Business Layer - Service
                @Service
                class CharacterService(
                    private val characterRepository: CharacterRepository  // Injected automatically
                ) {
                    fun getCharacterById(id: Long): CharacterDTO {
                        val character = characterRepository.findById(id)
                            ?: throw CharacterNotFoundException(id)
                        return character.toDTO()
                    }

                    fun createCharacter(request: CreateCharacterRequest): CharacterDTO {
                        val character = Character(
                            name = request.name,
                            type = request.type,
                            level = request.level
                        )
                        return characterRepository.save(character).toDTO()
                    }
                }

                // 3. Presentation Layer - Controller
                @RestController
                @RequestMapping("/api/characters")
                class CharacterController(
                    private val characterService: CharacterService  // Injected automatically
                ) {
                    @GetMapping("/{id}")
                    fun getCharacter(@PathVariable id: Long): CharacterDTO {
                        return characterService.getCharacterById(id)
                    }

                    @PostMapping
                    @ResponseStatus(HttpStatus.CREATED)
                    fun createCharacter(@RequestBody request: CreateCharacterRequest): CharacterDTO {
                        return characterService.createCharacter(request)
                    }
                }

                // 4. Application Entry Point
                @SpringBootApplication
                class FantasySpaceApplication

                fun main(args: Array<String>) {
                    runApplication<FantasySpaceApplication>(*args)
                    // Spring Boot automatically:
                    // - Discovers all @Component, @Service, @Repository, @RestController classes
                    // - Creates instances of these classes
                    // - Injects dependencies via constructors
                    // - Manages their lifecycles
                }
            """.trimIndent(),
            executable = false
        )
        p {
            strong { +"Benefits:" }
        }
        ul {
            li { +"No manual dependency creation" }
            li { +"No need to track dependency order" }
            li { +"Easy to swap implementations (e.g., for testing)" }
            li { +"Clean, maintainable code" }
            li { +"Automatic lifecycle management" }
        }
    }
)
