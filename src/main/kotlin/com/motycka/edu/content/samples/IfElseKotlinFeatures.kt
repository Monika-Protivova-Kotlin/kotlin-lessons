fun main() {
    // Traditional approach with mutable variable
    val temperature = 25
    var message: String
    if (temperature > 20) {
        message = "It's warm"
    } else {
        message = "It's cold"
    }
    println(message)

    // Kotlin's concise approach with if-else as expression
    val message2 = if (temperature > 20) {
        "It's warm"
    } else {
        "It's cold"
    }
    println(message2)

    // Ternary-like usage (single line)
    val age = 17
    val status = if (age >= 18) "Adult" else "Minor"
    println("Status: $status")
}