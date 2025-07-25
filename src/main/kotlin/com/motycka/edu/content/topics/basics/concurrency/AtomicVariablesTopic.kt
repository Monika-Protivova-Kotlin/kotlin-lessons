package com.motycka.edu.content.topics.basics.concurrency

import com.motycka.edu.model.Slide
import com.motycka.edu.model.Topic
import com.motycka.edu.model.highlight
import com.motycka.edu.model.inlineCode
import com.motycka.edu.model.kotlinPlayground
import kotlinx.html.*

object AtomicVariablesTopic : Topic(
    title = "Atomic Variables",
    slides = listOf(
        AtomicVariablesSlide,
        AtomicReadWriteSlide,
        AtomicCompareAndSetSlide,
        AtomicVariablesExampleSlide
    )
)

object AtomicVariablesSlide : Slide(
    header = "Atomic Variables",
    summary = {
        +"The "
        inlineCode("java.util.concurrent")
        +" package defines classes that support atomic operations on single variables. "
        +"All atomic operations are thread-safe."
    },
    content = {
        p {
            +"There are several classes in this package, for example "
            inlineCode("AtomicBoolean")
            +", "
            inlineCode("AtomicInteger")
            +", "
            inlineCode("AtomicLong")
            +", etc."
        }
        p { +"Here is what you can do with atomic variables ..." }
    }
)

object AtomicReadWriteSlide : Slide(
    header = "Atomic Variables",
    content = {
        p {
            highlight("Atomic Read and Write")
            br()
            +"You can read or write the value of atomic variables in a thread-safe manner. "
            +"When you update an atomic variable, it ensures that the new value is immediately visible to other threads."
        }
        kotlinPlayground(
            code = """
                val atomicInteger = AtomicInteger(0)
                
                atomicInteger.set(78)
                
                val value = atomicInteger.get()
            """,
            executable = false
        )
        p {
            highlight("Atomic Update")
            br()
            +"This allows you to atomically update the value of atomic variables. "
            +"For Atomic integers and longs, it includes methods to increment, decrement, and add a certain value atomically."
        }
        kotlinPlayground(
            code = """
                val atomicInteger = AtomicInteger(0)
                
                atomicInteger.incrementAndGet()
                atomicInteger.addAndGet(46)
            """,
            executable = false
        )
    }
)

object AtomicCompareAndSetSlide : Slide(
    header = "Atomic Variables",
    content = {
        p {
            highlight("Compare and Set/Swap (CAS)")
            br()
            +"It enables you to update the value of a variable only when it has a certain expected value. "
            +"It's a way of managing concurrency, without traditional lock-based synchronization. "
            +"For example, to atomically update a value only if it's currently equal to 10, you can use:"
        }
        kotlinPlayground(
            code = """
                val atomicInteger = AtomicInteger(10)
                
                val updated = atomicInteger.compareAndSet(10, 78)
            """,
            executable = false
        )
        p {
            inlineCode("getAndIncrement")
            + ", "
            inlineCode("getAndDecrement")
            + ", "
            inlineCode("getAndAdd")
            br()
            +"These are atomic operations that atomically increment, decrement, or add the value and return the old value."
        }
        kotlinPlayground(
            code = """
            val atomicInteger = AtomicInteger(0)

            val oldValue = atomicInteger.getAndIncrement()
            """,
            executable = false
        )
    }
)

object AtomicVariablesExampleSlide : Slide(
    header = "Atomic Variables",
    content = {
        kotlinPlayground(
            code = """
                import java.util.concurrent.atomic.AtomicInteger
                import kotlin.concurrent.thread
                
                private val counter = AtomicInteger(10)
                
                fun main() {
                
                    thread(name = "thread-1") {
                        while (counter.getAndDecrement() > 0) {
                            println("Hello from '" + Thread.currentThread().name + "' thread. Counter = ${DOLLAR}{counter.get()}");
                        }
                    }
                
                    thread(name = "thread-2") {
                        while (counter.getAndDecrement() > 0) {
                            println("Hello from '" + Thread.currentThread().name + "' thread. Counter = ${DOLLAR}{counter.get()}");
                        }
                    }
                }
            """,
            executable = true
        )
    }
)
