package com.motycka.edu.content.topics.introductory.java

import com.motycka.edu.model.Slide
import com.motycka.edu.model.Topic
import com.motycka.edu.model.highlight
import kotlinx.html.*

object JavaVirtualMachineTopic : Topic(
    title = "Java Virtual Machine",
    slides = listOf(
        JavaVirtualMachineSlide,
        JVMImplementationsSlide
    )
)

object JavaVirtualMachineSlide : Slide(
    header = "Java Virtual Machine",
    summary = { +"\"Write Once, Run Anywhere\"" },
    content = {
        ul {
            li {
                +"The main purpose of JVM is to provide a runtime environment for Java applications, "
                +"that is independent of the underlying hardware and operating system."
            }
            li {
                +"Other programming languages that can run on JVM include "
                highlight("Kotlin")
                +", "
                highlight("Scala")
                +", "
                highlight("Groovy")
                +" and "
                highlight("Clojure")
                +"."
            }
            li {
                +"JVM also provides tools for runtime performance optimization, memory management (garbage collection), monitoring, and other."
            }
            li {
                +"Java Virtual Machine is a part of the Java Runtime Environment (JRE)."
            }
        }
    }
)

object JVMImplementationsSlide : Slide(
    header = "Java Virtual Machine",
    summary = { +"There are three ways to look at JVM implementations." },
    content = {
        ul {
            li {
                highlight("Specification")
                br()
                +"Defines how the JVM should be implemented."
            }
            li {
                highlight("Implementation")
                br()
                +"The actual JVM implementation."
            }
            li {
                highlight("Instance")
                br()
                +"A running JVM process (create every time you start a Java program)."
            }
        }
        p {
            +"Since JVM is open source, it exists in more than one implementation. "
            +"They all follow the specification, but may differ in performance, memory management, and other aspects."
        }
        p {
            +"Some of the most popular JVM implementations are:"
        }
        ul {
            li { +"Oracle HotSpot JVM" }
            li { +"Eclipse OpenJ9" }
            li { +"GraalVM" }
        }
    }
)