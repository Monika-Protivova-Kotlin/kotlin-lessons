package com.motycka.edu.content.topics.basics.datetime

import kotlinx.html.*
import com.motycka.edu.model.Topic
import com.motycka.edu.model.Slide
import com.motycka.edu.model.kotlinPlayground

object DurationAndPeriodTopic : Topic(
    title = "Duration and Period",
    slides = listOf(
        InstantSlide,
        DurationSlide,
        PeriodSlide
    )
)

object InstantSlide : Slide(
    header = "Instant",
    summary = {
        +"To work with zoned date and time correctly, we need to understand the concept of an instant."
    },
    content = {
        ul {
            li { +"Instant represents an instantaneous point on the time-line." }
            li { +"Instant stores date and time information in UTC (Coordinated Universal Time) format." }
            li { +"Instant doesn't contain any information about time zones and doesn't take daylight saving time into account." }
            li { +"Instant is useful for applications requiring a high degree of time precision." }
            li { +"You can easily convert Instant to DateTime, LocalDateTime, ZonedDate ZonedDateTime, but to convert to Instant, you need to specify time zone." }
        }
        kotlinPlayground(
            code = """
                import java.time.*

                fun main() {
                    val instantNow = Instant.now()
                    println("Instant now: ${'$'}instantNow") // 2024-01-14T09:01:00.878577Z

                    val localDateTime = LocalDateTime.now()
                    println("LocalDateTime now: ${'$'}localDateTime") // 2024-01-14T10:01:00.896618

                    val zone = ZoneId.of("Asia/Bangkok")
                    val zoneOffset = zone.rules.getOffset(localDateTime)
                    val instant = localDateTime.toInstant(zoneOffset)
                    println("LocalDateTime to Instant: ${'$'}instant") // 2024-01-14T03:01:00.896618Z

                    // Or using hard-coded offset
                    val instantWithOffset = localDateTime.toInstant(ZoneOffset.ofHours(6))
                    println("With hard-coded offset: ${'$'}instantWithOffset") // 2024-01-14T04:01:00.896618Z

                    // Converting back
                    val backToLocal = instantNow.atZone(ZoneId.systemDefault()).toLocalDateTime()
                    println("Back to LocalDateTime: ${'$'}backToLocal")
                }
            """.trimIndent(),
            executable = true
        )
    }
)

object DurationSlide : Slide(
    header = "Duration",
    summary = {
        +"Duration helps us measure time amount between instants."
    },
    content = {
        kotlinPlayground(
            code = """
                import java.time.*
                import java.time.temporal.ChronoUnit

                fun main() {
                    // Creating durations
                    val duration1 = Duration.ofHours(2)
                    val duration2 = Duration.ofMinutes(30)
                    val duration3 = Duration.ofSeconds(45)

                    println("2 hours: ${'$'}duration1")
                    println("30 minutes: ${'$'}duration2")
                    println("45 seconds: ${'$'}duration3")

                    // Duration between two instants
                    val start = Instant.now()
                    Thread.sleep(100) // Sleep for 100ms
                    val end = Instant.now()
                    val elapsed = Duration.between(start, end)
                    println("Elapsed time: ${'$'}elapsed")

                    // Duration between LocalDateTime
                    val dateTime1 = LocalDateTime.of(2024, 1, 1, 10, 0)
                    val dateTime2 = LocalDateTime.of(2024, 1, 1, 14, 30)
                    val timeBetween = Duration.between(dateTime1, dateTime2)
                    println("Time between: ${'$'}timeBetween")

                    // Working with duration
                    println("Hours: ${'$'}{timeBetween.toHours()}")
                    println("Minutes: ${'$'}{timeBetween.toMinutes()}")
                    println("Seconds: ${'$'}{timeBetween.seconds}")

                    // Adding duration to time
                    val newDateTime = dateTime1.plus(duration1)
                    println("Original: ${'$'}dateTime1")
                    println("Plus 2 hours: ${'$'}newDateTime")
                }
            """.trimIndent(),
            executable = true
        )
    }
)

object PeriodSlide : Slide(
    header = "Period",
    summary = {
        +"Period represents date-based amounts of time (years, months, days)."
    },
    content = {
        kotlinPlayground(
            code = """
                import java.time.*

                fun main() {
                    // Creating periods
                    val period1 = Period.ofYears(1)
                    val period2 = Period.ofMonths(6)
                    val period3 = Period.ofDays(10)
                    val period4 = Period.of(2, 3, 15) // 2 years, 3 months, 15 days

                    println("1 year: ${'$'}period1")
                    println("6 months: ${'$'}period2")
                    println("10 days: ${'$'}period3")
                    println("2y 3m 15d: ${'$'}period4")

                    // Period between two dates
                    val date1 = LocalDate.of(2020, 1, 1)
                    val date2 = LocalDate.of(2024, 3, 15)
                    val periodBetween = Period.between(date1, date2)
                    println("Period between ${'$'}date1 and ${'$'}date2: ${'$'}periodBetween")

                    // Working with period components
                    println("Years: ${'$'}{periodBetween.years}")
                    println("Months: ${'$'}{periodBetween.months}")
                    println("Days: ${'$'}{periodBetween.days}")

                    // Adding period to date
                    val newDate = date1.plus(period4)
                    println("Original: ${'$'}date1")
                    println("Plus period: ${'$'}newDate")

                    // Difference between Duration and Period
                    val dateTime = LocalDateTime.of(2024, 1, 1, 10, 0)
                    val withDuration = dateTime.plus(Duration.ofHours(24))
                    val withPeriod = dateTime.plus(Period.ofDays(1))

                    println("DateTime: ${'$'}dateTime")
                    println("Plus 24 hours (Duration): ${'$'}withDuration")
                    println("Plus 1 day (Period): ${'$'}withPeriod")
                    // Note: Usually the same, but can differ with daylight saving time
                }
            """.trimIndent(),
            executable = true
        )
    }
)
