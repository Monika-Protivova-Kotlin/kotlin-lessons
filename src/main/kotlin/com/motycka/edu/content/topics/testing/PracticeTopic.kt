package com.motycka.edu.content.topics.testing

import com.motycka.edu.model.Topic
import com.motycka.edu.model.Slide

object PracticeTopic : Topic(
    title = "Practice",
    slides = listOf(
        PracticeSlide
    )
)

object PracticeSlide : Slide(
    header = "Practice",
    content = {
        // Practice content can be added here
    }
)