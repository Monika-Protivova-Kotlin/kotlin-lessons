package com.motycka.edu.content.topics.oop

import kotlinx.html.*
import com.motycka.edu.model.ExerciseTopic
import com.motycka.edu.model.Slide
import com.motycka.edu.model.Topic
import com.motycka.edu.model.kotlinPlayground
import com.motycka.edu.model.inlineCode

object InheritanceExercisesTopic : Topic(
    title = "Inheritance Exercises",
    subtitle = "Hands-on Practice",
    slides = listOf(
        InheritanceExercise1Slide,
        InheritanceExercise2Slide,
        InheritanceExercise3Slide,
        InheritanceExercise4Slide,
        InheritanceExercise5Slide
    )
)

object InheritanceExercise1Slide : Slide(
    header = "Part 1: Rectangle Class",
    content = {
        p {
            +"Create an "
            strong { +"open" }
            +" class "
            strong { +"Rectangle" }
            +" with the following:"
        }
        p { strong { +"Properties:" } }
        ul {
            li { inlineCode("width: Double"); +" - make it "; strong { +"protected" } }
            li { inlineCode("length: Double"); +" - make it "; strong { +"protected" } }
            li { +"Constructor that initializes both fields" }
        }
        p { strong { +"Methods:" } }
        ul {
            li { inlineCode("area()"); +" - returns the area of the rectangle (width × length)" }
            li { inlineCode("perimeter()"); +" - returns the perimeter (2 × (width + length))" }
        }
    }
)

object InheritanceExercise2Slide : Slide(
    header = "Part 2: Square Class",
    content = {
        p {
            +"Create a class "
            strong { +"Square" }
            +" that extends "
            strong { +"Rectangle" }
            +"."
        }
        p {
            +"A square has equal sides, so use a single "
            inlineCode("side")
            +" parameter and pass it to the "
            inlineCode("Rectangle")
            +" constructor for both width and length."
        }
        p {
            +"Now, if you instantiate either "
            strong { +"Square" }
            +" or "
            strong { +"Rectangle" }
            +" and call the "
            inlineCode("area()")
            +" and "
            inlineCode("perimeter()")
            +" methods, you should get the correct results."
        }
    }
)

object InheritanceExercise3Slide : Slide(
    header = "Part 3: Cuboid Class",
    content = {
        p {
            +"Create a class "
            strong { +"Cuboid" }
            +" (3D rectangular block) with:"
        }
        p { strong { +"Properties:" } }
        ul {
            li { inlineCode("width: Double") }
            li { inlineCode("height: Double") }
            li { inlineCode("depth: Double") }
            li { +"Constructor that initializes all fields" }
        }
        p { strong { +"Methods:" } }
        ul {
            li { inlineCode("volume()"); +" - returns the volume (width × height × depth)" }
            li { inlineCode("surfaceArea()"); +" - returns the surface area (2 × (width × height + height × depth + width × depth))" }
        }
    }
)

object InheritanceExercise4Slide : Slide(
    header = "Part 4: Rectangle to3D Method",
    content = {
        p {
            +"Update the "
            strong { +"Rectangle" }
            +" class, add method:"
        }
        ul {
            li {
                inlineCode("to3D(depth: Double): Cuboid")
                +" - returns a new "
                inlineCode("Cuboid")
                +" object with the same width and height (length), plus the given depth"
            }
        }
        p {
            +"This converts a 2D rectangle into a 3D cuboid by adding depth."
        }
    }
)

object InheritanceExercise5Slide : Slide(
    header = "Part 5: Square to3D Method",
    content = {
        p {
            +"Update the "
            strong { +"Square" }
            +" class, add method:"
        }
        ul {
            li {
                inlineCode("to3D(): Cuboid")
                +" - returns a new "
                inlineCode("Cuboid")
                +" where all dimensions are equal (creates a cube)"
            }
        }
        p {
            +"You can proxy the call to the "
            inlineCode("to3D")
            +" method of the "
            strong { +"Rectangle" }
            +" class by passing the square's side length as the depth."
        }
        p {
            +"On this example you can see that "
            strong { +"Square" }
            +" inherits all "
            strong { +"Rectangle" }
            +" methods and properties, and you can also add new methods to it."
        }
    }
)
