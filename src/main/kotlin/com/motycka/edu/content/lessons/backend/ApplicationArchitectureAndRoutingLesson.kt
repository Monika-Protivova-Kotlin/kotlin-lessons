package com.motycka.edu.content.lessons.backend

import com.motycka.edu.content.topics.introductory.ApplicationArchitectureTopic
import com.motycka.edu.content.topics.basics.api.WhatIsAPITopic
import com.motycka.edu.content.topics.basics.api.WhatIsRESTTopic
import com.motycka.edu.content.topics.basics.api.APIPracticeTopic
import com.motycka.edu.model.Lesson

val ApplicationArchitectureAndRoutingLesson = Lesson(
    title = "Application Architecture and Routing",
    preTitle = "Week 2 | Lesson 5",
    subTitle = "Backend architecture patterns and routing",
    topics = listOf(
        WhatIsAPITopic,
        WhatIsRESTTopic,
        ApplicationArchitectureTopic,
        APIPracticeTopic
    )
)