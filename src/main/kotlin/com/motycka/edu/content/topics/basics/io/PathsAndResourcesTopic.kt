package com.motycka.edu.content.topics.basics.io

import com.motycka.edu.model.*
import kotlinx.html.li
import kotlinx.html.p
import kotlinx.html.ul

object PathsAndResourcesTopic : Topic(
    title = "Paths and Resources",
    slides = listOf(
        PathsAndResourcesSlide
    )
)

object PathsAndResourcesSlide : Slide(
    header = "Paths and resources",
    summary = {
        +"Understanding how to access files and resources in Kotlin applications."
    },
    content = {
        p {
            +"To access a file, we need to obtain its path reference as "
            highlight("URI")
            +" (Uniform Resource Identifier), which is a string of characters used to identify a resource. You can also use "; highlight("URL"); +", which is a specific type of URI that identifies a resource on network location."
        }
        p {
            +"There are two types of paths:"
        }
        ul {
            li {
                highlight("Absolute path"); +" - A complete path to a file or directory from the root of the file system."
            }
            li {
                highlight("Relative path"); +" - A path to a file or directory relative to the current working directory."
            }
        }
        p {
            +"Resources are files that are bundled with your application, typically in the "
            inlineCode("src/main/resources")
            +" directory."
        }
        kotlinPlayground(
            code = """
                import java.io.File

                fun main() {
                    val absoluteFile = File("/path/to/file.txt") // Absolute path

                    val relativeFile = File("file.txt") // Relative path

                    // Getting current working directory
                    val currentDir = File(".")
                    println("Current directory: ${'$'}{currentDir.absolutePath}")

                    // Accessing resources
                    val resourceUrl = object {}.javaClass.getResource("/example.txt")
                    if (resourceUrl != null) {
                        val resourceFile = File(resourceUrl.toURI())
                        println("Resource file: ${'$'}{resourceFile.absolutePath}")
                    }
                }
            """,
            executable = false
        )
    }
)
