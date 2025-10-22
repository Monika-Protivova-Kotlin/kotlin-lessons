package com.motycka.edu.content.topics.basics.collections

import com.motycka.edu.model.Slide
import com.motycka.edu.model.Topic
import com.motycka.edu.model.highlight
import kotlinx.html.*

object CollectionsPracticeTopic : Topic(
    title = "Collections Practice - Fantasy Game",
    slides = listOf(
        CollectionsPracticeSlide,
    )
)

object CollectionsPracticeSlide : Slide(
    header = "Practice: Fantasy.Space Game",
    summary = {
        +"Let's extend out game with new features using collections!"
    },
    content = {
        p {
            +"In this practice exercise, you'll create a game tournament system with leaderboard."
        }
//        ul {
//            li { +"Data classes and interfaces" }
//            li { +"Enums for character progression" }
//            li { +"Collection operations for simulating battles" }
//            li { +"Transformations for calculating results" }
//        }
//        p {
//            +"We'll build this incrementally in 3 parts:"
//        }
//        ol {
//            li { strong { highlight("Match System") }; +" - Create classes to represent character battles" }
//            li { strong { highlight("Recovery Mechanic") }; +" - Add healing between battles" }
//            li { strong { highlight("Level System") }; +" - Implement character progression with experience points" }
//        }
//        blockQuote {
//            +"This exercise brings together data modeling, OOP principles, and collection operations "
//            +"in a practical, real-world scenario similar to what you'd encounter in game development."
//        }
    }
)
