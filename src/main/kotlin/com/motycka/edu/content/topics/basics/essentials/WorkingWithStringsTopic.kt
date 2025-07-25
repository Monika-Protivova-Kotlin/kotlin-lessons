package com.motycka.edu.content.topics.basics.essentials

import com.motycka.edu.model.Slide
import com.motycka.edu.model.Slide.Companion.DOLLAR
import com.motycka.edu.model.Topic
import com.motycka.edu.model.kotlinPlayground
import kotlinx.html.p

object WorkingWithStringsTopic : Topic(
    title = "Working with Strings",
    slides = listOf(
        StringBasicsSlide,
        StringMethodsSlide
    )
)

const val PRINTING_TO_CONSOLE_EXAMPLE = """
fun main() {
    print("Hello, ") // Prints without newline
    println("World") // Prints with newline
    println("I am learning Kotlin!")
}
"""

const val STRING_CONCATENATION_EXAMPLE = """
fun main() {
    val firstName = "John"
    val lastName = "Doe"
    val age = 25
    var learning = "I am learning "
    
    println("My name is" + firstName + " " + lastName + ".") // Concatenation with + operator (discouraged)
    println("I am ${DOLLAR}age years old." ) // String interpolation with $ syntax (commonly used)
    println("Next year I'll be ${DOLLAR}{age + 1}.") // Expression in curly braces (commonly used)
    
    learning += "Kotlin!" // Concatenation with += operator (discouraged, not very common)
    println(learning)                           
}
"""

const val STRING_FORMATTING_EXAMPLE = """
fun main() {
    val formatted = String.format("Student %s scored %.1f%%", "Alice", 95.7)
    println(formatted)
}
"""

object StringBasicsSlide : Slide(
    header = "Working with Strings",
    content = {
        p { +"This example demonstrates basic string operations including printing to console, concatenation, and formatting."}
        kotlinPlayground(
            code = PRINTING_TO_CONSOLE_EXAMPLE,
            executable = true
        )
        kotlinPlayground(
            code = STRING_CONCATENATION_EXAMPLE,
            executable = true
        )
        kotlinPlayground(
            code = STRING_FORMATTING_EXAMPLE,
            executable = true
        )
    }
)

const val STRING_OPERATIONS_EXAMPLE = """
fun main() {

    val text = "  Hello, Kotlin World!  "
    val empty = ""
    val blank = "   "

    val textLength = text.length
    
    val trimmedText = text.trim()
    val substring1 = trimmedText.substring(0, 5)
    val substring2 = trimmedText.substring(7)
    val words = text.trim().split(", ")
    
    val upperCaseText = text.uppercase()
    val lowerCaseText = text.lowercase()
    val replacedText = text.replace("World", "Universe")
    
    val containsString = text.contains("Kotlin")
    val startsWithString = text.trim().startsWith("Hello")
    val endsWithString = text.trim().endsWith("!")

    val isEmpty = empty.isEmpty()
    val isBlank = empty.isBlank()
    val isEmpty2 = blank.isEmpty()
    val isBlank2 = blank.isBlank()
    
    
    println("Original: '${DOLLAR}text'")
    println("Trimmed: '${DOLLAR}trimmedText'")
    println("Length: ${DOLLAR}textLength")
    println("Substring (0, 5): ${DOLLAR}substring1")
    println("Substring from 7: ${DOLLAR}substring2")
    println("Words: ${DOLLAR}words")
    println("Uppercase: ${DOLLAR}upperCaseText")
    println("Lowercase: ${DOLLAR}lowerCaseText")
    println("Replace 'World' with 'Universe': ${DOLLAR}replacedText")
    println("Contains 'Kotlin': ${DOLLAR}containsString")
    println("Starts with 'Hello': ${DOLLAR}startsWithString")
    println("Ends with '!': ${DOLLAR}endsWithString")
    println("Empty is empty: ${DOLLAR}isEmpty")
    println("Empty is blank: ${DOLLAR}isBlank")
    println("Blank is empty: ${DOLLAR}isEmpty2")
    println("Blank is blank: ${DOLLAR}isBlank2")
}
"""


object StringMethodsSlide : Slide(
    header = "Working with Strings",
    content = {
        p { +"This example demonstrates various string operations including length, substrings, conversion, and content checks."}
        kotlinPlayground(
            code = STRING_OPERATIONS_EXAMPLE,
            selectLines = 3..24,
            executable = true
        )
    }
)
