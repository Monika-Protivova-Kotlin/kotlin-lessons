package com.motycka.edu.content.topics.basics.datetime

import com.motycka.edu.model.inlineCode

import kotlinx.html.*
import com.motycka.edu.model.Topic
import com.motycka.edu.model.Slide

object DateTimeTopic : Topic(
    title = "Working with date and time",
    slides = listOf(
        DateTimeIntroSlide,
        JavaTimeSlide,
        JavaTimeClassesSlide
    )
)

object DateTimeIntroSlide : Slide(
    header = "Working with date and time",
    summary = {
        +"Using "
        inlineCode("java.time")
    },
    content = {
        p {
            +"Kotlin does provide date and time library in the SDK. "
            +"Instead, you can use Java's "
            inlineCode("java.time")
            +" package for date and time operations."
        }
        p {
            +"There is kotlinx-datetime library, which is a multiplatform library for working with dates and times in Kotlin, "
            +"but you need to add it as an extra dependency to your project."
        }
        p {
            +"For most of the cases, however, "
            inlineCode("java.time")
            +" should be sufficient."
        }
    }
)

object JavaTimeSlide : Slide(
    header = "Java Time",
    summary = {
        +"The "
        inlineCode("java.time")
        +" package contains tools to work with dates, time, intervals and durations."
    },
    content = {
        p {
            inlineCode("java.time")
            +" replaced date and time related tools from "
            inlineCode("java.util")
            +", which was not well-designed, "
            +"was cumbersome to use, did not provide good way fo handling time zones and was not thread safe (!!!)."
        }
        p {
            +"For these reasons, it was often substituted with 3rd party libraries such as "
            inlineCode("joda.time")
            +", which you may come across in older projects."
        }
        p {
            +"The current "
            inlineCode("java.time")
            +" package, however, is generally considered to be good implementation, "
            +"and you should be using it whenever you are working with "
            strong { +"Java 8" }
            +" newer."
        }
    }
)

object JavaTimeClassesSlide : Slide(
    header = "Java Time",
    summary = {
        +"Here are the tools you will find in "
        inlineCode("java.time")
        +"."
    },
    content = {
        table {
            style = "font-size: 70%"
            tbody {
                tr {
                    td { code(classes = "inline") { strong { +"Clock" } } }
                    td { +"A clock providing access to the current instant, date and time using a time-zone." }
                }
                tr {
                    td { code(classes = "inline") { strong { +"Duration" } } }
                    td { +"A time-based amount of time, such as '34.5 seconds'." }
                }
                tr {
                    td { code(classes = "inline") { strong { +"Instant" } } }
                    td { +"An instantaneous point on the time-line." }
                }
                tr {
                    td { code(classes = "inline") { strong { +"LocalDate" } } }
                    td { +"A date without a time-zone in the ISO-8601 calendar system, such as 2007-12-03." }
                }
                tr {
                    td { code(classes = "inline") { strong { +"LocalDateTime" } } }
                    td { +"A date-time without a time-zone in the ISO-8601 calendar system, such as 2007-12-03T10:15:30." }
                }
                tr {
                    td { code(classes = "inline") { strong { +"LocalTime" } } }
                    td { +"A time without a time-zone in the ISO-8601 calendar system, such as 10:15:30." }
                }
                tr {
                    td { code(classes = "inline") { strong { +"MonthDay" } } }
                    td { +"A month-day in the ISO-8601 calendar system, such as --12-03." }
                }
                tr {
                    td { code(classes = "inline") { strong { +"OffsetDateTime" } } }
                    td { +"A date-time with an offset from UTC/Greenwich in the ISO-8601 calendar system." }
                }
                tr {
                    td { code(classes = "inline") { strong { +"OffsetTime" } } }
                    td { +"A time with an offset from UTC/Greenwich in the ISO-8601 calendar system." }
                }
                tr {
                    td { code(classes = "inline") { strong { +"Period" } } }
                    td { +"A date-based amount of time in the ISO-8601 calendar system, such as '2 years, 3 months and 4 days'." }
                }
                tr {
                    td { code(classes = "inline") { strong { +"Year" } } }
                    td { +"A year in the ISO-8601 calendar system, such as 2007." }
                }
                tr {
                    td { code(classes = "inline") { strong { +"YearMonth" } } }
                    td { +"A year-month in the ISO-8601 calendar system, such as 2007-12." }
                }
                tr {
                    td { code(classes = "inline") { strong { +"ZonedDateTime" } } }
                    td { +"A date-time with a time-zone in the ISO-8601 calendar system." }
                }
                tr {
                    td { code(classes = "inline") { strong { +"ZoneId" } } }
                    td { +"A time-zone ID, such as Europe/Paris." }
                }
                tr {
                    td { code(classes = "inline") { strong { +"ZoneOffset" } } }
                    td { +"A time-zone offset from Greenwich/UTC, such as +02:00." }
                }
            }
        }
    }
)
