package com.motycka.edu.content.topics.testing

import kotlinx.html.*
import com.motycka.edu.model.Topic
import com.motycka.edu.model.Slide

object UnitTestingTopic : Topic(
    title = "Unit Testing",
    slides = listOf(
        UnitTestingOverviewSlide,
        AssertionSlide,
        TestDrivenDevelopmentSlide
    )
)

object UnitTestingOverviewSlide : Slide(
    header = "Unit Testing",
    summary = {
        +"The purpose of unit testing is to verify individual units of the code base work as intended by the author. "
        +"It is an essential tool in maintaining "
        strong { +"internal quality" }
        +" of a software."
    },
    content = {
        p {
            h4 { +"Unit" }
            +"Unit is the smallest testable parts of the software, such as individual method, function or objects."
        }
        br()
        p {
            +"Another important role of unit testing is documentation. By writing unit tests, we document the behavior "
            +"we intended, so that when we, or someone else wants to make changes in the software, they will understand "
            +"how the software was supposed to work."
        }
    }
)

object AssertionSlide : Slide(
    header = "Assertion",
    summary = {
        strong { +"Assertion" }
        +" is a term used form mechanism of verifying if test expected outcomes match actual outcomes."
    },
    content = {
        ul {
            li { +"Assertion itself is usually a function (method) that we call in our tests which evaluates actual value with expected value." }
            li {
                +"Based on result of this evaluation, the assertion ends in one of two states:"
                br()
                br()
                div {
                    style = "text-align: center; font-size: 110%"
                    em { style = "color: green; font-weight: bold"; +"PASSED" }
                    +" or "
                    em { style = "color: red; font-weight: bold"; +"FAILED" }
                }
                br()
                br()
            }
            li { +"Test may contain any number of assertions, anywhere within the test." }
            li { +"When a test is run, and no assertion fails, the test is marked as passed." }
            li { +"When a test is run, and any assertion fails, the test is marked as failed." }
            li {
                +"Generally, when assertion fails, test is ended immediately."
                br()
                +"Any code following the assertion is not executed."
            }
        }
    }
)

object TestDrivenDevelopmentSlide : Slide(
    header = "Test Driven Development",
    summary = {
        +"You may encounter the term "
        strong { +"Test Driven Development" }
        +" (TDD). Know that, although the term suggest it might be testing technique, it really is not. "
        +"Rather it is a software design technique."
    },
    content = {
        ol {
            li { +"In TDD, you write a tests first, they will initially be failing." }
            li { +"Then you start implement the functionality." }
            li { +"When all the tests finally pass, your implementation is complete." }
        }
        p {
            +"The reason TDD is development technique and not a test technique is because by writing tests "
            +"first, you are making code testable by design. Well-testable code usually directly correlates with "
            +"code quality and therefore overall software quality."
        }
    }
)