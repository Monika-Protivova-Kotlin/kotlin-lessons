package com.motycka.edu.model

import kotlinx.html.SectioningOrFlowContent
import kotlinx.html.br
import kotlinx.html.em
import kotlinx.html.h1
import kotlinx.html.h2
import kotlinx.html.h3
import kotlinx.html.p
import kotlinx.html.section
import kotlinx.html.style
import java.time.LocalDate

open class Lesson(
    val title: String,
    val preTitle: String? = null,
    val subTitle: String? = null,
    val summary: String? = null,
    val contentInfo: List<String> = emptyList(),
    val topics: List<Topic>
)

fun SectioningOrFlowContent.lessonIntro(
    lesson: Lesson,
) {
    section {
        attributes["data-background-gradient"] = "var(--kotlin-gradient)"
        lesson.preTitle?.let {
            p { +it }
        }
        h1 { +lesson.title }
        p {
            lesson.subTitle?.let { em { +it } }
        }
        p {
            lesson.contentInfo.forEach {
                em { +it }
                br()
            }
        }
        br()
        br()
        em { +"© ${LocalDate.now().year} by Monika Protivová" }
    }
}
