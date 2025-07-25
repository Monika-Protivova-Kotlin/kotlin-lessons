package com.motycka.edu.content.topics.design

import com.motycka.edu.model.Topic
import com.motycka.edu.model.Slide
import kotlinx.html.*

object SOLIDPracticeTopic : Topic(
    title = "Practice",
    slides = listOf(
        SOLIDPracticeSlide
    )
)

object SOLIDPracticeSlide : Slide(
    header = "Practice",
    content = {
        div {
            style = "font-size: 200%"
            em {
                +"Catch up on all the lessons!"
            }
            br()
            em {
                +"Make sure you have your fantasy game model ready for next lesson."
            }
        }
    }
)
