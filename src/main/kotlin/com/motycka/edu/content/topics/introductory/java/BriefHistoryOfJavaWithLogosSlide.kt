package com.motycka.edu.content.topics.introductory.java

import com.motycka.edu.model.Slide
import com.motycka.edu.model.imgByName
import kotlinx.html.div
import kotlinx.html.img
import kotlinx.html.li
import kotlinx.html.style
import kotlinx.html.ul

object BriefHistoryOfJavaWithLogosSlide : Slide(
    header = "Brief history of Java",
    content = {
        ul(classes = "text-align: left") {
            li { +"Developed by Sun Microsystems" }
            li { +"Introduced in 1995" }
            li { +"First version available in 1996" }
            li { +"Open source since 2007" }
            li { +"Sun Microsystems was acquired by Oracle in 2010" }
        }
        div {
            img("Java Logo", imgByName("java")) {
                style = "width: 200px"
            }
            img("to-right", imgByName("to-right")) {
                style = "height: 40px; padding: 40px"
            }
            img("Kotlin Logo", imgByName("Kotlin_logo_2021", "svg")) {
                style = "width: 200px; padding-bottom: 40px; padding-top: 40px"
            }
        }
    },
    textAlign = "center",
    fontSize = "100%"
)
