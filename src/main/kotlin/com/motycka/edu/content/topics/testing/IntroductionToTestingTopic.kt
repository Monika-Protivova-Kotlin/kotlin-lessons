package com.motycka.edu.content.topics.testing

import kotlinx.html.*
import com.motycka.edu.model.Topic
import com.motycka.edu.model.Slide
import com.motycka.edu.model.imgByName
import com.motycka.edu.model.twoColumns

object IntroductionToTestingTopic : Topic(
    title = "Introduction to Software Testing",
    slides = listOf(
        WhatIsTestingSlide,
        WhatIsQualitySlide,
        ExternalInternalQualitySlide,
        SevenPrinciplesOfTestingSlide,
        TestingPyramidSlide,
        CostOfDefectsSlide
    )
)

object WhatIsTestingSlide : Slide(
    header = "What is Testing",
    content = {
        ul {
            li { +"Testing aims to determine the degree of alignment between reality and expectations." }
            li { +"It helps measure quality but cannot directly influence it." }
            li { +"It provides information to stakeholders." }
            li { +"It is an ongoing activity, not a development phase." }
            li { +"It is the responsibility of the entire team, not an isolated role." }
            li {
                +"The goal of testing is to:"
                ul {
                    li { +"Verify that the product does what is expected of it." }
                    li { +"Provide information." }
                    li { +"Identify problems, not just bugs." }
                    li { +"Reduce risks." }
                }
            }
            li { +"The goal of testing is not to make decisions but to provide information to support decision-making (the tester is not the decision-maker)." }
        }
    }
)

object WhatIsQualitySlide : Slide(
    header = "What is Quality",
    summary = {
        p { +"What is quality, and how does it relate to testing and the product?" }
        p { +"Is a product considered good quality if it contains no errors?" }
    },
    content = {
        div("fragment fade-in") {
            blockQuote {
                +"A product is something someone desires because it satisfies their needs."
            }
        }
        div("fragment fade-in") {
            p { +"We can view the quality of a product from two perspectives:" }
        }
        div("fragment fade-in") {
            blockQuote {
                em { strong { +"What the product does" }; +" = external quality." }
                br()
                +"&"
                br()
                em { strong { +"How it does it" }; +" = internal quality." }
            }
        }
    }
)

object ExternalInternalQualitySlide : Slide(
    header = "External and Internal Quality",
    summary = {
        +"External quality focuses on the user's perspective, while internal quality emphasizes the code's structure and maintainability."
    },
    content = {
        twoColumns(
            left = {
                h4 { +"External Quality" }
                br()
                ul {
                    li { +"Does the product fulfill user's needs?" }
                    li { +"Does it operate in a way that is usable for the user?" }
                }
            },
            right = {
                h4 { +"Internal Quality" }
                br()
                ul {
                    li { +"Is the software well written?" }
                    li { +"Is the code readable and understandable?" }
                    li { +"Is the code designed well?" }
                    li { +"Is the code testable? Is the test coverage sufficient?" }
                    li { +"Is there sufficient documentation?" }
                    li { +"Is there sufficient logging?" }
                }
            }
        )
        p {
            +"While it is possible for product with relatively low internal quality to have high external quality, "
            +"it is not surprising, that the two usually correlate. When software is testable, it is easier to extend and maintain, "
            +"requiring both less skill and time, making it more resistant to "
            strong { +"regression" }
            +"."
            blockQuote {
                strong { +"Regression" }
                +" == in terms of testing, regression is a defect unintentionally introduced by a change into a previously working part of software."
            }
        }
    }
)

object SevenPrinciplesOfTestingSlide : Slide(
    header = "7 principles of testing",
    content = {
        ol {
            li { +"Testing shows the presence of defects, not their absence" }
            li { +"Exhaustive testing is not possible" }
            li { +"Early testing saves time and money" }
            li { +"Defect have a tendency to cluster" }
            li { +"The Pesticide Paradox" }
            li { +"Testing is context dependent" }
            li { +"Absence of errors fallacy" }
        }
    },
    textAlign = "center",
    fontSize = "100%"
)

object TestingPyramidSlide : Slide(
    header = "The Testing Pyramid",
    content = {
        img("Testing Pyramid", imgByName("testing_pyramid"))
    }
)

object CostOfDefectsSlide : Slide(
    header = "The Cost of Defects",
    content = {
        img("Cost of Defects", imgByName("cost_of_defects"))
    }
)
