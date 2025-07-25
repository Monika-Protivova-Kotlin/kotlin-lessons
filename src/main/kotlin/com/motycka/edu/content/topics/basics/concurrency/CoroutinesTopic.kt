package com.motycka.edu.content.topics.basics.concurrency

import com.motycka.edu.builders.codeOrderedList
import com.motycka.edu.model.*
import kotlinx.html.*

object CoroutinesTopic : Topic(
    title = "Coroutines",
    slides = listOf(
        CoroutinesSlide,
        CoroutinesBasicsSlide,
        CoroutinesStructuredConcurrencySlide,
        CoroutinesAsyncSlide,
        CoroutineDispatchersSlide,
        CoroutineCancellationSlide,
        CoroutineErrorHandlingSlide,
        CoroutineBestPracticesSlide
    )
)

object CoroutinesSlide : Slide(
    header = "Coroutines",
    summary = { +"Coroutines are light-weight alternative to threads, which are used for asynchronous programming." },
    content = {
        p {
            +"A coroutine is an instance of a suspendable computation. "
            +"It allows for asynchronous code executions, like threads, but they are not bound to any particular thread. "
            +"A coroutine may start executing in one thread, suspend its execution and resume in another one. "
            +"This makes coroutines more efficient than threads - by design, they are non-blocking and reuse threads."
        }
        p {
            +"Coroutines run in a context of a "
            highlight("CoroutineScope")
            +", which defines the lifecycle of the coroutine. "
            +"When the scope is cancelled, all coroutines started in that scope are cancelled. "
            +"CoroutineScope can only finish when all its inner coroutines are finished."
        }
        blockQuote {
            +"Coroutines are one of the main features of Kotlin, and while working with them is straightforward, "
            +"I believe it is a topic for Kotlin Advanced course."
        }
    }
)

object CoroutinesBasicsSlide : Slide(
    header = "Coroutines Basics",
    summary = { +"There are several principles to work with coroutines." },
    content = {
        p { +"Let's explain with example:" }
        kotlinPlayground(
            code = """
                import kotlinx.coroutines.*

                fun main() = runBlocking {
                    launch {
                        delay(1000)
                        println("Kotlin Coroutines World!")
                    }
                    println("Hello")
                }
            """,
            executable = true
        )
        p {
            +"Coroutines follow a principle of "
            highlight("structured concurrency")
            +" which means that new coroutines can only be launched in a specific coroutine scope which delimits the lifetime of the coroutine."
            ul {
                li { +"It ensures that all coroutines are properly managed and not leaking outside of their scope." }
                li { +"It also ensures that any errors in the code are properly reported and are never lost." }
            }
            kotlinPlayground(
                code = """
                    suspend fun task() = coroutineScope {
                        delay(1000)
                        println("Kotlin Coroutines World!")
                    }
                """,
                executable = false
            )
        }
    }
)

object CoroutinesStructuredConcurrencySlide : Slide(
    header = "Structured Concurrency",
    summary = { +"Sequential execution of coroutines" },
    content = {
        kotlinPlayground(
            code = """
                import kotlinx.coroutines.*
                import kotlin.system.measureTimeMillis

                fun main() = runBlocking {
                    val time = measureTimeMillis {
                        val task1 = task1()
                        val task2 = task2()
                        println("Result: ${'$'}task1${'$'}task2")
                    }
                    println("It took ${'$'}time ms")
                }

                suspend fun task1(): String {
                    delay(1000)
                    return "Hello"
                }

                suspend fun task2(): String {
                    delay(2000)
                    return "Coroutines"
                }
            """,
            executable = true
        )
    }
)

object CoroutinesAsyncSlide : Slide(
    header = "Async Coroutines",
    summary = { +"Concurrent execution of coroutines" },
    content = {
        kotlinPlayground(
            code = """
                import kotlinx.coroutines.*
                import kotlin.system.measureTimeMillis

                fun main() = runBlocking {
                    val time = measureTimeMillis {
                        val task1 = async { task1() }
                        val task2 = async { task2() }
                        println("Result: ${'$'}{task1.await()}${'$'}{task2.await()}")
                    }
                    println("It took ${'$'}time ms")
                }

                suspend fun task1(): String {
                    delay(1000)
                    return "Hello"
                }

                suspend fun task2(): String {
                    delay(2000)
                    return "Coroutines"
                }
            """,
            executable = true
        )
    }
)

object CoroutineDispatchersSlide : Slide(
    header = "Coroutine Dispatchers",
    summary = { +"Coroutines are dispatched onto different threads using Dispatchers." },
    content = {
        p {
            +"The coroutine context includes a "
            highlight("CoroutineDispatcher")
            +" that determines what thread or threads the corresponding coroutine uses for its execution. "
            +"The coroutine dispatcher can confine coroutine execution to a specific thread, dispatch it to a thread pool, or let it run unconfined."
        }
        twoColumns(
            ratio = 2 to 1,
            left = {
                kotlinPlayground(
                    code = """
                            import kotlinx.coroutines.*
            
                            fun main() {
                                runBlocking {
                                    launch(Dispatchers.IO) {
                                        // I/O operations
                                        println("I/O work on thread: ${'$'}{Thread.currentThread().name}")
                                    }
                
                                    launch(Dispatchers.Default) {
                                        // CPU-intensive work
                                        println("CPU work on thread: ${'$'}{Thread.currentThread().name}")
                                    }
                                }
                            }
                        """,
                    executable = true
                )
            },
            right = {
                codeOrderedList(
                    "Dispatchers.Main" to { +" - for UI updates (Android main thread)" },
                    "Dispatchers.IO" to { +" - for I/O operations (file, network, database)" },
                    "Dispatchers.Default" to { +" - for CPU-intensive computations" },
                    "Dispatchers.Unconfined" to { +" - not confined to any specific thread" }
                )
            },
        )
    }
)

object CoroutineCancellationSlide : Slide(
    header = "Coroutine Cancellation, Timeouts and Exceptions",
    summary = { +"Handling cancellation, timeouts, and exceptions properly is crucial for building reliable coroutine-based applications." },
    content = {
        twoColumns(
            ratio = 2 to 1,
            left = {
                kotlinPlayground(
                    code = """
                            import kotlinx.coroutines.*
            
                            fun main() {
                                runBlocking {
                                    try {
                                        withTimeout(1000) {
                                            repeat(1000) { i ->
                                                println("I'm sleeping ${'$'}i ...")
                                                delay(500)
                                            }
                                        }
                                    } catch (e: TimeoutCancellationException) {
                                        println("Timed out!")
                                    }
                                }
                            }
                        """,
                    executable = true
                )
            },
            right = {
                p {
                    +"Coroutines in Kotlin are cooperative, meaning they need to actively check for cancellation and respond appropriately."
                }
                p {
                    +"Coroutines can be cancelled by calling the "
                    inlineCode("cancel()")
                    +" function on a coroutine's Job."
                }
            },
        )
    }
)

object CoroutineErrorHandlingSlide : Slide(
    header = "Coroutine Exception Handling",
    summary = { +"Handling Exceptions in Coroutines" },
    content = {
        twoColumns(
            ratio = 2 to 1,
            left = {
                kotlinPlayground(
                    code = """
                            import kotlinx.coroutines.*
            
                            fun main() {
                                runBlocking {
                                    val supervisor = SupervisorJob()
                                    val scope = CoroutineScope(coroutineContext + supervisor)
                
                                    val job1 = scope.launch {
                                        println("Job 1 started")
                                        delay(1000)
                                        throw Exception("Job 1 failed")
                                    }
                
                                    val job2 = scope.launch {
                                        println("Job 2 started")
                                        delay(2000)
                                        println("Job 2 completed")
                                    }
                
                                    job1.join()
                                    job2.join()  // Job 2 is not affected by Job 1's failure
                                }
                            }
                        """,
                    executable = true
                )
            },
            right = {
                p {
                    +"Exceptions in coroutines are handled differently depending on the coroutine builder (launch, async, etc.):"
                }
                codeOrderedList(
                    "launch" to {
                        +" - exceptions are thrown immediately and can crash the parent scope unless caught"
                    },
                    "async" to {
                        +" - exceptions are stored and thrown when "
                        inlineCode("await()")
                        +" is called"
                    }
                )
            }
        )
    }
)

object CoroutineBestPracticesSlide : Slide(
    header = "Coroutine Best Practices",
    content = {
        ol {
            li {
                +"Always use "
                inlineCode("runBlocking")
                +" in a limited scope (typically only in main functions or tests)."
            }
            li {
                +"Prefer "
                inlineCode("CoroutineScope")
                +" to manage coroutines in more complex applications."
            }
            li {
                +"Use "
                inlineCode("withContext(Dispatchers.IO)")
                +" for blocking I/O operations to prevent UI blocking."
            }
            li {
                +"Use structured concurrency principles to manage coroutine lifecycles (avoid orphaned coroutines)."
            }
        }
    }
)
