package com.motycka.edu.content.topics.design

import com.motycka.edu.model.Topic
import com.motycka.edu.model.Slide
import kotlinx.html.*

object DesignPatternsTopic : Topic(
    title = "Design Patterns",
    slides = listOf(
        DesignPatternsIntroSlide
    )
)

object DesignPatternsIntroSlide : Slide(
    header = "Design Patterns",
    summary = {
        +"In software engineering, a design pattern is a general repeatable solution to a commonly occurring "
        +"problem in software design. They are best practices that the programmer can use to solve common problems when designing an application or system."
    },
    content = {
        p { +"As a simplification, we can divide design patterns into 3 categories ..." }
        ul {
            li {
                strong { +"Creational Design Patterns" }
                +" deal with object creation mechanisms, helping with complexities of object creation and providing convenient ways to do so."
                br()
                sub { +"Builder Pattern, Singleton Pattern, Factory Pattern, Abstract Factory Pattern, Prototype Pattern ..." }
                br()
                br()
            }
            li {
                strong { +"Structural Design Patterns" }
                +" concern with composition of classes and objects which form larger structures."
                br()
                sub { +"Decorator Pattern, Adapter Pattern, Proxy Pattern, Composite Pattern, Bridge Pattern ..." }
                br()
                br()
            }
            li {
                strong { +"Behavioral Design Patterns" }
                +" are specifically concerned with communication between objects, how they interact, and distribute the work."
                br()
                sub { +"Observer Pattern, Strategy Pattern, Iterator Pattern, Command Pattern, Template Method Pattern ..." }
            }
        }
        hr()
        p {
            +"I will not go into detail, but I encourage you "
            a(href = "https://en.wikipedia.org/wiki/Software_design_pattern") { +"to read about them" }
            +"."
        }
    }
)