package com.motycka.edu.content.topics.basics.essentials

import com.motycka.edu.model.Slide
import com.motycka.edu.model.Topic
import com.motycka.edu.model.inlineCode
import com.motycka.edu.model.kotlinPlayground
import kotlinx.html.*

object KotlinEssentialsPracticeTopic : Topic(
    title = "Practice: Kotlin Essentials",
    slides = listOf(
        PracticeIntroSlide,
        Exercise1Slide,
        Exercise2Slide,
        Exercise3Slide,
        Exercise4Slide,
        Exercise5Slide
    )
)

object PracticeIntroSlide : Slide(
    header = "Practice Exercises",
    summary = {
        +"Time to practice what you've learned! Work through these exercises to reinforce your understanding of Kotlin essentials."
    },
    content = {
        p { +"These exercises cover:" }
        ul {
            li { +"Data types and variables" }
            li { +"Operators" }
            li { +"Conditionals" }
            li { +"String manipulation" }
            li { +"Type conversion" }
        }
        br()
        p { +"Try to solve each exercise on your own before looking at the solution!" }
    }
)

object Exercise1Slide : Slide(
    header = "Exercise 1: Temperature Converter",
    summary = {
        +"Write a function that converts temperature from Celsius to Fahrenheit."
    },
    content = {
        p {
            +"Formula: "
            inlineCode("F = (C × 9/5) + 32")
        }
        br()
        kotlinPlayground(
            code = """
                fun celsiusToFahrenheit(celsius: Double): Double {
                    // TODO: Implement the conversion
                    return 0.0
                }

                fun main() {
                    println("0°C = ${'$'}{celsiusToFahrenheit(0.0)}°F")
                    println("100°C = ${'$'}{celsiusToFahrenheit(100.0)}°F")
                    println("37°C = ${'$'}{celsiusToFahrenheit(37.0)}°F")
                }
            """.trimIndent(),
            executable = true
        )
    }
)

object Exercise2Slide : Slide(
    header = "Exercise 2: Grade Calculator",
    summary = {
        +"Write a function that takes a numeric score and returns a letter grade."
    },
    content = {
        p { +"Grading scale:" }
        ul {
            li { +"90-100: A" }
            li { +"80-89: B" }
            li { +"70-79: C" }
            li { +"60-69: D" }
            li { +"Below 60: F" }
        }
        br()
        kotlinPlayground(
            code = """
                fun getGrade(score: Int): String {
                    // TODO: Implement grade calculation
                    return ""
                }

                fun main() {
                    println("Score 95: ${'$'}{getGrade(95)}")
                    println("Score 85: ${'$'}{getGrade(85)}")
                    println("Score 75: ${'$'}{getGrade(75)}")
                    println("Score 65: ${'$'}{getGrade(65)}")
                    println("Score 55: ${'$'}{getGrade(55)}")
                }
            """.trimIndent(),
            executable = true
        )
    }
)

object Exercise3Slide : Slide(
    header = "Exercise 3: String Manipulation",
    summary = {
        +"Write a function that checks if a string is a palindrome (reads the same forwards and backwards)."
    },
    content = {
        p { +"Examples of palindromes: \"racecar\", \"level\", \"noon\"" }
        br()
        kotlinPlayground(
            code = """
                fun isPalindrome(text: String): Boolean {
                    // TODO: Implement palindrome check
                    // Hint: Compare the string with its reverse
                    return false
                }

                fun main() {
                    println("'racecar' is palindrome: ${'$'}{isPalindrome("racecar")}")
                    println("'hello' is palindrome: ${'$'}{isPalindrome("hello")}")
                    println("'level' is palindrome: ${'$'}{isPalindrome("level")}")
                }
            """.trimIndent(),
            executable = true
        )
    }
)

object Exercise4Slide : Slide(
    header = "Exercise 4: Even or Odd Counter",
    summary = {
        +"Write a function that takes a range and returns how many even and odd numbers are in that range."
    },
    content = {
        kotlinPlayground(
            code = """
                fun countEvenOdd(start: Int, end: Int): Pair<Int, Int> {
                    // TODO: Count even and odd numbers
                    // Return Pair(evenCount, oddCount)
                    return Pair(0, 0)
                }

                fun main() {
                    val (even, odd) = countEvenOdd(1, 10)
                    println("From 1 to 10: ${'$'}even even numbers, ${'$'}odd odd numbers")

                    val (even2, odd2) = countEvenOdd(5, 15)
                    println("From 5 to 15: ${'$'}even2 even numbers, ${'$'}odd2 odd numbers")
                }
            """.trimIndent(),
            executable = true
        )
    }
)

object Exercise5Slide : Slide(
    header = "Exercise 5: FizzBuzz",
    summary = {
        +"The classic programming exercise! Print numbers 1-30, but replace multiples of 3 with \"Fizz\", multiples of 5 with \"Buzz\", and multiples of both with \"FizzBuzz\"."
    },
    content = {
        kotlinPlayground(
            code = """
                fun fizzBuzz(n: Int) {
                    for (i in 1..n) {
                        // TODO: Implement FizzBuzz logic
                        // Hint: Use when or if-else
                        println(i)
                    }
                }

                fun main() {
                    fizzBuzz(30)
                }
            """.trimIndent(),
            executable = true
        )
        br()
        p {
            em { +"Expected output: 1, 2, Fizz, 4, Buzz, Fizz, 7, 8, Fizz, Buzz, 11, Fizz, 13, 14, FizzBuzz, ..." }
        }
    }
)
