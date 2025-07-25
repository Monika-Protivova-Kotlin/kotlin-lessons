package com.motycka.edu.content.topics.oop

import kotlinx.html.*
import com.motycka.edu.model.Topic
import com.motycka.edu.model.Slide
import com.motycka.edu.model.kotlinPlayground
import com.motycka.edu.model.inlineCode

object DataModelsTopic : Topic(
    title = "Data Models",
    subtitle = "including Data Classes & Enums",
    slides = listOf(
        DataModelsSlide,
        DataClassesOverviewSlide,
        EnumsOverviewSlide,
        EnumsWithPropertiesSlide,
        EnumsWithWhenSlide
    )
)

object DataModelsSlide : Slide(
    header = "Data Models",
    summary = {
        +"A data model is a conceptual representation of how data is structured and related in your application."
    },
    content = {
        p {
            +"A data model defines the shape and structure of data in terms of classes, properties, and relationships."
        }
        ul {
            li {
                +"In object-oriented programming, data models are typically expressed using classes "
                +"that reflect real-world entities (e.g., "
                inlineCode("User")
                +", "
                inlineCode("Product")
                +")."
            }
            li {
                +"A good data model serves as a bridge between:"
                ul {
                    li { strong { +"business logic" }; +" (how the system behaves)," }
                    li { strong { +"data storage" }; +" (like a database)," }
                    li { +"and the "; strong { +"API" }; +" layer (how other systems communicate with it)." }
                }
            }
        }
    }
)

object DataClassesOverviewSlide : Slide(
    header = "Data Classes",
    summary = {
        +"Kotlin provides a special kind of class called a "
        inlineCode("data class")
        +" to make data modeling more concise and expressive."
    },
    content = {
        p {
            +"For a data class, Kotlin compiler automatically generates convenience methods for copying, "
            +"comparing, and printing objects and more."
        }
        p {
            +"Data classes are marked with the "
            inlineCode("data")
            +" keyword."
        }
        kotlinPlayground(
            code = """
                data class UniversityCourse(
                    val subject: String,
                    val startDate: LocalDate,
                    val endDate: LocalDate = startDate.plusDays(30),
                    val students: MutableList<String> = mutableListOf()
                )
            """.trimIndent(),
            executable = false
        )
        p { +"There are a few rules for data classes:" }
        ul {
            li { +"The primary constructor must have at least one parameter." }
            li { +"Primary constructor must have "; inlineCode("val"); +" or "; inlineCode("var"); +" keyword." }
            li { +"Data classes cannot be abstract, open, sealed, or inner." }
            li { +"Data classes cannot inherit from other classes." }
        }
    }
)

object EnumsOverviewSlide : Slide(
    header = "Enums",
    summary = {
        +"Enum (enumeration) is a special type of class, which contains a fixed set of constants."
    },
    content = {
        ul {
            li { +"Enum is created with the "; inlineCode("enum class"); +" keyword." }
            li { +"Enum constants are static and final implicitly." }
            li { +"By convention, the enum values should be in upper case." }
            li { +"enums can also have properties, methods, and can be initialized with a constructor." }
        }
        kotlinPlayground(
            code = """
                enum class Days {
                    MONDAY,
                    TUESDAY,
                    WEDNESDAY,
                    THURSDAY,
                    FRIDAY,
                    SATURDAY,
                    SUNDAY
                }
            """.trimIndent(),
            executable = false
        )
        kotlinPlayground(
            code = """
                fun main() {
                    val day = Days.MONDAY
                    println(day) // prints "MONDAY"
                }
            """.trimIndent(),
            executable = true
        )
    }
)

object EnumsWithPropertiesSlide : Slide(
    header = "Enums",
    summary = {
        +"Since enum is a class, it may have "
        em { +"fields" }
        +", "
        em { +"constructors" }
        +" and "
        em { +"methods" }
        +"."
    },
    content = {
        kotlinPlayground(
            code = """
                enum class Days(val isWorkDay: Boolean) {
                    MONDAY(true),
                    TUESDAY(true),
                    WEDNESDAY(true),
                    THURSDAY(true),
                    FRIDAY(true),
                    SATURDAY(false),
                    SUNDAY(false);

                    fun isWeekend(): Boolean {
                        return !isWorkDay
                    }

                    fun isWeekday(): Boolean {
                        return isWorkDay
                    }
                }
            """.trimIndent(),
            executable = false
        )
        kotlinPlayground(
            code = """
                fun main() {
                    val day = Days.MONDAY
                    println(day) // prints MONDAY
                    println(day.isWorkDay()) // prints true
                }
            """.trimIndent(),
            executable = true
        )
    }
)

object EnumsWithWhenSlide : Slide(
    header = "Enums",
    summary = {
        +"Enums are particularly useful for evaluating a finite number of states."
    },
    content = {
        p {
            +"Especially in combination with "
            inlineCode("when")
            +" expression."
        }
        p {
            +"Whenever you use "
            inlineCode("when")
            +" with enums, the compiler will check if all possible values are covered."
        }
        kotlinPlayground(
            code = """
                fun getHoursInClass(day: Days): Int {
                    return when (day) {
                        Days.MONDAY,
                        Days.TUESDAY,
                        Days.THURSDAY -> 4
                        Days.WEDNESDAY,
                        Days.FRIDAY -> 3
                        Days.SATURDAY,
                        Days.SUNDAY -> 0
                    }
                }
            """.trimIndent(),
            executable = false
        )
        p {
            +"In case you forget to cover all possible values, the compiler will throw an error. "
            +"You may also use "
            inlineCode("else")
            +" branch to cover all other cases."
        }
        kotlinPlayground(
            code = """
                fun getHoursInClass(day: Days): Int {
                    return when (day) {
                        Days.MONDAY,
                        Days.TUESDAY,
                        Days.THURSDAY -> 4
                        Days.WEDNESDAY,
                        Days.FRIDAY -> 3
                        else -> 0
                    }
                }
            """.trimIndent(),
            executable = false
        )
    }
)
