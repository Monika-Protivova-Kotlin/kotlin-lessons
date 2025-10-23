package com.motycka.edu.exercise.generic

import com.motycka.edu.exercise.abstraction.Circle
import com.motycka.edu.exercise.abstraction.Rectangle
import com.motycka.edu.exercise.abstraction.Shape2D
import kotlin.math.sqrt

fun main() {
    val circle = Circle(3.0)
    val rectangle = Rectangle(2.0, 4.0)

    // Exercise 1
    val greaterShape = selectGreater(
        shape1 = circle,
        shape2 = rectangle
    )

    when (greaterShape) {
        is Circle -> println("The circle is greater than the rectangle")
        is Rectangle -> println("The rectangle is greater than the circle")
    }

    // Exercise 2
    val triangle1 = Triangle(2.0, 4.0, 3.0)
    val triangle2 = Triangle(3.0, 3.0, 3.0)

    when {
        triangle1.isEqual(triangle2) -> println("Triangles are equal")
        triangle1.isGreater(triangle2) -> println("Triangle 1 is greater than triangle 2")
        triangle1.isSmaller(triangle2) -> println("Triangle 1 is smaller than triangle 2")
    }

}

fun <T: Shape2D> selectGreater(shape1: T, shape2: T): T {
    return listOf(shape1, shape2).maxBy { it.area() }
}

interface ComparableShape<T> {
    fun isEqual(other: T): Boolean
    fun isGreater(other: T): Boolean
    fun isSmaller(other: T): Boolean
}

class Triangle(
    val a: Double,
    val b: Double,
    val c: Double
): Shape2D, ComparableShape<Triangle> {
    override fun area(): Double {
        val s = (a + b + c) / 2
        return sqrt(s * (s - a) * (s - b) * (s - c))
    }

    override fun perimeter(): Double {
        return a + b + c
    }

    override fun isEqual(other: Triangle): Boolean {
        return this.area() == other.area()
    }

    override fun isGreater(other: Triangle): Boolean {
        return this.area() > other.area()
    }

    override fun isSmaller(other: Triangle): Boolean {
        return this.area() < other.area()
    }

}
