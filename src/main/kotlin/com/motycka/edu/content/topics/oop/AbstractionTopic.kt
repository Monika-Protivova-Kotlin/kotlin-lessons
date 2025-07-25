package com.motycka.edu.content.topics.oop

import com.motycka.edu.model.Topic

object AbstractionTopic : Topic(
    title = "Abstraction",
    subtitle = "OOP Principle #4",
    slides = listOf(
        AbstractClassOverviewSlide,
        AbstractClassExampleSlide,
        InterfacesOverviewSlide,
        InterfacesExampleSlide,
        MultipleInterfacesSlide,
        InterfaceInheritanceSlide
    )
)
