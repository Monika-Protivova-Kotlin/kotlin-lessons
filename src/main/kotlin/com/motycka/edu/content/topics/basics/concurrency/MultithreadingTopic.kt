package com.motycka.edu.content.topics.basics.concurrency

import com.motycka.edu.model.Slide
import com.motycka.edu.model.Topic
import com.motycka.edu.model.kotlinPlayground
import com.motycka.edu.model.highlight
import com.motycka.edu.model.inlineCode
import com.motycka.edu.model.twoColumns
import kotlinx.html.*

object MultithreadingTopic : Topic(
    title = "Multithreading",
    slides = listOf(
        MultithreadingSlide,
        ThreadJavaWaySlide,
        ThreadMethodsSlide,
        ThreadKotlinWaySlide,
        RunnableSlide,
        MemorySynchronizationSlide,
        MemorySynchronizationWarningSlide,
        MemorySynchronizationNotSafeSlide,
        MemorySynchronizationSafeWithAnnotationSlide,
        MemorySynchronizationSafeWithBlockSlide
    )
)

object MultithreadingSlide : Slide(
    header = "Multithreading",
    summary = {
        +"Multithreading allows execution of multiple parts of a program concurrently, "
        +"using lightweight processes called threads. It aims to maximize the use of CPU time."
    },
    content = {
        p {
            +"Generally, there is always at least one thread running in a Java program - the "
            highlight("main")
            +" thread."
        }
        p {
            +"To create a new thread, need to create new instance "
            inlineCode("Thread")
            +" class. To start a thread, need to call its "
            inlineCode("start()")
            +" method of the "
            inlineCode("Thread")
            +" class instance."
        }
        p {
            +"Another way to create a thread is by implementing the "
            inlineCode("Runnable")
            +" interface and passing an instance of it to a new thread."
        }
        p {
            +"Alternatively, we can use the "
            highlight("Executor Framework")
            +" provided by "
            inlineCode("java.util.concurrent")
            +"."
        }
        p {
            +"Synchronization in Java / Kotlin is an important feature that allows only one thread to have access to the shared resource."
        }
    }
)

object ThreadJavaWaySlide : Slide(
    header = "Thread",
    summary = { +"The Java way." },
    content = {
        p {
            inlineCode("Thread")
            +" is a class in Java that allows you to create and manage threads. "
            +"You can create thread directly, or by extending the "
            inlineCode("Thread")
            +" class and overriding the "
            inlineCode("run()")
            +" method."
        }
        kotlinPlayground(
            code = """
                fun main() {
                    val thread = Thread {
                        println("Hello from '${DOLLAR}{Thread.currentThread().name}' thread")
                    }

                    thread.start()

                    println("Hello from '" + Thread.currentThread().name + "' thread")
                }
            """
        )
        kotlinPlayground(
            code = """
                fun main() {
                    MyThread().start()
                }

                class MyThread : Thread() {
                    override fun run() {
                        println("Hello from '${DOLLAR}{currentThread().name}' thread");
                    }
                }
            """
        )
    }
)

object ThreadMethodsSlide : Slide(
    header = "Thread",
    summary = { +"The Java way." },
    content = {
        p {
            +"Thread is started by calling the "
            inlineCode("start()")
            +" method. When the "
            inlineCode("start()")
            +" method is called, the JVM calls the "
            inlineCode("run()")
            +" method of the thread."
        }
        p {
            +"When the "
            inlineCode("run()")
            +" method finishes, the thread is considered to be terminated."
        }
        p {
            +"If at any time you want to stop a thread, you can call the "
            inlineCode("interrupt()")
            +" method."
        }
        p {
            +"To wait for a thread to finish, you can call the "
            inlineCode("join()")
            +" method. However, beware that this will block the current thread until the other thread finishes."
        }
    }
)

object ThreadKotlinWaySlide : Slide(
    header = "Thread",
    summary = { +"The Kotlin way" },
    content = {
        p {
            +"While we use Java "
            inlineCode("Thread")
            +" class in Kotlin, as usual, Kotlin provides a more concise way to create and manage threads."
        }
        p {
            +"You can use the "
            inlineCode("thread")
            +" function to create a new thread."
        }
        p {
            +"The function simplifies the creation of a new thread by allowing you to configure the thread properties in a more concise way. "
            +"The arguments available are:"
        }
        ul {
            li {
                inlineCode("start: Boolean = true")
                +" - start the thread immediately"
            }
            li {
                inlineCode("isDaemon: Boolean = false")
                +" - create a daemon thread"
            }
            li {
                inlineCode("contextClassLoader: ClassLoader? = null,")
                +" - class loader to use for loading classes and resources"
            }
            li {
                inlineCode("name: String? = null,")
                +" - name of the thread"
            }
            li {
                inlineCode("priority: Int = -1,")
                +" - priority of the thread"
            }
            li {
                inlineCode("block: () -> Unit")
                +" - code to be executed in the thread"
            }
        }
        kotlinPlayground(
            code = """
                import kotlin.concurrent.thread
                
                fun main() {
                    thread(start = true, isDaemon = true, name = "my-thread", priority = 999) {
                        println("Hello from '${DOLLAR}{Thread.currentThread().name}' thread")
                    }
                
                    println("Hello from '" + Thread.currentThread().name + "' thread")
                }
            """
        )
    }
)

object RunnableSlide : Slide(
    header = "Runnable",
    summary = {
        inlineCode("Runnable")
        +" is an interface in Java that allows you to create and manage threads."
    },
    content = {
        p {
            +"To use "
            inlineCode("Runnable")
            +", you pass an instance of "
            inlineCode("Runnable")
            +" to a new "
            inlineCode("Thread")
            +"."
        }
        kotlinPlayground(
            code = """
                class MyRunnable : Runnable {
                    override fun run() {
                        println("Hello from '${DOLLAR}{Thread.currentThread().name}' thread")
                    }
                }
                
                fun main() {
                    val thread = Thread(MyRunnable(), "Runner 1")
                
                    println("Starting thread '${DOLLAR}{thread.name}'")
                
                    thread.start()
                
                    try {
                        // this will block the main thread until the other thread finishes
                        thread.join()
                    } catch (e: InterruptedException) {
                        Thread.currentThread().interrupt()
                        throw RuntimeException(e)
                    }
                
                    println("Thread '${DOLLAR}{thread.name}' finished")
                }
            """,
            executable = false
        )
    }
)

object MemorySynchronizationSlide : Slide(
    header = "Memory Synchronization",
    summary = { +"Memory synchronization ensures that the changes made by one thread to the shared data are visible to other threads." },
    content = {
        ul {
            li {
                inlineCode("@Volatile")
                twoColumns(
                    left = {
                        p {
                            +"Used to mark a field as volatile to the JVM. "
                            +"It ensures that all reads of a volatile variable are read directly from main memory, "
                            +"and all writes to a volatile variable are written directly to main memory. "
                            +"By itself, volatile does not provide atomicity, but it ensures visibility."
                        }
                    },
                    right = {
                        kotlinPlayground(
                            code = """
                                
                                @Volatile
                                private var flag: Boolean = true
                                
                            """,
                            executable = false
                        )
                    }
                )
            }
            li {
                inlineCode("@Synchronized")
                twoColumns(
                    left = {
                        p {
                            +"If "
                            highlight("method")
                            +" is synchronized, only one thread can access the method or block at a time. "
                            +"This ensures that the changes made by one thread to the shared data are visible to other threads."
                        }
                    },
                    right = {
                        kotlinPlayground(
                            code = """
                                
                                @Synchronized
                                fun someMethod() {
                                    // ...
                                }

                            """,
                            executable = false
                        )
                    },
                )
            }
            li {
                inlineCode("synchronized")
                +" block"
                br()
                p {
                    +"You can also use synchronized block to synchronize access to shared data within a block of code."
                }
                p {
                    em {
                        +"The difference between "
                        inlineCode("@Synchronized")
                        +" and "
                        inlineCode("synchronized")
                        +" block is that the former is used to synchronize a method, "
                        +"while the latter is used to synchronize a block of code. "
                        +"Synchronizing on method level can be more efficient than synchronizing on block level, especially if the code is called frequently, "
                        +"because "
                        inlineCode("synchronized")
                        +" needs to acquire and release the lock every time the method is called."
                    }
                }
            }
        }
    }
)

object MemorySynchronizationWarningSlide : Slide(
    header = "Memory Synchronization",
    content = {
        blockQuote("warning") {
            +"Remember that incorrect synchronization can lead to issues like race conditions, deadlocks or even data inconsistency. "
            +"It advised to avoid shared mutable data for threads and use thread confinement or immutability."
        }
    }
)

object MemorySynchronizationNotSafeSlide : Slide(
    header = "Memory Synchronization",
    summary = {
        +"No synchronization - NOT thread safe!"
    },
    content = {
        kotlinPlayground(
            code = """
            var sharedCounter = 0
            
            fun main() {
            
                val thread1 = Thread(::incrementCounter)
                val thread2 = Thread(::incrementCounter)
            
                thread1.start()
                thread2.start()
            
                // wait for both threads to finish
                thread1.join()
                thread2.join()
            
                println("Final Counter Value: ${DOLLAR}sharedCounter")
            }
            
            fun incrementCounter() {
                repeat(1000) {
                    sharedCounter++
                }
            }
            """,
            executable = true
        )
    }
)

object MemorySynchronizationSafeWithAnnotationSlide : Slide(
    header = "Memory Synchronization",
    summary = {
        +"With synchronization using "
        inlineCode("@Synchronized")
        +" - thread safe."
    },
    content = {
        kotlinPlayground(
            code = """
                @Volatile // doesn't ensure atomicity, but ensures visibility
                var sharedCounter = 0
                
                fun main() {
                
                    val thread1 = thread { incrementCounter() }
                    val thread2 = thread { incrementCounter() }
                
                    thread1.start()
                    thread2.start()
                
                    // wait for both threads to finish
                    thread1.join()
                    thread2.join()
                
                    println("Final Counter Value: ${DOLLAR}sharedCounter")
                }
                
                @Synchronized // ensures that only one thread can execute this function at a time, acquire lock on whole function
                fun incrementCounter() {
                    repeat(1000) {
                        sharedCounter++
                    }
                }
            """,
            executable = true
        )
    }
)

object MemorySynchronizationSafeWithBlockSlide : Slide(
    header = "Memory Synchronization",
    summary = {
        +"With synchronization with "
        inlineCode("synchronized")
        +" function - thread safe."
    },
    content = {
        kotlinPlayground(
            code = """
                @Volatile // doesn't ensure atomicity, but ensures visibility
                var sharedCounter = 0
                
                fun main() {
                
                    val thread1 = thread { incrementCounter() }
                    val thread2 = thread { incrementCounter() }
                
                    thread1.start()
                    thread2.start()
                
                    // wait for both threads to finish
                    thread1.join()
                    thread2.join()
                
                    println("Final Counter Value: ${DOLLAR}sharedCounter")
                }
                
                private val lock = Any() // because incrementCounter is a top-level function, we need and container object to lock on
                
                fun incrementCounter() {
                    repeat(1000) {
                        synchronized(lock) { // ensures atomicity, acquire lock on this object
                            sharedCounter++
                        }
                    }
                }
            """,
            executable = true
        )
    }
)
