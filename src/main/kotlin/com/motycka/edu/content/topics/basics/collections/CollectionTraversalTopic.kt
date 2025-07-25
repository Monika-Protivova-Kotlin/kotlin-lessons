package com.motycka.edu.content.topics.basics.collections

import kotlinx.html.*
import com.motycka.edu.model.Topic
import com.motycka.edu.model.Slide
import com.motycka.edu.model.highlight
import com.motycka.edu.model.kotlinPlayground
import com.motycka.edu.model.inlineCode

object CollectionTraversalTopic : Topic(
    title = "Traversing Collections",
    slides = listOf(
        IteratorsSlide,
        CollectionForLoopSlide,
        ForEachFunctionSlide,
        ForEachIndexedFunctionSlide
    )
)

object IteratorsSlide : Slide(
    header = "Iterators",
    summary = {
        +"Kotlin provides standard library functions for traversing collections using iterators."
    },
    content = {
        p {
            +"Iterators can be obtained for collections that implement the "; inlineCode("Iterable"); +" interface by calling the "; inlineCode("iterator()"); +" function."
            br
            +"Once you have an iterator, you can traverse the collection using the "; inlineCode("next()"); +"and "; inlineCode("hasNext()"); +" functions."

            kotlinPlayground(
                code = """
                val list = listOf("Java", "Kotlin", "JavaScript", "TypeScript", "Python")
                val iterator = list.iterator()

                while (iterator.hasNext()) {
                    println(iterator.next())
                }
            """,
                executable = false
            )
        }
        p {
            +"For "; highlight("List"); +", there is also "; inlineCode("ListIterator"); +", which allows traversing the list in reverse order by using the "; inlineCode("previous()"); +" function."
//            kotlinPlayground(
//                code = """
//                while (iterator.hasPrevious()) {
//                    println(iterator.previous())
//                }
//            """,
//                executable = false
//            )
        }
        p {
            +"For mutable collections, you can use the "; inlineCode("MutableIterator"); +", which provides a "; inlineCode("remove()"); +" function to remove elements from the collection."
            kotlinPlayground(
                code = """
                val mutableList = mutableListOf("Java", "Kotlin", "JavaScript", "TypeScript", "Python")
                val iterator = mutableList.iterator()

                while (iterator.hasNext()) {
                    val element = iterator.next()
                    if (element == "JavaScript") {
                        iterator.remove()
                    }
                }
            """,
                executable = false
            )
        }
    }
)

object CollectionForLoopSlide : Slide(
    header = "For Loop",
    summary = {
        +"You can use a "; inlineCode("for"); +" loop to iterate over collections that implement "
        +"the "; inlineCode("Iterable"); +" interface "; br; +"(or its subtypes)."
    },
    content = {
        p {
            +"Iterators are not the most idiomatic way to iterate over collections, "
            +"so Kotlin provides other ways to iterate over collections which implement the "; inlineCode("Iterable"); +" interface."
        }
        p {
            +"One of such ways is to use a "; inlineCode("for"); +" loop."
        }
        kotlinPlayground(
            code = """
                val list = listOf("Java", "Kotlin", "JavaScript", "TypeScript", "Python")

                for (element in list) {
                    println(element)
                }
            """.trimIndent(),
            executable = false
        )
        p {
            +"You can also iterate with index using "; inlineCode("withIndex()"); +" function:"
        }
        kotlinPlayground(
            code = """
                for ((index, element) in list.withIndex()) {
                    println("Element at index ${'$'}index is ${'$'}element")
                }
            """.trimIndent(),
            executable = false
        )
    }
)

object ForEachFunctionSlide : Slide(
    header = "The forEach Function",
    summary = {
        +"Another way to iterate over collections is to use the "; inlineCode("forEach"); +" function."
    },
    content = {
        p {
            +"The "; inlineCode("forEach"); +" function is a higher-order function that takes a lambda as an argument. "
            +"The basic syntax is ..."
        }
        kotlinPlayground(
            code = """
                val list = listOf("Java", "Kotlin", "JavaScript", "TypeScript", "Python")

                list.forEach {
                    println(it)
                }
            """.trimIndent(),
            executable = false
        )
        p {
            +"By default, the lambda passed to the "; inlineCode("forEach"); +" function takes a single argument, "
            +"which can be referenced using the "; inlineCode("it"); +" keyword."
        }
        p {
            +"You can also specify the argument name explicitly (in this case, "; inlineCode("element"); +")."
        }
        kotlinPlayground(
            code = """
                list.forEach { element ->
                    println(element)
                }
            """.trimIndent(),
            executable = false
        )
        p {
            +"There is also a shorthand syntax for the lambda if it takes a single argument."
        }
        kotlinPlayground(
            code = """
                list.forEach(::println)
            """.trimIndent(),
            executable = false
        )
    }
)

object ForEachIndexedFunctionSlide : Slide(
    header = "The forEachIndexed Function",
    summary = {
        +"You can use the "; inlineCode("forEachIndexed"); +" function to iterate over collections with index."
    },
    content = {
        p {
            +"The "; inlineCode("forEachIndexed"); +" function is similar to the "; inlineCode("forEach"); +" function, "
            +"but it also provides the index of the element as the first argument to the lambda. This may be useful in some situations."
        }
        kotlinPlayground(
            code = """
                val list = listOf("Java", "Kotlin", "JavaScript", "TypeScript", "Python")

                list.forEachIndexed { index, element ->
                    println("Element at index ${'$'}index is ${'$'}element")
                }
            """.trimIndent(),
            executable = false
        )
        p {
            +"This function is particularly useful when you need both the position and the value of each element in your collection."
        }
    }
)
