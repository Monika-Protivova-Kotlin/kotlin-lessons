package com.motycka.edu.content.topics.introductory.kotlin

import com.motycka.edu.model.Slide
import kotlinx.html.li
import kotlinx.html.strong
import kotlinx.html.ul

object KotlinReleasesSlide : Slide(
    header = "Kotlin Releases: Major Versions",
    content = {
        ul {
            li {
                strong { +"2010:" }
                +" Project Kotlin was born. JetBrains unveiled Project Kotlin, a new language for the JVM, which had been under development for a year."
            }
            li {
                strong { +"2012:" }
                +" JetBrains open sourced Project Kotlin. The company has set up a Web demo for the language, and a plugin is already available for IntelliJ IDEA 11." }
            li {
                strong { +"2016:"}
                +" Kotlin 1.0 was officially released. It was considered stable and ready for production." }
            li {
                strong { +"2017:" }
                +" Google officially announced Kotlin as a first-class language for Android applications development during Google I/O. This played a crucial role in Kotlin's popularity among Android developers." }
            li {
                strong { +"2019:" }
                +" Google announced Kotlin as its preferred language for Android app developers, meaning that development tooling would be optimized for Kotlin, and that Kotlin-specific APIs would be prioritized." }
            li {
                strong { +"2020:" }
                +" Kotlin 1.4 released with focusing on improving the performance and tooling." }
            li {
                strong { +"2021:" }
                +" Release of Kotlin 1.5.0 with stable language features like JVM records, sealed interfaces and the new default JVM IR compiler." }
            li {
                strong { +"2022:" }
                +" Kotlin 1.6 was released in November 2021." }
            li {
                strong { +"2023:" }
                +" Kotlin 1.7 was released in June 2023, including the alpha version of the new Kotlin K2 compiler." }
            li {
                strong { +"2023:" }
                +" Kotlin 1.8 was released in December 2023, 1.8.0 was released on January 11, 2024." }
            li {
                strong { +"2024:" }
                +" Kotlin 1.9 was released in July 2024, 1.9.0 was released on July 6, 2024." }
            li {
                strong { +"2024:" }
                +" Kotlin 2.0 was released in May 2024, 2.0.0 was released on May 21, 2024." }
        }
    },
    fontSize = "70%"
)
