package com.motycka.edu.content.topics.basics.collections

import kotlinx.html.*
import com.motycka.edu.model.Slide
import com.motycka.edu.model.Topic
import com.motycka.edu.model.kotlinPlayground
import com.motycka.edu.model.highlight
import com.motycka.edu.model.inlineCode

object CollectionMappingExercisesTopic : Topic(
    title = "Collection Mapping Exercises",
    slides = listOf(
        MappingExercise1Slide,
        MappingExercise2Slide,
        MappingExercise3Slide,
        MappingExercise4Slide
    )
)

object MappingExercise1Slide : Slide(
    header = "Exercise #1: Mapping",
    summary = {
        +"Convert shape dimension data to Shape objects using "; inlineCode("map"); +"."
    },
    content = {
        ol {
            li {
                +"Create a "
                inlineCode("ShapeType")
                +" enum class with values: CIRCLE, SQUARE, RECTANGLE, TRIANGLE."
            }
            li {
                +"Create a "; inlineCode("Shape")
                +" data class that has a "
                inlineCode("type: ShapeType")
                +" and a "; inlineCode("dimensions: List<Double>")
                +"."
            }
            li {
                +"Add a method "; inlineCode("area()"); +" to the "; inlineCode("Shape")
                +" class that calculates the area based on the shape type and dimensions (use "
                inlineCode("when")
                +")."
            }
            li {
                +"Given a list of shape descriptions (type as string and dimensions), use "
                inlineCode("map")
                +" to convert them to a list of "; inlineCode("Shape"); +" objects."
            }
        }
//        kotlinPlayground(
//            code = """
//                enum class ShapeType {
//                    CIRCLE,
//                    SQUARE,
//                    RECTANGLE,
//                    TRIANGLE
//                }
//
//                data class Shape(
//                    val type: ShapeType,
//                    val dimensions: List<Double>
//                ) {
//                    fun area(): Double = when (type) {
//                        ShapeType.CIRCLE -> Math.PI * dimensions[0] * dimensions[0]
//                        ShapeType.SQUARE -> dimensions[0] * dimensions[0]
//                        ShapeType.RECTANGLE -> dimensions[0] * dimensions[1]
//                        ShapeType.TRIANGLE -> {
//                            val s = dimensions.sum() / 2
//                            Math.sqrt(s * (s - dimensions[0]) * (s - dimensions[1]) * (s - dimensions[2]))
//                        }
//                    }
//                }
//            """.trimIndent(),
//            executable = false
//        )
        p {
            +"Now, given a list of shape descriptions (type and dimensions):"
        }
        kotlinPlayground(
            code = """
                data class ShapeData(val type: String, val dimensions: List<Double>)

                val shapeDataList = listOf(
                    ShapeData("circle", listOf(5.0)),
                    ShapeData("square", listOf(4.0)),
                    ShapeData("rectangle", listOf(3.0, 6.0)),
                    ShapeData("triangle", listOf(3.0, 4.0, 5.0)),
                    ShapeData("circle", listOf(7.0)),
                    ShapeData("square", listOf(2.0))
                )
            """.trimIndent(),
            executable = false
        )
        p {
            strong { highlight("Task:") }
            +" Use the "; inlineCode("map"); +" function to convert each "; inlineCode("ShapeData")
            +" to a "; inlineCode("Shape"); +" object. You'll need to convert the string type to "
            inlineCode("ShapeType"); +" enum:"
        }
        ul {
            li { +"\"circle\" → ShapeType.CIRCLE" }
            li { +"\"square\" → ShapeType.SQUARE" }
            li { +"\"rectangle\" → ShapeType.RECTANGLE" }
            li { +"\"triangle\" → ShapeType.TRIANGLE" }
        }
        p {
            +"Your result should be a "; inlineCode("List<Shape>"); +"."
        }
        blockQuote {
            em { +"Hint: You can use a when expression inside the map function to determine the ShapeType based on the string." }
        }
    }
)

object MappingExercise2Slide : Slide(
    header = "Exercise #2: Mapping with Index",
    summary = {

        +"Pair shapes using "; inlineCode("mapIndexed"); +" to create shape combinations."
    },
    content = {
        p {
            +"Now that you have a list of "; inlineCode("Shape"); +" objects, let's create shape pairs for comparison!"
        }
        p {
            strong { highlight("Task:") }
            +" Use "; inlineCode("mapIndexed"); +" to pair up shapes in a list. Each even-indexed shape (0, 2, 4...) "
            +"should be paired with the next odd-indexed shape (1, 3, 5...)."
        }
        p {
            +"For example, if you have the shapes from Exercise #1:"
        }
        ul {
            li { +"Index 0 (Circle radius 5.0) pairs with Index 1 (Square side 4.0)" }
            li { +"Index 2 (Rectangle 3.0×6.0) pairs with Index 3 (Triangle 3,4,5)" }
            li { +"Index 4 (Circle radius 7.0) pairs with Index 5 (Square side 2.0)" }
        }
        p {
            +"Create a "; inlineCode("Pair<Shape, Shape>"); +" for each combination."
        }
        kotlinPlayground(
            code = """
                // Your result should look like:
                val shapePairs: List<Pair<Shape, Shape>> = listOf(
                    Shape(ShapeType.CIRCLE, listOf(5.0)) to
                        Shape(ShapeType.SQUARE, listOf(4.0)),
                    Shape(ShapeType.RECTANGLE, listOf(3.0, 6.0)) to
                        Shape(ShapeType.TRIANGLE, listOf(3.0, 4.0, 5.0)),
                    // ... etc
                )
            """.trimIndent(),
            executable = false
        )
        blockQuote {
            em {
                +"Hint: Use mapIndexed and filter for even indices, then create pairs by accessing the element at index + 1. "
                +"Or use chunked(2) to split the list into pairs, then map each chunk to a Pair."
            }
        }
    }
)

object MappingExercise3Slide : Slide(
    header = "Exercise #3: Nested Mapping",
    summary = {
        +"Create multiple comparisons per shape pair with nested data structures."
    },
    content = {
        p {
            +"Let's make things more interesting! Each shape pair should be compared using "
            strong { +"3 different scale factors" }
            +", and we need to track the results."
        }
        p {
            +"First, define a "; inlineCode("ComparisonResult"); +" data class:"
        }
        kotlinPlayground(
            code = """
                data class ComparisonResult(
                    val scaleFactor: Double,
                    val shape1: Shape,
                    val shape2: Shape,
                    val largerShape: Shape  // The one with larger area
                )
            """.trimIndent(),
            executable = false
        )
        p {
            strong { highlight("Task:") }
            +" For each pair of shapes from Exercise #2, create "; strong { +"3 comparison results" }
            +" using scale factors 1.0, 1.5, and 2.0."
        }
        p {
            +"For each comparison:"
        }
        ul {
            li { +"Apply the scale factor to both shapes' dimensions" }
            li { +"Calculate the areas" }
            li { +"Determine which shape has the larger area" }
        }
        p {
            +"Your result should be: "; inlineCode("List<List<ComparisonResult>>")
        }
        blockQuote {
            em {
                +"Hint: Use map on your pairs, and for each pair, create a list of 3 ComparisonResult objects. "
                +"You can use listOf(1.0, 1.5, 2.0).map { } inside your outer map to create the 3 comparisons."
            }
        }
    }
)

object MappingExercise4Slide : Slide(
    header = "Exercise #4: Flattening with flatMap",
    summary = {
        +"Flatten the nested comparison results using "; inlineCode("flatMap"); +"."
    },
    content = {
        p {
            +"From Exercise #3, you have a "; inlineCode("List<List<ComparisonResult>>"); +" structure."
            +" This nested structure isn't very convenient to work with."
        }
        p {
            strong { highlight("Task:") }
            +" Use "; inlineCode("flatMap"); +" to flatten your nested list structure "
            +"from "; inlineCode("List<List<ComparisonResult>>"); +" into a single "
            inlineCode("List<ComparisonResult>"); +"."
        }
        p {
            +"The difference between "; inlineCode("map"); +" and "; inlineCode("flatMap"); +":"
        }
        ul {
            li {
                inlineCode("map")
                +" transforms each element and keeps the structure: "
                inlineCode("List<A>"); +" → "; inlineCode("List<B>")
            }
            li {
                inlineCode("flatMap")
                +" transforms each element to a collection and flattens: "
                inlineCode("List<A>"); +" → "; inlineCode("List<List<B>>"); +" → "
                inlineCode("List<B>")
            }
        }
        p {
            +"After flattening, you should have a single list containing all comparison results from all shape pairs."
        }
        kotlinPlayground(
            code = """
                // Example of flatMap:
                val nestedList = listOf(
                    listOf(1, 2, 3),
                    listOf(4, 5, 6),
                    listOf(7, 8, 9)
                )

                val flattened = nestedList.flatMap { it }
                // Result: [1, 2, 3, 4, 5, 6, 7, 8, 9]
            """.trimIndent(),
            executable = false
        )
        blockQuote {
            em {
                +"Hint: If you have the list from Exercise #3, you can simply call flatMap { it } to flatten one level, "
                +"or use flatten() which is equivalent to flatMap { it }."
            }
        }
    }
)
