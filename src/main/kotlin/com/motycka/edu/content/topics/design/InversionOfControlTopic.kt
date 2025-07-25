package com.motycka.edu.content.topics.design

import com.motycka.edu.model.Topic
import com.motycka.edu.model.highlight
import com.motycka.edu.model.Slide
import com.motycka.edu.model.kotlinPlayground
import com.motycka.edu.model.twoColumns
import kotlinx.html.*
import com.motycka.edu.model.inlineCode
import com.motycka.edu.model.highlight

object InversionOfControlTopic : Topic(
    title = "Inversion of Control",
    slides = listOf(
        InversionOfControlIntroSlide,
        DependencyInjectionSlide,
        WithoutInjectionExampleSlide,
        ConstructorInjectionSlide,
        SetterInjectionSlide,
        InterfaceInjectionSlide,
        IoCExerciseIntroSlide,
        CharacterMatchingStrategySlide,
        CharacterRepositorySlide,
        IoCExerciseImplementationSlide,
        IoCExerciseResultsSlide
    )
)

object InversionOfControlIntroSlide : Slide(
    header = "Inversion of Control (IoC)",
    summary = {
        +"Inversion of Control is a principle in software engineering by which the control is transferred "
        +"from higher-level components to the lower-level components"
    },
    content = {
        p {
            +"This allows the higher-level and lower-level components to focus on their functionalities, "
            +"promotes better decoupling, more flexibility, and easier maintenance."
        }
    }
)

object DependencyInjectionSlide : Slide(
    header = "Dependency Injection",
    summary = {
        +"Dependency Injection is a design pattern that implements IoC. "
        +"It allows us to inject dependencies into a class, rather than creating them inside the class."
    },
    content = {
        p {
            +"There are three types of dependency injection:"
        }
        ul {
            li { +"Constructor Injection" }
            li { +"Setter Injection" }
            li { +"Interface Injection" }
        }
    }
)

object WithoutInjectionExampleSlide : Slide(
    header = "Without Injection",
    summary = {
        +"This example is not using dependency injection. The Car class is tightly coupled with the ElectricEngine class."
    },
    content = {
        kotlinPlayground(
            code = """
                fun main() {
                    val car = Car()
                    car.start()
                    car.stop()
                }
            """.trimIndent(),
            executable = true
        )
        twoColumns(
            left = {
                kotlinPlayground(
                    code = """
                        interface Engine {
                            fun on()
                            fun off()
                        }
                    """.trimIndent(),
                    executable = false
                )
                kotlinPlayground(
                    code = """
                        class ElectricEngine : Engine {
                            override fun on() {
                                println("Electric engine is on")
                            }

                            override fun off() {
                                println("Electric engine is off")
                            }
                        }
                    """.trimIndent(),
                    executable = false
                )
            },
            right = {
                kotlinPlayground(
                    code = """
                        class Car {

                            // engine dependency is tightly coupled with Car class
                            private val engine = ElectricEngine()

                            fun start() {
                                engine.on()
                            }

                            fun stop() {
                                engine.off()
                            }
                        }
                    """.trimIndent(),
                    executable = false
                )
            }
        )
    }
)

object ConstructorInjectionSlide : Slide(
    header = "Constructor Injection",
    summary = {
        +"This example is using constructor injection. The Engine dependency is injected into the Car class through its constructor."
    },
    content = {
        kotlinPlayground(
            code = """
                fun main() {
                    // First, we need to create an instance of the dependency
                    val engine: Engine = ElectricEngine()
                    // Then we inject the dependency via constructor
                    val car = Car(engine)
                    car.start()
                    car.stop()
                }
            """.trimIndent(),
            executable = true
        )
        twoColumns(
            left = {
                kotlinPlayground(
                    code = """
                        interface Engine {
                            fun on()
                            fun off()
                        }
                    """.trimIndent(),
                    executable = false
                )
                kotlinPlayground(
                    code = """
                        class ElectricEngine : Engine {
                            override fun on() {
                                println("Electric engine is on")
                            }

                            override fun off() {
                                println("Electric engine is off")
                            }
                        }
                    """.trimIndent(),
                    executable = false
                )
            },
            right = {
                kotlinPlayground(
                    code = """
                        // dependency is injected via constructor
                        class Car(private val engine: Engine) {

                            fun start() {
                                engine.on()
                            }

                            fun stop() {
                                engine.off()
                            }

                        }
                    """.trimIndent(),
                    executable = false
                )
            }
        )
    }
)

object SetterInjectionSlide : Slide(
    header = "Setter Injection",
    summary = {
        +"This example is using setter injection. The Engine dependency is injected into the Car class through its setter."
    },
    content = {
        kotlinPlayground(
            code = """
                fun main() {
                    // First, we need to create an instance of the dependency
                    val engine: Engine = ElectricEngine()
                    // Then we create an instance of the class that has the dependency
                    val car = Car()
                    // Then we inject the dependency via setter
                    car.setEngine(engine)
                    car.start()
                    car.stop()
                }
            """.trimIndent(),
            executable = true
        )
        twoColumns(
            left = {
                kotlinPlayground(
                    code = """
                        interface Engine {
                            fun on()
                            fun off()
                        }
                    """.trimIndent(),
                    executable = false
                )
                kotlinPlayground(
                    code = """
                        class ElectricEngine : Engine {
                            override fun on() {
                                println("Electric engine is on")
                            }

                            override fun off() {
                                println("Electric engine is off")
                            }
                        }
                    """.trimIndent(),
                    executable = false
                )
            },
            right = {
                kotlinPlayground(
                    code = """
                        class Car {

                            private lateinit var engine: Engine

                            // dependency is injected via setter
                            fun setEngine(engine: Engine) {
                                this.engine = engine
                            }

                            fun start() {
                                engine.on()
                            }

                            fun stop() {
                                engine.off()
                            }

                        }
                    """.trimIndent(),
                    executable = false
                )
            }
        )
    }
)

object InterfaceInjectionSlide : Slide(
    header = "Interface Injection",
    summary = {
        +"This is just conceptual example of interface injection. "
        +"For it to work you need to have some kind of an injector that will inject the dependency into the class that implements the interface."
    },
    content = {
        p {
            +"Application frameworks usually provide such injector for us."
        }
        p {
            em { +"This is just a conceptual example!" }
        }
        kotlinPlayground(
            code = """
                @Component
                class NotificationApplication(
                    private var service: MessageService
                ) {

                    fun sendNotifications(msg: String, rec: String) {
                        service.sendMessage(msg, rec)
                    }

                }
            """.trimIndent(),
            executable = false
        )
        kotlinPlayground(
            code = """
                interface MessageService {

                    fun sendMessage(msg: String, rec: String)

                }
            """.trimIndent(),
            executable = false
        )
        kotlinPlayground(
            code = """
                @Service
                class EmailService : MessageService {

                    override fun sendMessage(msg: String, rec: String) {
                        // Logic to send email
                        println("Email sent to " + rec + " with Message=" + msg)
                    }
                }
            """.trimIndent(),
            executable = false
        )
    }
)

object IoCExerciseIntroSlide : Slide(
    header = "Exercise: IoC",
    summary = {
        +"You will work with our fantasy game again (the one with warriors and sorcerers). "
        +"This time, I want you to implement the game using "
        highlight("IoC")
        +" principle."
    },
    content = {
        p {
            +"There will be the following components to our game:"
        }
        ul {
            li {
                highlight("CharacterService")
                +" (interface) → Finds a challenger and an opponent using a given strategy."
            }
            li {
                highlight("MatchService")
                +" (interface) → Handles the battle logic."
            }
            li {
                highlight("CharacterRepository")
                +" (provided) → Contains a list of characters."
            }
            li {
                highlight("CharacterMatchingStrategy")
                +" (provided) → Contains the filtering strategy for matching characters."
            }
            li {
                highlight("Game")
                +" → Uses both services to run a match."
            }
        }
        p { +"To get you started, you are given the following ..." }
        br()
        br()
        br()
        br()
        em { +"Next ↓" }
    }
)

object CharacterMatchingStrategySlide : Slide(
    header = "Exercise: IoC - Character matching strategy",
    summary = {
        +"The "
        highlight("CharacterMatchingStrategy")
        +" represents a strategy (filter) for matching characters."
    },
    content = {
        p {
            +"The idea here is that you have an object that you can use to represent a predicate that can then be "
            +"differently implemented in a concrete service implementation."
        }
        kotlinPlayground(
            code = """
                internal data class CharacterMatchingStrategy(
                    val name: String?,
                    val level: CharacterLevel?,
                    val characterClass: String?
                ) {
                    companion object {
                        val ANY = CharacterMatchingStrategy(null, null, null)
                    }
                }
            """.trimIndent(),
            executable = false
        )
        br()
        br()
        br()
        br()
        br()
        br()
        em { +"Next ↓" }
    }
)

object CharacterRepositorySlide : Slide(
    header = "Exercise: IoC - Character repository",
    summary = {
        +"The "
        highlight("CharacterRepository")
        +" is an object representing a database of characters."
    },
    content = {
        p {
            +"In real application, we would have a database, but for this exercise, we will use a simple object."
        }
        kotlinPlayground(
            code = """
                internal object CharacterRepository {

                    private val harryPotterCharacters = listOf(
                        Sorcerer(name = "Harry Potter", health = 100, attackPower = 40, mana = 30, healingPower = 30, level = CharacterLevel.LEVEL_1),
                        Sorcerer(name = "Hermione Granger", health = 90, attackPower = 40, mana = 40, healingPower = 30, level = CharacterLevel.LEVEL_1),
                        Sorcerer(name = "Ron Weasley", health = 120, attackPower = 50, mana = 20, healingPower = 10, level = CharacterLevel.LEVEL_1),
                        // ... more characters
                    )

                    private val starWarsCharacters = listOf(
                        Warrior(name = "Luke Skywalker", health = 110, attackPower = 40, stamina = 20, defensePower = 30, level = CharacterLevel.LEVEL_1),
                        Warrior(name = "Yoda", health = 80, attackPower = 30, stamina = 50, defensePower = 40, level = CharacterLevel.LEVEL_1),
                        // ... more characters
                    )

                    fun getCharacters() = harryPotterCharacters + starWarsCharacters

                }
            """.trimIndent(),
            executable = false
        )
        em { +"Next ↓" }
    }
)

object IoCExerciseImplementationSlide : Slide(
    header = "Exercise: IoC - Implementation Tasks",
    content = {
        ol {
            li {
                highlight("Implement CharacterService interface:")
                kotlinPlayground(
                    code = """
                        internal interface CharacterService {
                            fun findChallenger(strategy: CharacterMatchingStrategy? = null): Character
                            fun findOpponent(challenger: Character, strategy: CharacterMatchingStrategy? = null): Character
                        }
                    """.trimIndent(),
                    executable = false
                )
                ul(classes = "font-size: 70%") {
                    li {
                        +"Create a class that implements "
                        inlineCode("CharacterService")
                        +" (for example "
                        inlineCode("RandomCharacterService")
                        +")."
                    }
                    li {
                        +"Use "
                        inlineCode("findChallenger()")
                        +" to select random characters."
                    }
                    li {
                        +"Ensure that "
                        inlineCode("findOpponent")
                        +" does not return the same character as the challenger."
                    }
                }
            }
            li {
                highlight("Implement MatchService interface:")
                kotlinPlayground(
                    code = """
                        internal interface MatchService {
                            fun match(rounds: Int, matchingStrategy: CharacterMatchingStrategy? = null): MatchResult
                        }
                    """.trimIndent(),
                    executable = false
                )
                ul(classes = "font-size: 70%") {
                    li {
                        +"Create a class that implements "
                        inlineCode("MatchService")
                        +" (for example "
                        inlineCode("RandomMatchService")
                        +")."
                    }
                    li {
                        +"It should take a "
                        inlineCode("CharacterService")
                        +" as a constructor parameter."
                    }
                    li {
                        +"Call "
                        inlineCode("CharacterService")
                        +" to get a challenger and an opponent."
                    }
                    li {
                        +"Simulate a match and return a "
                        inlineCode("MatchResult")
                        +"."
                    }
                }
            }
            li {
                highlight("Bonus Challenge: Implement a factory class")
                ul(classes = "font-size: 70%") {
                    li { +"A factory class is a static class (object) that creates instances of other classes." }
                    li {
                        +"You can create a factory class to create a preset game with a specific "
                        inlineCode("MatchService")
                        +" and "
                        inlineCode("CharacterService")
                        +"."
                    }
                    li {
                        +"The factory class should return a "
                        inlineCode("MatchService")
                        +" instance."
                    }
                }
            }
        }
        em { +"Next ↓" }
    }
)

object IoCExerciseResultsSlide : Slide(
    header = "Exercise: IoC - Expected Results",
    content = {
        p {
            +"After completing this exercise, your program should:"
        }
        ul {
            li { +"✅ Successfully match two characters for battle." }
            li {
                +"✅ Use "
                highlight("constructor injection")
                +" to provide dependencies."
            }
            li {
                +"✅ Follow "
                highlight("IoC principles")
                +", where the "
                inlineCode("MatchService")
                +" does not directly create dependencies."
            }
        }
        p {
            +"The main function can look something like this:"
        }
        kotlinPlayground(
            code = """
                fun main() {
                    val game = Game(
                        matchService = RandomMatchService(
                            characterService = RandomCharacterService()
                        )
                    )
                    game.playMatch(20)
                }
            """.trimIndent(),
            executable = true
        )
        p {
            highlight("Bonus Challenge:")
        }
        p {
            +"Allow users to specify a "
            inlineCode("CharacterMatchingStrategy")
            +" to filter characters based on their level or name."
        }
    }
)
