package com.motycka.edu.exercise.abstraction

import kotlin.math.pow

// Sphere implementing Shape3D
class Sphere(
    private val radius: Double
) : Shape3D {

    override fun volume(): Double = (4.0 / 3.0) * Math.PI * radius.pow(3)

    override fun surfaceArea(): Double = 4 * Math.PI * radius.pow(2)

}
