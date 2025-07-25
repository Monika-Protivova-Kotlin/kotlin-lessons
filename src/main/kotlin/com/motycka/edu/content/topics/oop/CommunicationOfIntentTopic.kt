package com.motycka.edu.content.topics.oop

import kotlinx.html.*
import com.motycka.edu.model.Topic
import com.motycka.edu.model.Slide

object CommunicationOfIntentTopic : Topic(
    title = "Programming language",
    subtitle = "and communication of intent", 
    slides = listOf(
        CommunicationOfIntentSlide
    )
)

object CommunicationOfIntentSlide : Slide(
    header = "Communication of intent",
    summary = {
        +"Programming language provides means of expressing programmer's intent to a computer system."
    },
    content = {
        blockQuote {
            +"But programming it is not just a way of giving instructions to a computer. "
            +"It can also be a means of communication between humans, particularly in the context of team development, "
            +"code reviews, and future maintenance of the software. Here are few points to keep in mind ..."
        }
        h4 { +"Code Clarity" }
        p {
            +"Code is more often read than it is written. Therefore, it is important to keep it clean and easily understood."
        }
        br()
        h4 { +"Code Consistency" }
        p {
            +"Keeping your code consistent in terms of syntax, programming style and design patterns makes "
            +"it easier to understand."
        }
        br()
        h4 { +"Documentation and Comments" }
        p {
            +"Some code can become hard to understand despite our best effort. In these cases, "
            +"comments and code documentation should be used to clarify the programmer's intent "
            +"or communicate unintuitive information."
        }
    }
)