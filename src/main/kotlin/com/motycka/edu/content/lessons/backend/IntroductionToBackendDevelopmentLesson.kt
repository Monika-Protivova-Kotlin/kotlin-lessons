package com.motycka.edu.content.lessons.backend

import com.motycka.edu.content.topics.introductory.*
import com.motycka.edu.content.topics.introductory.java.IntroductionToJavaTopic
import com.motycka.edu.content.topics.introductory.kotlin.IntroductionToKotlinTopic
import com.motycka.edu.content.topics.introductory.kotlin.WhyKotlinTopic
import com.motycka.edu.content.topics.introductory.kotlin.YourFirstKotlinProgramTopic
import com.motycka.edu.model.Lesson

val IntroductionToBackendDevelopmentLesson = Lesson(
    title = "Introduction to Backend Development",
    preTitle = "Week 1 | Lesson 1",
    subTitle = "in Kotlin",
    topics = listOf(
        WhyKotlinTopic,
        DevelopmentEnvironmentTopic,
        YourFirstKotlinProgramTopic,
        BackendDevelopmentTopic,
        ResponsibilitiesOfBackendTopic,
        ApplicationArchitectureTopic,
        TwelveFactorApplicationsTopic,
        IntroductionToJavaTopic,
        IntroductionToKotlinTopic,
        FrameworksTopic
    )
)
