fun main() {
    // Simple if statement
    val temperature = 25
    if (temperature > 20) {
        println("It's warm today!")
    }

    // if-else statement
    val age = 17
    if (age >= 18) {
        println("You can vote")
    } else {
        println("You cannot vote yet")
    }

    // Multiple conditions with else if
    val score = 85
    if (score >= 90) {
        println("Grade: A")
    } else if (score >= 80) {
        println("Grade: B")
    } else if (score >= 70) {
        println("Grade: C")
    } else if (score >= 60) {
        println("Grade: D")
    } else {
        println("Grade: F")
    }
}