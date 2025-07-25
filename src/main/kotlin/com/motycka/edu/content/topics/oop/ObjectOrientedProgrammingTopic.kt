package com.motycka.edu.content.topics.oop

import com.motycka.edu.model.highlight
import com.motycka.edu.builders.*
import com.motycka.edu.model.Topic
import com.motycka.edu.model.Slide
import kotlinx.html.br
import kotlinx.html.em
import kotlinx.html.p
import com.motycka.edu.model.highlight

object ObjectOrientedProgrammingTopic : Topic(
    title = "Object-Oriented Programming",
    slides = listOf(
        ObjectOrientedProgrammingIntroSlide
    )
)

object ObjectOrientedProgrammingIntroSlide : Slide(
    header = "Object-Oriented Programming",
    summary = {
        +"There are four main principles in Object-Oriented Programming ..."
        highlight("Object-Oriented Programming")
        +" ..."
    },
    content = {
        highlightNumberedList(
            startFrom = 1,
            "Encapsulation" to {
                +"is a concept of controlling access to the internal state of an object, protecting it from unauthorized access and ensuring data integrity."
                br()
            },
            "Inheritance" to {
                +"enables a class to have the same behavior as another class by inheriting its properties and methods."
                br()
            },
            "Polymorphism" to {
                +"allows us to define one interface or method that can have multiple implementations."
                br()
                +"It means that the same method or property could exhibit different behavior in different instances of object implementing given interface."
                br()
            },
            "Abstraction" to {
                +"is a mechanism to represent the object features without exposing the actual implementation details."
                br()
                +"In other words, user of such object only needs to know what it does, not how it does it."
                br()
            }
        )
        p {
            em { +"We will come back to these principles later in the course." }
        }
    }
)
