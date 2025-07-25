package com.motycka.edu.content.samples

fun getSomething(): Any {
    return 1234567890
}

fun main() {
    val something = getSomething()

    println(something is Int)			 // true
    println(something is Long)			 // false
    println(something::class.simpleName) // Int
}
