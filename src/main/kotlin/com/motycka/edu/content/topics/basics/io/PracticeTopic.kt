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
    header = "Practice: Fantasy.Space Game",
    summary = {
        +"Let's use what we have learned."
    },
    content = {
        p {
            +"Load game characters from a character.csv file provided in resources."
        }
    }
)
