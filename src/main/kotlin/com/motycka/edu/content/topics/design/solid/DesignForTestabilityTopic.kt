package com.motycka.edu.content.topics.design.solid

import com.motycka.edu.model.Slide
import com.motycka.edu.model.Topic
import com.motycka.edu.model.highlight
import kotlinx.html.*

object DesignForTestabilityTopic : Topic(
    title = "Design for Testability",
    slides = listOf(
        DesignForTestabilitySlide
    )
)

object DesignForTestabilitySlide : Slide(
    header = "Design for Testability",
    summary = {
        +"Following SOLID principles naturally leads to more testable code by promoting better separation of concerns and dependency management."
    },
    content = {
        ul {
            li {
                strong {
                    highlight("Single Responsibility Principle (SRP)")
                }
                br()
                +"→ Smaller classes with one job are easier to isolate and test"
                br()
                br()
            }
            li {
                strong {
                    highlight("Open/Closed Principle (OCP)")
                }
                br()
                +"→ Extend functionality without changing existing code, reducing risk of breaking functionality"
                br()
                br()
            }
            li {
                strong {
                    highlight("Liskov Substitution Principle (LSP)")
                }
                br()
                +"→ Use derived classes without breaking functionality, guards against unwanted changes in behavior and also allows for flexible test doubles"
                br()
                br()
            }
            li {
                strong {
                    highlight("Interface Segregation Principle (ISP)")
                }
                br()
                +"→ Smaller, focused interfaces make it easier to implement functionality and also to mock them in tests"
                br()
                br()
            }
            li {
                strong {
                    highlight("Dependency Inversion Principle (DIP)")
                }
                br()
                +"→ Rely on interfaces, so you can easily swap in mocks or fakes"
                br()
                br()
            }
            li {
                strong {
                    highlight("Constructor Injection")
                }
                br()
                +"→ Makes it simple to provide test doubles without frameworks"
                br()
                br()
            }
            li {
                strong {
                    highlight("Fewer Dependencies = Simpler Tests")
                }
                br()
                +"→ Cleaner test setup, fewer mocks, and better reliability"
                br()
                br()
            }
        }
    }
)
