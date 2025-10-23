package com.motycka.edu.exercise.abstraction

import kotlin.math.PI

class Circle(
    override val radius: Double
): Shape2D, Ellipse {

    override fun area() = PI * radius * radius

    override fun perimeter() = 2 * PI * radius

    override fun to3D() = Sphere(radius)

}
