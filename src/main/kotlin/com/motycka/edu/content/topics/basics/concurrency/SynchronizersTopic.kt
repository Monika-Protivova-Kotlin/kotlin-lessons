package com.motycka.edu.content.topics.basics.concurrency

import com.motycka.edu.model.Slide
import com.motycka.edu.model.Topic
import com.motycka.edu.model.inlineCode
import com.motycka.edu.model.kotlinPlayground
import com.motycka.edu.model.twoColumns
import kotlinx.html.*

object SynchronizersTopic : Topic(
    title = "Synchronizers",
    slides = listOf(
        SynchronizersSlide,
        SemaphoreSlide,
        CountDownLatchSlide,
        CyclicBarrierSlide,
        PhaserSlide,
        ExchangerSlide
    )
)

object SynchronizersSlide : Slide(
    header = "Synchronizers",
    content = {
        ul {
            li {
                inlineCode("Semaphore")
                br()
                +"It controls the access to a shared resource through the use of a counter. "
                +"If the counter is greater than zero, the access is allowed, otherwise the access is denied. "
                +"This is often used to limit the number of threads that can access a particular resource."
            }
            li {
                inlineCode("CountDownLatch")
                br()
                +"It allows one or more threads to wait until a set of operations being performed in other threads completes. "
                +"Once the count is zero, all waiting threads proceed. "
                +"It's a one-time phenomenon, once the latch reaches zero it cannot be reset."
            }
            li {
                inlineCode("CyclicBarrier")
                br()
                +"It's used when multiple threads carry out different sub tasks and the output of these sub tasks need to be combined to form the final output. "
                +"It's called cyclic because it can be reused after waiting threads are released."
            }
            li {
                inlineCode("Phaser")
                br()
                +"It's more flexible than both CountDownLatch and CyclicBarrier. "
                +"It's called Phaser because it phases all the threads into stages of execution."
            }
            li {
                inlineCode("Exchanger")
                br()
                +"It's used to exchange data between two threads. It waits for both the threads to reach the exchange point. "
                +"If the threads do not appear simultaneously to exchange their objects, they'll be paused until the arrival of the other thread."
            }
        }
    }
)

object SemaphoreSlide : Slide(
    header = "Semaphore",
    content = {
        twoColumns(
            ratio = 3 to 1,
            left = {
                kotlinPlayground(
                    code = """
                import java.util.concurrent.Semaphore
                import kotlin.concurrent.thread
                
                private val semaphore = Semaphore(1)
                
                fun main() {
                    thread(name = "A") { execute(semaphore) }
                    thread(name = "B") { execute(semaphore) }
                }
                
                fun execute(semaphore: Semaphore) {
                    try {
                        semaphore.acquire()
                        println("Thread '${DOLLAR}{Thread.currentThread().name}' acquired the semaphore")
                    } catch (e: InterruptedException) {
                        Thread.currentThread().interrupt()
                        throw RuntimeException(e)
                    } finally {
                        println("Thread '${DOLLAR}{Thread.currentThread().name}' is releasing the semaphore")
                        semaphore.release()
                    }
                }
            """,
                    executable = false
                )
            },
            right = {
                pre {
                    +"""
                    Thread 'A' acquired the semaphore
                    Thread 'A' is releasing the semaphore
                    Thread 'B' acquired the semaphore
                    Thread 'B' is releasing the semaphore
                    """.trimIndent()
                }
            },
        )
    }
)

object CountDownLatchSlide : Slide(
    header = "CountDownLatch",
    content = {
        twoColumns(
            ratio = 3 to 1,
            left = {
                kotlinPlayground(
                    code = """
                            import java.util.concurrent.CountDownLatch
                            import kotlin.concurrent.thread
                            
                            private val latch = CountDownLatch(3)
                            
                            fun main() {
                            
                                thread(name = "WAITING") {
                                    println("Thread '${DOLLAR}{Thread.currentThread().name}' started")
                                    try {
                                        latch.await()
                                    } catch (e: InterruptedException) {
                                        Thread.currentThread().interrupt()
                                        throw RuntimeException(e)
                                    }
                                    println("Thread '${DOLLAR}{Thread.currentThread().name}' finished")
                                }
                            
                                thread(name = "COUNTING") {
                                    println("Thread '${DOLLAR}{Thread.currentThread().name}' started")
                                    while (latch.count > 0) {
                                        println("Thread '${DOLLAR}{Thread.currentThread().name}' counting down ${DOLLAR}{latch.count}...")
                                        latch.countDown()
                                    }
                                    println("Thread '${DOLLAR}{Thread.currentThread().name}' finished")
                                }
                            }
                        """,
                    executable = false
                )
            },
            right = {
                pre {
                    +"""
                    Thread 'COUNTING' started
                    Thread 'WAITING' started
                    Thread 'COUNTING' counting down 3...
                    Thread 'COUNTING' counting down 2...
                    Thread 'COUNTING' counting down 1...
                    Thread 'COUNTING' finished
                    Thread 'WAITING' finished
                    """.trimIndent()
                }
            },
        )
    }
)

object CyclicBarrierSlide : Slide(
    header = "CyclicBarrier",
    content = {
        twoColumns(
            ratio = 3 to 1,
            left = {
                kotlinPlayground(
                    code = """
                        import java.util.concurrent.CyclicBarrier
                        import kotlin.concurrent.thread
                        
                        private var barrier = CyclicBarrier(3) { println("Barrier reached") }
                        
                        fun main() {
                            thread(name = "A") { execute(barrier) }
                            thread(name = "B") { execute(barrier) }
                            thread(name = "C") { execute(barrier) }
                        }
                        
                        fun execute(barrier: CyclicBarrier) {
                            try {
                                println("Thread '${DOLLAR}{Thread.currentThread().name}' is waiting on the barrier")
                                barrier.await()
                                println("Thread '${DOLLAR}{Thread.currentThread().name}' has passed the barrier")
                            } catch (e: Exception) {
                                throw RuntimeException(e)
                            }
                        }
                    """,
                    executable = false
                )
            },
            right = {
                pre {
                    +"""
                    Thread 'C' is waiting on the barrier
                    Thread 'B' is waiting on the barrier
                    Thread 'A' is waiting on the barrier
                    Barrier reached
                    Thread 'A' has passed the barrier
                    Thread 'C' has passed the barrier
                    Thread 'B' has passed the barrier
                    """.trimIndent()
                }
            },
        )
    }
)

object PhaserSlide : Slide(
    header = "Phaser",
    content = {
        twoColumns(
            ratio = 3 to 1,
            left = {
                kotlinPlayground(
                    code = """
                        import java.util.concurrent.Phaser
                        import kotlin.concurrent.thread
                        
                        private val phaser = Phaser(2)
                        
                        fun main() {
                            thread(name = "PRE-PROCESSOR") { preProcessor(phaser) }
                            thread(name = "POST-PROCESSOR") { postProcessor(phaser) }
                        }
                        
                        fun postProcessor(phaser: Phaser) {
                            println("Thread '${DOLLAR}{Thread.currentThread().name}' has arrived. Waiting for others...")
                            phaser.arriveAndAwaitAdvance()
                            println("Thread '${DOLLAR}{Thread.currentThread().name}' has finished.")
                        }
                        
                        fun preProcessor(phaser: Phaser) {
                            try {
                                Thread.sleep(1000)
                            } catch (e: InterruptedException) {
                                Thread.currentThread().interrupt()
                                throw RuntimeException(e)
                            }
                            println("Thread '${DOLLAR}{Thread.currentThread().name}' has arrived.")
                            phaser.arriveAndDeregister()
                            println("Thread '${DOLLAR}{Thread.currentThread().name}' has finished.")
                        }
                    """,
                    executable = false
                )
            },
            right = {
                pre {
                    +"""
                    Thread 'POST-PROCESSOR' has arrived. Waiting for others...
                    Thread 'PRE-PROCESSOR' has arrived.
                    Thread 'PRE-PROCESSOR' has finished.
                    Thread 'POST-PROCESSOR' has finished.
                    """.trimIndent()
                }
            }
        )
    }
)

object ExchangerSlide : Slide(
    header = "Exchanger",
    content = {
        twoColumns(
            ratio = 3 to 1,
            left = {
                kotlinPlayground(
                    code = """
                        import java.util.concurrent.Exchanger
                        import kotlin.concurrent.thread
                        
                        private val exchanger = Exchanger<String>()
                        
                        fun main() {
                            thread(name = "A") { exchange(exchanger) }
                            thread(name = "B") { exchange(exchanger) }
                        }
                        
                        fun exchange(exchanger: Exchanger<String>) {
                            try {
                                val message = exchanger.exchange("Hello from ${DOLLAR}{Thread.currentThread().name}")
                                println("Thread '${DOLLAR}{Thread.currentThread().name}' received message: ${DOLLAR}message")
                            } catch (e: InterruptedException) {
                                Thread.currentThread().interrupt()
                                throw RuntimeException(e)
                            }
                        }
                    """,
                    executable = false
                )
            },
            right = {
                pre {
                    +"""
                    Thread 'A' received message: Hello from B
                    Thread 'B' received message: Hello from A
                    """.trimIndent()
                }
            }
        )
    }
)
