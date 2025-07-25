package com.motycka.edu.content.lessons.backend

import com.motycka.edu.model.Lesson
import com.motycka.edu.content.topics.backend.ServiceLayerTopic
import com.motycka.edu.content.topics.backend.AuthenticationTopic
import com.motycka.edu.content.topics.backend.AuthorizationTopic
import com.motycka.edu.content.topics.backend.BasicAuthTopic
import com.motycka.edu.content.topics.backend.JWTTopic
import com.motycka.edu.content.topics.basics.functions.LambdaAndAnonymousFunctionsTopic
import com.motycka.edu.content.topics.basics.functions.ExtensionFunctionsTopic
import com.motycka.edu.content.topics.basics.functions.ScopeFunctionsTopic

val BusinessLogicAndSecurityLesson = Lesson(
    title = "Business Logic and Security",
    preTitle = "Week 2 | Lesson 6",
    subTitle = "Implementing business logic and security measures",
    topics = listOf(
        ServiceLayerTopic,
        AuthenticationTopic,
        AuthorizationTopic,
        BasicAuthTopic,
        JWTTopic,
        LambdaAndAnonymousFunctionsTopic,
        ExtensionFunctionsTopic,
        ScopeFunctionsTopic
    )
)
