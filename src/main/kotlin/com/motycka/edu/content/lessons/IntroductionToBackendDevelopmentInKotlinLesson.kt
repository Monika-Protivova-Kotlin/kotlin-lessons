package com.motycka.edu.content.lessons

import com.motycka.edu.content.topics.introductory.kotlin.YourFirstKotlinProgramTopic
import com.motycka.edu.content.topics.introductory.ApplicationArchitectureTopic
import com.motycka.edu.content.topics.introductory.BackendDevelopmentTopic
import com.motycka.edu.content.topics.introductory.DevelopmentEnvironmentTopic
import com.motycka.edu.content.topics.introductory.FrameworksTopic
import com.motycka.edu.content.topics.introductory.java.IntroductionToJavaTopic
import com.motycka.edu.content.topics.introductory.kotlin.IntroductionToKotlinTopic
import com.motycka.edu.content.topics.introductory.ResponsibilitiesOfBackendTopic
import com.motycka.edu.content.topics.introductory.TwelveFactorApplicationsTopic
import com.motycka.edu.content.topics.introductory.kotlin.WhyKotlinTopic
import com.motycka.edu.model.Lesson
import com.motycka.edu.model.Slide
import com.motycka.edu.model.Topic
import kotlinx.html.li
import kotlinx.html.ol
import kotlinx.html.p

object IntroductionToBackendDevelopmentInKotlinLesson : Lesson(
    preTitle = "Week 1 | Lesson 1",
    title = "Introduction to Backend Development",
    subTitle = "In Kotlin",
    contentInfo = emptyList(),
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
        FrameworksTopic,
        Topic(
            title = "Next Lesson",
            slides = listOf(
                Slide(
                    header = "Next Lesson: Kotlin Essentials",
                    content = {
                        ol {
                            li { +"Basic syntax and data types" }
                            li { +"Control flow (if, when, loops)" }
                            li { +"Functions and lambdas" }
                            li { +"Arrays and Collections" }
                            li { +"Null safety" }
                        }
                        p { + "... and more!" }
                    }
                )
            )
        )
    )
)
