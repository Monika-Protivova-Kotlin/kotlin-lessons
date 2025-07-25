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
        EncapsulationSlide
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
