package com.motycka.edu.content.topics.oop

import com.motycka.edu.model.highlight
import com.motycka.edu.builders.*
import kotlinx.html.*
import com.motycka.edu.model.Topic
import com.motycka.edu.model.Slide
import com.motycka.edu.model.kotlinPlayground
import com.motycka.edu.model.inlineCode
import com.motycka.edu.model.highlight

object SingletonObjectsTopic : Topic(
    title = "Singleton Objects",
    subtitle = "Static classes and constants in Kotlin",
    slides = listOf(
        SingletonObjectsIntroSlide,
        SingletonObjectsFeaturesSlide,
        StaticObjectsUseCaseSlide,
        CompanionObjectsSlide
    )
)

object SingletonObjectsIntroSlide : Slide(
    header = "Singleton Objects",
    summary = {
        +"Singleton object is a class that can have only one instance in memory."
    },
    content = {
        p {
            +"In Java and other languages, we used so called singleton pattern to create a class that can have only one instance."
        }
        p {
            +"Kotlin provides a convenient way to create singleton objects using the "
            inlineCode("object")
            +" keyword."
        }
        kotlinPlayground(
            code = """
                object StringUtils {
                
                    const val DECIMAL_PLACES = 2
                
                    fun formatNumber(number: Double): String {
                        return "%.${'$'}{DECIMAL_PLACES}f".format(number)
                    }
                
                }
                
                fun main() {
                    val formattedNumber = StringUtils.formatNumber(3.14159)
                    println(formattedNumber)
                }
            """,
            executable = true
        )
    }
)

object SingletonObjectsFeaturesSlide : Slide(
    header = "Singleton Objects Key Features",
//    summary = {
//        +"Key Features"
//    },
    textAlign = "center",
    content = {
        h4 { +"True Singleton" }
        p { +"Only one instance of the object is created." }
        h4 { +"Utility Methods" }
        p { +"Commonly used for utility methods and constants." }
        h4 { +"Initialization" }
        p { +"The object is initialized when it is first accessed." }
        h4 { +"No Constructors" }
        p { +"Objects cannot have constructors." }

//        highlightOrderedList(
//            "Singleton" to {
//                +"Only one instance of the object is created."
//            },
//            "Utility Methods" to {
//                +"Commonly used for utility methods and constants."
//            },
//            "Initialization" to {
//                +"The object is initialized when it is first accessed."
//            },
//            "No Constructors" to {
//                +"Objects cannot have constructors."
//            }
//        )
//        ul {
//            li { highlight("Singleton"); +" - Only one instance of the object is created." }
//            li { highlight("Utility Methods"); +" - Commonly used for utility methods and constants." }
//            li { highlight("Initialization"); +" - The object is initialized when it is first accessed." }
//            li { highlight("No Constructors"); +" - Objects cannot have constructors." }
//        }
    }
)

object StaticObjectsUseCaseSlide : Slide(
    header = "Use case for static objects",
    content = {
        h4 { +"Utility or Helper Classes" }
        p {
            +"Sometimes we need some methods to be globally available in the application without needing to create class instance every time. "
            +"Example of such is utility classes with static methods is "
            inlineCode("String.valueOf()")
            +" or "
            inlineCode("Integer.toBinaryString()")
            +"."
        }
        br
        h4 { +"Global Constants and Variables" }
        p {
            +"Static keyword can be used to define class level variables hat are accessible throughout our application."
        }
        br
        h4 { +"Singleton Pattern" }
        p {
            +"Kotlin object is equivalent to Java's singleton pattern, but with the convenience of a simpler, error-proof syntax. "
            +"Singleton class is a design pattern that restricts the instantiation of a class to a single instance."
        }
    }
)

object CompanionObjectsSlide : Slide(
    header = "Companion Objects",
    summary = {
        +"Companion object is an object that is tied to a class, rather than to an instance of a class."
    },
    content = {
        ul {
            li { +"There can be only one companion object per class, and it is defined using the "; inlineCode("companion"); +" keyword." }
            li { +"It may have a name, but it is optional. Otherwise, it is referred to as a default companion object." }
            li { +"It is often used to define static methods and constants." }
            li { +"Other than that, it is just like any other object." }
            li { +"Companion objects are by convention placed at the bottom of the class." }
        }
        kotlinPlayground(
            code = """
                import java.time.LocalDate
                
                class UniversityCourse(
                    val subject: String,
                    val startDate: LocalDate,
                    val endDate: LocalDate
                ) {
                    // regular class methods

                    companion object UniversityCourseFactory { // companion object name is optional
                        const val KOTLIN = "Kotlin"

                        fun kotlinCourse(startDate: String, endDate: String): UniversityCourse {
                            return UniversityCourse(KOTLIN, LocalDate.parse(startDate), LocalDate.parse(endDate))
                        }
                    }
                }
                
                fun main() {
                    val kotlinCourse = UniversityCourse.kotlinCourse("2024-02-03", "2024-02-21")
                    println(UniversityCourse.KOTLIN)
                }
            """,
            selectLines = 3..17,
            executable = true
        )
    }
)
