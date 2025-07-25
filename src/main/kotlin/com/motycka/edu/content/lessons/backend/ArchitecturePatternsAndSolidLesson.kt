package com.motycka.edu.content.lessons.backend

import com.motycka.edu.model.Lesson
import com.motycka.edu.content.topics.design.solid.SolidPrinciplesTopic
import com.motycka.edu.content.topics.design.InversionOfControlTopic
import com.motycka.edu.content.topics.design.TestabilityTopic
import com.motycka.edu.content.topics.backend.IoCInKtorTopic
import com.motycka.edu.content.topics.design.FunctionalProgrammingTopic
import com.motycka.edu.content.topics.basics.GenericsTopic

val ArchitecturePatternsAndSolidLesson = Lesson(
    title = "Architecture Patterns and Solid",
    preTitle = "Week 3 | Lesson 9",
    subTitle = "Design patterns and SOLID principles for backend",
    topics = listOf(
        SolidPrinciplesTopic,
        TestabilityTopic,
        InversionOfControlTopic,
        IoCInKtorTopic,
        FunctionalProgrammingTopic,
        GenericsTopic
    )
)
