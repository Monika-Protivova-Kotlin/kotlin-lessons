package com.motycka.edu.content.topics.basics.io

import kotlinx.html.*
import com.motycka.edu.model.Topic
import com.motycka.edu.model.Slide

object IOPracticeTopic : Topic(
    title = "Practice",
    slides = listOf(
        IOPracticeSlide
    )
)

object IOPracticeSlide : Slide(
    header = "Can you see a problem?",
    summary = {
        +"Looking back at our fantasy characters game model, there is one problem that breaks one of the OOP principles."
    },
    content = {
        div("content-center content-100") {
            style = "font-size: 120%"
            p {
                +"Can you see it?"
            }
            p {
                +"Can you fix it?"
            }
        }
    }
)