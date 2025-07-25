package com.motycka.edu.content.topics.basics.datetime

import kotlinx.html.*
import com.motycka.edu.model.Topic
import com.motycka.edu.model.Slide
import com.motycka.edu.model.kotlinPlayground

object LocalDateTimeTopic : Topic(
    title = "LocalDateTime",
    slides = listOf(
        LocalDateTimeSlide
    )
)

object LocalDateTimeSlide : Slide(
    header = "LocalDateTime",
    summary = {
        strong { +"LocalDateTime" }
        +" works the same way, but it also adds time component ..."
    },
    content = {
        kotlinPlayground(
            code = """
                import java.time.LocalDateTime
                import java.time.format.DateTimeFormatter

                fun main() {
                    // Get current date and time
                    val dateTimeNow = LocalDateTime.now()
                    println("Now: ${'$'}dateTimeNow")

                    // Create specific date and time
                    val dateTime = LocalDateTime.of(2024, 2, 20, 14, 30, 45)
                    println("Specific: ${'$'}dateTime")

                    // Parse from string
                    val parsedDateTime = LocalDateTime.parse("2024-02-20T14:30:45")
                    println("Parsed: ${'$'}parsedDateTime")

                    // Formatting
                    val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")
                    val formatted = dateTime.format(formatter)
                    println("Formatted: ${'$'}formatted")

                    // Date and time operations
                    val inTwoHours = dateTime.plusHours(2)
                    val lastMonth = dateTime.minusMonths(1)
                    val nextWeek = dateTime.plusWeeks(1)

                    println("In 2 hours: ${'$'}inTwoHours")
                    println("Last month: ${'$'}lastMonth")
                    println("Next week: ${'$'}nextWeek")

                    // Extract components
                    println("Year: ${'$'}{dateTime.year}")
                    println("Month: ${'$'}{dateTime.month}")
                    println("Day: ${'$'}{dateTime.dayOfMonth}")
                    println("Hour: ${'$'}{dateTime.hour}")
                    println("Minute: ${'$'}{dateTime.minute}")
                    println("Second: ${'$'}{dateTime.second}")
                }
            """.trimIndent(),
            executable = true
        )
    }
)
