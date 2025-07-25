package com.motycka.edu.content.topics.design.solid

import com.motycka.edu.model.Topic
import com.motycka.edu.model.highlight
import com.motycka.edu.model.Slide
import com.motycka.edu.model.kotlinPlayground
import kotlinx.html.*
import com.motycka.edu.model.highlight

object SolidPrinciplesTopic : Topic(
    title = "The SOLID Principle",
    slides = listOf(
        SolidIntroSlide,
        SingleResponsibilityPrincipleSlide,
        OpenClosedPrincipleSlide,
        LiskovSubstitutionPrincipleSlide,
        InterfaceSegregationPrincipleSlide,
        InterfaceSegregationPrincipleExampleSlide,
        DependencyInversionPrincipleSlide
    )
)

object SolidIntroSlide : Slide(
    header = "The SOLID Principle",
    summary = {
        +"The "
        highlight("SOLID")
        +" principle is a set of five principles that help us write better code, "
        +"making it more maintainable, readable, and easier to upgrade and modify."
    },
    content = {
        p {
            +"These principles are not specific to Java, but they are applicable to any object-oriented language."
        }
        p {
            +"The "
            highlight("SOLID")
            +" principle is an acronym for the following:"
        }
        ul {
            li {
                highlight("S")
                +"ingle Responsibility Principle"
            }
            li {
                highlight("O")
                +"pen/Closed Principle"
            }
            li {
                highlight("L")
                +"iskov Substitution Principle"
            }
            li {
                highlight("I")
                +"nterface Segregation Principle"
            }
            li {
                highlight("D")
                +"ependency Inversion Principle"
            }
        }
    }
)

object SingleResponsibilityPrincipleSlide : Slide(
    header = "Single Responsibility Principle",
    summary = {
        +"Each class should have a single responsibility or reason to change. "
        +"This helps to build a system that is better defined, modular, and easier to maintain."
    },
    content = {
        p {
            +"We have seen this principle applied when we talked about encapsulation. Also, you will "
            +"find that if this principle is applied correctly, your code will be much easier to test."
        }
    }
)

object OpenClosedPrincipleSlide : Slide(
    header = "Open/Closed Principle",
    summary = {
        +"Software entities (classes, modules, functions, etc.) should be open for extension but closed for modification. "
        +"This prevents issues introduced by changes to existing code."
    },
    content = {
        ul {
            li {
                highlight("Open for extension")
                br()
                +"means that the class should be designed in such a way that "
                +"it can be extended to perform new things."
                br()
                br()
                +"Example of this would be extending class with methods to handle new requirements without modifying the existing ones."
                br()
                br()
            }
            li {
                highlight("Closed for modification")
                br()
                +"means that once the class has been developed and tested, the code behavior must not change."
                br()
                br()
                +"Example of this would be that the class should not be modified to handle new requirements,"
                br()
                +"but rather extended, as explained above."
                br()
                br()
            }
        }
    }
)

object LiskovSubstitutionPrincipleSlide : Slide(
    header = "Liskov Substitution Principle",
    summary = {
        +"One should be able to use any derived class instead of a parent class and have it behave in the same manner without modification."
    },
    content = {
        p {
            +"We have already seen the Liskov principle applied when we worked with inheritance, abstract classes and interfaces."
        }
    }
)

object InterfaceSegregationPrincipleSlide : Slide(
    header = "Interface Segregation Principle",
    summary = {
        +"Many specific client-specific interfaces are better than one general-purpose interface. "
        +"This means that you should not impose the clients with interfaces that they don't use."
    },
    content = {
        p {
            +"Imagine you have a class that represents a printer. "
            +"You could have the class implement one interface with methods print(), scan(), etc."
        }
        kotlinPlayground(
            code = """
                interface Printer {
                    fun print()
                    fun scan()
                }

                class SimplePrinter : Printer { /* ... */ }
                class MultiPrinter : Printer { /* ... */ }
            """.trimIndent(),
            executable = false
        )
        p {
            +"Or you could have the class implement multiple interfaces, each with a single method."
        }
    }
)

object InterfaceSegregationPrincipleExampleSlide : Slide(
    header = "Interface Segregation Principle - Better Approach",
    summary = {
        +"Using multiple specific interfaces instead of one general interface."
    },
    content = {
        kotlinPlayground(
            code = """
                interface Printing {
                    fun print()
                }

                interface Scanning {
                    fun scan()
                }

                class SimplePrinter : Printing { /* ... */ }
                class MultiPrinter : Printing, Scanning { /* ... */ }
            """.trimIndent(),
            executable = false
        )
        p {
            +"This way, "
            code("hljs inline") { +"SimplePrinter" }
            +" only implements the interface it needs, and "
            code("hljs inline") { +"MultiPrinter" }
            +" implements both interfaces as required."
        }
    }
)

object DependencyInversionPrincipleSlide : Slide(
    header = "Dependency Inversion Principle",
    summary = {
        +"One should depend upon abstractions, rather than concrete implementations. "
        +"This way, modules can remain decoupled, leading to systems that are easier to refactor, change, and deploy."
    },
    content = {
        p {
            +"This is commonly demonstrated when working with collections. "
            +"We will also see how this is helpful once we start developing applications using Inversion of Control."
        }
    }
)
