package com.motycka.edu.content.samples

fun main() {
    val obj: Any = "Hello"
    val str = obj as String  // Explicit cast
    val safeStr = obj as? String // Safe cast, returns null if fails
}
