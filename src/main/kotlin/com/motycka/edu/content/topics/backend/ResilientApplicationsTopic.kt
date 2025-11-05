package com.motycka.edu.content.topics.backend

import com.motycka.edu.model.Topic
import com.motycka.edu.model.Slide
import com.motycka.edu.model.highlight
import kotlinx.html.*
import com.motycka.edu.model.inlineCode
import com.motycka.edu.model.highlight
import com.motycka.edu.model.importantCard
import com.motycka.edu.model.infoCard
import com.motycka.edu.model.twoColumns

object ResilientApplicationsTopic : Topic(
    title = "Developing Resilient Applications",
    slides = listOf(
        ResilientApplicationsSlide
    )
)

object ResilientApplicationsSlide : Slide(
    header = "Developing Resilient Applications",
    summary = {
        +"Building robust backends means expecting failure and responding clearly, consistently, and safely."
    },
    content = {
        twoColumns(
            left = {
                ul {
                    li {
                        strong { highlight("Validate Early") }
                        br()
                        +"Use "
                        inlineCode("require")
                        +", "
                        inlineCode("check")
                        +", and null checks to catch errors before they propagate."
                        br()
                        br()
                    }
                    li {
                        strong { highlight("Fail Fast, Fail Loud") }
                        br()
                        +"Throw descriptive exceptions when invariants are broken."
                        br()
                        br()
                    }
                    li {
                        strong { highlight("Define Custom Exceptions") }
                        br()
                        +"Clarify intent and aid in debugging and response formatting."
                        br()
                        br()
                    }
                    li {
                        strong { highlight("Handle Exceptions Globally") }
                        br()
                        +"Centralize response behavior with "
                        inlineCode("StatusPages")
                        +" for consistency."
                        br()
                        br()
                    }
                    li {
                        strong { highlight("Log Errors with Context") }
                        br()
                        +"Include trace IDs and timestamps to help trace and debug issues."
                        br()
                        br()
                    }
                    li {
                        strong { highlight("Use Standard Error Responses") }
                        br()
                        +"Clients benefit from consistent, parseable error formats."
                        br()
                        br()
                    }
                    li {
                        strong { highlight("Test Failure Scenarios") }
                        br()
                        +"Robust apps are not those that never fail, but those that fail gracefully."
                        br()
                        br()
                    }
                }
            },
            right = {
                importantCard(header = "Remember" ) {
                    +"Resilient applications expect the unexpected and handle errors gracefully while providing clear feedback to both users and developers."
                }
            }
        )
    }
)
