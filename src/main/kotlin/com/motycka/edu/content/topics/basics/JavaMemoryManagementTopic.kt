package com.motycka.edu.content.topics.basics

import com.motycka.edu.model.Slide
import com.motycka.edu.model.Topic
import com.motycka.edu.model.highlight
import com.motycka.edu.model.inlineCode
import com.motycka.edu.model.kotlinPlayground
import com.motycka.edu.model.twoColumns
import kotlinx.html.*

object JavaMemoryManagementTopic : Topic(
    title = "Java Memory Management",
    slides = listOf(
        JavaMemoryManagementSlide,
        JavaMemoryLayoutSlide,
        MetaSpaceSlide,
        HeapMemorySlide,
        StackMemorySlide,
        StackVsHeapComparisonSlide,
        MemoryAllocationExamples1Slide,
        MemoryAllocationExamples2Slide,
        MemoryAllocationExamples3Slide,
        MemoryAllocationExamples4Slide,
        GarbageCollectionSlide,
        GarbageCollectionGenerationsSlide,
        GarbageCollectionStrategiesSlide
    )
)

object JavaMemoryManagementSlide : Slide(
    header = "Java Memory Management",
    summary = { +"Java memory management is to a large extent automatic." },
    content = {
        p {
            +"Automatic memory management was one of the key features of Java when it was first introduced."
        }
        p {
            +"As your program runs, the JVM automatically allocates and de-allocates memory for variables, "
            +"objects, methods and other data structures."
        }
        p {
            +"The deallocation process is known as "
            span(classes = "highlight") { +"garbage collection (GC)" }
            +", and the process responsible for it is called the "
            span(classes = "highlight") { +"garbage collector" }
            +"."
        }
        p {
            span(classes = "highlight") { +"GC" }
            +" helps us to avoid memory leaks and optimize memory usage."
        }
        p {
            +"However, some memory leaks can still occur due to programming errors. "
            em { +"(By not releasing references to objects that are no longer needed.)" }
        }
    }
)

object JavaMemoryLayoutSlide : Slide(
    header = "Java Memory Layout",
    summary = {
        +"There are several types of memory spaces in Java, each serving a different purpose."
    },
    content = {
        pre {
            style = "font-size: 60%; line-height: 1.3em;"
            +"""
+---------------------------------------------+
|                  JVM Memory                 |
+---------------------------------------------+
|           Method Area (MetaSpace)           | ← Stores class metadata, method info, static variables
|                                             |   - Loaded class definitions
|                                             |   - Method and field descriptors
|                                             |   - Runtime constant pool
|                                             |   - Shared across all threads
+---------------------------------------------+
|                   Heap                      | ← Stores all class instances and arrays
|                                             |   - Object fields and values
|                                             |   - Managed by Garbage Collector
|                                             |   - Shared across all threads
+---------------------------------------------+
|             Stack (one per thread)          | ← Stores frames for active method calls
|                                             |   - Method arguments and return values
|                                             |   - Local variables and references
|                                             |   - Each thread has its own stack
+---------------------------------------------+
|          Native Method Stack                | ← Used by native (non-Java) methods
|                                             |   - Platform-specific, unmanaged by JVM GC
+---------------------------------------------+
|         Program Counter (PC) Register       | ← Tracks JVM bytecode instruction per thread
|                                             |   - One per thread
|                                             |   - Points to the current instruction
+---------------------------------------------+
            """
        }
    }
)

object MetaSpaceSlide : Slide(
    header = "MetaSpace",
    summary = {
        +"MetaSpace is a memory area in the JVM that stores class metadata. "
        +"It is used to store information about classes, methods, and fields."
    },
    content = {
        p { +"MetaSpace memory is allocated when:" }
        ul {
            li { +"Classes are loaded by the JVM when they are referenced in the code." }
            li { +"Methods are compiled by the JVM when they are called for the first time." }
            li { +"Static variables are initialized when the class is loaded." }
            li { +"..." }
        }
        p { +"MetaSpace is garbage collected just like the heap memory." }
        p { +"The size of MetaSpace can be controlled using the following JVM options:" }
        ul {
            li { inlineCode("-XX:MetaspaceSize"); +" - sets the initial size of MetaSpace" }
            li { inlineCode("-XX:MaxMetaspaceSize"); +" - sets the maximum size of MetaSpace" }
        }
        p {
            small {
                +"MetaSpace is a replacement for the Permanent Generation (PermGen) space in older JVM versions. "
                +"It is allocated in native memory, which means it is not limited by the heap size."
            }
        }
    }
)

object HeapMemorySlide : Slide(
    header = "Heap memory",
    summary = {
        +"Heap memory is where all objects (instances of classes) are stored."
        br()
        +"Each time an object is created, memory is allocated on the heap at runtime."
    },
    content = {
        p {
            +"Heap memory is shared among all threads, and it is the memory area that is maintained by the garbage collector."
        }
        p {
            +"You can control the size of the heap using the -Xms and -Xmx JVM options."
        }
        ul {
            li {
                inlineCode("-Xms")
                +" sets the initial size of the heap"
            }
            li {
                inlineCode("-Xmx")
                +" sets the maximum size of the heap"
            }
        }
    }
)

object StackMemorySlide : Slide(
    header = "Stack memory",
    summary = {
        +"Stack memory is used to store method frames, local variables, and primitive values."
    },
    content = {
        twoColumns(
            left = {
                ul {
                    li {
                        strong { +"Stack memory is used to store:" }
                        ul {
                            li { +"Method frames (call stack)" }
                            li { +"Local variables and parameters of methods" }
                            li { +"Primitive values (int, float, etc.)" }
                            li { +"References to objects in heap space" }
                        }
                    }
                    li {
                        strong { +"Properties of stack are:" }
                        ul {
                            li { +"Fast access" }
                            li { +"Automatically managed (cleared when function ends)" }
                            li { +"Exists per thread" }
                            li { +"Cannot grow too big (overflow → StackOverflowError)" }
                        }
                    }
                    li { +"Stack memory is allocated when a method is called and deallocated when the method returns." }
                    li { +"Each thread in Java has its own JVM stack which is created at the same time as the thread." }
                    li { +"Stack memory has a specific size and is not directly controlled by the programmer." }
                }
            },
            right = {
                +"Example of Method frame:"
                kotlinPlayground(
                    code = """
                        fun sum(a: Int, b: Int): Int {
                            val result = a + b
                            return result
                        }
                    """,
                    executable = false
                )
                pre {
                    style = "font-size: 90%; line-height: 1.2em;"
                    +"""
                        Method Frame (for sum):
                        +-----------------------+
                        | return address        |
                        | a = 3                 |
                        | b = 4                 |
                        | result = 7            |
                        | operand stack         |
                        +-----------------------+
                    """.trimIndent()
                }
            },
        )
    }
)

object StackVsHeapComparisonSlide : Slide(
    header = "Difference Between Stack and Heap",
    content = {
        twoColumns(
            left = {
                h4 { +"Stack" }
                ul {
                    li { +"Stores method frames, local variables, and primitive values" }
                    li { +"Memory is allocated and deallocated automatically" }
                    li { +"Fast access, but limited size" }
                    li { +"Each thread has its own stack" }
                    li { +"Used for method calls and local variables" }
                }
            },
            right = {
                h4 { +"Heap" }
                ul {
                    li { +"Stores objects and arrays" }
                    li { +"Memory is managed by the garbage collector" }
                    li { +"Slower access, but larger size" }
                    li { +"Shared among all threads" }
                    li { +"Used for dynamic memory allocation" }
                }
            },
        )
    }
)

object MemoryAllocationExamples1Slide : Slide(
    header = "Memory Allocation Examples",
    content = {
        p {
            highlight("1. Primitive Types → Stack (when local)")
            kotlinPlayground(
                code = """
                    fun main() {
                        val a = 42      // Int – primitive value, allocated on stack
                        val b = true    // Boolean – also on stack
                    }
                    """,
                executable = false
            )
            ul {
                style = "font-size: 80%;"
                li { +"These variables are local to the main function → stored in the stack frame." }
                li { +"No object instantiation → no heap allocation." }
            }
        }

        p {
            highlight("2. Reference Types (Classes) → Heap")
            kotlinPlayground(
                code = """
                    class User(val name: String)
                    
                    fun main() {
                        val user = User("Alice") // user is a reference on stack, actual User object on heap
                    }
                    """,
                executable = false
            )
            ul {
                style = "font-size: 80%;"
                li { +"List reference → stack" }
                li { +"Actual List and its contents → heap" }
                li { +"Returned to caller → remains alive after function ends" }
            }
        }
    }
)

object MemoryAllocationExamples2Slide : Slide(
    header = "Memory Allocation Examples",
    content = {
        p {
            highlight("3. Function-local Objects → Heap")
            kotlinPlayground(
                code = """
                    fun createList(): List<Int> {
                        val list = listOf(1, 2, 3) // list is a reference on stack, actual List on heap
                        return list
                    }
                    """,
                executable = false
            )
            ul {
                style = "font-size: 80%;"
                li { +"list reference → stack" }
                li { +"actual List and its contents → heap" }
                li { +"returned to caller → remains alive after function ends" }
            }
        }
        p {
            highlight("4. Objects Captured in Lambdas → Heap")
            kotlinPlayground(
                code = """
                    fun main() {
                        val prefix = "Item " // captured in lambda → stored on heap
                        val printer = { i: Int -> println(prefix + i) }
                    
                        printer(5)
                    }
                    """,
                executable = false
            )
            ul {
                style = "font-size: 80%;"
                li { +"Lambdas that capture variables allocate a closure object on the heap." }
                li { +"If prefix were not captured, lambda could be compiled more efficiently." }
            }
        }
    }
)

object MemoryAllocationExamples3Slide : Slide(
    header = "Memory Allocation Examples",
    content = {
        p {
            highlight("5. Top-Level / object Singleton → Heap (once)")
            kotlinPlayground(
                code = """
                    object Logger {
                        fun log(msg: String) {
                            println(msg)
                        }
                    }
                    """,
                executable = false
            )
            ul {
                style = "font-size: 80%;"
                li { +"The Logger object is allocated once and lives in heap/meta space." }
                li { +"Static-like structure with JVM guarantees." }
            }
        }

        p {
            highlight("6. Array Allocation → Heap")
            kotlinPlayground(
                code = """
                    val nums = IntArray(5) // array is always allocated on heap
                    """,
                executable = false
            )
            ul {
                style = "font-size: 80%;"
                li { +"Arrays, even of primitives, live on heap." }
                li { +"Elements of primitive type (Int) are stored inline; objects would be references." }
            }
        }
    }
)

object MemoryAllocationExamples4Slide : Slide(
    header = "Memory Allocation Examples",
    content = {
        p {
            highlight("7. Inline Functions and Reified Types → Reduced Allocation")
            kotlinPlayground(
                code = """
                    inline fun <reified T> printType() {
                        println(T::class.java.name)
                    }
                    """,
                executable = false
            )
            ul {
                style = "font-size: 80%;"
                li { +"Inlined at call site → may reduce allocation." }
                li { +"No closure or lambda object if no capturing → no heap cost." }
            }
        }
        p {
            highlight("8. Example of memory leak due to static reference")
            kotlinPlayground(
                code = """
                object Leaky {
                    var cache: MutableList<Any> = mutableListOf()
                }
                
                fun main() {
                    repeat(1_000_000) {
                        Leaky.cache.add(ByteArray(1024 * 1024)) // 1MB
                    }
                }
                """,
                executable = false
            )
            ul {
                style = "font-size: 80%;"
                li { +"Static reference in Leaky object → cache lives as long as program." }
            }
        }
    }
)

object GarbageCollectionSlide : Slide(
    header = "Garbage Collection",
    summary = { +"Garbage Collection (GC) is a process of automatically reclaiming memory." },
    content = {
        p {
            +"The Garbage Collector automatically frees up heap space memory allocations that are no longer "
            +"referenced by any running part of the program."
        }
        p {
            +"The process of GC is not predictable, and the programmer can't force garbage collection. "
            +"System.gc() can be called as a hint to JVM for garbage collection, but it is not guaranteed that it will be performed."
        }
        p {
            +"To make the garbage collection process more efficient, the heap is divided into generations."
        }
        ul {
            li {
                span(classes = "highlight") { +"Young Generation (Eden)" }
                br()
            }
            li {
                span(classes = "highlight") { +"Old Generation (Tenured)" }
                br()
            }
            li {
                span(classes = "highlight") { +"Permanent Generation (Meta Space)" }
                br()
            }
        }
    }
)

object GarbageCollectionGenerationsSlide : Slide(
    header = "Garbage Collection Generations",
    summary = {
        +"Java uses generational garbage collection strategy that categorizes objects by age. "
        +"This is because performing GC on the entire heap would be inefficient. "
        +"Most objects in java are short-lived, therefore, there can be more frequent GC events for those."
    },
    content = {
        ul {
            li {
                span(classes = "highlight") { +"Young Generation (Eden)" }
                br()
                +"This is where all new objects are created. It can be further divided into Eden space and Survivor spaces (FromSpace and ToSpace)."
                ul {
                    li { +"When it fills up, a Minor GC event occurs." }
                    li { +"Objects that are evaluated as dead or alive." }
                    li { +"Dead objects are removed, and the memory is compacted." }
                    li { +"If an object survives for a given number minor GC cycles, it is promoted to the Old Generation." }
                }
            }
            li {
                span(classes = "highlight") { +"Old Generation (Tenured)" }
                br()
                +"This contains objects that have survived the garbage collection from the Young Generation."
            }
            li {
                span(classes = "highlight") { +"Permanent Generation (Meta Space)" }
                br()
                +"This is used to store metadata about classes and methods. "
                +"It's garbage collected just like the other generations but usually at a slower rate."
            }
        }
    }
)

object GarbageCollectionStrategiesSlide : Slide(
    header = "Garbage Collection Strategies",
    summary = {
        +"There is a number of GC strategies that can be used in Java. "
        +"Each strategy has its own advantages and disadvantages, and is suitable for different types of applications."
    },
    content = {
        ul {
            li { highlight("Serial Collector"); +" ("; inlineCode("-XX:+UseSerialGC"); +")" }
            li { highlight("Parallel Collector"); +" ("; inlineCode("-XX:+UseParallelGC"); +" and "; inlineCode("-XX:+UseParallelOldGC"); +")" }
            li { highlight("Z Garbage Collector (ZGC)"); +" ("; inlineCode("-XX:+UseZGC"); +")" }
            li { highlight("Concurrent Mark Sweep (CMS) Collector"); +" ("; inlineCode("-XX:+UseConcMarkSweepGC"); +")" }
            li { highlight("G1 (Garbage-First) Collector"); +" ("; inlineCode("-XX:+UseG1GC"); +")" }
            li { highlight("Shenandoah GC"); +" ("; inlineCode("-XX:+UseShenandoahGC"); +")" }
        }
        p {
            +"Each JVM implementation may implement GC differently, and may have its own GC strategies, "
            +"although they should all follow the JVM specification."
        }
    }
)
