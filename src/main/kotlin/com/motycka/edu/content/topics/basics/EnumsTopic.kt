package com.motycka.edu.content.topics.basics

import kotlinx.html.*
import com.motycka.edu.model.Topic
import com.motycka.edu.model.Slide
import com.motycka.edu.model.kotlinPlayground
import com.motycka.edu.model.twoColumns
import com.motycka.edu.model.inlineCode

object EnumsTopic : Topic(
    title = "Enums",
    slides = listOf(
        EnumsIntroSlide,
        EnumsWithPropertiesSlide,
        EnumsWithWhenSlide
    )
)

object EnumsIntroSlide : Slide(
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
                
                fun main() {
                    val day = Days.MONDAY
                    println(day) // prints "MONDAY"
                }
            """,
            executable = true
        )
    }
)

object EnumsWithPropertiesSlide : Slide(
    header = "Enums",
    summary = {
        +"Since enum is a class, it may have "; em { +"fields" }; ", "; em { +"constructors" }; " and "; em { +"methods" }; "."
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
                
                fun main() {
                    val day = Days.MONDAY
                    println(day)
                    println(day.isWeekday())
                }
            """,
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
            strong { +"A) " }
            +"Whenever you use "
            inlineCode("when")
            +" with enums, the compiler will check if all possible values are covered."
        }

        p {
            strong { +"B) " }
            +"In case you forget to cover all possible values, the compiler will throw an error. "
            +"You may also use "
            inlineCode("else")
            +" branch to cover all other cases."
        }
        twoColumns(
            left = {
                strong { +"A" }
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
                    """,
                    executable = false
                )
            },
            right = {
                strong { +"B" }
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
                    """,
                    executable = false
                )
            }
        )

    }
)
