package com.motycka.edu.content.lessons.backend

import com.motycka.edu.content.topics.testing.*
import com.motycka.edu.model.Lesson

val TestingBackendLesson = Lesson(
    title = "Testing",
    preTitle = "Week 1 | Lesson 4",
    subTitle = "Testing strategies for backend applications",
    topics = listOf(
        DependencyManagementTopic,
        LoggingTopic,
        DebuggingDocumentationTopic,
        IntroductionToTestingTopic,
        TypesOfTestingTopic,
        TestCasesTopic,
        TestDesignTechniquesTopic,
        UnitTestingTopic,
        IntegrationTestingTopic,
        KotestTopic
    )
)