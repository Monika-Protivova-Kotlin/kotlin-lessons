package com.motycka.edu.content.topics.basics

import com.motycka.edu.model.Slide
import com.motycka.edu.model.Topic
import com.motycka.edu.model.highlight
import com.motycka.edu.model.inlineCode
import kotlinx.html.br
import kotlinx.html.div
import kotlinx.html.em
import kotlinx.html.li
import kotlinx.html.p
import kotlinx.html.span
import kotlinx.html.strong
import kotlinx.html.style
import kotlinx.html.ul

object GarbageCollectionTopic : Topic(
    title = "Garbage Collection (GC)",
    slides = listOf(
        GarbageCollectionSlide,
        GarbageCollectionGenerationsSlide,
        GarbageCollectionStrategiesSlide,
//        ObjectLifecycleAnimationSlide,
//        GenerationalGCAnimationSlide,
//        MarkAndSweepAnimationSlide,
//        MemoryCompactionAnimationSlide
    )
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

object ObjectLifecycleAnimationSlide : Slide(
    header = "GC Animation: Object Lifecycle",
    summary = {
        +"See how objects are created, referenced, and collected when they become unreachable."
    },
    content = {
        div("gc-animation") {
            // Stack on the left
            div("gc-memory-area gc-stack") {
//                attributes["data-fragment-index"] = "1"
                div("gc-memory-area-label") { +"Stack" }

                // Variable A reference
                div("gc-stack-var fragment fade-in") {
                    attributes["data-fragment-index"] = "2"
                    div("gc-stack-var-name") { +"var a" }
                    div("gc-stack-var-ref") { +"→ A" }
                }

                // Variable C reference
                div("gc-stack-var fragment fade-in") {
                    attributes["data-fragment-index"] = "4"
                    div("gc-stack-var-name") { +"var c" }
                    div("gc-stack-var-ref") { +"→ C" }
                }

                // Remove varA reference
                div("gc-stack-var fragment fade-in") {
                    attributes["data-fragment-index"] = "5"
                    div("gc-stack-var-name") { +"var a" }
                    div("gc-stack-var-ref") { +"→ null" }
                }
            }

            // Heap on the right
            div("gc-memory-area gc-heap") {
                attributes["data-fragment-index"] = "1"
                div("gc-memory-area-label") { +"Heap" }

                // Object A (live)
                div("gc-object gc-live fragment fade-in") {
                    attributes["data-fragment-index"] = "2"
                    div("gc-object-label") {
                        +"Object A"
                    }
                }

                // Object B (referenced by A)
                div("gc-object gc-live fragment fade-in") {
                    attributes["data-fragment-index"] = "2"
                    div("gc-object-label") { +"Object B" }
                }

                // Object C (live)
                div("gc-object gc-live fragment fade-in") {
                    attributes["data-fragment-index"] = "4"
                    div("gc-object-label") { +"Object C" }
                }

                // A and B become unreachable
                div("gc-object gc-unreachable fragment") {
                    attributes["data-fragment-index"] = "6"
                    style = "position: absolute; left: 235px; top: 65px;"
                    div("gc-object-label") { +"Object A" }
                }
                div("gc-object gc-unreachable fragment") {
                    attributes["data-fragment-index"] = "6"
                    style = "position: absolute; left: 345px; top: 65px;"
                    div("gc-object-label") { +"Object B" }
                }

                // A and B collected (disappear)
                div("fragment fade-out") {
                    attributes["data-fragment-index"] = "7"
                    style = "position: absolute; left: 235px; top: 65px;"
                    div("gc-object-label") { +"A" }
                }
                div("fragment fade-out") {
                    attributes["data-fragment-index"] = "7"
                    style = "position: absolute; left: 345px; top: 65px;"
                    div("gc-object-label") { +"B" }
                }
            }
        }

        // Annotation text
        p("fragment fade-in") {
            attributes["data-fragment-index"] = "1"
            +"Step 1: Empty heap and stack"
        }
        p("fragment fade-in") {
            attributes["data-fragment-index"] = "2"
            +"Step 2: Create Object A, referenced by stack variable "
            span("gc-highlight") { +"varA" }
        }
        p("fragment fade-in") {
            attributes["data-fragment-index"] = "3"
            +"Step 3: Create Object B, referenced by Object A"
        }
        p("fragment fade-in") {
            attributes["data-fragment-index"] = "4"
            +"Step 4: Create Object C, referenced by stack variable "
            span("gc-highlight") { +"varC" }
        }
        p("fragment fade-in") {
            attributes["data-fragment-index"] = "5"
            +"Step 5: Remove stack reference to A (A and B become unreachable)"
        }
        p("fragment fade-in") {
            attributes["data-fragment-index"] = "6"
            span("gc-highlight") { +"Step 6: GC identifies unreachable objects" }
            +" - A and B are marked for collection"
        }
        p("fragment fade-in") {
            attributes["data-fragment-index"] = "7"
            span("gc-highlight") { +"Step 7: GC collects unreachable objects" }
            +" - A and B are removed from heap"
        }
        p("fragment fade-in") {
            attributes["data-fragment-index"] = "8"
            +"Step 8: Only Object C remains (still referenced by varC)"
        }
    }
)

object GenerationalGCAnimationSlide : Slide(
    header = "GC Animation: Generational Collection",
    summary = {
        +"Watch objects age and move through generations: Eden → Survivor → Old Generation"
    },
    content = {
        // Young Generation
        div("gc-young-gen fragment fade-in") {
            attributes["data-fragment-index"] = "1"
            div("gc-memory-area-label") { +"Young Generation" }

            // Eden Space
            div("gc-eden") {
                div("gc-generation-label") { +"Eden" }

                // Initial objects in Eden
                div("gc-object gc-live fragment fade-in") {
                    attributes["data-fragment-index"] = "2"
                    div("gc-object-label") { +"Obj1" }
                }
                div("gc-object gc-live fragment fade-in") {
                    attributes["data-fragment-index"] = "2"
                    div("gc-object-label") { +"Obj2" }
                }
                div("gc-object gc-dead fragment fade-in") {
                    attributes["data-fragment-index"] = "2"
                    div("gc-object-label") { +"Obj3" }
                }

                // Obj3 collected after Minor GC #1
                div("fragment fade-out") {
                    attributes["data-fragment-index"] = "3"
                    style = "display: inline-block;"
                    div("gc-object gc-dead") {
                        div("gc-object-label") { +"Obj3" }
                    }
                }

                // New objects after GC
                div("gc-object gc-live fragment fade-in") {
                    attributes["data-fragment-index"] = "4"
                    div("gc-object-label") { +"Obj4" }
                }
                div("gc-object gc-live fragment fade-in") {
                    attributes["data-fragment-index"] = "4"
                    div("gc-object-label") { +"Obj5" }
                }
            }

            // Survivor 0
            div("gc-survivor") {
                div("gc-generation-label") { +"S0" }

                // Objects moved from Eden after Minor GC #1
                div("gc-object gc-live fragment fade-in") {
                    attributes["data-fragment-index"] = "3"
                    div("gc-object-label") { +"Obj1" }
                    div("gc-object-age") { +"1" }
                }
                div("gc-object gc-live fragment fade-in") {
                    attributes["data-fragment-index"] = "3"
                    div("gc-object-label") { +"Obj2" }
                    div("gc-object-age") { +"1" }
                }
            }

            // Survivor 1
            div("gc-survivor") {
                div("gc-generation-label") { +"S1" }

                // Objects moved from S0 after Minor GC #2
                div("gc-object gc-live fragment fade-in") {
                    attributes["data-fragment-index"] = "5"
                    div("gc-object-label") { +"Obj1" }
                    div("gc-object-age") { +"2" }
                }
                div("gc-object gc-live fragment fade-in") {
                    attributes["data-fragment-index"] = "5"
                    div("gc-object-label") { +"Obj2" }
                    div("gc-object-age") { +"2" }
                }
                div("gc-object gc-live fragment fade-in") {
                    attributes["data-fragment-index"] = "5"
                    div("gc-object-label") { +"Obj4" }
                    div("gc-object-age") { +"1" }
                }
            }
        }

        // Old Generation
        div("gc-old-gen fragment fade-in") {
            attributes["data-fragment-index"] = "1"
            div("gc-memory-area-label") { +"Old Generation (Tenured)" }

            // Promoted object after multiple GC cycles
            div("gc-object gc-live fragment fade-in") {
                attributes["data-fragment-index"] = "7"
                div("gc-object-label") { +"Obj1" }
                div("gc-object-age") { +"8" }
            }
        }

        // GC Event indicators and annotations
        div("fragment fade-in") {
            attributes["data-fragment-index"] = "2"
            p { +"Objects created in "; span("gc-highlight") { +"Eden Space" } }
        }

        div("fragment fade-in") {
            attributes["data-fragment-index"] = "3"
            div("gc-event minor") { +"Minor GC #1" }
            p {
                +"Survivors (Obj1, Obj2) moved to "
                span("gc-highlight") { +"S0" }
                +" with age=1. Dead objects (Obj3) collected."
            }
        }

        div("fragment fade-in") {
            attributes["data-fragment-index"] = "4"
            p { +"New objects (Obj4, Obj5) allocated in Eden" }
        }

        div("fragment fade-in") {
            attributes["data-fragment-index"] = "5"
            div("gc-event minor") { +"Minor GC #2" }
            p {
                +"S0 objects moved to "
                span("gc-highlight") { +"S1" }
                +" with incremented age. Eden survivors join them."
            }
        }

        div("fragment fade-in") {
            attributes["data-fragment-index"] = "6"
            p("gc-note") {
                +"This cycle continues: objects swap between Survivor spaces, "
                +"aging with each Minor GC until they reach the promotion threshold (typically age 8-15)."
            }
        }

        div("fragment fade-in") {
            attributes["data-fragment-index"] = "7"
            div("gc-event major") { +"Promotion" }
            p {
                +"Obj1 reaches age threshold and is "
                span("gc-highlight") { +"promoted to Old Generation" }
                +", where it will stay until a Major GC."
            }
        }
    }
)

object MarkAndSweepAnimationSlide : Slide(
    header = "GC Animation: Mark-and-Sweep Algorithm",
    summary = {
        +"Step-by-step visualization of how GC determines which objects to keep and which to remove"
    },
    content = {
        div("fragment fade-in") {
            attributes["data-fragment-index"] = "1"
            p {
                strong { +"Phase 1: Mark" }
                +" - Traverse object graph from GC roots, marking all reachable objects"
            }
        }

        div("gc-object-graph fragment fade-in") {
            attributes["data-fragment-index"] = "2"

            // GC Roots
            div("gc-object gc-root fragment fade-in") {
                attributes["data-fragment-index"] = "2"
                div("gc-object-label") { +"Stack Var" }
            }

            div("gc-object gc-root fragment fade-in") {
                attributes["data-fragment-index"] = "2"
                div("gc-object-label") { +"Static Field" }
            }

            // Reachable objects - will be marked
            div("gc-object gc-live fragment fade-in") {
                attributes["data-fragment-index"] = "2"
                div("gc-object-label") { +"A" }
            }

            div("gc-object gc-marked fragment") {
                attributes["data-fragment-index"] = "4"
                div("gc-object-label") { +"A" }
            }

            div("gc-object gc-live fragment fade-in") {
                attributes["data-fragment-index"] = "2"
                div("gc-object-label") { +"B" }
            }

            div("gc-object gc-marked fragment") {
                attributes["data-fragment-index"] = "4"
                div("gc-object-label") { +"B" }
            }

            div("gc-object gc-live fragment fade-in") {
                attributes["data-fragment-index"] = "2"
                div("gc-object-label") { +"C" }
            }

            div("gc-object gc-marked fragment") {
                attributes["data-fragment-index"] = "5"
                div("gc-object-label") { +"C" }
            }

            // Unreachable objects - will NOT be marked
            div("gc-object gc-live fragment fade-in") {
                attributes["data-fragment-index"] = "2"
                div("gc-object-label") { +"D" }
            }

            div("gc-object gc-live fragment fade-in") {
                attributes["data-fragment-index"] = "2"
                div("gc-object-label") { +"E" }
            }
        }

        div("fragment fade-in") {
            attributes["data-fragment-index"] = "3"
            p("gc-note") {
                +"Starting from GC roots (stack variables, static fields), "
                +"the GC traverses all object references..."
            }
        }

        div("fragment fade-in") {
            attributes["data-fragment-index"] = "4"
            p {
                +"Objects "
                span("gc-highlight") { +"A" }
                +" and "
                span("gc-highlight") { +"B" }
                +" are "
                strong { +"marked as reachable" }
                +" (blue) from Stack Var"
            }
        }

        div("fragment fade-in") {
            attributes["data-fragment-index"] = "5"
            p {
                +"Object "
                span("gc-highlight") { +"C" }
                +" is "
                strong { +"marked as reachable" }
                +" (blue) from Static Field"
            }
        }

        div("fragment fade-in") {
            attributes["data-fragment-index"] = "6"
            p {
                +"Mark phase complete. Objects "
                strong { +"D and E remain white (unmarked)" }
                +" - they are unreachable!"
            }
        }

        div("fragment fade-in") {
            attributes["data-fragment-index"] = "7"
            div("gc-event") { +"Phase 2: Sweep" }
            p {
                strong { +"Sweep" }
                +" - Remove all unmarked (white) objects from heap"
            }
        }

        div("gc-object-graph fragment") {
            attributes["data-fragment-index"] = "8"

            // Show marked objects still alive
            div("gc-object gc-live") {
                div("gc-object-label") { +"A" }
            }
            div("gc-object gc-live") {
                div("gc-object-label") { +"B" }
            }
            div("gc-object gc-live") {
                div("gc-object-label") { +"C" }
            }

            // Unreachable objects being swept
            div("gc-object gc-unreachable fragment") {
                attributes["data-fragment-index"] = "8"
                div("gc-object-label") { +"D" }
            }
            div("gc-object gc-unreachable fragment") {
                attributes["data-fragment-index"] = "8"
                div("gc-object-label") { +"E" }
            }

            // D and E disappear
            div("fragment fade-out") {
                attributes["data-fragment-index"] = "9"
                style = "display: inline-block;"
                div("gc-object") { +"D" }
            }
            div("fragment fade-out") {
                attributes["data-fragment-index"] = "9"
                style = "display: inline-block;"
                div("gc-object") { +"E" }
            }
        }

        div("fragment fade-in") {
            attributes["data-fragment-index"] = "8"
            p {
                +"Unmarked objects "
                span("gc-highlight") { +"D and E" }
                +" identified for removal (red)"
            }
        }

        div("fragment fade-in") {
            attributes["data-fragment-index"] = "9"
            p("gc-note") {
                strong { +"Sweep complete!" }
                +" Memory occupied by D and E is reclaimed. "
                +"Only reachable objects (A, B, C) remain in heap."
            }
        }

        div("fragment fade-in") {
            attributes["data-fragment-index"] = "10"
            p {
                em {
                    +"This two-phase approach ensures only truly unreachable objects are collected, "
                    +"preventing memory leaks while avoiding premature deletion."
                }
            }
        }
    }
)

object MemoryCompactionAnimationSlide : Slide(
    header = "GC Animation: Memory Compaction",
    summary = {
        +"See how GC eliminates memory fragmentation by compacting objects into contiguous space"
    },
    content = {
        div("fragment fade-in") {
            attributes["data-fragment-index"] = "1"
            p {
                strong { +"The Problem: Memory Fragmentation" }
            }
            p("gc-note") {
                +"After many allocations and deallocations, memory becomes fragmented with gaps. "
                +"Even with enough total free space, you may not have enough "
                em { +"contiguous" }
                +" space for a large object."
            }
        }

        // Initial fragmented state
        div("gc-memory-bar fragment fade-in") {
            attributes["data-fragment-index"] = "2"

            div("gc-memory-block allocated") { +"Obj A (30KB)" }
            div("gc-memory-block free fragment fade-in") {
                attributes["data-fragment-index"] = "2"
                +"free 20KB"
            }
            div("gc-memory-block allocated") { +"Obj B (25KB)" }
            div("gc-memory-block free fragment fade-in") {
                attributes["data-fragment-index"] = "2"
                +"free 15KB"
            }
            div("gc-memory-block allocated") { +"Obj C (40KB)" }
            div("gc-memory-block free fragment fade-in") {
                attributes["data-fragment-index"] = "2"
                +"free 25KB"
            }
        }

        div("fragment fade-in") {
            attributes["data-fragment-index"] = "3"
            p {
                +"Total free space: "
                span("gc-highlight") { +"60KB" }
                +" (20 + 15 + 25)"
            }
            p {
                +"But if we need to allocate a 50KB object, it "
                strong { +"FAILS" }
                +" - no single gap is large enough!"
            }
        }

        div("fragment fade-in") {
            attributes["data-fragment-index"] = "4"
            div("gc-event") { +"Memory Compaction Begins" }
            p {
                strong { +"Solution: Compact" }
                +" - Slide all live objects together, eliminating gaps"
            }
        }

        // Compacted state
        div("gc-memory-bar fragment fade-in") {
            attributes["data-fragment-index"] = "5"

            div("gc-memory-block allocated") { +"Obj A (30KB)" }
            div("gc-memory-block allocated") { +"Obj B (25KB)" }
            div("gc-memory-block allocated") { +"Obj C (40KB)" }
            div("gc-memory-block free") { +"free 60KB" }
        }

        div("fragment fade-in") {
            attributes["data-fragment-index"] = "5"
            p {
                +"Objects moved together (addresses updated). "
                +"All "
                span("gc-highlight") { +"60KB" }
                +" free space is now "
                strong { +"contiguous" }
                +"!"
            }
        }

        div("fragment fade-in") {
            attributes["data-fragment-index"] = "6"
            p("gc-note") {
                strong { +"Compaction complete!" }
                +" Now we can successfully allocate the 50KB object in the contiguous free space."
            }
        }

        // Successful allocation after compaction
        div("gc-memory-bar fragment fade-in") {
            attributes["data-fragment-index"] = "7"

            div("gc-memory-block allocated") { +"Obj A (30KB)" }
            div("gc-memory-block allocated") { +"Obj B (25KB)" }
            div("gc-memory-block allocated") { +"Obj C (40KB)" }
            div("gc-memory-block allocated fragment fade-in") {
                attributes["data-fragment-index"] = "7"
                style = "background: linear-gradient(135deg, #4caf50 0%, #81c784 100%); border-color: #2e7d32;"
                +"NEW (50KB)"
            }
            div("gc-memory-block free") { +"free 10KB" }
        }

        div("fragment fade-in") {
            attributes["data-fragment-index"] = "7"
            p {
                span("gc-highlight") { +"Success!" }
                +" 50KB object allocated. Only 10KB free space remains."
            }
        }

        div("fragment fade-in") {
            attributes["data-fragment-index"] = "8"
            p {
                em {
                    +"Compaction happens during GC. The GC must update all references to moved objects, "
                    +"which takes time but significantly improves memory utilization and allocation performance."
                }
            }
        }

        div("fragment fade-in") {
            attributes["data-fragment-index"] = "9"
            p {
                strong { +"Benefits:" }
            }
            ul {
                style = "font-size: 0.9em;"
                li { +"Eliminates fragmentation → better memory utilization" }
                li { +"Enables allocation of large objects" }
                li { +"Improves allocation speed (bump-the-pointer allocation)" }
                li { +"Reduces OutOfMemoryError due to fragmentation" }
            }
        }
    }
)
