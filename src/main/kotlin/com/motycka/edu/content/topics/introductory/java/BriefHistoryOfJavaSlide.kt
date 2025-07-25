package com.motycka.edu.content.topics.introductory.java

import com.motycka.edu.model.Slide
import com.motycka.edu.model.imgByName
import kotlinx.html.br
import kotlinx.html.img
import kotlinx.html.p
import kotlinx.html.style

object BriefHistoryOfJavaSlide : Slide(
    header = "Brief history of Java",
    summary = { +"Why do we need to talk about Java first?" },
    content = {
        p {
            +"While Java and Kotlin are two separate languages, they are closely related. They both run on the Java Virtual Machine (JVM), and they are interoperable. In fact, Kotlin compiles to Java bytecode, which means that it can run on any platform that supports Java. Understanding some basic principles in Java will help you understand Kotlin better."
        }
        br()
        img("Java Logo", imgByName("java")) {
            style = "width: 200px"
        }
        img("to-right", imgByName("to-right")) {
            style = "height: 40px; padding: 40px"
        }
        img("Kotlin Logo", imgByName("Kotlin_logo_2021", "svg")) {
            style = "width: 200px; padding-bottom: 40px; padding-top: 40px"
        }
    },
    textAlign = "center",
    fontSize = "100%"
)
