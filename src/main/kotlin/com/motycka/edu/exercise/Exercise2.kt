package com.motycka.edu.exercise

/**
 * Exercise 2: Shape Analyzer with Functions
 *
 * Overview:
 * Build on Exercise 1 by extracting the shape determination and calculation logic into reusable functions.
 * This exercise teaches you how to organize code into functions for better reusability and readability.
 *
 * Learning Objectives:
 * - Create functions that take parameters and return values
 * - Break down complex logic into smaller, manageable functions
 * - Pass arrays as function parameters
 * - Use function return values in your main logic
 * - Understand the benefits of code reusability
 *
 * Task Description:
 * Create the following functions:
 *
 * 1. determineShape2D(dimensions: Array<Double>): String
 *    - Takes a dimensions array
 *    - Returns the 2D shape name based on array size
 *    - Returns: "circle", "rectangle", "triangle", or "unknown 2D shape"
 *
 * 2. determineShape3D(dimensions: Array<Double>): String
 *    - Takes a dimensions array
 *    - Returns the 3D shape name based on array size
 *    - Returns: "sphere", "cylinder", "box", or "unknown 3D shape"
 *
 * 3. calculateArea(shape: String, dimensions: Array<Double>): Double
 *    - Takes the shape name and dimensions array
 *    - Returns the calculated area based on the shape
 *    - Formulas:
 *      - Circle: π × r²
 *      - Rectangle: width × height
 *      - Triangle: Heron's formula (same as Exercise 1)
 *
 * 4. calculateVolume(shape: String, dimensions: Array<Double>): Double
 *    - Takes the shape name and dimensions array
 *    - Returns the calculated volume based on the shape
 *    - Formulas:
 *      - Sphere: (4/3) × π × r³
 *      - Cylinder: π × r² × h
 *      - Box: length × width × height
 *
 * 5. Update main() to use these functions
 *    - Call determineShape2D() and determineShape3D()
 *    - Call calculateArea() and calculateVolume()
 *    - Print the results
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
 * - Function declaration with parameters and return types
 * - Function calls with arguments
 * - Code organization and reusability
 * - Breaking complex problems into smaller functions
 * - Working with arrays in function parameters
 */

// TODO: Create determineShape2D function
fun determineShape2D(dimensions: Array<Double>): String {
    return when (dimensions.size) {
        1 -> "circle"      // just radius
        2 -> "rectangle"   // width, height
        3 -> "triangle"    // 3 sides
        else -> "unknown 2D shape"
    }
}

// TODO: Create determineShape3D function
fun determineShape3D(dimensions: Array<Double>): String {
    return when (dimensions.size) {
        1 -> "sphere"      // just radius
        2 -> "cylinder"    // radius, height
        3 -> "box"         // length, width, height
        else -> "unknown 3D shape"
    }
}

// TODO: Create calculateArea function
fun calculateArea(shape: String, dimensions: Array<Double>): Double {
    return when (shape) {
        "circle" -> Math.PI * dimensions[0] * dimensions[0]
        "rectangle" -> dimensions[0] * dimensions[1]
        "triangle" -> {
            val s = (dimensions[0] + dimensions[1] + dimensions[2]) / 2
            Math.sqrt(s * (s - dimensions[0]) * (s - dimensions[1]) * (s - dimensions[2]))
        }
        else -> 0.0
    }
}

// TODO: Create calculateVolume function
fun calculateVolume(shape: String, dimensions: Array<Double>): Double {
    return when (shape) {
        "sphere" -> (4.0/3.0) * Math.PI * Math.pow(dimensions[0], 3.0)
        "cylinder" -> Math.PI * dimensions[0] * dimensions[0] * dimensions[1]
        "box" -> dimensions[0] * dimensions[1] * dimensions[2]
        else -> 0.0
    }
}

fun main() {
    val dimensions = arrayOf(4.0, 3.0, 2.0)

    // TODO: Call determineShape2D function
    val shape2d = determineShape2D(dimensions)

    // TODO: Call calculateArea function
    val area = calculateArea(shape2d, dimensions)

    println("2D Shape with [${dimensions.joinToString(", ")}] is: $shape2d")
    println("Area: $area")
    println()

    // TODO: Call determineShape3D function
    val shape3d = determineShape3D(dimensions)

    // TODO: Call calculateVolume function
    val volume = calculateVolume(shape3d, dimensions)

    println("3D Shape with [${dimensions.joinToString(", ")}] is: $shape3d")
    println("Volume: $volume")
}
