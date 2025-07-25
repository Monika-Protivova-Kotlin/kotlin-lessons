package com.motycka.edu.content.topics.basics.arrays

import com.motycka.edu.model.Topic

object ArraysTopic : Topic(
    title = "Arrays",
    slides = listOf(
        ArraysIntroSlide,
        ArrayDeclarationSlide,
        ArrayAccessSlide,
        ArrayOperationsSlide
    )
)

