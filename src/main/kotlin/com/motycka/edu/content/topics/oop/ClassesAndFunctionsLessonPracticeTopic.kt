package com.motycka.edu.content.topics.oop

import com.motycka.edu.model.Slide
import com.motycka.edu.model.Topic
import com.motycka.edu.model.inlineCode
import com.motycka.edu.model.kotlinPlayground
import kotlinx.html.*

object ClassesAndFunctionsLessonPracticeTopic : Topic(
    title = "Practice: Classes and Functions",
    slides = listOf(
        ClassesAndFunctionsPracticeIntroSlide,
        ClassesAndFunctionsPractice1Slide,
        ClassesAndFunctionsPractice2Slide,
        ClassesAndFunctionsPractice3Slide,
        ClassesAndFunctionsPractice4Slide
    )
)

object ClassesAndFunctionsPracticeIntroSlide : Slide(
    header = "Practice Exercises",
    summary = {
        +"Time to practice! These exercises combine concepts from this lesson using geometric shapes as our domain."
    },
    content = {
        p { +"These exercises cover:" }
        ul {
            li { +"Creating shape classes with properties and methods" }
            li { +"Using functions with default and vararg parameters" }
            li { +"Singleton objects for utilities" }
            li { +"Companion objects for factories" }
            li { +"Combining multiple concepts in a cohesive system" }
        }
        br()
        p { +"The shape theme will help you build intuition that will be useful in later lessons on abstraction and polymorphism!" }
    }
)

object ClassesAndFunctionsPractice1Slide : Slide(
    header = "Practice 1: Shape Comparison System",
    summary = {
        +"Create Circle and Rectangle classes and compare their areas."
    },
    content = {
        p { +"Requirements:" }
        ul {
            li { +"Create a "; inlineCode("Circle"); +" class with radius and "; inlineCode("area()"); +" method" }
            li { +"Create a "; inlineCode("Rectangle"); +" class with width, height, and "; inlineCode("area()"); +" method" }
            li { +"Create a function "; inlineCode("compareAreas(area1: Double, area2: Double)"); +" that returns which is larger" }
            li { +"Use PI = 3.14159 for circle calculations" }
        }
        kotlinPlayground(
            code = """
                // TODO: Create Circle class with radius and area() method
                // TODO: Create Rectangle class with width, height, and area() method
                // TODO: Create compareAreas function

                fun main() {
                    val circle = Circle(5.0)
                    val rectangle = Rectangle(7.0, 11.0)

                    println("Circle area: ${'$'}{circle.area()}")      // Should print ~78.54
                    println("Rectangle area: ${'$'}{rectangle.area()}")  // Should print 77.0

                    val larger = compareAreas(circle.area(), rectangle.area())
                    println("Larger area: ${'$'}larger")  // Should indicate circle is larger
                }
            """.trimIndent(),
            executable = true
        )
    }
)

object ClassesAndFunctionsPractice2Slide : Slide(
    header = "Practice 2: Shape Collection Manager",
    summary = {
        +"Build a system to manage multiple shapes and calculate total area."
    },
    content = {
        p { +"Requirements:" }
        ul {
            li { +"Create simple "; inlineCode("Circle"); +" and "; inlineCode("Square"); +" classes with "; inlineCode("area()"); +" methods" }
            li { +"Create a "; inlineCode("ShapeCollection"); +" class that stores shape areas (not the shapes themselves, just the area values)" }
            li { +"Method "; inlineCode("addShapeArea(area: Double)"); +" to add a shape's area" }
            li { +"Method "; inlineCode("getTotalArea(): Double"); +" to sum all areas" }
            li { +"Method "; inlineCode("getAverageArea(): Double"); +" to calculate average" }
        }
        kotlinPlayground(
            code = """
                // TODO: Create Circle class with radius and area() method
                // TODO: Create Square class with side and area() method
                // TODO: Create ShapeCollection class

                fun main() {
                    val collection = ShapeCollection()

                    val circle = Circle(5.0)
                    val square1 = Square(4.0)
                    val square2 = Square(6.0)

                    collection.addShapeArea(circle.area())
                    collection.addShapeArea(square1.area())
                    collection.addShapeArea(square2.area())

                    println("Total area: ${'$'}{collection.getTotalArea()}")     // Should print ~130.54
                    println("Average area: ${'$'}{collection.getAverageArea()}")  // Should print ~43.51
                }
            """.trimIndent(),
            executable = true
        )
    }
)

object ClassesAndFunctionsPractice3Slide : Slide(
    header = "Practice 3: Shape Validator Singleton",
    summary = {
        +"Create a singleton shape validator with dimension checking and statistics."
    },
    content = {
        p {
            +"Create a singleton object "
            inlineCode("ShapeValidator")
            +" that validates and tracks shape dimensions."
        }
        p { +"It should have:" }
        ul {
            li { +"A mutable list to store validated areas" }
            li { +"Function "; inlineCode("validateAndLogCircle(radius: Double)"); +" that checks radius > 0, calculates area, and stores it" }
            li { +"Function "; inlineCode("validateAndLogRectangle(width: Double, height: Double)"); +" that checks both > 0, calculates area, and stores it" }
            li { +"Function "; inlineCode("getTotalArea(): Double"); +" to sum all validated areas" }
            li { +"Function "; inlineCode("getValidShapeCount(): Int"); +" to return count of valid shapes" }
        }
        kotlinPlayground(
            code = """
                // TODO: Implement ShapeValidator singleton object
                // Use PI = 3.14159

                fun main() {
                    ShapeValidator.validateAndLogCircle(5.0)      // Valid
                    ShapeValidator.validateAndLogRectangle(4.0, 6.0)  // Valid
                    ShapeValidator.validateAndLogCircle(-2.0)     // Invalid, should not be stored
                    ShapeValidator.validateAndLogRectangle(3.0, 8.0)  // Valid

                    println("Total area: ${'$'}{ShapeValidator.getTotalArea()}")      // Should print ~126.54
                    println("Valid shapes: ${'$'}{ShapeValidator.getValidShapeCount()}")  // Should print 3
                }
            """.trimIndent(),
            executable = true
        )
    }
)

object ClassesAndFunctionsPractice4Slide : Slide(
    header = "Practice 4: Complete Shape Library",
    summary = {
        +"Build a comprehensive shape library system combining all concepts."
    },
    content = {
        p { +"Create a complete shape system:" }
        ul {
            li { +"Create "; inlineCode("Circle"); +" and "; inlineCode("Rectangle"); +" classes with name, dimensions, "; inlineCode("area()"); +", and "; inlineCode("perimeter()"); +" methods" }
            li { +"Create "; inlineCode("ShapeLibrary"); +" class to manage multiple shapes" }
        }
        p { +"ShapeLibrary requirements:" }
        ul {
            li { +"Store lists of circles and rectangles separately" }
            li { +"Method "; inlineCode("addCircle(circle: Circle)"); +" and "; inlineCode("addRectangle(rectangle: Rectangle)") }
            li { +"Method "; inlineCode("getTotalArea(): Double"); +" to sum all shape areas" }
            li { +"Method "; inlineCode("findLargestArea(): Double"); +" to find the largest area among all shapes" }
            li { +"Companion object with "; inlineCode("create(libraryName: String)"); +" factory and shape counter" }
        }
        kotlinPlayground(
            code = """
                // TODO: Create Circle class with name, radius, area(), perimeter()
                // TODO: Create Rectangle class with name, width, height, area(), perimeter()
                // TODO: Create ShapeLibrary class with companion object factory
                // Use PI = 3.14159

                fun main() {
                    val library = ShapeLibrary.create("My Shapes")

                    library.addCircle(Circle("Circle A", 5.0))
                    library.addRectangle(Rectangle("Rect A", 4.0, 6.0))
                    library.addCircle(Circle("Circle B", 3.0))
                    library.addRectangle(Rectangle("Rect B", 7.0, 2.0))

                    println("Total area: ${'$'}{library.getTotalArea()}")       // Should print ~130.82
                    println("Largest area: ${'$'}{library.findLargestArea()}")  // Should print ~78.54
                }
            """.trimIndent(),
            executable = true
        )
    }
)
