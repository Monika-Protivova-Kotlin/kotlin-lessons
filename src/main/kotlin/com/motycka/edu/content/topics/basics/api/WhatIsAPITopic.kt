package com.motycka.edu.content.topics.basics.api

import com.motycka.edu.model.Slide
import com.motycka.edu.model.Topic
import kotlinx.html.*

object WhatIsAPITopic : Topic(
    title = "What is API",
    slides = listOf(
        WhatIsAPISlide,
        DesignOfAPISlide
    )
)

object WhatIsAPISlide : Slide(
    header = "What is API",
    summary = {
        +"API stands for "
        strong { +"Application Programming Interface" }
        +"."
    },
    content = {
        ul {
            li {
                +"It is a set of rules and protocols that allow different software applications to communicate with each other."
            }
            li {
                +"In contrast to user interface, API is meant for program to program or computer to computer communication."
            }
            li {
                +"There are many forms of APIs, such as web APIs, library APIs, and operating system APIs. "
                +"Some APIs are specific to a particular programming language, some are specific to a particular application."
            }
        }
    }
)

object DesignOfAPISlide : Slide(
    header = "Design of API",
    content = {
        blockQuote {
            +"When you think about designing an interface, you first need to think about the problem you are trying to solve with it. "
            +"Consider how the API will be used, and design it in a way that is convenient for the users of the API."
        }
        p {
            +"This is why in this course, we will start by designing and interface (API) and only then we will implement the service and data model behind it."
        }
        p {
            +"If we did it the other way around, we would be tempted to design the service and data model in a way that is convenient for us, the developers, "
            +"and not in a way that is convenient for the users of the API."
        }
    }
)