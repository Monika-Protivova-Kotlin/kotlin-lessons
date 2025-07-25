package com.motycka.edu.content.topics.basics.concurrency

import com.motycka.edu.builders.highlightOrderedList
import com.motycka.edu.model.Slide
import com.motycka.edu.model.Topic
import com.motycka.edu.model.inlineCode
import kotlinx.html.p

object ConcurrentUtilitiesTopic : Topic(
    title = "Concurrent Utilities",
    slides = listOf(
        ConcurrentUtilitiesSlide
    )
)

object ConcurrentUtilitiesSlide : Slide(
    header = "java.util.concurrent",
    summary = {
        +"Besides the low-level synchronization mechanisms such as "
        inlineCode("volatile")
        +" and "
        inlineCode("synchronized")
        +" keywords, "
        +"Java provides a number of classes and interfaces in the "
        inlineCode("java.util.concurrent")
        +" package to help with multithreading."
    },
    content = {
        p { +"The package includes:" }
        highlightOrderedList(
            "Atomic Variables" to {
                +"This includes classes that support atomic operations on single variables, such as"
                inlineCode("AtomicInteger")
                +", "
                inlineCode("AtomicLong")
                +" and"
                inlineCode("AtomicReference")
                +"."
            },
            "Synchronizers" to {
                +"These are higher-level synchronization constructs such as "
                inlineCode("CountDownLatch")
                +", "
                inlineCode("CyclicBarrier")
                +", and "
                inlineCode("Semaphore")
                +"."
            },
            "Concurrent Collections" to {
                +"This includes thread-safe collection classes used in place of synchronized wrappers such as ."
                inlineCode("Hashtable")
                +" or "
                inlineCode("Collections.synchronizedMap(Map)")
                +"."
            },
            "Locks" to {
                +"More advanced and flexible locking mechanism compared to intrinsic locking."
            },
            "Callable and Future" to {
                +"Callable tasks are similar to Runnable tasks but they can return a result and are capable of throwing checked exceptions. "
                +"Futures represent result of an asynchronous computation - a way to handle the results of callable tasks."
            },
            "Executor Framework" to {
                +"This is a higher-level replacement for working with threads directly. "
                +"Executors are capable of managing a pool of threads, so we do not need to manually create new threads and run tasks in an asynchronous fashion."
            }
        )
    }
)
