package com.motycka.edu.content.topics.basics.arrays

import com.motycka.edu.model.Topic

object ArraysAdvancedTopic : Topic(
    title = "Arrays",
    slides = listOf(
        ArraysAdvancedIntroSlide,
        ArrayAdvancedDeclarationSlide,
        ArrayAdvancedAccessSlide,
        ArrayAdvancedOperationsSlide
    )
)

