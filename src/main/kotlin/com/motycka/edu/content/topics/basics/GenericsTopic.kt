package com.motycka.edu.content.topics.basics

import kotlinx.html.*
import com.motycka.edu.model.Topic
import com.motycka.edu.model.Slide
import com.motycka.edu.model.kotlinPlayground
import com.motycka.edu.model.inlineCode

object GenericsTopic : Topic(
    title = "Generics",
    subtitle = "Type Parameters and Reusability",
    slides = listOf(
        GenericsOverviewSlide,
        GenericClassesSlide,
        GenericClassesExampleSlide,
        GenericFunctionsSlide
    )
)

object GenericsOverviewSlide : Slide(
    header = "Generics",
    summary = {
        +"Generics allow us to create classes, interfaces, and methods that take types as parameters."
    },
    content = {
        p {
            +"They are a way to make our code more reusable by allowing us to use the same code with different types."
        }
        p {
            +"Generics are used to create classes, interfaces, and methods that operate on a type parameter."
        }
        p {
            +"We may have already seen generics in action with the "
            inlineCode("List")
            +" interface. For example, all of these methods use generics:"
        }
        kotlinPlayground(
            code = """
                val list = mutableListOf<String>()

                list.add("Hello")
                list.add("World")

                list.forEach {
                    println(it)
                }
            """.trimIndent(),
            executable = false
        )
    }
)

const val GENERIC_CLASS = """
class MyClass<T>(private val id: T) {

    fun getId(): T {
        return id
    }

}
"""

const val GENERIC_CLASS_ENHANCED = """
class MyClassEnhanced<T1, T2> {

    fun equals(value1: T1, value2: T2 ): Boolean {
        return value1 == value2
    }

} 
"""

const val GENERICS_MAIN = """
$GENERIC_CLASS
$GENERIC_CLASS_ENHANCED

fun main() {
    val myClass1 = MyClass<String>("ABC")
    println(myClass1.getId())

    val myClass2 = MyClass<Int>(123)
    println(myClass2.getId())

    val myClassEnhanced = MyClassEnhanced<Int, Double>()
    println(myClassEnhanced.equals(123, 123.0))
}
"""

object GenericClassesSlide : Slide(
    header = "Generics",
    summary = {
        +"How to define a generic class"
    },
    content = {
        p {
            +"To define a generic class, need to create a type parameter in the class definition."
        }
        kotlinPlayground(
            code = GENERIC_CLASS,
            executable = false
        )
        p {
            +"Classes may have multiple type parameters."
        }
        kotlinPlayground(
            code = GENERIC_CLASS_ENHANCED,
            executable = false
        )
//        p {
//            +"Usage:"
//        }
//        kotlinPlaygroundCode(
//            code = GENERICS_MAIN,
//            executable = false
//        )
    }
)

object GenericClassesExampleSlide : Slide(
    header = "Generics: Full Example",
    content = {
        kotlinPlayground(
            code = GENERICS_MAIN,
            executable = true
        )
    }
)

object GenericFunctionsSlide : Slide(
    header = "Generics",
    summary = {
        +"How to define a generic function"
    },
    content = {
        p {
            +"Similarly, you define generic functions by adding a type parameter to the function definition."
        }
        p {
            +"Functions can also have multiple type parameters and the type parameters can have constraints (types)."
        }
        kotlinPlayground(
            code = """
                fun <T1: Number, T2: Number> myFunction(value1: T1, value2: T2 ): Double {
                    return value1.toDouble() + value2.toDouble()
                }
            """.trimIndent(),
            executable = false
        )
        p {
            +"You can also define generic return types."
        }
        kotlinPlayground(
            code = """
                interface MyInterface<T1, T2, R> {
                    fun myFunction(a: T1, b: T2): R
                }
            """.trimIndent(),
            executable = false
        )
    }
)
