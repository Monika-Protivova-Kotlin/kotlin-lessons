fun main() {
    val a = 5
    val b = 3
    val c = 2

    println("a == b: ${a == b}")  // false
    println("a != b: ${a != b}")  // true
    println("a > b: ${a > b}")    // true
    println("b < a: ${b < a}")    // true
    println("a >= c: ${a >= c}")  // true
    println("b <= c: ${b <= c}")  // false

    // What will these print?
    println("a + c == b + b: ${a + c == b + b}")  // ?
    println("a > b && b > c: ${a > b && b > c}")  // ?
}