package com.motycka.edu.content.topics.introductory

import com.motycka.edu.model.Slide
import com.motycka.edu.model.Topic
import com.motycka.edu.model.nestedList
import com.motycka.edu.model.twoColumns
import kotlinx.html.br
import kotlinx.html.span

object DevelopmentEnvironmentTopic : Topic(
    title = "Development Environment",
    subtitle = "IDE",
    slides = listOf(WhatIsIDESlide)
)

object WhatIsIDESlide: Slide(
    header = "What is Integrated Development Environment (IDE)",
    summary = {
        span { + "Being able to efficiently use an IDE is just as important as being able to write code." }
        br()
        span { +"Gives you a set of tools that you need as a developer." }
    },
    content = {
        twoColumns(
            left = {
                nestedList(
                    "Provides code editor with syntax highlighting" to emptyList(),
                    "Easy ways to manage code, project and builds" to emptyList(),
                    "Helps your productivity" to listOf(
                        "contextual suggestions",
                        "autocomplete",
                        "code navigation",
                        "refactoring",
                        "language tips"
                    ),
                    "Helps you avoid some errors in your programs" to listOf(
                        "can warn you about problematic code",
                        "gives you tools inspect your code",
                        "gives you tools test your code"
                    )
                )
            },
            right = {
                nestedList(
                    "Integrates tools" to listOf(
                        "source code management, such as git",
                        "database clients",
                        "documentation",
                        "project management tools",
                        "AI"
                    ),
                    "Usually has a community led plugin ecosystem" to emptyList()
                )
            }
        )
    }
)
