package com.motycka.edu.content.topics.basics.concurrency

import com.motycka.edu.model.Slide
import com.motycka.edu.model.Slide.Companion.DOLLAR
import com.motycka.edu.model.Topic
import com.motycka.edu.model.highlight
import com.motycka.edu.model.kotlinPlayground
import com.motycka.edu.model.twoColumns
import kotlinx.html.p
import kotlinx.html.pre

object FuturesTopic : Topic(
    title = "Concurrent Utilities",
    slides = listOf(
        FutureExampleSlide
    )
)

object FutureExampleSlide : Slide(
    header = "Future example",
    content = {
        p {
            +"Again, "
            highlight("Future")
            +" is one of the objects you may encounter when working with Java libraries in Kotlin."
        }
        twoColumns(
            ratio = 3 to 1,
            left = {
                kotlinPlayground(
                    code = """
                        import java.util.concurrent.Executors
                        import java.util.concurrent.Future
                        
                        fun main() {
                            val messenger = Messenger()
                            val message: Future<String> = messenger.receiveMessage()
                        
                            while (!message.isDone) {
                                println("Waiting for message...")
                                try {
                                    Thread.sleep(500)
                                } catch (e: InterruptedException) {
                                    Thread.currentThread().interrupt()
                                    throw RuntimeException(e)
                                }
                            }
                        
                            try {
                                println("Received message: ${DOLLAR}{message.get()}")
                            } catch (e: Exception) {
                                throw RuntimeException(e)
                            }
                        }
                        
                        class Messenger {
                            private val executor = Executors.newSingleThreadExecutor()
                        
                            fun receiveMessage(): Future<String> {
                                return executor.submit<String> {
                                    Thread.sleep(3000)
                                    "Hello from future!"
                                }
                            }
                        }
                    """,
                    executable = false
                )
            },
            right = {
                pre {
                    +"""
                    Waiting for message...
                    Waiting for message...
                    Waiting for message...
                    Waiting for message...
                    Waiting for message...
                    Waiting for message...
                    Received message: Hello from future!
                    """.trimIndent()
                }
            }
        )
    }
)
