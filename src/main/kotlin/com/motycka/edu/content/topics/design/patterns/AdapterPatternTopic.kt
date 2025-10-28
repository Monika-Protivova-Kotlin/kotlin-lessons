package com.motycka.edu.content.topics.design.patterns

import com.motycka.edu.model.Slide
import com.motycka.edu.model.Topic
import com.motycka.edu.model.highlight
import com.motycka.edu.model.inlineCode
import com.motycka.edu.model.kotlinPlayground
import kotlinx.html.*

object AdapterPatternTopic : Topic(
    title = "Adapter Pattern",
    slides = listOf(
        AdapterPatternExplanationSlide,
        AdapterPatternExampleSlide,
//        AdapterExercise1Slide,
//        AdapterExercise2Slide
    )
)

object AdapterPatternExplanationSlide : Slide(
    header = "Adapter Pattern (Structural)",
    summary = {
        +"Converts the interface of a class into another interface that clients expect, enabling incompatible interfaces to work together."
    },
    content = {
        p {
            +"The Adapter pattern acts as a bridge between two incompatible interfaces. It wraps an existing class "
            +"with a new interface to make it compatible with the client's code."
        }

        p { highlight("When to use:") }
        ul {
            li { +"Integrate legacy code with new systems" }
            li { +"Work with third-party libraries with incompatible interfaces" }
            li { +"Make classes work together that couldn't otherwise" }
        }

        p { highlight("Pros & Cons:") }
        ul {
            li { +"✓ Reuses existing code without modification" }
            li { +"✓ Separates interface conversion logic" }
            li { +"✗ Increases code complexity" }
            li { +"✗ May reduce performance due to extra layer" }
        }
    }
)

object AdapterPatternExampleSlide : Slide(
    header = "Adapter Pattern - Kotlin Implementation",
    summary = {
        +"Example: Adapting a media player with incompatible interface"
    },
    content = {
        kotlinPlayground(
            code = """
                // Target interface that client expects
                interface MediaPlayer {
                    fun play(filename: String)
                }

                // Existing class with incompatible interface
                class AdvancedMediaPlayer {
                    fun playMp4(filename: String) {
                        println("Playing MP4: ${'$'}filename")
                    }

                    fun playVlc(filename: String) {
                        println("Playing VLC: ${'$'}filename")
                    }
                }

                // Adapter makes AdvancedMediaPlayer compatible with MediaPlayer
                class MediaAdapter(private val advancedPlayer: AdvancedMediaPlayer) : MediaPlayer {
                    override fun play(filename: String) {
                        when {
                            filename.endsWith(".mp4") -> advancedPlayer.playMp4(filename)
                            filename.endsWith(".vlc") -> advancedPlayer.playVlc(filename)
                            else -> println("Unsupported format")
                        }
                    }
                }

                fun main() {
                    val player: MediaPlayer = MediaAdapter(AdvancedMediaPlayer())
                    player.play("movie.mp4")
                    player.play("song.vlc")
                }
            """.trimMargin(),
            executable = true
        )
    }
)

object AdapterExercise1Slide : Slide(
    header = "Exercise 1: Printer Adapter",
    summary = {
        +"Adapt a legacy printer class to work with a modern printing interface."
    },
    content = {
        p {
            +"You have an old "; inlineCode("LegacyPrinter"); +" class that doesn't match your new "; inlineCode("Printer"); +" interface. "
            +"Create an adapter to make them compatible."
        }

        p { strong { +"Requirements:" } }
        ol {
            li { +"You have this legacy class (don't modify it):" }
            kotlinPlayground(
                code = """
                    class LegacyPrinter {
                        fun printDocument(docName: String, pageCount: Int) {
                            println("Legacy: Printing '${'$'}docName' (${'$'}pageCount pages)")
                        }
                    
                        fun printInColor(docName: String, colorMode: Boolean) {
                            val mode = if (colorMode) "color" else "grayscale"
                            println("Legacy: Printing '${'$'}docName' in ${'$'}mode")
                        }
                    }
                """.trimMargin(),
                executable = false
            )
            li { +"Create this modern interface:" }
            kotlinPlayground(
                code = """
                    interface Printer {
                        fun print(document: Document)
                    }
                    
                    data class Document(
                        val name: String,
                        val pages: Int,
                        val isColor: Boolean
                    )
                """.trimMargin(),
                executable = false
            )
            li { +"Create "; inlineCode("class LegacyPrinterAdapter"); +" that implements "; inlineCode("Printer") }
            li { +"The adapter should call the appropriate legacy methods based on document properties" }
        }

        p { strong { +"Example Usage:" } }
        kotlinPlayground(
            code = """
                // Legacy and modern code provided above
                
                class LegacyPrinterAdapter(private val legacyPrinter: LegacyPrinter) : Printer {
                    // TODO: Implement adapter
                }
                
                fun main() {
                    val printer: Printer = LegacyPrinterAdapter(LegacyPrinter())
                
                    val doc1 = Document("Report.pdf", 10, false)
                    printer.print(doc1)
                
                    val doc2 = Document("Brochure.pdf", 5, true)
                    printer.print(doc2)
                }
            """.trimMargin(),
            executable = true
        )

        p { strong { +"Expected Output:" } }
        pre {
            +"""
                |Legacy: Printing 'Report.pdf' (10 pages)
                |Legacy: Printing 'Report.pdf' in grayscale
                |Legacy: Printing 'Brochure.pdf' (5 pages)
                |Legacy: Printing 'Brochure.pdf' in color
            """.trimMargin()
        }
    }
)

object AdapterExercise2Slide : Slide(
    header = "Exercise 2: Temperature Converter Adapter",
    summary = {
        +"Adapt a Fahrenheit thermometer to work with a system expecting Celsius."
    },
    content = {
        p {
            +"Your system works with Celsius, but you have a legacy thermometer that only provides Fahrenheit readings."
        }

        p { strong { +"Scenario:" } }
        kotlinPlayground(
            code = """
                // Modern interface your system uses
                interface TemperatureSensor {
                    fun getTemperature(): Double  // Returns Celsius
                    fun getUnit(): String          // Returns "C"
                }
                
                // Legacy thermometer (can't be modified)
                class FahrenheitThermometer {
                    fun readTemperature(): Double {
                        // Simulates reading temperature in Fahrenheit
                        return (0..100).random().toDouble()
                    }
                }
            """.trimMargin(),
            executable = false
        )

        p { strong { +"Your Task:" } }
        ol {
            li { +"Create "; inlineCode("class ThermometerAdapter"); +" that implements "; inlineCode("TemperatureSensor") }
            li { +"Convert Fahrenheit to Celsius using: "; inlineCode("C = (F - 32) × 5/9") }
            li { +"Test with a "; inlineCode("ClimateControl"); +" system that expects Celsius" }
        }

        p { strong { +"Example Usage:" } }
        kotlinPlayground(
            code = """
                // Interfaces and legacy code from above
                
                class ThermometerAdapter(private val thermometer: FahrenheitThermometer) : TemperatureSensor {
                    // TODO: Implement adapter
                }
                
                class ClimateControl(private val sensor: TemperatureSensor) {
                    fun displayStatus() {
                        val temp = sensor.getTemperature()
                        val unit = sensor.getUnit()
                        println("Current temperature: ${'$'}{"%.1f".format(temp)}°${'$'}unit")
                
                        when {
                            temp < 18 -> println("Status: Too cold, heating activated")
                            temp > 25 -> println("Status: Too hot, cooling activated")
                            else -> println("Status: Comfortable temperature")
                        }
                    }
                }
                
                fun main() {
                    val sensor: TemperatureSensor = ThermometerAdapter(FahrenheitThermometer())
                    val climate = ClimateControl(sensor)
                
                    repeat(3) {
                        climate.displayStatus()
                        println()
                    }
                }
            """.trimMargin(),
            executable = true
        )

        p { strong { +"Bonus Questions:" } }
        ol {
            li { +"How would you test the adapter without the actual thermometer?" }
            li { +"What if you needed to support both Celsius and Kelvin sensors?" }
            li { +"Could you use Kotlin delegation ("; inlineCode("by"); +") for this adapter?" }
        }
    }
)
