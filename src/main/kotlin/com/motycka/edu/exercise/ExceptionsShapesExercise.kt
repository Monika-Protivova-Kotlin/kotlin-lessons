package com.motycka.edu.exercise

import kotlin.math.sqrt

enum class ShapeType {
    CIRCLE,
    SQUARE,
    RECTANGLE,
    TRIANGLE
}

data class Shape(
    val type: ShapeType,
    val dimensions: List<Double>
) {
    init {
        TODO("Validate dimensions based on shape type and throw InvalidShapeException if invalid")
    }

    fun area(): Double = when (type) {
        ShapeType.CIRCLE -> Math.PI * dimensions[0] * dimensions[0]
        ShapeType.SQUARE -> dimensions[0] * dimensions[0]
        ShapeType.RECTANGLE -> dimensions[0] * dimensions[1]
        ShapeType.TRIANGLE -> {
            val (a, b, c) = dimensions
            val s = (a + b + c) / 2
            sqrt(s * (s - a) * (s - b) * (s - c))
        }
    }
}

class InsufficientSpaceException(
    val required: Double,
    val available: Double
) : Exception("Not enough space! Required: $required, Available: $available")

// Part 3: Canvas Class
class Canvas(private var availableSpace: Double) {
    private val shapes = mutableListOf<Shape>()

    fun addShape(type: ShapeType, dimensions: List<Double>) {
        TODO("Add shape to canvas if enough space, otherwise throw InsufficientSpaceException")
    }

    fun getStatus(): String {
        return "Canvas: ${shapes.size} shapes, $availableSpace space remaining"
    }
}

fun main() {
    val canvas = Canvas(100.0)

    val testShapes = listOf(
        ShapeType.CIRCLE to listOf(3.0),
        ShapeType.RECTANGLE to listOf(5.0, 4.0),
        ShapeType.SQUARE to listOf(-2.0),
        ShapeType.TRIANGLE to listOf(3.0, 4.0, 5.0),
        ShapeType.CIRCLE to listOf(15.0),
        ShapeType.TRIANGLE to listOf(1.0, 2.0, 10.0),
        ShapeType.SQUARE to listOf(3.0),
        ShapeType.RECTANGLE to listOf(4.0, 3.0),
    )

    TODO("Iterate testShapes, attempt to add each to canvas, handle exceptions, and print status")
}
