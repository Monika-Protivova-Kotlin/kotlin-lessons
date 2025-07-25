package com.motycka.edu.content.lessons.backend

import com.motycka.edu.model.Lesson
import com.motycka.edu.content.topics.backend.RepositoryTopic
import com.motycka.edu.content.topics.backend.ExposedFrameworkTopic
import com.motycka.edu.content.topics.testing.MockingTopic
import com.motycka.edu.content.topics.testing.TestingLayersTopic

val DataLayerAndRepositoriesLesson = Lesson(
    title = "Data Layer and Repositories",
    preTitle = "Week 2 | Lesson 7",
    subTitle = "Data access patterns and repository design",
    topics = listOf(
        RepositoryTopic,
        ExposedFrameworkTopic,
        MockingTopic,
        TestingLayersTopic
    )
)