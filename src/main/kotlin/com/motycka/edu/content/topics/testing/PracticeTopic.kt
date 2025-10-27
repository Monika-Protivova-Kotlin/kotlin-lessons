package com.motycka.edu.content.topics.testing

import kotlinx.html.*
import com.motycka.edu.model.Topic
import com.motycka.edu.model.Slide
import com.motycka.edu.model.kotlinPlayground
import com.motycka.edu.model.inlineCode

object PracticeTopic : Topic(
    title = "Practice",
    slides = listOf(
        PracticeSlide
    )
)

object PracticeSlide : Slide(
    header = "Assignment 06: Coffee Machine Testing",
    summary = {
        +"Write tests to find and fix bugs in a coffee machine simulator using Kotest"
    },
    content = {
        h4 { +"The Challenge" }
        p {
            +"You've been given a "; strong { +"CoffeeMachine" }; +" simulator that has bugs. "
            +"Your job is to write tests using Kotest to find and fix them."
        }

        h4 { +"The System" }
        p { +"A coffee machine that manages:" }
        ul {
            li { strong { +"Water" }; +": 30ml per espresso shot" }
            li { strong { +"Coffee beans" }; +": 5g (LIGHT), 7g (NORMAL), or 10g (STRONG)" }
            li { strong { +"Double espresso" }; +": Uses 2x the amounts" }
        }

        h4 { +"Your Tasks" }
        ol {
            li {
                strong { +"Write tests" }; +" covering:"
                ul {
                    li { +"Making espressos with different strengths" }
                    li { +"Resource consumption (water and beans decrease)" }
                    li { +"Exceptions when resources insufficient" }
                    li { +"Refilling water and beans" }
                }
            }
            li { strong { +"Find the bugs" }; +" - Some tests will fail!" }
            li { strong { +"Fix the bugs" }; +" in CoffeeMachine.kt" }
            li { strong { +"Verify" }; +" all tests pass" }
        }
    }
)
