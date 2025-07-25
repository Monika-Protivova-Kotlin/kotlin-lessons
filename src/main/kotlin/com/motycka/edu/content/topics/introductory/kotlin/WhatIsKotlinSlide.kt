package com.motycka.edu.content.topics.introductory.kotlin

import com.motycka.edu.model.Slide
import kotlinx.html.a
import kotlinx.html.img
import kotlinx.html.li
import kotlinx.html.style
import kotlinx.html.ul

object WhatIsKotlinSlide : Slide(
    header = "What is Kotlin?",
    content = {
        ul {
            li {
                +"Developed by "
                a(href = "https://www.jetbrains.com/") { +"JetBrains" }
                +" and officially released in 2016."
            }
            li {
                +"Statically-typed programming language that runs on the Java Virtual Machine (JVM)."
            }
            li {
                +"Fully interoperable with Java, which means that you can use Java libraries in Kotlin and vice versa."
            }
            li {
                +"Designed to improve on Java's shortcomings, and it is considered a modern alternative to Java."
            }
            li {
                +"It has modern and intuitive syntax, and it is designed to be concise and expressive."
            }
        }
        img("JetBrains Logo", "https://resources.jetbrains.com/storage/products/company/brand/logos/jetbrains.png") {
            style = "width: 200px"
        }
    },
    textAlign = "center",
    fontSize = "100%"
)
