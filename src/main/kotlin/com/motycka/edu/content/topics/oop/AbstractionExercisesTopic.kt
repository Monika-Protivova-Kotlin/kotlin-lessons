package com.motycka.edu.content.topics.oop

import kotlinx.html.*
import com.motycka.edu.model.ExerciseTopic
import com.motycka.edu.model.Slide
import com.motycka.edu.model.Topic
import com.motycka.edu.model.kotlinPlayground
import com.motycka.edu.model.inlineCode

object AbstractionExercisesTopic : Topic(
    title = "Abstraction Exercises",
    subtitle = "Hands-on Practice",
    slides = listOf(
        AbstractionExercise1Slide,
        AbstractionExercise2Slide,
        AbstractionExercise3Slide,
        AbstractionExercise4Slide,
        AbstractionExercise5Slide
    )
)

object AbstractionExercise1Slide : Slide(
    header = "Part 1: Shape2D Interface",
    content = {
        p {
            +"Create an "
            strong { +"interface" }
            +" called "
            strong { +"Shape2D" }
            +" with the following methods:"
        }
        ul {
            li { inlineCode("fun area(): Double"); +" - returns the area of the 2D shape" }
            li { inlineCode("fun perimeter(): Double"); +" - returns the perimeter of the 2D shape" }
        }
        p {
            +"Update your "
            strong { +"Rectangle" }
            +" class from the inheritance exercise to "
            strong { +"implement" }
            +" the "
            strong { +"Shape2D" }
            +" interface."
        }
        p {
            +"Since "
            strong { +"Rectangle" }
            +" already has these methods, you just need to add "
            inlineCode(": Shape2D")
            +" after the class declaration."
        }
    }
)

object AbstractionExercise2Slide : Slide(
    header = "Part 2: Quadrilateral Interface",
    content = {
        p {
            +"Create an "
            strong { +"interface" }
            +" called "
            strong { +"Quadrilateral" }
            +" with the following:"
        }
        p { strong { +"Properties:" } }
        ul {
            li { inlineCode("val width: Double") }
            li { inlineCode("val length: Double") }
        }
        p { strong { +"Methods:" } }
        ul {
            li { inlineCode("fun to3D(depth: Double): Cuboid"); +" - converts the 2D quadrilateral to a 3D cuboid" }
        }
        p {
            +"Update your "
            strong { +"Rectangle" }
            +" class to implement "
            strong { +"both" }
            +" the "
            strong { +"Shape2D" }
            +" and "
            strong { +"Quadrilateral" }
            +" interfaces."
        }
        p {
            +"Your "
            strong { +"Rectangle" }
            +" class should now look like:"
        }
        kotlinPlayground(
            code = """
                    open class Rectangle(
                        override val width: Double,
                        override val length: Double
                    ) : Shape2D, Quadrilateral {
                        // existing area(), perimeter(), and to3D() methods
                    }
                """.trimIndent(),
            executable = false
        )
        p {
            +"This demonstrates how a class can implement "
            strong { +"multiple interfaces" }
            +" in Kotlin."
        }
    }
)

object AbstractionExercise3Slide : Slide(
    header = "Part 3: Circle and Ellipse Interface",
    content = {
        p {
            +"Create an "
            strong { +"interface" }
            +" called "
            strong { +"Ellipse" }
            +" with the following:"
        }
        p { strong { +"Properties:" } }
        ul {
            li { inlineCode("val radius: Double") }
        }
        p { strong { +"Methods:" } }
        ul {
            li { inlineCode("fun to3D(): Sphere"); +" - converts the 2D circle to a 3D sphere" }
        }
        p {
            +"Create a class "
            strong { +"Circle" }
            +" that implements "
            strong { +"both" }
            +" the "
            strong { +"Shape2D" }
            +" and "
            strong { +"Ellipse" }
            +" interfaces:"
        }
        p { strong { +"Properties:" } }
        ul {
            li { inlineCode("override val radius: Double"); +" - the radius of the circle" }
        }
        p { strong { +"Methods:" } }
        ul {
            li { inlineCode("override fun area()"); +" - returns π × radius² (use "; inlineCode("Math.PI"); +")" }
            li { inlineCode("override fun perimeter()"); +" - returns 2 × π × radius" }
            li { inlineCode("override fun to3D()"); +" - returns a new "; inlineCode("Sphere"); +" with the same radius" }
        }
    }
)

object AbstractionExercise4Slide : Slide(
    header = "Part 4: Shape3D Interface",
    content = {
        p {
            +"Create an "
            strong { +"interface" }
            +" called "
            strong { +"Shape3D" }
            +" with the following methods:"
        }
        ul {
            li { inlineCode("fun volume(): Double"); +" - returns the volume of the 3D shape" }
            li { inlineCode("fun surfaceArea(): Double"); +" - returns the surface area of the 3D shape" }
        }
        p {
            +"Update your "
            strong { +"Cuboid" }
            +" class from the inheritance exercise to "
            strong { +"implement" }
            +" the "
            strong { +"Shape3D" }
            +" interface."
        }
        p {
            +"Since "
            strong { +"Cuboid" }
            +" already has these methods, you just need to add "
            inlineCode(": Shape3D")
            +" after the class declaration."
        }
    }
)

object AbstractionExercise5Slide : Slide(
    header = "Part 5: Sphere Class",
    content = {
        p {
            +"Create a class "
            strong { +"Sphere" }
            +" that implements the "
            strong { +"Shape3D" }
            +" interface:"
        }
        p { strong { +"Properties:" } }
        ul {
            li { inlineCode("val radius: Double"); +" - the radius of the sphere" }
        }
        p { strong { +"Methods:" } }
        ul {
            li { inlineCode("override fun volume()"); +" - returns (4/3) × π × radius³ (use "; inlineCode("Math.PI"); +")" }
            li { inlineCode("override fun surfaceArea()"); +" - returns 4 × π × radius²" }
        }
        p {
            +"Now you can test your implementation by creating instances of all shapes and calling their methods:"
        }
        ul {
            li { strong { +"Rectangle" }; +" and "; strong { +"Square" }; +" implement "; inlineCode("Shape2D"); +" and "; inlineCode("Quadrilateral") }
            li { strong { +"Circle" }; +" implements "; inlineCode("Shape2D"); +" and "; inlineCode("Ellipse") }
            li { strong { +"Cuboid" }; +" and "; strong { +"Sphere" }; +" implement "; inlineCode("Shape3D") }
            li { +"All 2D shapes can be converted to 3D shapes using their "; inlineCode("to3D()"); +" methods" }
        }
    }
)
