package com.motycka.edu.content.lessons

import com.motycka.edu.content.topics.basics.functions.FunctionsTopic
import com.motycka.edu.content.topics.basics.essentials.KotlinLanguageSyntaxTopic
import com.motycka.edu.content.topics.oop.ObjectOrientedProgrammingIntroSlide
import com.motycka.edu.model.Lesson
import com.motycka.edu.model.Topic

object KotlinEssentialsLesson : Lesson(
    preTitle = "Week 1 | Lesson 2",
    title = "Kotlin Essentials",
    contentInfo = listOf(
        "Variables, Operators, Functions, Control flow, Collections"
    ),
    topics = listOf(
        Topic(
            title = "Object-Oriented Programming",
            slides = listOf(
                ObjectOrientedProgrammingIntroSlide
            )
        ),
        KotlinLanguageSyntaxTopic,
        FunctionsTopic
    )
)
