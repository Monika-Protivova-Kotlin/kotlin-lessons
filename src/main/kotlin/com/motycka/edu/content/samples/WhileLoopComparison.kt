fun main() {
    // Example 1 - while loop
    var counter1 = 10
    while (counter1 < 5) {
        println("Counter: $counter1")
        counter1++
    }
    println("Final counter: $counter1")
    // What will this print?

    println("---")

    // Example 2 - do-while loop
    var counter2 = 10
    do {
        println("Counter: $counter2")
        counter2++
    } while (counter2 < 5)
    println("Final counter: $counter2")
    // What will this print?
}