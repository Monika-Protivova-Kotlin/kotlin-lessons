package com.motycka.edu.content.topics.basics.collections

import kotlinx.html.*
import com.motycka.edu.model.Topic
import com.motycka.edu.model.Slide
import com.motycka.edu.model.kotlinPlayground
import com.motycka.edu.model.inlineCode
import com.motycka.edu.model.highlight

object CollectionOperationsTopic : Topic(
    title = "Collection Operations",
    subtitle = "Adding, Removing and Retrieving Elements",
    slides = listOf(
        CollectionOperationsIntroSlide,
        AddingElementsSlide,
        RemovingElementsSlide,
        RetrievingElementsSlide,
        RetrievingCollectionPartsSlide
    )
)

object CollectionOperationsIntroSlide : Slide(
    header = "Operations",
    summary = {
        +"The Kotlin standard library provides a rich set of functions for working with collections."
    },
    content = {
        p {
            +"Collection operations are declared in the standard library in two ways:"
        }
        ol {
            li {
                highlight("Member functions of collection interfaces")
                em { +" defining operations that are essential for the collection type." }
            }
            li {
                highlight("Extension functions")
                em { +" providing additional functionality." }
            }
        }
        p {
            +"This is important to know in case you want to implement you own collection type as you will need "
            +"to implement all functions in the given interface(s)."
        }
        p {
            +"Some of the common operations on collections include:"
        }
        ul {
            li { +"Transformations" }
            li { +"Filtering" }
            li { +"plus and minus operators" }
            li { +"Grouping" }
            li { +"Retrieving collection parts" }
            li { +"Retrieving single elements" }
            li { +"Ordering" }
            li { +"Aggregate operations" }
        }
    }
)

object AddingElementsSlide : Slide(
    header = "Adding Elements",
    content = {
        p {
            +"For immutable collections, you can use the "; inlineCode("plus()"); +" function to create a new collection with the added element."

            kotlinPlayground(
                code = """
                val list = mutableListOf("Java", "Kotlin", "JavaScript", "TypeScript")
                
                list.plus("Python")
            """,
                executable = false
            )
        }
        p {
            +"For mutable collections, you can use the "; inlineCode("add()"); +", "
            inlineCode("addFirst()"); +", "; inlineCode("addLast()")
            +" and "; inlineCode("addAll()"); +"functions to add an elements to the collection."

            kotlinPlayground(
                code = """
                val mutableList = mutableListOf("Java", "Kotlin")
                
                mutableList.add("C#")
                mutableList.addLast("Rust")
                mutableList.addAll(listOf("JavaScript", "TypeScript"))
            """,
                executable = false
            )
        }
        p {
            +"For both mutable and immutable collections, you can use the "; inlineCode("+"); +" operator to create a new collection with the added element."

            kotlinPlayground(
                code = """
                val newList = list + "Python"
            """,
                executable = false
            )
        }
        p {
            +"You can also add elements to mutable collections using the "; inlineCode("+="); +" operator."

            kotlinPlayground(
                code = """
                mutableList += "Python"
            """,
                executable = false
            )
        }
        p {
            +"And finally, you can use the "; inlineCode("addAll()"); +", "
            inlineCode("addFirst()"); +", and "; inlineCode("addLast()"); +" functions to add multiple elements to a mutable collection."
        }
    }
)

object RemovingElementsSlide : Slide(
    header = "Removing Elements",
    content = {
        p {
            +"Removing elements is similar."
        }
        p {
            +"Immutable collections provide the "
            inlineCode("minus()"); +" function and the "
            inlineCode("-"); +" operator to create a new collection with the removed element."

            kotlinPlayground(
                code = """
                val list = mutableListOf("Java", "Kotlin", "JavaScript", "TypeScript", "Python")
                
                val newList = list - "JavaScript"
            """,
                executable = false
            )
        }
        p {
            +"Mutable collections provide the "
            inlineCode("remove()"); +", "
            inlineCode("removeFirst()"); +", "
            inlineCode("removeLast()")
            inlineCode("removeAt()"); +", "
            inlineCode("removeAll()")
            +" and also "; inlineCode("removeIf()"); +" functions to remove an element from the collection."

            kotlinPlayground(
                code = """
                val mutableList = mutableListOf("Java", "Kotlin", "JavaScript", "TypeScript", "Python")
                
                mutableList.remove("JavaScript") // removes element "JavaScript"
                mutableList.removeAt(2) // removes element at index 2
                mutableList.removeAll(listOf("Java", "Kotlin")) // removes elements "Java" and "Kotlin"
                mutableList.removeIf { it.length > 5 } // removes elements with length > 5
            """,
                executable = false
            )
        }
        p {
            +"You can also use the "; inlineCode("-="); +" operator to remove an element from a mutable collection."

            kotlinPlayground(
                code = """
                mutableList -= "JavaScript"
            """,
                executable = false
            )
        }
    }
)

object RetrievingElementsSlide : Slide(
    header = "Retrieving Elements",
    content = {
        p {
            +"Retrieving elements from a collection is straightforward and similar to arrays."
        }
        p {
            +"Here are few examples:"
            kotlinPlayground(
                code = """
                val list = listOf("Java", "Kotlin", "JavaScript", "TypeScript", "Python")

                // get element at index 2
                val element = list[2]

                // get first element
                val first = list.first()

                // get last element
                val last = list.last()

                // get element at index 2 or return "C++" if index is out of bounds
                val elementAtOrElse = list.getOrElse(2) { "C++" }

                // get element at index 10 or return null if index is out of bounds
                val elementAtOrNull = list.getOrNull(10)

                // you can also use the random() function to get a random element from the collection
                val randomElement = list.random()
            """.trimIndent(),
                executable = false
            )
        }
    }
)

object RetrievingCollectionPartsSlide : Slide(
    header = "Retrieving collection parts",
    content = {
        p {
            +"You are not limited to retrieving single elements from a collection. You can also retrieve parts of a collection, or slices."
        }
        p {
            +"These are some of the functions available in the Kotlin SDK:"
        }
        ul {
            li { inlineCode("slice"); +" - returns a list of elements at the specified indices." }
            li { inlineCode("take"); +" - returns a list of the first n elements." }
            li { inlineCode("takeLast"); +" - returns a list of the last n elements." }
            li { inlineCode("takeWhile"); +" - returns a list of elements that match the predicate." }
            li { inlineCode("drop"); +" - returns a list of elements after the first n elements." }
            li { inlineCode("dropLast"); +" - returns a list of elements before the last n elements." }
            li { inlineCode("dropWhile"); +" - returns a list of elements after the first element that does not match the predicate." }
        }
        p {
            +"Examples of using these functions:"
            kotlinPlayground(
                code = """
                val numbers = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

                val slice = numbers.slice(2..5) // [3, 4, 5, 6]
                val firstThree = numbers.take(3) // [1, 2, 3]
                val lastThree = numbers.takeLast(3) // [8, 9, 10]
                val takeWhileSmall = numbers.takeWhile { it < 5 } // [1, 2, 3, 4]
                val dropFirst = numbers.drop(3) // [4, 5, 6, 7, 8, 9, 10]
                val dropLast = numbers.dropLast(3) // [1, 2, 3, 4, 5, 6, 7]
            """.trimIndent(),
                executable = false
            )
        }
    }
)
