package com.motycka.edu.content.topics.introductory.java

import com.motycka.edu.model.Topic

object IntroductionToJavaTopic : Topic(
    title = "Introduction to Java",
    slides = listOf(
        BriefHistoryOfJavaSlide,
        BriefHistoryOfJavaWithLogosSlide,
        KeyFeaturesOfJavaSlide,
        JavaComponentsSlide
    )
)

