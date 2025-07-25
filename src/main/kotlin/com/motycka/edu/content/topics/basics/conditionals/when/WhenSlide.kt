package com.motycka.edu.content.topics.basics.conditionals.`when`

import com.motycka.edu.model.Slide
import kotlinx.html.p

object WhenSlide : Slide(
    header = "when",
    summary = {
        +"One of the most powerful control flow statements in Kotlin is when."
    },
    content = {
        p {
            +"It is similar to switch in Java, but it is more powerful."
        }
    }
)
