package com.motycka.edu.content.topics.testing

import kotlinx.html.*
import com.motycka.edu.model.Topic
import com.motycka.edu.model.Slide
import com.motycka.edu.model.kotlinPlayground
import com.motycka.edu.model.inlineCode
import com.motycka.edu.model.highlight

object LoggingTopic : Topic(
    title = "Logging",
    slides = listOf(
        LoggingOverviewSlide,
        LoggingLevelsSlide,
        LoggingSetupExerciseSlide,
        LoggingConfigurationExerciseSlide
    )
)

object LoggingOverviewSlide : Slide(
    header = "Logging",
    summary = {
        +"Logging is an important aspect of software quality. "
        +"It allows us to monitor the behavior of the software while it is running in real world conditions, "
        +"and to diagnose possible problems."
    },
    content = {
        p {
            +"Several logging frameworks are available in Java, such as "
            strong { +"Log4j" }
            +", "
            strong { +"Logback" }
            +" and "
            strong { +"java.common.logging" }
            +"."
        }
        p {
            +"One of the popular Kotlin-specific logging frameworks is "
            strong { +"Kotlin-logging" }
            +"."
        }
        kotlinPlayground(
            code = """
                import mu.KLogging

                object TemperatureConverter: KLogging() {

                    fun toCelsius(fahrenheit: Double): Double {
                        logger.info("Converting ${'$'}fahrenheit Fahrenheit to Celsius")
                        return (fahrenheit - 32) * 5 / 9
                    }

                    fun toFahrenheit(celsius: Double): Double {
                        logger.info("Converting ${'$'}celsius Celsius to Fahrenheit")
                        return celsius * 9 / 5 + 32
                    }

                }
            """.trimIndent(),
            executable = false
        )
    }
)

object LoggingLevelsSlide : Slide(
    header = "Logging Levels",
    summary = {
        +"Each logging framework has a set of logging levels that can be used to control the amount of information that is logged."
    },
    content = {
        p {
            +"There are several logging levels, such as "
            strong { +"TRACE" }
            +", "
            strong { +"DEBUG" }
            +", "
            strong { +"INFO" }
            +", "
            strong { +"WARN" }
            +", "
            strong { +"ERROR" }
            +" and "
            strong { +"FATAL" }
            +"."
        }
        p {
            +"By setting the logging level, you can control the amount of information that is logged. "
            +"For example, if you set the logging level to "
            strong { +"INFO" }
            +", only messages with level "
            strong { +"INFO" }
            +" and higher will be logged."
        }
    }
)

object LoggingSetupExerciseSlide : Slide(
    header = "Exercise",
    summary = {
        +"Setup logging for your project."
    },
    content = {
        p {
            +"Add a Gradle dependency for "
            strong { +"Kotlin-logging" }
            +" and use it in your project by adding "
            +"the following to the "
            strong { +"build.gradle.kts" }
            +" file dependencies section, so it might look like:"
        }
        kotlinPlayground(
            code = """
                dependencies {
                    implementation("org.slf4j:slf4j-api:2.0.7")
                    implementation("ch.qos.logback:logback-classic:1.4.11")
                    implementation("io.github.oshai:kotlin-logging-jvm:7.0.3")
                    testImplementation(kotlin("test"))
                }
            """.trimIndent(),
            executable = false
        )
        p {
            +"In your code, you add a logger by adding "
            inlineCode("KotlinLogging.logger { }")
            +" and use it by calling"
            br()
            inlineCode("logger.info { \"info message\" }")
            +","
            br()
            inlineCode("logger.debug { \"debug message\" }")
            +","
            br()
            inlineCode("logger.error { \"error message\" }")
            +", "
            br()
            +"etc."
        }
        kotlinPlayground(
            code = """
                import io.github.oshai.kotlinlogging.KotlinLogging

                private val logger = KotlinLogging.logger { }

                fun main() {
                    logger.info { "Hello, World!" }
                }
            """.trimIndent(),
            executable = true
        )
    }
)

object LoggingConfigurationExerciseSlide : Slide(
    header = "Exercise",
    summary = {
        +"Configure logging levels and appenders."
    },
    content = {
        p {
            +"Include this file in your "
            strong { +"src/main/resources" }
            +" folder."
        }
        kotlinPlayground(
            code = """
                <?xml version="1.0" encoding="UTF-8"?>
                    <configuration>
                        <statusListener class="ch.qos.logback.core.status.OnConsoleStatusListener" />

                        <appender name="STDOUT" class="ch.qos.logback.core.ConsoleAppender">
                            <encoder>
                                <pattern>%d{HH:mm:ss} %highlight(%-5level) [%thread] %cyan(%logger{1}) - %msg%n</pattern>
                            </encoder>
                            <filter class="ch.qos.logback.classic.filter.ThresholdFilter">
                                <level>INFO</level>
                            </filter>
                        </appender>
                        <root level="TRACE">
                            <appender-ref ref="STDOUT" />
                        </root>
                        <logger name="*" level="DEBUG"/>
                    </configuration>
            """.trimIndent(),
            executable = false
        )
    }
)
