package com.motycka.edu.content.topics.oop

import com.motycka.edu.model.highlight
import kotlinx.html.*
import com.motycka.edu.model.Topic
import com.motycka.edu.model.Slide
import com.motycka.edu.model.kotlinPlayground
import com.motycka.edu.model.twoColumns
import com.motycka.edu.model.highlight

object EncapsulationTopic : Topic(
    title = "Encapsulation",
    subtitle = "OOP Principle #1",
    slides = listOf(
        EncapsulationSlide,
        KotlinGettersSettersSlide
    )
)

object EncapsulationSlide : Slide(
    header = "What is Encapsulation",
    summary = {
        +"Encapsulation is a concept of controlling access to the internal state of an object, protecting it from unauthorized access and ensuring data integrity."
    },
    content = {
        twoColumns(
            right = {
                p {
                    +"In Java/Kotlin, this is typically achieved using "
                    highlight("access modifiers")
                    +" (private, protected, internal) and "
                    highlight("getter")
                    +" / "
                    highlight("setter")
                    +" methods."
                }
                p {
                    +"By using "
                    highlight("getter")
                    +" / "
                    highlight("setter")
                    +" methods, the class can enforce its own data validation rules to ensure it's internal state remains valid and consistent."
                }
            },
            left = {
                kotlinPlayground(
                    code = """
                import java.time.LocalDate

                data class Assignment(
                    val dueDate: LocalDate,
                    val assignee: String,
                ) {
                    private var finalGrade: Int? = null

                    fun getFinalGrade(): Int? {
                        return finalGrade
                    }

                    fun setFinalGrade(finalGrade: Int) {
                        require(finalGrade in 0..100) { "Final grade must be between 0 and 100" }
                        this.finalGrade = finalGrade
                    }
                }

                fun main() {
                    val assignment = Assignment(LocalDate.now(), "John Doe")
                    assignment.setFinalGrade(90)
                    println(assignment.getFinalGrade())
                }
            """.trimIndent(),
                    executable = true,
                )
            },
            ratio = 2 to 1
        )
    }
)

object KotlinGettersSettersSlide : Slide(
    header = "Kotlin Getters and Setters",
    summary = {
        +"Kotlin provides idiomatic syntax for custom getters and setters using the get() and set(value) keywords."
    },
    content = {
        twoColumns(
            right = {
                p {
                    +"Kotlin properties can have custom "
                    highlight("get()")
                    +" and "
                    highlight("set(value)")
                    +" accessors directly on the property."
                }
                p {
                    +"Use the "
                    highlight("field")
                    +" keyword to access the backing field inside custom accessors."
                }
                p {
                    +"Custom "
                    highlight("get()")
                    +" can create "
                    highlight("computed properties")
                    +" that derive their value from other properties."
                }
                p {
                    +"You can make the setter "
                    highlight("private")
                    +" to allow external read access while restricting write access to the class."
                }
            },
            left = {
                kotlinPlayground(
                    code = """
                import java.time.LocalDate

                data class Assignment(
                    val dueDate: LocalDate,
                    val assignee: String,
                ) {
                    var finalGrade: Int? = null
                        set(value) {
                            value?.let {
                                require(it in 0..100) { "Final grade must be between 0 and 100" }
                            }
                            field = value
                        }

                    val isPassing: Boolean
                        get() = finalGrade?.let { it >= 60 } ?: false
                }

                fun main() {
                    val assignment = Assignment(LocalDate.now(), "John Doe")
                    assignment.finalGrade = 90
                    println("Grade: ${DOLLAR}{assignment.finalGrade}")
                    println("Passing: ${DOLLAR}{assignment.isPassing}")

                    assignment.finalGrade = 45
                    println("Grade: ${DOLLAR}{assignment.finalGrade}")
                    println("Passing: ${DOLLAR}{assignment.isPassing}")
                }
            """.trimIndent(),
                    executable = true,
                )
            },
            ratio = 2 to 1
        )
    }
)
