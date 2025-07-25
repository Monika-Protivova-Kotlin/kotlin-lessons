package com.motycka.edu.content.lessons.backend

import com.motycka.edu.model.Lesson
import com.motycka.edu.content.topics.backend.ExceptionHandlingTopic
import com.motycka.edu.content.topics.backend.ValidationTopic
import com.motycka.edu.content.topics.backend.ApplicationErrorHandlingTopic
import com.motycka.edu.content.topics.backend.ResilientApplicationsTopic

val ValidationExceptionsAndRobustnessLesson = Lesson(
    title = "Validation Exceptions and Robustness",
    preTitle = "Week 3 | Lesson 11",
    subTitle = "Error handling and validation in backend systems",
    topics = listOf(
        ExceptionHandlingTopic,
        ValidationTopic,
        ApplicationErrorHandlingTopic,
        ResilientApplicationsTopic
    )
)