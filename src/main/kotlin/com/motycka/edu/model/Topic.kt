package com.motycka.edu.model

import kotlinx.html.FlowContent
import kotlinx.html.em
import kotlinx.html.h2
import kotlinx.html.p
import kotlinx.html.section

open class Topic(
    val preTitle: String? = null,
    val title: String,
    val subtitle: String? = null,
    val slides: List<Slide> = emptyList(),
)

open class ExerciseTopic(
    preTitle: String? = null,
    title: String,
    subtitle: String? = null,
    slides: List<Slide> = emptyList(),
) : Topic(preTitle, title, subtitle, slides)

fun FlowContent.topic(topic: Topic) {
    val isExercise = topic is ExerciseTopic
    section {
        // Skip initial slide for exercises
        if (!isExercise) {
            section {
                attributes["data-background-gradient"] = "var(--kotlin-gradient)"
                topic.preTitle?.let { p { +it } }
                h2 { +topic.title }
                topic.subtitle?.let {
                    em { +it }
                }
            }
        }
        topic.slides.forEach { slide -> slide(slide, isExercise) }
    }
}
