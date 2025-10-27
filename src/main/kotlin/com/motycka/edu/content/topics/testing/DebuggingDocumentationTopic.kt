package com.motycka.edu.content.topics.testing

import kotlinx.html.*
import com.motycka.edu.model.Topic
import com.motycka.edu.model.Slide
import com.motycka.edu.model.imgByName
import com.motycka.edu.model.kotlinPlayground
import com.motycka.edu.model.twoColumns

object DebuggingDocumentationTopic : Topic(
    title = "Debugging and Documentation",
    slides = listOf(
        DebuggingSlide,
        DocumentationSlide
    )
)

object DebuggingSlide : Slide(
    header = "Debugging",
    summary = {
        +"Debugging is the process of finding and resolving defects or problems within a computer program that prevent correct operation of computer software or a system."
    },
    content = {
        p {
            strong { +"It is an essential skill of any software developer." }
        }
        p {
            +"Usually, an IDE (such as IntelliJ IDEA) will have a debugger built in, "
            +"which will allow you to "
            strong { +"step" }
            +" through your code, "
            strong { +"inspect variables" }
            +" and "
            strong { +"evaluate expressions" }
            +" to see what the program is doing, while it is executing"
        }
    },
    textAlign = "ceneter"
)

object DocumentationSlide : Slide(
    header = "Java Documentation",
    summary = {
        +"Another important aspect of software quality is documentation."
    },
    content = {
        +"In Java, we can use a tool called "
        strong { +"Javadoc" }
        +" to generate documentation from our code."
        twoColumns(
            left = {
                div {
                    style = "font-size: 80%"
                    kotlinPlayground(
                        code = """
                            object TemperatureConverter: KLogging() {

                                /**
                                 * Converts temperature value given in Fahrenheit to Celsius
                                 *
                                 * @param fahrenheit temperature value in Fahrenheit
                                 * @return temperature value in Celsius
                                 * @see [Fahrenheit](https://en.wikipedia.org/wiki/Fahrenheit)
                                 * @see [Celsius](https://en.wikipedia.org/wiki/Celsius)
                                 */
                                fun toCelsius(fahrenheit: Double): Double {
                                    logger.info("Converting ${'$'}fahrenheit Fahrenheit to Celsius")
                                    return (fahrenheit - 32) * 5 / 9
                                }

                                /**
                                 * Converts temperature value given in Celsius to Fahrenheit
                                 *
                                 * @param celsius temperature value in Celsius
                                 * @return temperature value in Fahrenheit
                                 * @see [Fahrenheit](https://en.wikipedia.org/wiki/Fahrenheit)
                                 * @see [Celsius](https://en.wikipedia.org/wiki/Celsius)
                                 */
                                fun toFahrenheit(celsius: Double): Double {
                                    logger.info("Converting ${'$'}celsius Celsius to Fahrenheit")
                                    return celsius * 9 / 5 + 32
                                }

                            }
                        """.trimIndent(),
                        executable = false
                    )
                }
            },
            right = {
                img("Javadoc", imgByName("javadoc", "png"))
            }
        )
        p {
            +"For details, see "
            a(href = "https://www.oracle.com/technical-resources/articles/java/javadoc-tool.html") { +"Javadoc Tool" }
        }
    }
)
