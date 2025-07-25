fun main() {
    var a = 10
    var b = 3

    println("a + b = ${a + b}")  // 13
    println("a - b = ${a - b}")  // 7
    println("a * b = ${a * b}")  // 30
    println("a / b = ${a / b}")  // 3 (integer division)
    println("a % b = ${a % b}")  // 1 (remainder)

    // Pre-increment vs Post-increment
    println("a++ = ${a++}")      // 10 (returns old value, then increments)
    println("a = $a")            // 11
    println("++a = ${++a}")      // 12 (increments first, then returns new value)

    // Floating point division
    val c = 10.0
    val d = 3.0
    println("c / d = ${c / d}")  // 3.3333333333333335
}