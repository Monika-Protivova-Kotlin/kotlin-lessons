package com.motycka.edu.content.topics.introductory.java

import com.motycka.edu.model.Slide
import kotlinx.html.br
import kotlinx.html.em
import kotlinx.html.p
import kotlinx.html.strong

object KeyFeaturesOfJavaSlide : Slide(
    header = "Key features of Java",
    content = {
        p {
            strong { +"Simple" }
            br()
            em(classes = "font-size: 80%") { +"easy to learn and use, familiar syntax (based on C++), memory management" }
        }
        p {
            strong { +"Object-Oriented" }
            br()
            em(classes = "font-size: 80%") { +"everything in Java is an object" }
        }
        p {
            strong { +"Platform Independent, Portable, Architecture-Neutral" }
            br()
            em(classes = "font-size: 80%") { +"write once, run anywhere (on java virtual machine), compiled to Java byte code, no specific architecture dependent features (for example data types)" }
        }
        p {
            strong { +"Multi-Threaded" }
            br()
            em(classes = "font-size: 80%") { +"enables concurrent task execution" }
        }
        p {
            strong { +"Secure" }
            br()
            em(classes = "font-size: 80%") { +"no pointers, runs inside virtual machine, guards against illegal access to resources" }
        }
        p {
            strong { +"Robust" }
            br()
            em(classes = "font-size: 80%") { +"no pointers, memory management, strong type checking" }
        }
        p {
            strong { +"High Performance" }
            br()
            em(classes = "font-size: 80%") { +"slower than compiled languages, but fast for interpreted language due java byte code being close to native code" }
        }
    }
)
