package com.motycka.edu.content.topics.basics.io

import kotlinx.html.*
import com.motycka.edu.model.Topic
import com.motycka.edu.model.Slide
import com.motycka.edu.model.kotlinPlayground
import com.motycka.edu.model.inlineCode
import com.motycka.edu.model.twoColumns

object StreamsTopic : Topic(
    title = "Input and Output Stream",
    slides = listOf(
        InputOutputStreamSlide
    )
)

object InputOutputStreamSlide : Slide(
    header = "Input and Output Stream",
    summary = {
        +"Working with InputStream and OutputStream classes."
    },
    content = {
        p {
            +"When working with programs and libraries, you may come across code that uses "
            strong { +"InputStream" }
            +" and "
            strong { +"OutputStream" }
            +" classes."
            br
            +"These classes are used to read and write data from and to a source, such as a file, network connection, or memory."
        }
        twoColumns(
            ratio = 2 to 1,
            left = {
                kotlinPlayground(
                    code = """
                        import java.io.FileInputStream
                        import java.io.FileOutputStream
                        import java.io.IOException
        
                        fun main() {
                            try {
                                val inputStream = FileInputStream("input.txt")
                                val outputStream = FileOutputStream("output.txt")
        
                                // Reading from input stream
                                val buffer = ByteArray(1024)
                                var bytesRead = inputStream.read(buffer)
        
                                while (bytesRead != -1) {
                                    // Writing to output stream
                                    outputStream.write(buffer, 0, bytesRead)
                                    bytesRead = inputStream.read(buffer)
                                }
        
                                // Always close streams
                                inputStream.close()
                                outputStream.close()
        
                                println("File copied successfully")
                            } catch (e: IOException) {
                                println("IO Error: ${'$'}{e.message}")
                            }
                        }
        
                        // Better approach using 'use' for automatic resource management
                        fun copyFileWithUse() {
                            try {
                                FileInputStream("input.txt").use { input ->
                                    FileOutputStream("output.txt").use { output ->
                                        input.copyTo(output)
                                    }
                                }
                                println("File copied using 'use'")
                            } catch (e: IOException) {
                                println("IO Error: ${'$'}{e.message}")
                            }
                        }
                    """,
                    executable = false
                )
            },
            right = {
                ul {
                    li {
                        inlineCode("InputStream")
                        +" is an abstract class that represents an input stream of bytes."
                    }
                    li {
                        inlineCode("OutputStream")
                        +" is an abstract class that represents an output stream of bytes."
                    }
                }
            }
        )
    }
)
