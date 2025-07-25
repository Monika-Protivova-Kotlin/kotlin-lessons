package com.motycka.edu.content.topics.oop

import kotlinx.html.*
import com.motycka.edu.model.ExerciseTopic
import com.motycka.edu.model.Slide
import com.motycka.edu.model.kotlinPlayground
import com.motycka.edu.model.inlineCode

object AbstractionExercisesTopic : ExerciseTopic(
    title = "Abstraction Exercises",
    subtitle = "Hands-on Practice",
    slides = listOf(
        AbstractionExercise1Slide,
        AbstractionExercise2Slide,
        AbstractionExercise3Slide
    )
)

object AbstractionExercise1Slide : Slide(
    header = "Exercise: Abstraction #1",
    content = {
        div("content exercise-formatting") {
            p {
                +"Update the "
                strong { +"Character" }
                +", change the "
                strong { +"attack" }
                +" method to be "
                inlineCode("abstract")
                +" and remove the implementation from the "
                strong { +"Character" }
                +" class. Leave the "
                strong { +"receiveAttack" }
                +" function as it is."
            }
            p {
                +"Change the "
                strong { +"Warrior" }
                +" class, add a new private mutable property "
                inlineCode("private var stamina: Int")
                +"."
            }
            p {
                +"Update the "
                strong { +"attack" }
                +" function in the "
                strong { +"Warrior" }
                +" class:"
            }
            ul {
                li { +"If the warrior has "; strong { +"health <= 0" }; +", print "; inlineCode("\"\$name is dead and cannot attack\"") }
                li { +"If the warrior has "; strong { +"stamina <= 0" }; +", print "; inlineCode("\"\$name is too tired to attack\"") }
                li { +"Else, print "; inlineCode("\"\$name swings a sword at \${target.name}\""); +" and call "; inlineCode("target.receiveAttack(attackPower)") }
                li { +"Decrease the stamina by 1" }
            }
            p {
                +"Extend the Sorcerer class, add a new private mutable property "
                inlineCode("private var mana: Int")
            }
            p {
                +"Update the "
                strong { +"attack" }
                +" function in the "
                strong { +"Sorcerer" }
                +" class:"
            }
            ul {
                li { +"If the sorcerer has "; strong { +"health <= 0" }; +", print "; inlineCode("\"\$name is dead and cannot attack\"") }
                li { +"If the sorcerer has "; strong { +"mana <= 0" }; +", print "; inlineCode("\"\$name is out of mana\"") }
                li { +"Else, print "; inlineCode("\"\$name casts a spell at \${target.name}\""); +" and call "; inlineCode("target.receiveAttack(attackPower)") }
                li { +"Decrease the mana by 1" }
            }
            p {
                +"Update the test code to use the new classes and properties and run the match function."
            }
        }
    }
)

object AbstractionExercise2Slide : Slide(
    header = "Exercise: Abstraction #2",
    content = {
        div("content exercise-formatting") {
            p {
                +"Create new interfaces "
                strong { +"Defender" }
                +" with the following methods and properties:"
            }
            ul {
                li { inlineCode("val name: String") }
                li { inlineCode("var stamina: Int") }
                li { inlineCode("val defensePower: Int") }
                li { inlineCode("fun defend(attackPower: Int): Int") }
            }
            p {
                +"Have the "
                strong { +"Warrior" }
                +" class "
                strong { +"implement" }
                +" the "
                strong { +"Defender" }
                +" interface:"
            }
            ul {
                li { +"Implement the "; strong { +"defend" }; +" method to "; strong { +"reduce the attackPower by the defensePower and decrease the stamina by 1" } }
                li { +"If the warrior has "; strong { +"stamina <= 0" }; +", print "; inlineCode("\"\$name is too tired to defend\""); +" and return the "; strong { +"attackPower" } }
                li { +"If the warrior has "; strong { +"stamina > 0" }; +", print "; inlineCode("\"\$name raises shield and defends against \$defensePower damage\""); +" and return "; strong { +"attackPower - defensePower" } }
                li { +"Override the "; strong { +"receiveAttack" }; +" method to call the defend function and then call the super.receiveAttack function with the result" }
            }
            p { +"Example:" }
            kotlinPlayground(
                code = """
                    override fun receiveAttack(attackPower: Int) {
                        super.receiveAttack(defend(attackPower))
                    }
                """.trimIndent(),
                executable = false
            )
            p {
                +"Update the test code to use the new classes and properties and run the match function."
            }
        }
    }
)

object AbstractionExercise3Slide : Slide(
    header = "Exercise: Abstraction #3",
    content = {
        div("content exercise-formatting") {
            p {
                +"Create new interfaces "
                strong { +"Healer" }
                +" with the following methods and properties:"
            }
            ul {
                li { +"var mana: Int" }
                li { +"val healingPower: Int" }
                li { +"fun heal()" }
            }
            p {
                +"Have the "
                strong { +"Sorcerer" }
                +" class "
                strong { +"implement" }
                +" the "
                strong { +"Healer" }
                +" interface:"
            }
            ul {
                li { +"Implement the "; strong { +"heal" }; +" method to "; strong { +"increase the health by the healingPower and decrease the mana by 1" } }
                li { +"If the sorcerer has "; strong { +"mana <= 0" }; +", print "; inlineCode("\"\$name is out of mana\""); +" and do not heal" }
                li { +"If the sorcerer has "; strong { +"mana > 0" }; +", print "; inlineCode("\"\$name heals self to \$health health\""); +" and increase the health by "; strong { +"healingPower" } }
                li { +"Update the "; strong { +"attack" }; +" function to call "; strong { +"heal" }; +" function before attacking" }
            }
            p {
                +"Update the test code to use the new classes and properties and run the match function."
            }
        }
    }
)
