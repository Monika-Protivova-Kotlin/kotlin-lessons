package com.motycka.edu.content.topics.introductory.kotlin

import com.motycka.edu.model.Slide
import com.motycka.edu.model.highlight
import kotlinx.html.li
import kotlinx.html.span
import kotlinx.html.strong
import kotlinx.html.ul

object ImportantFeaturesSlide : Slide(
    header = "Important features",
    content = {
        ul {
            li {
                highlight("Null safety")
                span { +" by distinguishing nullable and non-nullable types" }
            }
            li {
                highlight("Interoperability")
                span { +" with Java, allowing developers to use Java libraries in Kotlin and vice versa" }
            }
            li {
                highlight("Conciseness")
                span { +" reducing boilerplate code and improving readability" }
            }
            li {
                highlight("Coroutines")
                span { +" provide built-in support for coroutines for easy and efficient concurrent programming" }
            }
            li {
                highlight("Extension Functions")
                span { +" allowing you to add new functions to existing classes without modifying their source code" }
            }
            li {
                highlight("Data Classes")
                span { +" providing a concise way to create classes that only hold data" }
            }
            li {
                highlight("Higher-Order Functions and Lambdas")
                span { +" supporting functional programming paradigms" }
            }
            li {
                highlight("Companion Objects")
                span { +" providing a way to create static methods and properties in Kotlin" }
            }
            li {
                highlight("Smart Casts")
                span { +" used to automatically casts types when certain conditions are met" }
            }
            li {
                highlight("Sealed Classes")
                span { +" providing a way to restrict inheritance" }
            }
        }
    }
)
