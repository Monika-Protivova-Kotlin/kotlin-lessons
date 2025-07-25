package com.motycka.edu.content.lessons.programming

import com.motycka.edu.model.Lesson
import com.motycka.edu.content.topics.design.solid.SolidPrinciplesTopic
import com.motycka.edu.content.topics.design.InversionOfControlTopic
import com.motycka.edu.content.topics.design.FunctionalProgrammingTopic
import com.motycka.edu.content.topics.design.DesignPatternsTopic

val SolidLesson = Lesson(
    title = "Solid",
    preTitle = "Week 2 | Lesson 10",
    subTitle = "The SOLID Principle",
    topics = listOf(
        SolidPrinciplesTopic,
        InversionOfControlTopic,
        FunctionalProgrammingTopic,
        DesignPatternsTopic
    )
)
