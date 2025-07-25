package com.motycka.edu.content.topics.basics.collections

import com.motycka.edu.builders.highlightOrderedList
import kotlinx.html.*
import com.motycka.edu.model.Topic
import com.motycka.edu.model.Slide
import com.motycka.edu.model.imgByName
import com.motycka.edu.model.kotlinPlayground
import com.motycka.edu.model.twoColumns
import com.motycka.edu.model.inlineCode

object CollectionsTopic : Topic(
    title = "Collections",
    slides = listOf(
        CollectionsIntroSlide,
        CollectionsOverviewSlide,
        CollectionTypesSlide,
        ConstructingCollectionsSlide,
        MutableCollectionsSlide,
        ArraysVsCollectionsSlide,
        CollectionsKotlinVsJavaSlide
    )
)

object CollectionsIntroSlide : Slide(
    header = "Collections",
    summary = {
        +"Collections are similar to arrays, but they are more flexible and have more features at the cost "
        +"of being less efficient in terms of memory and performance."
    },
    content = {
        p {
            +"The main difference between arrays and collections is that collections can grow or shrink in size. "
            +"They generally provide more functionality and are easier to work with than arrays, but also "
            +"are less efficient in terms of memory and performance."
        }
        p {
            +"There are several types of collections in Kotlin, such as "; inlineCode("List"); +", "; inlineCode("Set"); +", "; inlineCode("Map"); +", etc."
        }
        p {
            +"Unlike and array, which is basic data structure, collections are interfaces that define a set of operations that can be performed on a group of objects."
        }
    }
)

object CollectionsOverviewSlide : Slide(
    header = "Collections",
    summary = {
        +"Collection is a group of "
        span("highlight") { +"variable number" }
        +" of objects of the same type (and its subtypes)."
    },
    content = {
        div {
            p {
                +"The Kotlin Standard Library provides implementations for basic collection types: sets, lists, and maps. "
                +"A pair of interfaces represent each collection type:"
            }
        }
        twoColumns(
            left = {
                highlightOrderedList(
                    "read-only interface" to { +"provides operations for accessing collection elements." },
                    "mutable interface" to { +"extends the corresponding read-only interface with write operations: adding, removing, and updating its elements." }
                )
                p {
                    +"See Kotlin "
                    a(href = "https://kotlinlang.org/docs/collections-overview.html#collection-types") { +"documentation" }
                    +" for more details."
                }
            },
            right = {
                img("Collections diagram", imgByName("collections-diagram"))
            }
        )
    }
)

object CollectionTypesSlide : Slide(
    header = "Collections",
    summary = {
        +"There are 3 main types of collections in Kotlin: lists, sets, and maps."
    },
    content = {
        h4 { +"Lists" }
        p {
            +"Lists are "; strong { +"ordered" }; +" collections of elements that "; strong { +"can contain duplicates" }; +" and individual elements can be accessed by their index."
        }
        h4 { +"Sets" }
        p {
            +"Sets are "; strong { +"unordered" }; +" collections of unique elements, meaning order is not guaranteed, and they "; strong { +"don't allow duplicate elements" }; +"."
        }
        p {
            +"You can work with a set just like you would with a list, but there are some differences:"
        }
        ul {
            li { +"You cannot access elements by index, because sets are unordered." }
            li { +"Adding an element that already exists in the set will not add a duplicate." }
            li { +"Removing an element that does not exist in the set will not throw an exception." }
        }
        br; br
        h4 { +"Maps" }
        p {
            +"Maps are collections of "; strong { +"key-value pairs" }; +", where "; strong { +"keys are unique" }; +" and are used to access values. Values can be duplicates."
        }
        p {
            blockQuote {
                +"Kotlin provides standard library functions for working with collections, which we will explore in more detail."
            }
        }
    }
)

object ConstructingCollectionsSlide : Slide(
    header = "Constructing Collections",
    summary = {
        +"There are standard library functions for constructing collections in Kotlin for both read-only and mutable collections."
    },
    content = {
        p {
            +"Collections are constructed using functions "; inlineCode("listOf<Type>()"); +", "
            inlineCode("setOf<Type>()")
            +" or "; inlineCode("mapOf<KeyType, ValueType>()"); +" for read-only collections."
            kotlinPlayground(
                code = """
                val list = listOf<String>()
                val set = setOf<Int>()
                val map = mapOf<String, Int>()
            """.trimIndent(),
                executable = false
            )
        }
        p {
            +"Or by variable type declaration."
            kotlinPlayground(
                code = """
                val list: List<String> = listOf()
            """.trimIndent(),
                executable = false
            )
        }
        p {
            +"If type can be inferred from the elements, you can omit the type declaration."
            kotlinPlayground(
                code = """
                val list = listOf("Java", "Kotlin", "JavaScript", "TypeScript", "Python")

                val set = setOf(2020, 2021, 2022, 2023, 2024, 2025)

                val map = mapOf(
                    "Java" to 1995,
                    "Kotlin" to 2011,
                    "JavaScript" to 1995,
                    "TypeScript" to 2012,
                    "Python" to 1991
                )
            """.trimIndent(),
                executable = false
            )
        }
    }
)

object MutableCollectionsSlide : Slide(
    header = "Constructing Collections",
    summary = {
        +"There are standard library functions for constructing collections in Kotlin for both read-only and mutable collections."
    },
    content = {
        p {
            +"Mutable collections can be created using "; inlineCode("mutableListOf<Type>()"); +", "
            inlineCode("mutableSetOf<Type>()")
            +" and "; inlineCode("mutableMapOf<KeyType, ValueType>()"); +"."
        }
        p {
            +"To construct an empty collection, you can use the "; inlineCode("emptyList<Type>()"); +", "
            inlineCode("emptySet<Type>()")
            +" or "; inlineCode("emptyMap<KeyType, ValueType>()"); +" functions."
        }
        kotlinPlayground(
            code = """
                val emptyList = emptyList<String>()

                val emptySet = emptySet<Int>()

                val emptyMap = emptyMap<String, Int>()
            """.trimIndent(),
            executable = false
        )
        p {
            +"Similarly, empty collections can be created "; inlineCode("emptyMutableList<Type>()"); +", "
            inlineCode("emptyMutableSet<Type>()")
            +" and "; inlineCode("emptyMutableMap<KeyType, ValueType>()"); +" functions."
        }
    }
)

object ArraysVsCollectionsSlide : Slide(
    header = "Arrays vs. Collections",
    summary = {
        +"Both arrays and Collections are used to store data. "
        br
        +"There are however some notable differences that make them suitable for different use cases."
    },
    content = {
        table {
            thead {
                tr {
                    style = "font-weight: bold"
                    td { }
                    td { +"Arrays" }
                    td { +"Collections" }
                }
            }
            tbody {
                tr {
                    td { style = "font-weight: bold"; +"Size" }
                    td { +"Arrays have fixed size. This may lead to memory wastage, but is also inconvenient to work with." }
                    td { +"Collections can grow or shrink dynamically to accommodate the data." }
                }
                tr {
                    td { style = "font-weight: bold"; +"Type Safety" }
                    td { +"Arrays are type-safe" }
                    td { +"Collections are type-safe (through generic typing)" }
                }
                tr {
                    td { style = "font-weight: bold"; +"Performance" }
                    td { +"Arrays can perform better than collections for some operations because of their simpler memory layout, lower overhead, and ability to employ direct indexing." }
                    td { +"Collections have more overhead than arrays, and certain operations may be slower as a result. However, the built-in utilities in collections make them more convenient for complex data manipulation." }
                }
                tr {
                    td { style = "font-weight: bold"; +"Functionality" }
                    td { +"Arrays offer basic functionality such as adding elements, getting elements, and modifying existing elements." }
                    td { +"Collections provide a wide variety of functionalities. They can be sorted, reversed, shuffled. They support operations like addition, inspection, modification, deletion, searching and other." }
                }
                tr {
                    td { style = "font-weight: bold"; +"Use Cases" }
                    td { +"Arrays are best for fixed-size collections where performance is critical." }
                    td { +"Collections are best for dynamic collections with rich functionality and advanced operations." }
                }
            }
        }
    }
)

object CollectionsKotlinVsJavaSlide : Slide(
    header = "Collections: Kotlin vs. Java",
    summary = {
        +"Collections in Kotlin are actually one of the most significant differences between Kotlin and Java, "
        +"because they are implemented differently in Kotlin."
    },
    content = {
        table {
            thead {
                tr {
                    td { style = "font-weight: bold"; }
                    td { style = "font-weight: bold"; +"Java" }
                    td { style = "font-weight: bold"; +"Kotlin" }
                }
            }
            tbody {
                tr {
                    td { style = "font-weight: bold"; +"Null Safety" }
                    td { +"Collections can contain null values unless explicitly handled." }
                    td { +"Collections are null-safe by default. You can explicitly declare nullable collections if needed." }
                }
                tr {
                    td { style = "font-weight: bold"; +"Read-Only vs Mutable" }
                    td { +"Collections are mutable by default. Read-only views can be created using utility methods." }
                    td { +"Distinguishes between read-only (List, Set, Map) and mutable (MutableList, MutableSet, MutableMap) collections." }
                }
                tr {
                    td { style = "font-weight: bold"; +"Higher-Order Functions" }
                    td { +"Introduced lambda expressions and streams in Java 8, but the syntax is more verbose compared to Kotlin." }
                    td { +"Supports higher-order functions and lambda expressions, making it easier to perform operations like filtering, mapping, and reducing." }
                }
                tr {
                    td { style = "font-weight: bold"; +"Default Implementations" }
                    td { +"Requires more boilerplate code for common operations." }
                    td { +"Provides default implementations for many collection operations, making the code more concise." }
                }
            }
        }
        blockQuote {
            +"Note that because Java and Kotlin are fully interoperable, "
            +"you can opt to use Java implementations of collections in Kotlin, if needed."
        }
    }
)
