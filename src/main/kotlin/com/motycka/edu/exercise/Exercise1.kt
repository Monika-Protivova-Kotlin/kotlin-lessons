package com.motycka.edu.exercise

/**
 * Exercise: Shape Dimension Analyzer
 *
 * Overview:
 * Write a program that takes an array of dimensions and interprets it as both a 2D and 3D shape,
 * calculating the area and volume respectively.
 *
 * Learning Objectives:
 * - Use `when` expressions to make decisions based on array size
 * - Access array elements using indexing (dimensions[0], dimensions[1], etc.)
 * - Apply mathematical formulas using Math.PI, Math.sqrt(), and Math.pow()
 * - Understand how the same data can represent different shapes depending on interpretation
 *
 * Task Description:
 * Given a `dimensions` array containing 1-3 numeric values, your program should:
 *
 * 1. Determine the 2D shape based on the number of dimensions:
 *    - 1 dimension → Circle (radius)
 *    - 2 dimensions → Rectangle (width, height)
 *    - 3 dimensions → Triangle (side1, side2, side3)
 *
 * 2. Calculate the area for the 2D shape:
 *    - Circle: π × r²
 *    - Rectangle: width × height
 *    - Triangle: Use Heron's formula:
 *      - s = (a + b + c) / 2 (semi-perimeter)
 *      - area = √(s × (s-a) × (s-b) × (s-c))
 *
 * 3. Determine the 3D shape based on the number of dimensions:
 *    - 1 dimension → Sphere (radius)
 *    - 2 dimensions → Cylinder (radius, height)
 *    - 3 dimensions → Box/Cuboid (length, width, height)
 *
 * 4. Calculate the volume for the 3D shape:
 *    - Sphere: (4/3) × π × r³
 *    - Cylinder: π × r² × h
 *    - Box: length × width × height
 *
 * Example Output:
 * For dimensions = arrayOf(4.0, 3.0, 2.0):
 *
 * 2D Shape with [4.0, 3.0, 2.0] is: triangle
 * Area: 2.9047375096555625
 *
 * 3D Shape with [4.0, 3.0, 2.0] is: box
 * Volume: 24.0
 *
 * Skills Practiced:
 * - Array manipulation (.size, indexing)
 * - when expressions with multiple conditions
 * - Mathematical calculations
 * - String interpolation in println()
 * - Understanding different shape formulas
 */
fun main() {
    val dimensions = arrayOf(
        4.0,
        3.0,
        2.0
    )

    val shape2d = when (dimensions.size) {
        1 -> "circle"      // just radius
        2 -> "rectangle"   // width, height
        3 -> "triangle"    // 3 sides
        else -> "unknown 2D shape"
    }

    val area = when (shape2d) {
        "circle" -> Math.PI * dimensions[0] * dimensions[0]
        "rectangle" -> dimensions[0] * dimensions[1]
        "triangle" -> {
            val s = (dimensions[0] + dimensions[1] + dimensions[2]) / 2
            Math.sqrt(s * (s - dimensions[0]) * (s - dimensions[1]) * (s - dimensions[2]))
        }
        else -> 0.0
    }

    println("2D Shape with [${dimensions.joinToString(", ")}] is: $shape2d")
    println("Area: $area")

    val shape3d = when (dimensions.size) {
        1 -> "sphere"      // just radius
        2 -> "cylinder"    // radius, height
        3 -> "box"         // length, width, height
        else -> "unknown 3D shape"
    }


    val volume = when (shape3d) {
        "sphere" -> (4.0/3.0) * Math.PI * Math.pow(dimensions[0], 3.0)
        "cylinder" -> Math.PI * dimensions[0] * dimensions[0] * dimensions[1]
        "box" -> dimensions[0] * dimensions[1] * dimensions[2]
        else -> 0.0
    }

    println("3D Shape with [${dimensions.joinToString(", ")}] is: $shape3d")
    println("Volume: $volume")
}
