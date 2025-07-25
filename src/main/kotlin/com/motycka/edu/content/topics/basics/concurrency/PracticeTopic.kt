package com.motycka.edu.content.topics.basics.concurrency

import com.motycka.edu.model.Slide
import com.motycka.edu.model.Topic
import kotlinx.html.*

object PracticeTopic : Topic(
    title = "Practice",
    slides = listOf(
        PracticeSlide
    )
)

object PracticeSlide : Slide(
    header = "Practice",
    content = {
        div {
            style = "font-size: 200%"
            em { +"Catch up on all the lessons!" }
            br()
            em { +"Remember to turn in you assignments." }
        }
    },
    textAlign = "center",
    fontSize = "200%"
)