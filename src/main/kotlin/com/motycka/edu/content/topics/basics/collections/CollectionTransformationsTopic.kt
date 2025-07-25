package com.motycka.edu.content.topics.basics.collections

import kotlinx.html.*
import com.motycka.edu.model.Topic
import com.motycka.edu.model.Slide
import com.motycka.edu.model.kotlinPlayground
import com.motycka.edu.model.inlineCode
import com.motycka.edu.model.highlight
import com.motycka.edu.model.twoColumns

object CollectionTransformationsTopic : Topic(
    title = "Collection Transformations",
    slides = listOf(
        CollectionTransformationsIntroSlide,
        MappingFunctionsSlide,
        FilteringFunctionsSlide,
        GroupingAndSortingSlide,
        OtherTransformationFunctionsSlide
    )
)

object CollectionTransformationsIntroSlide : Slide(
    header = "Collection Transformations",
    summary = {
        +"There are many operations that can be performed on collections to transform them in some way which are "
        +"part of the Kotlin SDK."
    },
    content = {
        p {
            +"Some common transformation operations performed on collections include:"
        }
        ul {
            li {
                highlight("map")
                +" using functions like "
                inlineCode("map"); +", "
                inlineCode("flatMap"); +", "
                inlineCode("mapNotNull"); +", "
                inlineCode("mapIndexed"); +", "
                inlineCode("mapIndexedNotNull"); +"."
            }
            li {
                highlight("filter")
                +" using functions like "
                inlineCode("filter"); +", "
                inlineCode("filterNot"); +", "
                inlineCode("filterIndexed"); +", "
                inlineCode("filterNotNull"); +", "
                inlineCode("distinct"); +", "
                inlineCode("distinctBy"); +"."
            }
            li {
                highlight("sort")
                +" using functions like "
                inlineCode("sorted"); +", "
                inlineCode("sortedBy"); +", "
                inlineCode("sortedWith"); +", "
                inlineCode("sortedDescending"); +", "
                inlineCode("sortedByDescending"); +", "
                inlineCode("reversed"); +", "
                inlineCode("shuffled"); +"."
            }
            li {
                highlight("group")
                +" using functions like "
                inlineCode("groupBy"); +", "
                inlineCode("partition"); +", "
                inlineCode("associate"); +", "
                inlineCode("associateBy"); +", "
                inlineCode("associateWith"); +"."
            }
            li {
                highlight("plus, minus")
                +" to add or remove elements from a collection."
            }
            li {
                highlight("other transformation")
                +" functions like "
                inlineCode("reduce"); +", "
                inlineCode("zip"); +", "
                inlineCode("zipWithNext"); +", "
                inlineCode("unzip"); +", "
                inlineCode("flatten"); +", "
                inlineCode("fold"); +"."
            }
        }
        p {
            +"All of these transformations return a new collection with the transformation applied, "
            +"they do not modify the original collection."
        }
    }
)

object MappingFunctionsSlide : Slide(
    header = "Mapping Functions",
    summary = {
        inlineCode("map"); +" is a transformation operation that applies a function to each element in the collection "
        +"and returns a new collection with the results."
    },
    content = {
        p {
            +"The returned collection can be of any type, not necessarily the same as the original collection."
        }
        p {
            +"There are several map functions available in the Kotlin standard library:"
        }
        ul {
            li {
                inlineCode("map")
                +" - applies a function to each element and returns a list of the results."
            }
            li {
                inlineCode("mapNotNull")
                +" - applies a function to each element and returns a list of non-null results."
            }
            li {
                inlineCode("mapIndexed")
                +" - applies a function to each element and its index and returns a list of the results."
            }
            li {
                inlineCode("mapIndexedNotNull")
                +" - applies a function to each element and its index and returns a list of non-null results."
            }
            li {
                inlineCode("flatMap")
                +" - applies a function to each element and returns a list of the results, which are then flattened into a single list."
            }
            li {
                inlineCode("mapTo, mapIndexedTo, mapNotNullTo, etc ... ")
                +" - applies a function to each element and adds the results to the given destination."
            }
        }
        p { +"Examples:" }
        kotlinPlayground(
            code = """
                val numbers = listOf(1, 2, 3, 4, 5)

                val squares = numbers.map { it * it } // [1, 4, 9, 16, 25] 

                val indexed = numbers.mapIndexed { index, value -> "${DOLLAR}index: ${DOLLAR}value" }

                val words = listOf("Hello", "World")
                val letters = words.flatMap { it.toList() } // ['H','e','l','l','o','W','o','r','l','d']
            """,
            executable = false
        )
    }
)

object FilteringFunctionsSlide : Slide(
    header = "Filtering Functions",
    summary = {
        +"Kotlin SDK provides us with a rich set of functions for filtering collections. "
        +"Like map, filter returns a new collection with the elements that satisfy a predicate."
    },
    content = {
        p {
            +"Generally, the returned collection is the same type as the original collection."
        }
        p {
            +"Some of the filter functions available in the Kotlin standard library include:"
        }
        ul {
            li {
                inlineCode("filter")
                +" - filters elements based on a predicate and returns a list of elements that satisfy the predicate."
            }
            li {
                inlineCode("filterNot")
                +" - filters elements based on a predicate and returns a list of elements that do not satisfy the predicate."
            }
            li {
                inlineCode("filterNotNull")
                +" - filters out null elements and returns a list of non-null elements."
            }
            li {
                inlineCode("filterIndexed")
                +" - filters elements based on a predicate with index and returns a list of elements that satisfy the predicate."
            }
            li {
                inlineCode("distinct")
                +" - returns a list containing only unique elements from the original collection."
            }
            li {
                inlineCode("distinctBy")
                +" - returns a list containing only elements that are distinct by the given selector function."
            }
        }
        p { +"Examples:" }
        kotlinPlayground(
            code = """
                val numbers = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

                val evenNumbers = numbers.filter { it % 2 == 0 } // [2, 4, 6, 8, 10]

                val oddNumbers = numbers.filterNot { it % 2 == 0 } // [1, 3, 5, 7, 9]

                val evenIndexed = numbers.filterIndexed { index, _ -> index % 2 == 0 }

                val duplicates = listOf(1, 2, 2, 3, 3, 3)
                val unique = duplicates.distinct() // [1, 2, 3]
            """,
            executable = false
        )
    }
)

object GroupingAndSortingSlide : Slide(
    header = "Grouping and Sorting",
    fontSize = "70%",
    content = {
        twoColumns(
            left = {
                h4 { +"Grouping" }
                p {
                    +"The result of grouping operations is a map where the key is the result of the selector function and the value is a list of elements."
                }
                ul {
                    li { inlineCode("groupBy"); +" - groups elements by the result of the given selector function." }
                    li { inlineCode("partition"); +" - splits the collection into a pair of lists based on a predicate." }
                    li { inlineCode("associate"); +" - creates a map from the elements of the collection." }
                    li { inlineCode("associateBy"); +" - creates a map from the elements of the collection using the provided key selector function." }
                    li { inlineCode("associateWith"); +" - creates a map from the elements of the collection using the provided value selector function." }
                }
            },
            right = {
                h4 { +"Sorting" }
                ul {
                    li { inlineCode("sorted"); +" - sorts elements in natural order." }
                    li { inlineCode("sortedBy"); +" - sorts elements by the result of the given selector function." }
                    li { inlineCode("sortedWith"); +" - sorts elements using the given comparator." }
                    li { inlineCode("sortedDescending"); +" - sorts elements in reverse natural order." }
                    li { inlineCode("sortedByDescending"); +" - sorts elements by the result of the given selector function in reverse order." }
                    li { inlineCode("reversed"); +" - reverses the order of elements in the collection." }
                    li { inlineCode("shuffled"); +" - shuffles the elements in the collection." }
                }
            }
        )
        p { +"Examples:" }
        kotlinPlayground(
            code = """
                val words = listOf("apple", "banana", "cherry", "date", "elderberry")

                val grouped = words.groupBy { it.first() }
                // Result: {a=[apple], b=[banana], c=[cherry], d=[date], e=[elderberry]}

                val (short, long) = words.partition { it.length <= 5 }
                // short: [apple, date], long: [banana, cherry, elderberry]

                val sortedByLength = words.sortedBy { it.length }
                // Result: [date, apple, banana, cherry, elderberry]
            """,
            executable = false
        )
    }
)

object OtherTransformationFunctionsSlide : Slide(
    header = "Other Transformation Functions",
    content = {
        p {
            +"There are even more useful transformation functions available in the Kotlin SDK. Some worth mentioning are:"
        }
        ul {
            li {
                inlineCode("reduce")
                +" - combines elements of a collection into a single value."
            }
            li {
                inlineCode("zip")
                +" - combines two collections into a single collection of pairs."
            }
            li {
                inlineCode("zipWithNext")
                +" - combines each element with the next element in the collection."
            }
            li {
                inlineCode("unzip")
                +" - splits a collection of pairs into two collections."
            }
            li {
                inlineCode("flatten")
                +" - flattens a collection of collections into a single collection."
            }
            li {
                inlineCode("fold")
                +" - combines elements of a collection into a single value starting with an initial value."
            }
        }
        p { +"Examples:" }
        kotlinPlayground(
            code = """
                val numbers = listOf(1, 2, 3, 4, 5)

                val sum = numbers.reduce { acc, n -> acc + n } // 15

                val product = numbers.fold(1) { acc, n -> acc * n } // 120

                val names = listOf("Alice", "Bob", "Charlie")
                val ages = listOf(25, 30, 35)
                val people = names.zip(ages) // [(Alice, 25), (Bob, 30), (Charlie, 35)]

                val nested = listOf(listOf(1, 2), listOf(3, 4), listOf(5, 6))
                val flat = nested.flatten() // [1, 2, 3, 4, 5, 6]

                val consecutive = numbers.zipWithNext() // [(1, 2), (2, 3), (3, 4), (4, 5)]
            """,
            executable = false
        )
    }
)
