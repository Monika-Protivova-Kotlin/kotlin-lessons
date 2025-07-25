fun main() {
    // Basic when statement with specific values
    val dayOfWeek = 3
    when (dayOfWeek) {
        1 -> println("Monday")
        2 -> println("Tuesday")
        3 -> println("Wednesday")
        4 -> println("Thursday")
        5 -> println("Friday")
        6, 7 -> println("Weekend")
        else -> println("Invalid day")
    }

    // when statement with conditions
    val x = 15
    when {
        x < 0 -> println("Negative")
        x == 0 -> println("Zero")
        x > 0 && x <= 10 -> println("Small positive")
        x in 11..100 -> println("Medium positive")
        else -> println("Large positive")
    }

    // when statement with ranges
    val grade = 85
    val letterGrade = when (grade) {
        in 90..100 -> "A"
        in 80..89 -> "B"
        in 70..79 -> "C"
        in 60..69 -> "D"
        else -> "F"
    }
    println("Grade: $letterGrade")
}