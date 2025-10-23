package com.motycka.edu.exercise.abstraction

class Square(
    side: Double
) : Rectangle(side, side) {

    fun to3D(): Cuboid = to3D(width)

}
