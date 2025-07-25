package com.motycka.edu.content.topics.basics.datetime

import kotlinx.html.*
import com.motycka.edu.model.Topic
import com.motycka.edu.model.Slide
import com.motycka.edu.model.kotlinPlayground

object ZonedDateTimeTopic : Topic(
    title = "ZonedDateTime",
    slides = listOf(
        ZonedDateTimeSlide
    )
)

object ZonedDateTimeSlide : Slide(
    header = "ZonedDateTime",
    summary = {
        strong { +"ZonedDateTime" }
        +" adds time zones ..."
    },
    content = {
        p { strong { +"Examples:" } }
        kotlinPlayground(
            code = """
                import java.time.ZonedDateTime
                import java.time.ZoneId
                import java.time.LocalDateTime
                import java.time.format.DateTimeFormatter

                fun main() {
                    // Current date and time with system default time zone
                    val zonedDateTimeNow = ZonedDateTime.now()
                    println("Now (system zone): ${'$'}zonedDateTimeNow")

                    // Specific time zone
                    val pragueTime = ZonedDateTime.now(ZoneId.of("Europe/Prague"))
                    val tokyoTime = ZonedDateTime.now(ZoneId.of("Asia/Tokyo"))
                    val newYorkTime = ZonedDateTime.now(ZoneId.of("America/New_York"))

                    println("Prague: ${'$'}pragueTime")
                    println("Tokyo: ${'$'}tokyoTime")
                    println("New York: ${'$'}newYorkTime")

                    // Converting between time zones
                    val pragueToTokyo = pragueTime.withZoneSameInstant(ZoneId.of("Asia/Tokyo"))
                    println("Prague time in Tokyo zone: ${'$'}pragueToTokyo")

                    // Creating from LocalDateTime
                    val localDateTime = LocalDateTime.of(2024, 2, 20, 14, 30)
                    val zonedFromLocal = localDateTime.atZone(ZoneId.of("Europe/London"))
                    println("Local to zoned: ${'$'}zonedFromLocal")

                    // Available time zones
                    println("\nSome available time zones:")
                    ZoneId.getAvailableZoneIds()
                        .filter { it.contains("Europe") }
                        .take(5)
                        .forEach { println("  ${'$'}it") }
                }
            """.trimIndent(),
            executable = true
        )
    }
)
