package com.motycka.edu.content.topics.basics.datetime

import kotlinx.html.*
import com.motycka.edu.model.Topic
import com.motycka.edu.model.Slide
import com.motycka.edu.model.kotlinPlayground

object LocalDateTopic : Topic(
    title = "LocalDate",
    slides = listOf(
        LocalDateSlide
    )
)

object LocalDateSlide : Slide(
    header = "LocalDate",
    summary = {
        strong { +"LocalDate" }
        +" allows us to work with dates without time zone. Here are some examples."
    },
    content = {
        p { +"Get today's date" }
        kotlinPlayground(
            code = """
                import java.time.LocalDate

                val dateNow = LocalDate.now()
                println(dateNow)
            """.trimIndent(),
            executable = true
        )
        p { +"Get a specific date" }
        kotlinPlayground(
            code = """
                import java.time.LocalDate

                val date = LocalDate.of(2024, 2, 20)
                println(date)
            """.trimIndent(),
            executable = true
        )
        p { +"Parse an ISO-8601 format date" }
        kotlinPlayground(
            code = """
                import java.time.LocalDate

                val parsedDate = LocalDate.parse("2024-02-20")
                println(parsedDate)
            """.trimIndent(),
            executable = true
        )
        p { +"Working with date operations" }
        kotlinPlayground(
            code = """
                import java.time.LocalDate
                import java.time.temporal.ChronoUnit

                val date = LocalDate.of(2024, 2, 20)

                // Adding/subtracting days, months, years
                val tomorrow = date.plusDays(1)
                val lastWeek = date.minusWeeks(1)
                val nextMonth = date.plusMonths(1)

                println("Original: ${'$'}date")
                println("Tomorrow: ${'$'}tomorrow")
                println("Last week: ${'$'}lastWeek")
                println("Next month: ${'$'}nextMonth")

                // Calculating differences
                val today = LocalDate.now()
                val daysBetween = ChronoUnit.DAYS.between(date, today)
                println("Days between ${'$'}date and today: ${'$'}daysBetween")
            """.trimIndent(),
            executable = true
        )
    }
)
