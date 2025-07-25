package com.motycka.edu.content.topics.testing

import kotlinx.html.*
import com.motycka.edu.model.Topic
import com.motycka.edu.model.Slide

object IntegrationTestingTopic : Topic(
    title = "Integration Testing",
    slides = listOf(
        IntegrationTestingSlide
    )
)

object IntegrationTestingSlide : Slide(
    header = "Integration Testing",
    summary = {
        +"Integration testing is a level of software testing, which aims to test the integration of different units or components of the system."
    },
    content = {
        p { +"Integration testing can be ..." }
        ul {
            li { +"Integration of different modules, classes, or services within the software." }
            li {
                +"Testing of the integration of other systems, such as ..."
                ul {
                    li { +"Operating System functions and services" }
                    li { +"Database, file systems, data sources" }
                    li { +"External services, APIs, message queues, cloud services" }
                }
            }
        }
        p {
            +"Integration tests are typically more costly to run than unit tests, "
            +"because they require more resources and are usually slower. "
            +"They may also be less reliable."
        }
        p {
            +"On the other hand, they provide more information about the system as a whole, and may uncover problems that are not visible at the unit level."
        }
    }
)