package com.motycka.edu.content.topics.basics.essentials

import com.motycka.edu.model.Slide
import com.motycka.edu.model.Topic
import com.motycka.edu.model.inlineCode
import com.motycka.edu.model.kotlinPlayground
import kotlinx.html.*

object KotlinEssentialsAssignmentTopic : Topic(
    preTitle = "Assignment",
    title = "Fantasy.Space Game",
    subtitle = "Introduction to the project and assignments",
    slides = listOf(
        AssignmentIntroSlide,
        Assignment1Slide,
        Assignment2Slide,
        Assignment3Slide,
        Assignment4Slide
    )
)

object AssignmentIntroSlide : Slide(
    header = "Assignment: Fantasy.Space Game",
    summary = {
        +"Throughout this course, you'll build a fantasy character battle game called Fantasy.Space!"
    },
    content = {
        p { +"In this assignment, you'll work with basic character statistics and game mechanics." }
        p { +"The game features characters like:" }
        ul {
            li { strong { +"Warriors" }; +" - High health, moderate attack" }
            li { strong { +"Sorcerers" }; +" - Lower health, high attack power" }
        }
        br()
        p { +"Each character has:" }
        ul {
            li { +"A name" }
            li { +"Health points (HP)" }
            li { +"Attack power" }
        }
        br()
        p { +"Complete these exercises to build the foundation of the game mechanics!" }
    }
)

object Assignment1Slide : Slide(
    header = "Assignment 1: Character Stats Calculator",
    summary = {
        +"Create functions to calculate character statistics using operators."
    },
    content = {
        p {
            +"Write two functions:"
        }
        ul {
            li {
                inlineCode("calculateTotalDamage(baseDamage: Int, multiplier: Double)")
                +" - returns base damage multiplied by multiplier"
            }
            li {
                inlineCode("calculateRemainingHealth(currentHealth: Int, damageTaken: Int)")
                +" - returns remaining health (minimum 0)"
            }
        }
        kotlinPlayground(
            code = """
                fun calculateTotalDamage(baseDamage: Int, multiplier: Double): Int {
                    // TODO: Calculate and return total damage (baseDamage * multiplier as Int)
                    return 0
                }

                fun calculateRemainingHealth(currentHealth: Int, damageTaken: Int): Int {
                    // TODO: Calculate remaining health (current - damage, but not less than 0)
                    return 0
                }

                fun main() {
                    // Warrior attacks
                    val damage = calculateTotalDamage(15, 1.5)
                    println("Total damage: ${'$'}damage")  // Should print 22

                    // Sorcerer takes damage
                    val health = calculateRemainingHealth(50, 30)
                    println("Remaining health: ${'$'}health")  // Should print 20

                    // Fatal blow
                    val health2 = calculateRemainingHealth(10, 30)
                    println("Remaining health: ${'$'}health2")  // Should print 0
                }
            """.trimIndent(),
            executable = true
        )
    }
)

object Assignment2Slide : Slide(
    header = "Assignment 2: Attack Damage Calculator",
    summary = {
        +"Create a function that calculates damage with critical hits and dodges."
    },
    content = {
        p {
            +"Write a function "
            inlineCode("calculateAttackDamage(attackPower: Int, isCritical: Boolean, isDodged: Boolean)")
        }
        ul {
            li { +"If dodged, damage is 0" }
            li { +"If critical hit, damage is attackPower × 2" }
            li { +"Otherwise, damage is attackPower" }
        }
        kotlinPlayground(
            code = """
                fun calculateAttackDamage(attackPower: Int, isCritical: Boolean, isDodged: Boolean): Int {
                    // TODO: Implement the damage calculation logic
                    return 0
                }

                fun main() {
                    println("Normal attack: ${'$'}{calculateAttackDamage(20, false, false)}")  // Should print 20
                    println("Critical hit: ${'$'}{calculateAttackDamage(20, true, false)}")    // Should print 40
                    println("Dodged attack: ${'$'}{calculateAttackDamage(20, false, true)}")   // Should print 0
                    println("Dodged critical: ${'$'}{calculateAttackDamage(20, true, true)}")  // Should print 0
                }
            """.trimIndent(),
            executable = true
        )
    }
)

object Assignment3Slide : Slide(
    header = "Assignment 3: Character Name Validator",
    summary = {
        +"Validate and format character names using string operations."
    },
    content = {
        p {
            +"Write a function "
            inlineCode("formatCharacterName(name: String)")
            +" that:"
        }
        ul {
            li { +"Trims whitespace" }
            li { +"Returns \"Invalid Name\" if the name is empty or shorter than 3 characters" }
            li { +"Capitalizes the first letter" }
            li { +"Returns the formatted name" }
        }
        kotlinPlayground(
            code = """
                fun formatCharacterName(name: String): String {
                    // TODO: Trim, validate, and format the character name
                    return ""
                }

                fun main() {
                    println(formatCharacterName("  thorne  "))     // Should print "Thorne"
                    println(formatCharacterName("eldrin"))         // Should print "Eldrin"
                    println(formatCharacterName("  ab  "))         // Should print "Invalid Name"
                    println(formatCharacterName(""))               // Should print "Invalid Name"
                    println(formatCharacterName("ariadne the brave"))  // Should print "Ariadne the brave"
                }
            """.trimIndent(),
            executable = true
        )
    }
)

object Assignment4Slide : Slide(
    header = "Assignment 4: Health Status Checker",
    summary = {
        +"Create a function that determines a character's health status."
    },
    content = {
        p {
            +"Write a function "
            inlineCode("getHealthStatus(currentHealth: Int, maxHealth: Int)")
            +" that returns:"
        }
        ul {
            li { +"\"Dead\" if health <= 0" }
            li { +"\"Critical\" if health is 1-25% of max" }
            li { +"\"Wounded\" if health is 26-50% of max" }
            li { +"\"Healthy\" if health is above 50% of max" }
        }
        kotlinPlayground(
            code = """
                fun getHealthStatus(currentHealth: Int, maxHealth: Int): String {
                    // TODO: Determine and return the health status
                    // Use when expression or if-else
                    return ""
                }

                fun main() {
                    println(getHealthStatus(100, 100))  // Should print "Healthy"
                    println(getHealthStatus(50, 100))   // Should print "Healthy"
                    println(getHealthStatus(49, 100))   // Should print "Wounded"
                    println(getHealthStatus(25, 100))   // Should print "Critical"
                    println(getHealthStatus(0, 100))    // Should print "Dead"
                    println(getHealthStatus(-10, 100))  // Should print "Dead"
                }
            """.trimIndent(),
            executable = true
        )
    }
)
