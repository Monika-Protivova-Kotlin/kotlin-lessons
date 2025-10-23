package com.motycka.edu.exercise.abstraction

open class Rectangle(
    override val width: Double,
    override val length: Double
) : Shape2D, Quadrilateral {

    override fun area() = width * length

    override fun perimeter() = 2 * (width + length)

    override fun to3D(depth: Double) = Cuboid(
        width = width,
        height = length,
        depth = depth
    )

}
