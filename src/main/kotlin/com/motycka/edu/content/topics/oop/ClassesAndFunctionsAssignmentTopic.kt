package com.motycka.edu.content.topics.oop

import com.motycka.edu.model.Slide
import com.motycka.edu.model.Topic
import com.motycka.edu.model.inlineCode
import com.motycka.edu.model.kotlinPlayground
import kotlinx.html.*

object ClassesAndFunctionsAssignmentTopic : Topic(
    title = "Assignment: Fantasy.Space Data Models",
    slides = listOf(
        AssignmentIntroSlide,
        Assignment1Slide,
        Assignment2Slide,
        Assignment3Slide,
        Assignment4Slide
    )
)

object AssignmentIntroSlide : Slide(
    header = "Assignment: Fantasy.Space Data Models",
    summary = {
        +"Build the core data models for the Fantasy.Space game using classes, data classes, enums, and singletons."
    },
    content = {
        p { +"In this assignment, you'll create:" }
        ul {
            li { +"A Character data class to represent game characters" }
            li { +"A CharacterType enum for different character classes" }
            li { +"A GameStats singleton to track game statistics" }
            li { +"A simple battle system combining all concepts" }
        }
        br()
        p { +"These models will form the foundation of your Fantasy.Space game!" }
    }
)

object Assignment1Slide : Slide(
    header = "Assignment 1: Character Data Class",
    summary = {
        +"Create a Character data class with properties and methods."
    },
    content = {
        p {
            +"Create a "
            inlineCode("Character")
            +" data class with:"
        }
        ul {
            li { inlineCode("val name: String") }
            li { inlineCode("var health: Int") }
            li { inlineCode("val attackPower: Int") }
        }
        p { +"Add a method "; inlineCode("isAlive()"); +" that returns true if health > 0" }
        kotlinPlayground(
            code = """
                // TODO: Create Character data class with:
                // - val name: String
                // - var health: Int
                // - val attackPower: Int
                // - fun isAlive(): Boolean

                fun main() {
                    val warrior = Character("Thorne", 100, 15)
                    println("${'$'}{warrior.name} is alive: ${'$'}{warrior.isAlive()}")  // Should print true

                    warrior.health = 0
                    println("${'$'}{warrior.name} is alive: ${'$'}{warrior.isAlive()}")  // Should print false

                    // Test data class features
                    val warrior2 = warrior.copy(name = "Thorne II", health = 100)
                    println(warrior2)  // Should print Character data
                }
            """.trimIndent(),
            executable = true
        )
    }
)

object Assignment2Slide : Slide(
    header = "Assignment 2: CharacterType Enum",
    summary = {
        +"Create a CharacterType enum to represent different character classes."
    },
    content = {
        p {
            +"Create a "
            inlineCode("CharacterType")
            +" enum with:"
        }
        ul {
            li { inlineCode("WARRIOR") }
            li { inlineCode("SORCERER") }
            li { inlineCode("ARCHER") }
        }
        p { +"Add properties for each type:" }
        ul {
            li { inlineCode("val baseHealth: Int") }
            li { inlineCode("val baseAttack: Int") }
        }
        p { +"Warriors: 100 health, 15 attack" }
        p { +"Sorcerers: 50 health, 25 attack" }
        p { +"Archers: 75 health, 20 attack" }
        kotlinPlayground(
            code = """
                // TODO: Create CharacterType enum with baseHealth and baseAttack properties

                fun main() {
                    val type = CharacterType.WARRIOR
                    println("${'$'}type - Health: ${'$'}{type.baseHealth}, Attack: ${'$'}{type.baseAttack}")
                    // Should print "WARRIOR - Health: 100, Attack: 15"

                    println("Sorcerer stats: ${'$'}{CharacterType.SORCERER.baseHealth} HP")
                    // Should print "Sorcerer stats: 50 HP"
                }
            """.trimIndent(),
            executable = true
        )
    }
)

object Assignment3Slide : Slide(
    header = "Assignment 3: GameStats Singleton",
    summary = {
        +"Create a singleton object to track game statistics."
    },
    content = {
        p {
            +"Create a singleton "
            inlineCode("GameStats")
            +" object with:"
        }
        ul {
            li { +"A mutable list to store battle results (winner names)" }
            li { +"Function "; inlineCode("recordVictory(winnerName: String)"); +" to add a victory" }
            li { +"Function "; inlineCode("getTotalBattles(): Int"); +" to return count of battles" }
            li { +"Function "; inlineCode("getMostVictories(): String?"); +" to return the character with most wins (null if no battles)" }
        }
        kotlinPlayground(
            code = """
                // TODO: Create GameStats singleton object

                fun main() {
                    GameStats.recordVictory("Thorne")
                    GameStats.recordVictory("Eldrin")
                    GameStats.recordVictory("Thorne")
                    GameStats.recordVictory("Thorne")

                    println("Total battles: ${'$'}{GameStats.getTotalBattles()}")  // Should print 4
                    println("Most victories: ${'$'}{GameStats.getMostVictories()}")  // Should print "Thorne"
                }
            """.trimIndent(),
            executable = true
        )
    }
)

object Assignment4Slide : Slide(
    header = "Assignment 4: Simple Battle System",
    summary = {
        +"Combine all concepts to create a simple battle system."
    },
    content = {
        p { +"Create a complete battle system:" }
        ul {
            li { +"Create Character data class with "; inlineCode("takeDamage(damage: Int)"); +" method" }
            li { +"Use CharacterType enum to create characters with proper stats" }
            li { +"Create "; inlineCode("battle(char1: Character, char2: Character)"); +" function" }
            li { +"Record victory in GameStats" }
        }
        kotlinPlayground(
            code = """
                // TODO: Complete implementation

                data class Character(val name: String, var health: Int, val attackPower: Int) {
                    fun isAlive() = health > 0

                    fun takeDamage(damage: Int) {
                        // TODO: Reduce health by damage (minimum 0)
                    }
                }

                enum class CharacterType(val baseHealth: Int, val baseAttack: Int) {
                    // TODO: Add WARRIOR, SORCERER, ARCHER with stats
                }

                object GameStats {
                    // TODO: Implement GameStats singleton
                }

                fun battle(char1: Character, char2: Character): String {
                    // TODO: Simple battle: they attack each other once
                    // Return winner's name or "Draw"
                    return ""
                }

                fun main() {
                    val warrior = Character("Thorne", CharacterType.WARRIOR.baseHealth, CharacterType.WARRIOR.baseAttack)
                    val sorcerer = Character("Eldrin", CharacterType.SORCERER.baseHealth, CharacterType.SORCERER.baseAttack)

                    val winner = battle(warrior, sorcerer)
                    println("Winner: ${'$'}winner")

                    GameStats.recordVictory(winner)
                    println("Total battles: ${'$'}{GameStats.getTotalBattles()}")
                }
            """.trimIndent(),
            executable = true
        )
    }
)
