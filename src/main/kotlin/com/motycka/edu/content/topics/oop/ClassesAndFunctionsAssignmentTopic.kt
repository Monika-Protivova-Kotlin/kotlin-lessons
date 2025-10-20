package com.motycka.edu.content.topics.oop

import com.motycka.edu.model.Slide
import com.motycka.edu.model.Topic
import com.motycka.edu.model.inlineCode
import kotlinx.html.*

object ClassesAndFunctionsAssignmentTopic : Topic(
    title = "Assignment: Fantasy.Space Data Models",
    slides = listOf(
        AssignmentInfoSlide,
    )
)

object AssignmentInfoSlide : Slide(
    header = "Assignment: Fantasy.Space Data Models",
    summary = {
        +"Build the core data models for the Fantasy.Space game using classes, data classes, enums, and singletons."
    },
    content = {
        p { +"In this assignment, you'll create:" }
        ul {
            li { +"A "; inlineCode("Character"); +" data class to represent game characters" }
            li { +"A "; inlineCode("CharacterClass"); +" enum for different character classes" }
            li { +"A "; inlineCode("Match"); +" singleton to run a match" }
            li { +"A "; inlineCode("MatchOutcome"); +" enum for match result" }
            li { +"A "; inlineCode("MatchResult"); +" data class enum holding information about challenger and opponent and the match outcome" }
        }
        br()
        p { +"These models will form the foundation of your Fantasy.Space game!" }
    }
)
