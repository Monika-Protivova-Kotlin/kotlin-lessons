package com.motycka.edu.content.topics.basics

import com.motycka.edu.model.Slide
import com.motycka.edu.model.Topic
import com.motycka.edu.model.kotlinPlayground
import com.motycka.edu.model.inlineCode
import kotlinx.html.*

object ReflectionTopic : Topic(
    title = "Reflection",
    slides = listOf(
        ReflectionIntroSlide,
        ReflectionExampleClassSlide,
        ReflectionInspectionSlide,
        ReflectionInstantiationSlide,
        ReflectionUseCasesSlide,
        ReflectionPitfallsSlide
    )
)

object ReflectionIntroSlide : Slide(
    header = "Reflection",
    summary = {
        +"Reflection allows us to inspect and modify runtime behavior of classes, interfaces, fields, and methods at runtime, "
        +"without knowing their name at compile time."
    },
    content = {
        p {
            +"Reflection is part of the Kotlin API, and is defined in the "
            inlineCode("kotlin.reflect")
            +" package."
        }
        p {
            +"Reflection is used by many frameworks, such as Spring, Jackson, Hibernate, and JUnit."
        }
        p {
            strong { +"Reflection allows us to:" }
            ul {
                li { +"Inspect classes" }
                li { +"Inspect constructors" }
                li { +"Inspect methods" }
                li { +"Inspect fields" }
                li { +"Instantiate objects dynamically" }
                li { +"Invoke methods" }
                li { +"Get or set field value" }
                li { +"Change field value" }
            }
        }
    }
)

object ReflectionExampleClassSlide : Slide(
    header = "Reflection",
    content = {
        p { +"Given class ..." }
        kotlinPlayground(
            code = """
                package lesson10

                class Employee(
                    val id: Long,
                    private var firstName: String,
                    private var lastName: String,
                    private var position: String
                ) {
                    fun getEmployee(): String = "${'$'}firstName ${'$'}lastName: ${'$'}position"

                    fun updateEmployee(firstName: String, lastName: String, position: String) {
                        this.firstName = firstName
                        this.lastName = lastName
                        this.position = position
                    }
                }
            """.trimIndent()
        )
    }
)

object ReflectionInspectionSlide : Slide(
    header = "Reflection",
    content = {
        p { +"Inspection of classes, constructors, methods and fields." }
        kotlinPlayground(
            code = """
                package lesson10

                fun main() {
                    val obj = Employee(
                        id = 123,
                        firstName = "Monika",
                        lastName = "Protivova",
                        position = "Kotlin Developer"
                    )
                    val cls = obj.javaClass

                    // Inspect class
                    println("Class: ")
                    println(" Name = ${'$'}{cls.name}")
                    println(" Simple Name = ${'$'}{cls.simpleName}")

                    // Inspect constructors
                    println("Constructors:")
                    cls.constructors.forEach { constructor ->
                        println(" ${'$'}{constructor.name}(${'$'}{constructor.parameters.joinToString { it.type.simpleName }})")
                    }

                    // Inspect methods
                    println("Methods:")
                    cls.methods.forEach { method ->
                        println(" ${'$'}{method.name}(${'$'}{method.parameters.joinToString { it.type.simpleName }}): ${'$'}{method.returnType.simpleName}")
                    }

                    // Inspect fields
                    println("Fields:")
                    cls.declaredFields.forEach { field ->
                        println(" ${'$'}{field.name}: ${'$'}{field.type.simpleName}")
                    }
                }
            """.trimIndent()
        )
    }
)

object ReflectionInstantiationSlide : Slide(
    header = "Reflection",
    content = {
        p { +"Instantiation, dynamic invocation of methods and fields, and changing field values." }
        kotlinPlayground(
            code = """
                package lesson10

                fun main() {
                    try {
                        // get class by fully qualified name
                        val cls = Class.forName("lesson10.Employee")

                        // create instance
                        val instance = cls
                            .getDeclaredConstructor(Long::class.java, String::class.java, String::class.java, String::class.java)
                            .newInstance(123, "Monika", "Protivova", "Developer")

                        // get method
                        val getterMethod = cls.getDeclaredMethod("getEmployee")

                        // invoke method
                        val result = getterMethod.invoke(instance)
                        println("Result: ${'$'}result")

                        // get and modify field
                        val firstNameField = cls.getDeclaredField("firstName")
                        firstNameField.isAccessible = true

                        println("Original firstName: ${'$'}{firstNameField.get(instance)}")
                        firstNameField.set(instance, "John")
                        println("Modified firstName: ${'$'}{firstNameField.get(instance)}")

                    } catch (e: Exception) {
                        println("Error: ${'$'}{e.message}")
                    }
                }
            """.trimIndent()
        )
    }
)

object ReflectionUseCasesSlide : Slide(
    header = "Reflection use cases",
    summary = {
        +"Reflection is often used by frameworks and libraries to provide functionality that would be difficult to achieve otherwise, "
        +"such as runtime configuration, serialization, and deserialization, type analysis, object instantiation, and method invocation."
    },
    content = {
        div {
            h4 { +"Frameworks" }
            p {
                +"Java/Kotlin frameworks use reflection to analyze and manipulate the classes, interfaces, constructors, methods, and fields that they work with. "
                +"For instance, Spring Framework uses it to create classes via IoC, Hibernate uses reflection API to fetch data and populate your entity objects."
            }
        }
        br()
        div {
            h4 { +"Testing" }
            p {
                +"Tools like JUnit and Mockito use the reflection to observe class, field, and method size, and also to call methods while doing the testing."
            }
        }
        br()
        div {
            h4 { +"Serialization & Deserialization" }
            p {
                +"Reflection is often used to convert (text) data, such as JSON, to an equivalent Java objects and vice versa."
            }
        }
    }
)

object ReflectionPitfallsSlide : Slide(
    header = "Reflection pitfalls",
    summary = {
        +"Despite its usefulness, it is not recommended to use reflection where not necessary because of the following reasons."
    },
    content = {
        div {
            style = "font-size: 70%"
            div {
                h4 { +"Performance Overhead" }
                p {
                    +"Reflection operations have performance overhead because it involves type inference, and often disables certain JVM optimizations. "
                    +"Reflective code is therefore generally slower than the normal code, and should be avoided in parts of the code that are called frequently, "
                    +"or in performance-critical applications."
                }
            }
            div {
                h4 { +"Exposure of Internals" }
                p {
                    +"Reflections allows access to restricted parts of the code, such as private methods and fields. "
                    +"By doing this, it breaks the encapsulation and exposes the internals of the class in a way it was not designed to be used."
                }
            }
            div {
                h4 { +"Lack of Compile-Time Safety" }
                p {
                    +"Reflection bypasses the compile-time type checks, and therefore may result in runtime exceptions."
                }
            }
            div {
                h4 { +"Decreased Maintainability of Code" }
                p {
                    +"Reflection makes the code less readable and maintainable, by making it more difficult to understand. "
                    +"It also makes the code more error-prone, because the compiler cannot detect errors in the reflective code."
                }
                p {
                    +"Additionally, it makes a lot of development tools, such as IDEs, less useful, because they cannot analyze the reflective code."
                }
            }
            div {
                h4 { +"Security Concerns" }
                p {
                    +"Reflection can access and manipulate private fields and methods, which can be a security concern."
                }
            }
        }
    },
    fontSize = "70%"
)
