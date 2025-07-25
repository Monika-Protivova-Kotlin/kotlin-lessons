package com.motycka.edu.content.samples

fun main() {
    val intValue = 100
    val longValue = intValue.toLong()   // Convert to Long
    val shortValue = intValue.toShort() // May lose data if too large!
    val floatValue = intValue.toFloat()
    val stringValue = intValue.toString()
}
