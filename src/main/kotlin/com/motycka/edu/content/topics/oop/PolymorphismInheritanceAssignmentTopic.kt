package com.motycka.edu.content.topics.oop

import com.motycka.edu.model.Slide
import com.motycka.edu.model.Topic
import com.motycka.edu.model.inlineCode
import kotlinx.html.*

object PolymorphismInheritanceAssignmentTopic : Topic(
    title = "Assignment: Fantasy.Space OOP Principles",
    slides = listOf(
        PolymorphismInheritanceAssignmentSlide,
    )
)

object PolymorphismInheritanceAssignmentSlide : Slide(
    header = "Assignment: Fantasy.Space OOP Principles",
    summary = {
        +"Apply OOP principles (Encapsulation, Inheritance, Polymorphism, Abstraction) using abstract classes and interfaces."
    },
    content = {
        p { +"In this assignment, you'll refactor and enhance the Fantasy.Space game by:" }
        ul {
            li { +"Making "; inlineCode("Character"); +" abstract with proper "; strong { +"encapsulation" }; +" (private currentHealth)" }
            li { +"Creating interfaces: "; inlineCode("Recoverable"); +", "; inlineCode("Defender"); +", "; inlineCode("Healer") }
            li { +"Implementing "; inlineCode("Warrior"); +" class (Recoverable + Defender interfaces)" }
            li { +"Implementing "; inlineCode("Sorcerer"); +" class (Recoverable + Healer interfaces)" }
            li { +"Adding resource management (stamina for Warriors, mana for Sorcerers)" }
            li { +"Updating "; inlineCode("Match"); +" object with "; inlineCode("beforeRound()"); +" and "; inlineCode("afterRound()"); +" calls" }
            li { +"Updating CSV file loading to support class-specific properties" }
        }
        br()
        p { strong { +"Key Concepts:" } }
        ul {
            li { strong { +"Encapsulation" }; +" - Private currentHealth with controlled access" }
            li { strong { +"Inheritance" }; +" - Warrior/Sorcerer extend abstract Character" }
            li { strong { +"Polymorphism" }; +" - Different behavior for attack(), beforeRound(), etc." }
            li { strong { +"Abstraction" }; +" - Abstract class and interfaces define contracts" }
        }
    }
)
