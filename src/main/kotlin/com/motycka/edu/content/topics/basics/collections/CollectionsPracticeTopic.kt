package com.motycka.edu.content.topics.basics.collections

import kotlinx.html.*
import com.motycka.edu.model.Topic
import com.motycka.edu.model.Slide
import com.motycka.edu.model.kotlinPlayground
import com.motycka.edu.model.highlight
import com.motycka.edu.model.inlineCode

object CollectionsPracticeTopic : Topic(
    title = "Collections Practice - Fantasy Game",
    slides = listOf(
        PracticeIntroSlide,
        PracticePart1Slide,
        PracticePart2Slide,
        PracticePart3Slide
    )
)

object PracticeIntroSlide : Slide(
    header = "Practice: Fantasy Game Combat System",
    summary = {
        +"Let's build a complete combat system for a fantasy game using everything you've learned!"
    },
    content = {
        p {
            +"In this practice exercise, you'll create a game combat system that uses:"
        }
        ul {
            li { +"Data classes and interfaces" }
            li { +"Enums for character progression" }
            li { +"Collection operations for simulating battles" }
            li { +"Transformations for calculating results" }
        }
        p {
            +"We'll build this incrementally in 3 parts:"
        }
        ol {
            li { strong { highlight("Match System") }; +" - Create classes to represent character battles" }
            li { strong { highlight("Recovery Mechanic") }; +" - Add healing between battles" }
            li { strong { highlight("Level System") }; +" - Implement character progression with experience points" }
        }
        blockQuote {
            +"This exercise brings together data modeling, OOP principles, and collection operations "
            +"in a practical, real-world scenario similar to what you'd encounter in game development."
        }
    }
)

object PracticePart1Slide : Slide(
    header = "Practice: Part 1 - Match Class",
    summary = {
        +"Create a "; inlineCode("Match"); " class to simulate combat between two characters."
    },
    content = {
        p {
            +"First, let's define a simple "; inlineCode("Character"); " data class:"
        }
        kotlinPlayground(
            code = """
                data class Character(
                    val name: String,
                    var health: Int,
                    val attackPower: Int
                )
            """.trimIndent(),
            executable = false
        )
        br()
        p {
            strong { highlight("Task 1.1: Create the Match Class") }
        }
        p {
            +"Create a "; inlineCode("Match"); " class that:"
        }
        ul {
            li { +"Takes two "; inlineCode("Character"); " objects in the constructor" }
            li { +"Has a "; inlineCode("fight()"); " function that returns the winning "; inlineCode("Character") }
        }
        kotlinPlayground(
            code = """
                class Match(
                    val character1: Character,
                    val character2: Character
                ) {
                    fun fight(): Character {
                        // Implementation here
                    }
                }
            """.trimIndent(),
            executable = false
        )
        br()
        p {
            strong { highlight("Task 1.2: Implement the fight() Function") }
        }
        p {
            +"The fight logic should work as follows:"
        }
        ol {
            li { +"Characters take turns attacking each other" }
            li { +"Each attack reduces the opponent's health by the attacker's "; inlineCode("attackPower") }
            li { +"Character 1 attacks first" }
            li { +"The battle continues until one character's health drops to 0 or below" }
            li { +"Return the character with remaining health as the winner" }
        }
        blockQuote {
            em { +"Hint: Use a while loop that continues while both characters have health > 0" }
        }
        br()
        p {
            strong { highlight("Task 1.3: Simulate a Tournament") }
        }
        p {
            +"Create a list of 6 characters and use collection operations to:"
        }
        ol {
            li { +"Pair them up using "; inlineCode("chunked(2)"); " (creates 3 matches)" }
            li { +"Use "; inlineCode("map"); " to simulate each match and get the winners" }
            li { +"Print the tournament results" }
        }
        kotlinPlayground(
            code = """
                val characters = listOf(
                    Character("Warrior", 100, 20),
                    Character("Mage", 80, 25),
                    Character("Rogue", 90, 22),
                    Character("Paladin", 110, 18),
                    Character("Ranger", 85, 23),
                    Character("Barbarian", 120, 15)
                )

                val winners = characters
                    .chunked(2)
                    .map { (char1, char2) -> Match(char1, char2).fight() }
            """.trimIndent(),
            executable = false
        )
    },
    fontSize = "80%"
)

object PracticePart2Slide : Slide(
    header = "Practice: Part 2 - Recoverable Interface",
    summary = {
        +"Add a recovery mechanic using interfaces."
    },
    content = {
        p {
            strong { highlight("Task 2.1: Create the Recoverable Interface") }
        }
        p {
            +"Define a "; inlineCode("Recoverable"); " interface:"
        }
        kotlinPlayground(
            code = """
                interface Recoverable {
                    fun recover(amount: Int)
                }
            """.trimIndent(),
            executable = false
        )
        br()
        p {
            strong { highlight("Task 2.2: Update Character Class") }
        }
        p {
            +"Make "; inlineCode("Character"); " implement "; inlineCode("Recoverable"); ":"
        }
        kotlinPlayground(
            code = """
                data class Character(
                    val name: String,
                    var health: Int,
                    val attackPower: Int,
                    val maxHealth: Int = health
                ) : Recoverable {
                    override fun recover(amount: Int) {
                        health = minOf(health + amount, maxHealth)
                    }
                }
            """.trimIndent(),
            executable = false
        )
        br()
        p {
            strong { highlight("Task 2.3: Recovery Between Rounds") }
        }
        p {
            +"Modify your tournament simulation to:"
        }
        ol {
            li { +"Store the initial health of each character" }
            li { +"After Round 1 (3 matches), recover all surviving characters by 30 HP" }
            li { +"Pair up the 3 winners for Round 2 (winner of match 1 vs match 2, match 3 gets a bye)" }
            li { +"Simulate Round 2" }
            li { +"Use "; inlineCode("forEach"); " or "; inlineCode("also"); " to apply recovery" }
        }
        kotlinPlayground(
            code = """
                val round1Winners = // ... winners from part 1

                // Recover all characters
                round1Winners.forEach { it.recover(30) }

                // Continue tournament
                val finalists = round1Winners.take(2)
                val champion = Match(finalists[0], finalists[1]).fight()
            """.trimIndent(),
            executable = false
        )
        blockQuote {
            em {
                +"This demonstrates how interfaces allow you to add common behaviors to your classes "
                +"and how collection operations can be used to apply those behaviors to multiple objects."
            }
        }
    },
    fontSize = "80%"
)

object PracticePart3Slide : Slide(
    header = "Practice: Part 3 - Character Level System",
    summary = {
        +"Add a leveling system with experience points and stat bonuses."
    },
    content = {
        p {
            strong { highlight("Task 3.1: Create CharacterLevel Enum") }
        }
        p {
            +"Define a "; inlineCode("CharacterLevel"); " enum with experience requirements:"
        }
        kotlinPlayground(
            code = """
                enum class CharacterLevel(val requiredExp: Int, val healthBonus: Int, val attackBonus: Int) {
                    LEVEL_1(0, 0, 0),
                    LEVEL_2(100, 20, 5),
                    LEVEL_3(250, 40, 10),
                    LEVEL_4(500, 70, 15),
                    LEVEL_5(1000, 100, 25);

                    companion object {
                        fun fromExperience(exp: Int): CharacterLevel {
                            return entries.lastOrNull { exp >= it.requiredExp } ?: LEVEL_1
                        }
                    }
                }
            """.trimIndent(),
            executable = false
        )
        br()
        p {
            strong { highlight("Task 3.2: Update Character Class") }
        }
        p {
            +"Add experience tracking to "; inlineCode("Character"); ":"
        }
        kotlinPlayground(
            code = """
                data class Character(
                    val name: String,
                    val baseHealth: Int,
                    val baseAttackPower: Int,
                    var experience: Int = 0
                ) : Recoverable {
                    val level: CharacterLevel
                        get() = CharacterLevel.fromExperience(experience)

                    val health: Int
                        get() = baseHealth + level.healthBonus

                    val attackPower: Int
                        get() = baseAttackPower + level.attackBonus

                    fun gainExperience(amount: Int) {
                        experience += amount
                    }

                    override fun recover(amount: Int) {
                        // Adjust for new health calculation
                    }
                }
            """.trimIndent(),
            executable = false
        )
        br()
        p {
            strong { highlight("Task 3.3: Experience from Battles") }
        }
        p {
            +"Modify your "; inlineCode("Match.fight()"); " function to:"
        }
        ul {
            li { +"Award 50 XP to the winner" }
            li { +"Award 10 XP to the loser (participation points!)" }
        }
        br()
        p {
            strong { highlight("Task 3.4: Level Analysis") }
        }
        p {
            +"After running a full tournament, use collection operations to:"
        }
        ol {
            li { +"Group characters by level using "; inlineCode("groupBy") }
            li { +"Calculate average experience per level using "; inlineCode("map"); " and "; inlineCode("average()") }
            li { +"Find the highest level character using "; inlineCode("maxByOrNull") }
            li { +"Sort characters by experience using "; inlineCode("sortedByDescending") }
        }
        kotlinPlayground(
            code = """
                val charactersByLevel = allCharacters.groupBy { it.level }
                val highestLevel = allCharacters.maxByOrNull { it.experience }
                val leaderboard = allCharacters.sortedByDescending { it.experience }
            """.trimIndent(),
            executable = false
        )
        blockQuote {
            em {
                +"This final part demonstrates how enums with properties can model complex game mechanics, "
                +"and how collection operations are perfect for analyzing and reporting on game state!"
            }
        }
    },
    fontSize = "75%"
)
