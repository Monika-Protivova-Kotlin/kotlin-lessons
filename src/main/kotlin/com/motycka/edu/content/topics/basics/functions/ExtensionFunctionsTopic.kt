package com.motycka.edu.content.topics.basics.functions

import kotlinx.html.*
import com.motycka.edu.model.Topic
import com.motycka.edu.model.Slide
import com.motycka.edu.model.kotlinPlayground
import com.motycka.edu.model.inlineCode
import com.motycka.edu.model.highlight

object ExtensionFunctionsTopic : Topic(
    title = "Extension Functions",
    slides = listOf(
        ExtensionFunctionsIntroSlide,
        ExtensionFunctionsAddingFunctionalitySlide,
        ExtensionFunctionsFluentAPISlide,
        ExtensionFunctionsReadabilitySlide,
        ExtensionFunctionsTransformationSlide,
    )
)

object ExtensionFunctionsIntroSlide : Slide(
    header = "Extension functions",
    summary = {
        +"Extension functions allow adding new methods to existing classes without modifying their source code."
    },
    content = {
        div {
            p {
                +"Extension functions are one of the most powerful and popular features of Kotlin. "
                +"You define an extension function by prefixing the function name with the type you want to extend."
            }
        }
        br()
        div {
            strong { +"Benefits of extension functions include:" }
            ul {
                li { +"Adding new functionality to existing classes which you may not have access to." }
                li { +"Using extension functions to create a more fluent API and DSLs." }
                li { +"Improving readability and maintainability of code by encapsulating and naming logic in extension functions." }
                li { +"Using extension functions to transform objects into other objects." }
            }
        }
    }
)

object ExtensionFunctionsAddingFunctionalitySlide : Slide(
    header = "Extension functions: Examples",
    summary = {
        +"Adding new functionality to existing classes which you may not have access to."
    },
    content = {
        p {
            +"For example, you can add a new method to the "
            inlineCode("String")
            +" class to capitalize the first letter of each word in a sentence."
        }
        kotlinPlayground(
            code = """
                    fun String.capitalizeWords(): String {
                        return this.split(" ").joinToString(" ") { it.capitalize() }
                    }
                """,
            executable = false
        )
        p {
            +"You can then use this extension function on any "
            inlineCode("String")
            +" object."
        }
        kotlinPlayground(
            code = """
                fun String.capitalizeWords(): String {
                        return this.split(" ").joinToString(" ") { it.capitalize() }
                }
                    
                fun main() {
                    val sentence = "hello world from kotlin"
                    println(sentence.capitalizeWords())
                }
                """,
            selectLines = 5..8,
            executable = true
        )
    }
)

object ExtensionFunctionsFluentAPISlide : Slide(
    header = "Extension functions: Examples",
    summary = {
        +"Using extension functions to create a more fluent API and DSLs."
    },
    content = {
        p {
            +"You can use extension functions to create a more fluent API by adding methods to existing classes that allow you to chain method calls together."
        }
//        kotlinPlayground(
//            code = """
//                    data class Person(
//                        val name: String,
//                        val age: Int,
//                        val country: String
//                    )
//                """,
//            executable = false
//        )
//        kotlinPlayground(
//            code = """
//                    fun List<Person>.filterByCountry(country: String): List<Person> {
//                        return this.filter { it.country.lowercase() == country.lowercase() }
//                    }
//
//                    fun List<Person>.sortByName(): List<Person> {
//                        return this.sortedBy { person -> person.name }
//                    }
//                """,
//            executable = false
//        )
//        p {
//            +"You can then use these extension functions to create a more readable and expressive code."
//        }
        kotlinPlayground(
            code = """
                data class Person(
                    val name: String,
                    val age: Int,
                    val country: String
                )
                
                fun List<Person>.filterByCountry(country: String): List<Person> {
                    return this.filter { it.country.lowercase() == country.lowercase() }
                }

                fun List<Person>.sortByName(): List<Person> {
                    return this.sortedBy { person -> person.name }
                }
                
                fun main() {
                    val people = listOf(
                        Person("John", 21, "USA"),
                        Person("Alice", 25, "UK"),
                        Person("May", 30, "Thailand")
                    )
         
                    val filteredPeople = people
                        .filterByCountry("Thailand")
                        .sortByName()
                        
                    println(filteredPeople)
                }
                """,
            selectLines = 7..27,
            executable = true
        )
    }
)

object ExtensionFunctionsReadabilitySlide : Slide(
    header = "Extension functions: Examples",
    summary = {
        +"Improving readability and maintainability of code by encapsulating and naming logic in extension functions."
    },
    content = {
        kotlinPlayground(
            code = """
                    data class Person(
                        val name: String,
                        val age: Int,
                        val country: String
                    )

                    fun Person.canDrinkBeer(): Boolean {
                        val legalAge = when (country) {
                            "USA" -> 21
                            else -> 18
                        }
                        return age >= legalAge
                    }

                    fun main() {
                        val person = Person("John", 21, "USA")
                        println("${'$'}{person.name} can drink beer: ${'$'}{person.canDrinkBeer()}")
                    }
                """,
            executable = true
        )
    }
)

object ExtensionFunctionsTransformationSlide : Slide(
    header = "Extension functions: Examples",
    summary = {
        +"Using extension functions to transform objects into other objects."
    },
    content = {
        kotlinPlayground(
            code = """
                import java.time.LocalDate
                
                data class Person(
                    val name: String,
                    val age: Int,
                    val country: String
                )

                data class Student(
                    val name: String,
                    val country: String,
                    val dateEnrolled: LocalDate
                )

                fun Person.toStudent() = Student(
                    name = name,
                    country = country,
                    dateEnrolled = LocalDate.now()
                )

                fun main() {
                    val person = Person("John", 21, "USA")
                    val student = person.toStudent()
                    println(student)
                }
                """,
            selectLines = 3..25,
            executable = true
        )
    }
)

object ExtensionFunctionsExerciseSlide : Slide(
    header = "Exercise",
    content = {
        div("content") {
            div {
                p {
                    +"Implement the following extension functions ..."
                }
                highlight("A) Int extension")
                p {
                    +"Implement an extension functions "
                    inlineCode("isEven")
                    +" and "
                    inlineCode("isOdd")
                    +" for the "
                    inlineCode("Int")
                    +" class that returns "
                    inlineCode("Boolean")
                    +"."
                }
            }
            br()
            div {
                highlight("B) Array extension")
                p {
                    +"Create an extension function for the "
                    inlineCode("Array<String>")
                    +" class that will return a new array with the same elements repeated twice."
                    br()
                    br()
                    +"For example, if the input array is "
                    inlineCode("[\"a\", \"b\", \"c\"]")
                    +", the output array should be "
                    inlineCode("[\"a\", \"b\", \"c\", \"a\", \"b\", \"c\"]")
                    +"."
                }
            }
        }
    }
)
