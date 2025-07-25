package com.motycka.edu.content.topics.introductory.kotlin

import com.motycka.edu.model.Slide
import com.motycka.edu.model.highlight
import kotlinx.html.br
import kotlinx.html.em
import kotlinx.html.li
import kotlinx.html.p
import kotlinx.html.span
import kotlinx.html.strong
import kotlinx.html.ul

object WhyKotlinSlide: Slide(
    header = "Why Kotlin?",
    summary = {
        highlight("Java")
        span { +" has been the dominant language for backend development for many years." }
        br()
        highlight("Kotlin")
        span { +" is considered a modern alternative to Java." }
    },
    content = {
        p {
            +"Kotlin is fully interoperable with Java, which means that you develop and deploy Kotlin applications in the same way as Java applications, and you can use existing Java libraries and frameworks with Kotlin."
        }
        p {
            +"Both Java and Kotlin are a solid choice for backend development because of their design:"
        }
        ul {
            li { +"Statically typed languages, which means developers can catch many errors at compile time." }
            li { +"Run on the Java Virtual Machine (JVM), which means that they can run on any platform that supports it." }
            li { +"Can be containerized easily and is easy to deploy and scale in cloud environments." }
            li { +"Have a large ecosystem of libraries and frameworks that can be used to build applications." }
            li { +"Have a strong community and support from major companies (e.g., Google for Android, JetBrains for Kotlin)." }
            li { +"There is some performance overhead with JVM, but it is generally negligible for most applications and JVM can be tuned for performance." }
        }
        p {
            strong {
                +"What we will learn in this course is not specific to Kotlin and can be applied to any backend language."
            }
        }
        p {
            em {
                +"Also, an honest answer is that it is the language I know best, and therefore I can teach it best."
            }
        }
    }
)
