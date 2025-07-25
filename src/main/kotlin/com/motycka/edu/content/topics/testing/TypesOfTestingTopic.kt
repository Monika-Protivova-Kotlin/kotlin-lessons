package com.motycka.edu.content.topics.testing

import kotlinx.html.*
import com.motycka.edu.model.Topic
import com.motycka.edu.model.Slide
import com.motycka.edu.model.twoColumns

object TypesOfTestingTopic : Topic(
    title = "Types of testing",
    slides = listOf(
        TestingByKnowledgeSlide,
        TestingByExecutionSlide,
        FunctionalVsNonFunctionalSlide
    )
)

object TestingByKnowledgeSlide : Slide(
    header = "Types of testing",
    summary = {
        +"Testing based on the internal knowledge of the system"
    },
    content = {
        p {
            +"There are two types of testing based on the testers knowledge of the system internal structure/design/implementation."
        }
        br()
        p {
            h4 { +"Blackbox Testing" }
            +"Internal structure of the system is "
            strong { +"not known" }
            +" to the tester."
        }
        p {
            h4 { +"Whitebox Testing" }
            +"Internal structure of the system is "
            strong { +" known" }
            +" to the tester."
        }
        br()
        p {
            h4 { +"Greybox Testing" }
            +"Sometimes, this term is used when the internal structure of the system is "
            strong { +"partially known" }
            +" to the tester."
        }
    }
)

object TestingByExecutionSlide : Slide(
    header = "Types of testing",
    summary = {
        +"Testing based on code execution"
    },
    content = {
        twoColumns(
            left = {
                h4 { +"Dynamic" }
                ul {
                    li { +"The tested system code is executed during testing" }
                    li {
                        +"Dynamic testing can further be divided into"
                        ul {
                            li { +"Functional" }
                            li { +"Non-functional" }
                        }
                    }
                }
            },
            right = {
                h4 { +"Static" }
                ul {
                    li { +"Code is not executed during testing" }
                    li { +"Static analysis usually involves the use of tools" }
                    li { +"Code review" }
                    li { +"Document reviews - specifications, requirement lists, tests, etc" }
                    li { +"Best practices" }
                }
            }
        )
    }
)

object FunctionalVsNonFunctionalSlide : Slide(
    header = "Functional vs. Non-Functional Testing",
    summary = {
        +"We can also distinguish between functional and non-functional testing."
    },
    content = {
        p {
            h4 { +"Functional testing" }
            +"is testing of the functionality of the system, meaning testing of functions of the system as a real user would use it."
            br()
            br()
            +"During functional testing, system functions and features are exercised by providing appropriate inputs and verifying that the outputs are as expected."
        }
        br()
        p {
            h4 { +"Non-functional testing" }
            +"is testing of the non-functional aspects of the system."
            br()
            br()
            +"Some examples of non-functional testing include: "
            br()
            em { +"Performance, Security, Usability, Interoperability, Compatibility, Compliance, etc." }
        }
    }
)
