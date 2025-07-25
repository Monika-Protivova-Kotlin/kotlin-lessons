package com.motycka.edu.content.topics.testing

import kotlinx.html.*
import com.motycka.edu.model.Topic
import com.motycka.edu.model.Slide

object TestCasesTopic : Topic(
    title = "Test Case",
    slides = listOf(
        WhatIsTestCaseSlide,
        TestCaseExampleSlide
    )
)

object WhatIsTestCaseSlide : Slide(
    header = "What is a Test Case",
    summary = {
        +"Test case is a sequence of pre-conditions, inputs, actions steps with expected results and post-conditions, developed based on test conditions."
    },
    content = {
        p {
            h4 { +"Test condition" }
            +"is a testable aspect of a component or system identified as a basis for testing."
            br()
            br()
            em { +"In other words, some behavior we expect from the system." }
        }
        br()
        p {
            h4 { +"Test case" }
            +"is a sequence of pre-conditions, inputs, actions steps with expected results and post-conditions, developed based on test conditions."
            br()
            br()
            em { +"In other words, test case = a scenario describing how to test a particular test condition." }
        }
    }
)

object TestCaseExampleSlide : Slide(
    header = "Test Case",
    content = {
        p {
            strong { +"Test ID:" }; +" 1234"
            br()
        }
        p {
            strong { +"Title:" }; +" User is blocked after 3 failed login attempts"
            br()
        }
        p {
            strong { +"Pre-Conditions:" }
            br()
            +"User test.user@harbourspace.com exists and is not blocked."
        }
        p {
            strong { +"Test Steps:" }
            br()
        }
        table {
            thead {
                tr {
                    td { +"#" }
                    td { +"Step" }
                    td { +"Expected Result" }
                }
            }
            tbody {
                tr {
                    td { +"1." }
                    td { +"Open the login page" }
                    td { +"Login page is shown" }
                }
                tr {
                    td { +"2." }
                    td { +"Enter the username "; em { +"test.user@harbourspace.com" }; +" and password "; em { +"invalid" } }
                    td { +"User is not logged in, is informed of invalid credentials. Password field is nullified." }
                }
                tr {
                    td { +"3." }
                    td { +"Enter the password "; em { +"invalid" }; +" again" }
                    td { +"User is not logged in, is informed of invalid credentials. Password field is nullified." }
                }
                tr {
                    td { +"4." }
                    td { +"Enter the password "; em { +"invalid" }; +" again" }
                    td { +"User is not logged in, is informed that their account was locked." }
                }
            }
        }
        p {
            strong { +"Expected Result:" }
            br()
            +"User is not logged in and their account is locked."
        }
    }
)