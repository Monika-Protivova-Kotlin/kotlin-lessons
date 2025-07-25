fun main() {
    // Basic for loop syntax
    for (i in 1..5) {
        println("Number: $i")
    }

    // Iterating over arrays
    val fruits = arrayOf("apple", "banana", "orange")
    for (fruit in fruits) {
        println("Fruit: $fruit")
    }

    // Range expressions with downTo and step
    for (i in 10 downTo 1 step 2) {
        println("Countdown: $i")  // 10, 8, 6, 4, 2
    }

    // Iterating with indices
    for (index in fruits.indices) {
        println("$index: ${fruits[index]}")
    }

    // Using withIndex() for both index and value
    for ((index, value) in fruits.withIndex()) {
        println("$index -> $value")
    }

    // Iterating over characters in a string
    val text = "Hello"
    for (char in text) {
        print("$char ")
    }
}