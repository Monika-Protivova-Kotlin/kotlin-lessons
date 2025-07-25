package com.motycka.edu.content.topics.basics.io

import com.motycka.edu.builders.codeOrderedList
import com.motycka.edu.builders.highlightOrderedList
import kotlinx.html.*
import com.motycka.edu.model.Topic
import com.motycka.edu.model.Slide
import com.motycka.edu.model.highlight
import com.motycka.edu.model.kotlinPlayground
import com.motycka.edu.model.inlineCode
import com.motycka.edu.model.twoColumns

object FileOperationsTopic : Topic(
    title = "Working with Files",
    slides = listOf(
        WorkingWithFilesSlide,
        ReadingFilesSlide,
        WritingFilesSlide,
        OtherFileOperationsSlide
    )
)

object WorkingWithFilesSlide : Slide(
    header = "Working with Files",
    summary = {
        +"Kotlin provides a "
        strong { +"kotlin.io" }
        +" library of functions to work with files which is actually an extension of Java libraries."
    },
    content = {
        p {
            +"This is where it gets a little messy. When we work with files in Kotlin, we need to understand a little bit about Java history."
        }
        p {
            +"Java comes with two libraries for working with files and directories:"
        }
        ul {
            li {
                highlight("java.io")
                +" "
                em { +"(legacy)" }
                br()
                +"Provides classes for reading and writing files, and working with directories. "
                +"This library contains the "
                inlineCode("File")
                +" class that represents a file or directory path."
            }
            li {
                highlight("java.nio.file")
                +" "
                em { +"(\"modern\")" }
                br()
                +"Provides a more modern way to work with files and directories and was introduced in Java 7."
            }
        }
        p {
            +"Kotlin also provides its own functions for working with files in the "
            highlight("kotlin.io")
            +" package. These functions are implemented as extension functions on top of the Java libraries, "
            +"specifically the "
            inlineCode("File")
            +" class."
        }
    }
)

object ReadingFilesSlide : Slide(
    header = "Reading Files",
    summary = {
        +"Some of the "
        highlight("kotlin.io")
        +" functions to read files are ..."
    },
    content = {
        twoColumns(
            ratio = 2 to 1,
            right = {
                codeOrderedList(
                    "readText" to { +"Reads the entire file as a String." },
                    "readLines" to { +"Reads the file as a list of lines." },
                    "readBytes" to { +"Reads the entire file as a byte array." },
                    "forEachLine" to { +"Iterates through each line of the file." }

                )
            },
            left = {
                kotlinPlayground(
                    code = """
                import java.io.File

                fun main() {
                    val file = File("example.txt")

                    // Reading entire file as text
                    try {
                        val content = file.readText()
                        println("File content: ${DOLLAR}content")
                    } catch (e: Exception) {
                        println("Error reading file: ${DOLLAR}{e.message}")
                    }

                    // Reading lines
                    try {
                        val lines = file.readLines()
                        lines.forEachIndexed { index, line ->
                            println("Line ${DOLLAR}index: ${DOLLAR}line")
                        }
                    } catch (e: Exception) {
                        println("Error reading lines: ${DOLLAR}{e.message}")
                    }

                    // Processing line by line
                    try {
                        file.forEachLine { line ->
                            println("Processing: ${DOLLAR}line")
                        }
                    } catch (e: Exception) {
                        println("Error processing lines: ${DOLLAR}{e.message}")
                    }
                }
            """,
                    executable = false
                )
            }
        )
    }
)

object WritingFilesSlide : Slide(
    header = "Writing Files",
    summary = {
        +"Some of the "
        highlight("kotlin.io")
        +" functions to write files are ..."
    },
    content = {
        twoColumns(
            ratio = 2 to 1,
            left = {
                kotlinPlayground(
                    code = """
                import java.io.File

                fun main() {
                    val file = File("output.txt")

                    // Writing text to file
                    try {
                        file.writeText("Hello, World!\nThis is a new file.")
                        println("File written successfully")
                    } catch (e: Exception) {
                        println("Error writing file: ${DOLLAR}{e.message}")
                    }

                    // Appending text to file
                    try {
                        file.appendText("\nAppended line.")
                        println("Text appended successfully")
                    } catch (e: Exception) {
                        println("Error appending to file: ${DOLLAR}{e.message}")
                    }

                    // Using PrintWriter
                    try {
                        file.printWriter().use { writer ->
                            writer.println("Line 1")
                            writer.println("Line 2")
                            writer.println("Line 3")
                        }
                        println("File written with PrintWriter")
                    } catch (e: Exception) {
                        println("Error with PrintWriter: ${DOLLAR}{e.message}")
                    }
                }
            """,
                    executable = false
                )
            },
            right = {
                codeOrderedList(
                    "writeText" to { +"Writes the specified text to the file." },
                    "writeBytes" to { +"Writes the specified byte array to the file." },
                    "appendText" to { +"Appends text to the file." },
                    "printWriter" to { +"Returns a PrintWriter for writing to the file." }
                )
            }
        )
    }
)

object OtherFileOperationsSlide : Slide(
    header = "Other File Operations",
    summary = {
        +"Other file operations Kotlin makes available for us include ..."
    },
    content = {
        twoColumns(
            left = {
                kotlinPlayground(
                    code = """
                        import java.io.File
                        import java.util.Date
        
                        fun main() {
                            val file = File("example.txt")
        
                            println("File exists: ${DOLLAR}{file.exists()}")
                            println("Absolute path: ${DOLLAR}{file.absolutePath}")
                            println("Is file: ${DOLLAR}{file.isFile}")
                            println("Is directory: ${DOLLAR}{file.isDirectory}")
                            println("Can read: ${DOLLAR}{file.canRead()}")
                            println("Can write: ${DOLLAR}{file.canWrite()}")
                            println("Can execute: ${DOLLAR}{file.canExecute()}")
        
                            if (file.exists()) {
                                println("Last modified: ${DOLLAR}{Date(file.lastModified())}")
                                println("File size: ${DOLLAR}{file.length()} bytes")
                            }
        
                            // Creating directories
                            val directory = File("new-directory")
                            if (!directory.exists()) {
                                val created = directory.mkdir()
                                println("Directory created: ${DOLLAR}created")
                            }
        
                            // Listing files in directory
                            val currentDir = File(".")
                            currentDir.listFiles()?.forEach { child ->
                                println("${DOLLAR}{if (child.isDirectory) "[DIR]" else "[FILE]"} ${DOLLAR}{child.name}")
                            }
                        }
                    """,
                    executable = false
                )
            },
            right = {
                codeOrderedList(
                    "exists" to { +" - "; em { +"Checks if the file exists." } },
                    "absolutePath" to { +" - "; em { +"Returns the absolute path of the file." } },
                    "canonicalPath" to { +" - "; em { +"Returns the canonical path of the file." } },
                    "isFile" to { +" - "; em { +"Checks if the file is a regular file." } },
                    "isDirectory" to { +" - "; em { +"Checks if the file is a directory." } },
                    "canRead" to { +" - "; em { +"Checks if has read permissions." } },
                    "canWrite" to { +" - "; em { +"Checks if the file has write permissions." } },
                    "canExecute" to { +" - "; em { +"Checks if the file execute permissions." } },
                    "lastModified" to { +" - "; em { +"Returns the time the file was last modified." } }
                )
            }
        )
    }
)
