package com.motycka.edu.content.lessons.backend

import com.motycka.edu.content.topics.backend.ApplicationLifecycleTopic
import com.motycka.edu.content.topics.backend.DevelopmentAndEnvironmentsTopic
import com.motycka.edu.content.topics.backend.BuildAndPackagingTopic
import com.motycka.edu.content.topics.backend.DeploymentStrategiesTopic
import com.motycka.edu.content.topics.backend.ObservabilityTopic
import com.motycka.edu.model.Lesson

val DeploymentAndObservabilityLesson = Lesson(
    title = "Production",
    preTitle = "Week 3 | Lesson 12",
    subTitle = "Deployment, Configuration and Secrets, Observability, Monitoring",
    topics = listOf(
        ApplicationLifecycleTopic,
        DevelopmentAndEnvironmentsTopic,
        BuildAndPackagingTopic,
        DeploymentStrategiesTopic,
        ObservabilityTopic
    )
)