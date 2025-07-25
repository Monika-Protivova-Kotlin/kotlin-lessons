package com.motycka.edu.content.courses

import com.motycka.edu.content.lessons.backend.*
import com.motycka.edu.model.Course

val BackendDevelopmentInKotlinCourse = Course(
    name = "Backend Development and Testing",
    subTitle = "in Kotlin",
    lessons = listOf(
        IntroductionToBackendDevelopmentLesson,
        KotlinEssentialsBackendLesson,
        OopPrinciplesAndModelsLesson,
        TestingBackendLesson,
        ApplicationArchitectureAndRoutingLesson,
        BusinessLogicAndSecurityLesson,
        DataLayerAndRepositoriesLesson,
        PracticeLesson,
        ArchitecturePatternsAndSolidLesson,
        MemoryConcurrencyBackendLesson,
        ValidationExceptionsAndRobustnessLesson,
        DeploymentAndObservabilityLesson
    )
)
