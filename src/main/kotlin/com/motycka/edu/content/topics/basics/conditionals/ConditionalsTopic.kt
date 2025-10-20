package com.motycka.edu.content.topics.basics.conditionals

import com.motycka.edu.content.topics.basics.conditionals.`if`.IfElseKotlinFeaturesSlide
import com.motycka.edu.content.topics.basics.conditionals.`if`.IfElseSlide
import com.motycka.edu.content.topics.basics.conditionals.loops.BasicForLoopSlide
import com.motycka.edu.content.topics.basics.conditionals.loops.WhileLoopExamplesSlide
import com.motycka.edu.content.topics.basics.conditionals.loops.WhileLoopSlide
import com.motycka.edu.content.topics.basics.conditionals.`when`.WhenExampleSlide
import com.motycka.edu.content.topics.basics.conditionals.`when`.WhenSlide
import com.motycka.edu.model.Topic

object ConditionalsTopic : Topic(
    title = "Conditionals",
    subtitle = "Control Flow Statements",
    slides = listOf(
        IfElseSlide,
        IfElseKotlinFeaturesSlide,
        WhenSlide,
        WhenExampleSlide,
        BasicForLoopSlide,
        WhileLoopSlide,
        WhileLoopExamplesSlide
    )
)

