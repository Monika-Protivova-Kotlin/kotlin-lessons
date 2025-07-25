package com.motycka.edu.content.topics.oop

import kotlinx.html.*
import com.motycka.edu.model.ExerciseTopic
import com.motycka.edu.model.Slide
import com.motycka.edu.model.kotlinPlayground
import com.motycka.edu.model.inlineCode

object InheritanceExercisesTopic : ExerciseTopic(
    title = "Inheritance Exercises",
    subtitle = "Hands-on Practice",
    slides = listOf(
        InheritanceExercise1Slide,
        InheritanceExercise2Slide
    )
)

object InheritanceExercise1Slide : Slide(
    header = "Exercise: Inheritance #1",
    content = {
        div("content exercise-formatting") {
            p {
                +"Create an open class "
                strong { +"Character" }
                +" with the following properties:"
            }
            kotlinPlayground(
                code = """
                    val name: String
                    var health: Int
                    val attackPower: Int
                """,
                executable = false,
                showLines = false
            )
            p {
                +"Create an open method "
                inlineCode("receiveAttack(attackPower: Int)")
                +" that will receive an attack and reduce the health of the character."
            }
            ul {
                li { +"If the character has "; strong { +"health <= 0" }; +", print "; inlineCode("\"\$name has been defeated\"") }
                li { +"Else, print "; inlineCode("\"\$name has \$health health remaining\"") }
            }
            p {
                +"Create an open method "
                inlineCode("attack(target: Character)")
                +" that will attack the target character."
            }
            ul {
                li { +"If the character has "; strong { +"health <= 0" }; +", print "; inlineCode("\"\$name is dead and cannot attack\"") }
                li { +"Else, print "; inlineCode("\"\$name attacks \${target.name}\""); +" and call "; inlineCode("target.receiveAttack(attackPower)") }
            }
        }
    }
)

object InheritanceExercise2Slide : Slide(
    header = "Exercise: Inheritance #2",
    content = {
        div("content exercise-formatting") {
            p {
                +"Create two other classes "
                strong { +"Warrior" }
                +" and "
                strong { +"Sorcerer" }
                +" that inherit from "
                strong { +"Character" }
                +"."
            }
            p {
                +"You can use the following code to test your implementation:"
            }
            kotlinPlayground(
                code = """
                    fun main() {
                        match(
                            character1 = Warrior(name = "Thorne Ironfist", health = 100, attackPower = 10),
                            character2 = Sorcerer(name = "Eldrin Starfire", health = 50, attackPower = 20)
                        )
                    }

                    internal fun match(character1: Character, character2: Character) {
                        var round = 0
                        while (character1.health > 0 && character2.health > 0 && round < 10) {
                            round++
                            println("\nROUND ${'$'}round:")
                            character1.attack(character2)
                            character2.attack(character1)
                        }
                        when {
                            character1.health <= 0 && character2.health > 0 -> println("\n${'$'}{character2.name} is the victor in round ${'$'}round!")
                            character2.health <= 0 && character1.health > 0 -> println("\n${'$'}{character1.name} is the victor in round ${'$'}round!")
                            else -> println("\nIt's a draw!")
                        }
                    }
                """,
                executable = false
            )
        }
    }
)
